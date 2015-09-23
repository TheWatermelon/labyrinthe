
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** Sous menu de selection de l'édition de niveaux. */
public class SubBuildMenu1{
	private Fenetre MainFenetre = null;
	private Fenetre fenetre = null;
	/** Prend en argument une Fenetre (la fenetre du menu précédant).*/
	SubBuildMenu1(Fenetre f){
		this.MainFenetre = f;
		
		this.fenetre = new Fenetre("Labyrinthe");
		this.fenetre.setLocation(this.MainFenetre.getLocationOnScreen());
		this.fenetre.setResizable(false);
		this.MainFenetre.setVisible(false);
		
		GridLayout gestionnaire = new GridLayout(3, 1);
		gestionnaire.setHgap(20);
		this.fenetre.setLayout(gestionnaire);

		JButton newButton = new JButton("nouveau");
		JButton loadButton = new JButton("charger");
		JButton retourButton = new JButton("retour");
	
		ControleSubBuildMenu1 listener = new ControleSubBuildMenu1(MainFenetre, fenetre);

		newButton.addActionListener(listener);
		loadButton.addActionListener(listener);
		retourButton.addActionListener(listener);

		this.fenetre.add(newButton);
		this.fenetre.add(loadButton);
		this.fenetre.add(retourButton);

		this.fenetre.setVisible(true);
	}
}
