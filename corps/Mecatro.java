package corps;

import java.awt.Container;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import corps.tableauCouleurs.TableauCouleurs;
import corps.tableauCouleurs.algos.AlgoMecatro;
import corps.tableauCouleurs.algos.AlgoRaytracing;
import corps.tableauCouleurs.algos.AlgoSimple;
import corps.tableauCouleurs.parametres.ParametresMecatro;
import corps.tableauCouleurs.parametres.ParametresRaytracing;
import ihm.fenetreImage.commandesSup.IHMGrapheIntensite;
import objets.scene.SceneMecatro;

public class Mecatro extends GenerateurImageScene{

	//=============================================
	//Attributs
	//=============================================



	protected ParametresMecatro param;
	protected SceneMecatro sc;
	protected ParametresRaytracing previsu;



	//=====================================
	//Constructeur: définit les attributs par défaut
	
	public Mecatro(SceneMecatro s, ParametresMecatro p) {
		sc=s;
		param=p;
		previsu = p.toRaySimple();
	}
	
	
	public Mecatro() {
		this(new SceneMecatro() ,new ParametresMecatro());
	}




	
	//===============================================
	//Getters
	
	@Override
	public SceneMecatro getScene() {
		return sc;
	}

	@Override
	public ParametresMecatro getParam() {
		return param;
	}
	
	
	//==================================================
	@Override
	public TableauCouleurs getTab(TypeImage t) {
		switch(t) {
		case Raytracing: return new TableauCouleurs(new AlgoRaytracing(param.toRay(),sc)); 
		case Previsualisation: return new TableauCouleurs(new AlgoSimple(getParamPrevisu(),sc));
		case Mecatro : return new TableauCouleurs(new AlgoMecatro(param,sc)); 
		default : throw new RuntimeException(t+" pas pris en compte par ce générateur d'image!");
		}
	}
	

	//=========================================================
	//Methodes de programmable

	@Override
	public BufferedImage imageFinale() {
		return mainProgram(TypeImage.Mecatro);
	}

	@Override
	protected ParametresRaytracing getParamPrevisu() {
		return previsu;
	}
	
	@Override
	protected void setParamPrevisu(ParametresRaytracing p) {
		previsu=p;
	}
	
	@Override
	public JPanel panelOptions() {
		JPanel result = new JPanel();
		JPanel pan0 = super.panelOptions();
		JPanel pan1 = new IHMGrapheIntensite(param,new AlgoMecatro(param,sc),IHMGrapheIntensite.diagIntensite);
		JPanel pan2 = new IHMGrapheIntensite(param,new AlgoMecatro(param,sc),IHMGrapheIntensite.diagDeviation);
		JPanel pan3 = new IHMGrapheIntensite(param,new AlgoMecatro(param,sc),IHMGrapheIntensite.diagIntensiteEffective);
		result.add(pan0);
		result.add(pan1);
		result.add(pan2);
		result.add(pan3);
		return result;
	}




}
