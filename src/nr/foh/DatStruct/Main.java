package nr.foh.DatStruct;
import nr.foh.Sort;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Integer[] a = new Integer[53];
        Random rnd = new Random();
        for (int i = 0; i < 53; i++) {
            a[i] = rnd.nextInt(666);
        }
        for (int i : a) {System.out.print(i); System.out.print(" ");}
        System.out.println();
        Sort.merge(a, true);
        for (int i : a) {System.out.print(i); System.out.print(" ");}
    }
}
