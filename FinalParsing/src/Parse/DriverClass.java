package Parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class DriverClass {

	public static void main (String args[]) throws FileNotFoundException,NoSuchElementException, GridIndexOutOfBoundsException {

		//Use JFileChooser to easily select a text file. 
		JFileChooser fileChooser = new JFileChooser();
		String label = null;

		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			// Get the selected file
			File swingFile = fileChooser.getSelectedFile();

			String frameList = null;
			ArrayList<String> buttonGroup = new ArrayList<String>(); 
			ArrayList<String> extraPanel = new ArrayList<String>();
			ArrayList<String> gridLayout = new ArrayList<String>();
			ArrayList<GPInformation> panels = new ArrayList<GPInformation>(); // add all the different 
			ArrayList<GridLayoutInformation> grids = new ArrayList<GridLayoutInformation>();

			// create the parser object 
			Parsing swing = new Parsing();
			Scanner input = new Scanner(swingFile);

			// alist of JFrames array list ( check if name matches .add and convert)

			while(input.hasNext()) {

				boolean isGridPane = false;

				// its a single word ( token)
				String token = input.next(); // just one thing at a time gets read in the loop JFrame then frame ....
			
				// create the pattern for recognizing the pattern (the comment by //) now create a match with tokens object
				Pattern p = Pattern.compile("//.*");
				Matcher match = p.matcher(token);
				if(token.equals("import")){
				
					String delete = input.nextLine();
					delete = delete.substring(1,delete.length());
					
					if(!delete.equals("javax.swing.SwingConstants;")){ // delete it 
						swing.parseGUI(token, frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane);
						swing.parseGUI(delete, frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane);
					}
				continue;
				}		
				
				if(token.equals("JLabel")) {
					String ref_label = input.next();
					label = ref_label;

					swing.parseGUI(token, frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane);
					swing.parseGUI(ref_label, frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane);

				}
				
				//dealing with gridlayout tokkens and passing them to the parseGUI
				else if(token.equals("GridLayout")){
					String ref_grid = input.next();

					// add it to the list since there is more than one gridlayout

					String coordinates = input.nextLine();
					//swing.parseGUI(coordinates,frameList,label,buttonGroup,gridLayout,panels,grids);

					coordinates = coordinates.substring(18,21);// parse the string to int by taking the values (31) and 3 is stored

					String[] vaulOfRowCol = coordinates.split(","); 

					grids.add(new GridLayoutInformation(ref_grid,Integer.parseInt(vaulOfRowCol[1]),Integer.parseInt(vaulOfRowCol[0])));
					// the class has the value of gird, row and columns 
				}

				// this is an example of how to deal with the tokenizing of the JPanel's reference
				else if(token.equals("JPanel")){

					String ref_panel = input.next(); // dont need to pass it in since it 

					String tempGridNameUpdate = input.nextLine();

					String arry[] = tempGridNameUpdate.split(" "); // 
					String temp = arry[arry.length-1].split("\\(")[1];
					temp = temp.substring(0, temp.length()-2);

					//System.out.println(temp);
					if(temp.length()>0)
						panels.add(new GPInformation(ref_panel)); // the panel reference is added into the object and current col and row are set 0,0 as coordinates

					// filling up the ArrayList ( with objects filled)
					for(int i=0; i<grids.size(); i++) {

						GridLayoutInformation currGrid= grids.get(i);

						if(currGrid.getRefGrid().equals(temp)) {  // checking the reference of the gridLayout and equal (tempGridname) meaning the panels (g1) = the gridLayout
							panels.get(panels.size()-1).setMaxCol(currGrid.getMaxCol()); // match the value and assign the object with the max coordinate for coloumn 
							panels.get(panels.size()-1).setMaxRow(currGrid.getMaxRow());// match the value and assign the object with the max coordinate for Row
							panels.get(panels.size()-1).setGrid(currGrid.getRefGrid()); // set the grid 
						}
					}

					// grab gridpane ref in Jpanel
					tempGridNameUpdate = tempGridNameUpdate.substring(14, tempGridNameUpdate.length()-2); 

					if(tempGridNameUpdate.length()==0){ // when panel has no gridLaylout place it in a list 
						extraPanel.add(ref_panel);
					}else{ // else resume adding into token 

						swing.addDynamicRef(ref_panel, tempGridNameUpdate + ".", tempGridNameUpdate);
						isGridPane = true;
					}

					swing.parseGUI(token,frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane);
					swing.parseGUI(ref_panel,frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane); // pass in the token panel ref

					for(int i= 0; i<arry.length;i++) {
						swing.parseGUI(arry[i],frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane); // this passes in the line of "
					}

					continue;

				}else if(token.equals("ButtonGroup")){
					String ref_ButtonGroup = input.next();
					//System.out.println(ref_ButtonGroup);

					buttonGroup.add(ref_ButtonGroup);

					swing.parseGUI(token,frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane);
					swing.parseGUI(ref_ButtonGroup,frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane); 

				}else if(token.equals("JFrame")) { // to match a specific object and storing the reference token of Jframe in an array list 

					String ref_frame = input.next();
					frameList = ref_frame; // for the fact if there is more than one frame created it increases efficiency

					swing.parseGUI(token,frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane);
					swing.parseGUI(ref_frame,frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane); // pass in because jframe is special case 

				} else if(match.matches()){ // it matches the pattern and then perform the comment conversion

					// find how add a new line
					String currentline = input.nextLine(); // found the comment and check the next nie is comment if not continue
					//System.out.println(token + currentline);
					swing.updateFile( token + currentline + "\n"); // adding a new line whit the comment 

					for(int i=0; i< swing.getIndent();i++){
						swing.updateFile("	"); // adding a new line with the comment 
					}
				}else {
					swing.parseGUI(token,frameList,label,buttonGroup,gridLayout,panels,grids,isGridPane); // when no specific case to deal with
				}
			}	

			input.close();
			swing.getOutput().close(); // close it after u write the stream
		}
	}
}


