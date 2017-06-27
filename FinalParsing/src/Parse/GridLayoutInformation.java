package Parse;

public class GridLayoutInformation {
	private String refGrid;
	private int maxCol;
	private int maxRow;

	GridLayoutInformation(String n_refGrid, int n_maxCol, int n_maxRow){
		refGrid = n_refGrid;
		maxCol = n_maxCol;
		maxRow = n_maxRow;
	}

	public void setRefGrid(String refGrid) {
		this.refGrid = refGrid;
	}

	public String getRefGrid() {
		return refGrid;
	}

	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public int getMaxRow() {
		return maxRow;
	}
}
