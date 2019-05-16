package generics;

import scanner.*;
import java.util.*;

public class ClonableVegetableListChoser {

	// methode "chose"
	// elle est similaire a celles de ListChoser et ListChoserLegume
	// simplement elle ne fonctionne qu'avec des listes d'objets de type Legume
	// qui de plus sont clonables (donc implémentent l'interface
	// java.lang.Clonable).
	// Methode chose, qui prend en premier parametre
	// un message sous forme de chaene de caracteres et en second une liste.
	// Cette liste est typee mais sans restriction sur les types admis.
	// Cette methode propose de choisir un element de la liste en saisissant
	// sa position dans la liste.
	// L'element choisi est retourne par la methode, null si le choix 0 est
	// fait.
	//
	public <T extends Vegetable & Cloneable> T chose(String message, List<T> list){
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

		List<Carrot> lCarrots = new ArrayList<Carrot>();
		lCarrots.add(new Carrot(1));
		lCarrots.add(new Carrot(2));
		lCarrots.add(new Carrot(3));

		List<Cauliflower> lCauliflowers = new LinkedList<Cauliflower>();
		lCauliflowers.add(new Cauliflower(1));
		lCauliflowers.add(new Cauliflower(2));
		lCauliflowers.add(new Cauliflower(3));

		ClonableVegetableListChoser lclc = new ClonableVegetableListChoser();

		Carrot chosenCarrot = lclc.chose("which carrot ? ", lCarrots);
		System.out.println("you have chosen : " + chosenCarrot);

		Cauliflower chosenCauliflower = lclc.chose("which cauliflower ? ", lCauliflowers);
		System.out.println("you have chosen : " + chosenCauliflower);

		List<Rutabaga> lRutabagas = new ArrayList<Rutabaga>();
		lRutabagas.add(new Rutabaga(1));
		lRutabagas.add(new Rutabaga(2));
		lRutabagas.add(new Rutabaga(3));
		// NE COMPILE PAS :
		// Rutabaga chosenRutabaga = lclc.chose("which rutabaga ? ",lRutabagas);

		List<Apple> lApples = new ArrayList<Apple>();
		lApples.add(new Apple(1));
		// NE COMPILE PAS :
		// Apple chosenApple = lclc.chose("which apple ? ",lApples);

	}
}
