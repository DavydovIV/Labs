package lab2;


import java.util.ArrayList;


public class Main
{
    public static void main(String[] args)
    {
        Hall hall = new Hall(10000);
        ArrayList<User> users = new ArrayList<User>();
        for(int i = 0; i < 100; i++) {
            users.add(new User(hall));
            users.get(users.size() - 1).start();
        }
        for(User u : users) {
            try
            {
                u.join();
            }
            catch (InterruptedException ignored)
            {

            }
        }
        System.out.println("All ok");
    }
}
