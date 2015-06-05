package lab2;


import java.util.ArrayList;


public class Hall
{
    enum States {FREE, SELECTED, RESERVED}
    private ArrayList<States> hall;

    public Hall(int size)
    {
        hall = new ArrayList<States>(size);
        for (int i = 0; i < size; i++) {
            hall.add(States.FREE);
        }
    }

    public synchronized boolean isFull()
    {
        for (States s : hall) {
            if (s != States.RESERVED)
                return false;
        }
        return true;
    }

    public synchronized int size()
    {
        return hall.size();
    }

    public void select(int index) throws Exception
    {
        if (hall.get(index) == States.FREE) {
            hall.set(index, States.SELECTED);
        } else {
            throw new Exception("Can't provide operation");
        }
    }

    public void reserve(int index) throws Exception
    {
        if (hall.get(index) == States.SELECTED) {
            hall.set(index, States.RESERVED);
        } else {
            throw new Exception("Can't provide operation");
        }
    }
}
