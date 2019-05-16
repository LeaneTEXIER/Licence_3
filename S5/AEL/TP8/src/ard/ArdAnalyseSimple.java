package ard;

import java.io.Reader;

/**
 * Implémentation d'un analyseur récursif descendant pour la grammaire
 * S -> ERS | epsilon
 * E -> L   | (S)
 * R -> C   | epsilon
 * L -> a | b | c
 * C -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 *
 * @author Léane TEXIER
 *
 */
public class ArdAnalyseSimple extends Ard {

	public ArdAnalyseSimple(Reader in) {
		super(in);
	}

	private void S() throws SyntaxException, ParserException {
		switch (current) {
			case 'a':
			case 'b':
			case 'c':
			case '(':
				// S -> ERS
				E();
				R();
				S();
				break;
			case ')':
			case END_MARKER:
				// S -> epsilon
				break;
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}

	private void E() throws SyntaxException, ParserException {
		switch (current) {
			case 'a':
			case 'b':
			case 'c':
				// E -> L
				L();
				break;
			case '(':
				// E -> (S)
				eat('(');
				S();
				eat(')');
				break;
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}

	private void R() throws SyntaxException, ParserException {
		switch (current) {
			case 'a':
			case 'b':
			case 'c':
			case '(':
			case ')':
			case END_MARKER:
				// R -> epsilon
				break;
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
				C();
				break;
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}
	
	private void L() throws SyntaxException, ParserException {
		switch (current) {
			case 'a':
				// L -> a
				eat('a');
				break;
			case 'b':
				// L -> b
				eat('b');
				break;
			case 'c': 
				// L -> c
				eat('c');
				break;
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}
	
	private void C() throws SyntaxException, ParserException {
		switch (current) {
			case '0':
				// C -> 0
				eat('0');
				break;
			case '1':
				// C -> 1
				eat('1');
				break;
			case '2':
				// C -> 2
				eat('2');
				break;
			case '3':
				// C -> 3
				eat('3');
				break;
			case '4':
				// C -> 4
				eat('4');
				break;
			case '5':
				// C -> 5
				eat('5');
				break;
			case '6':
				// C -> 6
				eat('6');
				break;
			case '7':
				// C -> 7
				eat('7');
				break;
			case '8':
				// C -> 8
				eat('8');
				break;
			case '9':
				// C -> 9
				eat('9');
				break;
			default:
				throw new SyntaxException(ErrorType.NO_RULE,current);
		}
	}

	@Override
	protected void axiom() throws SyntaxException, ParserException {
	  S();
	}


}
