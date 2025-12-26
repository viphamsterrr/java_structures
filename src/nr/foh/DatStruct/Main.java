package nr.foh.DatStruct;
import nr.foh.Sorts.InsertSort;

public class Main {
    public static void main(String[] args) {
        Integer[] a = new Integer[8];
        a[0] = 1;
        a[1] = 4;
        a[2] = 5;
        a[3] = 7;
        a[4] = 13;
        a[5] = 27;
        a[6] = 314;
        a[7] = 412;
        InsertSort.inssort(a, true);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
