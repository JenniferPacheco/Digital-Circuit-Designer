
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
//		for (int i = 0; i < this.inputConnections.size(); i++) {
//			LogicGate test = inputConnections.get(i);
//			
//			if (test.output == "") {
//				test.performOperation();
//			}
//			
//			if (test.output == "0") {
//				this.output = "0";
//				break;
//			} else if (test.output == "1") {
//				if (test.output == "") {
//					this.output = "1";
//				}
//			} else {
//				if (this.output == "") {
//					if (i + 1 == inputConnections.size()) {
//						this.output = test.output;
//					} else {
//						this.output = "(" + test.output;
//					}
//				} else if (i + 1 == inputConnections.size()) {
//					this.output += "*" + test.output + ")";
//				} else {
//					this.output += "*" + test.output;
//				}
//			}
//		}
		
		//start off the expression with an open parenthesis
		this.output = "(";
		for (int i = 0; i < inputConnections.size(); i++) {
			LogicGate testGate = inputConnections.get(i);
			
			//check to make sure the testGate has a defined output
			if (testGate.output == "") {
				testGate.performOperation();
			}
			
			if (this.output == "(") {
				this.output += testGate.output;
			} else {
				this.output += "*" + testGate.output;
			}
		}
		//finish the expression with a closed parenthesis
		this.output += ")";
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
