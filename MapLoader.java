import java.awt.*;
import javax.swing.*;
import java.io.*;

/** Gestion de chargement de fichier .lab retourne la taille et la map. */
public class MapLoader{
	private byte taille;
	private byte[] map;
	private File file;
	
	/** Prend en argument le fichier a charger et en extrait le donnée (taille, position thésé, sortie et des murs).*/
	MapLoader(File s){
		this.file = s;
		try{
			
			FileInputStream fichier = new FileInputStream(file);
			BufferedInputStream bufSource = new BufferedInputStream(fichier);
			DataInputStream source = new DataInputStream(bufSource);

		try{
			 byte ltese, ctese, lexit, cexit;

			this.taille = source.readByte();
			int taille2 = this.taille*this.taille;
			if(taille2%8 >0){
				taille2 += 8-taille2%8;
			}
			
			
			map = new byte[taille2];
			ltese = source.readByte();
			ctese = source.readByte();
			lexit = source.readByte();
			cexit = source.readByte();

			for(int i=0; i<taille2; i = i+8){
				byte wall = source.readByte();
				this.map[i]= (byte)((wall>>7) & 0x1);
				this.map[i+1]= (byte)((wall>>6) & 0x1);
				this.map[i+2]= (byte)((wall>>5) & 0x1);
				this.map[i+3]= (byte)((wall>>4) & 0x1);
				this.map[i+4]= (byte)((wall>>3) & 0x1);
				this.map[i+5]= (byte)((wall>>2) & 0x1);
				this.map[i+6]= (byte)((wall>>1) & 0x1);
				this.map[i+7]= (byte)(wall & 0x1);
			}
			
			map[this.taille*cexit+lexit] = 3; //sortie
			map[this.taille*ctese+ltese] = 2; //tese
		}catch(IOException e){
			System.err.println("erreur lecture de "+this.file.getName()+" !");
		}
		try{
			source.close();
		}catch(IOException e){
			System.err.println("erreur de fermeture de "+this.file.getName()+" !");
		}
		}catch(FileNotFoundException e){
			System.err.println("erreur d'ouverture de "+this.file.getName()+" !");
		}
	}
	
	/**Premet de récupérer la taille de la grille a construire.*/
	public byte getSize(){
		return this.taille;
	}

	/**Permet de récupérer les murs ainssi que la position de Thésé et de la sortie.*/
	public byte[] getMap(){
		return this.map;
	}

	/**Permet de récupérer le nom du fichier d'ou les information on été extraites.*/
	public String getFileName(){
		return this.file.getName();
	}
}
			

