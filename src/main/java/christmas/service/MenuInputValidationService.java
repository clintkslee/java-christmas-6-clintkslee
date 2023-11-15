package christmas.service;

import christmas.food.WholeFood;
import java.util.*;
import static christmas.constants.Constants.INPUT_ORDER_ERROR_MESSAGE;

public class MenuInputValidationService {
    public boolean isNotEndWithComma(String menuInput) {
        if(menuInput.endsWith(",")) {
            throw new IllegalArgumentException(INPUT_ORDER_ERROR_MESSAGE+" remove last comma");
        }
        return true;
    }

    public boolean isLengthBiggerThan3(String menuInput) {
        String[] foodHyphenNum = menuInput.split(",");
        for(String order : foodHyphenNum) {
            if(order.length()<3) {
                throw new IllegalArgumentException(INPUT_ORDER_ERROR_MESSAGE+" too short [food-number 꼴]");
            }
        }
        return true;
    }

    public boolean hasOneHyphen(String menuInput) {
        String[] foodHyphenNum = menuInput.split(",");
        int hyphenCount = 0;
        for(String order : foodHyphenNum) {
            if(!order.contains("-")) {              // 하이픈이 없는 경우
                throw new IllegalArgumentException(INPUT_ORDER_ERROR_MESSAGE+" no hyphen");
            }
            if(order.matches(".*-{1,}.*-{1,}.*")) {       // 두개 이상인 경우 정규 표현식
                throw new IllegalArgumentException(INPUT_ORDER_ERROR_MESSAGE+" more than 2 hyphens");
            }
        }
        return true;
    }

    public boolean belongsToWholeFood(String menuInput) {
        String[] foodHyphenNum = menuInput.split(",");
        for(String order : foodHyphenNum) {
            String[] parts = order.split("-");
            if(!isWholeFood(parts[0])) {              // 음식명이 wholefood 에 속하지 않는 경우
                throw new IllegalArgumentException(INPUT_ORDER_ERROR_MESSAGE+" not wholefood");
            }
        }
        return true;
    }

    private boolean isWholeFood(String foodName) {
        for (WholeFood wholeFood : WholeFood.values()) {
            if (wholeFood.name().equalsIgnoreCase(foodName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDuplicateFoodName(String menuInput) {
        String[] foodHyphenNum = menuInput.split(",");
        List<String> foodNum = new ArrayList<>();
        for(String order : foodHyphenNum) {
            String[] parts = order.split("-");
            foodNum.addAll(Arrays.asList(parts));
        }
        List<String> foods = new ArrayList<>();
        for(int i=0; i<foodNum.size(); i+=2) {
            foods.add(foodNum.get(i));
        }
        Set<String> uniqueFoods = new HashSet<>(foods);
        if (foods.size() != uniqueFoods.size()) {
            throw new IllegalArgumentException(INPUT_ORDER_ERROR_MESSAGE+" not unique");
        }
        return true;
    }

    public boolean isNumber(String menuInput) {
        String[] foodHyphenNum = menuInput.split(",");
        List<String> foodNum = new ArrayList<>();
        for(String order : foodHyphenNum) {
            String[] parts = order.split("-");
            foodNum.addAll(Arrays.asList(parts));
        }
        List<String> nums = new ArrayList<>();
        for(int i=1; i<foodNum.size(); i+=2) {
            nums.add(foodNum.get(i));
        }
        for(String num : nums) {
            try {
                Integer.parseInt(num);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(INPUT_ORDER_ERROR_MESSAGE+" not number");
            }
        }
        return true;
    }

    public boolean isPositive(String menuInput) {
        String[] foodHyphenNum = menuInput.split(",");
        List<String> foodNum = new ArrayList<>();
        for(String order : foodHyphenNum) {
            String[] parts = order.split("-");
            foodNum.addAll(Arrays.asList(parts));
        }
        List<String> nums = new ArrayList<>();
        for(int i=1; i<foodNum.size(); i+=2) {
            nums.add(foodNum.get(i));
        }
        for(String num : nums) {
            if(Integer.parseInt(num)<=0) {
                throw new IllegalArgumentException(INPUT_ORDER_ERROR_MESSAGE+" not positive");
            }
        }
        return true;
    }

    public boolean isOrderQuantityAcceptable(String menuInput) {
        String[] foodHyphenNum = menuInput.split(",");
        List<String> foodNum = new ArrayList<>();
        for(String order : foodHyphenNum) {
            String[] parts = order.split("-");
            foodNum.addAll(Arrays.asList(parts));
        }
        int sum = 0;
        for(int i=1; i<foodNum.size(); i+=2) {
            sum += Integer.parseInt(foodNum.get(i));
        }
        if(sum>20) {
            throw new IllegalArgumentException(INPUT_ORDER_ERROR_MESSAGE+" too many order");
        }
        return true;
    }
}
