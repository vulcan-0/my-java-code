package org.example.java.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortingAlgorithms {

    // 1. 冒泡排序
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // 如果没有发生交换，说明数组已经有序
            if (!swapped) break;
        }
    }

    // 2. 选择排序
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // 找到最小元素的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换找到的最小元素与当前元素
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // 3. 插入排序
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // 将大于key的元素向后移动
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 4. 希尔排序
    public static void shellSort(int[] arr) {
        int n = arr.length;

        // 初始步长为n/2，逐渐减小步长
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 对每个子数组进行插入排序
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    // 5. 归并排序
    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;

            // 分成两个子数组
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            // 递归排序子数组
            mergeSort(left);
            mergeSort(right);

            // 合并排序好的子数组
            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // 合并两个子数组
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // 复制剩余元素
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // 6. 快速排序
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 分区操作，返回基准元素的正确位置
            int pi = partition(arr, low, high);

            // 递归排序基准元素左右两边的子数组
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // 选择最右边的元素作为基准
        int pivot = arr[high];
        int i = low - 1; // 小于基准的元素的索引

        for (int j = low; j < high; j++) {
            // 如果当前元素小于或等于基准
            if (arr[j] <= pivot) {
                i++;
                // 交换元素
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 将基准元素放到正确的位置
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // 7. 堆排序
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 从堆中提取元素
        for (int i = n - 1; i > 0; i--) {
            // 将当前根（最大值）移到数组末尾
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 对剩余元素重新构建最大堆
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // 初始化最大值为根节点
        int left = 2 * i + 1; // 左子节点索引
        int right = 2 * i + 2; // 右子节点索引

        // 如果左子节点大于根节点
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // 如果右子节点大于当前最大值
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // 如果最大值不是根节点
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // 递归调整受影响的子树
            heapify(arr, n, largest);
        }
    }

    // 8. 计数排序
    public static void countingSort(int[] arr) {
        int n = arr.length;
        if (n == 0) return;

        // 找到数组中的最大值和最小值
        int max = arr[0], min = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[n];

        // 统计每个元素出现的次数
        for (int num : arr) {
            count[num - min]++;
        }

        // 计算前缀和，确定每个元素在输出数组中的位置
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        // 构建输出数组
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // 将结果复制回原数组
        System.arraycopy(output, 0, arr, 0, n);
    }

    // 9. 桶排序
    public static void bucketSort(int[] arr) {
        int n = arr.length;
        if (n == 0) return;

        // 创建桶
        int numBuckets = (int) Math.sqrt(n) + 1;
        List<Integer>[] buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        // 找到最大值和最小值
        int max = arr[0], min = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        // 计算桶的范围
        int range = (max - min) / numBuckets + 1;

        // 将元素分配到相应的桶中
        for (int num : arr) {
            int bucketIndex = (num - min) / range;
            buckets[bucketIndex].add(num);
        }

        // 对每个桶进行排序，并合并结果
        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    // 10. 基数排序
    public static void radixSort(int[] arr) {
        int n = arr.length;
        if (n == 0) return;

        // 找到最大值，确定需要排序的位数
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }

        // 按照每个位数进行计数排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // 统计每个数字出现的次数
        for (int num : arr) {
            count[(num / exp) % 10]++;
        }

        // 计算前缀和
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 构建输出数组
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // 复制到原数组
        System.arraycopy(output, 0, arr, 0, n);
    }

    // 辅助方法：打印数组
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] temp;

        System.out.println("原始数组:");
        printArray(arr);

        temp = arr.clone();
        bubbleSort(temp);
        System.out.println("冒泡排序后:");
        printArray(temp);

        temp = arr.clone();
        selectionSort(temp);
        System.out.println("选择排序后:");
        printArray(temp);

        temp = arr.clone();
        insertionSort(temp);
        System.out.println("插入排序后:");
        printArray(temp);

        temp = arr.clone();
        shellSort(temp);
        System.out.println("希尔排序后:");
        printArray(temp);

        temp = arr.clone();
        mergeSort(temp);
        System.out.println("归并排序后:");
        printArray(temp);

        temp = arr.clone();
        quickSort(temp);
        System.out.println("快速排序后:");
        printArray(temp);

        temp = arr.clone();
        heapSort(temp);
        System.out.println("堆排序后:");
        printArray(temp);

        temp = arr.clone();
        countingSort(temp);
        System.out.println("计数排序后:");
        printArray(temp);

        temp = arr.clone();
        bucketSort(temp);
        System.out.println("桶排序后:");
        printArray(temp);

        temp = arr.clone();
        radixSort(temp);
        System.out.println("基数排序后:");
        printArray(temp);
    }

}