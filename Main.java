import org.w3c.dom.Node;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(8);
        list.add(4);
        list.add(9);

       /* list.remove(2);

        list.print();
        list.remove(4);
        list.print();
        list.remove(1);
        list.print();*/

        /*list.print();
        System.out.println(list.getValue(3));
        list.swap(1,2);
        list.print();*/

        /*list.print();
        list.removeAt(0);
        list.print();*/

        //list.removeAll(5);
        /*list.print();
        list.quickSort();
        list.print();*/

        /*list.addSorted(5);
        list.addSorted(7);
        list.addSorted(5);
        list.addSorted(2);*/
        /*for(int i = 0; i < 20; i++)
            list.addSorted(new Random().nextInt(20));*/

        list.revert(3,5);
        list.print();
    }


}
