package lab2;


import java.util.Random;


public class User extends Thread
{

    private Hall hall;
    private volatile int count;

    public User(Hall hall)
    {
        this.hall = hall;
    }

    @Override
    public void run()
    {
        Random random = new Random();
        while (!hall.isFull()) {
            int index = Math.abs(random.nextInt()) % hall.size();
            synchronized (User.class)
            {
                try
                {
                    hall.select(index);
                    hall.reserve(index);
                    System.out.println("RESERVED " + index);
                }
                catch (Exception ignored)
                {

                }
            }
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException ignored)
            {

            }
        }
    }
}
