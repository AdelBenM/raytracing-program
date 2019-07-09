package objets;

import java.util.List;
import java.util.function.BiFunction;

import objets.scene.SceneMecatro;
import objets.scene.Stageable;
import optique.Eclairage;
import optique.Source;
import optique.SourcePonctuelleIsotrope;

public interface PreMiroir extends BiFunction<Source,Stageable,List<Source>>{
	
	
	
	
	/**Renvoie la source virtuelle engendree par la reflexion d une source reelle par le miroir.
	 * La fonction prend en compte les eventuelles interactions de la scene.
	 */
	@Override
	public List<Source> apply(Source src, Stageable sc) ;
	
	/**Renvoie la contribution du miroir à l'éclairage sous la forme de sources virtuelles qui s'ajoutent à la liste entrée.
	 * 
	 * 
	 */
	public default void addSourcesVirtuelles(List<Eclairage> listeSourcesVirtuelles, SceneMecatro s) {
		for (SourcePonctuelleIsotrope e : s.getSources())
			listeSourcesVirtuelles.addAll(apply(e,s));
	}

	
	
	
		
}
