package A09_ZyklenTiefensuche;

import java.util.ArrayList;
import java.util.List;

import basisAlgorithmen.Graph;
import basisAlgorithmen.WeightedEdge;

public class Zyklen {

	private Graph g;

	public Zyklen(Graph g) {
		this.g = g;
	}

	/**
	 * Retourniert einen Zyklus eines Graphen, sofern einer existiert
	 * @param //g zu pr�fender Graph
	 * @return Anzahl der Komponenten
	 */
	public List<Integer> getCycle() {
		//For Schleife die so lange l�uft solange i kleiner als die Gr��e des Graphen g ist, diese bricht ab falls ein Kreis gefunden wurde
		for (int i = 0; i < g.numVertices(); i++)
		{
			//Neue ArrayList zum speichern aller Werte i
			ArrayList<Integer> graph = new ArrayList<Integer>();
			//F�ge i in den Graph hinzu
			graph.add(i);
			//ArrayList um zu �berpr�fen ob im graph eine Kreisstrecke exisiter
			ArrayList<Integer> kreis = sucheEinenKreis(graph);
			//Wenn Kreis nicht mehr null retourniert
			if (kreis != null)
			{
				//Gebe die Kreisstrecke aus
				System.out.println(kreis);
				//und retourniere die ArrayList kreis
				return kreis;
			}

		}
		//Wenn kein Kreis gefunden wurde retourniere null
		return null;
	}

	private ArrayList<Integer> sucheEinenKreis(ArrayList<Integer> besuchteKnoten)
	{
		//W�hle aktuellen Knoten aus = letzter Knoten der bisherig erstellten Liste
		int aktuellerKnoten = aktuellerKnotenBisherigerPfad(besuchteKnoten);
		//�bernimm den bisherigen Pfad des Graphen
		ArrayList<Integer> bisherigerPfad = PfadBisZumAktuellenKnoten(besuchteKnoten);
		//Wenn der bisherige Pfad den aktuellen Knoten beinhaltet
		if (bisherigerPfad.contains(aktuellerKnoten))
		{
			//Erstelle einer neue ArrayList Kreis
			ArrayList<Integer> Kreis = new ArrayList<>();
			//Gehe auf jeden Knoten solange i kleiner ist als die �bergebene Liste
			for (int i = besuchteKnoten.indexOf(aktuellerKnoten); i < besuchteKnoten.size(); i++)
			{
				//F�ge die besuchten Knoten in die Liste Kreis hinzu
				Kreis.add(besuchteKnoten.get(i));
			}
			//Retourniere die Liste
			return Kreis;
		}

		//Suche f�r jeden Knoten weitere m�gliche Knoten
		for (Integer Knoten : this.sucheM�glicheKnoten(besuchteKnoten)) {
			//Kopiere die Liste besuchte Knoten in die Liste neuer Pfad
			ArrayList<Integer> neuerPfad = (ArrayList<Integer>) besuchteKnoten.clone();
			neuerPfad.add(Knoten);
			//Erstelle eine neue Liste Kreispfad
			ArrayList<Integer> Kreispfad = this.sucheEinenKreis(neuerPfad);
			//Wenn die Liste Kreispfad nicht null ist retourniere die Liste
			if (Kreispfad != null)
				return Kreispfad;
		}

		return null;
	}

	private ArrayList<Integer> PfadBisZumAktuellenKnoten(ArrayList<Integer> besuchteKnoten)
	{
		//Erstelle neue Liste mit dem bisherigen Pfad
		ArrayList<Integer> bisherigerPfad = new ArrayList<Integer>();
		//F�ge alle bisherig besuchten Knoten hinzu und retourniere die Liste
		for (int i = 0; i < besuchteKnoten.size() - 1; i++)
		{
			bisherigerPfad.add(besuchteKnoten.get(i));
		}
		return bisherigerPfad;
	}

	private ArrayList<Integer> sucheM�glicheKnoten(ArrayList<Integer> besuchteKnoten)
	{
		//�bernimm den aktuellen Knoten des bisherigen Pfades
		int aktuellerKnoten = aktuellerKnotenBisherigerPfad(besuchteKnoten);
		//Erstelle eine Liste mit allen m�glichen weiteren Knoten des Graphen
		ArrayList<Integer> m�glicheKnoten = this.sucheN�chsteKnoten(aktuellerKnoten);
		//Wenn der Graph nicht gerichtet ist und die besuchten Knoten gr��er als 1 sind
		if (!g.isDirected() && besuchteKnoten.size() > 1)
		{
			//L�schen den Knoten von dem wir kommen aus der Liste
			int vorherigeKnoten = sucheKnotenN(besuchteKnoten, 1);
			m�glicheKnoten.remove((Integer) vorherigeKnoten);
		}
		//Retourniere alle m�glichen Knoten
		return m�glicheKnoten;
	}

	private int aktuellerKnotenBisherigerPfad (ArrayList<Integer> besuchteKnoten)
	{
		//Retourniere den Wert des letzten besuchten Knotens
		return besuchteKnoten.get(besuchteKnoten.size() - 1);
	}

	private int sucheKnotenN(ArrayList<Integer> besuchteKnoten, int n)
	{
		//wenn n gr��er gleich die Anzahl der besuchten Knoten ist
		if (n >= besuchteKnoten.size()) {
			//Setze n auf 0
			n = 0;
		}
		//Ansonsten retourniere Knoten
		return besuchteKnoten.get(besuchteKnoten.size() - 1 - n);
	}

	private ArrayList<Integer> sucheN�chsteKnoten(int aktuellerKnoten)
	{
		//Erstelle eine neue Liste f�r die n�chsten Knoten
		ArrayList<Integer> n�chsteKnoten = new ArrayList<>();
		//F�r jede gewichtete Kante die vom aktuellen Knoten weg gehen
		for (WeightedEdge Edge : g.getEdges(aktuellerKnoten)) {
			//Wenn der Graph gerichtet ist
			if (g.isDirected())
			{
				//Wenn die Kante nicht auf den aktuellen Knoten zeigt
				if (Edge.to_vertex != aktuellerKnoten)
					//F�ge den Knoten in die Liste der n�chsten Knoten hinzu
					n�chsteKnoten.add(Edge.to_vertex);
			}
			//Sonst
			else
			{
				//Wenn die Kante nicht auf den aktuellen Knoten zeigt
				if (Edge.to_vertex != aktuellerKnoten)
					//F�ge den Knoten hinzu
					n�chsteKnoten.add(Edge.to_vertex);
					//Wenn die Kante nicht vom aktuellen Knoten kommt
				else if (Edge.from_vertex != aktuellerKnoten)
					//F�ge den Knoten hinzu
					n�chsteKnoten.add(Edge.from_vertex);
			}
		}
		//Retourniere die Liste mit den n�chsten m�glichen Knoten
		return n�chsteKnoten;
	}
}