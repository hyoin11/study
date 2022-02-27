package Java;

public class a19_ConditionalStatements {
	/*
	 * 제어문 (control flow statements)
	 * 자바 프로그램이 원하는 결과를 얻기 위해서는 프로그램의 순차적인 흐름을 제어해야만 할 경우가 생김
	 * 이때 사용하는 명령문을 제어문이라고 하며, 이 제어문에서는 조건문, 반복문 등이 있음
	 * 이러한 제어문에 속하는 명령문들은 중괄호로 둘러싸여 있으며, 이러한 중괄호 영역을 블록이라고 함
	 * 
	 * 조건문(conditional statements)
	 * 주어진 조건식의 결과에 따라 별도의 명령을 수행하도록 제어하는 명령문
	 * 	if문
	 * 	조건식의 결과가 참이라면 주어진 명령문을 실행하며, 거짓이면 아무것도 실행하지 않음
	 * 
	 * 	if/else문
	 * 	else문은 if문과는 반대로 주어진 조건식의 결과가 거짓이면 주어진 명령문을 실행
	 * 
	 * 	if/else if/else문
	 * 	조건식을 여러개 명시할 수 있으므로 중첩된 if문을 좀 더 간결하게 표현
	 * 
	 * 	삼항 연산자에 의한 조건문
	 * 	문법
	 * 		조건식 ? 반환값1 : 반환값2
	 * 
	 * 	switch문
	 * 	주어진 조건 값의 결과에 따라 프로그램이 다른 명령문을 수행하도록 하는 조건문
	 * 	if/else문보다 가독성이 더 좋으며, 컴파일러가 최적화를 쉽게 할 수 있어 속도 또한 빠른편
	 * 	하지만 조건 값으로는 int형으로 승격할 수 있는(integer promotion)값만이 사용될 수 있음
	 * 	즉, 조건값으로 byte, short, char, int형의 변수나 리터럴을 사용할 수 있음
	 * 	또한, 이러한 기본 타입에 해당하는 데이터를 객체로 포장해주는 래퍼 클래스 중에서 위에 해당하는 Byte, Short, Character, Integer클래스의 객체도 사용할 수 있음
	 * 	그리고 enum키워드를 사용한 열거체(enumeration type)와 String클래스의 객체도 사용할 ㅅ ㅜ있음
	 * 
	 * 
	 */
}