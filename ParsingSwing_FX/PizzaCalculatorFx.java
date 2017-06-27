import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx .scene. *;
import javafx .scene. border *;
import java awt *;
public class PizzaApplicationFx  extends Application {
	 //create global variables better for the event handling programming 
 	 RadioButton rbSmall;
	RadioButton rbMedium;
	RadioButton rbLarge;
	RadioButton rbRegular;
	RadioButton rbWholeWheat;
	RadioButton rbCheeseStuffed;
	RadioButton rbHotDogStuffed;
	Button btCalculate;
	Button btExit;
	CheckBox cbPepperoni;
	CheckBox cbGreenPepper;
	CheckBox cbGreenOlive;
	CheckBox cbExtraCheese;
	CheckBox cbMushroom ;
	CheckBox cbRedPepper;
	CheckBox cbBlackOlive ;
	CheckBox cbHam;
	CheckBox cbSausage;
	CheckBox cbOnion;
	CheckBox cbHotPepper;
	CheckBox cbPineapple;
	public void main(String[] args) {
 		launch(args); 
 
	}
    	public BorderPane drawPane() {

		BorderPane bPane = new BorderPane();
		 Label title = new Label("Welcome to Algoma's Pizzareia");
		//title.setForeground(Color.BLUE); 
 	 	 //title.setFont(new Font("Arial", Font.BOLD, 20));
 	 	 Scene frame = new Scene(new StackPane());
		borderPane.setAlignment(title,Pos. CENTER);
		// to make the layout visible you need to add it to the frame
 	 	 bPane.setTop(title);
		// create a new gridePane
 	 	 //--------------------------------------------------------------------
 	 	 GridLayout g1 = new GridLayout(3,1);
		JPanel p1 = new JPanel(g1);
		Text t1 = new Text("Size");
		//title.setFont(new Font("Arial", Font.BOLD, 20));
 	 	 // to create the panels border and the title 
 	 	 p1 setBorder(t1);
		// this is made so that you can use the name of the constructor to make button groups. 
 	 	 rbSmall = new RadioButton("Small");
		rbMedium = new RadioButton("Medium");
		rbLarge = new RadioButton("Large");
		// adds the button to the panel
 	 	 p1 setToggleGroup(rbSmall);
		p1 setToggleGroup(rbMedium);
		p1 setToggleGroup(rbLarge);
		//create the button group for the fact it only choices one option at a time.
 	 	 ToggleGroup = new ToggleGroup();
		rbSmall.setToggleGroup(group);
		setToggleGroup(rbSmall);
		rbMedium.setToggleGroup(group);
		setToggleGroup(rbMedium);
		rbLarge.setToggleGroup(group);
		setToggleGroup(rbLarge);
		bPane.setLeft(p1);
		// create a new gridePane
 	 	 //--------------------------------------------------------------------
 	 	 GridLayout g2 = new GridLayout(4,1);
		JPanel p2 = new JPanel(g2);
		Text t2 = new Text("Dough");
		// type what need to be presented inside the brackets
 	 	 p2 setBorder(t2);
		// One way to convert this is do this by creating all the objects first then add them to the appropriate panel  
 	 	 rbRegular = new RadioButton("Regular");
		rbWholeWheat = new RadioButton("Whole Wheat");
		rbCheeseStuffed = new RadioButton("Cheese Stuffed");
		rbHotDogStuffed = new RadioButton("HotDog Stuffed");
		p2 setToggleGroup(rbRegular);
		p2 setToggleGroup(rbWholeWheat);
		p2 setToggleGroup(rbCheeseStuffed);
		p2 setToggleGroup(rbHotDogStuffed);
		// button group them so only one option can be selected out of the few provided to the user
 	 	 ToggleGroup = new ToggleGroup();
		rbRegular.setToggleGroup(group2);
		setToggleGroup(rbRegular);
		rbWholeWheat.setToggleGroup(group2);
		setToggleGroup(rbWholeWheat);
		rbCheeseStuffed.setToggleGroup(group2);
		setToggleGroup(rbCheeseStuffed);
		rbHotDogStuffed.setToggleGroup(group2);
		setToggleGroup(rbHotDogStuffed);
		bPane(p2);
		// create a new gridePane
 	 	 //--------------------------------------------------------------------		
 	 	 GridLayout g3 = new GridLayout(4,3);
		JPanel p3 = new JPanel(g3);
		Text t3 = new Text("Toppings");
		p3 setBorder(t3);
		// you are not creating a new variable since its just being added to the panel 
 	 	 // also demonstrating that you can convert even if you make object first then add to panel the algorithm will work
 	 	 cbPepperoni = new CheckBox("Peperoni");
		p3 setToggleGroup(cbPepperoni);
		cbGreenPepper = new CheckBox("Green Peeper");
		p3 setToggleGroup(cbGreenPepper);
		// need to add the JCheckbox to the panel
 	 	 cbGreenOlive = new CheckBox("Green Olive");
		p3 setToggleGroup(cbGreenOlive);
		cbExtraCheese = new CheckBox("Extra Cheese");
		p3 setToggleGroup(cbExtraCheese);
		cbMushroom = new CheckBox("Mushrooms");
		p3 setToggleGroup(cbMushroom);
		cbRedPepper = new CheckBox("Red Pepper");
		p3 setToggleGroup(cbRedPepper);
		cbBlackOlive = new CheckBox("Black Olive");
		p3 setToggleGroup(cbBlackOlive);
		cbHam = new CheckBox("Ham");
		p3 setToggleGroup(cbHam);
		cbSausage =new CheckBox("Sausage");
		p3 setToggleGroup(cbSausage);
		cbOnion = new CheckBox("Onion");
		p3 setToggleGroup(cbOnion);
		cbHotPepper = new CheckBox("Hot Pepper");
		p3 setToggleGroup(cbHotPepper);
		cbPineapple = new CheckBox("Pineapple");
		p3 setToggleGroup(cbPineapple);
		// adding it to the frame
 	 	 bPane.setRight(p3);
		// create a new panel
 	 	 //--------------------------------------------------------------------
 	 	 JPanel p4 = new JPanel();
		btCalculate = new Button("Calculate");
		btExit = new Button("Exit");
		p4 setToggleGroup(btExit);
		bPane.setBottom(p4);
		primaryStage.setTitle("Pizza primaryStage.setScene(frame);
		Calculator Tool");
		primaryStage.setWidth(650);
		primaryStage.setHeight(250);
		// divide into set vertical and set horizontal
 	 	 primaryStage.show();
		}
}
