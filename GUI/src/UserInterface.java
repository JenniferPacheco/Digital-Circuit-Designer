import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//class containing necessary info for each gate
//class LogicGate{
//	
//	int numInputs;
//	String gateName;
//	String gateType;
//	String [] inputs;
//	int level;
//	
//};

public class UserInterface implements ActionListener, ItemListener, ListSelectionListener {
	
	//array of logic gates, 13 is max number of gates
	//4 for each of the first 3 levels and 1 for the last level
	LogicGate [] userGates = new LogicGate [13]; 
	
	//initialize counting variables for the number of gates
	int numGates = 0;
	int numAnds = 0;
	int numOrs = 0;
	int numXors = 0;
	int numXnors = 0;
	int numNors = 0;
	int numNots = 0;
	int numNands = 0;
	
	//array to hold the number of gates in each level
	//to ensure that we don't go over the max
	int [] numGatesInLevel = new int[4];

	//menu items
	JMenuItem newFile;
	JMenuItem open;
	JMenuItem save;
	
	JMenuItem input1;
	JMenuItem input2;
	JMenuItem input3;
	JMenuItem zeroInput;
	JMenuItem oneInput;
	
	JMenuItem and2;
	JMenuItem and3;
	JMenuItem and4;
	
	JMenuItem or2;
	JMenuItem or3;
	JMenuItem or4;
	
	JMenuItem nand2;
	JMenuItem nand3;
	JMenuItem nand4;
	
	JMenuItem nor2;
	JMenuItem nor3;
	JMenuItem nor4;
	
	JMenuItem xor2;
	JMenuItem xnor2;
	JMenuItem not;
	
	JMenuItem manual;
	
	//array of JPanels for drawing area
	JPanel[] drawLevels = new JPanel[4];
	
	//JPanel to place the output on
	JPanel output;	
	
	JButton simulate;
	JButton clear;
	JButton connections;
	
	JFrame frame;
	JFrame newMenu;
	
	//items for the create logic gate new menu that comes
	//up whenever one of the gates is chosen
	JList<String> inputList;
	DefaultListModel<String> inputs;
	DefaultComboBoxModel<String> gates;
	JButton showInputs;
	JButton submit;
	JComboBox<String> selectGate;
	
