package christmas.view;

import christmas.model.Customer;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static christmas.constants.Constants.*;

public class OutputView {
    private Customer customer;
    public OutputView(Customer customer) {
        this.customer = customer;
    }
    public void printResult() {
        printPreview(customer);
        printOrderMenu(customer);
        printTotalBeforeDiscount(customer);
        printGiftMenu(customer);
        printBenefitDetails(customer);
        printTotalDiscount(customer);
        printTotalAfterDiscount(customer);
        printBadge(customer);
    }

    private void printPreview(Customer customer) {
        System.out.println("12월 "+customer.getDate()+EVENT_PREVIEW_MESSAGE);
        System.out.println();
    }

    private void printOrderMenu(Customer customer) {
        System.out.println(ORDER_MENU_LABEL);
        for(int i=0; i<customer.getMenu().length; i+=2) {
            System.out.println(customer.getMenu()[i]+" "+customer.getMenu()[i+1]+EA);
        }
        System.out.println();
    }

    private void printTotalBeforeDiscount(Customer customer) {
        System.out.println(TOTAL_BEFORE_DISCOUNT_LABEL);
        String money = toMoneyFormat(customer.getTotalBeforeDiscount());
        System.out.println(money+WON);
        System.out.println();
    }

    private void printGiftMenu(Customer customer) {
        System.out.println(GIFT_MENU_LABEL);
        if(customer.isGiftMenu()) {
            System.out.println("샴페인 1개");
        }
        else
            System.out.println(NONE);
        System.out.println();
    }

    private void printBenefitDetails(Customer customer) {
        System.out.println(BENEFIT_DETAILS_LABEL);
        if(noBenefitDetails(customer)) {
            System.out.println(NONE);
            System.out.println();
            return;
        }
        hasBenefitDetails(customer);
    }

    private boolean noBenefitDetails(Customer customer) {
        if(customer.getChristmasDiscount()!=0) {
            return false;
        }
        if(customer.getWeekdayDiscount()!=0) {
            return false;
        }
        if(customer.getWeekendDiscount()!=0) {
            return false;
        }
        if(customer.isSpecialDiscount()) {
            return false;
        }
        return true;
    }
    private void hasBenefitDetails(Customer customer) {
        printChristmasBenefit(customer);
        printWeekdayBenefit(customer);
        printWeekendBenefit(customer);
        printSpecialBenefit(customer);
        printGiftMenuBenefit(customer);
        System.out.println();
    }
    void printChristmasBenefit(Customer customer) {
        if(customer.getChristmasDiscount()!=0) {
            String money = toMoneyFormat(customer.getChristmasDiscount());
            System.out.println(CHRISTMAS_DISCOUNT_PREFIX+"-"+money+WON);
        }
    }
    void printWeekdayBenefit(Customer customer) {
        if(customer.getWeekdayDiscount()!=0) {
            String money = toMoneyFormat(customer.getWeekdayDiscount());
            System.out.println(WEEKDAY_DISCOUNT_PREFIX+"-"+money+WON);
        }
    }
    void printWeekendBenefit(Customer customer) {
        if(customer.getWeekendDiscount()!=0) {
            String money = toMoneyFormat(customer.getWeekendDiscount());
            System.out.println(WEEKEND_DISCOUNT_PREFIX+"-"+money+WON);
        }
    }
    void printSpecialBenefit(Customer customer) {
        if(customer.isSpecialDiscount()) {
            System.out.println(SPECIAL_DISCOUNT_PREFIX+"-1,000"+WON);
        }
    }
    void printGiftMenuBenefit(Customer customer) {
        if(customer.isGiftMenu()) {
            System.out.println(GIFT_EVENT_PREFIX+"-25,000"+WON);
        }
    }
    private void printTotalDiscount(Customer customer) {
        System.out.println(TOTAL_DISCOUNT_LABEL);
        if(customer.getTotalDiscount()==0) {
            System.out.println(customer.getTotalDiscount()+WON);
            System.out.println();
            return;
        }
        String money = toMoneyFormat(customer.getTotalDiscount());
        System.out.println("-"+money+WON);
        System.out.println();
    }

    private void printTotalAfterDiscount(Customer customer) {
        System.out.println(TOTAL_AFTER_DISCOUNT_LABEL);
        String money = toMoneyFormat(customer.getTotalBeforeDiscount()-customer.getTotalDiscount());
        System.out.println(money+WON);
        System.out.println();
    }

    private void printBadge(Customer customer) {
        System.out.println(BADGE_LABEL);
        System.out.println(customer.getBadge());
    }

    private String toMoneyFormat(int money) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(money);
    }


}
