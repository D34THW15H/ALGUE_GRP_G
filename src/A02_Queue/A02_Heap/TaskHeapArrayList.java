package A02_Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskHeapArrayList {

	/**
	 * Internes Task-Array für den Heap
	 * Ansonsten keine anderen Variablen verwenden!
	 */
	private ArrayList<Task> tasks;

	/**
	 * Konstruktor
	 */
	public TaskHeapArrayList() {
		tasks = new ArrayList<>();
	}

	/**
	 * Neuen Task in den Heap einfügen
	 * @param t Einzufügender Task
	 */
	public void insert(Task t) {
		tasks.add(t);

		Collections.sort(tasks, new Comparator<Task>() {
				@Override
				public int compare(Task o1, Task o2) {
					return Integer.compare(o1.getPriority(),o2.getPriority());
				}
		});
	}

	/**
	 * Das oberste Element (mit kleinster Priorität entfernen)
	 * @return Task mit kleinster Priorität
	 */
	public Task remove() {
			if (tasks.isEmpty()){
				return null;
			}
			return tasks.remove(0);
	}
}
