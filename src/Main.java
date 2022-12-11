public class Main {
    public static void main(String[] args) {

        Main myObj = new Main();

        LinkedList<Integer> list = new LinkedList<Integer>();

        list.addFirst(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addFirst(1);
        list.remove(4);

        System.out.println("************************************************");
        System.out.println(list.getFirst());


        System.out.println(list.size());

        list.removeLast();
        list.removeFirst();

        System.out.println(list.size());

        System.out.println("************************************************");
        for (int num : list) {
            System.out.println(num);
        }

//        System.out.println("************************************************");
//        System.out.println("list 5");
//        System.out.println(list.contains(-6));
//
//        System.out.println("************************************************");
//        list.add(1,34);
//        for(int num : list) {
//            System.out.println(num);
//        }
//        System.out.println(list.size());
    }
}
