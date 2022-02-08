package DataStructure;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {
	/*
	 * LinkedList
	 * ArrayList와 가장 큰 차이점은 '노드'라는 객체를 이용하여 연결
	 * ArrayList의 경우 최상위 타입인 오브젝트 배열(Object[])을 사용하여 데이터를 담아두었다면
	 * LinkedList는 배열을 이용하는 것이 아닌 하나의 객체를 두고 그 안에 데이터와 다른 노드를 가리키는 래퍼런스 데이터로 구성하여 여러 노드를 하나의 체인처럼 연결하는 것
	 * 노드(node)는 데이터와 다른 노드를 가리킬 주소데이터를 담은 객체
	 * 노드들이 여러개가 연결 되어있는 것을 연결 리스트, 즉 LinkedList라고 함
	 * 연결된 노드들에서 삽입을 한다면 링크만 바꿔주면 되기 때문에 매우 편리
	 * 반대로 삭제를 한다면 삭제할 노드에 연결 된 이전노드에서 링크를 끊고 삭제할 노드의 다음 노드를 연결해주면 됨
	 */
	
	private Node<E> head;	// 노드의 첫 부분
	private Node<E> tail;	// 노드의 마지막 부분
	private int size;	// 요소 개수
	
	// 생성자
	public SinglyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/*
	 * search() 메소드 부터 구현
	 * 단일 연결리스트이다보니 특정 위치의 데이터를 삽입, 삭제, 검색하기 위해서는 처음 노드부터 next변수를 통해 특정 위치까지 찾아가야하기 때문
	 */
	// 특정 위치의 노드를 반환하는 메소드
	private Node<E> search(int index){
		// 범위 밖일 경우 예외 던지기
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> x = head; 	// head가 가리키는 노드부터 시작
		
		for(int i=0; i<index; i++) {
			x = x.next;	// x노드의 다음 노드를 x에 저장
		}
		return x;
	}
	
	/*
	 * add 메소드
	 * - 가장 앞부분에 추가 - addFirst(E value)
	 * 		데이터를 이동시키는 것이 아닌 새로운 노드를 생성하고 새 노드의 레퍼런스 변수(next)가 head 노드를 가리키도록
	 * - 가장 마지막 부분에 추가(기본값) - addLast(E value)
	 * 		LinkedList API를 보면 add 메소드를 호출하면 add()는 addLast()를 호출함
	 * 		addFirst()를 먼저 구현한 이유는 size가 0일 경우(= 아무런 노드가 없을 경우)는 결국 처음으로 데이터를 추가한다는 뜻이기 때문에 addFirst()를 호출하면 됨
	 * - 특정 위치에 추가 - add(int index, E value)
	 * 		넣으려는 위치 앞뒤로 링크를 새로 업데이트해줘야함
	 */
	public void addFirst(E value) {
		Node<E> newNode = new Node<E>(value);	// 새 노드 생성
		newNode.next = head;	// 새 노드의 다음 노드로 head 노드를 연결
		head = newNode;	// head가 가리키는 노드를 새 노드로 변경
		size++;
		
		/*
		 * 다음에 가리킬 노드가 없는 경우(= 데이터가 새 노드밖에 없는 경우)
		 * 데이터가 한개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자 마지막 노드
		 * 즉, tail = head
		 */
		if(head.next == null) {
			tail = head;
		}
	}
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		Node<E> newNode = new Node<E>(value);	// 새 노드 생성
		
		if(size == 0) {	// 처음 넣는 노드일 경우 addFirst로  추가
			addFirst(value);
			return;
		}
		
		// 마지막 노드(tail)의 다음 노드(next)가 새 노드를 가리키도록 하고 tail이 가리키는 노드를 새 노드로 바꿔줌
		tail.next = newNode;
		tail = newNode;
		size++;
	}

	@Override
	public void add(int index, E value) {
		// 잘못된 인덱스를 참조할 경우 예외 발생
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		// 추가하려는 index가 가장 앞에 추가하려는 경우 addFirst 호출
		if(index == 0) {
			addFirst(value);
			return;
		}
		
		// 추가하려는 index가 마지막 위치일 경우 addLast 호출
		if(index == size) {
			addLast(value);
			return;
		}
		
		// 추가하려는 위치의 이전 노드
		Node<E> prev_Node = search(index-1);
		
		// 추가하려는 위치의 노드
		Node<E> next_Node = prev_Node.next;
		
		// 추가하려는 노드
		Node<E> newNode = new Node<E>(value);
		
		// 이전 노드가 가리키는 노드를 끊은 뒤 새 노드로 변경
		// 또한 새노드가 가리키는 노드는 next_Node로 설정
		prev_Node.next = null;
		prev_Node.next = newNode;
		newNode.next = next_Node;
		size++;
	}
	
	/*
	 * remove()
	 * - 가장 앞의 요소(head)를 삭제 - remove()
	 * 		head가 가리키는 요소만 없애주면 됨
	 * 		head가 가리키는 노드의 링크와 데이터를 null로 지워준 뒤 head를 다음 노드로 업데이트
	 * 		그리고 삭제하려는 노드가 리스트에서 유일한 노드였을 경우 해당 노드를 삭제하면 tail이 가리키는 노드 또한 없어지게 됨. 이에 대해서도 정확한 처리를 해줘야함
	 * - 특정 index의 요소를 삭제 - remove(int index)
	 * - 특정 요소를 삭제 - remove(Object value)
	 */
	public E remove() {
		Node<E> headNode = head;
		
		if(headNode == null) {
			throw new NoSuchElementException();
		}
		
		// 삭제된 노드를 반환하기 위한 임시 변수
		E element = headNode.data;
		
		// head의 다음 노드
		Node<E> nextNode = head.next;
		
		// head 노드의 데이터들을 모두 삭제
		head.data = null;
		head.next = null;
		
		// head가 다음 노드를 가리키도록 업데이트
		head = nextNode;
		size--;
		
		// 삭제된 요소가 리스트의 유일한 요소였을 경우 head 이자 tail이었으므로 삭제되면서 tail도 가리킬 요소가 없기 때문에 size가 0일 경우 tail도 null
		if(size == 0) {
			tail = null;
		}
		return element;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(E value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int index, E value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(E value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int indexOf(E value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
}
