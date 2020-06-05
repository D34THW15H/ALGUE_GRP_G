package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{

    private Node<T> first;
    private Node<T> current;
    private Node<T> last;
    private int counter = 0;


    /**
     * Einf�gen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {
        //Erstelle ein neues Node<T> mit dem �bergebenen Node a
        Node<T> newNode = new Node<T>(a);

        //wenn die erste Node leer ist wird Sie mit der neu generierten Node bef�llt
        if (getFirst() == null) {
            first = newNode;
        } else {
            //beim neuen Node wird das lezte Node als das vorherige Node gesetzt
            newNode.setPrevious(last);
            //beim vorherigen Node wird dann das neue Node als n�chstes gesetzt
            last.setNext(newNode);
        }
        //das new Node wird als neues Last gesetzt
        last = newNode;

    }

    /**
     * Internen Zeiger f�r next() zur�cksetzen
     */
    public void reset() {
            //Zeiger f�r current setzt auf das erste Node zur�ck
            current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        //Zeiger f�r current wird auf das letzte Node gesetzt
        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
        //wenn das erste Node nicht leer ist wird es direkt zur�ckgeliefert, ansonsten wird ein null zur�ckgegeben
        if(first != null){
            return first;
        } else {
            return null;
        }
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
        //wenn die Node last nicht leer ist wird sie retour geliefert, ansonsten null
        if(last != null){
            return last;
        } else {
            return null;
        }

    }
    
    /**
     * Gibt aktuelle <T> zur�ck und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {
        //T zeiger initialisieren f�r einen m�glichen return Wert
        T zeiger;
        /** wenn das aktuelle Node nicht leer ist wird der zeiger mit den Daten des aktuellen Node gef�llt,
         * danach wird mit moveNext auf das n�chste Node gezeigt und am Ende wird der gef�llte Node zeiger zur�ckgeliefert
         */
        if(current != null){
            zeiger = current.getData();
            moveNext();
            return zeiger;
        }
        //ansonsten wird null zur�ckgeliefert
        return null;
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {
        //T zeiger wird initialisiert f�r einen m�glichen return Wert
        T zeiger;
        /**
         * wenn das aktuelle Node nicht leer ist wird der zeiger mit den Daten des aktuellen Node gef�llt,
         * danach wird mit movePrevious auf das vorherige Node gezeigt und der gef�llte Node zeiger zur�ckgeliefert
         */
        if(current != null){
            zeiger = current.getData();
            movePrevious();
            return zeiger;
        }
        //ansonsten wird null zur�ckgeliefert
        return null;
    }
    
    /**
     * Current-Pointer auf n�chste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext(){
        //wenn current nicht null, dann wird versucht current auf das n�chste Node zu setzen
        if(current != null){
            current = current.getNext();
        }

    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        //wenn current nicht null, dann wird versucht current auf das vorherige Node zu setzten
        if(current != null){
            current = current.getPrevious();
        }

    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu �ndern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        //Es wird versucht die Daten des aktuellen Node zur�ckzuliefern
        // bei einem Fehler wird eine CurrentNotSetException geworfen

        try{
            return current.getData();
        } catch (Exception e){
            throw new CurrentNotSetException();
        }
    }

    /**
     * Gibt <T> an bestimmter Position zur�ck
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {


        //zeiger wird initialisiert f�r eine m�gliche R�ckgabe
        T zeiger;
        //mit reset wird current auf das erste Node zur�ckgesetzt
        reset();

        //Schleife l�uft so lange bis man and die angeforderte Position kommt
        for (int i = 0; i < pos;i++){
            //solange es ein n�chstes Node gibt wird move next durchgef�hrt
            if(current.getNext() != null) {
                moveNext();
            } else {
                //wenn das n�chste Element nicht vorhanden ist wird null zur�ckgeliefert
                return null;
            }
        }

        //zeiger wird mit den aktuellen Node Daten bef�llt
        zeiger = current.getData();
        //Wert von zeiger wird zur�ckgeliefert
        return zeiger;
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) throws CurrentNotSetException
    {
        //current wird mit reset auf das erste Node zur�ckgesetzt
        reset();
        //Schleife l�uft solange bis man an der �bergebenen Pos angekommen ist
        for (int i = 0; i < pos; i++)
        {
            moveNext();
        }
        //removeCurrent wird aufgerufen um das aktuelle Node zu entfernen
        removeCurrent();
        //current wird mit moveNext auf das n�chste Element gesetzt
        moveNext();
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
        //wenn das aktuelle Node null ist wird eine CurrentNotSetException geworfen
        if (current == null)
            throw new CurrentNotSetException();

        //wenn das vorherige Node nicht null ist
        // wird beim vorherigen Node das n�chste Node vom aktuellen auf dies gesetzt
        if (current.getPrevious() != null) {
            current.getPrevious().setNext(current.getNext());
            //wenn das n�chste Node nicht leer ist
            //wird beim n�chsten Node der Wert des vorherigen des aktuellen Node gesetzt
            if (current.getNext() != null) {
                current.getNext().setPrevious(current.getPrevious());
                //current wird auf das n�chste Node gesetzt
                current = current.getNext();
            } else {
                //wenn current das letzte Node war wird last auf das vorherige Node vom aktuellen gesetzt
                last = current.getPrevious();
                //current wird auf das vorige Node gesetzt
                current = current.getPrevious();
            }
        } else {
            //das first Node wird auf das n�chste Node des aktuellen gesetzt
            first = current.getNext();
            //wenn first ungleich null wird das vorige Node von first auf null gesetzt
            if (first != null) {
                first.setPrevious(null);
            }
            //wenn current ungleich null wird current auf das n�chste Element gesetzt
            if (current != null) {
                current = current.getNext();
            }
        }

    }
    
    /**
     * Die Methode f�gt die �bergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingef�gte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {
        try
        {
            //n Node generieren mit �bergebenem a Node
            Node<T> n = new Node<T>(a);

            //beim neuen Node next setzten vom current Node
            n.setNext(current.getNext());
            //beim neuen Node das vorige setzen mit dem current Node
            n.setPrevious(current);

            //wenn das n�chste Node nach current nicht null
            if (current.getNext() != null)
                //setze beim n�chsten Node von current den vorigen Wert auf das neue n Node
                current.getNext().setPrevious(n);

            //das aktuelle Node bekommt das neue Node als n�chstes Node
            current.setNext(n);
            //current auf das n�chste Node setzen
            moveNext();
        }

        catch (Exception e)
        {
            throw new CurrentNotSetException();
        }
    }
}
