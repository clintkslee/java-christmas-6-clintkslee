package christmas.model;

import christmas.food.Badge;
import christmas.food.Dessert;
import christmas.food.MainDish;
import christmas.food.WholeFood;

import static christmas.constants.Constants.NONE;

public class Customer {
    private int date; // 1~31
    private String[] menu; // [even] : menu, [odd] : ea
    private int totalBeforeDiscount; // 메뉴 입력받은 걸로 계산
    private boolean giftMenu; // 샴페인 증정 대상인지
    private int christmasDiscount; // 1~25값 해서 100원씩 할인
    private int weekdayDiscount; // 평일에 디저트 1개당 2023원 할인
    private int weekendDiscount; // 주말에 메인메뉴 1개당 2023원 할인
    private boolean specialDiscount; // 이벤트 달력에 별 있으면 트루 해서 1000원 할인
    private int TotalDiscount; // 총 할인 금액 계산된 거
    private String badge; // 뱃지
    public Customer(int date, String[] menu) {
        this.date = date;
        this.menu = menu;
        totalBeforeDiscount = calculateTotalBeforeDiscount(menu);
        giftMenu = isEligibleForGiftMenu(totalBeforeDiscount);
        christmasDiscount = calculateChristmasDiscount(date);
        weekdayDiscount = calculateWeekdayDiscount(date, menu);
        weekendDiscount = calculateWeekendDiscount(date, menu);
        specialDiscount = isEligibleForSpecialDiscount(date);
        TotalDiscount = calculateTotalDiscount(totalBeforeDiscount, giftMenu, christmasDiscount, weekdayDiscount, weekendDiscount, specialDiscount);
        badge = setBadge(TotalDiscount);
    }

    public int getDate() {
        return date;
    }

    public String[] getMenu() {
        return menu;
    }

    public int getTotalBeforeDiscount() {
        return totalBeforeDiscount;
    }

    public boolean isGiftMenu() {
        return giftMenu;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getWeekdayDiscount() {
        return weekdayDiscount;
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public boolean isSpecialDiscount() {
        return specialDiscount;
    }

    public int getTotalDiscount() {
        return TotalDiscount;
    }

    public String getBadge() {
        return badge;
    }

    private int calculateTotalBeforeDiscount(String[] menu) {
        int sum = 0;
        for(int i=0; i<menu.length; i+=2) {
            sum += WholeFood.valueOf(menu[i]).getPrice()*Integer.parseInt(menu[i+1]);
        }
        return sum;
    }

    private boolean isEligibleForGiftMenu(int totalBeforeDiscount) {
        if(totalBeforeDiscount<120000) {
            return false;
        }
        return true;
    }

    private  int calculateChristmasDiscount(int date) {
        if(date>25) {
            return 0;
        }
        return 1000 + (date-1) * 100;
    }

    private int calculateWeekdayDiscount(int date, String[] menu) {
        if(!isWeekday(date)) {
            return 0;
        }
        int dessertCount = 0;
        for(int i=0; i<menu.length; i+=2) {
            try {
                Dessert dessert = Dessert.valueOf(menu[i]); // throw
                dessertCount+=Integer.parseInt(menu[i+1]);
            } catch (IllegalArgumentException e) {
                ; // current menu is not dessert
            }
        }
        return dessertCount*2023;
    }

    private int calculateWeekendDiscount(int date, String[] menu) {
        if(isWeekday(date)) {
            return 0;
        }
        int mainDishCount = 0;
        for(int i=0; i<menu.length; i+=2) {
            try {
                MainDish mainDish = MainDish.valueOf(menu[i]); // throw
                mainDishCount+=Integer.parseInt(menu[i+1]);
            } catch (IllegalArgumentException e) {
                ; // current menu is not maindish
            }
        }
        return mainDishCount*2023;
    }

    private boolean isWeekday(int date) {
        if(date == 1 || date == 2 || date == 8 || date == 9 || date == 15 || date == 16 || date == 22 || date == 23 || date == 29 || date == 30) {
            return false;
        }
        return true;
    }


    private boolean isEligibleForSpecialDiscount(int date) {
        if(!isStarday(date)) {
            return false;
        }
        return true;
    }

    private boolean isStarday(int date) {
        if(!(date == 3 || date == 10 || date == 17 || date == 24 || date == 25 || date == 31)) {
            return false;
        }
        return true;
    }

    private int calculateTotalDiscount(int totalBeforeDiscount,
                                         boolean giftMenu,
                                         int christmasDiscount,
                                         int weekdayDiscount,
                                         int weekendDiscount,
                                         boolean specialDiscount) {
        if(totalBeforeDiscount<10000) {
            return 0;
        }

        int totalDiscount = 0;
        if(giftMenu) {
            totalDiscount += 25000;
        }
        totalDiscount += christmasDiscount;
        totalDiscount += weekdayDiscount;
        totalDiscount += weekendDiscount;
        if(specialDiscount) {
            totalDiscount += 1000;
        }
        return totalDiscount;
    }

    private String setBadge(int TotalDiscount) {
        if(TotalDiscount < Badge.별.getPrice()) {
            return NONE;
        }
        if(TotalDiscount < Badge.트리.getPrice()) {
            return Badge.별.name();
        }
        if(TotalDiscount < Badge.산타.getPrice()) {
            return Badge.트리.name();
        }
        return Badge.산타.name();
    }
}
