package DataStructure;

public class ArrayDeque<E> implements Queue<E> {
	/*
	 * Deque(덱)
	 * Double-ended queue의 줄임말
	 * 양방향 연결리스트와 유사한 메커니즘
	 * 양쪽 방향으로 삽입 삭제가 이루어 질 수 있도록 구현
	 * 덱의 장점은 스택처럼 사용할 수도 있고 큐 처럼 사용할 수도 있음
	 */
	
	private static final int DEFAULT_CAPACITY = 64;	// 최소(기본) 용적 크기
	
	private Object[] array;	// 요소를 담을 배열
	private int size;	// 요소 개수
	
	private int front;	// 시작 인덱스를 가리키는 변수(빈 공간임을 유의)
	private int rear;	// 마지막 요소의 인덱스를 가리키는 변수
	
	// 생성자1 (초기 용적 할당 X)
	public ArrayDeque() {
		this.array = new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
	
	// 생성자2 (초기 용적 할당 O)
	public ArrayDeque(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}
}
