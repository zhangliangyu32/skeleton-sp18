public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;
        private int size;

        private Node(T it, Node pr, Node ne) {
            item = it;
            next = ne;
            prev = pr;
        }
    }

    private int size;
    //Use circular sentinel.
    private Node sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        Node tmp = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = tmp;
        sentinel.next = tmp;
        size += 1;
    }

    public void addLast(T item) {
        Node tmp = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = tmp;
        sentinel.prev = tmp;
        size += 1;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node tmp = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return tmp.item;
    }

    public T removeLast()
    {
        if (size == 0)
        {
            return null;
        }
        Node tmp = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return tmp.item;
    }

    public boolean isEmpty()
    {
        return (size == 0);
    }

    public int size()
    {
        return size;
    }

    public void printDeque()
    {
        Node tmp = sentinel.next;
        while (tmp != sentinel)
        {
            System.out.print(tmp.item);
            System.out.print(' ');
            tmp = tmp.next;
        }
    }

    public T get(int index)
    {
        if (index >= size)
        {
            return null;
        }
        Node tmp = sentinel.next;
        while (true)
        {
            if (index == 0)
            {
                return tmp.item;
            }
            tmp = tmp.next;
            index --;
        }
    }

    private T getRecursive(int index, Node begin)
    {
        if (index >= size)
        {
            return null;
        }
        else if (index == 0)
        {
            return begin.item;
        }
        else
        {
            return getRecursive(index - 1, begin.next);
        }
    }
    public T getRecursive(int index)
    {
        return getRecursive(index, sentinel.next);
    }
}
