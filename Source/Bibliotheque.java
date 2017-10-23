import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Math;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Set;


public class Bibliotheque{

	public static Vecteurs[] vec;
	public int size;
	public static int taille;
	public final String filename;
	public static HashMap<String, Integer> DicoVecteurs = new HashMap<String, Integer>();
	
	/** Constructeurs */
	/** Biliotheque vide */
	public Bibliotheque(String fn, int t){
		this.vec = new Vecteurs[t];  
		this.size = 0;
		this.filename = fn;
	}
	
	/** Bibliotheque pleine*/
	public Bibliotheque(Vecteurs[] v, String fn){
		this.vec = v;
		this.size = 0;
		this.filename = fn;
	} 
	
	/** Fonctions Utiles*/
	/** getSize()*/
	public int getSize(){
		return this.size;
	}
	
	/** tousLesVecteurs()
	 *  Imprime la bibliotheque de vecteurs */
	public void tousLesVecteurs(){ 
        for (int i = 0; i < size; i++){
            System.out.println(vec[i]);
        }
    }
    /** ajouterVecteur(Vecteurs)
     *  Ajoute un vecteur à la biliothèque */
    public void ajouterVecteur(Vecteurs v){ 
		this.vec[size++] = v;
		taille++;
	}
	
	/** tabToDict()
	 *  Crée un dictionnaire qui associe à chaque mot, son indice dans le tableau de Vecteurs (vec[]) --> Requetes Complexes */
	public void tabToDict() throws IOException{ 
		for(int i=0; i<taille; i++){
			DicoVecteurs.put(this.vec[i].label,i);
		}
	}
    
    /** restaure()
     *  créé une bibliotèque à partir du fichier .txt  */
    public void restaure() throws IOException{ 
		FileReader fr = new FileReader(this.filename);
		BufferedReader br = new BufferedReader(fr);
		String line; 
		while((line = br.readLine()) != null){
			String[] bfr = line.split(" ");
			if (bfr.length == 51){
				double[] vect = new double[50];
				String mot = bfr[0];
				for (int i = 0; i < vect.length; i++){
					int j = i+1;
					vect[i] = Double.parseDouble(bfr[j]);
				}
				Vecteurs v = new Vecteurs(mot,vect);
				this.ajouterVecteur(v);
			}
		}
	}
	
	/** Fonctions de trie  */
	/** quicksort(double[],int,int,String[])
	 *  Trie en meme temps les 50 vecteurs et leur mot associé */
	public static void quicksort(double[] tab, int g, int d,String[] mots) {
		if (tab == null || tab.length == 0){
			return;
		}
		if (d > g){
			int pospivot = pivoter(g,d,tab,mots);
			quicksort(tab, g, pospivot-1,mots);
			quicksort(tab,pospivot+1,d,mots);
		}
	}

	public static int pivoter(int g, int d,  double[] tab, String[] mots) {
		double pivot = tab[d];
		int pospivot = g;
		for (int i = g; i < d; i++){
			if (tab[i] <= pivot){
				double bfr = tab[i];
				tab[i] = tab[pospivot];
				tab[pospivot] = bfr;
				
				String bfrS = mots[i];
				mots[i] = mots[pospivot];
				mots[pospivot] = bfrS;
				
				pospivot++;
			}
		}
		double bfr = tab[pospivot];
		tab[pospivot] = tab[d];
		tab[d] = bfr;
		String bfrS = mots[pospivot];
		mots[pospivot] = mots[d];
		mots[d] = bfrS;
		return pospivot;
	}

	/** similariteK(Vecteurs,int)
	 *  calcul la similarité à un vecteur pour toute la bibliothèque
	 *  retourne les k plus similaires en utilisant la fonction quicksort*/
	public static void similaritek(Vecteurs un, int k) throws ExceptionOperation, LengthVectorException{
		double[] tab_sim = new double[taille];
		String[] mots = new String[taille];
		for(int i=0; i<taille; i++){
			tab_sim[i] = Vecteurs.similarite(un,vec[i]);
			mots[i] = vec[i].label;
		}
		quicksort(tab_sim, 0, tab_sim.length-1,mots);	
		for(int j = taille-1; j > taille-k -1; j--){
			System.out.print(tab_sim[j]+"  ");
			System.out.println(mots[j]);
		}
	}
	
}	
