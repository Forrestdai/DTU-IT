import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aimo & Lukas
 */
public class Assignment1_SortingAlgorithms {

    public static void main(String[] args) {

        Assignment1_SortingAlgorithms ass1 = new Assignment1_SortingAlgorithms();
        
        System.out.println("Selection sorting\n");
        ass1.createArray();

        System.out.println(Arrays.toString(ass1.arrayAsc));
        System.out.println(Arrays.toString(ass1.arrayDes));
        System.out.println(Arrays.toString(ass1.arrayRan));

        ass1.selectionSort(ass1.arrayAsc);
        ass1.selectionSort(ass1.arrayDes);
        ass1.selectionSort(ass1.arrayRan);

        System.out.println(Arrays.toString(ass1.arrayAsc));
        System.out.println(Arrays.toString(ass1.arrayDes));
        System.out.println(Arrays.toString(ass1.arrayRan));
        
        System.out.println("Insertion sorting\n");
        
        ass1.createArray();
        
        System.out.println(Arrays.toString(ass1.arrayAsc));
        System.out.println(Arrays.toString(ass1.arrayDes));
        System.out.println(Arrays.toString(ass1.arrayRan));
        
        ass1.insertionSort(ass1.arrayAsc);
        ass1.insertionSort(ass1.arrayDes);
        ass1.insertionSort(ass1.arrayRan);
       
        System.out.println(Arrays.toString(ass1.arrayAsc));
        System.out.println(Arrays.toString(ass1.arrayDes));
        System.out.println(Arrays.toString(ass1.arrayRan));
        
        System.out.println("System quick sorting\n");
        ass1.createArray();
        
        System.out.println(Arrays.toString(ass1.arrayAsc));
        System.out.println(Arrays.toString(ass1.arrayDes));
        System.out.println(Arrays.toString(ass1.arrayRan));
        
        ass1.systemSort(ass1.arrayAsc);
        ass1.systemSort(ass1.arrayDes);
        ass1.systemSort(ass1.arrayRan);
        
        System.out.println(Arrays.toString(ass1.arrayAsc));
        System.out.println(Arrays.toString(ass1.arrayDes));
        System.out.println(Arrays.toString(ass1.arrayRan));
        
        

    }

    public int arrayLength = 10000;
    long start;
    long stop;
    long waste;
    int[] arrayAsc = new int[arrayLength];
    int[] arrayDes = new int[arrayLength];
    int[] arrayRan = new int[arrayLength];

    public void createArray() {
        for (int i = 0; i < this.arrayLength; i++) {
            arrayRan[i] = (int) (Math.random() * this.arrayLength);
            arrayAsc[i] = i;
            arrayDes[i] = this.arrayLength - i - 1;
        }
    }

    public void selectionSort(int[] array) {
        start = System.currentTimeMillis();
        for (int i = 0; i < array.length - 1; i++) {
            int index = i;
            for (int j = index + 1; j < array.length; j++) {
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            int lowestNumber = array[index];
            array[index] = array[i];
            array[i] = lowestNumber;

        }
        stop = System.currentTimeMillis();
        waste = stop - start;
        System.out.println("Time wasted: " + waste / 1000f);
    }

    public void insertionSort(int[] array) {
        start = System.currentTimeMillis();
        for (int i = 1; i < array.length; i++) {
            int sort = array[i];
            int j;
            for (j = i - 1; j >= 0 && sort < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = sort;

        }
        stop = System.currentTimeMillis();
        waste = stop - start;
        System.out.println("Time wasted: " + waste / 1000f);
    }
    
    public void systemSort(int[] array){
        start = System.currentTimeMillis();
        Arrays.sort(array);
        stop = System.currentTimeMillis();
        waste = stop - start;
        System.out.println("Time wasted: " + waste / 1000f);
        
    }

}
