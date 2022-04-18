public class ArrayDeque <T>{
    private T[] mem;
    private int size;
    private int front;
    private int tail;
    private int R_factor;
    private int mem_size;
//  the array is circular
    public ArrayDeque()
    {
        mem = null;
        size = 0;
        mem_size = 8;
        R_factor = 2;
        front = 0;
        tail = 0;
    }
    public T get(int index)
    {
        if (index >= size)
            return null;
        return mem[(front + index) % mem_size];
    }

    public boolean isEmpty()
    {
        return (size == 0);
    }

    public void printDeque()
    {
        int index = 0;
        while (index < size)
        {
            System.out.print(get(index));
            System.out.print(' ');
            index++;
        }
    }

    public int size()
    {
        return size;
    }

    private void resize(int flag)
    {
        //flag==0 means increase mem_size, flag==1 means decrease mem_size
        //reassign front and tail while resizing
        if (flag == 0)
        {
            mem_size *= R_factor;
            T[] tmp_mem = (T[]) new Object[mem_size];
            for(int i = 0; i < size; i++)
            {
                tmp_mem[i] = get(i);
            }
            front = 0;
            tail = size - 1;
            mem = tmp_mem;
        }
        else if (flag == 1)
        {
            if (mem_size <= 16)
                return;
            mem_size /= R_factor;
            T[] tmp_mem = (T[]) new Object[mem_size];
            for(int i = 0; i < size; i++)
            {
                tmp_mem[i] = get(i);
            }
            front = 0;
            tail = size - 1;
            mem = tmp_mem;
        }
    }

    public void addFirst(T item)
    {
        if (size == 0)
        {
            mem = (T[]) new Object[8];
        }
        if (size + 1 > mem_size)
        {
            resize(0);
        }
        size += 1;
        front = (front - 1 + mem_size) % mem_size;
        mem[front] = item;
    }

    public void addLast(T item)
    {
        if (size == 0)
        {
            mem = (T[]) new Object[8];
        }
        if (size + 1 > mem_size)
        {
            resize(0);
        }
        size += 1;
        tail = (tail + 1) % mem_size;
        mem[tail] = item;
    }

    public T removeFirst()
    {
        if (size == 0)
        {
            return null;
        }
        if (size - 1 < mem_size / 2 && size > 16)
        {
            resize(1);
        }
        size -= 1;
        T tmp = mem[front];
        front = (front + 1) % mem_size;
        return tmp;
    }
    public T removeLast()
    {
        if (size == 0)
        {
            return null;
        }
        if (size - 1 < mem_size / 2 && size > 16)
        {
            resize(1);
        }
        size -= 1;
        T tmp = mem[tail];
        tail = (tail - 1 + mem_size) % mem_size;
        return tmp;
    }
}
