package A07_BubbleSort;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		if (personen == null)
		{
			return;
		}
		Person tmpPerson1;
		for (int i=0; i < personen.length;i++)
		{
			for (int j = 0; j<personen.length-i;j++)
			{
				if(j+1 == personen.length)
				{
					break;
				}
				int sortResult = personen[j].compareTo(personen[j+1]);
				if (sortResult > 0)
				{
					tmpPerson1 = personen[j];
					personen[j] = personen[j+1];
					personen[j+1] = tmpPerson1;
				}
			}
		}
	}
	
}
