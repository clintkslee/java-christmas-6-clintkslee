package christmas.constants;

public final class Constants {
    private Constants() {
    }

    // InputView
    public static final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String INPUT_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    public static final String INPUT_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String INPUT_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    public static final String INPUT_ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String EVENT_PREVIEW_MESSAGE = "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    // OutputView
    public static final String ORDER_MENU_LABEL = "<주문 메뉴>";
    public static final String TOTAL_BEFORE_DISCOUNT_LABEL = "<할인 전 총주문 금액>";
    public static final String GIFT_MENU_LABEL = "<증정 메뉴>";
    public static final String BENEFIT_DETAILS_LABEL = "<혜택 내역>";
    public static final String CHRISTMAS_DISCOUNT_PREFIX = "크리스마스 디데이 할인: ";
    public static final String WEEKDAY_DISCOUNT_PREFIX = "평일 할인: ";
    public static final String SPECIAL_DISCOUNT_PREFIX = "특별 할인: ";
    public static final String GIFT_EVENT_PREFIX = "증정 이벤트: ";
    public static final String TOTAL_DISCOUNT_LABEL = "<총혜택 금액>";
    public static final String TOTAL_AFTER_DISCOUNT_LABEL = "<할인 후 예상 결제 금액>";
    public static final String BADGE_LABEL = "<12월 이벤트 배지>";
    
    //
    public static final String EA = "개";
    public static final String WON = "원";
    public static final String NONE = "없음";
}
