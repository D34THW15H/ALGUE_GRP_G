package A11_DijkstraDGShortestPath;


import java.util.ArrayList;
import java.util.List;

public abstract class FindWay {
	protected Graph graph;
	protected int[] pred;
	
	public FindWay(Graph graph) {
		this.graph = graph;
		this.pred = new int[graph.numVertices()];
	}

	/**
	 * Liefert den Weg von (from) nach (to) als Liste zur�ck
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg von Start nach Ziel oder null
	 */
	public List<Integer> findWay(int from, int to, boolean useChargeRoads) {
		initPathSearch();
		if (!calculatePath(from, to, useChargeRoads )) {
			return null;
		}
		return createWay(from, to);
	}

	/**
	 * Initialisierungsfunktion
	 */
	abstract protected void initPathSearch();

	/**
	 * Berechnungsfunktion f�r Weg von (from) nach (to)
	 */
	abstract protected boolean calculatePath(int from, int to, boolean useChargeRoads);
	
	/**
	 * Weg von (to) nach (from) aus Vorg�ngerknoten rekonstruieren
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg als Liste
	 */
	protected ArrayList<Integer> createWay(int from, int to) {
		ArrayList<Integer> way = new ArrayList<Integer>();
		int i = to;
		while (i != from) {
			way.add(0, i);
			i = pred[i];
		}
		way.add(0, from);
		return way;
	}
}
