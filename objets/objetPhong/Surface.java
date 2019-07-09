package objets.objetPhong;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import auxMaths.objetmaths.surfacemaths.SurfMath;
import ihm.fenetre1.edition.Editable;
import ihm.fenetre1.edition.Entrable;
import objets.CategorieObjet;
import objets.ObjetRaytracing;
import objets.scene.SceneRaytracing;
import objets.scene.Stageable;
import optique.Photon;
import optique.couleur.CouleurL;
import optique.couleur.CouleurS;
import optique.lumiere.AssociationLumieres;
import optique.lumiere.Lumiere;
import optique.sources.Illumination;

public abstract class Surface<G extends SurfMath> extends ObjetRaytracing implements SurfMath,Editable{
	int dispoCouleurs=0;    //Disposition de la couleur sur la surface
	protected CouleurS[] listeCouleurs;
	protected G surfGeometrie;


	/**La liste des attributs de la surface. A tout instant en dehors du corps d'une méthode,
	 * ce dictionnaire est la copie conforme des attributs de l'instance en cours.
	 */
	protected Map<String,Entrable> attributs = new HashMap<String,Entrable>();

	

	double kd=0.75;
	double ks=0.5;
	double s=100;


	public Surface(String name, G surf, Color c1, Color... tab) {
		super(name);
		this.surfGeometrie=surf;
		if (tab.length>0)	//TODO
			throw new RuntimeException("Cas de couleurs multiples pas encore traité!");
		listeCouleurs = new CouleurS[] {new CouleurS(c1)};
	}
	
	//TODO: rendre le constructeur compatible avec le cas de plusieurs couleurs
	protected Surface(String name, G surf, CouleurS[] tab) {
		this(name,surf,tab[0].getColor());
	}


	@Override
	public abstract Surface<G> clone();


	@Override
	public CategorieObjet getTypeObjet() {
		return CategorieObjet.Surface;
	}



	public CouleurS[] getListeCouleurs() {
		return listeCouleurs.clone();
	}

	public G getGeometrie() {
		return surfGeometrie;
	}
	

	protected void setGeometrie(G s) {
		surfGeometrie=s;
	}

	//===============================================
	// Methodes d'édition

	@Override
	public Map<String,Entrable> getAttributsEditables() {
		return new HashMap<String,Entrable>(attributs);
	}



	//====================================================
	//====================== Rendu =======================
	//====================================================

	//Methodes de geometrie

	@Override
	public double dist(Point3 m, R3 d) {    //d est normÃ©
		return surfGeometrie.dist(m, d);
	}


	@Override
	/**Renvoie un vecteur normÃ© normal a la surface au point m
	 * 
	 * @param m
	 * @return
	 */
	public R3 getNorm(Point3 m) {
		return surfGeometrie.getNorm(m);
	}



	// Methodes de couleur

	public CouleurS getCouleurIntra(Point3 m) {
		if (dispoCouleurs==0) 
			return listeCouleurs[0];
		else 
			throw new IllegalArgumentException("Disposition des couleurs invalide!");
	}

	public R3 getValueCouleurIntra(Point3 m) {
		CouleurS r = getCouleurIntra(m);
		return new R3(r.getColor().getRed(),r.getColor().getGreen(),r.getColor().getBlue());
	}



	public CouleurL refDif(Photon lux, R3 n, double a) {
		if (a >0 ) {
			return lux.getCoul().multiplieIntensite(kd * a);
		}
		else {
			return CouleurL.noir;
		}
	}

	public CouleurL refMiroir(Point3 m, R3 d, Stageable s, int k) {
		if (k==0)
			return CouleurL.noir;
		else {
			Photon lux = new Photon(m,s);
			ObjetRaytracing o =lux.avancer(d.symetrieOrth(getNorm(m)));  //RÃ©flexion parfaite
			return o.getColor(lux.getPosition(), lux.getV(), s, k-1);

		}
	}

	@Override
	public CouleurL getColor(Point3 m, VectUnitaire d, Stageable scene) {
		CouleurL lumTot;
		CouleurL lumTotSpec;

		VectUnitaire n = new VectUnitaire(d.bonSens(getNorm(m)));
		
		AssociationLumieres lux = scene.getLumieresEn(m);

		
		lumTot = lux.mesurerSelon(n.opp()).multiplieIntensite(kd);
		lumTotSpec = lux.reflexion(n).mesurerRefSpec(d.opp() , s ).multiplieIntensite(ks);
		
		//System.out.println(lumTotSpec);
		//System.out.println( getCouleurIntra(m) );
		//System.out.println( getCouleurIntra(m).getResultante(lumTot) );
		return (getCouleurIntra(m).getResultante(lumTot)).plus(lumTotSpec);
	}
	


/*	@Override
	public CouleurL getColor(Point3 m, VectUnitaire d, Stageable scene) {
		CouleurL lumTot = scene.getAmbiant().getCouleurL();
		CouleurL lumTotSpec = CouleurL.noir;
		R3 n = d.bonSens(getNorm(m));
		//Photon lux;
		double costheta;
		double cosalpha;
		for (Photon lux : (scene).getLumieresEn(m)) {
			costheta= - n.scal(lux.getV());
			if (costheta>0) {
				lumTot = lumTot.plus(lux.getCoul().multiplieIntensite(kd * costheta));  //terme de reflexion diffuse
				cosalpha=Math.abs(d.scal(n.prod(2*costheta).plus(lux.getV()).normer()));  //cos(alpha)=d.c où c=2*(L.n)n-L (normé) (symetrie axiale)
				lumTotSpec=lumTotSpec.plus(lux.getCoul().multiplieIntensite(ks * Math.pow(cosalpha, s)));

			}
		}
		//lumTotSpec=CouleurL.noir;
		      if ((getCouleurIntra(m).getResultante(lumTot)).plus(lumTotSpec) .getIntensite()< 0.2)
        System.out.print("");
		return (getCouleurIntra(m).getResultante(lumTot)).plus(lumTotSpec);
	}*/

	@Override
	public CouleurL getColor(Point3 m, R3 d, Stageable scene, int ref) {
		return getColor(m,d,scene).plus(refMiroir(m,d,scene,ref));
	}

	@Override
	public CouleurL getColorSimple(Point3 m, R3 d, Stageable scene) {
		return getCouleurIntra(m).getResultante(scene.getAmbiant().getCouleurL());
	}

	
	
	
	public static void main(String[] args) {
		SceneRaytracing sc = new SceneRaytracing();
		
		VectUnitaire norm = R3.ux;
		Point3 ptPart = Point3.origine.plus(new R3(2,0,0));
		Plan p = new Plan("", norm, ptPart, Color.BLACK);
		sc.ajouter(p);
		
		R3 ptDeVue = R3.ux;
		System.out.println(p.getColor(ptPart, ptDeVue, sc));
	}
}


/**Modelise le champ lumineux créé par la réflexion spéculaire d'une lumière en un point.
 * Pondération en cos(alpha)**s
 * 
 * @author Adel
 *
 */
class LumiereReflechie implements Illumination {
	
	Lumiere lumBase;
	Point3 pointSource;
	VectUnitaire dir;
	double s;
	
	public LumiereReflechie(Point3 ptDeReflexion, Lumiere lumRecueApresReflexion, VectUnitaire directionReflechie, double s) {
		this.s = s;
		pointSource = ptDeReflexion;
		lumBase = lumRecueApresReflexion;
		dir = directionReflechie;
	}
	
	@Override
	public Lumiere champLumiere(Point3 pt) {
		
		return null;
	}
	
}


