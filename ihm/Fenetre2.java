package ihm;

import java.awt.Checkbox;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ihm.fenetre1.commandesSup.Slide;
import ihm.fenetreImage.VisuImage;
import ihm.util.MenuSauverOuvrir;

public class Fenetre2 extends VisuImage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6073674098267913730L;
	Programmable pr;
	Slide slideInt;
	Checkbox optionsSups;
	JFrame fenOpt;

	MenuSauverOuvrir sauvOuv;


	public Fenetre2(Programmable prog) {
		super();
		pr=prog;
		changerImage(prog.imageFinale());
		

		setLocation(150, 100);
		format=prog.getFormatImage();

		//Boutons
		optionsSups = new Checkbox("Plus");
		optionsSups.addItemListener(this);

		commandes.add(optionsSups);

		//Slide
		slideInt=new Slide();
		slideInt.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent event){

				modifBlanc(((JSlider)event.getSource()).getValue(), slideInt.getMaximum());

			}});
		slideInt.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent w) {
				// masque la feêntre externe :
				slideInt.setVisible(false);
				optionsSups.setState(false);

			}
		});

		//=============================================
		//Enregistrement et sauvegarde de scènes

		sauvOuv=new MenuSauverOuvrir();
		sauvOuv.ajouterA(menuFichier);
		menuFichier.remove(quitter);
		menuFichier.add(quitter);

		setVisible(true);
	}




	@Override
	public void itemStateChanged(ItemEvent evt) {
		if (evt.getSource() == optionsSups) {
			if (evt.getStateChange() == ItemEvent.SELECTED) {
				fenOpt = pr.fenetreOptions();
			}
			else fenOpt.dispose();
		}
		else super.itemStateChanged(evt);    
	}




	public void modifBlanc(double d, double dMax) {
		/*	  switch (p.mode) {
		case Raytracing :
			changerImage(p.r.getParam().getPic(p.r.imageBase, p.r.intBlanc*(1+d/dMax)));;
			break;
		case Miroirs:
			changerImage(p.mec.getParam().getPic(p.r.imageBase, p.r.intBlanc*(1+d/dMax)));;
			break;
		}*/
	}


}
