package logic;

import java.util.List;

import model.Cart;

public class DeleteCart {
	public static List<Cart> execute(List<Cart> cartlist,int pid){
		for(int i = 0;i < cartlist.size();i++) {
			Cart cart = cartlist.get(i);
			if(cart.getProduct().getId() == pid) {
				cartlist.remove(i);
			}
		}
		return cartlist;
	}
}
