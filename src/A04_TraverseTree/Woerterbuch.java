package A04_TraverseTree;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class Woerterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;
	
	public Wort getRoot() {
		return root;
	}

	/**
	 * Zählt alle Wörter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der Wörter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {
		Queue<Wort> queue = new LinkedList<>();//Erstellt eine Queue wo alles gespeichert wird
		int counter = 0;
		queue.add(w);//Nimmt das übergebene element und fügt es der queue hinzu.
		Wort node;
		while ((node = queue.poll())!= null){
			if (node.getLeft() != null){
				queue.add(node.getLeft()); //wenn unser Knoten ein linkes Kind hat, dann wird es zur queue hinzugefügt
			}
			if(node.getRight() !=null){
				queue.add(node.getRight()); //wenn unser Knoten ein rechtes Kind hat, dann wird es zur queue hinzugefügt
			}
			counter++; // erhöht den counter jeweils um eins.
		}


		return counter;
	}

	/**
	 * Liefert die Menge aller Wörter retour, die ein spezifisches Präfix haben.
	 * @param prefix Wörter müssen diesen Präfix haben
	 * @return Menge aller zutreffenden Wörter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {
		Set<String> returnset = new HashSet<>(); //erstellt ein Set das zurück gegeben wird.
		Queue<Wort> queue = new LinkedList<>(); //Erstellt eine Queue wo alles gespeichert wird
		queue.add(getRoot()); //Nimmt das root element und fügt es der queue hinzu.
		Wort node;
		while ((node = queue.poll())!= null){
			if (node.getLeft() != null){ //wenn unser Knoten ein linkes Kind hat, dann wird es zur queue hinzugefügt
				queue.add(node.getLeft());
			}
			if(node.getRight() !=null){ //wenn unser Knoten ein rechtes Kind hat, dann wird es zur queue hinzugefügt
				queue.add(node.getRight());
			}
			if(node.getWort().startsWith(prefix)){ //wenn unser Knoten mit den prefix beginnt, dann wird der Knoten an das Set angehängt
				returnset.add(node.getWort());
			}
		}
		return returnset;
	}
	

	/**
	 * Neues Wort hinzufügen
	 * @param wort Hinzuzufügendes Wort
	 */
	public void add(String wort) {
		Wort neu = new Wort(wort);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Wort w = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = wort.compareTo(w.getWort());
			if (vgl < 0) {			// Neues Wort ist lexikographisch kleiner
				if (w.getLeft() == null) {
					w.setLeft(neu);
					neu.setParent(w);
					return;
				}
				w = w.getLeft();
			}
			else if (vgl > 0) {		// Neues Wort ist lexikographisch größer
				if (w.getRight() == null) {
					w.setRight(neu);
					neu.setParent(w);
					return;
				}
				w = w.getRight();
			}
			else {					// Neues Wort ist lexikographisch gleich
				return;
			}
		}
	}

	public Wort find(String s) {
		return find(root, s);
	}
	
	private Wort find(Wort current, String s) {
		if (current == null) {
			return null;
		}
		int vgl = s.compareTo(current.getWort());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), s);
		}
		else {				// Rechts
			return find(current.getRight(), s);
		}
	}
	
}
