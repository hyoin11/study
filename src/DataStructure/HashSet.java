package DataStructure;

public class HashSet<E> implements Set<E> {
	/*
	 * Hash란
	 * 	임의의 길이를 갖는 데이터를 고정된 길이의 데이터로 변환(매핑)하는 것
	 * 	위와 같은 기능을 하는 함수를 해시 함수(Hash Function)이라고 함
	 * 	쉽게 말해서 예를들어 abcd라는 문자열을 특정 길이(예> 256바이트, 512바이트...)의 데이터로 변환시키는 것
	 * 	(이때 32비트는 2진수일 때 기준이므로, 16진수로 보자면 4비트 당 한 묶음이기 때문에 16진수로 표현하면 8개의 문자를 얻게 됨
	 * 	즉, 해시함수를 돌리기 전 문자열의 길이가 얼마건 일정한 길이를 얻는다는 것)
	 * 	이러한 과정을 해싱(hashing)이라고 하고 해시함수에 얻어진 값을 보통 다이제스트(digest)라고 함
	 * 	해시 함수를 돌리는 이유는 자료구조에서 특정 값이 있는지 찾아내기 위해서 배열 혹은 노드를 순회하면서 찾았지만 해시함수는 순회할 필요가 없음
	 * 	왜냐하면 동일한 메세지(값)에 대해서는 동일한 다이제스트를 갖기 때문임
	 * 	동일한 해시 알고리즘을 사용하는 경우 동일한 값에 대하여 동일한 다이제스트를 얻게 됨
	 * 	특정 값에 대한 다이제스트는 변하지 않기 때문에 이 다이제스트의 값을 배열 위치로 활용
	 * 	좀 더 쉽게 말하자면 특정 값에 대해 배열의 특정 위치로 지정해두는 것과 같음
	 * 
	 * set은 중복원소를 허용하지 않는데 원소를 추가해야 할 때마다 해당 원소가 중복되는지 검사를 해야함
	 * 모든 원소를 검사하면 매우 비효율적이라 hash함수를 통해 특정 값에 대한 고유의 다이제스트를 얻고 그 값에 대응하는 인덱스를 찾아 해당 인덱스에 있는 요소만 검사하면됨
	 * 
	 * 자바에서는 hashCode() 라는 함수가 있음
	 * 객체의 주소값을 이용하여 해시 알고리즘에 의해 생성된 고유의 정수값을 반환함
	 * 즉, 객체가 같은 메모리 주소를 가리키고 있다면 같은 정수값을 얻음
	 * 여기서 주의할 점은 객체가 같은 주소를 가리킬 경우임. 객체가 같은 내용을 지니더라도 가리키는 주소가 다르면 다른 값을 지닌다는 것
	 * 기본적 자료형으로는 같은 내용일 경우 동일한 값을 갖으나, 만약 사용자 클래스를 사용하게 될 경우 해당 클래스 내에 hashCode메소드를 오버라이드 해주지 않으면 메모리 주소를 기반으로 해싱된 값이 나오기 때문에 객치내용을 비교해주기 위해서는 반드시 hashCode()를 오버라이드 해줘야함
	 * 
	 * 해시충돌
	 * hashCode()를 사용하더라도 문제점이 있음
	 * int형의 범위는 32비트로 표현되는 자료형이기때문에 2의 32승의 경우의 수를 갖음
	 * 하지만 우리가 생성가능한 경우의 수는 훨씬 많음. 모든 객체를 다 표현할 수 없다는 한계가 있음
	 * 설령 생성되는 객체가 우연하게 2의 32승으로 표현 가능하더라도 문제가 되는 점은 그만큼의 버킷(배열) 공간을 생성해야한다는 점
	 * 이는 메모리낭비가 심할뿐더러 자바에서는 대략 단일 배열에 대해 약 21억 크기까지만 생성이 가능함
	 * 그렇기 때문에 메모리의 낭비를 줄이기 위해 배열의 사이즈를 줄여서 사용함
	 * 예를들어 m개의 인덱스를 갖고있는 배열(테이블)이 있을 때 ind idx = Math.abs(x.hashCode()) % m; 으로 인덱스 지정(음수값이 나올 수 있으므로 절대값으로 변환)
	 * 위와 같이 해주어도 해시충돌의 경우와 버킷(배열)의 크기에 따른 같은 인덱스를 참조하는 일이 발생할 수 밖에 없음
	 * 이를 해결할 수 있는 방법은 크게 두가지로 나뉨
	 * 1. Open Addressing 방식
	 * 	장점
	 * 		해시충돌이 일어날 가능성이 적기 때문에 데이터의 개수가 작을수록 속도가 빠름
	 * 		연속된 공간에 데이터를 저장하기 때문에 캐시 효율이 좋아짐
	 * 	단점
	 * 		해시충돌이 발생하면 해당 객체의 인덱스 값이 바뀜
	 * 		부하율(load factor) 즉, 테이블에 저장된 데이터의 개수가 많아질수록(=밀도가 높아질수록) 성능이 급격히 저하됨
	 * 2. Separate Chaining 방식
	 * 	장점
	 * 		해시충돌이 일어나더라도 연결리스트로 노드가 연결되기 때문에 인덱스가 변하지 않음
	 * 		테이블의 각 인덱스는 연결리스트로 구현되기 때문에 데이터 개수의 제약을 거의 받지 않음
	 * 	단점
	 * 		부하율(load factor)에 따라 선형적으로 성능이 저하 됨. 즉, 부하율이 작을 경우에는 Open Addressing방식이 빠름
	 * 자바는 separate chaining 방식을 사용함
	 * 또한 최악의 경우 해시충돌에 의해 한개의 인덱스에 여러개의 노드가 연결될 경우도 있음
	 * 자바 내부에서는 이럴 경우 해당 인덱스의 LinkedList를 Binary Tree(이진트리)로 변환하여 데이터의 색인 속도를 높임
	 * 하지만, 이진트리로 구현하기는 너무 복잡하여 링크드리스트 형식으로만 연결하기로 함
	 * 
	 * 해시셋은 key, hash, next로 구성된 노드로 구현
	 * 	key : HashSet에서는 data(value)자체가 key값이 됨
	 * 	hash : 해싱 된 key의 고유값
	 * 	next : 다음 노드를 가리키는 변수 
	 */
	
