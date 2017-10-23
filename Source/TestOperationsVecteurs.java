import java.io.IOException;

public class TestOperationsVecteurs{

	/* Teste les fonctions mathématiques définies dans la classe Vecteurs à partir du fichier vecs50.txt */
	
	public static void main(String[] args) throws ExceptionOperation, LengthVectorException{
		Bibliotheque bibli = new Bibliotheque("vecs50.txt", 200000);
		try{
			bibli.restaure();
		}
		catch(IOException ioe){
			System.out.println(ioe);
		}
		
		System.out.println("addition du premier et du second vecteur : " + Vecteurs.addition(bibli.vec[0],bibli.vec[1]));
		System.out.println("soustraction du premier et du second vecteur : " + Vecteurs.soustraction(bibli.vec[0],bibli.vec[1]));
		System.out.println("multiplication scalaire du premier vecteur avec le scalaire 3.2 : " + Vecteurs.multiplicationScalaire(bibli.vec[0],3.2));
		System.out.println("produit scalaire du premier et du second vecteur : " + Vecteurs.produitScalaire( bibli.vec[0],bibli.vec[1]));
		System.out.println("norme du premier vecteur : "+ Vecteurs.norme(bibli.vec[0]));
		System.out.println("distance entre le premier et le second vecteur : "+ Vecteurs.distance( bibli.vec[0], bibli.vec[1]));
		System.out.println("similarité cosinus entre le premier et le second vecteur : "+ Vecteurs.similarite(bibli.vec[0],bibli.vec[1]));
		System.out.println("similarité cosinus entre un vecteur et ses k similaires : ");
		Bibliotheque.similaritek(bibli.vec[1996],10);
	}
}