package A01_Stack;



import java.util.EmptyStackException;

public class Stack<T>
{
     private int counter=0;
	 private Node<T> first;
    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException 
     */
    public T pop() throws StackEmptyException {
        // Store first to return it afterwards
        Node<T> todelete = first;
        // CHeck if stack is empty
        if(first == null)
        {
            throw new StackEmptyException("Error: First Entry not found!");
        }
        // überprüfe ob es ein NEXT gibt
        if(counter > 1)
        {
            // Next becomes First therefore first is deleted
            first = first.getNext();

        }
        // if no next then first = null => Stack is empty
        else
        {
            first = null;
        }
        // set counter -1
        counter--;
        // as mentioned upper element needs to be returned
        return todelete.getData();
    }
    
    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {
        //New Node Element
        Node<T> data = new Node<>(i);
        //Set next Element
        data.setNext(first);
        //Save first as next Element
        first = data;
        counter++;
    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
        return counter;
    }
}
