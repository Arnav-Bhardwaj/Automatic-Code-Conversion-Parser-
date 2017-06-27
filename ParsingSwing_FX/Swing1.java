import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonToFx {
  public static void main(String[] args) {
	    
	     JPanel p1 = new JPanel();	    	     // create the button
	    // made another comment	    JButton jbtOK = new JButton("OK");	 
		// Add the button to the panel  	 	p1.add(jbtOK);  	    	    JFrame frame = new JFrame(); 	    	    frame.add(p1); 	    frame.setSize(200,50);	    frame.setTitle("MyFrameWithComponents");	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	    frame.setLocationRelativeTo(null);	    frame.setVisible(true);
  }
}