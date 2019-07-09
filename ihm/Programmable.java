package ihm;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ihm.fenetreImage.AfficheurImageEdition;
import ihm.fenetreImage.ImageScrollPane;

public interface Programmable {
	
	//Fenetre 1
	BufferedImage imageEdition();
	
	ImageScrollPane afficheurImage();
	JPanel commandesSupp();
	JTabbedPane ongletsEdition();
	
	//Fenetre 2
	BufferedImage imageFinale();
	JPanel panelOptions();
	default JFrame fenetreOptions() {
		JFrame result = new JFrame("Options");
		result.setContentPane(panelOptions());
		result.pack();
		result.setVisible(true);
		return result;
	}
	JMenu menu();
	String getFormatImage();
	
	
	

}
