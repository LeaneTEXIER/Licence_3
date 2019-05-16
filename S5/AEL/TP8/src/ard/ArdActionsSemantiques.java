package ard;

import java.io.Reader;

/**
 * Actions Semantiques (Exercice 1 partie 2)
 *
 * @author Léane TEXIER
 *
 */
public class ArdActionsSemantiques extends Ard {

	public ArdActionsSemantiques(Reader in) {
		super(in);
	}

	/** Retourne le mot developpe correspondant a cette expression
	 * @return mot developpe correspondant a cette expression
	 */
	private String S() throws SyntaxException, ParserException {
		switch (current) {
			case 'a':
			case 'b':
			case 'c':
			case '(':
				// S -> ERS
				String er = E();
				int r = R();
				String e="";
				for (int i=0; i<r; i++){
					e += er;
				}
				e+= S();
				return (e);
			case ')':
			case END_MARKER:
				// S -> epsilon
				return "";
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}

	/** Retourne le mot developpe correspondant a cette portion d'expression
	 * @return mot developpe correspondant a cette portion d'expression
	 */
	private String E() throws SyntaxException, ParserException {
		switch (current) {
			case 'a':
			case 'b':
			case 'c':
				// E -> L
				return L();
			case '(':
				// E -> (S)
				eat('(');
				String pe = S();
				eat(')');
				return pe;
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}

	/** Retourne le nombre de repetition represente
	 * @return nombre de repetition
	 */
	private int R() throws SyntaxException, ParserException {
		switch (current) {
			case 'a':
			case 'b':
			case 'c':
			case '(':
			case ')':
			case END_MARKER:
				// R -> epsilon
				return 1;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				// R -> C
				return C();
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}

	/** Retourne une chaine contenant le caractere
	 * @return chaine contenant le caractere
	 */
	private String L() throws SyntaxException, ParserException {
		switch (current) {
			case 'a':
				// L -> a
				eat('a');
				return "a";
			case 'b':
				// L -> b
				eat('b');
				return "b";
			case 'c':
				// L -> c
				eat('c');
				return "c";
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}

	/** Retourne la valeur du caractere (chiffre decimal)
	 * @return valeur du caractere
	 */
	private int C() throws SyntaxException, ParserException {
		switch (current) {
			case '0':
				// C -> 0
				eat('0');
				return 0;
			case '1':
				// C -> 1
				eat('1');
				return 1;
			case '2':
				// C -> 2
				eat('2');
				return 2;
			case '3':
				// C -> 3
				eat('3');
				return 3;
			case '4':
				// C -> 4
				eat('4');
				return 4;
			case '5':
				// C -> 5
				eat('5');
				return 5;
			case '6':
				// C -> 6
				eat('6');
				return 6;
			case '7':
				// C -> 7
				eat('7');
				return 7;
			case '8':
				// C -> 8
				eat('8');
				return 8;
			case '9':
				// C -> 9
				eat('9');
				return 9;
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}

	@Override
	protected void axiom() throws SyntaxException, ParserException {
	  String rep = S();
	  System.out.println(rep);
	}

}
