package DataStructure;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
	/*
	 * 다른 자료구조와 달리 Object[] 배열(객체 배열)을 두고 사용한다는 점
	 * 동적 할당을 전제로 함
	 * 데이터 사이에 빈 공간을 허락하지 않음
	 * 리스트 계열 자료구조는 데이터들이 연속되어 있어야 함
	 */
	
	/*
	 * DEFAULT_CAPACITY : 배열이 생성 될 때의 최초 할당 크기(용적)이자 최소 할당 용적 변수로 기본값은 10
	 * EMPTY_ARRAY : 아무것도 없는 빈 배열(용적이 0 인 배열)
	 * size : 배열에 담긴 요소의 개수 (용적 크기가 아님!!!)
	 * arrya : 요소들을 담을 배열
	 */
	private static final int DEFAULT_CAPACITY = 10;	// 최소(기본) 용적 크기
	private static final Object[] EMPTY_ARRAY = {};	// 빈 배열
	
	private int size;	// 요소 개수
	
	Object[] array;	// 요소를 담을 배열
	
	// 생성자1 (초기 공간 할당 X)
	public ArrayList() {
		this.array = EMPTY_ARRAY;
		this.size = 0;
	}
	
	// 생성자2 (초기 공간 할당 O)
	public ArrayList(int capacity) {
		this.array = new Object[capacity];
		this.size = 0;
	}
	
	
	// 동적할당을 위한 resize 메소드 구현
	private void resize() {
		int array_capacity = array.length;
		
		// if array's capacity 0
		if(Arrays.equals(array, EMPTY_ARRAY)) {	// 주소가 아닌 값을 비교해야 하기 때문에 Arrays.equals() 메소드를 사용
			array = new Object[DEFAULT_CAPACITY];
			return;
		}
		
		// 용량이 꽉 찰 경우
		if(size == array_capacity) {
			int new_capacity = array_capacity * 2;
			
			// copy
			array = Arrays.copyOf(array, new_capacity);
			return;
		}
		
		// 용적의 절반 미만으로 요소가 차지하고 있을 경우
		if(size < (array_capacity / 2)) {
			int new_capacity = array_capacity / 2;
			
			// copy
			array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
			return;
		}
	}
	
	/*
	 * add 메소드 구현
	 * add 메소드에는 크게 3가지로 분류가 됨
	 * - 가장 마지막 부분에 추가(기본값) - addLast(E value)
	 * - 가장 앞부분에 추가 - addFirst(E value)
	 * - 특정 위치에 추가 - add(int index, E value)
	 */
	@Override
	public boolean add(E value) {
		// 가장 마지막 부분에 추가
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		// 꽉차있는 상태라면 용적 재할당
		if(size == array.length) {
			resize();
		}
		array[size] = value;	// 마지막 위치에 요소 추가
		size++;	// 사이즈 1 증가
	}

	@Override
	public void add(int index, E value) {
		// 중간에 추가
		if(index > size || index < 0) {	// 영역을 벗어날 경우 예외 발생
			throw new IndexOutOfBoundsException();
		}
		
		if(index == size) {	// index가 마지막 위치라면 addLast 메소드로 요소 추가
			addLast(value);
		}else {
			if(size == array.length) {	// 꽉차있다면 용적 재할당
				resize();
			}
			
			// index 기준 후자에 있는 모든 요소들을 한 칸씩 뒤로 밀기
			for(int i=size; i>index; i--) {
				array[i] = array[i-1];
			}
			
			array[index] = value;	// index 위치에 요소 할당
			size++;
		}
	}
	
	public void addFirst(E value) {
		add(0, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if(index >= size || index < 0) {	// 범위 벗어나면 예외 발생
			throw new IndexOutOfBoundsException();
		}
		
		// Object 타입에서 E타입으로 캐스팅 후 반환;
		return (E) array[index];
	}
	/*
	 * @SuppressWarnings("uncheckd")
	 * 붙이지 않으면 return값에 type safe에 대해 경고를 보냄
	 * 반환되는 것을 보면 E타입으로 캐스팅을 하고 있고 그 대상이 되는 것은 object[] 배열의 objec 데이터임
	 * 즉, Object -> E 타입으로 변환을 하는 것인데 이 과정에서 변환할 수 없는 타입을 가능성이 있다는 경고로 메소드 옆에 경고 표시가 뜨는데, add 하여 받아들이는 데이터 타입은 유일하게 E타입만 존재함
	 * 그렇기때문에 형 안정성이 보장됨
	 * 한마디로 ClassCastException이 뜨지 않으니 이 경고들을 무시하겠다는것임
	 * 
	 */
	
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
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
}
