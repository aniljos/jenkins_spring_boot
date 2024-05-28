package com.dbs.appservices.controller;

import com.dbs.appservices.entity.AccountEntity;
import com.dbs.appservices.repository.AccountRepository;
import com.dbs.appservices.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private AccountService service;

    @PostMapping
    public ResponseEntity<Void> Save(@RequestBody AccountEntity entity){
        try {

            AccountEntity savedEntity =  repository.save(entity);
            return ResponseEntity.created(new URI("/" + savedEntity.getId())).build();


        }catch(Exception ex){
            ex.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AccountEntity>> fetchByName(@RequestParam String name){
        try {

            List<AccountEntity> results =  repository.findByName(name);
            return ResponseEntity.ok(results);


        }catch(Exception ex){
            ex.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountEntity> fetchById(@PathVariable Long  id){
        try {

            Optional<AccountEntity> account =  repository.findById(id);
            if(account.isPresent())
                return ResponseEntity.ok(account.get());
            else
                return ResponseEntity.badRequest().build();


        }catch(Exception ex){
            ex.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/transfer/{from}/{to}/{amt}")
    public ResponseEntity<Void> transfer(@PathVariable Long  from, @PathVariable Long to, @PathVariable double amt){
        try {

            service.transfer(from, to, amt);
            return ResponseEntity.ok().build();

        }catch(Exception ex){
            ex.printStackTrace();
            return  ResponseEntity.internalServerError().build();
        }
    }
}
