package reduction;

public class BooleanExpression {
	protected String image;

	public String getImage() {
		return image;
	}

	public BooleanExpression(String image) {
		this.image = image;
	}

	public String toString() {
		return image;
	}

	/**
	 *
	 * @return right composition by AND
	 */
	public BooleanExpression rightAnd(BooleanExpression other) {
		if(other.getImage()==null){
			return this;
		}
		if(other.getImage().equals("false") || this.getImage().equals("false")){
			return new BooleanExpression("false");
		}
		else if (other.getImage().equals("true")){
			return this;
		}
		else if (this.getImage().equals("true")){
			return other;
		}
		else{
			return new BooleanExpression(this.getImage()+" and "+other.getImage());
		}
	}

	/**
	 *
	 * @return right composition by OR
	 */
	public BooleanExpression rightOr(BooleanExpression other) {
		if(other.getImage()==null){
			return this;
		}
		else if(other.getImage().equals("true") || this.getImage().equals("true")){
			return new BooleanExpression("true");
		}
		else if (this.getImage().equals("false")){
			return other;
		}
		else if (other.getImage().equals("false")){
			return this;
		}
		else{
			return new BooleanExpression(this.getImage()+" or "+other.getImage());
		}
	}

	/**
	 *
	 * @return negation of this expression
	 */
	public BooleanExpression not() {
		if (this.getImage().equals("false")){
			return new BooleanExpression("true");
		}
		else if(this.getImage().equals("true")){
			return new BooleanExpression("false");
		}
		else{
			return new BooleanExpression("not "+this.getImage());
		}
	}

	/**
	 *
	 * @return wrap expression with brackets
	 */
	public BooleanExpression wrap() {
		String s = this.getImage().trim();
		s.substring(1,s.length()-2).trim();
		return new BooleanExpression(s);
	}
}
