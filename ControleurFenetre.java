import javax.swing.*;
import java.awt.event.*;

/* Ce contrôleur redimensionne la fenêtre en carré selon le la plus petite dimension */
public class ControleurFenetre implements ComponentListener {
	private JFrame fenetre = null;
	private int taille;

	/**
	 * Cree un controleur qui observe la fenetre
	*/
	public ControleurFenetre(JFrame f, int t) {
		super();
		this.fenetre = f;
		this.taille = t;
	}

	/**
	 * Redimensionne la fenetre en carre lorsque l'utilisateur la redimensionne
	*/
	@Override
	public void componentResized(ComponentEvent e) {
		if(this.fenetre.getWidth() > this.fenetre.getHeight()) {
			this.fenetre.setSize(this.fenetre.getHeight()+this.taille-(this.fenetre.getHeight()%this.taille), this.fenetre.getHeight()+this.taille-(this.fenetre.getHeight()%this.taille));
		}
		else if(this.fenetre.getHeight() > this.fenetre.getWidth()) {
			this.fenetre.setSize(this.fenetre.getWidth()+this.taille-(this.fenetre.getWidth()%this.taille), this.fenetre.getWidth()+this.taille-(this.fenetre.getWidth()%this.taille));
		}
	}
	@Override
	public void componentHidden(ComponentEvent e) {}
	@Override
	public void componentMoved(ComponentEvent e) {}
	@Override
	public void componentShown(ComponentEvent e) {} 
}