	void CreateUI() {
		
		//initialize 0 gates in each level
		numGatesInLevel[0]=0;
		numGatesInLevel[1]=0;
		numGatesInLevel[2]=0;
		numGatesInLevel[3]=0;
		
		//create the main frame
		frame = new JFrame("Digital Circuit Designer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(1080, 720);
		
		//create the menu bar
		JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(154, 165, 127));
        menuBar.setPreferredSize(new Dimension(1080, 40));
        
        //and gate menu
        JMenu andMenu = new JMenu("AND Gates");
        menuBar.add(andMenu);
        and2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputAND.png"));
        and2.addActionListener(this);
        andMenu.add(and2);
        and3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputAND.png"));
        andMenu.add(and3);
        and3.addActionListener(this);
        and4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputAND.png"));
        and4.addActionListener(this);
        andMenu.add(and4);
        
        //or gate menu
        JMenu orMenu = new JMenu("OR Gates");
        menuBar.add(orMenu);
        or2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputOR.png"));
        or2.addActionListener(this);
        orMenu.add(or2);
        or3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputOR.png"));
        or3.addActionListener(this);
        orMenu.add(or3);
        or4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputOR.png"));
        or4.addActionListener(this);
        orMenu.add(or4);
        
        //nand gate menu
        JMenu nandMenu = new JMenu("NAND Gates");
        menuBar.add(nandMenu);
        nand2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputNAND.png"));
        nand2.addActionListener(this);
        nandMenu.add(nand2);
        nand3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputNAND.png"));
        nand3.addActionListener(this);
        nandMenu.add(nand3);
        nand4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputNAND.png"));
        nand4.addActionListener(this);
        nandMenu.add(nand4);
        
        //nor gate menu
        JMenu norMenu = new JMenu("NOR Gates");
        menuBar.add(norMenu);
        nor2 = new JMenuItem("2 Inputs", new ImageIcon("images/2inputNOR.png"));
        nor2.addActionListener(this);
        norMenu.add(nor2);
        nor3 = new JMenuItem("3 Inputs", new ImageIcon("images/3inputNOR.png"));
        nor3.addActionListener(this);
        norMenu.add(nor3);
        nor4 = new JMenuItem("4 Inputs", new ImageIcon("images/4inputNOR.png"));
        nor4.addActionListener(this);
        norMenu.add(nor4);
        
        //xor gate menu
        JMenu xorMenu = new JMenu("XOR Gates");
        menuBar.add(xorMenu);
        xor2 = new JMenuItem("XOR", new ImageIcon("images/2inputXOR.png"));
        xor2.addActionListener(this);
        xorMenu.add(xor2);
        
        //xnor gate menu
        JMenu xnorMenu = new JMenu("XNOR Gates");
        menuBar.add(xnorMenu);
        xnor2 = new JMenuItem("XNOR", new ImageIcon("images/2inputXNOR.png"));
        xnor2.addActionListener(this);
        xnorMenu.add(xnor2);
        
        //not gate menu
        JMenu notMenu = new JMenu("NOT Gate");
        menuBar.add(notMenu);
        not = new JMenuItem("NOT", new ImageIcon("images/NOT.png"));
        not.addActionListener(this);
        notMenu.add(not);
        
        //help menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        manual = new JMenuItem("User Manual");
        manual.addActionListener(this);
        helpMenu.add(manual);
        
        //add the 4 drawing panels
        for(int i = 0; i<4; i++){
        	//array so that you can index the individual panels while drawing the gates
        	drawLevels[i] = new JPanel();
        	frame.add(drawLevels[i]);
        	drawLevels[i].setBounds(80 + 300*i, 20, 300, 290);
        	
        }
        
        //simulate button
        connections = new JButton("Connect");
        frame.add(connections);
        connections.setBounds(450, 325, 100, 25);
        connections.addActionListener(this);
        
        //simulate button
        simulate = new JButton("Simulate");
        frame.add(simulate);
        simulate.setBounds(575, 325, 100, 25);
        simulate.addActionListener(this);
        
        //clear button
        clear = new JButton("Clear");
        frame.add(clear);
        clear.setBounds(700, 325, 100, 25);
        clear.addActionListener(this);
        
        //output JPanel
        output = new JPanel();
        frame.add(output);
        output.setBounds(80, 365, 1200, 280);
        
        //create a new menu that pops up when you select a gate
        //so that you set inputs and the level
        newMenu = new JFrame("Logic Gate Set Up");
		newMenu.setSize(600, 600);
		
		//JComboBox for choosing the level to draw the gate in
		JLabel pickGate = new JLabel("Pick a gate to select inputs for: ");
		newMenu.add(pickGate);
		pickGate.setBounds(60, 20, 200, 50);
		gates = new DefaultComboBoxModel<String>();
		selectGate = new JComboBox<String>(gates);	
		selectGate.addItemListener(this);
		newMenu.add(selectGate);
		selectGate.setBounds(250, 40, 100, 20);
		
		JLabel pickInputs = new JLabel("Pick Inputs: ");
		newMenu.add(pickInputs);
		pickInputs.setBounds(60, 175, 200, 50);	
	
		//add the first few input options to the JList
		inputs = new DefaultListModel<String>();
		inputs.addElement("0");
		inputs.addElement("1");
		inputs.addElement("x");
		inputs.addElement("y");
		inputs.addElement("z");
		
		//JList to select the inputs
		inputList = new JList<String>(inputs);
		inputList.addListSelectionListener(this);
		
		//allows the user to click on multiple selections instead of needing
		//to press ctrl and click on selections
		inputList.setSelectionModel(new DefaultListSelectionModel() {
		    private int i0 = -1;
		    private int i1 = -1;

		    public void setSelectionInterval(int index0, int index1) {
		        if(i0 == index0 && i1 == index1){
		            if(getValueIsAdjusting()){
		                 setValueIsAdjusting(false);
		                 setSelection(index0, index1);
		            }
		        }else{
		            i0 = index0;
		            i1 = index1;
		            setValueIsAdjusting(false);
		            setSelection(index0, index1);
		        }
		    }
		    private void setSelection(int index0, int index1){
		        if(super.isSelectedIndex(index0)) {
		            super.removeSelectionInterval(index0, index1);
		        }else {
		            super.addSelectionInterval(index0, index1);
		        }
		    }
		});
		
		JScrollPane listScroller = new JScrollPane(inputList);
		listScroller.setBounds(250, 185, 100, 150);
		newMenu.add(listScroller);
		
		showInputs = new JButton("Show Inputs");		
		showInputs.addActionListener(this);
		newMenu.add(showInputs);
		showInputs.setBounds(175, 130, 125, 25);
		
		//submit button to submit the gate creation information
		submit = new JButton("Submit");		
		submit.addActionListener(this);
		newMenu.add(submit);
		submit.setBounds(200, 450, 100, 25);
        
        JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(200, 180));
        
        newMenu.getContentPane().add(yellowLabel);
		
