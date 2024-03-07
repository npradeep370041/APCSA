public class Identifier {
	
	private String name;
	private double value;
	
	public Identifier() {
		name = "";
		value = 0;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setValue(double d) {
		value = d;
	}
	
	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;
	}
}
