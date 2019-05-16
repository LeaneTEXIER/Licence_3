package analyse;

import java.io.IOException;
import java.io.Reader;

import lexical.Constant;
import lexical.Ident;
import lexical.LexicalException;
import lexical.TokenType;
import reduction.BooleanConstant;
import reduction.BooleanExpression;

public class BooleanExpReductor extends AbstractParser<String> {

	public BooleanExpReductor(Reader in) throws IOException, LexicalException {
		super(in);
	}

	@Override
	protected String axiom() throws SyntaxException, ParserException, IOException, LexicalException {
		return E().toString();
	}

	private BooleanExpression E() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
		case CONSTANT:
		case IDENT:
		case OPEN_BRACKET:
		case NOT:
			BooleanExpression e1 = T();
			BooleanExpression e2 = Ep();
			return e1.rightOr(e2);
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private BooleanExpression Ep() throws ParserException, IOException, LexicalException, SyntaxException {
		switch (current.getType()) {
		case OR:
			next();
			BooleanExpression e1 = T();
			BooleanExpression e2 = Ep();
			return e1.rightOr(e2);
		case CLOSE_BRACKET:
		case EOD:
			return new BooleanConstant(false); // neutral element for OR
		default:
			throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private BooleanExpression T() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
			case CONSTANT:
			case IDENT:
			case OPEN_BRACKET:
			case NOT:
				BooleanExpression e1 = F();
				BooleanExpression e2 = Tp();
				return e1.rightAnd(e2);
			default:
				throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private BooleanExpression Tp() throws ParserException, IOException, LexicalException, SyntaxException {
		switch (current.getType()) {
			case AND:
				next();
				BooleanExpression e1 = F();
				BooleanExpression e2 = Tp();
				return e1.rightAnd(e2);
			case OR:
			case CLOSE_BRACKET:
			case EOD:
				return new BooleanConstant(true);
			default:
				throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}

	private BooleanExpression F() throws SyntaxException, ParserException, IOException, LexicalException {
		switch (current.getType()) {
			case CONSTANT: {
				Constant c = (Constant) current;
				next();
				return new BooleanConstant(c.getValue());
			}
			case IDENT:
				Ident i = (Ident) current;
				next();
				return new BooleanExpression(i.toString());
			case OPEN_BRACKET:
				next();
				BooleanExpression e1 = E();
				next();
				return e1;
			case NOT:
				next();
				BooleanExpression e2 = F();
				return e2.not();
			default:
				throw new SyntaxException(ErrorType.NO_RULE, current);
		}
	}
}
