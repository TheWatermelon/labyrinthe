import java.awt.*;
import java.io.*;

/** Cette classe est destinée a sauvegarder un niveau editer dans le format requit.*/
public class MapSaver{

	/**Prend en argument le fichier a sauvegarder,
	* la taille du niveau,
	* la posistion de Thésé,
	* la posistion de la sortie,
	* et le tableau des murs.
	*/
	MapSaver(File f, byte s, int t, int e, byte[] m){
		try{
			DataOutputStream destination = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
			try{
				destination.write(s);
				//these
				destination.writeShort(t);
				//exit
				destination.writeShort(e);
				//map
				int taille2 = s*s;
				if(taille2%8>0){
					taille2 += (8-(taille2)%8);
				}
				
				byte wall = 0;
				for( int i=0; i<taille2; i = i+8){
					wall = m[i];
					wall = (byte)((wall<<(byte)1) + m[i+1]);
					wall = (byte)((wall<<(byte)1) + m[i+2]);
					wall = (byte)((wall<<(byte)1) + m[i+3]);
					wall = (byte)((wall<<(byte)1) + m[i+4]);
					wall = (byte)((wall<<(byte)1) + m[i+5]);
					wall = (byte)((wall<<(byte)1) + m[i+6]);
					wall = (byte)((wall<<(byte)1) + m[i+7]);
					System.out.println(wall);
					destination.write(wall);
				}
			}catch(IOException ex){
				System.err.println("Erreur d'ecriture de "+f.getName()+"!");
			}
			try{
				destination.close();
			}catch(IOException ex){
				System.err.println("Erreur de fermeture de "+f.getName()+"!");
			}
		}catch(FileNotFoundException ex){
			System.err.println("Erreur d'ouverture fichier "+f.getName()+"!");
		}

	}

}
