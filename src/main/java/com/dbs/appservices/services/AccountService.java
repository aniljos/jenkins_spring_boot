package com.dbs.appservices.services;

import com.dbs.appservices.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository repository;


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void deposit(Long id, double amt) throws Exception {


        try {
            int rowsAffected = repository.deposit(id, amt);
            if(rowsAffected !=1){
                throw new Exception("Deposit failed");
            }
            System.out.println("AccountService.deposit " + id);
        } catch (Exception e) {
            throw new Exception("deposit failed");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void withdraw(Long id, double amt) throws Exception {

        try {
            double balance = repository.getBalanceById(id);
            System.out.println("AccountService.withdraw" + balance);
            if(balance > amt) {
                int rowsAffected = repository.withdraw(id, amt);
                System.out.println("AccountService.withdraw " + id);
                if(rowsAffected !=1){
                    throw new Exception("withdraw failed");
                }

            }
        } catch (Exception e) {
            throw new Exception("withdraw failed");
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class})
    public void transfer(Long fromId, Long toId, double amt) throws Exception {

        try {
            deposit(toId, amt);
            withdraw(fromId, amt);
        } catch (Exception e) {

            e.printStackTrace();;
            throw new Exception("Transfer failed");
        }

    }
}
