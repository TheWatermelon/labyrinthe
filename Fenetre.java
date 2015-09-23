import javax.swing.*;
import java.awt.*;

/** La calsse Fenetre est destinner a éviter les repétition dans le code.  */
public class Fenetre extends JFrame{
	Fenetre(String s){
		super(s);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setSize(300, 400);
		this.setLocation(200, 200);
	}
}
