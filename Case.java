import javax.swing.*;
import java.awt.*;

    /** L'etat d'une case peut etre : 	0 -> chemin, 
     *        							1 -> mur, 
     *        							2 -> perso, 
     *        							3 -> sortie 
	*/
public class Case extends JComponent {
    private byte etat;
    private ControleurOutils contOutils;
    private ControleurCase contCase;
    private byte presEtat = 5;
	/**
	 * Ce constructeur sert a creer la case d'indication dans la barre d'outils
	*/
    public Case(byte etat) {
	super();
	this.etat = etat;

	this.setBackground(Color.WHITE);
	this.setForeground(Color.BLACK);
	this.setOpaque(true);
	this.setPreferredSize(new Dimension(50, 50));
	this.setSize(50, 50);
    }

	/**
	 * Ce constructeur sert pour toutes les cases de la grille
	*/
    public Case(byte etat, ControleurOutils o) {
	super();
	this.etat = etat;
	this.contCase = new ControleurCase(this);
	this.contOutils = o;

	this.setBackground(Color.WHITE);
	this.setForeground(Color.BLACK);
	this.setOpaque(true);
	this.setPreferredSize(new Dimension(50, 50));
	this.setSize(50, 50);
	this.addMouseListener(contCase);

    }

	/**
	 * Renvoie l'etat de la case
	*/
    public byte getEtat() {
	return this.etat;
    }

    /**
	 *  Defini l'etat de la case (utilisable par le programmeur) 
	*/
    public void setEtat(byte e) {
	this.etat = e;
	this.repaint();
    }

    /**
     * Defini l'etat de visualisation de la case
    */
    public void setPresEtat(byte e){
	    this.presEtat = e;
	    this.repaint();
    }

    /**
	 * Defini l'etat de la case (utilisable par le controleur)
	*/
    public void changerEtat() {
	if(this.etat!=0){
	    this.etat = 0;
	}else{
	    this.etat = this.contOutils.getEtat();
	}
	this.repaint();
    }

	/**
	 * Renvoie le controleur de la case
	*/
    public ControleurCase getControleur() {
	return this.contCase;
    }

	/**
	 * Dessine une case
	*/
    @Override
    public void paintComponent(Graphics g) {
	Graphics g2 = g.create();

	if(this.etat == 1) {
	    g2.setColor(this.getForeground());
	}
	else if(this.etat == 0 || this.etat == 2){
	    g2.setColor(this.getBackground());
	}
	else if(this.etat == 3) {
	    g2.setColor(Color.GREEN);	
	}
	if(this.getWidth() < this.getHeight())
	    g2.fillRect(0, 0, this.getWidth()-1, this.getWidth()-1);
	else
	    g2.fillRect(0, 0, this.getHeight()-1, this.getHeight()-1);

	g2.setColor(this.getForeground());
	/* choix de la largeur pour taille pour la beaute du mot */
	/* la hauteur est disponible ici */
	/* if(this.getWidth() < this.getHeight()) {
	   g2.drawRect(0, 0, this.getWidth()-1, this.getWidth()-1);
	   if(this.etat == 2) {
	   g2.drawOval(5, 5, this.getWidth()-11, this.getWidth()-11);	
	   }
	   } */
	g2.drawRect(0, 0, this.getWidth()-1, this.getWidth()-1);
	if(this.etat == 2) {
	    g2.drawOval(5, 5, this.getWidth()-11, this.getWidth()-11);
	    g2.drawLine(this.getWidth() /4, this.getWidth() /2, this.getWidth()/4 + this.getWidth()/2, this.getWidth() /2);
	}
	else if(this.etat == 3) {
	    g2.drawArc(5, 5, (this.getWidth()-11), (this.getWidth()-11)*2, 0, 180);
	}
	
	g2.setColor(Color.RED);
	if(this.presEtat == 0){ //cibler NORD
		g2.drawLine(this.getWidth()/2, this.getWidth()/2, (this.getWidth())/2, 0);

	}else if(this.presEtat == 1){ //cibler EST
		g2.drawLine(this.getWidth()/2, this.getWidth()/2, this.getWidth()-1, this.getWidth()/2);

	}else if(this.presEtat == 2){ //cibler SUD
		g2.drawLine(this.getWidth()/2, this.getWidth()/2, (this.getWidth())/2, this.getWidth()-1);

	}else if(this.presEtat == 3){ //cibler WEST
		g2.drawLine(this.getWidth()/2, this.getWidth()/2, 0+1, this.getWidth()/2);

	}else if(this.presEtat == 4){ //cibler
		g2.drawOval(5, 5, this.getWidth()-11, this.getWidth()-11);

	}else if(this.presEtat == 5){ //fin ciblage
		//null
	}

    }
}
