package tests;

import automata.*;
import java.io.*;

public class TestMinimalise{

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


	public static void main(String[] args) throws IOException{
    AutomataUtils a1 = new AutomataUtils();
    AutomatonBuilder a = new NDAutomaton();
    a.addNewState("q0");
    a.addNewState("q1");
    a.addNewState("q2");
    a.addNewState("q3");
    a.addTransition("q0",'a',"q1");
    a.addTransition("q0",'a',"q2");
    a.addTransition("q0",'a',"q3");
    a.addTransition("q0",'b',"q0");
    a.addTransition("q1",'a',"q2");
    a.addTransition("q1",'b',"q0");
    a.addTransition("q1",'b',"q1");
    a.addTransition("q2",'b',"q0");
    a.addTransition("q2",'b',"q2");
    a.addTransition("q3",'a',"q2");
    a.setInitial("q0");
    a.setAccepting("q0");
    a.setAccepting("q1");
    dotToFile(a, "dot/automate-TestMinimaliseInitial.dot");
    AutomatonBuilder dest = new NDAutomaton();
    a1.minimalise(a, dest);
    dotToFile(dest, "dot/automate-TestMinimalise.dot");
    System.out.println(dest);
	}
}
