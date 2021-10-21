package service;

import Model.Customer;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerService {
    private static CustomerService INSTANCE;
    private static Collection<Customer> collections = new ArrayList<Customer>();

    public static CustomerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        collections.add(new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail) {
        Customer result = null;
        for (Customer customers : collections) {
            if (customers.getEmail() == customerEmail) {
                result = customers;
            }
        }
        return result;

    }

    public Collection<Customer> getAllCustomers() {
        return collections;
    }
}
