package tests;

import automata.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TestExpRegPlate {

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
		/* Test automate pour expression reguliere "plate"*/
		AutomataUtils a = new AutomataUtils();
		AutomatonBuilder a2 = new NDAutomaton();
		dotToFile(a2, "dot/automate-TestExpRegPlateInitial.dot");
		a.addFlatExp("a*bc*", a2);
		dotToFile(a2, "dot/automate-TestExpRegPlate.dot");
		System.out.println(a2);
	}
}
