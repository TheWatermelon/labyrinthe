import java.awt.event.*;

public class ControleurCase implements MouseListener {
	private Case maCase;

	/**
	 * Cree un controleur qui observe une case
	*/
	public ControleurCase(Case c) {
		this.maCase = c;
	}

	/**
	 * Change l'etat de la case lorsqu'on clique dessus
	*/
	@Override
	public void mouseClicked(MouseEvent e) {
		this.maCase.changerEtat();	
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
