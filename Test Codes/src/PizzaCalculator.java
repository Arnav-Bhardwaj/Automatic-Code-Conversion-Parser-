import javax.swing.*;
import javax.swing.border.*; 
import java.awt.*;

public class PizzaCalculator {
		
		//create global variables better for the event handling programming 
		static JRadioButton rbSmall;
		static JRadioButton rbMedium; 
		static JRadioButton rbLarge;
		
		static JRadioButton rbRegular;
		static JRadioButton rbWholeWheat;
		static JRadioButton rbCheeseStuffed;
		static JRadioButton rbHotDogStuffed;
		
		static JButton btCalculate;
		static JButton btExit;
		
		static JCheckBox cbPepperoni;
		static JCheckBox cbGreenPepper;
		static JCheckBox cbGreenOlive;
		static JCheckBox cbExtraCheese;
		static JCheckBox cbMushroom ;
		static JCheckBox cbRedPepper;
		static JCheckBox cbBlackOlive ;
		static JCheckBox cbHam;
		static JCheckBox cbSausage; 
		static JCheckBox cbOnion;
		static JCheckBox cbHotPepper;
		static JCheckBox cbPineapple;
	
	public static void main(String[] args) {

		JLabel title = new JLabel("Welcome to Algoma's Pizzareia"); 
		//title.setForeground(Color.BLUE); 
		
		//title.setFont(new Font("Arial", Font.BOLD, 20));
		JFrame frame = new JFrame();

		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		// to make the layout visible you need to add it to the frame
		frame.add(title,BorderLayout.NORTH); 

		// create a new gridePane
		//--------------------------------------------------------------------
		GridLayout g1 = new GridLayout(3,1);
		JPanel p1 = new JPanel(g1);
		
		TitledBorder t1 = new TitledBorder("Size");
		
		//title.setFont(new Font("Arial", Font.BOLD, 20));
		
		// to create the panels border and the title 
		p1.setBorder(t1); 
		
		// this is made so that you can use the name of the constructor to make button groups. 
		rbSmall = new JRadioButton("Small");
		rbMedium = new JRadioButton("Medium");
		rbLarge = new JRadioButton("Large");
		
		//create the button group for the fact it only choices one option at a time.
		ButtonGroup group = new ButtonGroup();

		group.add(rbSmall);
		group.add(rbMedium);
		group.add(rbLarge);

		// adds the button to the panel
		p1.add(rbSmall); 
		p1.add(rbMedium);
		p1.add(rbLarge);

		frame.add(p1,BorderLayout.WEST);

		// create a new gridePane
		//--------------------------------------------------------------------
		GridLayout g2 = new GridLayout(4,1);
		JPanel p2 = new JPanel(g2);
		
		TitledBorder t2 = new TitledBorder("Dough");
		
		// type what need to be presented inside the brackets
		p2.setBorder(t2);

		// One way to convert this is do this by creating all the objects first then add them to the appropriate panel  
		rbRegular = new JRadioButton("Regular");
		rbWholeWheat = new JRadioButton("Whole Wheat");
		rbCheeseStuffed = new JRadioButton("Cheese Stuffed");
		rbHotDogStuffed = new JRadioButton("HotDog Stuffed");

		p2.add(rbRegular);
		p2.add(rbWholeWheat);
		p2.add(rbCheeseStuffed);
		p2.add(rbHotDogStuffed);
		
		// button group them so only one option can be selected out of the few provided to the user
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(rbRegular);
		group2.add(rbWholeWheat);
		group2.add(rbCheeseStuffed);
		group2.add(rbHotDogStuffed);

		frame.add(p2,BorderLayout.CENTER);
		
		// create a new gridePane
		//--------------------------------------------------------------------		
		GridLayout g3 = new GridLayout(4,3);
		JPanel p3 = new JPanel(g3);
		
		TitledBorder t3 = new TitledBorder("Toppings");
		p3.setBorder(t3);

		// you are not creating a new variable since its just being added to the panel 
		// also demonstrating that you can convert even if you make object first then add to panel the algorithm will work

		cbPepperoni = new JCheckBox("Peperoni");
		p3.add(cbPepperoni);
		
		cbGreenPepper = new JCheckBox("Green Peeper");
		p3.add(cbGreenPepper); // need to add the JCheckbox to the panel
		
		cbGreenOlive = new JCheckBox("Green Olive");
		p3.add(cbGreenOlive);
		
		cbExtraCheese = new JCheckBox("Extra Cheese");
		p3.add(cbExtraCheese);
		
		cbMushroom = new JCheckBox("Mushrooms");
		p3.add(cbMushroom);
		
		cbRedPepper = new JCheckBox("Red Pepper");
		p3.add(cbRedPepper);
		
		cbBlackOlive = new JCheckBox("Black Olive");
		p3.add(cbBlackOlive);
		
		cbHam = new JCheckBox("Ham");
		p3.add(cbHam);
		
		cbSausage =new JCheckBox("Sausage");
		p3.add(cbSausage);
		
		cbOnion = new JCheckBox("Onion");
		p3.add(cbOnion);
		
		cbHotPepper = new JCheckBox("Hot Pepper");
		p3.add(cbHotPepper);
		
		cbPineapple = new JCheckBox("Pineapple");
		p3.add(cbPineapple);
		
		// adding it to the frame
		frame.add(p3,BorderLayout.EAST);
		
		// create a new panel
		//--------------------------------------------------------------------
		JPanel p4 = new JPanel();
		
		
		btCalculate = new JButton("Calculate");
		
		btExit = new JButton("Exit");
		
		p4.add(btExit);

		frame.add(p4,BorderLayout.SOUTH);
	
		frame.setTitle("Pizza Calculator Tool");
		frame.setSize(650,250); // divide into set vertical and set horizontal
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}