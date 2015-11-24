
public class varGate extends LogicGate {
	public varGate(String output) {
		super(0, "");
		this.output = output; //could be 0, 1, or a variable (x, y, z, w)
	}
	
	@Override
	public void processingState(String state) {
		if (output == "0") {
			stateOutput = 0;
		} else if (output == "1") {
			stateOutput = 1;
		} else {
			if (output == "x") {
				String firstChar = "" + state.charAt(0);
				stateOutput = Integer.parseInt(firstChar);
			} else if (output == "y") {
				String secondChar = "" + state.charAt(1);
				stateOutput = Integer.parseInt(secondChar);
			} else if (output == "z") {
				String thirdChar = "" + state.charAt(2);
				stateOutput = Integer.parseInt(thirdChar);
			} else {
				String fourthChar = "" + state.charAt(3);
				stateOutput = Integer.parseInt(fourthChar);
			}
		}
	}
}
