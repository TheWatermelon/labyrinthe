import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** Menu de gestion de la simulation. */
public class SimulMenu {
	private Fenetre old = null;
	private JFrame fenetre = null;
	private JButton aleatoire;
	private JButton deterministe;
	private JButton auto;
	private JButton manuel;
	private JButton simuler;
	private JButton retour;
	private GridLayout gestionnaire;

	/**Constructeur prend en argument la fenetre du menu précédant.*/
	public SimulMenu(Fenetre oldF) {
		this.fenetre = new JFrame("Labyrinth");
		this.old = oldF;

		this.aleatoire = new JButton("aléatoire");
		this.deterministe = new JButton("déterministe");
		
		this.auto = new JButton("auto");
		this.manuel = new JButton("manuel");
		
		this.simuler = new JButton("simuler");
		this.retour = new JButton("retour");
		
		this.gestionnaire = new GridLayout(6, 1);

		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fenetre.setSize(200,420);
		this.fenetre.setLocation(0, 30);
		this.fenetre.setLayout(gestionnaire);

		this.fenetre.add(aleatoire);
		this.fenetre.add(deterministe);
		this.fenetre.add(auto);
		this.fenetre.add(manuel);
		this.fenetre.add(simuler);
		this.fenetre.add(retour);
			
		ControleSimulMenu listener = new ControleSimulMenu(this.old, this.fenetre, aleatoire, deterministe, auto, manuel, simuler);
		this.aleatoire.addActionListener(listener);
		this.deterministe.addActionListener(listener);
		this.auto.addActionListener(listener);
		this.manuel.addActionListener(listener);
		this.simuler.addActionListener(listener);
		this.retour.addActionListener(listener);
		
		this.fenetre.setVisible(true);
		this.fenetre.setResizable(false);
	}



}
