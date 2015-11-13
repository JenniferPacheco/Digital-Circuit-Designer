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
class logicGate{
	
	int numInputs;
	String gateName;
	String gateType;
	String [] inputs;
	int level;
	
};

public class UserInterface implements ActionListener, ItemListener, ListSelectionListener {
	
	//array of logic gates, 13 is max number of gates
	//4 for each of the first 3 levels and 1 for the last level
	logicGate [] userGates = new logicGate [13]; 
	
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
	
	JFrame frame;
	JFrame newMenu;
	
	//items for the create logic gate new menu that comes
	//up whenever one of the gates is chosen
	JList<String> inputList;
	DefaultListModel<String> inputs;
	JButton submit;
	JComboBox drawLevel;
	
	void CreateUI() {
		
		//initialize 0 gates in each level
		numGatesInLevel[0]= 0;
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
        
        //file menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        newFile = new JMenuItem("New");
        newFile.addActionListener(this);
        fileMenu.add(newFile);
        open = new JMenuItem("Open...");
        fileMenu.add(open);
        open.addActionListener(this);
        save = new JMenuItem("Save");
        save.addActionListener(this);
        fileMenu.add(save);
        
        //inputOutput menu
        JMenu inputOutputMenu = new JMenu("Inputs");
        menuBar.add(inputOutputMenu);
        oneInput = new JMenuItem("1");
        oneInput.addActionListener(this);
        inputOutputMenu.add(oneInput);
        zeroInput = new JMenuItem("0");
        zeroInput.addActionListener(this);
        inputOutputMenu.add(zeroInput);
        JMenu addInputMenu = new JMenu("Add Variables");
        inputOutputMenu.add(addInputMenu);
        input1 = new JMenuItem("x");
        input1.addActionListener(this);
        addInputMenu.add(input1);
        input2 = new JMenuItem("y");
        addInputMenu.add(input2);
        input2.addActionListener(this);
        input3 = new JMenuItem("z");
        input3.addActionListener(this);
        addInputMenu.add(input3);
        
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
        simulate = new JButton("Simulate");
        frame.add(simulate);
        simulate.setBounds(535, 325, 100, 25);
        simulate.addActionListener(this);
        
        //clear button
        clear = new JButton("Clear");
        frame.add(clear);
        clear.setBounds(685, 325, 100, 25);
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
		String [] levels = {"1", "2", "3", "4"};
		JLabel pickLevel = new JLabel("Pick Draw Level: ");
		newMenu.add(pickLevel);
		pickLevel.setBounds(100, 20, 200, 50);		
		drawLevel = new JComboBox(levels);	
		drawLevel.addItemListener(this);
		newMenu.add(drawLevel);
		drawLevel.setBounds(250, 40, 50, 20);
		
		JLabel pickInputs = new JLabel("Pick Inputs: ");
		newMenu.add(pickInputs);
		pickInputs.setBounds(100, 175, 200, 50);	
	
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
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		Object source = arg0.getSource();
		
		//detect when the user changes their level selection and update the gate accordingly
		if(source.equals(drawLevel))
			userGates[numGates].level = drawLevel.getSelectedIndex()+1;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		
		//submit button in the create logic gate menu
		if(source.equals(submit)){
			
			boolean level = false;
			boolean inputs = false;
			
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
	        if(chosenCount == userGates[numGates].numInputs){
	        	
	        	int j =0;
	        	//add the chosen inputs to the gate's input array
	        	for (int i = minIndex; i <= maxIndex; i++) {
	                if (inputList.isSelectedIndex(i)) {
	                    userGates[numGates].inputs[j] = inputList.getSelectedValue();
	                    j++;
	                }
	            }
	        	inputs = true;
	        }
	        
	        //too many inputs were chosen, try again
	        else if(chosenCount > userGates[numGates].numInputs)	        	
	        	JOptionPane.showMessageDialog(newMenu, "Too many inputs selected.");
	        
	        //too little inputs were chosen, try again
	        else
	        	JOptionPane.showMessageDialog(newMenu, "Too little inputs selected.");
	        
	        //levels 1-3 can have max of 4 gates each
	        if(userGates[numGates].level < 4){
	        	
	        	//already 4 gates in that level, pick a different level
	        	if(numGatesInLevel[userGates[numGates].level-1]>4){
	        		
	        		//reset level to 0
	        		userGates[numGates].level = 0;
	        		JOptionPane.showMessageDialog(newMenu, "Too many gates in that level.");
	        		
	        	}
	        	
	        	//there is room in that level for the gate to be added
	        	else{
	        		
	        		numGatesInLevel[userGates[numGates].level-1]++;
	        		level = true;
	        		
	        	}
	        	
	        }
	        
	        //the last level(4) can only have 1 gate
	        else{
	        	
	        	//there is already a gate here, try again
	        	if(numGatesInLevel[userGates[numGates].level-1]>1){
	        		
	        		//reset level to 0
	        		userGates[numGates].level = 0;
	        		JOptionPane.showMessageDialog(newMenu, "Too many gates in that level.");
	        		
	        	}
	        	
	        	//there is room for a gate here
	        	else{
	        		
	        		numGatesInLevel[userGates[numGates].level-1]++;
	        		level = true;
	        		
	        	}
	        	
	        }
	        
	        //both the level and inputs are valid, create the gate
	        if(level == true && inputs == true){
	        	
	        	//close the create logic gate menu
	        	newMenu.setVisible(false);  
	        	
	        	//add the number of the gate type for name purposes
	        	if(userGates[numGates].gateType == "and")
	        		numAnds++;
	        	else if(userGates[numGates].gateType == "or")
	        		numOrs++;
	        	else if(userGates[numGates].gateType == "nor")
	        		numNors++;
	        	else if(userGates[numGates].gateType == "xor")
	        		numXors++;
	        	else if(userGates[numGates].gateType == "xnor")
	        		numXnors++;
	        	else if(userGates[numGates].gateType == "not")
	        		numNots++;
	        	else if(userGates[numGates].gateType == "nand")
	        		numNands++;
	        	
	        	//a gate was successfully created so add to the total number of gates
	        	numGates++;
	        	
	        }
		
		}
		
		//and gate with 2 inputs
		if(source.equals(and2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 2;
			userGates[numGates].gateType = "and";
			
			//numAnds is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "and" + numAnds;
			userGates[numGates].inputs = new String[2];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();		 
			
		}
		
		//and gate with 3 inputs
		if(source.equals(and3)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 3;
			userGates[numGates].gateType = "and";
			
			//numAnds is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "and" + numAnds;
			userGates[numGates].inputs = new String[3];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();		 
			
		}
		
		//and gate with 4 inputs
		if(source.equals(and4)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 4;
			userGates[numGates].gateType = "and";
			
			//numAnds is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "and" + numAnds;
			userGates[numGates].inputs = new String[4];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();		 
			
		}
		
		//or gate with 2 inputs
		if(source.equals(or2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 2;
			userGates[numGates].gateType = "or";
			
			//numOrs is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "or" + numOrs;
			userGates[numGates].inputs = new String[2];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();			 
			
		}
		
		//or gate with 3 inputs
		if(source.equals(or3)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 3;
			userGates[numGates].gateType = "or";
			
			//numOrs is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "or" + numOrs;
			userGates[numGates].inputs = new String[3];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();		 
			
		}
		
		//or gate with 4 inputs
		if(source.equals(or4)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 4;
			userGates[numGates].gateType = "or";
			
			//numOrs is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "or" + numOrs;
			userGates[numGates].inputs = new String[4];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();			 
			
		}
		
		//nand gate with 2 inputs
		if(source.equals(nand2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 2;
			userGates[numGates].gateType = "nand";
			
			//numNands is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "nand" + numNands;
			userGates[numGates].inputs = new String[2];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();			 
			
		}
		
		//nand gate with 3 inputs
		if(source.equals(nand3)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 3;
			userGates[numGates].gateType = "nand";
			
			//numNands is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "nand" + numNands;
			userGates[numGates].inputs = new String[3];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();			 
			
		}
		
		//nand gate with 4 inputs
		if(source.equals(nand4)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 4;
			userGates[numGates].gateType = "nand";
			
			//numNands is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "nand" + numNands;
			userGates[numGates].inputs = new String[4];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();			 
			
		}
		
		//nor gate with 2 inputs
		if(source.equals(nor2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 2;
			userGates[numGates].gateType = "nor";
			
			//numNors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "nor" + numNors;
			userGates[numGates].inputs = new String[2];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();			 
			
		}
		
		//nor gate with 3 inputs
		if(source.equals(nor3)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 3;
			userGates[numGates].gateType = "nor";
			
			//numNors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "nor" + numNors;
			userGates[numGates].inputs = new String[3];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();		 
			
		}
		
		//nor gate with 4 inputs
		if(source.equals(nor4)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 4;
			userGates[numGates].gateType = "nor";
			
			//numNors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "nor" + numNors;
			userGates[numGates].inputs = new String[4];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();
			
		}

		//not gate
		if(source.equals(not)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 1;
			userGates[numGates].gateType = "not";
			
			//numNots is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "not" + numNots;
			userGates[numGates].inputs = new String[1];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();
			
		}

		//xnor gate with 2 inputs
		if(source.equals(xnor2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 2;
			userGates[numGates].gateType = "xnor";
			
			//numXnors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "xnor" + numXnors;
			userGates[numGates].inputs = new String[2];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();
			
		}
		
		//xor gate with 2 inputs
		if(source.equals(xor2)){
			
			//set up values in the new logic gate
			userGates[numGates] = new logicGate();
			userGates[numGates].numInputs = 2;
			userGates[numGates].gateType = "xor";
			
			//numXors is used to name the different ands as we add them to the JList
			//maybe we can label each with their name as we draw them to make the 
			//distinction for the user to use while picking inputs
			userGates[numGates].gateName = "xor" + numXors;
			userGates[numGates].inputs = new String[2];
			
			//if there is a previous gate that has not been added to the JList do so
			if(numGates>0 && inputs.contains(userGates[numGates-1].gateName) != true)
				inputs.addElement(userGates[numGates-1].gateName);
			createLogicGate();
			
		}
		
	}    

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
	}
	
	public void createLogicGate(){

		//reset the combobox and list upon opening the new menu
		drawLevel.setSelectedIndex(0); 
    	inputList.clearSelection();
		userGates[numGates].level = drawLevel.getSelectedIndex()+1;
		newMenu.setVisible(true);
    	
    }
	
}
