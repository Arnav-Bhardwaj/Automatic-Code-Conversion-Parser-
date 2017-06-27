package Parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Parsing {

	// variables to be used and make the file object here once 
	private static ArrayList<Token> referenceTokens = new ArrayList<Token>(); 

	private File newFXfile = new File("PizzaApplication.java");  // the file name when it is converted
	private static String temp_reference =" " ;
	private PrintWriter output;
	private static String buttonGroup;
	private static ArrayList<GPInformation> GPI;
	private static String afterJPanel = "";

	// Condition flags
	private boolean match = false; 
	private boolean isFrameReference = false; // if the rest work
	private static int indent  = 0; // keep count every time it reachs 
	private static boolean isClass = false;
	private static boolean isPublic;
	private static boolean isApp = false;
	private static boolean isImports = false; // keep track  of special imports  what you are looking for then change it 
	private static boolean isStatic = false;
	private static boolean isExtraCode = false;
	private static boolean isRefLabel = false;
	private static boolean putLabel = false;
	private static boolean isTitle =false; 
	private static boolean isRefButtonGrp =false;
	private static boolean temp = false; 
	private static boolean isJPanel = false;
	private static boolean isJFrame = false;
	private static String additional = "";
	private static String storeFrame = null;

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
			output.println("import javafx.geometry.Pos;");
			output.println("import javafx.stage.Stage;");

		}
		// the idea for these if statements is to look for the particular contain in the file and when match occurs while reading write it inbetween the read in data 
		//TODO put there in the Separate method the recursive parsing
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
		if(str2.contentEquals("Label")) {
			putLabel = true;

		}

		if(str2.equals("bPane.setBottom(g4);")) { // TODO when you finish converting the entire program then change the token your looking for 
			isExtraCode = true;
		}

		output.print(str2); // print rest of the conversion as it is 

		if(isApp && str2.endsWith("{")) {
			output.print("\n");
			output.println(" 		launch(args); \n ");
			output.println("	}");
			output.println("    	public BorderPane drawPane() {");
			output.print("\n");
			output.print("		BorderPane bPane = new BorderPane();");

			isApp =false;
		}

		if(isExtraCode && str2.endsWith("g4);")){ //TODO
			output.print("\n");
			output.println("		g4.setAlignment(Pos.CENTER);\n");
			output.println("		return bPane;");
			output.println("	   }");
			output.print("\n");
			output.println("	  @Override");
			output.println("	  public void start(Stage primaryStage) {");
			output.print("\n");
			output.print("		BorderPane pane1 = drawPane();");
			output.print("\n");
			output.print("		"+additional);
			output.print("\n");
			output.print("		"+storeFrame +"."+ "setRoot(pane1);"); // add the root portion

			isExtraCode = false;
		}
		// TODO NOW place the scene object


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
	 * MEHTOD DESCRIPTION:  it contains many checks to ensure the conversion 
	 * is done properly and a special case for JFrame since it holds
	 * the rest of the components, ( basic way to convert is to take one token at a time use no dot split to get rid of
	 * the dots in the file. Then use each case to compare with reference file and perform any necessary extra splits
	 * before calling the updateFile function. after set the match to true and break for the main for loop.
	 * @param isGridPane 
	 * @throws FileNotFoundException 
	 * @throws GridIndexOutOfBoundsException 
	 * 
	 **/
	public void parseGUI(String str1,String frameList,
			String labelRef,ArrayList<String> buttonGrpRef,
			ArrayList<String> gridLayout,ArrayList<GPInformation> gpInfo, // gpInfor also contains panels
			ArrayList<GridLayoutInformation> grids, boolean isGridPane) throws FileNotFoundException, GridIndexOutOfBoundsException {
		//make sure it doesn't make another token
		if(referenceTokens.size() < 1){
			referenceToken(); 
		}

		// store the value from the other method token with no dots
		String[] noDotToken = dotSplitter(str1); 

		// test the label implies that you have a tag that allows you to jump back to and start again the process if necessary 
		tokenLoop:
			// checking around the whole program using the look and working with tokens without dots
			for(int i=0; i < noDotToken.length;i++) {

				// need to go through this loop in order to check the reference file every time need to make a check 
				for(int j = 0; j < referenceTokens.size();j++) {

					// store the comparison token referenceFile.get(i) in a variable
					Token token = referenceTokens.get(j);		

					//Check 1
					if(i==0) {

						if(noDotToken[i].equals("import")) { // dealing with imports 
							isImports = true;

						} 
						if(noDotToken[i].equals("public")){
							isPublic=true;
						}else if(noDotToken[i].equals("static")) { 
							if(!isPublic) {
								isStatic = true;
								continue tokenLoop;  //  goes to the next iteration of the loop labled tookenLoop 
							}
							// when the statick keyword is found and it follows  public then reset the boolean values and print static in "public static " appears
							isPublic = false;
							isStatic = false;
							break;

						}else{

							isPublic = false; // next token after public is not static , reset the check
							if(noDotToken[i].equals(labelRef)) {
								isRefLabel = true;
								match = true;
							}

							if(noDotToken[i].equals(frameList)) {  // to check if the the frame reference made by the user is equal to the actull file 
								isFrameReference = true;
							}

							if(noDotToken[i].equals("ButtonGroup")){
								temp = true;
							}

							for(int k=0; k < buttonGrpRef.size();k++) {

								if(noDotToken[i].equals(buttonGrpRef.get(k))){

									if(temp==true){  // use this because you want print group reference
										temp = false;
										updateFile(noDotToken[i]);
									}
									else {
										buttonGroup = buttonGrpRef.get(k);
										isRefButtonGrp =true;
										match = true;
									}
								}
							}
						}
					}

					//Check 2 LHS 
					//  if the swing text value matches the one read from the file then write 
					if(noDotToken[i].equals(token.getSwing())) { 		
						// token is not null for precuation, is Jframe to make sure its passed in as a value,
						if(token.getPanelToGridPane()!= null && isGridPane) {

							updateFile(token.getPanelToGridPane());

						}else if(token.getJavaFX()!=null &&noDotToken[i].equals("JFrame")){

							storeFrame = frameList;
							additional += token.getJavaFX() + " " + storeFrame+ " = "; 
							isJFrame = true;
							continue tokenLoop; 

						}else if(isJFrame){
							additional += " new " + token.getJavaFX();
							isJFrame = false;

							continue tokenLoop;
						
						}else if(token.getImportToJavaFX()!= null && isImports){
							updateFile(token.getImportToJavaFX());

						}else if(token.getImportToJavaFX()!= null && isStatic){
							updateFile(token.getJavaFX());

						}else if(token.getJframeToJavaFx()!= null && isFrameReference && i > 0) {
							updateFile(token.getJframeToJavaFx());

						}else {

							updateFile(token.getJavaFX()); // everything else but frame gets updated under left side
						}
						match = true; // once done converting set match to true due to the fact the program is inside the loop	
						break; // and now break out of loop to proceed to RHS
					}

					//Check RHS with more regular expressions portion exist here 
					else if(noDotToken[i].startsWith(token.getSwing())) { // if it has reg expressions check the file with token in ref and if encounter reg ex break it into two seperate strings 

						// converts the coordinates for The GridPane
						if(noDotToken.length > 1 && (noDotToken[1].startsWith("add") || noDotToken[1].startsWith("setBorder"))){ // only if the add in gereneral is there

							// to deal with the gridpane option
							for(int k=0; k < gpInfo.size();k++) {

								String temp = noDotToken[1].split("\\(")[1]; // storing the  right side of the parentheses .add(....)
								temp = temp.substring(0,temp.length()-2); // getting rid of closing pranthese and semicolon  to obtain the information of refernce

								if(gpInfo.get(k).getPanel().equals(noDotToken[0])) { // make the match based on the ArrayList contents of panel ref and coordinates from GPInfoClass

									boolean isTitle = false;

									if(noDotToken[1].startsWith("setBorder")){

										isTitle = true;
									}
									//call the function to pass in the proper information
									gridLayoutCoordinates(gpInfo.get(k),temp,isTitle); // make the conversion of the coordinates

									continue tokenLoop; // this will exit out back to where the label is created 
								}
							}
						}	
						if(isRefLabel) { 
							// changes from title.setHorizontalAlignment(SwingConstants.CENTER); to BorderPane.setAlignment(title, Pos.CENTER);

							temp_reference = labelRef;

							if(token.getSwing().equals("setHorizontalAlignment")) {
								updateFile("BorderPane"+ token.getJavaFX()+"(" + temp_reference +",Pos."); 
								isRefLabel = false;
								continue tokenLoop;
							}
						}

						else if(isGridPane) {
							updateFile(token.getPanelToGridPane()+"();");
							continue tokenLoop;
						}

						String []str_new = str1.split(token.getSwing(),2); // on the RHS takes care of = new JFrame (
						String convertedPos;

						// another case for buttongroup 
						if(isRefButtonGrp&& token.getSwing().startsWith("add")) { // look for a specific pattern inside the code

							isRefButtonGrp = false;// not detect add again after it has done with its special case portion
							String userButtonRef = str_new[1].substring(1, str_new[1].length()-2); // store the reference inside the button
							updateFile(userButtonRef+"."+ token.getJavaFX() +"("+buttonGroup +");" );
							continue tokenLoop;
						}

						if(token.getJframeToJavaFx()!= null && isFrameReference) { // to keep track of frame or implementing with reference 

							isFrameReference = false;

							switch(token.getSwing()) {

							case "setSize" :
								String[] size = str_new[1].split(",",2); // 
								updateFile("primaryStage" + token.getJavaFX() + size[0]+");"); // width 
								updateFile("primaryStage"+ token.getJframeToJavaFx()+ "(" + size[1]);  //height
								break;

							case "add" :
								convertedPos = ConvertPostion(str_new[1],referenceTokens,gpInfo); // got the line
								updateFile(convertedPos);
								break tokenLoop; // it breaks specify from the outer loop which causes it not to convert again in the else statment

							case "setVisible":
								updateFile("primaryStage"+ token.getJframeToJavaFx()+"();");
								break;

							default :

								updateFile("primaryStage"+token.getJframeToJavaFx() + str_new[1]);
								isTitle =true;
								//TODO ConvertFrame(frameList);
								break;
							}
						} 

						// imports get transformed here
						else if(token.getImportToJavaFX()!= null && isImports){

							updateFile(token.getImportToJavaFX());

						} else{

							updateFile(token.getJavaFX() + str_new[1]); // not a special case then just simply convert using the getJavaFx method
						}

						isImports = false; // keep track of when to stop converting 
						match = true;
						break; // break out of the loop
					}
				} // end of for loop with reference file
				if(!isJFrame){
					noSwingFoundHandler(noDotToken[i],noDotToken.length,labelRef,frameList);
				}	

			} // no dot token end
	}

	/**
	 * METHOD NAME: dotSplitter 
	 * MEHTOD DESCRIPTION: 
	 * @throws FileNotFoundException 
	 * 
	 **/ 
	// handler for JFrame and other components LAter  for recursive decent parsing problem

	public void noSwingFoundHandler(String noDotToken,int lenght,String labelRef,String frameList) throws FileNotFoundException {
		// if match is false and checking the length occurs since not reprint frame
		// ignore the length inorder for rewriting 	
		if(isRefLabel && noDotToken.equals("=") ) {
			updateFile(labelRef+" ="); 
			isRefLabel = false;

		}else if((match==false) && (isFrameReference ? lenght < 2 : true)) {
			updateFile(noDotToken); 

		}else if(isTitle && !match){
			isTitle = false;
			ConvertFrame(frameList);
		}else {		
			match = false; // to reset and ready to start again when called again 
		} 

	}

	//TODO
	/**
	 * METHOD NAME: updateFile
	 * MEHTOD DESCRIPTION: Used to output JavaFX code to a new file 
	 * Do any editing after parsing as been conducted here
	 * @throws FileNotFoundException 
	 * 
	 **/
	public void gridLayoutCoordinates(GPInformation arrayGPI,String temp, boolean isTitle) throws FileNotFoundException, GridIndexOutOfBoundsException {
		if(isTitle) {
			updateFile("add("+ temp + ",0,0);");

		}else {
			updateFile("add("+ temp + "," + arrayGPI.getCurrCol() + "," + arrayGPI.getCurrRow() + ");");
			arrayGPI.incrementRow();
		}
	}

	//TODO
	/**
	 * METHOD NAME: updateFile
	 * MEHTOD DESCRIPTION: Used to output JavaFX code to a new file 
	 * Do any editing after parsing as been conducted here
	 * @throws FileNotFoundException 
	 * 
	 **/
	public void ConvertFrame(String frameList) throws FileNotFoundException {

		// if match is false and checking the length occurs since not reprint frame
		updateFile("primaryStage.setScene("+frameList+");");
	}
	//TODO
	/**
	 * METHOD NAME: updateFile
	 * MEHTOD DESCRIPTION: Used to output JavaFX code to a new file 
	 * Do any editing after parsing as been conducted here
	 * @throws FileNotFoundException 
	 * 
	 **/
	//special case for ConvertPosition
	public String ConvertPostion(String token,ArrayList<Token> referenceFile,ArrayList<GPInformation> gpInfo) {

		String result = "bPane";
		String [] splitConvert = token.split(","); // split it in the form of (title , BorderLayout.NORTH) two portions

		// this loop will go through the entire file and convert only when it needs to convert for every line that is passed in
		for(int i = 0; i < referenceFile.size();i++ ){

			if(splitConvert[1].endsWith(referenceFile.get(i).getSwing()+");") && splitConvert[1].startsWith("BorderLayout.CENTER")){ // only for when it is CENTER
				result += referenceFile.get(i).getCenter();

			}

			else if(splitConvert[1].endsWith(referenceFile.get(i).getSwing()+");")) {
				result += referenceFile.get(i).getJavaFX();
			}

		}
		// split the (p1 and remove opening bracket
		String split = splitConvert[0].substring(1, splitConvert[0].length());

		for(int j=0; j < gpInfo.size(); j++) {
			if(split.equals(gpInfo.get(j).getPanel())){ // token matches the panels from the file passed in

				result += "("+ gpInfo.get(j).getGrid()+ ");";
				return result;
			}
		}

		result +=  splitConvert[0]+ ");";
		return result;
	}
	//TODO
	/**
	 * METHOD NAME: updateFile
	 * MEHTOD DESCRIPTION: Used to output JavaFX code to a new file 
	 * Do any editing after parsing as been conducted here
	 * @throws FileNotFoundException 
	 * 
	 **/
	// handler for JFrame and other components Later  for recursive decent parsing problem
	public String[] dotSplitter(String splitToken_Dot) {

		String [] split_dots = splitToken_Dot.split("\\."); // split get rid of dot 

		// if it checks the length then is zero set it as first element in since no reg exp to deal with 
		if(split_dots.length == 0) { 
			split_dots= new String[]{splitToken_Dot};

		}
		return split_dots;
	}
	//TODO
	/**
	 * METHOD NAME: updateFile
	 * MEHTOD DESCRIPTION: Used to output JavaFX code to a new file 
	 * Do any editing after parsing as been conducted here
	 * @throws FileNotFoundException 
	 * 
	 **/
	public void addDynamicRef(String swing, String fx, String gridPane){
		referenceTokens.add(new Token(swing,fx,null,null,gridPane,null));
	}

	//TODO
	/**
	 * METHOD NAME: referenceToken
	 * MEHTOD DESCRIPTION: 
	 * 
	 **/

	// Helper methods go at the end  one case at a time (GENERAL ALGORITHM code it in the parsing method) (cases represent the convert)
	public void referenceToken() {

		// create token object and add it to the  for all possible tokens to be compared  (compare to swing, convert to JavaFX version)
		referenceTokens.add(new Token("javax","javafx")); 
		referenceTokens.add(new Token("swing",null,null,".scene.",null,null));// button
		referenceTokens.add(new Token("TitledBorder","Text",null,"Text;",null,null));
		referenceTokens.add(new Token("setHorizontalAlignment",".setAlignment")); // special case
		referenceTokens.add(new Token("JFrame();","Scene(new StackPane());"));
		referenceTokens.add(new Token("java",null,null,"javafx",null,null));
		referenceTokens.add(new Token("border",null,null,"text.",null,null));
		referenceTokens.add(new Token("BorderLayout",null,null,"layout.BorderPane;",null,null));
		referenceTokens.add(new Token("awt",null,null,".scene.",null,null));
		referenceTokens.add(new Token("JButton","Button",null,"control.Button;",null,null)); // these are the comparisons 
		referenceTokens.add(new Token("JLabel","Label" ,null,"control.Label;",null,null));
		referenceTokens.add(new Token("JFrame","Scene",null,null,null,null)); 
		referenceTokens.add(new Token("JRadioButton","RadioButton",null,"control.RadioButton;",null,null)); 
		referenceTokens.add(new Token("GridLayout","GridPane",null,"layout.GridPane;",null,null)); 
		referenceTokens.add(new Token("setBorder","add",null,null,null,null)); 
		referenceTokens.add(new Token("JPanel","StackPane",null,"layout.StackPane;","GridPane",null)); // the constructor contains any kind of conversion involved with
		referenceTokens.add(new Token("JCheckBox","CheckBox",null,"control.CheckBox;",null,null)); 
		referenceTokens.add(new Token("add","setToggleGroup",".setRoot",null,null,null)); // Operate Case 
		referenceTokens.add(new Token("setSize",".setWidth",".setHeight",null,null,null));
		referenceTokens.add(new Token("setTitle",null,".setTitle",null,null,null));
		referenceTokens.add(new Token("setVisible",null,".show",null,null,null));
		referenceTokens.add(new Token("setVgap",".setVgap",null,null,null,null));
		referenceTokens.add(new Token("setHgap",".setHgap",null,null,null,null));
		referenceTokens.add(new Token("ButtonGroup","ToggleGroup",null,"control.ToggleGroup;",null,null)); // there  
		referenceTokens.add(new Token("NORTH",".setTop",null,null,null,null)); // there  (borderlayout conversion)
		referenceTokens.add(new Token("SOUTH",".setBottom",null,null,null,null)); // there  
		referenceTokens.add(new Token("BorderLayout.CENTER",null,null,null,null,".setCenter")); // there  
		referenceTokens.add(new Token("WEST",".setLeft",null,null,null,null)); // there 
		referenceTokens.add(new Token("EAST",".setRight",null,null,null,null)); // there  
	}

	// so that later when called can be used to access the private data member of output reference
	public PrintWriter getOutput() {
		return output;
	}

	public int getIndent() {
		return indent;
	}
}