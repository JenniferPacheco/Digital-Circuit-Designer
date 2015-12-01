
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
}
