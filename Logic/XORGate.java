
public class XORGate extends LogicGate{
	public XORGate(int numInputs) {
		super(numInputs, "XOR");
	}
	
	@Override
	public void performOperation() {
		//XOR calculation
		//Since implementation will be like an odd-parity checker, simply run
		//through the inputConnections and count up how many true outputs there are.
		//If there are an odd # of trues, set the output variable to true.
		//If there are an even # of trues, set the output variable to false.
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
					variables += " ^ " + test.output;
				}
			}
		}
		
		if (numOnes % 2 == 0) {
			this.output = "(" + variables + " ^ 0)";
		} else {
			this.output = "(" + variables + " ^ 1)";
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
			this.stateOutput = 0;
		} else {
			this.stateOutput = 1;
		}
	}
}
