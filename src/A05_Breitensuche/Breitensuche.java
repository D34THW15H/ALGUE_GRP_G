package A05_Breitensuche;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Breitensuche extends BaseTree<Integer> {

	@Override
	protected int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
	 * @param start Startknoten für Teilbaum
	 * @return Liste der Knoten in Breitenfolge
	 */
	public List<Integer> getBreadthFirstOrder(Node<Integer> start) {

		Queue<Node<Integer>> queue = new LinkedList<>();
		List<Integer> result = new ArrayList<>();

		queue.add(start);

		Node<Integer> currentNode;
		Node<Integer> leftNode;
		Node<Integer> rightNode;

		while(true)
		{
			currentNode = queue.poll();
			if (currentNode == null)
				break;

			leftNode = currentNode.getLeft();
			if (leftNode != null)
			{
				queue.add(leftNode);
			}
			rightNode = currentNode.getRight();
			if (rightNode != null)
			{
				queue.add(rightNode);
			}
			result.add(currentNode.getValue());
		}


		return result;
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
	 * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
	 * @param start Startknoten für Teilbaum
	 * @param level Nur Knoten dieser Ebene ausgeben
	 * @return Liste aller Knoten
	 */
	public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {
		Queue<Node<Integer>> queue = new LinkedList<>();
		List<Integer> result = new ArrayList<>();
		int currentLevel = start.getNodeLevel();

		Node<Integer> currentNode = start;
		Node<Integer> leftNode;
		Node<Integer> rightNode;

		// Check ob level und start das selbe wenn ja mit parent anfangen
		if (currentLevel == level && start.getParent() != null)
		{
			level = level+1;

		}


		queue.add(currentNode);

		while(true) {
			currentNode = queue.poll();
			if (currentNode == null)
				break;

			if ((leftNode = currentNode.getLeft()) != null ) {
				if (leftNode.getNodeLevel() <= level) {
					queue.add(leftNode);
				}
			}
			if ((rightNode = currentNode.getRight()) != null ) {
				if (rightNode.getNodeLevel() <= level)
				{
					queue.add(rightNode);
				}
			}
			if (currentNode.getNodeLevel() == level)
			{
				result.add(currentNode.getValue());
			}

		}


		return result;
	}

}
