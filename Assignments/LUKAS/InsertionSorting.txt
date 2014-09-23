/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aimo
 */
public class InsertionSorting {
    public void insertionSort(int[] array) {
        long start = System.currentTimeMillis();
        for (int i = 1; i < array.length; i++) {
            int sort = array[i];
            int j;
            for (j = i - 1; j >= 0 && sort < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = sort;

        }
        long stop = System.currentTimeMillis();
        long waste = stop - start;
        System.out.println("Time wasted: " + waste / 1000f);
    }
}
