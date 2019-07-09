package auxMaths.pavage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**Pave un triangle selon le schema:
 * |\			1 triangle
 * |\|\			3 triangles
 * |\|\|\		5 triangles
 * |\|\|\|\		7 triangles
 * On a donc 2i-1 triangles à la ie rangée, soit pour n rangees pavees, n² petits triangles au total.
 * 
 * @author Adel
 *
 */
public class PavageTriangleAvecTriangles<T> extends Triangle<T>{
	
	/**Matrice diagonale inferieure : tableau de lignes de taille le numero de la ligne.
	 * Represente les divers points du pavage. Equivalent a la liste des triangles
	 */
	List<List<T>> tableauPoints;	
	
	
	private PavageTriangleAvecTriangles(List<List<T>> tab, int nbRangees) {
		super(tab.get(0).get(0), tab.get(nbRangees-1).get(0), tab.get(nbRangees-1).get(nbRangees-1));
		tableauPoints = tab;
	}
	
	/**Constructeur protégé : on suppose que le tableau en entré a les propriétés adéquates
	 * (matrice triangulaire inferieure et toutes les valeurs distinctes deux à deux)
	 * 
	 * @param tab
	 */
	protected PavageTriangleAvecTriangles(List<List<T>> tab) {
		this(tab, tab.size());
	}
	
	
	//======================================================
	//Getters de base
	
	/**Il y a 2i-1 triangles sur la deuxieme rangee, soit i² dans le sous triangle forme par la ie ligne et le sommet.
	 * 
	 * 
	 * @param t
	 */
	public int getLigneTriangleNumero(int t) {
		return (int) Math.pow(t, 0.5);
	}
	
	public int getColonneTriangleNumero(int t) {
		return t-getLigneTriangleNumero(t);
	}
	
	/**Ligne et colonne commencent à 1
	 * 
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public T getVal(int ligne, int colonne) {
		return tableauPoints.get(ligne-1).get(colonne-1);
	}
	
	/**Renvoie le nombre de rangees de points
	 * 
	 * @return
	 */
	public int getTaille() {
		return tableauPoints.size(); 
	}
	
	//=================================================
	//Getters plus complexes
	
	/**Renvoie la liste des coordonnees de chaque triangle dans l'ordre de lecture pour un pavage triangle de triangle quelconque à n rangées
	 * Chaque tableau d'entier contient exactement deux éléments : le couple (ligne,colonne)
	 * @param nbRangees
	 * @return
	 */
	public static List<Triangle<Couple<Integer>>> getCoordonneesTriangles(int nbRangees){
		List<Triangle<Couple<Integer>>> listeTriangles = new ArrayList<Triangle<Couple<Integer>>>();
		
		
		for(int i=1;i<=nbRangees;i++) {		//On attribue à chaque rangee de points la rangee de triangles juste en dessous. Est donc exclue la derniere rangée de points.
			for (int j=1;j<i;j++) {	//On attribue à chaque point d'une rangee le triangle du dessous et du dessous-droite. Est donc exclu le point le plus a droite
				listeTriangles.add(new Triangle<Couple<Integer>>(new Couple<Integer> (i,j),new Couple<Integer> (i+1,j),new Couple<Integer> (i+1,j+1)));
				listeTriangles.add(new Triangle<Couple<Integer>>(new Couple<Integer> (i,j),new Couple<Integer> (i,j+1),new Couple<Integer> (i+1,j+1)));
			}
			listeTriangles.add(new Triangle<Couple<Integer>>(new Couple<Integer> (i,i),new Couple<Integer> (i+1,i),new Couple<Integer> (i+1,i+1)));
		}
		return listeTriangles;
	}
	
	/**Renvoie la liste des petits triangles du pavage du grand triangle.
	 * 
	 * @return
	 */
	public List<Triangle<T>> getTriangles(){
		List<Triangle<T>> result = new ArrayList<Triangle<T>>();
		Iterator<Triangle<Couple<Integer>>> itr= getCoordonneesTriangles(tableauPoints.size()-1).iterator();	//le tableau des coordonnes des triangles: s'il y a n rangées de triangles, il y en a n+1 de points
		Function<Couple<Integer>,T> f = tab -> getVal(tab.get1(),tab.get2());		//La fonction de lecture des coordonnees
		while (itr.hasNext())	//boucle sur les triangles
			result.add(itr.next().appliquer(f));
		return result;
	}
	
	
	@Override
	/**Renvoie le pavage formé par l'image des sommets de l'instance par f.
	 *TODO : s'assurer que le pavage sorti en soit bien un (problème éventuel de défaut d'injectivité de f)
	 * @param f
	 * @return
	 */
	public <S> PavageTriangleAvecTriangles<S> appliquer(Function<T,S> f){
		List<List<S>> result = new ArrayList<List<S>>();
		
		Iterator<List<T>> itrLignes = tableauPoints.iterator();
		Iterator<T> itrCol;
		while(itrLignes.hasNext()) {
			itrCol=itrLignes.next().iterator();
			List<S> newLigne = new ArrayList<S>();
			while (itrCol.hasNext())
				newLigne.add(f.apply(itrCol.next()));
			result.add(newLigne);
		}
		return new PavageTriangleAvecTriangles<S>(result);
		
		
	}
	

	//==========================================
	//Autres
	
	public String toString() {
		String result = "";
		Iterator<List<T>> itr = tableauPoints.iterator();
		while (itr.hasNext())
			result+= itr.next() + "\n";
		return result;
	}
	
	
	
	public static void main(String[] args) {
		List<List<R2>> cont = new ArrayList<List<R2>>();
		List<R2> l1 = new ArrayList<R2>();
		List<R2> l2 = new ArrayList<R2>();
		cont.add(l1);
		cont.add(l2);
		
		l1.add(new R2(0,0));
		l2.add(new R2(1,0));
		l2.add(new R2(1,2));
		
		
		PavageTriangleAvecTriangles<R2> t2 = new PavageTriangleAvecTriangles<R2>(cont);

		System.out.println(getCoordonneesTriangles(2));
	}
	
	
	
	
	
	
	
}