	// 최소 기본 용적이며 2^n 꼴 형태가 좋음
	private final static int DEFAULT_CAPACITY = 1 << 4;
	
	// 3/4 이상 채워질 경우 resize하기 위함
	private final static float LOAD_FACTOR = 0.75f;	// 테이블의 크기에 비해 데이텅양이 일정 이상 많아지면 성능이 저하됨. 그 기준점을 0.75로 잡아 75프로가 넘으면 배열의 용적을 늘릴 수 있도록 기준이 되는 변수
	
	HashNode<E>[] table;	// 요소의 정보를 담고있는 Node를 저장할 Node타입 배열
	private int size;	// 요소의 개수
	
	@SuppressWarnings("unchecked")
	public HashSet() {
		table = (HashNode<E>[]) new HashNode[DEFAULT_CAPACITY];
		size = 0;
	}
	
	// 보조 해시 함수 (상속 방지를 위해 private static final 선언)
	private static final int hash(Object key) {
		int hash;
		if(key == null) {
			return 0;
		}else {
			// hashCode()의 경우 음수가 나올 수 있으므로 절댓값을 통해 양수로 변환
			return Math.abs(hash = key.hashCode()) ^ (hash >>> 16);
		}
	}
	/*
	 * 반드시 필요한 메소드는 아님
	 * 요즘에는 hash함수도 고른 분포를 갖을 수 있게 설계되어있기 때문에 큰 역할을 하지는 못하지만
	 * 원래는 최대한 해시충돌을 피하기 위해 보조해시함수를 써서 고른 분포를 할 수 있도록 하기 위한 함수
	 * 여기서는 자바11의 구현유사하게 key의 hsahCode()의 절댓값과 그 값을 16비트 왼쪽으로 밀어낸 값하고의 XOR값을 반환하도록 만듦
	 * (만약 나머지 연산(%)을 할 경우에는 hashCode() 값이 음수가 나올 때를 고려하여 Math.abs() 함수를 써야하나, 만약 비트 &연산을 통해 할 경우 Math.abs()를 써줄 필요가 없다)
	 */
	
	/*
	 * HashSet에서는 사용자 용적을 설정할 수 있는 생성자를 따로 두지 않음
	 * 해시셋의 용적을 2^n꼴로 두는 것이 매우 편리하고 좋기 때문
	 * 사용자가 해시충돌을 예측하기 어렵고, 해시충돌이 발생하더라도 Separate Chaining방식으로 연결되기 때문에 굳이 사용자에게 용적을 설정하게 둘 필요가 없음
	 */
	
	/*
	 * add()
	 * 중복된 값을 허용하지 않음
	 * 즉, 사용자가 추가한 data는 key를 의미하고, 이 key에 대한 hash 값을 얻어 table 배열의 위치를 지정하여 해당 위치의 노드를 추가해야함
	 * 
	 * 	1. key로 받은 데이터를 보조해시 함수를 돌려 hash 값을 얻어냄
	 * 	2. hash 값을 토대로 index를 구한 뒤 원소를 추가
	 * 	3. 기존 원소가 존재할 경우 해당 node부터 연결된 노드의 끝까지 탐색하면서 key가 같은지 비교
	 * 	4. 마지막 노드까지 key 겹치지 않을 경우 가장 마지막 노드에 연결
	 */
	public boolean add(E e) {
		// key(e)에 대해 만들어두었던 보조해시함수의 값과 key(데이터 e)를 보냄
		return add(hash(e), e) == null;
	}
	
