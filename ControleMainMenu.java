import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

/**Controle de l'interface du menu pricipale.*/
public class ControleMainMenu implements ActionListener{
	private Fenetre fenetre;
	ControleMainMenu(Fenetre f){
		super();
		this.fenetre = f;
	}

	/**Gestion des actions boutons.*/
	@Override
		public void actionPerformed(ActionEvent e){
			String name = ((JButton)e.getSource()).getText();
			if(name.equals("quitter")){
				System.exit(0);
			}else if(name.equals("editer")){
				SubBuildMenu1 sub1 = new SubBuildMenu1(this.fenetre);
				this.fenetre.setVisible(false);
			}else{
				SimulMenu simul = new SimulMenu(this.fenetre);
				this.fenetre.setVisible(false);

		}
		}
}
