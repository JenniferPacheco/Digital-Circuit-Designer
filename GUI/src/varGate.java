
public class varGate extends LogicGate {
	public varGate(String output) {
		super(0, "");
		this.output = output; //could be 0, 1, or a variable (x, y, z, w)
		this.gateName = output;
	}
}
