package corps.tableauCouleurs;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import auxMaths.BarChart;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import corps.tableauCouleurs.algos.AlgoLightPoint;
import ihm.fenetre1.edition.Editable;
import ihm.fenetre1.edition.Entrable;
import ihm.fenetre1.edition.entrees.Point;
import ihm.fenetre1.edition.entrees.Scalaire;

public class Diagramme extends BarChart implements Editable{

	protected Point3 dep;
	protected R3 increment;
	protected int nbPoints;
	
	protected Function<Point3,Double> f;
	
	/**La liste des attributs de la surface. A tout instant en dehors du corps d'une méthode,
	 * ce dictionnaire est la copie conforme des attributs de l'instance en cours.
	 */
	protected Map<String,Entrable> attributs = new HashMap<String,Entrable>();

	

	public Diagramme(Point3 depart, R3 increment, int nbPoints, Function<Point3,Double> alg) {
		super("Graphe intensité",genererValeurs(depart,increment,nbPoints,alg));
		dep=depart;
		this.f=alg;
		this.increment=increment;
		this.nbPoints = nbPoints;
		for (int i=0; i<nbPoints/10; i++) {
			labels[10*i]=values[10*i]+"W";
		}
		majListeAttributs();
	}
	


	public Diagramme(String titre) {
		super(titre);
	}
	
	//Aux
	/**Methode géométrique qui déduit l'incrément de la donnée des points de départ et d'arrivée, et du nombre de points
	 * 
	 * @param depart
	 * @param arrivee
	 * @param nbPoints
	 * @return
	 */
	protected static R3 genererIncrement(Point3 depart, Point3 arrivee, int nbPoints) {
		return depart.Vecteur(arrivee).prod(1.0/(nbPoints-1));
	}

	/**Renvoie les valeurs correspondants aux entrées géométriques et optiques
	 * 
	 * @param depart
	 * @param increment
	 * @param nbPoints
	 * @param alg
	 * @return
	 */
	protected static double[] genererValeurs(Point3 depart, R3 increment, int nbPoints, Function<Point3,Double> alg) {
		double[] result = new double[nbPoints];
		Point3 act = depart;
		for (int i=0; i<nbPoints; i++) {
			result[i] = alg.apply(act);
			act = act.plus(increment);
		}
		return result;
	}
	
	
	//=====================================================
	//Edition

	/**Met à jour 'values' au vu des attributs actuels.
	 * 
	 */
	protected void update() {
		setValues(genererValeurs(dep,increment,nbPoints,f));
	}

	@Override
	public Map<String,Entrable> getAttributsEditables() {
		return new HashMap<String,Entrable>(attributs);
	}

	@Override
	public void majListeAttributs() {
		attributs.put("Depart", new Point(dep));
		attributs.put("Arrivee", new Point(dep.plus(increment.prod(nbPoints-1))));
		attributs.put("Nb Points",new Scalaire(nbPoints));
	}

	@Override
	public void maj() {
		dep = ((Point) attributs.get("Depart")).getValue();
		nbPoints = (int)((Scalaire) attributs.get("Nb Points")).getValue();
		increment = dep.Vecteur(((Point) attributs.get("Arrivee")).getValue()).prod(1.0/(nbPoints-1));
		update();
	}

}