import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

public class main {

	public static void main(String[] args) {
		try {
			//Lecture du fichier csv
			ArrayList<String> res = lectureFichier();
			//Decoupage du tableau csv par ligne
			ArrayList<String>[] lignes = decoupageListes(res);
			//La liste des maps
			ArrayList<Map<Integer, String>> m = null;

			for (int i = 0; i<lignes.length;i++ ) {
				//Transformation de la liste en map<cle,valeur>
				m.add(map(lignes[i]));
			}
			Map<Integer, ArrayList<String>> finalmap = reduce(m);
			ecrireFichier(finalmap);
		} catch (Exception e) {
			System.out.println("erreur");
		}
	}
	
	//Lecture du fichier csv
	private static ArrayList<String> lectureFichier() throws Exception {
		System.out.println("Lecture du fichier à faire pivoter");
		File csv = new File("pivot.csv");
		FileReader lecteur = new FileReader(csv);

		ArrayList<String> res = new ArrayList<String>();
		//TODO :Traitement du fichier pour obtenir une liste
		return res;
	}
	//Decoupage du tableau csv par ligne
	private static ArrayList<String>[] decoupageListes(ArrayList<String> res) {
		ArrayList<String>[] lignes = null;
		//TODO : decoupage en N listes de res
		return lignes;
	}
	//Transformation de la liste en map<cle,valeur>
	private static Map<Integer, String> map(ArrayList<String> ligne) {
		Map<Integer, String> listeMap = null;
		for (int i = 0; i<ligne.size();i++ ) {
			listeMap.put(i, ligne.get(i)); // map<clé,valeur>
		}
		return listeMap;
	}

	private static Map<Integer, ArrayList<String>> reduce(ArrayList<Map<Integer, String>> m) {
		Map<Integer, ArrayList<String>>  finalmap = null;
		//Shuffle et sort
		Map<Integer, String> sortedmap = null;
		//Pour chaque Map de la liste m
		for (int i = 0; i<m.size();i++ ) {
			//Pour chaque element de la map
			for (int j = 0; i<m.get(i).size();j++ ) {
				//Ajout a la map sorted
				sortedmap.put(j, m.get(i).get(j));
			}
		}
		//TODO : sortedmap.sortByKey();

		//Reduce
		ArrayList<ArrayList<String>> listesparcle = null;
		for (int i = 0; i<sortedmap.size();i++ ) {
			//TODO : creation de listes par clé
		}
		for (int i = 0; i<listesparcle.size();i++ ) {
			//TODO : creation de listes par KEY
			finalmap.put(i, listesparcle.get(i));
		}
		return finalmap;
	}
	
	private static void ecrireFichier(Map<Integer, ArrayList<String>> finalmap) {
		// TODO ecrire le csv pivot dans un fichier	
	}
}

