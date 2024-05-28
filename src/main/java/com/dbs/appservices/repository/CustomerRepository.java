package com.dbs.appservices.repository;

import com.dbs.appservices.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {

        try {
            String query = "select id, name, location from customers";
            List<Customer> customers =  jdbcTemplate.query(query, new RowMapper<Customer>() {
                @Override
                public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Customer(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("location"));
                }
            });
            return customers;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Customer fetchCustomerById(int id){

        try {

            String query = "select id, name, location from customers where id=?";
            Customer customer = jdbcTemplate.queryForObject(query, new RowMapper<Customer>() {
                @Override
                public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("location"));
                }
            }, id);
            return customer;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    public boolean saveCustomer(Customer customer){

        try {
            String insertCmd = "insert into customers (name, location) values(?, ?)";
            int rowsAffected =jdbcTemplate.update(insertCmd, customer.getName(), customer.getLocation());
            return rowsAffected == 1;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteCustomer(int id){

        try {
            String deleteCmd = "delete from customers where id = ?";
            int rowsAffected = jdbcTemplate.update(deleteCmd, id);
            return rowsAffected == 1;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}








