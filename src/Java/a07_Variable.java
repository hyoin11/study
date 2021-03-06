package Java;

public class a07_Variable {
	/*
	 * 변수
	 * 데이터를 저장하기 위해 프로그램에 의해 이름을 할당받은 메모리 공간을 의미
	 * 즉, 변수란 데이터를 저장할 수 있는 메모리 공간을 의미하며, 이렇게 저장된 값은 변경될 수 있음
	 * 
	 * 변수의 이름 생성 규칙
	 * 1. 영문자(대소문자), 숫자, 언더스코어(_), 달러($)로만 구성할 수 있음
	 * 2. 숫자로 시작할 수 없음
	 * 3. 이름 사이에는 공백을 포함할 수 없음
	 * 4. 자바에서 미리 정의된 키워드는 사용할 수 없음
	 * 
	 * 변수의 종류
	 * 1. 기본형(primitive type) 변수
	 * 	- 정수형 : byte, short, int, long
	 * 	- 실수형 : float, double
	 * 	- 논리형 : boolean
	 * 2. 참조형(reference type) 변수
	 * 
	 * 변수의 선언
	 * 사용하기 전에 반드시 먼저 변수를 선언하고 초기화 해야함
	 * 변수 선언방법
	 * 	1. 변수의 선언만 하는 방법
	 * 		먼저 변수를 선언하여 메모리 공간을 할당받고, 나중에 변수를 초기화 하는 방법
	 * 		이렇게 선언된 변수는 초기화 되지 않았으므로, 해당 메모리 공간에는 알 수 없는 쓰레기값만 들어가있음
	 * 		반드시 초기화한 후에 사용해야함
	 * 		예제
	 * 			int num;	// 변수의 선언
	 * 			num = 20;	// 변수의 초기화
	 * 	2. 변수의 선언과 동시에 초기화 하는 방법
	 * 		선언과 동시에 그 값을 초기화할 수 있음
	 * 		또한, 선언하고자 하는 변수들의 타입이 같다면 이를 동시에 선언할 수도 있음
	 * 		예제
	 * 			int num1, num2;	// 같은 타입의 변수를 동시에 선언함
	 * 			double num3 = 3.14;	// 선언과 동시에 초기화함
	 * 			double num4 = 1.23, num5 = 4.56;	// 같은 타입의 변수를 동시에 선언하면서 초기화함
	 * 			(* num1 = 1.23, num2 = 4.56; // 이미 선언된 여러 변수를 동시에 초기화 할수는 없음)
	 */
}
