import javax.swing.*;
import java.awt.*;

public class Outils {
	private JFrame fenetre;
	private JPanel panneau;
	private Case indication;
	private JButton reinitialiser;
	private JButton aleatoire;
	private JButton mur;
	private JButton chemin;
	private JButton perso;
	private JButton sortie;
	private JButton sauvegarder;
	private JButton retour;
	private GridLayout gestionnaire;
	private ControleurOutils contOutils;

	/**
	 * Cree une barre d'outils
	*/
	public Outils(Grille g, Fenetre old) {
		this.fenetre = new JFrame("Outils");
		this.panneau = new JPanel();
		this.indication = new Case((byte) 0);
		this.reinitialiser = new JButton("Réinitialiser");
		this.aleatoire = new JButton("Aléatoire");	
		this.mur = new JButton("Chemin");
		this.chemin = new JButton("Mur");
		this.perso = new JButton("Perso");
		this.sortie = new JButton("Sortie");
		this.sauvegarder = new JButton("Sauvegarder");
		this.sauvegarder.setEnabled(false);
		this.retour = new JButton("Retour");
		this.gestionnaire = new GridLayout(9, 1);
		this.contOutils = new ControleurOutils(indication, g, old, sauvegarder);

		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setSize(200,420);
		fenetre.setLocation(0, 30);
		fenetre.setLayout(gestionnaire);

		reinitialiser.addActionListener(contOutils);
		aleatoire.addActionListener(contOutils);
		chemin.addActionListener(contOutils);
		mur.addActionListener(contOutils);
		perso.addActionListener(contOutils);
		sortie.addActionListener(contOutils);
		sauvegarder.addActionListener(contOutils);
		retour.addActionListener(contOutils);
		((FlowLayout)panneau.getLayout()).setVgap(0);
		panneau.add(indication);
		fenetre.add(panneau);
		fenetre.add(reinitialiser);
		fenetre.add(aleatoire);
		fenetre.add(chemin);
		fenetre.add(mur);
		fenetre.add(perso);
		fenetre.add(sortie);
		fenetre.add(sauvegarder);
		fenetre.add(retour);

		fenetre.setVisible(true);
	}

	/**
	 * Renvoie le controleur des boutons
	*/
	public ControleurOutils getControleur() {
		return this.contOutils;
	}

	/**
	 * Ferme la fenetre
	*/
	public void setClosed(){
		this.fenetre.dispose();
	}
}
