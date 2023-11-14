package christmas.service;

import static christmas.constants.Constants.INPUT_DATE_ERROR_MESSAGE;

public class DateInputValidationService {
    public boolean isNumber(String dateInput) {
        try {
            Integer.parseInt(dateInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_DATE_ERROR_MESSAGE);
        }
        return true;
    }
    public boolean isNumberBetween1to31(String dateInput) {
        if(Integer.parseInt(dateInput)<0 || 31<Integer.parseInt(dateInput)) {
            throw new IllegalArgumentException(INPUT_DATE_ERROR_MESSAGE);
        }
        return true;
    }
}
