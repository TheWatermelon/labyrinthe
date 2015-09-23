import java.awt.event.*;
import javax.swing.*;

public class ControleurClavier implements KeyListener {
    private Algorithme monAlgo;
    private int count;
    private ControleSimulMenu menu;

    public ControleurClavier(Algorithme a) {
	this.monAlgo = a;
	this.count = 0;
    }

    /**
     * Deplace Thesee lorsqu'on appuie sur une touche
    */
    @Override
    public void keyPressed(KeyEvent e) {
	if(this.monAlgo.getAlgoType()){
		if(this.monAlgo.deterministe()){
			JOptionPane.showMessageDialog(null, this.count+" coups");
		}
		this.monAlgo.presDeterministe();
		if(this.monAlgo.realisable()){
		    JOptionPane.showMessageDialog(null , "Je n'est pas trouver de sortie");
		}

	}else{
		if(this.monAlgo.stepSimulationManuel()){
			JOptionPane.showMessageDialog(null, this.count+" coups");
			this.monAlgo.doClose();
		}
	}
	count++;
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
