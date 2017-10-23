import java.io.IOException;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;


public class WordEmbeddings{

	/** affiche un tableau de String avec sa taille */
	 public static void afficherTabS(String[] tab){
        assert tab != null : "Erreur: tab == null";
        System.out.print("[");
        for (int i = 0; i < tab.length; i++){
            System.out.println(i+" " + tab[i]);
        }
       System.out.println(" ] size=" + tab.length);
       System.out.println();
    }
    
    /** affiche un tableau de double avec sa taille */
    public static void afficherTabD(double[] tab){
        assert tab != null : "Erreur: tab == null";
        System.out.print("[");
        for (int i = 0; i < tab.length; i++){
            System.out.println(" " + tab[i]);
        }
        System.out.println(" ] size=" + tab.length);
    }
    
	public static void main(String[] args) throws ExceptionOperation, LengthVectorException{
		
		Bibliotheque bibli = new Bibliotheque(args[0], 200000);
		try{
			bibli.restaure();
		}
		catch(IOException ioe){
			System.out.println(ioe);
		}
		
		try{
			bibli.tabToDict();
		}
		catch(IOException ioe){
			System.out.println(ioe);
		}
				
		if (args.length != 0){
			/* requête complexe avec une seule commande, donc 11 arguments = > (exemple): vecs50.txt \( Londres + \( France - Paris \) \) 10 */
			if (args.length == 11){
				HashMap<String,Integer> DicoVecteurs = Bibliotheque.DicoVecteurs;
				StringBuilder chaine =  new StringBuilder() ;
				String mot5 = args[5]; // dans l'exemple : France
				String mot7 = args[7]; // dans l'exemple : Paris
				String mot2 = args[2]; // dans l'exemple : Londres
				Integer simi = Integer.valueOf(args[10]); /* Nombre de mots similaires que l'on souhaite, dans l'exemple : 10 */
				if (!DicoVecteurs.containsKey(mot5) || !DicoVecteurs.containsKey(mot7) || !DicoVecteurs.containsKey(mot2)){ /* on vérifie que tous les mots passés en arguments sont dans le fichier */
					if (!DicoVecteurs.containsKey(mot5)){
						System.out.println("Le mot "+mot5+" n'est pas dans notre liste de mots");
						}
					if (!DicoVecteurs.containsKey(mot7)){
						System.out.println("Le mot "+mot7+" n'est pas dans notre liste de mots");
						}
					if (!DicoVecteurs.containsKey(mot2)){
						System.out.println("Le mot "+mot2+ " n'est pas dans notre liste de mots");
						}
					System.out.println("Veuillez réessayer.");
					System.exit(-1);
				}
				Integer posMot5 = DicoVecteurs.get(mot5);
				Integer posMot7 = DicoVecteurs.get(mot7);
				Integer posMot2 = DicoVecteurs.get(mot2);
				Vecteurs soustr = Vecteurs.soustraction(bibli.vec[posMot5],bibli.vec[posMot7]); // un nouveau vecteur, soustraction des deux autres
				Vecteurs add = Vecteurs.addition(bibli.vec[posMot2],soustr);
				Echo.main(args);
				Bibliotheque.similaritek(add,simi);
			}
			else if (args.length == 3){ 
				/* cas de requêtes simples à partir d'un fichier, où le fichier comporte des mots et le nombre k de similarités que l'on veut pour chaque mot
						par exemple : java WordEmbeddings vecs50.txt requetessimplesK.txt KS */
				if (args[2].equals("KS")){
					HashMap<String,Integer> DicoVecteurs = Bibliotheque.DicoVecteurs;
					try {
						FileReader fr = new FileReader(args[1]);
						BufferedReader br = new BufferedReader(fr);
						String line;
						line = br.readLine(); //mot + simi
						Echo.main(args);
						while((line = br.readLine()) != null){
							String [] s = line.split(" ");
							int simi = Integer.valueOf(s[1]);
							Integer posMot = DicoVecteurs.get(s[0]);
							Vecteurs mot = bibli.vec[posMot];
							System.out.println(line);
							Bibliotheque.similaritek(mot,simi);
							System.out.println();
						}
					}
					catch(IOException ioe){
						System.out.println(ioe);
					}
				}
				/* cas de requêtes complexes à partir d'un fichier, où le fichier comporte des requêtes et le nombre k de similarités que l'on veut pour chaque requête
						par exemple : java WordEmbeddings vecs50.txt requetescomplexesK.txt KC */
				else if (args[2].equals("KC")){
					HashMap<String,Integer> DicoVecteurs = Bibliotheque.DicoVecteurs;
					try {
						FileReader fr = new FileReader(args[1]);
						BufferedReader br = new BufferedReader(fr);
						String line;
						line = br.readLine();
						Echo.main(args);
						while((line = br.readLine()) != null){
							String [] s = line.split(" ");
							String mot4 = s[4]; // France
							String mot6 = s[6]; // Paris
							String mot1 = s[1]; // Londres
							int simi = Integer.valueOf(s[9]);
							if (!DicoVecteurs.containsKey(mot4) || !DicoVecteurs.containsKey(mot6) || !DicoVecteurs.containsKey(mot1)){
								if (!DicoVecteurs.containsKey(mot4)){
									System.out.println("Le mot "+mot4+" n'est pas dans notre liste de mots");
									}
								if (!DicoVecteurs.containsKey(mot6)){
									System.out.println("Le mot "+mot6+" n'est pas dans notre liste de mots");
									}
								if (!DicoVecteurs.containsKey(mot1)){
									System.out.println("Le mot "+mot1+ " n'est pas dans notre liste de mots");
									}
								System.out.println("Veuillez réessayer.");
								System.exit(-1);
							}
							Integer posMot4 = DicoVecteurs.get(mot4);
							Integer posMot6 = DicoVecteurs.get(mot6);
							Integer posMot1 = DicoVecteurs.get(mot1);
							Vecteurs soustr = Vecteurs.soustraction(bibli.vec[posMot4],bibli.vec[posMot6]); // un nouveau vecteur, soustraction des deux autres
							Vecteurs add = Vecteurs.addition(bibli.vec[posMot1],soustr);
							System.out.println(line);
							Bibliotheque.similaritek(add,simi);
						}
					}
					catch(IOException ioe){
						System.out.println(ioe);
					}
				}
				/* cas de requête simple avec une seule commande => par exemple : vecs50.txt chat 10 */
				else{
					HashMap<String,Integer> DicoVecteurs = Bibliotheque.DicoVecteurs;
					String motS = args[1]; /* dans l'exemple : chat */
					Integer posMot = DicoVecteurs.get(motS);
					Vecteurs mot = bibli.vec[posMot];
					
					int simi = Integer.valueOf(args[2]); // 10
					if (!DicoVecteurs.containsKey(motS)){
						System.out.println("Le mot "+motS+" n'est pas dans notre liste de mots");
						System.out.println("Veuillez réessayer.");
						System.exit(-1);
					}
					Echo.main(args);
					Bibliotheque.similaritek(mot,simi);
				}

			}
			/* cas de requête simple à partir d'un fichier, où le fichier ne comporte que des mots, le nombre de similarités souhaitées entré par l'utilisateur est le même pour chaque mot
				par exemple : java WordEmbeddings vecs50.txt requetessimples.txt 10 S */
			else if (args.length == 4){ 
				if (args[3].equals("S")){
					HashMap<String,Integer> DicoVecteurs = Bibliotheque.DicoVecteurs;
					try {
						FileReader fr = new FileReader(args[1]);
						BufferedReader br = new BufferedReader(fr);
						String line;
						line = br.readLine(); //mot
						int simi = Integer.valueOf(args[2]);
						Echo.main(args);
						while((line = br.readLine()) != null){
							Integer posMot = DicoVecteurs.get(line);
							Vecteurs mot = bibli.vec[posMot];
							System.out.println(line);
							Bibliotheque.similaritek(mot,simi);
							System.out.println();
						}
					}
					catch(IOException ioe){
						System.out.println(ioe);
					}
				}
				/* cas de requête complexe à partir d'un fichier, où le fichier ne comporte que des requêtes, le nombre de similarités souhaitées entré par l'utilisateur est le même pour chaque requête
					par exemple : java WordEmbeddings vecs50.txt requetescomplexes.txt 10 C */
				else if (args[3].equals("C")){
					HashMap<String,Integer> DicoVecteurs = Bibliotheque.DicoVecteurs;
					try {
						FileReader fr = new FileReader(args[1]);
						BufferedReader br = new BufferedReader(fr);
						String line;
						line = br.readLine();
						int simi = Integer.valueOf(args[2]);
						Echo.main(args);
						while((line = br.readLine()) != null){
							String [] s = line.split(" ");
							String mot4 = s[4]; // France
							String mot6 = s[6]; // Paris
							String mot1 = s[1]; // Londres
							if (!DicoVecteurs.containsKey(mot4) || !DicoVecteurs.containsKey(mot6) || !DicoVecteurs.containsKey(mot1)){
								if (!DicoVecteurs.containsKey(mot4)){
									System.out.println("Le mot "+mot4+" n'est pas dans notre liste de mots");
									}
								if (!DicoVecteurs.containsKey(mot6)){
									System.out.println("Le mot "+mot6+" n'est pas dans notre liste de mots");
									}
								if (!DicoVecteurs.containsKey(mot1)){
									System.out.println("Le mot "+mot1+ " n'est pas dans notre liste de mots");
									}
								System.out.println("Veuillez réessayer.");
								System.exit(-1);
							}
							Integer posMot4 = DicoVecteurs.get(mot4);
							Integer posMot6 = DicoVecteurs.get(mot6);
							Integer posMot1 = DicoVecteurs.get(mot1);
							Vecteurs soustr = Vecteurs.soustraction(bibli.vec[posMot4],bibli.vec[posMot6]); // un nouveau vecteur, soustraction des deux autres
							Vecteurs add = Vecteurs.addition(bibli.vec[posMot1],soustr);
							System.out.println(line);
							Bibliotheque.similaritek(add,simi);
						}
					}
					catch(IOException ioe){
						System.out.println(ioe);
					}
				}
			}
		}
	}
	
}

