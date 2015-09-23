import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**Menu principale de "Labyrinthe".*/
public class MainMenu{
	private Fenetre fenetre = null;
	
	/**Mise en place de l'interface du menu.*/
	MainMenu(){
		fenetre = new Fenetre("Labyrinthe");
		fenetre.setResizable(false);
		JButton simuleButton = new JButton("simuler");
		JButton creeButton = new JButton("editer");
		JButton quiteButton = new JButton("quitter");
		
		ControleMainMenu listener = new ControleMainMenu(fenetre);

		quiteButton.addActionListener(listener);
		creeButton.addActionListener(listener);
		simuleButton.addActionListener(listener);
		
		GridLayout gestionnaire = new GridLayout(3, 1);
		gestionnaire.setVgap(20);
		fenetre.setLayout(gestionnaire);
		fenetre.add(simuleButton);
		fenetre.add(creeButton);
		fenetre.add(quiteButton);
		
		fenetre.setVisible(true);
	}

}
