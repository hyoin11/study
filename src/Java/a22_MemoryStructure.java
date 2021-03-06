package Java;

public class a22_MemoryStructure {
	/*
	 * 메모리 구조
	 * 모든 자바 프로그램은 자바 가상 머신(JVM)을 통해서 실행
	 * 자바 프로그램이 실행되면, JVM은 운영 체제로부터 해당 프로그램을 수행할 수 있도록 필요한 메모리를 할당받음
	 * 이렇게 할당받은 메모리를 JVM은 용도에 따라 다음과 같이 구분하여 관리함
	 * 	낮은 주소(low memory)							높은 주소(high memory)
	 * 	메모리	클래스, 클래스 변수		객체, 인스턴스		메소드, 지역변수, 매개변수
	 * 			메소드 영역			힙 영역			스택 영역
	 * 
	 * 메소드(method) 영역
	 * 자바 프로그램에서 사용되는 클래스에 대한 정보와 함께 클래스 변수(static variable)가 저장되는 영역
	 * JVM은 자바 프로그램에서 특정 클래스가 사용되면 해당 클래스의 클래스 파일(*.class)를 읽어들여, 해당 클래스에 대한 정보를 메소드 영역에 저장함
	 * 
	 * 힙(heap) 영역
	 * 자바 프로그램에서 사용되는 모든 인스턴스 변수가 저장되는 영역
	 * new 키워드를 사용하여 인스턴스가 생성되면, 해당 인스턴스의 정보를 힙 영역에 저장
	 * 힙 영역은 메모리의 낮은 주소에서 높은 주소의 방향으로 할당됨
	 * 
	 * 스택(stack) 영역
	 * 자바 프로그램에서 메소드가 호출될 때 메소드의 스택프레임이 저장되는 영역
	 * JVM은 자바 프로그램에서 메소드가 호출되면, 메소드의 호출과 관계되는 지역변수와 매개변수를 스택 영역에 저장함
	 * 이렇게 스택 영역은 메소드의 호출과 함께 할당되며, 메소드의 호출이 완료되면 소멸함
	 * 이렇게 스택 영역에 저장되는 메소드의 호출 정보를 스택 프레임(stack frame)이라고 함
	 * 스택영역은 푸시(push)동작으로 데이터를 저장하고, 팝(pop)동작으로 데이터를 인출함
	 * 후입선출(LIFO, Last In First Out) 방식에 따라 동작하므로, 가장 늦게 저장된 데이터가 가장 먼저 인출됨
	 * 스택 영역은 메모리의 높은 주소에서 낮은 주소의 방향으로 할당됨
	 */
}
