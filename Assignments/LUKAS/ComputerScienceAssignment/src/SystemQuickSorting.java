import java.util.Arrays;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aimo
 */
public class SystemQuickSorting {
    public void systemSort(int[] array) {
        long start = System.currentTimeMillis();
        Arrays.sort(array);
        long stop = System.currentTimeMillis();
        long waste = stop - start;
        System.out.println("Time wasted: " + waste / 1000f);
    }
}
