package tests;

import automata.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TestAddSingleton {

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
		/* Un mot */
		AutomataUtils a = new AutomataUtils();
		AutomatonBuilder a2 = new NDAutomaton();
		dotToFile(a2, "dot/automate-TestAddSingletonInitial.dot");
		a.addSingleton("hello",a2);
		dotToFile(a2, "dot/automate-TestAddSingleton.dot");
		System.out.println(a2);
		/* Plusieurs mots */
		AutomataUtils a3 = new AutomataUtils();
		AutomatonBuilder a4 = new NDAutomaton();
		Iterable<String> s = Arrays.asList("abc","def");
		dotToFile(a4, "dot/automate-TestAddSingletonsInitial.dot");
		a3.addFiniteSet(s,a4);
		dotToFile(a4, "dot/automate-TestAddSingletons.dot");
		System.out.println(a4);
	}
}
