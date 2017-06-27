/* 
 * COSC 2006AE Fall 2013 
 * Name : Arnav Bhardwaj
 * Assignment #2 
 * Description: This program uses different modules to solve the bin packing problem. There are two arrays one for storing the items from 
 * the given file and the other is the bin that contains the items. Then use the bubble sort inorder to display the data wiht either best fit 
 * algorithm or first fit algorithm.
 */
package Parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class TestConvertor {

	public static void main (String args[]) throws FileNotFoundException,NoSuchElementException {
		//Use JFileChooser to easily select a text file. 
		JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// Get the selected file
			File swingFile = fileChooser.getSelectedFile();

			ArrayList<String> frameList = new ArrayList<String>();

			// create the parser object 
			Parsing parseSwing = new Parsing();
			Scanner input = new Scanner(swingFile);

			// alist of JFrames array list ( check if name matches .add and convert)

			while(input.hasNext()) {

				// its a single word ( token)
				String token = input.next(); // just one thing at a time gets read in the loop JFrame then frame ....

				//System.out.println(token);

				if(token.equals("JFrame")) { // to match a specific object

					String ref_frame = input.next();

					frameList.add(ref_frame); // for the fact if there is more than one frame created it increases efficiency

					parseSwing.parseGUI(token,frameList);
					parseSwing.parseGUI(ref_frame,frameList); // pass in because jframe is special case 
					continue; // so it doesnt 
				}


				// create the pattern for recognizing the pattern (the comment by //) now create a match with tokens
				Pattern p = Pattern.compile("//");
				Matcher match = p.matcher(token);

				if(match.matches()){ // it matches the pattern and then perform the comment conversion

					// find how add a new line
					String currentline = input.nextLine(); // found the comment and check the next nie is comment if not continue
					parseSwing.updateFile(p + currentline + "\n"); // adding a new line whit the comment 

					for(int i=0; i< parseSwing.getIndent();i++){
						parseSwing.updateFile("	"); // adding a new line whit the comment 
					}
					// System.out.println(currentline); just a test
				}

				else {
					parseSwing.parseGUI(token,frameList); // when no specific case to deal with
				}
			}	

			input.close();
			parseSwing.getOutput().close(); // close it after u write the stream
		}
	}
}
	