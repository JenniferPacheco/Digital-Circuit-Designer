
public class NOTGate extends LogicGate {
	public NOTGate() {
		super(1, "NOT");
	}
	
	@Override
	public void performOperation() {
		//NOT calculation
		//only one input possible, so simply check the input connection
		//and return the opposite of it's output
		LogicGate test = inputConnections.get(0);
		
		if (test.output == "") {
			test.performOperation();
		}
		
		if (test.output == "1") {
			this.output = "0";
		} else if (test.output == "0") {
			this.output = "1";
		} else {
			this.output = "~" + test.output;
		}
	}
	
	@Override
	public void processingState(String state) {
		LogicGate test = inputConnections.get(0);
		
		if (test.stateOutput == -1) {
			test.processingState(state);
		}
		
		if (test.stateOutput == 0) {
			this.stateOutput = 1;
		} else {
			this.stateOutput = 0;
		}
	}
}
