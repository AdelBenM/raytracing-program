package objets.objetMecatro;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import auxMaths.algLin.M3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.objetmaths.surfacemaths.degre1.PolygoneConvexe;
import auxMaths.objetmaths.volumemaths.DemiEspace;
import auxMaths.objetmaths.volumemaths.VolumePyramide;
import auxMaths.transformations.Symetrie;
import objets.objetPhong.Surface;
import objets.scene.SceneMecatro;
import objets.scene.Stageable;
import optique.Source;
import optique.SourcePonctuelleIsotrope;
import optique.sources.Illumination;
import optique.sources.Obstruable;
import optique.sources.PointLumineux;
import optique.sources.obstruction.ObstructionBasiqueRestreinte;

public class MiroirPlanConvexe<S extends PolygoneConvexe> extends MiroirMecatro<S>{



	/**
	 * 
	 */
	private static final long serialVersionUID = -9175057473808062407L;




	protected MiroirPlanConvexe(String nom, S surfMath, Color c, Color... colors) {
		super(nom, surfMath, c, colors);
	}
	
	protected MiroirPlanConvexe(Surface<S> s) {
		super(s);
	}
	
	@Override
	public Surface<S> clone() {
		return new MiroirPlanConvexe<S>(this);
	}
	
	//==============================================================
	//Methodes de miroir

	@Override
	public List<Source> apply(Source src, Stageable sc) {
		if (! (src instanceof SourcePonctuelleIsotrope))
			throw new RuntimeException("Rayonnement pas encore pris en charge!");	//TODO
		else {
			Illumination ill = auxIllum((SourcePonctuelleIsotrope)src);
			Obstruable obs = auxObstr((SourcePonctuelleIsotrope)src, sc);
			List<Source> result = new LinkedList<Source> ();
			result.add(new Source( "Reflet de "+ src.getNom(), ill,obs));
			return result;
		}
	}

	/**Renvoie une obstruction indicatrice du volume defini comme : 
	 * une pyramide de hauteur infinie, de sommet le symetrique de la source par le miroir, de base le miroir et tronquee de sorte a ne pas traverser le miroir.
	 * TODO: prendre en compte les eventuels trucs qui viennent voiler la lumiere
	 * @param src
	 * @param sc
	 * @return
	 */
	public Obstruable auxObstr(PointLumineux src, Stageable sc) {
		VolumePyramide v1;	//la pyramide de sommet la source virtuelle et de base le rectangle
		DemiEspace v2;	//le demi-espace défini par le plan du miroir et contenant la source (l'origine)
		
		Point3 centreSurf = getGeometrie().getCentre();
		VectUnitaire normSurf = (Point3.origine.Vecteur(centreSurf)).bonSens(getGeometrie().getNorm());
		
		
		Symetrie sym = new Symetrie(centreSurf, normSurf, Symetrie.PLANE);
		Point3 sourceVirt = sym.agirSur(src.getPoint());

		
		v1 =new VolumePyramide(sourceVirt, getGeometrie() );
		v2 = new DemiEspace(centreSurf, normSurf);	//le demi-espace défini par le plan du miroir et contenant la source (l'origine)
		return new ObstructionBasiqueRestreinte(sourceVirt,v1.intersection(v2) , this);	//ne pas oublier d'oublier cet objet dans l'algorithme de calcul de voilement 
	}

	/**Renvoie l'illumination symetrique du point lumineux entre.
	 * 
	 * @param src
	 * @return
	 */
	public Illumination auxIllum(PointLumineux src) {
		Point3 centre = getGeometrie().getCentre();
		VectUnitaire normale = getGeometrie().getNorm();
		
		Symetrie transfoChamp = new Symetrie(centre, normale, Symetrie.PLANE);
		Illumination ill = pt -> src.getIllumination().champLumiere(transfoChamp.agirSur(pt)).reflexion(normale);
		return ill;
	}


	//================================================================
	//Methodes d'edition
	

	@Override
	public void majListeAttributs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maj() {
		// TODO Auto-generated method stub
		
	}

	
	
	
	public static void main(String[] args) {
		SceneMecatro scene = new SceneMecatro();		//blanc intensité 1000
		MiroirRectangle mir1 = new MiroirRectangle("Miroir 1" ,M3.id, Point3.origine.plus(new R3(-1,0,0)),1,1);
		//MiroirRectangle mir1 = new MiroirRectangle("Miroir 1" ,R3.ux.opp(), Point3.origine.plus(new R3(-1,0,0)),1,1);
		scene.ajouter(mir1);
		
		Source sVirt = mir1.apply(scene.getSources()[0],scene).get(0);
		
		Point3 ptVue = Point3.origine.plus(new R3(0,1.7 ,0));
		System.out.println(scene.getLumieresEn(ptVue));
		System.out.println(sVirt.getInfluence(ptVue,scene));
		
	}

}