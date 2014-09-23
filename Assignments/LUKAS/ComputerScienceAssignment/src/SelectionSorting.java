/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aimo
 */
public class SelectionSorting {
    
    public void selectionSort(int[] array) {
        long start = System.currentTimeMillis();
        for (int i = array.length-1; i > 0; i--) {
            int index = 0;
            for (int j = 1; j <= i; j++) {
                if (array[j] > array[index]) {
                    index = j;
                }
            }
            int lowestNumber = array[index];
            array[index] = array[i];
            array[i] = lowestNumber;

        }
        long stop = System.currentTimeMillis();
        long waste = stop - start;
        System.out.println("Time wasted: " + waste / 1000f);
    }
}
