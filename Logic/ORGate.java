
public class ORGate extends LogicGate{
	public ORGate(int numInputs) {
		super(numInputs, "OR");
	}
	
	@Override
	public void performOperation() {
		//OR calculation
		//Only one case where the output is false, so simply run through
		//the inputConnections and if there is a true, set the output variable
		//to true
		for (int i = 0; i < this.inputConnections.size(); i++) {
			LogicGate test = inputConnections.get(i);
			
			if (test.output == "") {
				test.performOperation();
			}
			
			if (test.output == "0") {
				if (test.output == "") {
					this.output = "0";
				}
			} else if (test.output == "1") {
				this.output = "1";
				break;
			} else {
				if (this.output == "") {
					this.output = test.output;
				} else {
					this.output += " + " + test.output;
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
			
			if (test.stateOutput == 1) {
				this.stateOutput = 1;
				break;
			} else {
				this.stateOutput = 0;
			}
		}
	}
}
