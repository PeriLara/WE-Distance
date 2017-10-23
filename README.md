 # WordEmbeddings

	Projet Lucie MARIGNIER et Lara PERINETTI
	
	
	Étape 1 : 
	
	Les tests sur les opérations se font à partir du fichier TestOperationsVecteurs.java
	
	Pour tester les requetes simples et complexes, les tests se font à partir du fichier WordEmbeddings.java	
	javac WordEmbeddings.java
	
	Étape 2 : 
	
		--- Requête complexe avec une seule commande ---
		Attention de bien respecter les espaces pour cette deuxième ligne
		
			java WordEmbeddings filename \( Mot1 + \( Mot2 - Mot3 \) \) Nombre
			exemple : java WordEmbeddings vecs50.txt \( Londres + \( France - Paris \) \) 10
			
		--- Requête simple avec une seule commande ---

			java WordEmbeddings filename Mot Nombre
			exemple : java WordEmbeddings vecs50.txt chat 10
			
		--- Requête simple à partir d'un fichier ---
			
			--- Fichier sous la forme : 
					chien
					chat
					poubelle
					(un mot par ligne)
			
			java WordEmbeddings filename(vecteurs) filename(requetes)  Nombre S
			exemple : java WordEmbeddings vecs50.txt requetessimples.txt 10 S

			--- Fichier sous la forme : 
					chien 10
					chat 2
					poubelle 30
					(chaque mot est associé à un nombre k, le nombre de mots similaires)
			
			java WordEmbeddings filename(vecteurs) filename(requetes) KS
			exemple : java WordEmbeddings vecs50.txt requetessimplesK.txt KS
			
		--- Requête complexe à partir d'un fichier ---
		
			--- Fichier sous la forme : 
					( France + ( Paris - ville ) ) 
					(une requete par ligne)
					
				java WordEmbeddings filename(vecteurs) filename(requetes)  Nombre C
				exemple : java WordEmbeddings vecs50.txt requetescomplexes.txt 10 C
				
			--- Fichier sous la forme : 
					( France + ( Paris - ville ) ) 15
					(une requete par ligne)
					
				java WordEmbeddings filename(vecteurs) filename(requetes) KC
				exemple : java WordEmbeddings vecs50.txt requetescomplexesK.txt KC
					
					
					
					
					
