package com.epam.engx.jam.task2.impl;

import com.epam.engx.jam.task2.MergeSort;
import org.springframework.stereotype.Component;

import java.util.concurrent.ForkJoinPool;

@Component
public final class ParallelMergeSortImpl implements MergeSort {

    @Override
    public void accept(int[] ints) {
        var forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(new MergeSortAction(ints));
    }
}
