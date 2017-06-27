import javafx.application.Application; // done
import javafx.geometry.Pos; // done
import javafx.scene.Scene;//done
import javafx.scene.control.Button; //done
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class XPizzaCalculatorFx extends Application {

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

	//TODO FIX THIS 
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

		// Create a label and set its properties
		Label title = new Label("Welcome to Algoma's Pizzeria");
		//l1.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		//	heading.setFill(Color.BLUE);
		BorderPane.setAlignment(title, Pos. CENTER);

		bPane.setTop(title);
		//----------------------------------------------

		GridPane gp1 = new GridPane();
		gp1.setVgap(5);
		//gp1.setStyle("-fx-border-color: black;"); // include this since it is by default swing application had these lines and fx doesnt 

		Text sizeTitle = new Text("Size");
		// sizeTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		bPane.setLeft(gp1);

		rbSmall = new RadioButton("Small");
		rbMedium = new RadioButton("Medium");
		rbLarge = new RadioButton("Large");

		ToggleGroup tg1 = new ToggleGroup();

		rbSmall.setToggleGroup(tg1);
		rbMedium.setToggleGroup(tg1);
		rbLarge.setToggleGroup(tg1);

		gp1.add(sizeTitle, 0, 0);
		gp1.add(rbSmall, 0, 1);
		gp1.add(rbMedium, 0, 2);
		gp1.add(rbLarge, 0, 3);

		//-----------------------------------------------

		GridPane gp2 = new GridPane();
		
		gp2.setVgap(5);
		//gp2.setStyle("-fx-border-color: black;");

		Text doughTitle = new Text("Dough");
		//doughTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		bPane.setCenter(gp2);

		ToggleGroup tgDough = new ToggleGroup();
		rbRegular  = new RadioButton("Regular");
		rbWholeWheat = new RadioButton("Whole Wheat"); 
		rbCheeseStuffed = new RadioButton("Cheese Stuffed"); 
		rbHotDogStuffed = new RadioButton("Hot Dog Stuffed");


		rbRegular.setToggleGroup(tgDough);
		rbWholeWheat.setToggleGroup(tgDough);
		rbCheeseStuffed.setToggleGroup(tgDough);
		rbHotDogStuffed.setToggleGroup(tgDough);

		gp2.add(doughTitle, 0, 0);
		gp2.add(rbRegular, 0, 1);
		gp2.add(rbWholeWheat, 0, 2);
		gp2.add(rbCheeseStuffed, 0, 3);
		gp2.add(rbHotDogStuffed, 0, 4);

		//---------------------------------------------------------------

		GridPane gp3 = new GridPane();
		gp3.setVgap(5);
		gp3.setHgap(10);

		 //gp3.setStyle("-fx-border-color: black;");

		Text toppingsTitle = new Text("Toppings");
		// toppingsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		bPane.setRight(gp3);

		cbPepperoni = new CheckBox("Pepperoni");
		cbGreenPepper = new CheckBox("Green Pepper");
		cbGreenOlive = new CheckBox("Green Olive");
		cbExtraCheese = new CheckBox("Extra Cheese");
		cbMushroom = new CheckBox("Mushroom");
		cbRedPepper = new CheckBox("Red Pepper");
		cbBlackOlive = new CheckBox("Black Olive");
		cbHam = new CheckBox("Ham");
		cbSausage = new CheckBox("Sausage");
		cbOnion = new CheckBox("Onion");
		cbHotPepper = new CheckBox("Hot Pepper");
		cbPineapple = new CheckBox("Pineapple");

		gp3.add(toppingsTitle, 1, 0);
		gp3.add(cbPepperoni, 0, 1);
		gp3.add(cbGreenPepper, 0, 2);
		gp3.add(cbGreenOlive, 0, 3);
		gp3.add(cbExtraCheese, 0, 4);

		gp3.add(cbMushroom, 1, 1);
		gp3.add(cbRedPepper, 1, 2);
		gp3.add(cbBlackOlive, 1, 3);
		gp3.add(cbHam, 1, 4);

		gp3.add(cbSausage, 2, 1);
		gp3.add(cbOnion, 2, 2);
		gp3.add(cbHotPepper, 2, 3);
		gp3.add(cbPineapple, 2, 4);

		//------------------------------------------------------
		GridPane gp4 = new GridPane();
		//If the hbox has a border and/or padding set, then the contents will be layed out within those insets.

		btCalculate = new Button("Calculate");
		btExit = new Button("Exit");
		
		gp4.add(btCalculate,0,1);
		gp4.add(btExit,1,1);
		
		bPane.setBottom(gp4);
		gp4.setAlignment(Pos.CENTER);
		
		return bPane;
	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane pane1 = drawPane();
		// hard code uptill here

		Scene frame = new Scene(new StackPane());
		frame.setRoot(pane1);
		
		primaryStage.setWidth(600);
		primaryStage.setHeight(200);
		primaryStage.setTitle("Pizza Builder");
		primaryStage.setScene(frame);
		primaryStage.show();
	}
}