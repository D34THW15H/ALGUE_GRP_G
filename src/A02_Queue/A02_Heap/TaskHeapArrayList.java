package A02_Heap;

import A02_Queue.A02_Heap.Task;

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
		//Wenn ArrayList leer kein siftUp notwendig
		if (tasks.isEmpty()){
			tasks.add(t);
		} else {
			tasks.add(t);
			//Nach oben im Heap durchgehen
			siftUp();
		}
	}

	/**
	 * Das oberste Element (mit kleinster Priorität entfernen)
	 * @return Task mit kleinster Priorität
	 */

	public Task remove() {
		//Wenn ArrayList leer return null
		if(tasks.isEmpty()){
			return null;
		}
		//k = letztes Element in der ArrayList
		Task k = tasks.get(tasks.size() - 1);
		//p = root Element
		Task p = tasks.get(0);
		//Tausche die beiden Elemente
		tasks.set(tasks.size() -1, p);
		tasks.set(0, k);
		//Speichere das gelöschte Element in temp
		Task temp = tasks.remove(tasks.size()-1);
		//Nach unten im Heap durchgehen
		siftDown();
		//return das gelöschte Element mit kleinster Priorität
		return temp;
	}

	private void siftUp(){
		//k = letztes Element in der ArrayList
		int k = tasks.size() - 1;
		//Solange k größer als 0 ist
		while(k>0){
			//p = Parent Element von k
			int p = (k-1)/2;
			//item = k
			Task item = tasks.get(k);
			//parent = p
			Task parent = tasks.get(p);
			//Wenn item kleiner als parent
			if(item.getPriority() < parent.getPriority()){
				//Tausche
				tasks.set(k, parent);
				tasks.set(p, item);
				//Setze k als neues item (Um nach oben im Heap zu gehen)
				k = p;
				//Ansonsten brich die Schleife ab
			} else {
				break;
			}
		}
	}

	private void siftDown(){
		//Setze k = root
		int k = 0;
		//Setze left (left von k)
		int left = 1;
		//Solange left kleiner als die Größe der ArrayList ist
		while (left < tasks.size()){
			//Setze max auf left
			int max = left;
			//Setze right (right von k)
			int right = left+1;
			//Wenn right kleiner ist als die Größe der ArrayList
			if(right < tasks.size()){
				//Wenn left größer als right setze max von left auf right
				if(tasks.get(left).getPriority() > tasks.get(right).getPriority()){
					max++;
				}
			}
			//Wenn k größer ist als max
			if(tasks.get(k).getPriority() > tasks.get(max).getPriority()){
				//Speichere Wert von k unter temp
				Task temp = tasks.get(k);
				//Tausche die Elemente
				tasks.set(k, tasks.get(max));
				tasks.set(max, temp);
				//Setze left um eine Ebene nach unten
				left = 2*k+1;
				//Ansonsten brich die Schleife ab
			}else{
				break;
			}
		}
	}
}
