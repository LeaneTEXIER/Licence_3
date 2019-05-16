package tests;

import automata.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class TestAho {

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


	public static void main(String[] args) throws StateException, IOException {
		String words[] = new String[3];
		words[0]= "create";
		words[1]= "at";
		words[2]= "cry";
		AutomatonBuilder dest = new NDAutomaton();
		dest.addNewState("f");
		dotToFile(dest, "dot/automate-TestAhoInitial.dot");
		AutomataUtils.createAhoCorasick(words,dest);
		dotToFile(dest, "dot/automate-TestAho.dot");
		System.out.println(dest);
	}

}
