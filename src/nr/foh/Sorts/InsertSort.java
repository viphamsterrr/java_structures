package nr.foh.Sorts;

/**
 * Sorts array via insertion sort method. Use it only for small size arrays. Any array type which
 * implements Comparable interface are allowed. This method of sort is stable.
 */
public class InsertSort {
    public static void inssort(Comparable[] arr) {
        inssort(arr, false);
    }

    /**
     * Sorts array via insertion sort method. Use it only for small size arrays. Any array type which
     * implements Comparable interface are allowed. This method of sort is stable.
     *
     * @param arr have to implement Comparable[] - array of data.
     * @param reversed for sorting in descending order. Default if false (ascending).
     * @return nothing, void function, sorts existing array.
     */
    public static void inssort(Comparable[] arr, boolean reversed) {
        if (reversed) {inssortdesc(arr);}
        else{inssortasc(arr);}
    }

    private static void inssortasc(Comparable[] arr) {
        int i = 0;
        int j = 0;
        while (i < arr.length - 1) {
            j++;
            if (arr[i].compareTo(arr[j]) > 0) {while (j > 0 && arr[j - 1].compareTo(arr[j]) > 0) {
                    exchange(arr, j - 1, j);
                    j--;
                }
            }
            i++;
            j = i;
        }
    }
    private static void inssortdesc(Comparable[] arr) {
        int i = 0;
        int j = 0;
        while (i < arr.length - 1) {
            j++;
            if (arr[i].compareTo(arr[j]) < 0) {while (j > 0 && arr[j - 1].compareTo(arr[j]) < 0) {
                exchange(arr, j - 1, j);
                j--;
            }
            }
            i++;
            j = i;
        }
    }


    private static void exchange(Comparable[] arr, int first, int second) {
        Comparable tmp = arr[first];
        arr[first] = arr[second];
        arr[second] = tmp;
    }
}
