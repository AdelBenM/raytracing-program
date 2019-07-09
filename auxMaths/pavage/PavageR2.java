package auxMaths.pavage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import auxMaths.algLin.R3;

/**Pave le disque unit� de R2 en l'approchant par un polygone r�gulier.
 * Il suffit alors de se restreindre au triangle form� par l'origine et chaque ar�te du polygone et d'ensuite appliquer une rotation.
 * @author Adel
 *
 */
public class PavageR2 extends PavageTriangleAvecTriangles<R2>{

	int m; //>3. nombre de c�t�s du polygone
	
	/**Entr�es:
	 * le nombre de rang�es de triangles dans un grand triangles
	 * le nombre de c�t�s du polygone qui approche le disque. Au moins 3
	 * @param nbRangeesTriangles
	 * @param nbCotes
	 */
	public PavageR2(int nbRangeesTriangles, int nbCotes) {
		super(initialize(nbRangeesTriangles,nbCotes));
		m=nbCotes;

	}
	
	/**Fabrique le tableau de points correspondant au triangle repr�sentatif du mod�le.
	 * 
	 * @param nbRangeesTriangles
	 * @param nbCotes
	 * @return
	 */
	private static List<List<R2>> initialize(int nbRangeesTriangles, int nbCotes){
		if (nbRangeesTriangles<=0 || nbCotes < 3)
			throw new IllegalArgumentException("Pavage mal d�fini.");
		else {
			List<List<R2>> result = new ArrayList<List<R2>>();
			//On d�finit les deux vecteurs qui d�finissent le triangle infini de r�f�rence.  
			double angle = 2*Math.PI/nbCotes;
			R2 u = new R2(1.0/nbRangeesTriangles,0);
			R2 v = u.rotation(angle);
			
			//On d�finit l'incr�ment : le vecteur qui s�pare deux points cons�cutifs d'une m�me rang�e
			R2 d = v.moins(u);
			
			//On d�finit la position initiale pour chaque rang�e. Cette derni�re sera mise � jour r�guli�rement.
			R2 posInit;
			
			for (int i=0; i<=nbRangeesTriangles; i++) {	//Il y une rang�e de points en plus que de rang�es de triangles
				posInit= u.fois(i);
				List<R2> ligneAct = new ArrayList<R2>();
				for (int j=0; j<=i; j++)
					ligneAct.add(posInit.plus( d.fois(j) ));
				result.add(ligneAct);
			}
			return result;
		}
	}
	
	/**Renvoie les triangles du pavage du ie triangle rpztatif (dans le sens direct).
	 * 
	 * @return
	 */
	public List<Triangle<R2>> getTrianglesRpztatifs(int i){
		if (i<=0)
			throw new IllegalArgumentException("i doit �tre un entier strictement positif.");
		else if (i==1)
			return super.getTriangles();
		else{
			List<Triangle<R2>> result = new ArrayList<Triangle<R2>>();
			
			Iterator<Triangle<R2>> itr = super.getTriangles().iterator();
			Rotation2D operation = new Rotation2D( (i-1)*2*Math.PI/m);

			while(itr.hasNext()) 
				result.add(itr.next().appliquer(operation));
			return result;
		}
	}
	
	@Override
	/**Renvoie les triangles du pavage total.
	 * 
	 */
	public List<Triangle<R2>> getTriangles(){
		List<Triangle<R2>> result = new ArrayList<Triangle<R2>>();
		for (int i=1;i<=m;i++)
			result.addAll(getTrianglesRpztatifs(i));
		return result;
		
		
		
	}

	
	
	
	public static void main(String[] args) {
		PavageR2 p = new PavageR2(2,4);
		Function<R2,R3> f = cple -> new R3(cple.get1(),cple.get2(), cple.get1()*cple.get1()+cple.get2()*cple.get2());
		PavageTriangleAvecTriangles<R3> p2 = p.appliquer(f);
		
		System.out.println(p.getTriangles());
		//System.out.println(getCoordonneesTriangles(2));
	}

}
