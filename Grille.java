import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Grille extends JPanel {
	private Fenetre fenetre;
	private int taille;
	private GridLayout gestionnaire;
	private Case[] tab_orig=null;
	private Case[] tab_cases=null;
	private byte[] tab_grille=null;
	private Outils mesOutils;
	private ControleurOutils contOutils;
	private Algorithme monAlgo;

	/**
	 * Cree une grille de cases de la dimension t*t
	*/
	public Grille(int t, Fenetre old) {
		super();
		this.fenetre = new Fenetre("Labyrinthe");
		this.taille = t;
		this.gestionnaire = new GridLayout(this.taille, this.taille);
		this.setLayout(gestionnaire);
		this.tab_cases = new Case[this.taille*this.taille+(8-(this.taille*this.taille)%8)];
		this.mesOutils = new Outils(this, old);
		this.contOutils = mesOutils.getControleur();

		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				this.tab_cases[j*this.taille+i] = new Case((byte) 0, contOutils);
				this.add(tab_cases[j*this.taille+i]);
			}
		}
		fenetre.add(this);
		fenetre.addComponentListener(new ControleurFenetre(fenetre, this.taille));
		fenetre.setVisible(true);
	}

	/**
	 * Cree une grille de la dimension t*t, en chargeant les etats du tableau tab, avec la memoire de la fenetre qui l'appelle
	*/
	public Grille(int t, byte[] tab, Fenetre old) {
		super();
		this.fenetre = new Fenetre("Labyrinthe");
		this.taille = t;
		this.gestionnaire = new GridLayout(this.taille, this.taille);
		this.setLayout(gestionnaire);
		this.tab_cases = new Case[this.taille*this.taille];
		this.tab_grille = tab;
		this.mesOutils = new Outils(this, old);
		this.contOutils = mesOutils.getControleur();
		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				this.tab_cases[j*this.taille+i] = new Case(tab_grille[j*this.taille+i], contOutils);
				this.add(tab_cases[j*this.taille+i]);
			}
		}
		fenetre.add(this);
		fenetre.addComponentListener(new ControleurFenetre(fenetre, this.taille));
		fenetre.setVisible(true);
	}

	/**
	 * Cree une grille de la dimension t*t, en chargeant les etats du tableau tab, avec la memoire de la fenetre qui l'appelle
	 * Ainsi que l'implementation de l'algorithme (deterministe/aleat et manuel/auto)
	*/
	public Grille(int t, byte[] tab, boolean a, boolean m) {
		super();
		this.fenetre = new Fenetre("Labyrinthe");
		this.fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.taille = t;
		this.gestionnaire = new GridLayout(this.taille, this.taille);
		this.setLayout(gestionnaire);
		this.tab_cases = new Case[this.taille*this.taille];
		this.tab_orig = new Case[this.taille*this.taille];
		this.tab_grille = tab;
		this.monAlgo = new Algorithme(this, a, m);

		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				this.tab_orig[j*this.taille+i] = new Case(tab_grille[j*this.taille+i]);
				this.tab_cases[j*this.taille+i] = new Case(tab_grille[j*this.taille+i]);
				this.add(tab_cases[j*this.taille+i]);
			}
		}

		fenetre.add(this);
		fenetre.addComponentListener(new ControleurFenetre(fenetre, this.taille));
		if(m) {
			/* Manuel */
			this.fenetre.addKeyListener(new ControleurClavier(this.monAlgo));
			fenetre.setVisible(true);
		} else if(!m) {
			/* Auto */
			fenetre.setVisible(false);
		}
	}

	/**
	 * Lance la simulation de l'algorithme
	*/
	public int lancerSimulationAuto() {
		return this.monAlgo.lancerSimulationAuto();	
	}

	/**
	 * Restitue les etats de toutes les cases de tab_cases selon celles de tab_orig
	*/
	public void restoTabCases() {
		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				this.tab_cases[j*this.taille+i].setEtat(tab_orig[j*this.taille+i].getEtat());
			}
		}
	}

	/**
	 * "Vide" (met l'etat de toute les cases a 0) la grille
	*/
	public void viderTabCases() {
		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				this.tab_cases[j*this.taille+i].setEtat((byte) 0);
				this.tab_cases[j*this.taille+i].repaint();
			}
		}
	}


	/** 
	 * "Remplit" (change l'etat des cases) aleatoirement le tableau de cases avec des murs, place Thesee et une sortie 
	*/
	public void remplirAleatTabCases() {
		Random rnd = new Random();
		int ligneThesee, ligneSortie, colonneThesee, colonneSortie;

		/* Generation des murs */
		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				if(rnd.nextInt(3) == 1) {
					this.tab_cases[j*this.taille+i].setEtat((byte) 1);
				} else {
					this.tab_cases[j*this.taille+i].setEtat((byte) 0);
				}
				this.tab_cases[j*this.taille+i].repaint();
			}
		}
		/* Generation de Thesee et de la Sortie */
		do {
			ligneThesee = rnd.nextInt(this.taille);
			colonneThesee = rnd.nextInt(this.taille);
			ligneSortie = rnd.nextInt(this.taille);
			colonneSortie = rnd.nextInt(this.taille);
		} while ((ligneThesee == ligneSortie) && (colonneThesee == colonneSortie));
		this.tab_cases[colonneThesee*this.taille+ligneThesee].setEtat((byte) 2);
		this.tab_cases[colonneSortie*this.taille+ligneSortie].setEtat((byte) 3);
	}

	/**
	 * Renvoie un tableau d'octets qui contient les chemins et les murs
	*/
	public byte[] getTabCases() {
		int taille2 = this.taille*this.taille;
		if(taille2%8>0){
			taille2 += 8-(taille2%8);
		}
		byte[] tabCases = new byte[taille2];

		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				tabCases[j*this.taille+i] = this.tab_cases[j*this.taille+i].getEtat();
				if((this.tab_cases[j*this.taille+i].getEtat() == 2) || (this.tab_cases[j*this.taille+i].getEtat() == 3)) {
					tabCases[j*this.taille+i] = (byte) 0;
				}
			}
		}
		System.out.println("return");
		return tabCases;
	}

	/**
	 * Renvoie la position de Thesee sous la forme d'un entier dont les deux octets de poids faibles sont respectivement la ligne et la colonne
	*/
	public int getPosThesee() {
		int pos = 0;

		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				if(tab_cases[i*this.taille+j].getEtat() == 2) {
					pos = j<<8;
					pos = pos | i;
				}
			}
		}
		return pos;
	}

	/**
	 * Rnevoie la posiiton de la sortie sous la forme d'un entier dont les deux octets de poids faibles sont respectivement la ligne et la colonne
	*/
	public int getPosSortie() {
		int pos = 0;

		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				if(tab_cases[i*this.taille+j].getEtat() == 3) {
					pos = j<<8;
					pos = pos | i;
				}
			}
		}
		return pos;
	}

	/**
	 * Renvoie le nombre de cases en hauteur (ou en largeur) de la grille
	*/
	public int getTaille() {
		return this.taille;
	}

	/**
	 * Renvoie vrai si la grille contient un Thesee et une sortie
	*/
	public boolean estValide() {
		boolean valid=false;
		int thesee=0, sortie=0;
		
		for(int i=0; i<this.taille; i++) {
			for(int j=0; j<this.taille; j++) {
				if(this.tab_cases[j*this.taille+i].getEtat() == 2) {
					thesee++;
				} else if(this.tab_cases[j*this.taille+i].getEtat() == 3) {
					sortie++;
				}
				
			}
		}
		
		if(thesee == 1 && sortie == 1) {
			valid=true;
		}
		return valid;
	}

	/**
	 * Effectue une previsualisation du mouvement de Thesee, la direction depend de la valeur de dir,
	 * 0 -> NORD
	 * 1 -> EST
	 * 2 -> SUD
	 * 3 -> OUEST
	*/
	public void presDeplacerThesee(int dir){
			for(int i=0; i<this.taille; i++){
				for(int j=0; j<this.taille; j++){
					this.tab_cases[j*this.taille+i].setPresEtat((byte) 5);
				}
			}
			for(int i=0; i<this.taille; i++){
			for(int j=0; j<this.taille; j++){
				if(this.tab_cases[j*this.taille+i].getEtat() == 2){
				
					if(dir == 0){
							this.tab_cases[j*this.taille+i].setPresEtat((byte) 0);
						if(i-1>=0){
							this.tab_cases[j*this.taille+(i-1)].setPresEtat((byte) 4);
						}
					}else if(dir == 1){
							this.tab_cases[j*this.taille+i].setPresEtat((byte) 1);
						if(j+1<this.taille){
							this.tab_cases[(j+1)*this.taille+i].setPresEtat((byte) 4);
						}
					}else if(dir == 2){
							this.tab_cases[j*this.taille+i].setPresEtat((byte) 2);
						if(i+1 <this.taille){
							this.tab_cases[j*this.taille+(i+1)].setPresEtat((byte) 4);
						}
					}else if(dir == 3){
							this.tab_cases[j*this.taille+i].setPresEtat((byte) 3);
						if(j-1 >=0){
							this.tab_cases[(j-1)*this.taille+i].setPresEtat((byte) 4);
						}
					}
				}
			}
		}
	}

	/**
	 * Deplace Thesee en fonction de la valeur de dir,
	 * 0 -> NORD
	 * 1 -> EST
	 * 2 -> SUD
	 * 3 -> OUEST
	*/
	public int deplacerThesee(int dir){
		for(int i=0; i<this.taille; i++){
			for(int j=0; j<this.taille; j++){
				if(this.tab_cases[j*this.taille+i].getEtat() == 2){

					if(dir == 0){
						if(i-1 >=0){
							if(tab_cases[j*this.taille+(i-1)].getEtat() != 1){
								this.tab_cases[j*this.taille+(i-1)].setEtat((byte) 2);
								this.tab_cases[j*this.taille+i].setEtat((byte) 0);
								return 1;
							}else{
								return 0;
							}
						}else{
							return 0;
						}
					}else if(dir == 1){
						if(j+1<this.taille){
							if(tab_cases[(j+1)*this.taille+i].getEtat() != 1){
								this.tab_cases[(j+1)*this.taille+i].setEtat((byte) 2);
								this.tab_cases[j*this.taille+i].setEtat((byte) 0);
								return 1;
							}else{
								return 0;
							}
						}else{
							return 0;
						}
					}else if(dir == 2){
						if(i+1 <this.taille){
							if(tab_cases[j*this.taille+(i+1)].getEtat() != 1){
								this.tab_cases[j*this.taille+(i+1)].setEtat((byte) 2);
								this.tab_cases[j*this.taille+i].setEtat((byte) 0);
								return 1;
							}else{
								return 0;
							}
						}else{
							return 0;
						}
					}else if(dir == 3){
						if(j-1 >=0){
							if(tab_cases[(j-1)*this.taille+i].getEtat() != 1){
								this.tab_cases[(j-1)*this.taille+i].setEtat((byte) 2);
								this.tab_cases[j*this.taille+i].setEtat((byte) 0);
								return 1;
							}else{
								return 0;
							}
						}else{
							return 0;
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Ferme la fenetre
	*/
	public void setClosed(){
		this.fenetre.dispose();
		this.mesOutils.setClosed();
	}

	/**
	 * Ferme la fenêtre lors d'une simulation pour relancer une nouvelle grille
	*/
	public void doClose(){
		this.fenetre.dispose();
	}
} 
