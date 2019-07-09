package corps.tableauCouleurs.algos;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import corps.tableauCouleurs.parametres.ParametresMecatro;
import objets.scene.SceneMecatro;
import optique.couleur.CouleurL;
import optique.lumiere.Lumiere;

public class AlgoMecatro extends AlgoLightPoint {

	SceneMecatro sc;
	
	public AlgoMecatro(ParametresMecatro p , SceneMecatro s ) {
		super(p, p.getCentre(), p.getBase());
		sc=s;
	}

	@Override
	protected Lumiere getLumierePoint(Point3 p) {
		/*if (sc.getLumieresEn(p).mesurerSelon(R3.uy).getIntensite()==0)
			System.out.println("**");*/
		//System.out.println(p.toStringHor());
		return sc.getLumieresEn(p);
	}



}
