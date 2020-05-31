package A02_Queue.A02_Heap;

import java.util.ArrayList;

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
		//add Task to private tasks ArrayList
		if (tasks.isEmpty()){
			tasks.add(t);
		} else {
			tasks.add(t);
			siftUp();
		}
	}

	/**
	 * Das oberste Element (mit kleinster Priorität entfernen)
	 * @return Task mit kleinster Priorität
	 */
	public Task remove() {
		//Check if ArrayList is Empty
		if(tasks.isEmpty()){
			return null;
		}
		Task k = tasks.get(tasks.size() - 1);
		Task p = tasks.get(0);
		tasks.set(tasks.size() -1, p);
		tasks.set(0, k);

		Task temp = tasks.remove(tasks.size()-1);
		siftDown();

		return temp;
	}



	private void siftUp(){
		int k = tasks.size() - 1;

		while(k>0){
			int p = (k-1)/2;
			Task item = tasks.get(k);
			Task parent = tasks.get(p);

			if(item.getPriority() < parent.getPriority()){
				//swap
				tasks.set(k, parent);
				tasks.set(p, item);
				k = p;
			} else {
				break;
			}
		}
	}

	private void siftDown(){
		int k = 0;
		int left = 2*k+1;
		while (left < tasks.size()){
			int max = left, right = left+1;
			if(right < tasks.size()){
				if(tasks.get(left).getPriority() > tasks.get(right).getPriority()){
					max++;
				}
			}
			if(tasks.get(k).getPriority() > tasks.get(max).getPriority()){
				Task temp = tasks.get(k);
				tasks.set(k, tasks.get(max));
				tasks.set(max, temp);
				k=max;
				left = 2*k+1;
			}else{
				break;
			}
		}
	}

}
