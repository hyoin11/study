package Test;

import java.util.ArrayList;
import java.util.Collection;

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
		
		ArrayList<Friend> list = (ArrayList<Friend>) friend.getFriends();
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getEmail());
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		Friend a = new Friend("A");
		Friend b = new Friend("B");
		Friend c = new Friend("C");
		
		a.addFriendship(b);
		b.addFriendship(c);
		
		System.out.println(a.canBeConnected(c));
		
		
	}
}
