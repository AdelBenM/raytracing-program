package ihm.fenetreImage.commandesSup;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import corps.tableauCouleurs.Diagramme;
import corps.tableauCouleurs.DiagrammeDev;
import corps.tableauCouleurs.DiagrammeIntensiteEffective;
import corps.tableauCouleurs.DiagrammeLum;
import corps.tableauCouleurs.algos.AlgoLightPoint;
import corps.tableauCouleurs.parametres.ParametresMecatro;
import ihm.fenetre1.edition.FenetreEntree;

public class IHMGrapheIntensite extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6762912237810543315L;
	JButton edition;
	JButton afficher;
	Diagramme diag;
	
	public static final int diagIntensite =0;
	public static final int diagDeviation =1;
	public static final int diagIntensiteEffective =2;
	
	public IHMGrapheIntensite(Diagramme diag) {
		super(new FlowLayout());
		this.diag=diag;
		setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(diag.getName()),
						BorderFactory.createEmptyBorder(5,5,5,5)));
		edition = new JButton("Editer");
		edition.addActionListener(this);
		afficher = new JButton("Afficher");
		afficher.addActionListener(this);
		add(edition);
		add(afficher);
		repaint();
	}
	
	public IHMGrapheIntensite(ParametresMecatro param, AlgoLightPoint alg,int type) {
		this(initiate(param,alg,type));
	}
	
	private static Diagramme initiate(ParametresMecatro param, AlgoLightPoint alg,int type) {
		if (type == diagIntensite)
			return new DiagrammeLum(param,alg);
		else if (type == diagDeviation)
			return new DiagrammeDev(param,alg);
		else if (type == diagIntensiteEffective)
			return new DiagrammeIntensiteEffective(param,alg);
		else throw new IllegalArgumentException("type pas encore pris en compte");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==edition)
			new FenetreEntree(diag, "graphe intensité");
		else if (e.getSource()==afficher){
			JFrame fen = new JFrame();
			fen.setContentPane(diag);
			fen.setSize(600,200);
			fen.setVisible(true);
		}
		
	}
	
	

}
