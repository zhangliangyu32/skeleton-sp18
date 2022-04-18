public class ArrayDeque<T> {
    private T[] mem;
    private int size;
    private int front;
//    tail is the place right after the last element.
    private int tail;
    private int rFactor;
    private int memSize;
//  the array is circular
    public ArrayDeque() {
        mem = null;
        size = 0;
        memSize = 8;
        rFactor = 2;
        front = 0;
        tail = 0;
    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return mem[(front + index) % memSize];
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public void printDeque() {
        int index = 0;
        while (index < size) {
            System.out.print(get(index));
            System.out.print(' ');
            index++;
        }
    }

    public int size() {
        return size;
    }

    private void resize(int flag) {
        //flag==0 means increase memSize, flag==1 means decrease memSize
        //reassign front and tail while resizing
        if (flag == 0) {
            memSize *= rFactor;
            T[] tmpMem = (T[]) new Object[memSize];
            for (int i = 0; i < size; i++) {
                tmpMem[i] = get(i);
            }
            front = 0;
            tail = size;
            mem = tmpMem;
        } else if (flag == 1) {
            if (memSize <= 16) {
                return;
            }
            memSize /= rFactor;
            T[] tmpMem = (T[]) new Object[memSize];
            for (int i = 0; i < size; i++) {
                tmpMem[i] = get(i);
            }
            front = 0;
            tail = size;
            mem = tmpMem;
        }
    }

    public void addFirst(T item) {
        if (size == 0) {
            mem = (T[]) new Object[8];
        }
        if (size + 1 > memSize) {
            resize(0);
        }
        size += 1;
        front = (front - 1 + memSize) % memSize;
        mem[front] = item;
    }

    public void addLast(T item) {
        if (size == 0) {
            mem = (T[]) new Object[8];
        }
        if (size + 1 > memSize) {
            resize(0);
        }
        size += 1;
        tail = (tail + 1) % memSize;
        mem[tail - 1] = item;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (size - 1 < memSize / 2 && size > 16) {
            resize(1);
        }
        size -= 1;
        T tmp = mem[front];
        front = (front + 1) % memSize;
        return tmp;
    }
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (size - 1 < memSize / 2 && size > 16) {
            resize(1);
        }
        size -= 1;
        T tmp = mem[tail - 1];
        tail = (tail - 1 + memSize) % memSize;
        return tmp;
    }
}
