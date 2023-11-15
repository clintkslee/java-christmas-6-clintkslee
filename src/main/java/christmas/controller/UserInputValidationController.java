package christmas.controller;

import christmas.service.DateInputValidationService;
import christmas.service.MenuInputValidationService;

public class UserInputValidationController {
    DateInputValidationService dateInputValidationService;
    MenuInputValidationService menuInputValidationService;
    private boolean isDateValid;
    private boolean isMenuValid;
    public UserInputValidationController(DateInputValidationService dateInputValidationService,
                                         MenuInputValidationService menuInputValidationService) {
        this.dateInputValidationService = dateInputValidationService;
        this.menuInputValidationService = menuInputValidationService;
        isDateValid = false;
        isMenuValid = false;
    }

    public void validateDate(String dateInput) {
        try {
            if(dateInputValidationService.isNumber(dateInput));                // 숫자인지, 아니면 throw
            if(dateInputValidationService.isNumberBetween1to31(dateInput));    // 1~31 사이인지, 아니면 throw
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        isDateValid = true;
    }

    public void validateMenu(String menuInput) {
        try {
            if(menuInputValidationService.isNotEndWithComma(menuInput));   // 쉼표로 끝나지 않았는지
            if(menuInputValidationService.isLengthBiggerThan3(menuInput));   // 길이가 각각 3 이상인지
            if(menuInputValidationService.hasOneHyphen(menuInput));  // ',' split 결과 각각 '-'을 갖고 있는지
            if(menuInputValidationService.belongsToWholeFood(menuInput));  // 음식명이 WholeFood 에 속하는지
            if(menuInputValidationService.hasDuplicateFoodName(menuInput));  // 음식명이 중복이 있는지
            if(menuInputValidationService.isNumber(menuInput));  // 주문 개수가 숫자 인지
            if(menuInputValidationService.isPositive(menuInput));  // 주문 개수가 양수 인지
            if(menuInputValidationService.isOrderQuantityAcceptable(menuInput));  // 주문 합이 20 이하인지
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        isMenuValid = true;
    }

    public boolean checkDateValidity () {
        return isDateValid;
    }

    public boolean checkMenuValidity () {
        return isMenuValid;
    }

}
