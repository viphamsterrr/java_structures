package nr.foh.DatStruct;
import nr.foh.Sort;

public class Main {
    public static void main(String[] args) throws Exception {
        Integer[] a = new Integer[2];
        a[0] = 0;
        a[1] = 1;
        Sort.unsort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
        Sort.shell(a, true);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
