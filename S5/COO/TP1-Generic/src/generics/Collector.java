package generics;
import generics.AlreadyCarryingException;

/** define collectors able to collect (and carry) one specific type T of objects
 * only one T object can be carried at a time
 */

public class Collector <T>{

    public Collector(String name) {
    	this.name = name;
    	this.carriedObject = null;
    }

    private String name;
    private T carriedObject;
    
    public String toString() {
    	return this.name;
    }
    public String description() {
    	return this.name + " carries " + this.carriedObject;
    }
    
    /**
     * the person takes an item of type T if it is possible, else raised an exception
     * @param item : the item to take
     */
    public void take(T item) throws AlreadyCarryingException{
    	if (this.getCarriedObject()==null){
    		this.carriedObject = item;
    	}
    	else{
    		throw new AlreadyCarryingException("The person already carries an item");
    	}
    }

    /**
     * return the carried item
     * @return the carried item
     */
    public T getCarriedObject(){
    	return this.carriedObject;
    }
    
    /**
     * the current collector gives his item to an other collector if it is possible
     * (nothing it's done if the current collector carries no item), else raised an exception
     * @param dest the collector who takes the item that the current collector gives
     */
    public void giveTo(Collector<? super T> dest)throws AlreadyCarryingException{
    	if (this.getCarriedObject()!=null){
    		try{
    			dest.take(this.getCarriedObject());
    			this.carriedObject = null;
    		}
    		catch (Exception e){
    			throw new AlreadyCarryingException("The person already carries an item");
    		}
    	}
    	else{
    		throw new AlreadyCarryingException("The person already carries an item");
    	}
    }
    
    
    /**
     * drop the item carries if the collector carries one, else do nothing
     * @return the item drops
     */
    public T drop(){
    	T item = this.getCarriedObject();
    	if (item!=null){
    		this.carriedObject=null;
    	}
    	return item;
    }
    
    
    public static void main(String[] args) throws AlreadyCarryingException{
	
		Carrot c1 = new Carrot(1);
		Carrot c2 = new Carrot(2);
		Carrot c3 = new Carrot(3);
		Apple p1 = new Apple(1);
		Apple p2 = new Apple(2);

		Collector<Carrot> carrotCollector1 = new Collector<Carrot>("carrot-collector-1");
		Collector<Carrot> carrotCollector2 = new Collector<Carrot>("carrot-collector-2");
		Collector<Apple> appleCollector1 = new Collector<Apple>("apple-collector-1");
		
		// attention ici le type d'objets ramasses est Legume :
		Collector<Vegetable> vegetableCollector = new Collector<Vegetable>("vegetable-collector");

		carrotCollector1.take(c3);
		System.out.println(carrotCollector1.description());
		// NE COMPILE PAS
		// carrotCollector2.take(p1);

		// NE COMPILE PAS
		// carrotCollector1.giveTo(appleCollector1);

		// COMPILE :
		carrotCollector1.giveTo(vegetableCollector);

		// NE COMPILE PAS
		// vegetableCollector.giveTo(carrotCollector1);
		// NE COMPILE PAS
		// appleCollector1.giveTo(vegetableCollector);

		carrotCollector1.take(c1);
		carrotCollector1.giveTo(carrotCollector2);
		System.out.println(carrotCollector1.description());
		System.out.println(carrotCollector2.description());
		carrotCollector1.take(c2);
		
		
		try {
			carrotCollector1.giveTo(carrotCollector2);
		} catch (AlreadyCarryingException e) {
			//System.out.println("*** exception : " + carrotCollector2 + " porte deja qque chose");
			System.out.println(" * " + e.getMessage());
		}

		appleCollector1.take(p2);
		System.out.println(appleCollector1.description());
		try {
			appleCollector1.take(p1);
		} catch (AlreadyCarryingException e) {
			//System.out.println("*** exception : " + appleCollector1 + " porte deja qque chose");
			System.out.println(" * " + e.getMessage());
		}
		appleCollector1.drop();
		System.out.println(appleCollector1.description());
		appleCollector1.take(p1);
	
     }
}
