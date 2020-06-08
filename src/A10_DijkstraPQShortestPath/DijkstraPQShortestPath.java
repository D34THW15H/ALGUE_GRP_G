package A10_DijkstraPQShortestPath;

import basisAlgorithmen.Vertex;
import basisAlgorithmen.VertexHeap;

import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernung initialisieren
	 * 
	 * param from
	 *            Startknoten
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			dist[i] = 9999; // Summen im Graph dürfen nie mehr ergeben
		}
	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {
		//Add Heap
		VertexHeap heap = new VertexHeap(graph.numVertices());
		// Set Distance "from" to 0
		dist[from] = 0;
		// Für jeden Eintrag in Dist, Erstelle einen vertex und füge es dem Heap hinzu.
		for (int i = 0; i < dist.length; i++)
		{
			Vertex v = new Vertex(i, dist[i]);
			heap.insert(v);
			pred[i] = -1;
		}
		//Setze von Knoten "from" die Kosten auf 0
		heap.setCost(from, 0);
		// Solange der Heap nicht leer ist
		while (!heap.isEmpty())
		{
			//Erstes Element in Heap = current
			Vertex current = heap.remove();
			//Liste mit allen Kanten
			List<WeightedEdge> alleKanten = graph.getEdges(current.vertex);
			//Für jede Kante
			for (WeightedEdge kante: alleKanten)
			{
				//Aktuelle Kante + Kantengewicht = Ergebnis
				int ergebnis = dist[current.vertex] + kante.weight;
				//Kosten zur Eckkante
				int zielKante = kante.to_vertex;
				// Wenn Ergebnis kleiner als vorhandener Wert, überschreibe Kosten mit Ergebnis.
				if (ergebnis < dist[zielKante])
				{
					//Füge Zielkante zur Überprüft Liste hinzu.
					dist[zielKante] = ergebnis;
					heap.setCost(zielKante, ergebnis);
					pred[zielKante] = current.vertex;
				}
			}
		}
		return true;
	}
}
