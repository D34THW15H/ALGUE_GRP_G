package A13_MergeSort;

import java.util.Arrays;


public class MergeSort implements PersonenSort {
	
	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {

		if(personen.length == 0){
			return;
		}

		// Start des rekursiven Aufrufs
		sort(personen, 0, personen.length-1);
	}

	/**
	 * Rekursive Funktion zum Sortieren eines Teils des Arrays
	 * @param personen Zu sortierendes Array
	 * @param start    Startpunkt im Array
	 * @param end      Endpunkt im Array
	 */
	public void sort(Person[] personen, int start, int end)
	{
		// TODO: Aufteilung & Rekursion implementieren
		//Pruefen ob übergebener End wert kleiner 1, wenn ja dann wird die Methode abgebrochen
		if(end < 1){
			return;
		}

		// hilfsvariable Mitte wird errechnet in dem man den übergebenen Wert End durch 2 dividiert
		int mitte = (end / 2);
		
		// Für Merge: Hälften in eigene Arrays kopieren
		// Hinweis: bei copyOfRange ist Obergrenze exklusiv, deshalb "+ 1"
		Person[] teil1 = Arrays.copyOfRange(personen, start, mitte+1);
		Person[] teil2 = Arrays.copyOfRange(personen, mitte+1, end+1);

		// wenn die laenge von teil1 größer 1 ist dann muss der teil nochmal sortiert / gesplittet werden
		if(teil1.length > 1) {
			sort(teil1, start, mitte);
		}
		// wenn die laenge von teil2 größer 1 ist dann muss der teil nochmal sortier / gesplittet werden
		if(teil2.length > 1) {
			sort(teil2, 0, (end - mitte)-1);
		}
		// Beide Hälften zusammenfügen und in data-Array schreiben
		merge(teil1, teil2, personen, start);
	}

	/**
	 * Merge zweier Arrays in ein Ergebnis-Array
	 * @param pers1 Erstes Array
	 * @param pers2 Zweites Array
	 * @param result Ergebnisarray
	 * @param start  Position für Speicherung in Ergebnisarray
	 */
	public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {

		// TODO: Merge implementieren
		//Hilfsvariablem i und j deklarieren mit Wert 0
		int i = 0, j = 0;

		// solange i kleiner der array laenge von pers1 & solange j kleiner der array laenge pers2
		while (i < pers1.length && j < pers2.length){
			// sortResult Variable
			// < 1 - pers1 an erster stelle
			// > 1 - pers2 an erster stelle
			// = 1 - beide Werte gleich
			int sortResult = pers1[i].compareTo(pers2[j]);
			if(sortResult > 0){
				result[start++] = pers2[j++];
			} else{
				result[start++] = pers1[i++];
			}
		}

		// wenn bei der ersten While schleife eines nicht wahr ist dann gibt es dafuer diese Extra checks
		// um jeweils ans ende der arrays zu gelangen.
		while( i < pers1.length){
			result[start++] = pers1[i++];
		}
		while(j < pers2.length){
			result[start++] = pers2[j++];
		}
	}

}
