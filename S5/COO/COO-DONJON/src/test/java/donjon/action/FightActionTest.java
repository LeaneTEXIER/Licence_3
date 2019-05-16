package donjon.action;

import donjon.room.*;
import donjon.gameCharacter.*;

public class FightActionTest extends ActionTest{

	public Action createAction(){
		return new FightAction();
	}

	public void addToTrue(Room r){
		Monster m = new Monster();
		r.addMonster(m);
		m.addLifePoints(5);
	}
	

}
