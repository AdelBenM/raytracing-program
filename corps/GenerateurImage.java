package corps;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JPanel;

import corps.tableauCouleurs.TableauCouleurs;
import ihm.Programmable;
import ihm.fenetre1.edition.EditableGenerique;
import ihm.fenetre1.edition.PanelEntree;
import ihm.fenetre1.edition.entrees.Scalaire;

public abstract class GenerateurImage implements Programmable{

	private Scalaire intBlanc=new Scalaire(Double.NaN);
	boolean isBlancTampered=false;
	public void modifBlanc(double d, double dMax) {
		intBlanc.conformerA(intBlanc.getValue() * (1 + d / dMax));
	}



	/**
	 * Renvoie le tableau de couleur qui correspond à l'image rendue
	 * 
	 * @return
	 */
	protected abstract TableauCouleurs getTab(TypeImage t) ;


	private TableauCouleurs getTabCalibre(TypeImage t) {
		TableauCouleurs result = getTab(t);
		if (isBlancTampered)
			result.setBlanc(intBlanc.getValue());
		return result;
	}

	// =================================================
	// ============= Programme principal ===============
	// =================================================

	/**
	 * Fonction auxilliaire des mainProgram; sert simplement � factoriser le corps des deux m�thodes suivantes
	 * 
	 * @return
	 */
	private BufferedImage auxMainProgram(TypeImage t, double iblanc, boolean isIntensiteImposee) {
		long startTime = System.nanoTime();

		TableauCouleurs premImage = getTabCalibre(t);

		if (isIntensiteImposee)
			premImage.setBlanc(iblanc);

		BufferedImage off_Image = premImage.getImage();
		/*try {
			File outputfile = new File("saved.png");
			ImageIO.write(off_Image, "png", outputfile);
		} catch (IOException e) {
		}*/
		System.out.println((System.nanoTime() - startTime) / 1000000 + "ms de lecture");
		return off_Image;
	}



	/**
	 * Renvoie une image du type demand� qui satisfasse les param�tres entr�s (ces derniers sont pris en compte dans getTab)
	 * Impose le seuil de saturation explicitement.
	 * @param t
	 * @param iblanc
	 * @return
	 */
	public BufferedImage mainProgram(TypeImage t, double iblanc) {
		return auxMainProgram(t,iblanc,true);
	}


	/**
	 * Renvoie une image du type demand� qui satisfasse les param�tres entr�s (ces derniers sont pris en compte dans getTab)
	 * Le seuil de saturation est impos� de telle sorte qu'il co�ncide avec l'intensit� max. 
	 * @param t
	 * @return
	 */
	public BufferedImage mainProgram(TypeImage t) {
		return auxMainProgram(t,Double.NaN, false);
	}





	// =================================================
	// ========== Methodes de Programmable =============
	// =================================================



	@Override
	public BufferedImage imageEdition() {
		return mainProgram(TypeImage.Previsualisation);
	}
	
	@Override
	public JPanel panelOptions() {
		return new PanelEntree(new EditableGenerique(intBlanc),"Saturation");
	}
	










	@Override
	public JMenu menu() {
		return null;
	}



}
