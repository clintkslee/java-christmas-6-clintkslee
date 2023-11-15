package christmas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.*;

class MenuInputValidationServiceTest {
    MenuInputValidationService menuInputValidationService;
    @BeforeEach
    void setUp() {
        menuInputValidationService = new MenuInputValidationService();
    }

    @DisplayName("입력이 ,로 끝나는지 여부에 대한 예외처리 - 정상입력")
    @ValueSource(strings = {"aaa","fff",""})
    @ParameterizedTest
    void isNotEndWithComma_정상입력(String menuInput) {
        assertThat(menuInputValidationService.isNotEndWithComma(menuInput)).isTrue();
    }

    @DisplayName("입력이 ,로 끝나는지 여부에 대한 예외처리 - 비정상입력")
    @ValueSource(strings = {"aaa,","fff,",","})
    @ParameterizedTest
    void isNotEndWithComma_비정상입력(String menuInput) {
        assertThatThrownBy(() -> menuInputValidationService.
                isNotEndWithComma(menuInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }

    @DisplayName("','로 split 한 각 주문들의 길이가 3이상인지(메뉴-숫자 꼴 입력 시 최소3) - 정상입력")
    @ValueSource(strings = {"a-aa,11111,--bb","fffff,t123","test-test,food-food"})
    @ParameterizedTest
    void isLengthBiggerThan3_정상입력(String menuInput) {
        assertThat(menuInputValidationService.isLengthBiggerThan3(menuInput)).isTrue();
    }

    @DisplayName("','로 split 한 각 주문들의 길이가 3이상인지(메뉴-숫자 꼴 입력 시 최소3) - 비정상입력")
    @ValueSource(strings = {"aa,11-11,--bb","fffff,t","test-test,-"})
    @ParameterizedTest
    void isLengthBiggerThan3_비정상입력(String menuInput) {
        assertThatThrownBy(() -> menuInputValidationService.
                isLengthBiggerThan3(menuInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }

    @DisplayName(",로 split 한 각 주문들에 정확히 하나의 '-' 문자가 있는지 - 정상입력")
    @ValueSource(strings = {"a-aa,-bb","aaa-aaa,-ffff","testtest-,-123"})
    @ParameterizedTest
    void hasOneHyphen_정상입력(String menuInput) {
        assertThat(menuInputValidationService.hasOneHyphen(menuInput)).isTrue();
    }

    @DisplayName(",로 split 한 각 주문들에 정확히 하나의 '-' 문자가 있는지 - 비정상입력")
    @ValueSource(strings = {"a-aa,--bb","aaaaaa,ffff","testtest--,-123"})
    @ParameterizedTest
    void hasOneHyphen_비정상입력(String menuInput) {
        assertThatThrownBy(() -> menuInputValidationService.
                hasOneHyphen(menuInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }

    @DisplayName("'-' 문자 앞의 내용이 WholeFood enum 클래스의 값인지 - 정상입력")
    @ValueSource(strings = {"양송이수프-aa,티본스테이크-ffff","초코케이크-,제로콜라-111"})
    @ParameterizedTest
    void belongsToWholeFood_정상입력(String menuInput) {
        assertThat(menuInputValidationService.belongsToWholeFood(menuInput)).isTrue();
    }

    @DisplayName("'-' 문자 앞의 내용이 WholeFood enum 클래스의 값인지 - 비정상입력")
    @ValueSource(strings = {"양송이-aa,티본스테이크-ffff","초코케이크-,콜라-111"})
    @ParameterizedTest
    void belongsToWholeFood_비정상입력(String menuInput) {
        assertThatThrownBy(() -> menuInputValidationService.
                belongsToWholeFood(menuInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }

    @DisplayName("메뉴 이름들에 중복이 있는지 - 정상입력")
    @ValueSource(strings = {"양송이수프-aa,티본스테이크-ffff","초코케이크-,제로콜라-111"})
    @ParameterizedTest
    void hasDuplicateFoodName_정상입력(String menuInput) {
        assertThat(menuInputValidationService.hasDuplicateFoodName(menuInput)).isTrue();
    }

    @DisplayName("메뉴 이름들에 중복이 있는지 - 비정상입력")
    @ValueSource(strings = {"양송이수프-aa,양송이수프-ffff","제로콜라-22,제로콜라-111"})
    @ParameterizedTest
    void hasDuplicateFoodName_비정상입력(String menuInput) {
        assertThatThrownBy(() -> menuInputValidationService.
                hasDuplicateFoodName(menuInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }

    @DisplayName("메뉴들이 Beverage enum 클래스의 값들만 포함되었는지 - 정상입력")
    @ValueSource(strings = {"양송이수프-aa,티본스테이크-ffff","초코케이크-,제로콜라-111"})
    @ParameterizedTest
    void isNotOnlyBeverage_정상입력(String menuInput) {
        assertThat(menuInputValidationService.isNotOnlyBeverage(menuInput)).isTrue();
    }

    @DisplayName("메뉴들이 Beverage enum 클래스의 값들만 포함되었는지 - 비정상입력")
    @ValueSource(strings = {"제로콜라-aa,레드와인-ffff","샴페인-,제로콜라-111"})
    @ParameterizedTest
    void isNotOnlyBeverage_비정상입력(String menuInput) {
        assertThatThrownBy(() -> menuInputValidationService.
                isNotOnlyBeverage(menuInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }

    @DisplayName("'-' 문자 뒤의 내용이 숫자인지 - 정상입력")
    @ValueSource(strings = {"양송이수프-1,티본스테이크-5555","초코케이크-33,제로콜라-111"})
    @ParameterizedTest
    void isNumber_정상입력(String menuInput) {
        assertThat(menuInputValidationService.isNumber(menuInput)).isTrue();
    }

    @DisplayName("'-' 문자 뒤의 내용이 숫자인지 - 비정상입력")
    @ValueSource(strings = {"양송이수프-1,티본스테이크-a","초코케이크-33333,제로콜라-b"})
    @ParameterizedTest
    void isNumber_비정상입력(String menuInput) {
        assertThatThrownBy(() -> menuInputValidationService.
                isNumber(menuInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }

    @DisplayName("'-' 문자 뒤의 숫자가 양수인지 - 정상입력")
    @ValueSource(strings = {"양송이수프-1,티본스테이크-123","초코케이크-33,제로콜라-111"})
    @ParameterizedTest
    void isPositive_정상입력(String menuInput) {
        assertThat(menuInputValidationService.isPositive(menuInput)).isTrue();
    }

    @DisplayName("'-' 문자 뒤의 숫자가 양수인지 - 비정상입력")
    @ValueSource(strings = {"양송이수프-0,티본스테이크-123","초코케이크-0,제로콜라-111"})   // 음수는 -- 예외처리에서 걸러짐
    @ParameterizedTest
    void isPositive_비정상입력(String menuInput) {
        assertThatThrownBy(() -> menuInputValidationService.
                isPositive(menuInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }

    @DisplayName("주문한 메뉴의 합이 20이하인지 - 정상입력")
    @ValueSource(strings = {"양송이수프-1,티본스테이크-2","초코케이크-3,제로콜라-17"})
    @ParameterizedTest
    void isOrderQuantityAcceptable_정상입력(String menuInput) {
        assertThat(menuInputValidationService.isOrderQuantityAcceptable(menuInput)).isTrue();
    }

    @DisplayName("주문한 메뉴의 합이 20이하인지 - 비정상입력")
    @ValueSource(strings = {"양송이수프-1,티본스테이크-20","초코케이크-19,제로콜라-2"})
    @ParameterizedTest
    void isOrderQuantityAcceptable_비정상입력(String menuInput) {
        assertThatThrownBy(() -> menuInputValidationService.
                isOrderQuantityAcceptable(menuInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }








}