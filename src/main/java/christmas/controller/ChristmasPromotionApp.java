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
        do {                                                                        // 날짜 입력
            dateInput = inputView.requestInput();
            userInputValidationController.validateDate(dateInput);
        } while(!userInputValidationController.checkDateValidity());
        inputView.printInputMenuMessage();
        do {                                                                        // 주문 입력
            menuInput = inputView.requestInput();
            userInputValidationController.validateMenu(menuInput);
        } while(!userInputValidationController.checkMenuValidity());
        customer = customerController.createCustomer(dateInput, menuInput);         // 고객 정보 생성
        outputView = new OutputView(customer);
        outputView.printResult();                                                   // 주문 출력
    }
}
