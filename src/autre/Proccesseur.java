package autre;
import java.util.ArrayList;


public class Proccesseur {

	private ArrayList<Tache> taches;
	
	public Proccesseur(){
		this.taches = new ArrayList<>();
	}

	public void addTache(Tache tache) {
		taches.add(tache);
	}

}
