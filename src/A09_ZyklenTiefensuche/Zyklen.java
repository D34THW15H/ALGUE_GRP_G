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
	 * @param //g zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	public List<Integer> getCycle() {
		//For Schleife die so lange läuft solange i kleiner als die Größe des Graphen g ist, diese bricht ab falls ein Kreis gefunden wurde
		for (int i = 0; i < g.numVertices(); i++)
		{
			//Neue ArrayList zum speichern aller Werte i
			ArrayList<Integer> graph = new ArrayList<Integer>();
			//Füge i in den Graph hinzu
			graph.add(i);
			//ArrayList um zu überprüfen ob im graph eine Kreisstrecke exisiter
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
		//Wähle aktuellen Knoten aus = letzter Knoten der bisherig erstellten Liste
		int aktuellerKnoten = aktuellerKnotenBisherigerPfad(besuchteKnoten);
		//Übernimm den bisherigen Pfad des Graphen
		ArrayList<Integer> bisherigerPfad = PfadBisZumAktuellenKnoten(besuchteKnoten);
		//Wenn der bisherige Pfad den aktuellen Knoten beinhaltet
		if (bisherigerPfad.contains(aktuellerKnoten))
		{
			//Erstelle einer neue ArrayList Kreis
			ArrayList<Integer> Kreis = new ArrayList<>();
			//Gehe auf jeden Knoten solange i kleiner ist als die übergebene Liste
			for (int i = besuchteKnoten.indexOf(aktuellerKnoten); i < besuchteKnoten.size(); i++)
			{
				//Füge die besuchten Knoten in die Liste Kreis hinzu
				Kreis.add(besuchteKnoten.get(i));
			}
			//Retourniere die Liste
			return Kreis;
		}

		//Suche für jeden Knoten weitere mögliche Knoten
		for (Integer Knoten : this.sucheMöglicheKnoten(besuchteKnoten)) {
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
		//Füge alle bisherig besuchten Knoten hinzu und retourniere die Liste
		for (int i = 0; i < besuchteKnoten.size() - 1; i++)
		{
			bisherigerPfad.add(besuchteKnoten.get(i));
		}
		return bisherigerPfad;
	}

	private ArrayList<Integer> sucheMöglicheKnoten(ArrayList<Integer> besuchteKnoten)
	{
		//Übernimm den aktuellen Knoten des bisherigen Pfades
		int aktuellerKnoten = aktuellerKnotenBisherigerPfad(besuchteKnoten);
		//Erstelle eine Liste mit allen möglichen weiteren Knoten des Graphen
		ArrayList<Integer> möglicheKnoten = this.sucheNächsteKnoten(aktuellerKnoten);
		//Wenn der Graph nicht gerichtet ist und die besuchten Knoten größer als 1 sind
		if (!g.isDirected() && besuchteKnoten.size() > 1)
		{
			//Löschen den Knoten von dem wir kommen aus der Liste
			int vorherigeKnoten = sucheKnotenN(besuchteKnoten, 1);
			möglicheKnoten.remove((Integer) vorherigeKnoten);
		}
		//Retourniere alle möglichen Knoten
		return möglicheKnoten;
	}

	private int aktuellerKnotenBisherigerPfad (ArrayList<Integer> besuchteKnoten)
	{
		//Retourniere den Wert des letzten besuchten Knotens
		return besuchteKnoten.get(besuchteKnoten.size() - 1);
	}

	private int sucheKnotenN(ArrayList<Integer> besuchteKnoten, int n)
	{
		//wenn n größer gleich die Anzahl der besuchten Knoten ist
		if (n >= besuchteKnoten.size()) {
			//Setze n auf 0
			n = 0;
		}
		//Ansonsten retourniere Knoten
		return besuchteKnoten.get(besuchteKnoten.size() - 1 - n);
	}

	private ArrayList<Integer> sucheNächsteKnoten(int aktuellerKnoten)
	{
		//Erstelle eine neue Liste für die nächsten Knoten
		ArrayList<Integer> nächsteKnoten = new ArrayList<>();
		//Für jede gewichtete Kante die vom aktuellen Knoten weg gehen
		for (WeightedEdge Edge : g.getEdges(aktuellerKnoten)) {
			//Wenn der Graph gerichtet ist
			if (g.isDirected())
			{
				//Wenn die Kante nicht auf den aktuellen Knoten zeigt
				if (Edge.to_vertex != aktuellerKnoten)
					//Füge den Knoten in die Liste der nächsten Knoten hinzu
					nächsteKnoten.add(Edge.to_vertex);
			}
			//Sonst
			else
			{
				//Wenn die Kante nicht auf den aktuellen Knoten zeigt
				if (Edge.to_vertex != aktuellerKnoten)
					//Füge den Knoten hinzu
					nächsteKnoten.add(Edge.to_vertex);
					//Wenn die Kante nicht vom aktuellen Knoten kommt
				else if (Edge.from_vertex != aktuellerKnoten)
					//Füge den Knoten hinzu
					nächsteKnoten.add(Edge.from_vertex);
			}
		}
		//Retourniere die Liste mit den nächsten möglichen Knoten
		return nächsteKnoten;
	}
}