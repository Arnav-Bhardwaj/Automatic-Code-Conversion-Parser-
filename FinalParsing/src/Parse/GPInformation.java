package Parse;
/*
 * Decription : contains the information regarding the Columns
 */
public class GPInformation {
	private String panel;
	private int maxCol;
	private int maxRow;
	private int currCol;
	private int currRow;
	private String grid; // to be able to match the panel with a grid attached

	// you need this only to set the values of the panel and set the current value to the 0 
	GPInformation(String n_panel) {
		panel = n_panel;
		currCol = 0;
		currRow = 1;
	}

	// Getters for all the different properties 
	public String getPanel() {
		return panel;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public void setMaxCol(int maxCol) { // only need this for the max value it will change
		this.maxCol = maxCol;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(int maxRow) { // only need this for the max value as value
		this.maxRow = maxRow;
	}

	public int getCurrCol() {
		return currCol;
	}

	public int getCurrRow() {
		return currRow;
	}
	
	public void setGrid(String grid){
		this.grid = grid;
	}
	
	public String getGrid(){
		return grid;
	}

	// take care of the colounm portion where the user needs  
	public void incrementRow() throws GridIndexOutOfBoundsException {
		currRow++;
		if(currRow > maxRow){
			currRow = 1; // start 
			incrementCol(); // rolls over the number
		}
	}

	//helper method private since it has not directly called to when it over max
	private void incrementCol() throws GridIndexOutOfBoundsException{
		currCol++;
		
		if(currCol>maxCol) {
			System.out.println(maxCol);
			throw new GridIndexOutOfBoundsException("Gridlayout index has exceeded maxGridSize");
		}
	}
}
