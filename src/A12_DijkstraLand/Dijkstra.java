package A12_DijkstraLand;

import basisAlgorithmen.Vertex;
import basisAlgorithmen.VertexHeap;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

	public static List<Integer> dijkstra(Graph g, int von, int nach) {

		int[] pred = new int[g.numVertices()];
		int[] dist = new int[g.numVertices()];
		//PathSearch
		for (int i = 0; i < dist.length; i++) {
			dist[i] = 9999; // Summen im Graph d�rfen nie mehr ergeben
			pred[i] = -1;
		}
		//dijkstra with Heap
		VertexHeap heap = new VertexHeap(g.numVertices());
		// Set Distance "from" to 0
		dist[von] = 0;
		// F�r jeden Eintrag in Dist, Erstelle einen vertex und f�ge es dem Heap hinzu.
		for (int i = 0; i < dist.length; i++)
		{
			Vertex v = new Vertex(i, dist[i]);
			heap.insert(v);

		}
		//Setze von Knoten "from" die Kosten auf 0
		heap.setCost(von, 0);
		// Solange der Heap nicht leer ist
		while (!heap.isEmpty())
		{
			//Erstes Element in Heap = current
			Vertex current = heap.remove();
			//Liste mit allen Kanten vom Konten "current"
			List<WeightedEdge> alleKanten = g.getEdges(current.vertex);
			//F�r jede Kante
			for (WeightedEdge kante: alleKanten)
			{
				//Aktuelle Kante + Kantengewicht = Ergebnis
				int ergebnis = dist[current.vertex] + kante.weight;
				//Eckkante
				int zielKante = kante.to_vertex;
				// Wenn Ergebnis kleiner als vorhandener Wert, �berschreibe Kosten mit Ergebnis.
				if (ergebnis < dist[zielKante])
				{
					//F�ge Zielkante zur �berpr�ft Liste hinzu.
					dist[zielKante] = ergebnis;
					heap.setCost(zielKante, ergebnis);
					pred[zielKante] = current.vertex;
				}
			}
		}
		// pred ausgeben
		for (int i = 0; i < pred.length; i++) {
			System.out.println(i + " �ber " + pred[i]);
		}
		// Way ausgeben
		System.out.println();
		ArrayList<Integer> way = predToWay(pred, von, nach);
		return way;
	}
	
	private static ArrayList<Integer> predToWay(int[] pred, int from, int to) {
		
		ArrayList<Integer> way = new ArrayList<Integer>();
		int temp = to;
		//Solange temp nicht From.
		while (temp != from)
		{
			//F�ge neue Kante immer als erstes in der Liste hinzu.
			way.add(0,temp);
			temp = pred[temp];
		}
		//F�ge From Kante als erstes in der Liste hinzu damit der Weg vollst�ndig ist.
		way.add(0,from);
		return way;
	}

	

}
