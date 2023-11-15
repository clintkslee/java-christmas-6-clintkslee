package christmas.view;

import christmas.model.Customer;

import static christmas.constants.Constants.*;

public class OutputView {
    private Customer customer;
    public OutputView(Customer customer) {
        this.customer = customer;
    }
    public void printResult() {
        System.out.println(ORDER_MENU_LABEL);
        for(int i=0; i<customer.getMenu().length; i+=2) {
            System.out.println(customer.getMenu()[i]+" "+customer.getMenu()[i+1]+EA);
        }
        System.out.println();

        System.out.println(TOTAL_BEFORE_DISCOUNT_LABEL);
        System.out.println(customer.getTotalBeforeDiscount()+EA);
        System.out.println();

        System.out.println(GIFT_MENU_LABEL);
        if(customer.isGiftMenu()) {
            System.out.println("샴페인 1개");
        }
        else
            System.out.println(NONE);
        System.out.println();

        System.out.println(BENEFIT_DETAILS_LABEL);
        if(customer.getChristmasDiscount()!=0) {
            System.out.println(CHRISTMAS_DISCOUNT_PREFIX+"-"+customer.getChristmasDiscount()+WON);
        }
        if(customer.getWeekdayDiscount()!=0) {
            System.out.println(WEEKDAY_DISCOUNT_PREFIX+"-"+customer.getWeekdayDiscount()+WON);
        }
        if(customer.getWeekendDiscount()!=0) {
            System.out.println(WEEKEND_DISCOUNT_PREFIX+"-"+customer.getWeekendDiscount()+WON);
        }
        if(customer.isSpecialDiscount()) {
            System.out.println(SPECIAL_DISCOUNT_PREFIX+"-1000"+WON);
        }
        if(customer.isGiftMenu()) {
            System.out.println(GIFT_EVENT_PREFIX+"-25000"+WON);
        }
        // 없음에 대한 예외처리
        System.out.println();

        System.out.println(TOTAL_DISCOUNT_LABEL);
        System.out.println("-"+customer.getTotalDiscount()+WON);
        System.out.println();

        System.out.println(TOTAL_AFTER_DISCOUNT_LABEL);
        System.out.println(customer.getTotalBeforeDiscount()-customer.getTotalDiscount()+WON);
        System.out.println();

        System.out.println(BADGE_LABEL);
        System.out.println(customer.getBadge());
    }
}
