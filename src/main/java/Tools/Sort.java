package Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sort<T extends Comparable<T>> {

    public T[] QuickSort(T[] target){

        ArrayList<T> t = new ArrayList<>(target.length);

        for (int i = 0; i < target.length; i++) {
            t.set(i, target[i]);
        }

        return (T[]) QuickSort(t , 0 , t.size()-1).toArray();
    }
    public List<T> QuickSort(List<T> target){
        return QuickSort(new ArrayList<>(target) , 0 , target.size()-1);
    }

    private ArrayList<T> QuickSort(ArrayList<T> target, int left , int right){

        if(left >= right) return target;

        var pivot = target.get(right);

        var r = right - 1;
        var l = left;

        while (r > l){

            while (pivot.compareTo(target.get(l)) > 0){
                l++;
            }

            while (pivot.compareTo(target.get(r)) <= 0){
                r--;
            }

            Swap(target , l , r);


            if(l < r){
                break;
            }

        }

        if(pivot.compareTo(target.get(r)) < 0){
            Swap(target , right , r);
        }


        QuickSort(target , 0 , r-1);
        QuickSort(target , r+1, target.size()-1);

        return target;

    }

    private void Swap(ArrayList<T> target, int a , int b){
        var temp = target.get(a);
        target.set(a , target.get(b));
        target.set(b , temp);
    }


}
