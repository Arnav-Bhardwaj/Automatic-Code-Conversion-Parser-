import javafx.application.Application; 
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.*;
//import javafx.scene.paint.*;

public class PizzaCalculatorFx extends Application {

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
	
	public static void main(String[] args){
		launch(args);
	}

	public BorderPane drawPane() {
		
		BorderPane bPane = new BorderPane();
		
		// Create a label and set its properties
		Label l1 = new Label("Welcome to Algoma Pizza Pit");
		//l1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	
		//	heading.setFill(Color.BLUE);
		BorderPane.setAlignment(l1, Pos. CENTER);
		
		bPane.setTop(l1);
//----------------------------------------------
		
		GridPane gp1 = new GridPane();
		gp1.setVgap(5);
	  //gpPizzaSize.setStyle("-fx-border-color: black;");
		
		Text sizeTitle = new Text("Size");
		//sizeTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
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
		
		GridPane gpDough = new GridPane();
		gpDough.setVgap(5);
		//	gpDough.setStyle("-fx-border-color: black;");
		
		Text doughTitle = new Text("Dough");
		//doughTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		
		bPane.setCenter(gpDough);
		
		ToggleGroup tgDough = new ToggleGroup();
		rbRegular  = new RadioButton("Regular");
		rbWholeWheat = new RadioButton("Whole Wheat"); 
		rbCheeseStuffed = new RadioButton("Cheese Stuffed"); 
		rbHotDogStuffed = new RadioButton("Hot Dog Stuffed");;
		
		
		rbRegular.setToggleGroup(tgDough);
		rbWholeWheat.setToggleGroup(tgDough);
		rbCheeseStuffed.setToggleGroup(tgDough);
		rbHotDogStuffed.setToggleGroup(tgDough);

		gpDough.add(doughTitle, 0, 0);
		gpDough.add(rbRegular, 0, 1);
		gpDough.add(rbWholeWheat, 0, 2);
		gpDough.add(rbCheeseStuffed, 0, 3);
		gpDough.add(rbHotDogStuffed, 0, 4);
		
//---------------------------------------------------------------
		
		GridPane gpToppings = new GridPane();
		gpToppings.setVgap(5);
		gpToppings.setHgap(10);
	   
		// gpToppings.setStyle("-fx-border-color: black;");
		
		Text toppingsTitle = new Text("Toppings");
	    // toppingsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		
		bPane.setRight(gpToppings);
		
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
		
		gpToppings.add(toppingsTitle, 1, 0);
		
		gpToppings.add(cbPepperoni, 0, 1);
		gpToppings.add(cbGreenPepper, 0, 2);
		gpToppings.add(cbGreenOlive, 0, 3);
		gpToppings.add(cbExtraCheese, 0, 4);
		
		gpToppings.add(cbMushroom, 1, 1);
		gpToppings.add(cbRedPepper, 1, 2);
		gpToppings.add(cbBlackOlive, 1, 3);
		gpToppings.add(cbHam, 1, 4);
		
		gpToppings.add(cbSausage, 2, 1);
		gpToppings.add(cbOnion, 2, 2);
		gpToppings.add(cbHotPepper, 2, 3);
		gpToppings.add(cbPineapple, 2, 4);
		
//------------------------------------------------------
		
		HBox hbButtons = new HBox();
		
		btCalculate = new Button("Calculate");
		btExit = new Button("Exit");
		bPane.setBottom(hbButtons);
		
		hbButtons.getChildren().addAll(btCalculate, btExit);
		hbButtons.setSpacing(10);
		hbButtons.setAlignment(Pos.CENTER);
		
		
		// converts up till here then need to hard code this part 		
		return bPane;
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		BorderPane pane1 = drawPane();
		// hard code uptill here
		
		Scene scene = new Scene(new StackPane());
		scene.setRoot(pane1);
		primaryStage.setWidth(600);
		primaryStage.setHeight(200);
		primaryStage.setTitle("Pizza Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}