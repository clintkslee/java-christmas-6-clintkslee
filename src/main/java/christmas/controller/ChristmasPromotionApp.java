package christmas.controller;

import christmas.model.Customer;
import christmas.service.DateInputValidationService;
import christmas.service.DiscountService;
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
    private DiscountController discountController;

    public ChristmasPromotionApp() {
        inputView = new InputView();
        userInputValidationController =
                new UserInputValidationController(new DateInputValidationService(), new MenuInputValidationService());
        discountController = new DiscountController(new DiscountService());
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

        customer = discountController.setCustomerData(dateInput, menuInput);
        outputView = new OutputView(customer);
        outputView.printResult();
    }
}
