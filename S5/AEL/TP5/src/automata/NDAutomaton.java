package automata;

import java.util.*;

/**
 *
 * Implémentation d'un automate non déterministe.
 * Version incomplète.
 *
 * @author Bruno.Bogaert (at) univ-lille1.fr
 *
 */
public class NDAutomaton extends AbstractNDAutomaton implements Recognizer, AutomatonBuilder {

	/** Return true if the word is accept, else false
	 * @param word the word to test
	 */
	public boolean accept(String word){
		Set<State> statesInit = this.getInitialStates();
		if (word.length()!=0){
			return acceptRec(word, statesInit);
		}
		else{
			for (State st: statesInit){
				if (this.getAcceptingStates().contains(st)){
					return true;
				}
			}
			return false;
		}
	}

	/** Return true if the word is accept, else false
	 * @param word the word to test
	 * @param states the states where we have to beggin to check
	 */
	public boolean acceptRec(String word, Set<State> states) {
		Set<State> s = new HashSet<State>();
		char l = word.charAt(0);
		for (State st: states){
			s.addAll(this.getTransitionSet(st,l));
		}
		if (s.isEmpty()){
			return false;
		}
		else if (word.length()>1){
			return this.acceptRec(word.substring(1), s);
		}
		else{
			for (State st: s){
				if (this.getAcceptingStates().contains(st)){
					return true;
				}
			}
			return false;
		}
	}

  /**
  * Calcul de l’ensemble des états pouvant être obtenus depuis un ensemble d’états
  *@param fromSet : ensemble d’états
  *@param letter : lettre de l’alphabet
  *@return ensemble d’états pouvant être obtenus en lisant letter,
  *en partant de n’importe lequel des états de l’ensemble fromSet
  */
	public Set<State> getTransitionSet(Set<State> fromSet, char letter) {
		Set<State> s = new PrintSet<State>();
		for (State i : fromSet){
			s.addAll(getTransitionSet(i, letter));
		}
		return s;
	}




}
