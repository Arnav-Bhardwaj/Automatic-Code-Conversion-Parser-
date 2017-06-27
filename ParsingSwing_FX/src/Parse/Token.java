package Parse;
/*
 * Author: Arnav Bhardwaj
 */
public class Token {
	private String swing; // tokens specified for both gui componets
	private String javaFX;
	private String jframeToJavaFx;
	private String importToJavaFX;
	
	//the constructor has the swing and fx  when created it can be used to compare the text values
	Token(String nswing, String njavaFX) {
		this.swing = nswing;
		this.javaFX = njavaFX;
	}
	
	Token(String n_swing, String n_javaFX,String n_jframeToJavaFx, String n_importToJavaFX) {
		this.swing = n_swing;
		this.javaFX = n_javaFX;
		this.jframeToJavaFx = n_jframeToJavaFx;
		this.importToJavaFX = n_importToJavaFX;
	}
	
	public String getSwing() { // used to compare to input file tokens
		return swing;
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
	
}
