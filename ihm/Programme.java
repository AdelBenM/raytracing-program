package ihm;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import auxMaths.algLin.Point3;
import auxMaths.algLin.R3;
import corps.Mecatro;
import corps.Raytracing;
import corps.tableauCouleurs.parametres.ParametresMecatro;
import objets.objetMecatro.MiroirMecatro;
import objets.objetMecatro.MiroirParabolePavee;
import objets.objetMecatro.MiroirRectangle;
import objets.objetPhong.Sphere;
import objets.scene.SceneMecatro;
import objets.scene.SceneRaytracing;
import optique.SourcePonctuelleIsotrope;



public class Programme implements ActionListener{

	
	public Programmable pr;

	public Fenetre1 f1;
	public Fenetre2 f2;

	public Programme(Programmable pr) {
		f1 = new Fenetre1(pr);
	}

	public Programme(Mode m) {

		switch (m) {
		case Raytracing:
			SceneRaytracing sc = new SceneRaytracing();

			sc.ajouter(new Sphere( "Sphere1", Point3.origine.plus(new R3(-3,4,0)), 3, Color.red ));
			sc.ajouter(new Sphere( "Sphere2", Point3.origine.plus(new R3(2,4,0)), 2, Color.blue ));
			//Cube c =new Cube(Point3.origine.plus(new R3(0,4,0)), 3, M3.id, Color.red );
			//c.tourner(R3.uz,Math.PI/4);
			//r.ajouter(c);
			sc.ajouter(new SourcePonctuelleIsotrope("Source 1",Point3.origine.plus(new R3(0,1,2)) , 1000));
			pr=new Raytracing(sc);
			f1=new Fenetre1(pr);
			break;
		case Miroirs:
			SceneMecatro sc2 = new SceneMecatro();
			Point3 ptRef =Point3.origine.plus(new R3(0,-0.5,0));
			MiroirRectangle miroir1 = new MiroirRectangle("Miroir 1" ,R3.uy, ptRef,1,1);
			MiroirRectangle miroir2 = new MiroirRectangle("Miroir 2" ,new R3(2,1,0), ptRef.plus(new R3(-1,0,0)),1,1);
			MiroirRectangle miroir3 = new MiroirRectangle("Miroir 3" ,new R3(-2,1,0), ptRef.plus(new R3(1,0,0)),1,1);
			MiroirRectangle miroir4 = new MiroirRectangle("Miroir 4" ,new R3(0,1,2), ptRef.plus(new R3(0,0,-1)),1,1);
			MiroirRectangle miroir5 = new MiroirRectangle("Miroir 5" ,new R3(0,1,-2), ptRef.plus(new R3(0,0,1)),1,1);
			
			MiroirRectangle miroir0 = new MiroirRectangle("Miroir 1" ,R3.uy, ptRef,5,5);
			//MiroirParabolePavee miroir = new MiroirParabolePavee("Miroir1",1,Point3.origine.plus(new R3(0,-1,0)), R3.uy, 1,4);

			//sc2.ajouter(miroir0);
			sc2.ajouter(miroir1);
			sc2.ajouter(miroir2);
			sc2.ajouter(miroir3);
			sc2.ajouter(miroir4);
			sc2.ajouter(miroir5);
			ParametresMecatro param = new ParametresMecatro();
			param.aggrandir(1);
			pr = new Mecatro(sc2, param);
			f1=new Fenetre1(pr);
			break;
		default:
			break;
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==f1.commencer || e.getSource()==f1.menuCommencer)
			f2=new Fenetre2(pr);
	}


	public static void main(String[] args) {
		ChoixDuMode c = new ChoixDuMode();
	}

}
