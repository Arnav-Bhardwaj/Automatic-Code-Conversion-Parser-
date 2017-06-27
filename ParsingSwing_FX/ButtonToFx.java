import javafx.application.Application;
import javafx.stage.Stage;
import javafx .scene. control.Button;
import javafx .scene. Scene;
import javafx .scene. layout.StackPane;
public class ButtonToFx  extends Application {
	 public static void main(String[] args) {
 		launch(args); 
 
	}
       	 @Override 
         public void start(Stage primaryStage) {

		 StackPane p1 = new StackPane();
		// create the button
 	 	 // made another comment
 	 	 Button jbtOK = new Button("OK");
		// Add the button to the panel  
 	 	 p1 .getChildren().add(jbtOK);
		Scene frame = new Scene(new StackPane());
		frame.setRoot(p1);
		primaryStage.setWidth(200);
		primaryStage.setHeight(50);
		primaryStage.setTitle("MyFrameWithComponents");
		primaryStage.setScene(frame);
		primaryStage.show();
		}
}
