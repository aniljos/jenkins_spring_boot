package com.dbs.appservices.controller;

import com.dbs.appservices.model.Customer;
import com.dbs.appservices.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

// Fetch all customers ==> GET /customers
// Fetch customer by the ID ==> GET /customers/{id} Success: 200 Error: 404(Not Found), 500(ISR)
// New customer => POST /customers Success: 201(created): Error: 400(Bad request), 500(ISR)
// Delete => DELETE /customers/{id} Success: 200 Error: 404(Not Found), 500(ISR)
// Update => PUT /customers success: 200, Error: 400(Bad request), 500(ISR)


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping
    public List<Customer> fetchCustomers(){
        return repository.findAll();
    }

    // Fetch customer by the ID ==> GET /customers/{id} Success: 200 Error: 404(Not Found), 500(ISR)
    //@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> fetchById(@PathVariable int id){

        try {
            Customer customer = repository.fetchCustomerById(id);
            if(customer != null){
                return ResponseEntity.ok(customer);
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    // New customer => POST /customers Success: 201(created): Error: 400(Bad request), 500(ISR)

    @PostMapping
    public ResponseEntity<Void> saveCustomer(@RequestBody Customer customer){

        try {

            boolean isSaved = repository.saveCustomer(customer);
            return isSaved ? ResponseEntity.created(new URI("/")).build() : ResponseEntity.badRequest().build();
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Delete => DELETE /customers/{id} Success: 200 Error: 404(Not Found), 500(ISR)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id){

        try {

            boolean isDeleted = repository.deleteCustomer(id);
            return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // Update => PUT /customers success: 200, Error: 400(Bad request), 500(ISR)
}
