import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx .scene. layout.BorderPane;
import javafx .scene. layout.GridPane;
import javafx .scene. control.ToggleGroup;
import javafx .scene. control.Button;
import javafx .scene. control.CheckBox;
import javafx .scene. Scene;
import javafx .scene. control.Label;
import javafx .scene. layout.StackPane;
import javafx .scene. control.RadioButton;
import javafx .scene. text. Text;
public class PizzaApplication  extends Application {
	 //create variables 
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
	public static void main(String[] args) {
 		launch(args); 
 
	}
    	public BorderPane drawPane() {

		BorderPane bPane = new BorderPane();
		 Label title = new Label("Welcome to Algoma's Pizzereia");
		BorderPane.setAlignment(title,Pos. CENTER);
		// to make the layout visible you need to add it to the frame
 	 	 bPane.setTop(title);
		// create a new gridePane
 	 	 //--------------------------------------------------------------------
 	 	 GridPane g1  = new GridPane();
		g1 .setVgap(5);
		Text t1 = new Text("Size");
		// this is made so that you can use the name of the constructor to make button groups. 
 	 	 rbSmall = new RadioButton("Small");
		rbMedium = new RadioButton("Medium");
		rbLarge = new RadioButton("Large");
		// adds the button to the panel
 	 	 // to create the panels border and the title 
 	 	 g1. add(t1,0,0);
		g1. add(rbSmall,0,1);
		g1. add(rbMedium,0,2);
		g1. add(rbLarge,0,3);
		//create the button group for the fact it only choices one option at a time.
 	 	 ToggleGroup group = new ToggleGroup();
		rbSmall.setToggleGroup(group);
		rbMedium.setToggleGroup(group);
		rbLarge.setToggleGroup(group);
		bPane.setLeft(g1);
		// create a new gridePane
 	 	 //--------------------------------------------------------------------
 	 	 GridPane g2  = new GridPane();
		g2 .setVgap(5);
		Text t2 = new Text("Dough");
		// One way to convert this is do this by creating all the objects first then add them to the appropriate panel  
 	 	 rbRegular = new RadioButton("Regular");
		rbWholeWheat = new RadioButton("Whole Wheat");
		rbCheeseStuffed = new RadioButton("Cheese Stuffed");
		rbHotDogStuffed = new RadioButton("HotDog Stuffed");
		// type what need to be presented inside the brackets
 	 	 g2. add(t2,0,0);
		g2. add(rbRegular,0,1);
		g2. add(rbWholeWheat,0,2);
		g2. add(rbCheeseStuffed,0,3);
		g2. add(rbHotDogStuffed,0,4);
		// button group them so only one option can be selected out of the few provided to the user
 	 	 ToggleGroup group2 = new ToggleGroup();
		rbRegular.setToggleGroup(group2);
		rbWholeWheat.setToggleGroup(group2);
		rbCheeseStuffed.setToggleGroup(group2);
		rbHotDogStuffed.setToggleGroup(group2);
		bPane.setCenter(g2);
		// create a new gridePane
 	 	 //--------------------------------------------------------------------		
 	 	 GridPane g3  = new GridPane();
		Text t3 = new Text("Toppings");
		g3 .setVgap(5);
		g3 .setHgap(10);
		// you are not creating a new variable since its just being added to the panel 
 	 	 // also demonstrating that you can convert even if you make object first then add to panel the algorithm will work
 	 	 g3. add(t3,0,0);
		cbPepperoni = new CheckBox("Peperoni");
		g3. add(cbPepperoni,0,1);
		cbGreenPepper = new CheckBox("Green Peeper");
		g3. add(cbGreenPepper,0,2);
		// need to add the JCheckbox to the panel
 	 	 cbGreenOlive = new CheckBox("Green Olive");
		g3. add(cbGreenOlive,0,3);
		cbExtraCheese = new CheckBox("Extra Cheese");
		g3. add(cbExtraCheese,0,4);
		cbMushroom = new CheckBox("Mushrooms");
		g3. add(cbMushroom,1,1);
		cbRedPepper = new CheckBox("Red Pepper");
		g3. add(cbRedPepper,1,2);
		cbBlackOlive = new CheckBox("Black Olive");
		g3. add(cbBlackOlive,1,3);
		cbHam = new CheckBox("Ham");
		g3. add(cbHam,1,4);
		cbSausage =new CheckBox("Sausage");
		g3. add(cbSausage,2,1);
		cbOnion = new CheckBox("Onion");
		g3. add(cbOnion,2,2);
		cbHotPepper = new CheckBox("Hot Pepper");
		g3. add(cbHotPepper,2,3);
		cbPineapple = new CheckBox("Pineapple");
		g3. add(cbPineapple,2,4);
		// adding it to the frame
 	 	 bPane.setRight(g3);
		GridPane g4  = new GridPane();
		btCalculate = new Button("Calculate");
		btExit = new Button("Exit");
		g4. add(btCalculate,0,1);
		g4. add(btExit,1,1);
		bPane.setBottom(g4);
		g4.setAlignment(Pos.CENTER);

		return bPane;
	   }

	  @Override
	  public void start(Stage primaryStage) {

		BorderPane pane1 = drawPane();
		Scene frame =  new Scene(new StackPane());
		frame.setRoot(pane1);
		primaryStage.setTitle("Pizza Builder");
		primaryStage.setScene(frame);
		primaryStage.setWidth(600);
		primaryStage.setHeight(200);
		// divide into set vertical and set horizontal
 	 	 primaryStage.show();
		}
}
