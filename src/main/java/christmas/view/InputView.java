package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static christmas.constants.Constants.GREETING_MESSAGE;
import static christmas.constants.Constants.INPUT_DATE_MESSAGE;
import static christmas.constants.Constants.INPUT_ORDER_MESSAGE;

public class InputView {
    public String requestInput() {
        return Console.readLine();
    }
    public void printInputDateMessage() {
        System.out.println(GREETING_MESSAGE);
        System.out.println(INPUT_DATE_MESSAGE);
    }
    public void printInputMenuMessage() {
        System.out.println(INPUT_ORDER_MESSAGE);
    }
}