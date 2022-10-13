package Fun;

import java.util.*;
import java.util.function.BiConsumer;


class A {
    int id;
    ArrayList<B> bRanking;

    HashSet<B> BsConsidered = new HashSet<>();
    boolean HasConsideredEveryB = false;

    public A(int id) {
        this.id = id;
    }

    public void SetRanking(ArrayList<B> bs) {
        bRanking = new ArrayList<>(bs);
        Collections.shuffle(bRanking);
    }

    public B HighestRankenBNotConsidered() {

        for (B b : bRanking) {
            if (BsConsidered.contains(b)) continue;
            BsConsidered.add(b);
            HasConsideredEveryB = BsConsidered.size() == bRanking.size();
            return b;
        }

        return null;
    }

    public int GetBRanking(B b) {
        return this.bRanking.indexOf(b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof A a)) return false;
        return id == a.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder().append("[ ");
        for (B b : bRanking) {
            sb.append(b.id).append(' ');
        }
        sb.append(']');
        return "A{" + id + ", " + sb.toString() + '}';
    }

}

class B {
    int id;
    ArrayList<A> aRanking;

    public B(int id) {
        this.id = id;
    }

    public void SetRanking(ArrayList<A> as) {
        aRanking = new ArrayList<>(as);
        Collections.shuffle(aRanking);
    }

    public int GetARanking(A a) {
        return this.aRanking.indexOf(a);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof B b)) return false;
        return id == b.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder().append("[ ");
        for (A a : aRanking) {
            sb.append(a.id).append(' ');
        }
        sb.append(']');
        return "B{" + id + ", " + sb.toString() + '}';
    }
}


class Matching {
    A a;
    B b;

    public Matching(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public boolean PrefersThisB(B c) {
        return a.GetBRanking(b) > a.GetBRanking(c);
    }

    public boolean PrefersThisA(A c) {
        return b.GetARanking(a) > b.GetARanking(c);
    }

    public boolean PerfectMatch() {
        return a.GetBRanking(b) == 0 && b.GetARanking(a) == 0;
    }

    @Override
    public String toString() {
        return "{" + a.id + ", " + b.id + '}';
    }
}

public class StableMatchingProblem {

    private int size;
    public ArrayList<A> as = new ArrayList<>(size);
    public ArrayList<B> bs = new ArrayList<>(size);

    public StableMatchingProblem(int size) {

        this.size = size;

        for (int i = 0; i < size; i++) {
            as.add(new A(i));
            bs.add(new B(i));
        }
        for (A a : as) {
            a.SetRanking(bs);
        }
        for (B b : bs) {
            b.SetRanking(as);
        }
    }

    boolean PossibleToCreateStableMatching() {
        return !false;
    }

    public Set<Matching> MatchAsAndBsWithBiasForA() {
        var res = new HashSet<Matching>();
        Set<A> chosenAs = new HashSet<>();
        Set<B> chosenBs = new HashSet<>();
        MatchBsToAs((HashSet<Matching>) res, (Set<A>) chosenAs, (Set<B>) chosenBs);
        MatchAsToBs((HashSet<Matching>) res, (Set<A>) chosenAs, (Set<B>) chosenBs);
        return res;
    }

    public Set<Matching> MatchAsAndBsWithBiasForB() {
        var res = new HashSet<Matching>();
        Set<A> chosenAs = new HashSet<>();
        Set<B> chosenBs = new HashSet<>();
        MatchAsToBs((HashSet<Matching>) res, (Set<A>) chosenAs, (Set<B>) chosenBs);
        MatchBsToAs((HashSet<Matching>) res, (Set<A>) chosenAs, (Set<B>) chosenBs);
        return res;
    }

    public Set<Matching> GaleShapleyAlgorithm() {

        var engagements = new HashMap<B, A>();
        var freeAndNotDesperateAs = new ArrayList<A>(as);

        while (!freeAndNotDesperateAs.isEmpty()) {

            A a = freeAndNotDesperateAs.get(0);

//                if (!a.HasConsideredEveryB) {
                    B b = a.HighestRankenBNotConsidered();

                    if (!engagements.containsKey(b)) {
                        engagements.put(b, a);
                        freeAndNotDesperateAs.remove(a);
                        break;
                    } else {
                        A rival = engagements.get(b);
                        if (b.GetARanking(a) > b.GetARanking(rival)) {
                            continue;
                        } else {
                            freeAndNotDesperateAs.add(rival);
                            freeAndNotDesperateAs.remove(a);
                            engagements.replace(b, a);
                            break;
                        }

//                    }
                }

        }

        Set<Matching> weddings = new HashSet<>();

        engagements.forEach(new BiConsumer<B, A>() {
            @Override
            public void accept(B b, A a) {
                weddings.add(new Matching(a, b));
            }
        });

        return weddings;

    }

    public Set<Matching> BrutForce() {
        var res = new HashSet<Matching>();
        Set<A> chosenAs = new HashSet<>();
        Set<B> chosenBs = new HashSet<>();

        int baseDesperation = 0;

        while (res.size() < size) {


            for (A a : as) {
                if (chosenAs.contains(a)) continue;
                int desperation = baseDesperation;
                for (B b : bs) {
                    if (chosenBs.contains(b)) continue;
                    if (FindBsBestMatch(b, chosenAs) == a && a.GetBRanking(b) < desperation) {
                        chosenAs.add(a);
                        chosenBs.add(b);
                        res.add(new Matching(a, b));
                        break;
                    }
                    desperation++;
                }
            }

            for (B b : bs) {
                if (chosenBs.contains(b)) continue;
                int desperation = baseDesperation;
                for (A a : as) {
                    if (chosenAs.contains(a)) continue;
                    if (FindAsBestMatch(a, chosenBs) == b && b.GetARanking(a) < desperation) {
                        chosenAs.add(a);
                        chosenBs.add(b);
                        res.add(new Matching(a, b));
                        break;
                    }
                    desperation++;
                }
            }
            baseDesperation++;
        }

        return res;

    }

