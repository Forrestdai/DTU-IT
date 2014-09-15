package Sorting;

public class BuiltInQuickSort implements Sortable
{
    @Override
    public int[] sort(int[] array)
    {
        java.util.Arrays.sort(array);
        return array;
    }
    
}
