import java.util.Iterator;

public interface LinkedListInterface<Type> extends Iterable<Type> {

    public void add(int index, Type element) throws IndexOutOfBoundsException;

    public boolean contains(Type value);

    public Boolean isEmpty();

    public void addFirst(Type type);

    public void addLast(Type type);

    public void removeFirst();

    public void removeLast();

    public Type remove(int index) throws IndexOutOfBoundsException ;

    public int size();

    @Override
    public Iterator<Type> iterator();
}


