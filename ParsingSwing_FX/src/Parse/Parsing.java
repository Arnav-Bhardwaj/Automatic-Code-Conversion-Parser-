package Parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Parsing {

	// variables to be used and make the file object here once 
	private static ArrayList<Token> referenceFile = new ArrayList<Token>(); 
	File newFXfile = new File("ButtonToFx.java"); 
	private PrintWriter output;
	// Condition flags
	private boolean match = false; 
	private boolean isJFrame = false; // if the rest work
	private static int indent  = 0; // keep count every time it reachs 
	private static boolean isClass = false;
	private static boolean isApp = false;
	private static boolean isImports = false; // keep track  of special imports


	//TODO
	/**
	 * METHOD NAME: updateFile
	 * MEHTOD DESCRIPTION: Used to output JavaFX code to a new file 
	 * Do any editing after parsing as been conducted here
	 * @throws FileNotFoundException 
	 * 
	 **/
	public void updateFile(String str2) throws FileNotFoundException {

		// just to check and make sure that the file is not there only then create the new object to write ( efficacy purpose)
		if(output == null) {	

			// now create the printwriter in order to start writing 
			output = new PrintWriter(newFXfile); // use this to create the file
			output.println("import javafx.application.Application;"); 
			output.println("import javafx.stage.Stage;");

		}

		//TODO put there in the Seperate method the recursive parsing
		if(str2.equals("class")){
			isClass = true;
		}

		if(isClass && str2.contains("{")) {
			output.print(" extends Application ");
			isClass =false;
		}

		if(str2.equals("args)")){
			isApp = true;
		}

		output.print(str2);

		if(isApp && str2.equals("{")) {
			output.print("\n");
			output.println(" 		launch(args); \n ");
			output.println("	}");
			output.println("       	 @Override ");
			output.println("         public void start(Stage primaryStage) {");
			isApp =false;
		}
		// this is the output for any converted 

		if(str2.contains("{")) { // start case
			output.println();
			indent++; 

			// start of file output
			for(int i=0; i<indent;i++){
				output.print("	");
			}
		}

		if(str2.endsWith(";")) { // this done for formating every time it reads a semicolon it will start a new line
			output.println();

			// start of file output
			for(int i=0; i<indent;i++){
				output.print("	");
			}
		}

		else if(str2.contains("}")) { // end brace 
			output.println();
			indent--; // count 

		} else {
			output.print(" "); // leave a space for when next time called formating again
		}
	}

	/**
	 * METHOD NAME: parseGUI
	 * MEHTOD DESCRIPTION:  it contains many 
	 * checks to ensure the conversion is done properly  and a special case for JFrame since it holds
	 * the rest of the components, ( basic way to convert is to take one token at a time use no dot split to get rid of
	 * the dots in the file. Then use each case to compare with reference file and perform any necessary extra splits
	 * before calling the updateFile function. after set the match to true and break for the main for loop.
	 * @throws FileNotFoundException 
	 * 
	 **/

	public void parseGUI(String str1,ArrayList<String> frameList) throws FileNotFoundException {

		//make sure it doesn't make another token
		if(referenceFile.size() < 1){
			referenceToken(); 
		}

		// store the value from the other method tokesn with no dots
		String[] noDotToken = dotSplitter(str1); 

		// checking around the whole program using the look and working with tokens without dots
		for(int i=0; i < noDotToken.length;i++ ) {

			// need to go through this loop in order to check the reference file every time need to make a check 
			for(int j = 0; j < referenceFile.size();j++ ) {
				//System.out.println(noDotToken[i]);
				// store the comparison token referenceFile.get(i) in a variable
				Token token = referenceFile.get(j);		

				//Check 1
				if(i==0) {

					if(noDotToken[i].equals("import")) { // dealing with imports 
						isImports = true;

					} else{
						for(int k = 0; k < frameList.size(); k++){
							if(noDotToken[i].equals(frameList.get(k))) {  // to check if the the frame reference made by the user is equal to the actull file 
								isJFrame = true;
							}
						}
					}	
				}

				//Check 2 LHS 
				//  if the swing text value matches the one read from the file then write 
				if(noDotToken[i].equals(token.getSwing())) { 		
					//System.out.println(noDotToken[i]);

					//Check 3
					// token is not null for precuation, is Jframe to make sure its passed in as a value,

					if(token.getImportToJavaFX()!= null && isImports){
						//System.out.println(token.getImportToJavaFX());
						updateFile(token.getImportToJavaFX());
					}

					else if(token.getJframeToJavaFx()!= null && isJFrame && i > 0) {
						updateFile(token.getJframeToJavaFx());

					} else {
						updateFile(token.getJavaFX()); // everything else but frame
					}

					match = true;		
					break;
				}

				//Check RHS with new JFRame 
				else if(noDotToken[i].startsWith(token.getSwing())) { // if it has reg expresions check the file with token in ref and if encounter reg ex break it into two seperate strings 

					String []str_new = str1.split(token.getSwing(),2); // on the RHS takes care of = new JFrame (

					if(token. getJframeToJavaFx()!= null && isJFrame){ // to keep track of frame or implementing with reference 

						// switch statement
						switch(token.getSwing()){

						case "setSize" :
							String[] size = str_new[1].split(",",2); //
							updateFile("primaryStage" + token.getJavaFX() + size[0]+");"); // width 
							updateFile("primaryStage"+ token.getJframeToJavaFx()+ "(" + size[1]);  //height
							break;

						case "add" :
							updateFile(noDotToken[0]+token.getJframeToJavaFx() + str_new[1]);
							break;

						case "setVisible":
							updateFile("primaryStage"+ token.getJframeToJavaFx()+"();");
							break;

						default :
							updateFile("primaryStage"+token.getJframeToJavaFx() + str_new[1]);
							//System.out.println("hey");
							ConvertFrame(noDotToken,frameList);
							break;
						}

					} 

					// imports get transformed
					else if(token.getImportToJavaFX()!= null && isImports){

						updateFile(token.getImportToJavaFX());

					} 

					// TODO create a check for 
					else{
						updateFile(token.getJavaFX() + str_new[1]);
					}
					isImports = false;
					match = true;
					break;
				}
			} // end of for loop with reference file
			noSwingFoundHandler(noDotToken[i],noDotToken.length);
		} /// no dot token end
	}

	/**
	 * METHOD NAME: dotSplitter 
	 * MEHTOD DESCRIPTION: 
	 * @throws FileNotFoundException 
	 * 
	 **/ 

	// handler for JFrame and other components LAter  for recursive decent parsing problem
	public void noSwingFoundHandler(String noDot_Token,int lenght) throws FileNotFoundException {
		// if match is false and checking the length occurs since not reprint frame
		// ignore the length inorder for rewriting 

		if((match==false) && (isJFrame ? lenght < 2 : true)) {
			updateFile(noDot_Token); 

		} else {		
			match = false; // to reset and ready to start again when called again 
		} 		
	}

	public void ConvertFrame(String[] noDotToken,ArrayList<String> frameList) throws FileNotFoundException {

		// if match is false and checking the length occurs since not reprint frame
		for(int k = 0; k < frameList.size(); k++){
			updateFile("primaryStage.setScene("+frameList.get(k)+");");
		}
	}

	// handler for JFrame and other components Later  for recursive decent parsing problem
	public String[] dotSplitter(String splitToken_Dot) {

		String [] split_dots = splitToken_Dot.split("\\."); // split get rid of dot 
		for(int i =0; i<split_dots.length;i++) {
		}

		// if it checks the length then is zero set it as first element in since no reg exp to deal with 
		if(split_dots.length == 0) { 
			split_dots= new String[]{splitToken_Dot};

		}
		return split_dots;
	}
	//TODO
	/**
	 * METHOD NAME: referenceToken
	 * MEHTOD DESCRIPTION: 
	 * 
	 **/
	// Helper methods go at the end  one case at a time (GENERAL ALGORITHM code it in the parsing method) (cases represent the conver)
	public void referenceToken() {

		// create token object and add it to the  for all possible tokens to be compared  (compare to swing, convert to JavaFX version)
		referenceFile.add(new Token("javax","javafx")); 
		referenceFile.add(new Token("swing",null,null,".scene."));// button
		referenceFile.add(new Token("JFrame();","Scene(new StackPane());")); 
		referenceFile.add(new Token("JFrame","Scene",null,null)); 
		referenceFile.add(new Token("JPanel","StackPane",null,"layout.StackPane;"));
		referenceFile.add(new Token("JButton","Button",null,"control.Button;")); // these are the comparisons 
		referenceFile.add(new Token("add",".getChildren().add",".setRoot",null)); // Operate Case 
		referenceFile.add(new Token("setSize",".setWidth",".setHeight",null));
		referenceFile.add(new Token("setTitle",null,".setTitle",null));
		referenceFile.add(new Token("setVisible",null,".show",null));

	}

	// so that later when called can be used to access the private data member of output reference
	public PrintWriter getOutput() {
		return output;
	}

	public int getIndent() {
		return indent;
	}
}