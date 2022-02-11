package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Friend {
	private Collection<Friend> friends;
	private String email;
	
	public Friend(String email) {
		this.email = email;
		this.friends = new ArrayList<Friend>();
	}
	
	public String getEmail() {
		return email;
	}
	
	public Collection<Friend> getFriends(){
		return friends;
	}
	
	public void addFriendship(Friend friend) {
		friends.add(friend);
		friend.getFriends().add(this);
	}
	
	public boolean canBeConnected(Friend friend) {
		String myEmail = this.getEmail();
		
		String friendEmail = friend.email;
		
		boolean[] visited = new boolean['Z'+1-65];
		visited[friendEmail.charAt(0)-65] = true;
		
		Queue<Friend> que = new LinkedList<>();
		que.add(friend);
		
//		Collection<Friend> test = friend.getFriends();
//		for(Friend test2 : test) {
//			System.out.println("!" + test2.email);
//		}
		
		while(!que.isEmpty()) {
			Friend nextFriend = que.poll();
			
			Collection<Friend> friendList = nextFriend.getFriends();
			for(Friend friend2 : friendList) {
				String nextFriendEmail = friend2.getEmail();
				
				if(!visited[nextFriendEmail.charAt(0)-65]) {
					if(nextFriendEmail.equals(myEmail)) return true;
					
					que.add(friend2);
					visited[nextFriendEmail.charAt(0)-65] = true;
				}
			}
		}
		
		
		return false;
	}
	
	public static void main(String[] args) {
		Friend a = new Friend("A");
		Friend b = new Friend("B");
		Friend c = new Friend("C");
//		Friend d = new Friend("D");
		
		a.addFriendship(b);
		b.addFriendship(c);
		
		System.out.println(a.canBeConnected(c));
		
		
	}
}
