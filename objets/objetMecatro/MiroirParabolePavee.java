package objets.objetMecatro;

import java.awt.Color;
import java.util.List;

import auxMaths.algLin.O3;
import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import auxMaths.algLin.VectUnitaire;
import objets.scene.SceneMecatro;
import optique.Source;
import optique.SourcePonctuelleIsotrope;
import optique.couleur.CouleurL;

public class MiroirParabolePavee extends MiroirGraphePave {


	public MiroirParabolePavee(String nom, double facteur, Point3 ptOrigine, O3 base, int nbRangees, int nbCotes) {
		super(nom, cple -> cple.normeCarree() ,facteur, ptOrigine, base, nbRangees, nbCotes);
	}
	
	private MiroirParabolePavee(MiroirParabolePavee m) {
		super(m);
	}

	public MiroirParabolePavee(String nom, double facteur,Point3 ptOrigine, VectUnitaire axe, int nbRangees, int nbCotes) {
		this(nom,facteur,ptOrigine, O3.base(axe).permutationColonnes(2),nbRangees,nbCotes);
	}

	@Override
	public void majListeAttributs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void maj() {
		// TODO Auto-generated method stub

	}

	@Override
	public MiroirParabolePavee clone() {
		return new MiroirParabolePavee(this);
	}
	
	public static void main(String[] args) {
		Point3 pt = Point3.origine.plus(new R3(0,-1,0));
		MiroirParabolePavee miroir = new MiroirParabolePavee("Miroir1",1,pt, R3.uy, 1,4);
		
		SourcePonctuelleIsotrope src = new SourcePonctuelleIsotrope("",Point3.origine, new CouleurL(Color.WHITE,10));
		
		SceneMecatro sc = new SceneMecatro();
		sc.ajouter(miroir);
		
		Point3 ptDeVue = Point3.origine.plus(new R3(0,1,0.1));
		System.out.println(sc.getLumieresEn(ptDeVue));
		
	}

}
