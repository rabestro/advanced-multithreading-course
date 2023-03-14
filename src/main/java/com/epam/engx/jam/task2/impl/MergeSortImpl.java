package com.epam.engx.jam.task2.impl;

import com.epam.engx.jam.task2.MergeSort;
import org.springframework.stereotype.Component;

@Component
public final class MergeSortImpl implements MergeSort {

    @Override
    public void accept(int[] ints) {
        if (ints.length < 2) {
            return;
        }
        int mid = ints.length / 2;

        int[] left = new int[mid];
        System.arraycopy(ints, 0, left, 0, mid);

        int[] right = new int[ints.length - mid];
        System.arraycopy(ints, mid, right, 0, ints.length - mid);

        accept(left);
        accept(right);
        merge(ints, left, right);
    }

    private void merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
