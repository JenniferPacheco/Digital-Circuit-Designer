
public class ANDGate extends LogicGate {
	public ANDGate(int numInputs) {
		super(numInputs, "AND");
	}
	
	@Override
	public void performOperation() {
		//AND calculation
		//only one way the output can be true, so simply run through the
		//inputConnections and if there is a false in any of them, set the output
		//variable to false
		for (int i = 0; i < this.inputConnections.size(); i++) {
			LogicGate test = inputConnections.get(i);
			
			if (test.output == "") {
				test.performOperation();
			}
			
			if (test.output == "0") {
				this.output = "0";
				break;
			} else if (test.output == "1") {
				if (test.output == "") {
					this.output = "1";
				}
			} else {
				if (this.output == "") {
					this.output = test.output;
				} else {
					this.output += "*" + test.output;
				}
			}
		}
	}
	
	@Override
	public void processingState(String state) {
		for (LogicGate test : inputConnections) {
			if (test.stateOutput == -1) {
				test.processingState(state);
			}
			
			if (test.stateOutput == 0) {
				this.stateOutput = 0;
				break;
			} else {
				this.stateOutput = 1;
			}
		}
	}
}
