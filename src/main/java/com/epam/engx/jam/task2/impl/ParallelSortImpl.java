package com.epam.engx.jam.task2.impl;

import com.epam.engx.jam.task2.MergeSort;

import java.util.concurrent.ForkJoinPool;

public class ParallelSortImpl implements MergeSort {

    @Override
    public void accept(int[] ints) {
        var forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(new MergeSortAction(ints));
    }
}
