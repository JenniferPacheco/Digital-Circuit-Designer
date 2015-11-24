
public class NANDGate extends LogicGate {
	public NANDGate(int numInputs) {
		super(numInputs, "NAND");
	}
	
	@Override
	public void performOperation() {
		//NAND calculation
		//similar to AND but instead, set the output variable to true
		for (int i = 0; i < this.inputConnections.size(); i++) {
			LogicGate test = inputConnections.get(i);
			
			if (test.output == "") {
				test.performOperation();
			}
			
			if (test.output == "0") {
				this.output = "1";
				break;
			} else if (test.output == "1") {
				if (test.output == "") {
					this.output = "0";
				}
			} else {
				if (this.output == "") {
					if (i + 1 == inputConnections.size()) {
						this.output = "~" + test.output;
					} else {
						this.output = "~(" + test.output;
					}
				} else if (i + 1 == inputConnections.size()) {
					this.output += "*" + test.output + ")";
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
				this.stateOutput = 1;
				break;
			} else {
				this.stateOutput = 0;
			}
		}
	}
}
