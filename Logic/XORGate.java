
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
		
		
	}
}
