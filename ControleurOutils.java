import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ControleurOutils implements ActionListener {
	private byte etat=0;
	private Case indication;
	private Grille maGrille;
	private Fenetre oldFenetre = null;
	private JButton sauvegarder;

	/**
	 * Cree un controleur qui observe la barre d'outils
	 */
	public ControleurOutils(Case c, Grille g, Fenetre old, JButton s) {
		super();
		this.indication = c;
		this.maGrille = g;
		this.oldFenetre = old;
		this.sauvegarder = s;
	}

	/**
	 * Renvoie l'etat du controleur (meme etat qu'une case)
	 */
	public byte getEtat(){
		return this.etat;
	}

	/**
	 * Effectue differentes actions en fonction du bouton que l'on clique
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Réinitialiser")){
			this.maGrille.viderTabCases();
		}
		else if(e.getActionCommand().equals("Aléatoire")){
			this.maGrille.remplirAleatTabCases();
		}
		else if(e.getActionCommand().equals("Chemin")) {
			this.etat = (byte) 0;
			this.indication.setEtat((byte) 0);
		}
		else if(e.getActionCommand().equals("Mur")) {
			this.etat = (byte) 1;
			this.indication.setEtat((byte) 1);
		}
		else if(e.getActionCommand().equals("Perso")) {
			this.etat = (byte) 2;
			this.indication.setEtat((byte) 2);
		}
		else if(e.getActionCommand().equals("Sortie")) {
			this.etat = (byte) 3;
			this.indication.setEtat((byte) 3);
		}
		else if(e.getActionCommand().equals("Sauvegarder")) {
			/* appel de la methode mapSaver */	
			JFileChooser chooser = new JFileChooser("./MAP/");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Labyrinthe file (.lab)", "lab");
			chooser.setFileFilter(filter);
			int returnval = chooser.showOpenDialog(null);
			if(returnval == JFileChooser.APPROVE_OPTION){
				System.out.println("You chose to save this file: "+chooser.getSelectedFile().getName());
				MapSaver map = new MapSaver(chooser.getSelectedFile(), (byte)this.maGrille.getTaille(), this.maGrille.getPosThesee(), this.maGrille.getPosSortie(), this.maGrille.getTabCases());
			}else{
				System.out.println("saving file aborded!");
			}
		}
		else if(e.getActionCommand().equals("Retour")) {
			/* retour au menu precedent */
			this.oldFenetre.setVisible(true);
			this.oldFenetre.repaint();
			this.maGrille.setClosed();
		}
		if(maGrille.estValide()) {
			this.sauvegarder.setEnabled(true);
		} else {
			this.sauvegarder.setEnabled(false);
		}
	}
}
