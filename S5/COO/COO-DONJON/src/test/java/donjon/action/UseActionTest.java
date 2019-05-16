package donjon.action;

import donjon.room.Room;
import donjon.item.*;

public class UseActionTest extends ActionTest{

	public Action createAction(){
		return new UseAction();
	}

	public void addToTrue(Room r){
		Item i = new Gold(5);
		r.addItem(i);
	}
	
}
