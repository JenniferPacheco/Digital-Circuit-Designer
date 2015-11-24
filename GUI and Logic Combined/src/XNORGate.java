
public class XNORGate extends LogicGate {
	public XNORGate(int numInputs) {
		super(numInputs, "XNOR");
	}
	
	@Override
	public void performOperation() {
		//XNOR calculation
		//Similar to XOR but instead this gate acts as an even-parity checker
		//If there are an odd # of trues, set the output to false
		//If there are an even # of trues, set the output to true
		int numOnes = 0;
		String variables = "";
		
		for (int i = 0; i < inputConnections.size(); i++) {
			LogicGate test = inputConnections.get(i);
			
			if (test.output == "") {
				test.performOperation();
			}
			
			if (test.output == "1") {
				numOnes++;
			} else {
				if (variables == "") {
					variables = test.output;
				} else {
					variables += "^" + test.output;
				}
			}
		}
		
		if (numOnes % 2 == 0) {
			this.output = "~(" + variables + "^1)";
		} else {
			this.output = "~(" + variables + "^0)";
		}
	}
	
	@Override
	public void processingState(String state) {
		int numOnes = 0;
		
		for (LogicGate test : inputConnections) {
			if (test.stateOutput == -1) {
				test.processingState(state);
			}
			
			if (test.stateOutput == 1) {
				numOnes++;
			}
		}
		
		if (numOnes % 2 == 0) {
			this.stateOutput = 1;
		} else {
			this.stateOutput = 0;
		}
	}
}