	    frame.setJMenuBar(menuBar);
	    frame.getContentPane().add(yellowLabel);
	    frame.setVisible(true);
	}
	
	public static void main( String[] args ) {
		UserInterface ui = new UserInterface();
		ui.CreateUI();
		MainLogic theMainLogic = new MainLogic();
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object source = arg0.getSource();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();		
		int index = 0;
		
		if(source.equals(showInputs)){
			
			int maxLevel = 0;					
			
			for(int i = 0; i<numGates; i++){
				
				if(userGates[i].gateName == selectGate.getSelectedItem()){
					
					maxLevel = userGates[i].level;
					index = i;
					
				}
				
			}
			
			for(int i = 0; i<numGates; i++){
				
				if(userGates[i].level >= maxLevel && i != index){
					
					inputs.addElement(userGates[i].gateName);
					
				}
				
			}
			
			inputList.setVisible(true);
			
		}
		
		if(source.equals(connections)){
			
			if(numGates > 0 && numGatesInLevel[0]==1)
				createLogicGate();
			else{
				if(numGates == 0)
					JOptionPane.showMessageDialog(frame, "No gates to connect. Add gates.");
				else
					JOptionPane.showMessageDialog(frame, "No gate in output level. Add an output gate to level 1.");
			}
			
		}
		
		//submit button in the create logic gate menu
		if(source.equals(submit)){
			
			boolean input = false;
			
			//loops through to count the number of selections
			int minIndex = inputList.getMinSelectionIndex();
	        int maxIndex = inputList.getMaxSelectionIndex();
	        int chosenCount = 0;
	        for (int i = minIndex; i <= maxIndex; i++) {
	            if (inputList.isSelectedIndex(i)) {
	                chosenCount++;
	            }
	        }
	        
	        //number of input selections = num of gate inputs
	        if(chosenCount == userGates[index].numInputs){
	        	
	        	int j =0;
	        	//add the chosen inputs to the gate's input array
	        	for (int i = minIndex; i <= maxIndex; i++) {
	                if (inputList.isSelectedIndex(i)) {
	                    userGates[index].inputs[j] = inputs.getElementAt(i);
	                    j++;
	                }
	            }
	        	input = true;
	        }
	        
	        //too many inputs were chosen, try again
	        else if(chosenCount > userGates[index].numInputs)	        	
	        	JOptionPane.showMessageDialog(newMenu, "Too many inputs selected.");
	        
	        //too little inputs were chosen, try again
	        else
	        	JOptionPane.showMessageDialog(newMenu, "Too little inputs selected.");
	        
	        //inputs are valid, create connections
	        if(input == true){
	        	
	        	//close the create logic gate menu
	        	newMenu.setVisible(false);
	        	if(inputs.getSize() > 5)
	        		inputs.removeRange(5, inputs.getSize()-1);
	        	
	        	for(int i = 0; i< userGates[index].numInputs; i++)
	        		System.out.println(userGates[index].gateName + " line to " + userGates[index].inputs[i]);
	        	
	        	
	        }
		
		}
		
		//and gate with 2 inputs
		if(source.equals(and2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new ANDGate(2);
			
			//numAnds is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "And" + numAnds;
			userGates[numGates].inputs = new String[2];        
			
			if(pickLevel() && gates.getElementAt(numGates)!=userGates[numGates].gateName){
				
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numAnds++;
				
			}
			
		}
		
		//and gate with 3 inputs
		if(source.equals(and3)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 3;
			userGates[numGates].gateType = "And";
			
			//numAnds is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "And" + numAnds;
			userGates[numGates].inputs = new String[3];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
				
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numAnds++;
				
			}
			
		}
		
		//and gate with 4 inputs
		if(source.equals(and4)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 4;
			userGates[numGates].gateType = "And";
			
			//numAnds is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "And" + numAnds;
			userGates[numGates].inputs = new String[4];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
				
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numAnds++;
				
			}
			
		}
		
		//or gate with 2 inputs
		if(source.equals(or2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new ORGate(2);
			
			//numOrs is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Or" + numOrs;
			userGates[numGates].inputs = new String[2];		
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
				
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numOrs++;
				
			}
			
		}
		
		//or gate with 3 inputs
		if(source.equals(or3)){
			
			//set up values in the new logic gate
			userGates[numGates] = new ORGate(3);
			userGates[numGates].numInputs = 3;
			userGates[numGates].gateType = "Or";
			
			//numOrs is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Or" + numOrs;
			userGates[numGates].inputs = new String[3];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
				
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numOrs++;
				
			}
			
		}
		
		//or gate with 4 inputs
		if(source.equals(or4)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 4;
			userGates[numGates].gateType = "Or";
			
			//numOrs is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Or" + numOrs;
			userGates[numGates].inputs = new String[4];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
				
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numOrs++;
				
			}
			
		}
		
		//nand gate with 2 inputs
		if(source.equals(nand2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 2;
			userGates[numGates].gateType = "Nand";
			
			//numNands is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Nand" + numNands;
			userGates[numGates].inputs = new String[2];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
								
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numNands++;
				
			}
			
		}
		
		//nand gate with 3 inputs
		if(source.equals(nand3)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 3;
			userGates[numGates].gateType = "Aand";
			
			//numNands is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Nand" + numNands;
			userGates[numGates].inputs = new String[3];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
								
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numNands++;
				
			}
			
		}
		
		//nand gate with 4 inputs
		if(source.equals(nand4)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 4;
			userGates[numGates].gateType = "Nand";
			
			//numNands is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Nand" + numNands;
			userGates[numGates].inputs = new String[4];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
								
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numNands++;
				
			}
			
		}
		
		//nor gate with 2 inputs
		if(source.equals(nor2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new NORGate(2);
			
			//numNors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Nor" + numNors;
			userGates[numGates].inputs = new String[2];

			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
								
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numNors++;
				
			}
			
		}
		
		//nor gate with 3 inputs
		if(source.equals(nor3)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 3;
			userGates[numGates].gateType = "Nor";
			
			//numNors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Nor" + numNors;
			userGates[numGates].inputs = new String[3];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
								
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numNors++;
				
			}
			
		}
		
		//nor gate with 4 inputs
		if(source.equals(nor4)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 4;
			userGates[numGates].gateType = "Nor";
			
			//numNors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Nor" + numNors;
			userGates[numGates].inputs = new String[4];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
								
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numNors++;
				
			}
			
		}

		//not gate
		if(source.equals(not)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 1;
			userGates[numGates].gateType = "Not";
			
			//numNots is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Not" + numNots;
			userGates[numGates].inputs = new String[1];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
								
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numNots++;
				
			}
			
		}

		//xnor gate with 2 inputs
		if(source.equals(xnor2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 2;
			userGates[numGates].gateType = "Xnor";
			
			//numXnors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Xnor" + numXnors;
			userGates[numGates].inputs = new String[2];
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
				
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numXnors++;
				
			}
			
		}
		
		//xor gate with 2 inputs
		if(source.equals(xor2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new LogicGate();
			userGates[numGates].numInputs = 2;
			userGates[numGates].gateType = "Xor";
			
			//numXors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "Xor" + numXors;
			userGates[numGates].inputs = new String[2];			
			
			if(pickLevel()&& gates.getElementAt(numGates)!=userGates[numGates].gateName){
								
				gates.addElement(userGates[numGates].gateName);
				numGates++;
				numXors++;
				
			}
			
		}
		
	}    

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
	}
	
	public void createLogicGate(){

		//reset the combobox and list upon opening the new menu
		newMenu.setVisible(true);
		selectGate.setSelectedIndex(0);
		inputList.setVisible(false);
    	inputList.clearSelection();		
    	
    }
	
	public boolean pickLevel(){
		
		String temp = JOptionPane.showInputDialog("Pick a drawing level (a number between 1 - 4):");
		while(Integer.parseInt(temp)>4 || Integer.parseInt(temp)<1){
			
			JOptionPane.showMessageDialog(frame, "Not a valid level. Try again.");
			temp = JOptionPane.showInputDialog("Pick a drawing level (a number between 1 - 4):");
			
		}
		//levels 4-2 can have max of 4 gates each
	    if(Integer.parseInt(temp) <= 4 && Integer.parseInt(temp) >= 2){
	        	
	       //already 4 gates in that level, pick a different level
	        while(numGatesInLevel[Integer.parseInt(temp)]>3){
	        		
	        	JOptionPane.showMessageDialog(frame, "Too many gates in that level. Try again.");
	        	temp = JOptionPane.showInputDialog("Pick a drawing level (a number between 1 - 4):");
	        		
	        }
	        	
	        //there is room in that level for the gate to be added
	        if(numGatesInLevel[Integer.parseInt(temp)]<=3){
	        		
	        	userGates[numGates].level = Integer.parseInt(temp);
	        	numGatesInLevel[userGates[numGates].level-1]++;
	        	return true;
	        		
	        }
	        	
	     }
		 //the last level(1) can only have 1 gate
	     else{
	        	
	        //there is already a gate here, try again
	        while(numGatesInLevel[Integer.parseInt(temp)-1]>0){
	        		
	        	JOptionPane.showMessageDialog(frame, "Too many gates in that level. Try again.");
	        	temp = JOptionPane.showInputDialog("Pick a drawing level (a number between 1 - 4):");
	        		
	        }
	        	
	        //there is room for a gate here
	        if(numGatesInLevel[Integer.parseInt(temp)-1]==0){
	        		
	        	userGates[numGates].level = Integer.parseInt(temp);
	        	numGatesInLevel[userGates[numGates].level-1]++;
	        	return true;
	        		
	        }
	        	
	     }
	    
	    return false;
		
	}
	
}
