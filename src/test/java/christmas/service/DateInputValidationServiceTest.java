package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DateInputValidationServiceTest {
    DateInputValidationService dateInputValidationService;

    @BeforeEach
    void setUp() {
        dateInputValidationService = new DateInputValidationService();
    }

    @DisplayName("입력이 숫자인 지에 대한 예외 처리 - 정상입력")
    @ValueSource(strings = {"1","-1","0"})
    @ParameterizedTest
    void isNumber_정상입력(String dateInput) {
        assertThat(dateInputValidationService.isNumber(dateInput)).isTrue();
    }

    @DisplayName("입력이 숫자인 지에 대한 예외 처리 - 비정상입력")
    @ValueSource(strings = {"a"," ","-aa"})
    @ParameterizedTest
    void isNumber_비정상입력(String dateInput) {
        assertThatThrownBy(() -> dateInputValidationService.
                isNumber(dateInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }

    @DisplayName("범위 내 숫자인 지에 대한 예외 처리 - 정상입력")
    @ValueSource(strings = {"1","31"})
    @ParameterizedTest
    void isNumberBetween1to31_정상입력(String dateInput) {
        assertThat(dateInputValidationService.isNumberBetween1to31(dateInput)).isTrue();
    }

    @DisplayName("범위 내 숫자인 지에 대한 예외 처리 - 비정상입력")
    @ValueSource(strings = {"-1","0","32"})
    @ParameterizedTest
    void isNumberBetween1to31_비정상입력(String dateInput) {
        assertThatThrownBy(() -> dateInputValidationService.
                isNumberBetween1to31(dateInput)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("[ERROR]");
    }
}