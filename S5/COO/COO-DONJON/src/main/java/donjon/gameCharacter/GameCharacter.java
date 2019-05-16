package donjon.gameCharacter;

import donjon.room.*;

public abstract class GameCharacter {
	/* Attributs*/
	protected Room room;
	protected int lifePoints;
	protected int strength;
	protected int gold;
	
	/* Create a GameCharacter */
	public GameCharacter(Room r, int lifePoints, int strength, int gold){
		this.room = r;
		this.lifePoints = lifePoints;
		this.strength = strength;
		this.gold = gold;	
	}
	
	/* Return the room where is the character
	 * @return the room
	*/
	public Room getRoom(){
		return this.room;
	}
	
	/* Change the room where is the character
	 * @param r the room 
	*/
	public void setRoom(Room r){
		this.room=r;
	}
	
	/* Return the number of lifePoints of the character
	 * @return the number of lifePoints
	*/
	public int getLifePoints(){
		return this.lifePoints;
	}
	
	/* Add lifePoints at the character
	 * @param i the lifePoints to add
	*/
	public void addLifePoints(int i){
		this.lifePoints+=i;
	}
	
	/* Remove lifePoints at the character
	 * @param i the lifePoints to remove
	*/
	public void removeLifePoints(int i){
		this.lifePoints-=i;
		if (this.getLifePoints()<0){
			this.lifePoints=0;
		}
	}
	
	/* Return the strength of the character
	 * @return the strength
	*/
	public int getStrength(){
		return this.strength;
	}
	
	/* Add strength at the character
	 * @param i the strength to add
	*/
	public void addStrength(int i){
		this.strength+=i;
	}
	
	/* Remove strength at the character
	 * @param i the strength to remove
	*/
	public void removeStrength(int i){
		this.strength-=i;
		if (this.getStrength()<0){
			this.strength=0;
		}
	}
	
	/* Return the number of gold of the character
	 * @return the number of gold
	*/
	public int getGold(){
		return this.gold;
	}
	
	/* Add gold at the character
	 * @param i the gold to add
	*/
	public void addGold(int i){
		this.gold+=i;
	}
	
	/* Remove gold at the character
	 * @param i the gold to remove
	*/
	public void removeGold(int i){
		this.gold-=i;
		if (this.getGold()<0){
			this.gold=0;
		}
	}
	
	/* Attack an other character
	 * @param c the character to attack
	 */
	public void attack(GameCharacter c){
		c.removeLifePoints(this.getStrength());
		if (c.isDead()){
			c.die();
		}
	}
	
	/* Return true if the character is dead, else return false
	 * @return true if the character is dead, else false
	 */
	public boolean isDead(){
		if (this.getLifePoints()==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/* What happens when the character dies */
	public abstract void die();
}
