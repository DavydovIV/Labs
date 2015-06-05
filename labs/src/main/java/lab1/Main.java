package lab1;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        RedBlackTree<Integer> rbtree = new RedBlackTree<Integer>();
        for(int i = 0; i < 100; i++) {
            rbtree.add(i);
        }
        for (int i = 0; i < 100; i++)
        {
            if (!rbtree.contains(i))
            {
                throw new Exception("Error");
            }
        }
        for (int i = 0; i < 50; i++) {
            if (!rbtree.remove(i)) {
                throw new Exception("Error");
            }
        }
        for (int i = 0; i < 50; i++) {
            if (rbtree.contains(i)) {
                throw new Exception("Error");
            }
        }
        for (int i = 50; i < 100; i++) {
            if (!rbtree.contains(i)) {
                throw new Exception("Error");
            }
        }
        for (Integer integer : rbtree) {
            System.out.println(integer.toString());
        }
        System.out.println("All ok.");
    }
}
