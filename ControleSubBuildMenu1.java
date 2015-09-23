import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/** controleur du sous menu d'édition de niveaux (getion des interaction avec l'interface). */
public class ControleSubBuildMenu1 implements ActionListener{
	private Fenetre mainFenetre = null;
	private Fenetre fenetre = null;
	ControleSubBuildMenu1(Fenetre main, Fenetre menu){
		super();
		mainFenetre = main;
		fenetre = menu;
	}
	
	/** Gestion de evenement de l'interface.*/
	@Override
	public void actionPerformed(ActionEvent e){
		String name = ((JButton)e.getSource()).getText();
		if(name.equals("retour")){
			this.mainFenetre.setVisible(true);
			this.mainFenetre.setLocation(this.fenetre.getLocationOnScreen());
			this.fenetre.dispose();
		}else if(name.equals("charger")){
			JFileChooser chooser = new JFileChooser("./MAP/");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Labyrinthe file (.lab)", "lab");
			chooser.setFileFilter(filter);
			Fenetre tmp = new Fenetre("Choisir fichier");
			tmp.setLocation(this.fenetre.getLocationOnScreen());
			int returnVal = chooser.showOpenDialog(tmp);
			this.fenetre.dispose();
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " +
						chooser.getSelectedFile().getName());
				MapLoader data = new MapLoader(chooser.getSelectedFile());
				Grille map = new Grille(data.getSize(), data.getMap(), this.mainFenetre);
				//Outils tool = new Outils(map);
			}else{
				System.out.println("loding file failed!");
				this.fenetre.setVisible(true);
			}
		}else if(name.equals("nouveau")){
			this.fenetre.setVisible(false);
			String dial = JOptionPane.showInputDialog(null, "Choisissez la taille ...");
			try{
				Grille map = new Grille(Integer.parseInt(dial), this.fenetre);
			}catch(NumberFormatException t){
				this.fenetre.setVisible(true);
			}
		}
	}
}