    public A FindBsBestMatch(B b, Set<A> chosenAs) {
        int desperation = Integer.MAX_VALUE;
        A perfectMatch = null;
        for (A a : as) {
            if (chosenAs.contains(a)) continue;
            if (a.GetBRanking(b) < desperation) {
                perfectMatch = a;
            }

        }
        return perfectMatch;
    }

    public B FindAsBestMatch(A a, Set<B> chosenBs) {
        int desperation = Integer.MAX_VALUE;
        B perfectMatch = null;
        for (B b : bs) {
            if (chosenBs.contains(b)) continue;
            if (b.GetARanking(a) < desperation) {
                perfectMatch = b;
            }

        }
        return perfectMatch;
    }

    private void MatchBsToAs(HashSet<Matching> res, Set<A> chosenAs, Set<B> chosenBs) {
        for (int i = 0; i < as.size(); i++) {
            var a = as.get(i);
            if (chosenAs.contains(a)) continue;
            for (B b : a.bRanking) {
                if (chosenBs.contains(b)) continue;
                else {
                    chosenAs.add(a);
                    chosenBs.add(b);
                    res.add(new Matching(a, b));
                    break;
                }
            }
        }
    }

    private void MatchAsToBs(HashSet<Matching> res, Set<A> chosenAs, Set<B> chosenBs) {
        for (int i = 0; i < bs.size(); i++) {
            var b = bs.get(i);
            if (chosenBs.contains(b)) continue;
            for (A a : b.aRanking) {
                if (chosenAs.contains(a)) continue;
                else {
                    chosenAs.add(a);
                    chosenBs.add(b);
                    res.add(new Matching(a, b));
                    break;
                }
            }
        }
    }


    /**
     * When for all pairs, no better pair can be made by breaking two pairs
     *
     * @param matching to be validated.
     * @return true if all matches are stable.
     */
    public static boolean ValidateStableMatch(Set<Matching> matching) {

        var ms = new HashSet<>(matching);

        ms.removeIf(Matching::PerfectMatch);

        if (ms.isEmpty()) return true;

        for (Matching m : ms) {
            for (Matching n : ms) {
                if (m.PrefersThisB(n.b) && n.PrefersThisA(m.a)) {
//                    System.out.println("Matches " + m + " and " + n + " are unstable");
                    return false;
                }
            }
        }
        return true;
    }


    private static double TestBrutForce(int size, int times) {

        StableMatchingProblem stableMatchingProblem;
        Set<Matching> res;

        int Correct = 0;

        for (int i = 0; i < times; i++) {
            stableMatchingProblem = new StableMatchingProblem(size);
            res = stableMatchingProblem.BrutForce();
            if (ValidateStableMatch(res)) Correct++;
        }

        return (double) Correct / (double) times;

    }

    private static double TestABias(int size, int times) {
        StableMatchingProblem stableMatchingProblem;
        Set<Matching> res;

        int Correct = 0;

        for (int i = 0; i < times; i++) {
            stableMatchingProblem = new StableMatchingProblem(size);
            res = stableMatchingProblem.MatchAsAndBsWithBiasForA();
            if (ValidateStableMatch(res)) Correct++;
        }
        return (double) Correct / (double) times;
    }

    private static double TestBook(int size, int times) {
        StableMatchingProblem stableMatchingProblem;
        Set<Matching> res;

        int Correct = 0;

        for (int i = 0; i < times; i++) {
            stableMatchingProblem = new StableMatchingProblem(size);
            res = stableMatchingProblem.GaleShapleyAlgorithm();
            if (ValidateStableMatch(res)) Correct++;
        }
        return (double) Correct / (double) times;
    }


    public static void main(String[] args) {

        int size = 100;
        int times = 1000;

        double BrutForceScore = TestBrutForce(size, times);
        double ABiasScore = TestABias(size, times);
        double bookScore = TestBook(size, times);


        System.out.println("Ran BrutForce " + times + " times with size " + size + " with score " + BrutForceScore);
        System.out.println("Ran Bias for A " + times + " times with size " + size + " with score " + ABiasScore);
        System.out.println("Ran Book Algorithm for A " + times + " times with size " + size + " with score " + bookScore);


//        StableMatchingProblem p;
//
//        while (true) {
//            p = new StableMatchingProblem(size);
//            var res = p.MatchAsAndBsWithBiasForA();
//            var otherRes = p.MatchAsAndBsWithBiasForB();
//
//            System.out.println(p.as);
//            System.out.println(p.bs);
//            System.out.println("Preference to A " + res);
//            System.out.println("Preference to B " + otherRes);
//
//            if (!ValidateStableMatch(res)) {
//                if (ValidateStableMatch(otherRes)) System.out.println("B was stable");
//                break;
//            }
//            if (!ValidateStableMatch(otherRes)) System.out.println("B was unstable");
//            System.out.println("=====================================");
//        }

//        while (true) {
//            p = new StableMatchingProblem(size);
//            var res = p.BrutForce();
//
//            System.out.println(p.as);
//            System.out.println(p.bs);
//            System.out.println("matches " + res);
//
//            if (!ValidateStableMatch(res)) {
//                break;
//            }
//            System.out.println("=====================================");
//        }
    }
}






















