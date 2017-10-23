import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Math;

public class Vecteurs{

	public String label;
	public double[] ve;
	
	/** Constructeurs */
	/** Vecteur tout court sans nombres*/
	public Vecteurs(){
		this.label = "Defaut";
		this.ve = new double[50];
	}
	/** Vecteur de Mot sans nombres*/
	public Vecteurs(String s){
		this.label = s;
		this.ve = new double[50];
	}
	
	/** Vecteur de Mot avec nombres*/
	public Vecteurs(String s, double[] tab){
		this.label = s;
		this.ve = tab;
	}
	
	/** Fonction Utile */
	/** toString */
	public String toString(){
		assert ve != null : "Erreur: tableau de vecteurs == null";
		String res = "[";
		for (int i = 0; i < ve.length; i++){
            res += " " +ve[i];
        }
        res += "]";
		return label+ " "+ res;
		
	} 
	
	/** Fonctions sur les Vecteurs */
	/** addition(Vecteurs,Vecteurs)
	 *  renvoie un nouveau vecteur, résultat de l'addition des deux autres
	 *  Vérifie si les deux vecteurs ont la même taille
	 * */
	public static Vecteurs addition(Vecteurs un, Vecteurs deux) throws ExceptionOperation, LengthVectorException{
		if(un.ve.length != deux.ve.length){
			throw new LengthVectorException("erreur: les deux vecteurs n'ont pas la même longueur");
		}
		else{
			Vecteurs trois = new Vecteurs(); 
			for (int i = 0; i < un.ve.length ; i++){
				trois.ve[i] = un.ve[i] + deux.ve[i];
			}
			return trois;
		}
	}
	
	/** soustraction(Vecteurs,Vecteurs)
	 *  renvoie un nouveau vecteur, résultat de la soustraction des deux autres
	 *  Vérifie si les deux vecteurs ont la même taille
	 * */
	public static Vecteurs soustraction(Vecteurs un, Vecteurs deux) throws ExceptionOperation, LengthVectorException{
		if(un.ve.length != deux.ve.length){
			throw new LengthVectorException("erreur: les deux vecteurs n'ont pas la même longueur");
		}
		else{
			Vecteurs trois = new Vecteurs();
			for (int i = 0; i < un.ve.length ; i++){
				trois.ve[i] = un.ve[i] - deux.ve[i];
			}
			return trois;
		}
	}
	
	/** multiplicationScalaire(Vecteurs,double)
	 *  renvoie un nouveau vecteur, résultat de la multipliction scalaire d'un vecteur par un scalaire
	 *  Vérifie si le scalaire n'est pas égal à 1, si oui on le renvoie directement
	 * */
	public static Vecteurs multiplicationScalaire(Vecteurs un, double lambda) throws ExceptionOperation{ 
		if (lambda == 1.0){
			return un;
		}
		Vecteurs trois = new Vecteurs();// Problème : comment l'appeler, pour l'instant défaut
		for (int i = 0; i < un.ve.length ; i++){
			trois.ve[i] = un.ve[i] * lambda;
		}
		return trois;
	}
		
	/** produitScalaire(Vecteurs,Vecteurs)
	 *  renvoie le produit scalaire, résultat du produit de deux vecteurs
	 *  Vérifie si les deux vecteurs ont la même taille
	 * */	
	public static double produitScalaire(Vecteurs un, Vecteurs deux) throws ExceptionOperation, LengthVectorException{
		if(un.ve.length != deux.ve.length){
			throw new LengthVectorException("erreur: les deux vecteurs n'ont pas la même longueur");
		}
		else{
			double produit_scalaire = 0.0;
			for (int i = 0; i < un.ve.length ; i++){
				produit_scalaire += un.ve[i] * deux.ve[i];
			}
			return produit_scalaire;
		}
	}
	
	/** norme(Vecteurs)
	 *  renvoie la norme d'un vecteur, résultat du produit d'un vecteur avec lui-même
	 *  forcement positif car le produit scalaire d'un vecteur avec lui-même rend toutes les coordonnées au carré, donc positives
	 * */	
	public static double norme(Vecteurs un) throws ExceptionOperation, LengthVectorException{
		return Math.sqrt(produitScalaire(un,un));					
	}
	
	/** distance(Vecteurs,Vecteurs)
	 *  renvoie la distance entre deux vecteurs, résultat de la norme du résultat de la soustraction des deux vecteurs
	 *  Vérifie si les deux vecteurs ont la même taille
	 * */
	public static double distance(Vecteurs un, Vecteurs deux) throws ExceptionOperation, LengthVectorException{
		if(un.ve.length != deux.ve.length){
			throw new LengthVectorException("erreur: les deux vecteurs n'ont pas la même longueur");
		}
		else{
			return norme(soustraction(un,deux));
		}
	}

	/** similarite(Vecteurs,Vecteurs)
	 *  renvoie la similarité entre deux vecteurs, resultat du produit scalaire des deux vecteurs, divisé par le produit des normes des deux vecteurs
	 *  Vérifie si les deux vecteurs ont la même taille
	 * */
	public static double similarite(Vecteurs un, Vecteurs deux) throws ExceptionOperation, LengthVectorException{
		if(un.ve.length != deux.ve.length){
			throw new LengthVectorException("erreur: les deux vecteurs n'ont pas la même longueur");
		}
		else{
			return produitScalaire(un,deux)/(norme(un)*norme(deux));
		}
	}
}
