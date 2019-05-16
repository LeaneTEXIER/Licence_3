package automata;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.*;
/**
 *
 * @author Bruno.Bogaert (at) univ-lille.fr
 *
 */
public class AutomataUtils{

	/**
	 * Extends automaton a, so that it accepts also this word.
	 * Created states are prefixed by 'q_'
	 * @param word : word to be accepted
	 * @param a : target automaton
	 */
	public static void addSingleton(String word, AutomatonBuilder a) {
		addSingleton(word, a, "q");
	}

	/**
	 * Extends automaton a so that it accepts also this word.
	 * Created states are prefixed by namePrefix followed by '_'
	 * @param word : word to be accepted
	 * @param a : target automaton
	 * @param namePrefix : prefix to use for state names.
	 */
	public static void addSingleton(String word, AutomatonBuilder a, String namePrefix) {
		int i;
		String prev = namePrefix+"_epsilon";
		String next;
		a.addNewState(prev);
		a.setInitial(prev);
		for (i=0; i<word.length(); i++){
			next = namePrefix+"_"+word.substring(0,i+1);
			a.addNewState(next);
			a.addTransition(prev, word.charAt(i), next);
			prev = next;
		}
		a.setAccepting(prev);
	}

	/**
	 * Extends automaton a so that it accepts also this finite language
	 * created states are prefixed by namePrefix followed by '_'
	 * @param finiteLanguage : set of words to be accepted
	 * @param a : target automaton
	 */
	public static void addFiniteSet(Iterable<String> finiteLanguage, AutomatonBuilder a) {
		int i = 1;
		for (String word: finiteLanguage){
			String pref = "q"+i;
			i++;
			addSingleton(word, a, pref);
		}
	}

	/**
	 * Extends automaton a so that it accepts also language denoted by exp
	 * created states are prefixed by namePrefix followed by '_'
	 * @param exp : flat regular expression (only letters and *)
	 * @param a : target automaton
	 */
	public static void addFlatExp(String exp, AutomatonBuilder a) {
		addFlatExp(exp, a, "q");
	}

	/**
	 * Extends automaton a so that it accepts also language denoted by exp
	 * created states are prefixed by namePrefix followed by '_'
	 * @param exp : flat regular expression (only letters and *)
	 * @param a : target automaton
	 * @param namePrefix : prefix to use for state names.
	 */
	public static void addFlatExp(String exp, AutomatonBuilder a, String namePrefix) {
		int i;
		String prev = namePrefix+"_epsilon";
		String next;
		a.addNewState(prev);
		a.setInitial(prev);
		for (i=0; i<exp.length(); i++){
			next = namePrefix+"_"+exp.substring(0,i+1);
			if (i<exp.length()-1 && exp.charAt(i+1)=='*'){
				a.addTransition(prev, exp.charAt(i), prev);
				i++;
			}
			else{
				a.addNewState(next);
				a.addTransition(prev, exp.charAt(i), next);
				prev = next;
			}
		}
		a.setAccepting(prev);
	}

	/**
	 * Transpose automaton
	 * Note : mirror is cleared before the operation.
	 *
	 * @param original : automaton to be transposed
	 * @param mirror : receive the transposed automaton
	 */
	public static void transpose(Automaton original, AutomatonBuilder mirror) {
		for (State s : original.getStates()){
			mirror.addNewState(s.getName());
		}
		for (State s : original.getInitialStates()){
			mirror.setAccepting(s.getName());
		}
		for (State s : original.getAcceptingStates()){
			mirror.setInitial(s.getName());
		}
		for (State from : original.getStates()){
			for (char letter : original.usedAlphabet()){
				for (State dest : original.getTransitionSet(from,letter)){
					mirror.addTransition(dest.getName(), letter, from.getName());
				}
			}
		}
	}

	/**
	 * Determinization of nfa automaton.
	 * Note : dfa is cleared before the operation.
	 * @param nfa : non deterministic automaton (to be determinize)
	 * @param dfa : receive determinization result
	 */
	public static void determinize(Automaton nfa, AutomatonBuilder dfa) {
		// For each computed state set from nfa, a corresponding state has to be created in dfa
		// map represents relationship  between nfa state set (key) and created dfa state (value)
		Map<Set<State>, State> map = new HashMap<Set<State>, State>();

		// stack todo contains state sets whose transitions have not yet been computed
		Stack<Set<State>> todo = new Stack<Set<State>>();

		dfa.clear();

		Set<State> startSet = nfa.getInitialStates();

		// create matching state in DFA
		State start = dfa.addNewState(startSet.toString()); // state creation
		map.put(startSet, start);  // record relationship in map

		dfa.setInitial(start); // start is the unique initial state of dfa

		todo.push(startSet); // put it in todo list.

		while (! todo.isEmpty()) {
			Set<State> fromSet = todo.pop(); // pick a state from todo list
			for(Character c: nfa.usedAlphabet()){
				Set<State> transitionSet = nfa.getTransitionSet(fromSet, c);
				if (!map.keySet().contains(transitionSet)){
					dfa.addNewState(transitionSet.toString());
					for (State s : fromSet){
						map.put(transitionSet,s);
					}
					todo.add(transitionSet);
				}
				dfa.addTransition(fromSet.toString(),c,transitionSet.toString());
			}
		}
		for (Set<State> qSet : map.keySet()) {	// foreach computed state set
			for (State aSet: nfa.getAcceptingStates()){
				if (qSet.contains(aSet)){
						dfa.setAccepting(qSet.toString());
				}
			}
		}
	}

	/** Create an AhoCorasick automaton
	* @param words the words to add in the automaton
	* @param dest the destination of the automaton create
	*/
	public static void createAhoCorasick (String [] words, AutomatonBuilder dest){
		dest.clear();
		new AhoCorasick(words, dest);
	}

	public void minimalise(Automaton a, AutomatonBuilder dest){
		dest.clear();
		AutomatonBuilder desti1 = new NDAutomaton();
		AutomatonBuilder desti2 = new NDAutomaton();
		AutomatonBuilder desti3 = new NDAutomaton();
		this.transpose(a,desti1);
		this.determinize(desti1, desti2);
		this.transpose(desti2,desti3);
		this.determinize(desti3,dest);
	}

}
