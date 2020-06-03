package A07_BubbleSort;

public class Person {
	
	private final String nachname;
	
	private final String vorname;

	public Person(String vorname, String nachname) {
		this.nachname = nachname;
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	/**
	 * Vergleicht zwei Personen miteinander
	 * @return <0, wenn a<b || =0, wenn a=b || >0, wenn a>b
	 */



	public int compareTo(Person p) {

		// überprüfe ob Nachname ident
		if( nachname == p.getNachname())
		{

			// überprüfe ob Vorname ident => 0
			if(vorname == p.getVorname())
			{
				return 0;
			}
			// wenn vorname von p kleiner vorname => 1
			else if (vorname.compareTo(p.getVorname()) > 0)
			{
				return 1;
			}
			// sonst -1 => nichtmehr interessant
			else
			{
				return -1;
			}


		}
		// Wenn nachname p kleiner nachname => 1
		else if (nachname.compareTo(p.getNachname()) > 0)
		{
			return 1;
		}
		// sonst => nicht interessant
		else {
			return -1;
		}
	}
}
