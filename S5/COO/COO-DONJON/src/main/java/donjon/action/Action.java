package donjon.action;

import donjon.gameCharacter.Player;

public interface Action {
	/* Return true if the action is possible, else return false
	 * @param p the player who wants do the action
	 * @return true if the action is possible, else false
	 */
	public boolean isPossible(Player p);
	
	/* Player p executes the action
	 * @param p the player who executes the action 
	 */
	public void execute(Player p);
}