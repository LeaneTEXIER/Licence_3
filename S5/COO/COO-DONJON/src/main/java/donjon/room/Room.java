package donjon.room;

import java.util.*;
import donjon.gameCharacter.*;
import donjon.item.*;
import donjon.Direction;

public class Room {
	/*Attributs*/
	protected List<Monster> monsters;
	protected List<Item> items;
	protected Map<Direction, Room> neighbours;
	
	/* Create a Room
	*/
	public Room(){
		this.monsters = new ArrayList<Monster>();
		this.items = new ArrayList<Item>();
		this.neighbours = new HashMap<Direction, Room>();
	}
	
	/* Add a monster in the room
	 * @param m the Monster to add
	*/
	public void addMonster(Monster m){
		this.monsters.add(m);
		m.setRoom(this);
	}
	
	/* Remove a monster in the room
	 * @param m the Monster to remove
	*/
	public void removeMonster(Monster m){
		this.monsters.remove(m);
		m.setRoom(null);
	}
	
	/* Return the list of the monsters in the room
	 * @return the list of the monsters
	*/
	public List<Monster> getMonsters(){
		return this.monsters;
	}
	
	/* Add an item in the room
	 * @param i the Item to add
	*/
	public void addItem(Item i){
		this.items.add(i);
	}
	
	/* Remove an item in the room
	 * @param i the item to remove
	*/
	public void removeItem(Item i){
		this.items.remove(i);
	}
	
	/* Return the list of the items in the room
	 * @return the list of the items
	*/
	public List<Item> getItems(){
		return this.items;
	}
	
	/* Add a neighbour in the room
	 * @param d the direction where the neighbour will be
	 * @param r the room that will be the neighbour
	*/
	public void addNeighbour(Direction d, Room r){
		this.neighbours.put(d,r);
		r.neighbours.put(d.opposite(), this);
	}
	
	/* Return the room in the direction d, null if there is no room
	 * @param d ths direction
	 * @return the room in the direction d
	*/
	public Room getNeighbour(Direction d){
		if (this.neighbours.containsKey(d)){
			return this.neighbours.get(d);
		}
		else{
			return null;
		}
	}
	
	/* Return true if the room is an exitRoom, else return false
	 * @return true if the room is an exitRoom, else false
	*/
	public boolean isExit(){
		return false;
	}
	
	/* Return all the directions possible to leave the room
	 * @return the directions possible
	*/
	public Set<Direction> getPossibleDirections(){
		return this.neighbours.keySet();
	}
}
