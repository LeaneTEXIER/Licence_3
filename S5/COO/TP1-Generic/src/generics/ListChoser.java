package generics;

import scanner.*;
import java.util.*;

public class ListChoser {

	// Methode chose, qui prend en premier parametre
	// un message sous forme de chaene de caracteres et en second une liste.
	// Cette liste est typee mais sans restriction sur les types admis.
	// Cette methode propose de choisir un element de la liste en saisissant
	// sa position dans la liste.
	// L'element choisi est retourne par la methode, null si le choix 0 est
	// fait.
	//
	public <T> T chose(String message, List<T> list){
		int i;
		int choice;
		int size;
		size = list.size();
		i=1;
		if (list.isEmpty()){
			return null;
		}
		System.out.println(message);
		System.out.println("0 : none");
		for (Object item: list){
			System.out.println((i++)+" : "+item);
		}
		choice =  ScannerInt.readInt(size+1);
		if (choice==0){
			return null;
		}
		else{
			return list.get(choice-1);
		}
	}

	public static void main(String[] args) {
		// JEU DE TEST

		List<Carrot> lCarrots = new ArrayList<Carrot>();
		lCarrots.add(new Carrot(1));
		lCarrots.add(new Carrot(2));
		lCarrots.add(new Carrot(3));

		List<Apple> lApples = new ArrayList<Apple>();
		lApples.add(new Apple(1));
		lApples.add(new Apple(2));
		lApples.add(new Apple(3));

		ListChoser lc = new ListChoser();

		Carrot chosenCarrot = lc.chose("which carrot ? ", lCarrots);
		System.out.println("you have chosen : " + chosenCarrot);

		Apple chosenApple = lc.chose("which appel? ", lApples);
		System.out.println("you have chosen : " + chosenApple);
	}
}
