package tests;

import automata.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TestDeterminizeAutomaton {

	private static void dotToFile(Automaton a, String fileName) {
		File f = new File(fileName);
		try {
			PrintWriter sortieDot = new PrintWriter(f);
			sortieDot.println(a.toGraphviz());
			sortieDot.close();
		} catch (IOException e) {
			System.out.println("création du fichier " + fileName + " impossible");
		}
	}


	public static void main(String[] args) throws StateException {

		/* Fabrication de l'automate */

		AutomatonBuilder a = new NDAutomaton();

		/*
		 * Définition des états Notez que les états sont numérotés 0, 1, 2, ... dans
		 * l'ordre de leur création dans l'automate par défaut les états sont nommés
		 * "qi", où i est leur numéro On peut leur choisir un autre nom en le passant en
		 * paramètre de la méthode addNewState
		 */

		a.addNewState();
		a.addNewState();
		a.addNewState();
		a.addNewState();

		/*
		 * Définition des états initiaux et des états acceptants Le paramètre est
		 * indifféremment le numéro ou le nom d'un état
		 */
		a.setAccepting("q0");
		a.setInitial("q0");
		a.setAccepting("q2");
		a.setInitial("q2");

		/*
		 * Définition des transitions
		 */
		a.addTransition("q0", 'a', "q1");
		a.addTransition("q1", 'b', "q0");
		a.addTransition("q2", 'b', "q3");
		a.addTransition("q3", 'a', "q2");

		/* Test automate determinize */
		AutomataUtils au = new AutomataUtils();
		AutomatonBuilder dfa = new NDAutomaton();
		dotToFile(a, "dot/automate-TestDeterminizeAutomatonInitial.dot");
		System.out.println(a);
 		au.determinize(a, dfa);
		dotToFile(dfa, "dot/automate-TestDeterminizeAutomaton.dot");
		System.out.println(dfa);
	}
}
