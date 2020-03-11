package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ISortImpl<T extends Comparable<T>> implements ISort<T> {

    private void quickSort(ArrayList arr, int left, int right, int n){
        if (left >= right)
            return;
        Random rand = new Random();
        int randomIndex = rand.nextInt((right - left) + 1) + left, newLeft = left + 1, small = left + 1, newRight;
        Collections.swap(arr, left, randomIndex);
        for (int i = left + 1; i < n + left; ++i) {
            if ((Integer)arr.get(i) < (Integer) arr.get(left)){
                Collections.swap(arr, i, small);
                small++;
            } else if ((Integer) arr.get(i) == (Integer) arr.get(left)){
                Collections.swap(arr, i, small);
                Collections.swap(arr, small++, newLeft++);
            }
        }
        newRight = small;
        while (newLeft > left){
            Collections.swap(arr, --newLeft, --small);
        }
        quickSort(arr, left, small - 1, small - left);
        quickSort(arr, newRight, right, right - newRight + 1);
    }

    private void bubbleSort(ArrayList arr, int n){
        for (int i = 0; i < n - 1; ++i){
            for (int j = 0; j < n - 1 - i; ++j){
                if ((Integer)arr.get(j) > (Integer) arr.get(j + 1))
                    Collections.swap(arr, j, j + 1);
            }
        }
    }

    @Override
    public IHeap<T> heapSort(ArrayList<T> unordered) {
        return null;
    }

    @Override
    public void sortSlow(ArrayList<T> unordered) {
        // Bubble Sort
        bubbleSort(unordered, unordered.size());
    }

    @Override
    public void sortFast(ArrayList<T> unordered) {
        // Quick Sort
        quickSort(unordered, 0, unordered.size() - 1, unordered.size());
    }
}
