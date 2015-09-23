import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.*;

/** Controle du menu de gestion de simulation (gestion d'intéraction avec l'interface). */
public class ControleSimulMenu implements ActionListener {
	private Fenetre oldFenetre = null;
	private JButton aleatoire;
	private JButton deterministe;
	private JButton auto;
	private JButton manuel;
	private JButton simuler;
	private boolean algo;
	private boolean mod;
	private JFrame fenetre = null;

	/** Controleur du menu de simulation.*/
	public ControleSimulMenu(Fenetre old, JFrame f, JButton alea, JButton deter, JButton aut, JButton man, JButton sim) {
		super();
		this.oldFenetre = old;
		this.fenetre = f;
		this.aleatoire = alea;
		this.deterministe = deter;
		this.auto = aut;
		this.manuel = man;
		this.simuler = sim;
		this.algo = false;
		this.mod = false;
		this.aleatoire.setEnabled(false);
		this.auto.setEnabled(false);
	}
	
	/**Gestion des etats buttons.*/
	@Override
	public void actionPerformed(ActionEvent e) {
		int count;
		String name = ((JButton)e.getSource()).getText();
		if(name.equals("aléatoire")){
			this.deterministe.setEnabled(true);
			this.aleatoire.setEnabled(false);
			this.algo = false;
		}else if(name.equals("déterministe")){
			this.deterministe.setEnabled(false);
			this.aleatoire.setEnabled(true);
			this.algo = true;
		}else if(name.equals("auto")){
			this.manuel.setEnabled(true);
			this.auto.setEnabled(false);
			this.mod = false;
		}else if(name.equals("manuel")){
			this.manuel.setEnabled(false);
			this.auto.setEnabled(true);
			this.mod = true;
		}else if(name.equals("simuler")){
			JFileChooser chooser = new JFileChooser("./MAP/");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Labyrinthe file (.lab)", "lab");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " +
				chooser.getSelectedFile().getName());
				MapLoader data = new MapLoader(chooser.getSelectedFile());
				Grille map = new Grille(data.getSize(), data.getMap(), this.algo, this.mod);
				if(!this.mod){
					count = map.lancerSimulationAuto();
					if(count > 0){
						JOptionPane.showMessageDialog(null, count+" coups");
					}else{
						JOptionPane.showMessageDialog(null, "Je n'est pas trouver de sortie");
					}
				}
			}else{
				System.out.println("loding file failed!");
			}

			System.out.println("algo: "+this.algo+" mod: "+this.mod);	
		}else if(name.equals("retour")){
			this.oldFenetre.setVisible(true);
			this.oldFenetre.repaint();
			this.fenetre.dispose();	
		}
	}

}
