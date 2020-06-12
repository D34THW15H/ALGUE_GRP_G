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
		//For Schleife die so lange laeuft solange i kleiner als die Groeße des Graphen g ist, diese bricht ab falls ein Kreis gefunden wurde
		for (int i = 0; i < g.numVertices(); i++)
		{
			//Neue ArrayList zum speichern aller Werte i
			ArrayList<Integer> startKnoten = new ArrayList<Integer>();
			//Fuege i in die Liste Startknoten hinzu
			startKnoten.add(i);
			//ArrayList um zu ueberpruefen ob im Graphen eine Kreisstrecke existiert
			ArrayList<Integer> kreis = sucheEinenKreis(startKnoten);
			//Wenn Kreis nicht mehr null retourniert
			if (kreis != null)
			{
				//Gib die Kreisstrecke aus
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
		//Waehle aktuellen Knoten aus = letzter Knoten der bisherig erstellten Liste
		int aktuellerKnoten = aktuellerKnotenBisherigerPfad(besuchteKnoten);
		//Uebernimm den bisherigen Pfad des Graphen
		ArrayList<Integer> bisherigerPfad = pfadBisZumAktuellenKnoten(besuchteKnoten);
		//Wenn der bisherige Pfad den aktuellen Knoten beinhaltet
		if (bisherigerPfad.contains(aktuellerKnoten))
		{
			//Erstelle eine neue ArrayList Kreis
			ArrayList<Integer> Kreis = new ArrayList<>();
			//Gehe auf jeden Knoten solange i kleiner ist als die uebergebene Liste
			for (int i = besuchteKnoten.indexOf(aktuellerKnoten); i < besuchteKnoten.size(); i++)
			{
				//Fuege die besuchten Knoten in die Liste Kreis hinzu
				Kreis.add(besuchteKnoten.get(i));
			}
			//Retourniere die Liste
			return Kreis;
		}

		//Suche für jeden Knoten weitere moegliche Knoten
		for (Integer Knoten : sucheMoeglicheKnoten(besuchteKnoten)) {
			//Kopiere die Liste besuchte Knoten in die Liste neuer Pfad
			ArrayList<Integer> neuerPfad = (ArrayList<Integer>) besuchteKnoten.clone();
			neuerPfad.add(Knoten);
			//Erstelle eine neue Liste result und rufe damit rekursiv die Methode sucheEinenKreis auf
			ArrayList<Integer> result = sucheEinenKreis(neuerPfad);
			//Wenn die Liste result nicht null ist retourniere die Liste
			if (result != null)
				return result;
		}
		return null;
	}

	private ArrayList<Integer> pfadBisZumAktuellenKnoten(ArrayList<Integer> besuchteKnoten)
	{
		//Erstelle neue Liste mit dem bisherigen Pfad
		ArrayList<Integer> bisherigerPfad = new ArrayList<Integer>();
		//Fuege alle bisherig besuchten Knoten hinzu und retourniere die Liste
		for (int i = 0; i < besuchteKnoten.size() - 1; i++)
		{
			bisherigerPfad.add(besuchteKnoten.get(i));
		}
		return bisherigerPfad;
	}

	private ArrayList<Integer> sucheMoeglicheKnoten(ArrayList<Integer> besuchteKnoten)
	{
		//Uebernimm den aktuellen Knoten des bisherigen Pfades
		int aktuellerKnoten = aktuellerKnotenBisherigerPfad(besuchteKnoten);
		//Erstelle eine Liste mit allen möglichen weiteren Knoten des Graphen
		ArrayList<Integer> möglicheKnoten = this.sucheNaechsteKnoten(aktuellerKnoten);
		//Loesche den Knoten von dem wir kommen aus der Liste
		if (!g.isDirected()&&besuchteKnoten.size()>1){
			int ausgangsknoten = besuchteKnoten.get(besuchteKnoten.size()-2);
			möglicheKnoten.remove((Integer) ausgangsknoten);
		}
		return möglicheKnoten;
	}

	private int aktuellerKnotenBisherigerPfad (ArrayList<Integer> besuchteKnoten)
	{
		//Retourniere den Wert des letzten besuchten Knotens
		return besuchteKnoten.get(besuchteKnoten.size() - 1);
	}

	private ArrayList<Integer> sucheNaechsteKnoten(int aktuellerKnoten)
	{
		//Erstelle eine neue Liste für die naechsten Knoten
		ArrayList<Integer> naechsteKnoten = new ArrayList<>();
		//Für jede gewichtete Kante die vom aktuellen Knoten weg gehen
		for (WeightedEdge Edge : g.getEdges(aktuellerKnoten)) {
			//Wenn der Graph gerichtet ist
			if (g.isDirected())
			{
				//Wenn die Kante nicht auf den aktuellen Knoten zeigt
				if (Edge.to_vertex != aktuellerKnoten) {
					//Fuege den Knoten in die Liste der naechsten Knoten hinzu
					naechsteKnoten.add(Edge.to_vertex);
				}
			}
			//Sonst
			else
			{
				//Wenn die Kante nicht auf den aktuellen Knoten zeigt
				if (Edge.to_vertex != aktuellerKnoten)
					//Fuege den Knoten hinzu
					naechsteKnoten.add(Edge.to_vertex);

					//Wenn die Kante nicht vom aktuellen Knoten kommt
				else if (Edge.from_vertex != aktuellerKnoten)
					//Fuege den Knoten hinzu
					naechsteKnoten.add(Edge.from_vertex);
			}
		}
		//Retourniere die Liste mit den naechsten moeglichen Knoten
		return naechsteKnoten;
	}
}