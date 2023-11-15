# 미션 - 크리스마스 프로모션

***

## 😄 프로그램 흐름
- 프로그램 시작 `main()`
- 방문날짜, 메뉴 개수를 입력받는다.
- 주문 내역 및 혜택 내역을 출력한다. (끝)

***

## 😄 구현 클래스
```
src/main/java/christmas
├─constants
│     └─Constants.java 
├─controller
│     ├─ChristmasPromotionApp.java
│     ├─CustomerController.java
│     └─UserInputValidationController.java 
├─food
│     ├─Appetizer.java
│     ├─Badge.java
│     ├─Beverage.java
│     ├─Dessert.java
│     ├─MainDish.java
│     └─WholeFood.java 
├─model
│     └─Customer.java
├─service
│     ├─CustomerService.java 
│     ├─DateInputValidationService.java 
│     └─MenuInputValidationService.java
├─view
│     ├─InputView.java
│     └─OutputView.java 
└─Application.java
```
- constants 패키지 : 뷰에서 메시지 출력 시 사용되는 스트링 상수
- controller 패키지 : 프론트 컨트롤러(역할) 클래스, 커스토머 객체 생성 컨트롤러 클래스, 사용자 입력에 대한 검증 컨트롤러 클래스
- food 패키지 : enum 클래스들  
- model 패키지 : 고객 정보 클래스
- service 패키지 : 고객 정보 객체 생성 기능 클래스, 날짜 입력 검증 기능 클래스, 메뉴 입력 검증 기능 클래스
- view 패키지 : 메시지 출력 및 입력 클래스, 출력 클래스
- 어플리케이션 클래스 : 프로그램 시작점

***

## 😄 기능 목록
-[x] 방문 날짜를 입력 요청 출력 `InputView.printInputDateMessage()`
-[x] 방문 날짜 입력 `InputView.requestInput()`
  -[x] 입력받은 날짜 유효성 판단 `UserInputValidationController.validateDate()`
    -[x] 숫자인지 `DateInputValidationService.isNumber()`
    -[x] 1~31 범위 내인지 `DateInputValidationService.isNumberBetween1to31()`    
-[x] 메뉴와 개수 입력 요청 출력 `InputView.printInputMenuMessage()`
-[x] 메뉴와 개수 입력 `InputView.requestInput()`
  -[x] 입력받은 메뉴와 개수 유효성 판단 `UserInputValidationController.validateMenu()`
    -[x] 쉼표로 끝나지 않았는지 `MenuInputValidationService.isNotEndWithComma()`
    -[x] 길이가 각각 3 이상인지 `MenuInputValidationService.isLengthBiggerThan3()`
    -[x] split 결과 각각 '-'을 갖고 있는지 `MenuInputValidationService.hasOneHyphen()`
    -[x] 모든 음식명이 WholeFood 에 속하는지 `MenuInputValidationService.belongsToWholeFood()`
    -[x] 음식명에 중복이 있는지 `MenuInputValidationService.hasDuplicateFoodName()`
    -[x] 음식에 음료만 있는지 `MenuInputValidationService.isNotOnlyBeverage()`
    -[x] 주문 개수가 숫자 인지 `MenuInputValidationService.isNumber()`
    -[x] 주문 개수가 양수 인지 `MenuInputValidationService.isPositive()`
    -[x] 주문 합이 20 이하인지 `MenuInputValidationService.isOrderQuantityAcceptable()`
-[x] 고객 정보 생성 `CustomerController.createCustomer()`
  -[x] 사용자 입력 가공 `CustomerService.processUserInput()`
-[x] 결과 출력 `OutputView.printResult()` (끝)

***

## 😄 기타 요구 사항
-[x] Java 코드 컨벤션 가이드 준수
-[x] indent depth 2 이하로 구현
-[x] 3항 연산자 사용하지 않기
-[x] 메서드 길이 15라인 이하로 구현
  -[x] 메서드가 한 가지 일만 하도록 구현
-[x] else 예약어 사용하지 않기
-[x] 도메인 로직 단위 테스트 구현
-[x] 잘못된 값 입력 시 `IllegalArgumentException` 발생
  -[x] "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 다시 입력
  -[x] `IllegalArgumentException`, `IllegalStateException` 처럼 명확한 유형의 예외 처리



