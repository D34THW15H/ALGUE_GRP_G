package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{

    private Node<T> first;
    private Node<T> current;
    private Node<T> last;
    private int counter = 0;


    /**
     * Einfügen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {
        Node<T> newNode = new Node<T>(a);

        if (getFirst() == null) {
            first = newNode;
        } else {
            newNode.setPrevious(last);
            last.setNext(newNode);
        }
        last = newNode;

    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {
            current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
            current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
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
        if(last != null){
            return last;
        } else {
            return null;
        }

    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {
        T zeiger;
        if(current != null){
            zeiger = current.getData();
            moveNext();
            return zeiger;
        }
        return null;
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {
        T zeiger;
        if(current != null){
            zeiger = current.getData();
            movePrevious();
            return zeiger;
        }
        return null;
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext(){

        if(current != null){
            current = current.getNext();
        }

    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {

        if(current != null){
            current = current.getPrevious();
        }

    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        try{
            return current.getData();
        } catch (Exception e){
            throw new CurrentNotSetException();
        }
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {

        T zeiger;
        reset();

        for (int i = 0; i < pos;i++){
            if(current.getNext() != null) {
                moveNext();
            } else {
                return null;
            }
        }

        zeiger = current.getData();

        return zeiger;
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) throws CurrentNotSetException
    {
        reset();
        for (int i = 0; i < pos; i++)
        {
            moveNext();
        }
        removeCurrent();
        moveNext();
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
        if (current == null)
            throw new CurrentNotSetException();

        if (current.getPrevious() != null) {
            current.getPrevious().setNext(current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrevious(current.getPrevious());
                current = current.getNext();
            } else {
                last = current.getPrevious();
                current = current.getPrevious();
            }
        } else {
            first = current.getNext();
            if (first != null) {
                first.setPrevious(null);
            }
            if (current != null) {
                current = current.getNext();
            }
        }

    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {
        try
        {
            Node<T> n = new Node<T>(a);

            n.setNext(current.getNext());
            n.setPrevious(current);

            if (current.getNext() != null)
                current.getNext().setPrevious(n);

            current.setNext(n);
            moveNext();
        }

        catch (Exception e)
        {
            throw new CurrentNotSetException();
        }
    }
}
