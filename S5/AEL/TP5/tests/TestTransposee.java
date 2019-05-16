package tests;

import automata.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TestTransposee {

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
		/* Test automate transposé */
		AutomataUtils a = new AutomataUtils();
		AutomatonBuilder a2 = new NDAutomaton();
		Iterable<String> s = Arrays.asList("abc","def");
		a.addFiniteSet(s,a2);
		AutomatonBuilder a3 = new NDAutomaton();
		dotToFile(a2, "dot/automate-TestTransposeeInitial.dot");
		System.out.println(a2);
		a.transpose(a2,a3);
		dotToFile(a3, "dot/automate-TestTransposee.dot");
		System.out.println(a3);
	}
}
