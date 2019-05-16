package donjon.action;

import donjon.Direction;
import donjon.room.Room;

public class MoveActionTest extends ActionTest{

	public Action createAction(){
		return new MoveAction();
	}

	public void addToTrue(Room r){
		Room r2 = new Room();
		Direction dN =  Direction.N;
		r.addNeighbour(dN, r2);
	}

}
