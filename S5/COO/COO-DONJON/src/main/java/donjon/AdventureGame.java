package donjon;

import donjon.room.*;
import donjon.gameCharacter.*;
import donjon.item.*;
import donjon.action.*;
import donjon.Donjon;

public class AdventureGame {
	/*Attributs */
	private Room currentRoom;
	private Player player;
	
	/* Create a new Game 
	 * @param startingRoom the room where the game starts
	 */
	public AdventureGame(Room startingRoom){
		this.player = new Player();
		this.player.setRoom(startingRoom);
		this.currentRoom = startingRoom;
	}
	
	/* Return the current room
	 * @return the room
	*/
	public Room currentRoom(){
		return this.currentRoom;
	}
	
	/* Return the Player
	 * @return the player
	*/
	public Player getPlayer(){
		return this.player;
	}
	
	/* Add a monster in a room
	 * @param monster the monster to add
	 * @param room the room where is the monster
	 */
	public void addMonster(Monster monster, Room room){
		room.addMonster(monster);
	}
	
	/* Add an item in a room
	 * @param item the item to add
	 * @param room the room where is the item
	 */
	public void addItem(Item item, Room room){
		room.addItem(item);
	}
	
	/* Return True if the game is finished, else return false
	 * @return true if the game is finished, else false
	 */
	public boolean isFinished(){
		return (this.getPlayer().isDead() || this.currentRoom().isExit());
	}
	
	/* Play the game until the end*/
	public void play(){
		int i;
		i = 1;
		while (!(this.isFinished())){
			System.out.println("Round number "+(i++));
			displayInformations();
			this.getPlayer().act();
			this.currentRoom = this.getPlayer().getRoom();
			System.out.println("\n\n");
		}
		this.endGame();
	}
	
	/* Display if the player win or not*/
	public void endGame(){
		if (this.currentRoom().isExit()){
			System.out.println("You win!");
		}
		else{
			System.out.println("You lose!");
		}
	}
	
	public void displayInformations(){
		Player p = this.getPlayer();
		System.out.println("You have "+p.getLifePoints()+" lifePoints, "+p.getStrength()+" strength points and "+p.getGold()+" gold coins.");
	}
	
	public static void main(String[] args) {
		Room r = new Room();
		AdventureGame g = new AdventureGame(r);
		new Donjon(r);
		g.getPlayer().addAction(new SeeAction());
		g.getPlayer().addAction(new MoveAction());
		g.getPlayer().addAction(new FightAction());
		g.getPlayer().addAction(new UseAction());
		g.play();
	}
}
