
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
					if (i + 1 == inputConnections.size()) {
						this.output = test.output;
					} else {
						this.output = "(" + test.output;
					}
				} else if (i + 1 == inputConnections.size()) {
					this.output += "+" + test.output + ")";
				} else {
					this.output += "+" + test.output;
				}
			}
		}
	}
}
