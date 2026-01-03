package nr.foh;

import nr.foh.DatStruct.Queue;
import nr.foh.DatStruct.Stack;

import java.security.DrbgParameters;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import static java.security.DrbgParameters.Capability.RESEED_ONLY;

public class Sort {
    /**
     * Sorts array via insertion sort method. Use it only for small size arrays. Any array type which
     * implements Comparable interface are allowed. This method of sort is stable.
     *
     * @param arr have to implement Comparable[] - array of data.
     * @param reversed for sorting in descending order. Default if false (ascending).
     */
    public static void insertion(Comparable[] arr, boolean reversed) {
        if (reversed) {insertionDesc(arr);}
        else{insertionAsc(arr);}
    }

    public static void insertion(Comparable[] arr) {
        insertion(arr, false);
    }

    private static void insertionAsc(Comparable[] arr) {
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
    private static void insertionDesc(Comparable[] arr) {
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

    /**
     * Sorts array via shell sort method.
     *
     * @param arr have to implement Comparable[] - array of data.
     * @param reversed for sorting in descending order. Default if false (ascending).
     */
    public static void shell(Comparable[] arr, boolean reversed) {
        if (reversed) {shellDesc(arr);}
        else{shellAsc(arr);}
    }

    public static void shell(Comparable[] arr) {
        shell(arr, false);
    }

    private static void shellAsc(Comparable[] arr) {
        int[] idxs = shellSortIdx(arr.length);
        int i;
        int j;
        for (int idx : idxs) {
            i = idx;
            while (i < arr.length) {
                j = i;
                while (j >= idx) {
                    if (arr[j].compareTo(arr[j - idx]) < 0) exchange(arr, j, j - idx);
                    j -= idx;
                }
                i++;
            }
        }
    }

    private static void shellDesc(Comparable[] arr) {
        int[] idxs = shellSortIdx(arr.length);
        int i;
        int j;
        for (int idx : idxs) {
            i = idx;
            while (i < arr.length) {
                j = i;
                while (j >= idx) {
                    if (arr[j].compareTo(arr[j - idx]) > 0) exchange(arr, j, j - idx);
                    j -= idx;
                }
                i++;
            }
        }
    }

    /**
     * Sorts array via merge sort method. Stable an effective sorting method.
     *
     * @param arr have to implement Comparable[] - array of data.
     * @param reversed for sorting in descending order. Default if false (ascending).
     */
    public static void merge(Comparable[] arr, boolean reversed) {
        if (reversed) {mergeDesc(arr);}
        else{mergeAsc(arr);}
    }

    public static void merge(Comparable[] arr) {
        merge(arr, false);
    }

    private static void mergeAsc(Comparable[] arr) {
        Comparable[] hlp = new Comparable[arr.length];
        mergeAsc(arr, hlp, 0, arr.length);
    }

    private static void mergeAsc(Comparable[] arr, Comparable[] hlp, int start, int finish) {
        int middle = start + (finish - start) / 2;
        if (!isSortedAsc(arr, start, middle)){mergeAsc(arr, hlp, start, middle);}
        if (!isSortedAsc(arr, middle, finish)){mergeAsc(arr, hlp, middle, finish);}
        glueArrAsc(arr, hlp, start, middle, finish);
    }

    private static void mergeDesc(Comparable[] arr) {
        Comparable[] hlp = new Comparable[arr.length];
        mergeDesc(arr, hlp, 0, arr.length);
    }

    private static void mergeDesc(Comparable[] arr, Comparable[] hlp, int start, int finish) {
        int middle = start + (finish - start) / 2;
        if (!isSortedDesc(arr, start, middle)){mergeDesc(arr, hlp, start, middle);}
        if (!isSortedDesc(arr, middle, finish)){mergeDesc(arr, hlp, middle, finish);}
        glueArrDesc(arr, hlp, start, middle, finish);
    }

    /**
     * Shuffles array of any type. No matter, ordered it or not, the method randomly shuffles current state.
     * Data types of array have not necessary be comparable.
     *
     * @param arr array of any type.
     * @throws NoSuchAlgorithmException if non-existent randomizing algorithm accidentally chosen
     */

    public static void unsort(Object[] arr) throws NoSuchAlgorithmException {
        try {
            SecureRandom rnd = SecureRandom.getInstance("DRBG",
                DrbgParameters.instantiation(256, RESEED_ONLY, null));
            for (int i = 0; i < arr.length; i++) {
                int idx = rnd.nextInt(i + 1);
                exchange(arr, i, idx);
                }
            }
        catch (Exception e) {
            throw  new NoSuchAlgorithmException("Random generator failed.");
        }
    }

    private static void glueArrAsc(Comparable[] arr, Comparable[] hlp, int start, int middle, int finish) {
        System.arraycopy(arr, start, hlp, start, finish - start);
        int i = start;
        int fa = start;
        int sa = middle;
        while (fa < middle || sa < finish) {
            if (fa == middle) {
                arr[i++] = hlp[sa++];
            } else {
                if (sa == finish) {
                    arr[i++] = hlp[fa++];
                } else {
                    if (hlp[sa].compareTo(hlp[fa]) > 0) {
                        arr[i++] = hlp[fa++];
                    } else {
                        arr[i++] = hlp[sa++];
                    }
                }
            }
        }
    }

    private static void glueArrDesc(Comparable[] arr, Comparable[] hlp, int start, int middle, int finish) {
        System.arraycopy(arr, start, hlp, start, finish - start);
        int i = start;
        int fa = start;
        int sa = middle;
        while (fa < middle || sa < finish) {
            if (fa == middle) {
                arr[i++] = hlp[sa++];
            } else {
                if (sa == finish) {
                    arr[i++] = hlp[fa++];
                } else {
                    if (hlp[sa].compareTo(hlp[fa]) < 0) {
                        arr[i++] = hlp[fa++];
                    } else {
                        arr[i++] = hlp[sa++];
                    }
                }
            }
        }
    }

    private static boolean isSortedAsc(Comparable[] arr, int start, int finish) {
        if (finish - start < 2) return true;
        for (int i = start; i < finish - 1; i++) {
            if (arr[i + 1].compareTo(arr[i]) < 0)
                return false;
        }
        return true;
    }

    private static boolean isSortedDesc(Comparable[] arr, int start, int finish) {
        if (finish - start < 2) return true;
        for (int i = start; i < finish - 1; i++) {
            if (arr[i + 1].compareTo(arr[i]) > 0)
                return false;
        }
        return true;
    }

    public static void exchange(Object[] arr, int first, int second) {
        Object intmd = arr[first];
        arr[first] = arr[second];
        arr[second] = intmd;
    }

    private static int powInt(int base, int pointer) {
        if (pointer == 0) {return 1;}
        else if (pointer == 1) {return base;}
        if (pointer % 2 == 0) {return powInt(base, pointer / 2) * powInt(base, pointer / 2);}
        else {return powInt(base, pointer - 1) * base;}
    }

    public static int[] shellSortIdx (int number) {
        int i = 0;
        int res = 0;
        Stack<Integer> first = new Stack<>();
        while (true) {
            res = (powInt(4, i) * 9) - (powInt(2, i) * 9) + 1;
            if (res <= number / 2) {first.put(res);}
            else break;
            i++;
        }
        i = 0;
        Stack<Integer> second = new Stack<>();
        while (true) {
            res = powInt(4, i) - (powInt(2, i) * 3) + 1;
            if (res <= number / 2) {if (res > 0){ second.put(res); }}
            else break;
            i++;
        }
        Queue<Integer> third = new Queue<>();
        while (!first.isEmpty() || !second.isEmpty()) {
            if ((!first.isEmpty() && !second.isEmpty()) && first.pick() == second.pick()) {first.take();}
            else if ((!first.isEmpty() && second.isEmpty()) || first.pick() > second.pick()) {third.put(first.take());}
                else {
                third.put(second.take());
            }
        }
        int[] rslt = new int[(int) third.getSize()];
        i = 0;
        while (!third.isEmpty()) {rslt[i++] = third.take();}
        return rslt;
    }
}
