package christmas.service;

import christmas.model.Customer;

public class CustomerService {
    public Customer processUserInput(String dateInput, String menuInput) {
        int date = processDateInput(dateInput);
        String[] menu = processMenuInput(menuInput);
        Customer customer = new Customer(date, menu);
        return customer;
    }

    private int processDateInput(String dateInput) {
        return Integer.parseInt(dateInput);
    }
    private String[] processMenuInput(String menuInput) {
        String[] menu = menuInput.split("[,-]");
        return menu;
    }
}
