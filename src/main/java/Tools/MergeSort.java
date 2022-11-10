package Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MergeSort<T extends Comparable<T>> {
    public List<T> Sort(List<T> list) {
        if(list.size() <= 1 ) return list;
        var middle = list.size() / 2;
        var l =  new ArrayList<T>(middle);
        for (int i = 0 ; i < middle ; i ++){
            l.add(list.get(i));
        }
        var r =  new ArrayList<T>(middle);
        for (int i = middle ; i < list.size() ; i ++){
            r.add(list.get(i));
        }
        return Merge(Sort(l) , Sort(r));
    }

    private ArrayList<T> Merge(List<T> l, List<T> r){
        var res = new ArrayList<T>(l.size() + r.size());
        var lp = 0;
        var rp = 0;
        while (lp < l.size() & rp < r.size()){
            if(l.get(lp).compareTo(r.get(rp)) <= 0){
                res.add(l.get(lp));
                lp++;
            } else {
                res.add(r.get(rp));
                rp++;
            }
        }
        while (lp < l.size()){
            res.add(l.get(lp));
            lp++;
        }
        while (rp < r.size()){
            res.add(r.get(rp));
            rp++;
        }
        return res;
    }
}
