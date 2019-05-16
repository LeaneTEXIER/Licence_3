package automata;

import java.util.*;

public class AhoCorasick extends NDAutomaton{

   protected String[] words;
   protected State root;
   protected Map<State, State> repli;

   	/** Create the automaton thanks to a set of words
   	 * @param words the words to add
   	 */
	public AhoCorasick (String[] words){
		this.words = words;
		this.root = this.addNewState("root");
		this.setInitial(this.root);
		this.repli = new HashMap<State, State>();
		this.repli.put(this.root, this.root);
		build(this);
		completeAutomate(this);
	}

  public AhoCorasick (String[] words, AutomatonBuilder a){
		this.words = words;
		this.root = a.addNewState("root");
		a.setInitial(this.root);
		this.repli = new HashMap<State, State>();
		this.repli.put(this.root, this.root);
		build(a);
		completeAutomate(a);
	}

	public void build(AutomatonBuilder a){
		int lgMax = 0;
		State finBranche[] = new State[this.words.length];
		for (int i=0; i<this.words.length; i++){
			finBranche[i] = this.root;
			if (lgMax < this.words[i].length()){
				lgMax = this.words[i].length();
			}
		}
		for (int l=0; l<lgMax; l++){
			for (int i=0; i<this.words.length; i++){
				if (l<this.words[i].length()){
					State q;
          Set<State> s = a.getTransitionSet(finBranche[i],this.words[i].charAt(l));
          if (s.isEmpty()){
						q = createState(finBranche[i],this.words[i].charAt(l), a);
					}
					else{
						q = s.iterator().next();
					}
					finBranche[i] = q;
					if (l+1 == this.words[i].length()){
						a.setAccepting(finBranche[i]);
					}
				}
			}
		}
	}


	public State createState(State parent, char letter, AutomatonBuilder a){
		String name;
		if (parent == this.root){
			name = Character.toString(letter);
		}
		else{
			name = parent.getName()+letter;
		}
		State q = a.addNewState(name);
		a.addTransition(parent,letter,q);
		if (parent == this.root){
			repli.put(q, this.root);
		}
		else{
			State s = parent;
			Set<State> e;
			do{
				s = repli.get(s);
				e = a.getTransitionSet(s, letter);
			} while (e.isEmpty() && s!=this.root);
			if (!e.isEmpty()){
        State tmp = e.iterator().next();
				repli.put(q,tmp);
				if (a.isAccepting(tmp)){
					a.setAccepting(q);
				}
			}
			else{
				repli.put(q,this.root);
			}
		}
		return q;
	}


	public void completeAutomate(AutomatonBuilder a){
		for(State q: a.getStates()){
			for(char letter: a.usedAlphabet()){
        Set<State> s = a.getTransitionSet(q, letter);
				if (s.isEmpty()){
					if (q==this.root){
						a.addTransition(q,letter,this.root);
					}
					else{
						a.addTransition(q,letter,(a.getTransitionSet(this.repli.get(q),letter).iterator().next()));
					}
				}
			}
		}
	}



}
