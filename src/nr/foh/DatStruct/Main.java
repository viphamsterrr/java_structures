package nr.foh.DatStruct;

public class Main {
    public static void main(String[] args) {
        Dqueue<Integer> s = new Dqueue<>(54);
        for (int i = 6; i < 163; i += 3) {s.putFirst(i); if (i == 9){s.putFirst(54);}}
        for (int i : s) {System.out.println(i);}
        s.putLast(54);
        System.out.println(s.count(54));
        while (s.getSize() > 0) {s.takeLast();}
        System.out.println();
        System.out.println();
        for (int i : s) {System.out.println(i);}
    }
}
