package christmas.controller;

import christmas.model.Customer;
import christmas.service.DateInputValidationService;
import christmas.service.CustomerService;
import christmas.service.MenuInputValidationService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasPromotionApp {
    private String dateInput;
    private String menuInput;
    private Customer customer;
    private InputView inputView;
    OutputView outputView;
    UserInputValidationController userInputValidationController;
    private CustomerController customerController;

    public ChristmasPromotionApp() {
        inputView = new InputView();
        userInputValidationController =
                new UserInputValidationController(new DateInputValidationService(), new MenuInputValidationService());
        customerController = new CustomerController(new CustomerService());
    }

    public void run() {
        inputView.printInputDateMessage();
        do {
            dateInput = inputView.requestInput();
            userInputValidationController.validateDate(dateInput);
        } while(!userInputValidationController.checkDateValidity());

        inputView.printInputMenuMessage();
        do {
            menuInput = inputView.requestInput();
            userInputValidationController.validateMenu(menuInput);
        } while(!userInputValidationController.checkMenuValidity());

        customer = customerController.createCustomer(dateInput, menuInput);
        outputView = new OutputView(customer);
        outputView.printResult();
    }
}
