package A06_Tiefensuche;

import java.util.*;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {
	@Override
	/*
	  Sortierkriterium im Baum: L�nge des Films
	 */

	protected int compare(Film a, Film b) {
		//L�nge von Film a
		double aLength = a.getL�nge();
		//L�nge von Film b
		double blength = b.getL�nge();
		//return Value
		int value = 0;
		//wenn gr��er - value 1
		if (aLength > blength)
		{
			value = 1;
		}
		//wenn kleiner - value -1
		else if (aLength < blength)
		{
			value = -1;
		}
		//sonst 0
		return value;

		//K�rzer
		//return Double.compare(a.getL�nge(),b.getL�nge());
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node) {
		//Arraylist mit allen Filmen
		ArrayList<Film> movies = getMoviesList(node);
		//Sortieren anhand der Filml�nge
		Collections.sort(movies, this::compare);
		//Neue Liste mit Titeln der Filme
		ArrayList<String> titles = new ArrayList<>();
		//F�r jeden Film - Hinzuf�gen des Titels der Liste
		for (Film filmNode :movies) {
			titles.add(filmNode.getTitel());
		}
		//Return der Titelliste
		return titles;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren L�nge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale L�nge des Spielfilms
	 * @param max Maximale L�nge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {
		//Root Element von Tiefensuche
		Node<Film> node = getRoot();
		//Arraylist mit allen Filmen
		ArrayList<Film> movies = getMoviesList(node);
		//Arraylist mit allen Titeln
		ArrayList<String> titels = new ArrayList<>();
		//F�r jeden Film
		for (Film film:movies) {
			//L�nge des Filmes
			double lengh = film.getL�nge();
			//Wenn L�nge des Filmes ist gr��er/gleich min & kleiner/gleich max dann zur Titelliste hinzuf�gen
			if (lengh >= min && lengh <=max)
			{
				titels.add(film.getTitel());
			}
		}
		//Return der Titelliste
		return titels;
	}
	private ArrayList<Film> getMoviesList(Node<Film>node) {
		//Arraylist mit allen Filmen
		ArrayList<Film> movies = new ArrayList<>();
		//Neuer Stack
		Stack<Node<Film>> stack = new Stack<>();
		//Aktueler Node = Startknoten
		Node<Film> currentNode = node;
		//Zum Stack hinzuf�gen
		stack.push(currentNode);
		//Solange Stack nicht leer
		while (!stack.isEmpty())
		{
			//FIFO - Neuer Current Node
			currentNode = stack.pop();
			//Current Node zur Arraylist hinzuf�gen
			movies.add(currentNode.getValue());
			// Wenn links und rechts leer - continue
			if (currentNode.getLeft() == null && currentNode.getRight() == null)
			{
				continue;
			}
			//Sonst
			else
			{
				//Wenn rechts nicht leer - hinzuf�gen zu Stack
				if (currentNode.getRight() != null)
				{
					stack.push(currentNode.getRight());
				}
				//Wenn links nicht leer - hinzuf�gen zu Stack
				if (currentNode.getLeft() != null)
				{
					stack.push(currentNode.getLeft());
				}
			}
		}
		//Return von allen Filmen in der Tiefensuche.
		return movies;
	}
}
