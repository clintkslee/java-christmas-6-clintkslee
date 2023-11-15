package christmas.controller;

import christmas.model.Customer;
import christmas.service.CustomerService;

public class CustomerController {
    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Customer createCustomer(String dateInput, String menuInput) {
        Customer customer = customerService.processUserInput(dateInput, menuInput);
        return customer;
    }
}
