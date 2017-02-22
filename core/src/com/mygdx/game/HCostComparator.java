package com.mygdx.game;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Created by MichaelSjogren on 2017-02-22.
 */
public class HCostComparator implements Comparator<Node> {


    @Override
    public int compare(Node n1, Node n2) {
        if(n1.getfCost() < n2.getfCost()) return 1;
        if(n1.getfCost() > n2.getfCost()) return -1;
        return 0;
    }

    @Override
    public Comparator<Node> reversed() {
        return null;
    }

    @Override
    public Comparator<Node> thenComparing(Comparator<? super Node> other) {
        return null;
    }

    @Override
    public <U> Comparator<Node> thenComparing(Function<? super Node, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Node> thenComparing(Function<? super Node, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Node> thenComparingInt(ToIntFunction<? super Node> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Node> thenComparingLong(ToLongFunction<? super Node> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Node> thenComparingDouble(ToDoubleFunction<? super Node> keyExtractor) {
        return null;
    }
}