	private E add(int hash, E key) {
		int idx = hash % table.length;
		
		// table[idx] 가 비어있을 경우 새 노드 생성
		if(table[idx] == null) {
			table[idx] = new HashNode<E>(hash, key, null);
		}
		/*
		 * table[idx]에 요소가 이미 존재할 경우(==해시충돌)
		 * 1. 객체가 같은 경우
		 * 2. 객체는 같지 않으나 얻어진 인덱스가 같은경우
		 */
		else {
			HashNode<E> temp = table[idx]; 	// 현재 위치 노드
			HashNode<E> prev = null;	// temp의 이전 노드
			
			// 첫 노드(table[idx])부터 탐색
			while(temp != null) {
				/*
				 * 만약 현재 노드의 객체가 같은 경우(해시값이 같으면서 키가 같을경우)는 중복을 허용하지 않으므로 키를 반납(반환)
				 * (키가 같은 경우는 주소가 같거나 객체가 같은 경우가 존재)
				 */
				if((temp.hash == hash) && (temp.key == key || temp.key.equals(key))) return key;
				
				prev = temp;
				temp = temp.next;	// 다음 노드로 이동
			}
			
			// 마지막 노드에 새 노드를 연결
			prev.next = new HashNode<E>(hash, key, null);
		}
		size++;
		
		// 데이터의 개수가 현재 table 용적의 75%를 넘어가는 경우 용적을 늘려줌
		if(size >= LOAD_FACTOR * table.length) {
			resize();	// 아직 미구현
		}
		
		return null;	// 정상적으로 추가되었을 경우 null 반환
	}
	
	/*
	 * resize()
	 * 일정 이상 데이터가 차면 배열을 늘려주어 재배치를 해주는 것이 성능에서 우월함
	 * 용적대비 데이터 개수의 비율을 부하율(load factor)라고 하고 이 기준점을 0.75 75%로 마지노선으로 잡음
	 * 
	 * 이번 resize()메소드는 두가지 방식으로 구현됨
	 * - 보편적인 방식
	 * - 용적이 만약 2^n꼴로 표현 될 경우 수학, 논리연산 지식을 조금 응용
	 */
	@SuppressWarnings("unchecked")
	private void resize() {
		int newCapacity = table.length * 2;
		
		// 기존 테이블의 두배 용적으로 생성
		final HashNode<E>[] newTable = (HashNode<E>[]) new HashNode[newCapacity];
		
		// 0번째 인덱스부터 차례대로 순회
		for(int i=0; i<table.length; i++) {
			// 각 인덱스의 첫번째 노드(head)
			HashNode<E> value = table[i];
			
			// 해당 값이 없을 경우 다음으로 넘어감
			if(value == null) continue;
			
			table[i] = null;	// gc
			
			HashNode<E> nextNode;	// 현재 노드의 다음 노드를 가리키는 변수
			
			// 현재 인덱스에 연결된 노드들을 순회
			while(value != null) {
				int idx = value.hash % newCapacity;	// 새로운 인덱스를 구함
				
				// 새로 담을 인덱스에 요소(노드)가 존재할 경우 == 새로 담을 newTable에 인덱스값이 겹칠 경우(해시 충돌)
				if(newTable[idx] != null) {
					HashNode<E> tail = newTable[idx];
					
					// 가장 마지막 노드로 감
					while(tail.next != null) tail = tail.next;
					
					/*
					 * 반드시 value가 참조하고 있는 다음 노드와의 연결을 끊어주어야함
					 * 안하면 각 인덱스의 마지막 노드(tail)도 다른 노드를 참조하게 되어버려 잘못된 참조가 발생할 수 있음
					 */
					nextNode = value.next;
					value.next = null;
					tail.next = value;
				}
				// 충돌되지 않는다면(= 빈 공간이라면 해당 노드를 추가)
				else {
					/*
					 * 반드시 value가 참조하고 있는 다음 노드와의 연결을 끊어주어야 함
					 * 안하면 각 인덱스의 마지막 노드(tail)도 다른 노드를 참조하게 되어버려 잘못된 참조가 발생할 수 있음
					 */
					nextNode = value.next;
					value.next = null;
					newTable[idx] = value;
				}
				
				value = nextNode;	// 다음 노드로 이동
			}
		}
		table = null;
		table = newTable;	// 새로 생성한 table을 table변수에 연결
	}
	
}
