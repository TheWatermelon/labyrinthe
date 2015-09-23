import java.util.*;

public class Algorithme {
	private Grille maGrille;
	private boolean algo;
	private boolean mode;
	private int count;
	private byte nextDir;
	private Random rnd;
	private int[][] looked;
	private int counter;
	private int taille;
	private int x;
	private int y;


	public Algorithme(Grille g, boolean a, boolean m) {
		this.maGrille = g;
		this.algo = a;
		this.mode = m;
		this.count = 0;
		this.rnd = new Random();
		this.taille = this.maGrille.getTaille();
		this.looked = new int[this.taille*4][this.taille*4];
	}

	/**
	 * Lance la simulation selon la valeur des booleens donnes lors de la construction,
	 * algo = true -> deterministe
	 * algo = false -> aleatoire
	 * mode = true -> manuel
	 * mode = false -> automatique
	*/
	public int lancerSimulationAuto() {
		if(this.algo) {
				/* Deterministe et automatique */
				while(this.maGrille.estValide()) {
					this.deterministe();
					this.count++;
					if(this.realisable()) {
						return -1;
					}
				}
		}
		else if(!this.algo) {
				/* Aleatoire et automatique */
				for(int i=0; i<100; i++){
					while(this.maGrille.estValide()) {
				
					this.maGrille.deplacerThesee(this.rnd.nextInt(4));
						this.count++;
						if(count>(1<<21)-1){
							return -1;
						}
					}
					this.maGrille.restoTabCases();

				}
				this.count = this.count/100;
				
		}
		return this.count;
	}

	/**
	 * Effectue un mouvement aleatoire
	*/
	public boolean stepSimulationManuel(){
		//aleatoir manuel
		this.maGrille.deplacerThesee(nextDir);
		this.nextDir = (byte)this.rnd.nextInt(4);
		this.maGrille.presDeplacerThesee(nextDir);
		if(this.maGrille.estValide()){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Renvoie faux si la grille est realisable, vrai sinon
	*/
	public boolean realisable(){
		if(this.x == this.taille*2 && this.y == this.taille*2){
			if(this.looked[x][y-1] == -1){
					return false;
			}else if(this.looked[x+1][y] == -1){
					return false;
			}else if(this.looked[x][y+1] == -1){
					return false;
			}else if(this.looked[x-1][y] == -1){
					return false;
			}else{
				return true;
			}
		}
		return false;
	}

	/**
	 * Effectue un mouvement selon l'algorithme deterministe (voir algorigramme)
	*/
	public boolean deterministe(){
		if(this.counter == 0){
			for(int i=0; i<this.taille*4; i++){
				for(int j=0; j<this.taille*4; j++){
					this.looked[i][j] = -1;
				}
			}
			this.x = taille*2;
			this.y = taille*2;
			this.looked[x][y] = 1;
			this.counter = 1;
			return false;
		}else{
		this.counter++;

		/* NORD */
		if(this.looked[x][y-1] == -1){
			 /* Si c'est un mur */
			 if(this.maGrille.deplacerThesee(0) == 0){
				 this.looked[x][y-1] = -2;
				 return false;
			 }else{
				 /* Deplacement */
				 this.y--;
				 this.looked[x][y] = this.counter;
			 }
		/* EST */
		}else if(this.looked[x+1][y] == -1){
			/* Si c'est un mur */
			if(this.maGrille.deplacerThesee(1) == 0){
				this.looked[x+1][y] = -2;
				return false;
			}else{
				/* Deplacement */
				this.x++;
				this.looked[x][y] = this.counter;
			}
		/* SUD */
		}else if(this.looked[x][y+1] == -1){
			/* Si c'est un mur */
			if(this.maGrille.deplacerThesee(2) == 0){
				this.looked[x][y+1] = -2;
				return false;
			}else{
				/* Deplacement */
				this.y++;
				this.looked[x][y] = this.counter;
			}
		/* OUEST */
		}else if(this.looked[x-1][y] == -1){
			/* Si c'est un mur */
			if(this.maGrille.deplacerThesee(3) == 0){
				this.looked[x-1][y] = -2;
				return false;
			}else{
				/* Deplacement */
				this.x--;
				this.looked[x][y] = this.counter;
			}
		/* Si toutes les directions sont connues */
		}else{
			if(this.looked[x][y-1] > this.looked[x+1][y] && this.looked[x][y-1] > this.looked[x][y+1] && this.looked[x][y-1] > this.looked[x-1][y]){
				//nord
				this.looked[x][y] = 0;
				this.y--;
				this.maGrille.deplacerThesee(0);
			}else if(this.looked[x+1][y] > this.looked[x][y+1] && this.looked[x+1][y] > this.looked[x-1][y]){
				//est
				this.looked[x][y] = 0;
				this.x++;
				this.maGrille.deplacerThesee(1);
			}else if(this.looked[x][y+1] >this.looked[x-1][y]){
				//sud
				this.looked[x][y] = 0;
				this.y++;
				this.maGrille.deplacerThesee(2);
			}else{
				//ouest
				this.looked[x][y] = 0;
				this.x--;
				this.maGrille.deplacerThesee(3);
			}
			return false;
		}
		if(!this.maGrille.estValide()){
			return true;
		}
	}
	return false;
}




	/**
	 * Effectue une previsualisation du mouvement de Thesee en mode deterministe
	*/
	public void presDeterministe(){

		if(this.looked[x][y-1] == -1){
			this.maGrille.presDeplacerThesee(0);
		}else if(this.looked[x+1][y] == -1){
			this.maGrille.presDeplacerThesee(1);
		}else if(this.looked[x][y+1] == -1){
			this.maGrille.presDeplacerThesee(2);
		}else if(this.looked[x-1][y] == -1){
			this.maGrille.presDeplacerThesee(3);
		}else{
			if(this.looked[x][y-1] > this.looked[x][y+1]){ 
				if(this.looked[x][y-1] > this.looked[x+1][y]){
					if(this.looked[x][y-1] >this.looked[x-1][y]){ //si nord superieur
						this.maGrille.presDeplacerThesee(0);
					}else{ //si WEST superieur
						this.maGrille.presDeplacerThesee(3);
					}
				}else if(this.looked[x+1][y] > this.looked[x-1][y]){ //si EST superieur
					this.maGrille.presDeplacerThesee(1);
				}else { // si WEST superieur
					this.maGrille.presDeplacerThesee(3);
				}
			}else if(this.looked[x][y+1] > this.looked[x+1][y]){
				if(this.looked[x][y+1] > this.looked[x-1][y]){ //si SUD superieur
					this.maGrille.presDeplacerThesee(2);
				}else{ //si WEST superieur
					this.maGrille.presDeplacerThesee(3);
				}
			}else if(this.looked[x+1][y] > this.looked[x-1][y]){
				this.maGrille.presDeplacerThesee(1);
			}else{
				this.maGrille.presDeplacerThesee(3);
			}
			
		}
	}

	/**
	 * Renvoie le booleen algo
	*/
	public boolean getAlgoType() {
		return this.algo;
	}

	/**
	 * Renvoie le booleen mode
	*/
	public boolean getMode(){
		return this.mode;
	}

	/**
	 * Ferme la fenetre
	*/
	public void doClose(){
		this.maGrille.doClose();
	}
}
