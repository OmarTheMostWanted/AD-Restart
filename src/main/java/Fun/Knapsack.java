package Fun;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Knapsack {

    public Knapsack(int maxWight) {
        this.itemList = new ArrayList<>();
        MaxWight = maxWight;
    }

    static class KnapsackResult{
        public int TotalWeight;
        public int TotalValue;

        public KnapsackResult(int tw, int tv) {
            this.TotalWeight = tw;
            this.TotalValue = tv;
        }

        @Override
        public String toString() {
            return "KnapsackResult{" +
                    "TotalWeight=" + TotalWeight +
                    ", TotalValue=" + TotalValue +
                    '}';
        }
    }

    static class KnapsackItem {
        int weight;
        int value;
        double ratio;

        public KnapsackItem(int w, int v) {
            weight = w;
            value = v;
            ratio = (double) v / (double) w;
        }

        @Override
        public String toString() {
            return "KnapsackItem{" + "weight=" + weight + ", value=" + value + ", ratio=" + ratio + '}';
        }
    }

    public int MaxWight;

    List<KnapsackItem> itemList = new ArrayList<>();

    public KnapsackResult IncreasingWeight(){
        var totalWeight = 0;
        var totalValue = 0;
        Comparator<KnapsackItem> comparator = new Comparator<KnapsackItem>() {
            @Override
            public int compare(KnapsackItem o1, KnapsackItem o2) {
                return o1.weight - o2.weight;
            }
        };

        return getKnapsackResult(totalWeight, totalValue, (Comparator<KnapsackItem>) comparator);
    }

    public KnapsackResult DecreasingValue(){
        var totalWeight = 0;
        var totalValue = 0;
        Comparator<KnapsackItem> comparator = new Comparator<KnapsackItem>() {
            @Override
            public int compare(KnapsackItem o1, KnapsackItem o2) {
                return o2.value - o1.value;
            }
        };

        return getKnapsackResult(totalWeight, totalValue, (Comparator<KnapsackItem>) comparator);
    }

    public KnapsackResult DecreasingRatio(){
        var totalWeight = 0;
        var totalValue = 0;
        Comparator<KnapsackItem> comparator = new Comparator<KnapsackItem>() {
            @Override
            public int compare(KnapsackItem o1, KnapsackItem o2) {
                return (int) Math.ceil( o2.ratio - o1.ratio);
            }
        };

        return getKnapsackResult(totalWeight, totalValue, (Comparator<KnapsackItem>) comparator);
    }

    private KnapsackResult getKnapsackResult(int totalWeight, int totalValue, Comparator<KnapsackItem> comparator) {
        PriorityQueue<KnapsackItem> priorityQueue = new PriorityQueue<>(comparator);

        priorityQueue.addAll(itemList);

        while (totalWeight < MaxWight && !priorityQueue.isEmpty()) {
            var i = priorityQueue.poll();

            if(totalWeight + i.weight <= MaxWight){
                totalWeight += i.weight;
                totalValue += i.value;
            }
        }
        return new KnapsackResult(totalWeight , totalValue);
    }

    public Knapsack AddItem(int w , int v){
        this.itemList.add(new KnapsackItem(w , v));
        return this;
    }

}