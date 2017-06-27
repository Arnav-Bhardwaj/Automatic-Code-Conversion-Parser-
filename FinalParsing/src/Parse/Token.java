package Parse;
/*
 * Author: Arnav Bhardwaj
 */
public class Token {
	private String swing; // tokens specified for both gui componets
	private String javaFX;
	private String jframeToJavaFx;
	private String importToJavaFX;
	private String panelToGridPane;
	private String center;
	
	//the constructor has the swing and fx  when created it can be used to compare the text values
	Token(String n_swing, String n_javaFX) {
		swing = n_swing;
		javaFX = n_javaFX;
	}
	
	Token(String n_swing, String n_javaFX, String n_jframeToJavaFx, String n_importToJavaFX, String n_panelToGridPane, String n_center) {
		swing = n_swing;
		javaFX = n_javaFX;
		jframeToJavaFx = n_jframeToJavaFx;
		importToJavaFX = n_importToJavaFX;
		panelToGridPane = n_panelToGridPane;
		center = n_center;
	}
	
	public String getSwing() { // used to compare to input file tokens
		return swing;
	}
	
	public String getCenter() { // used to compare to input file tokens
		return center;
	}

	public String getJavaFX() { // if matches then output 
		return javaFX;
	}

	public String getJframeToJavaFx() {
		return jframeToJavaFx;
	}

	public String getImportToJavaFX() {
		return importToJavaFX;
	}
	
	public String getPanelToGridPane() {
		return panelToGridPane;
	}
}
