package A08_GraphZusammen;
import basisAlgorithmen.*;

import java.util.List;

public class ConnectedComponents {
	private boolean visited[];
	private Graph graph;
	private int notvisied = 0;
	/**
	 * Retourniert die Anzahl der zusammenhängenden Komponenten eines Graphen
	 * @param g zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	public int getNumberOfComponents(Graph g) { //Laufzeit O(V² + E) da man sichestellen muss das man alle Knoten auch wirklich besucht hat
		visited = new boolean[g.numVertices()]; //erstellt Array zum Speichern ob ein Knoten besucht wurde
		int count = 0; //Zaehler mit allen verbundenen Knoten
		graph = g;
		while (!allvisited()){ //Schleife unterbrochen wenn alle Knoten besucht wurden. Laufzeit V
			nextVertrex(notvisied); //Tiefensuche mit den letzten nicht besuchten Knoten
			count ++; //Erhöhung des Zahlers um eins.

		}
		return count; //Rueckgabe des Zahlers
	}

	private void nextVertrex(int v) {

		visited[v] = true; //setzt in Array das man den Knoten schon besucht hatte.
		List<WeightedEdge> edges = graph.getEdges(v); //erstellt Liste von allen Kanten die der Knoten hat

		for (WeightedEdge e : edges // Laufzeit E
				) {
			if(visited[e.to_vertex] == false){ //Wen der Knoten der dem Knoten noch nicht besucht hat, dann wird die Funktion nextVertrex rekursive aufgerufen.
				nextVertrex(e.to_vertex);
			}

		}

	}

	private boolean allvisited()
		{
		boolean findall = true;
		for (int j = 0; j < visited.length; j++) //Sieht nach ob man alle Knoten besucht hatte Laufzeit V
		{
			if (visited[j] == false){
				findall = false;
				notvisied = j; //Wurde ein Knoten noch nicht besucht dann wird false zurueck gegeben und notvisied auf diesen Knoten gesetzt
			}

		}
		return findall;
	}

}
