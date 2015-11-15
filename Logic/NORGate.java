
public class NORGate extends LogicGate {
	public NORGate(int numInputs) {
		super(numInputs, "NOR");
	}
	
	@Override
	public void performOperation() {
		//NOR calculation
		//similar to OR but instead, set the output variable to false
		for (int i = 0; i < this.inputConnections.size(); i++) {
			LogicGate test = inputConnections.get(i);
			
			if (test.output == "") {
				test.performOperation();
			}
			
			if (test.output == "0") {
				if (test.output == "") {
					this.output = "1";
				}
			} else if (test.output == "1") {
				this.output = "0";
				break;
			} else {
				if (this.output == "") {
					this.output = "~(" + test.output;
				} else if (i + 1 == inputConnections.size()) {
					this.output += " + " + test.output + ")";
				} else {
					this.output += " + " + test.output;
				}
			}
		}
	}
}
