/*
Algorithm does not seem exactly right when there are duplicates in the matrix (i.e. for {2,3,3,9] 3rd largest is 3, not 2 as it would be intuitively).
On samples with no duplicates it works fine.

My idea was to use quicksort to sort the matrix descending.
This method uses divide et conquer.
We choose a pivot(the irst element in a subarray) and put all the elements that are bigger or equal to it to its left an those that are less or equal to its right. After this,
the pivot is in its right place so we return its position.
At every step, check if the final position of the pivot matches our input n. If it does, it means the pivot has n-1 numbers bigger than it to its left, so the pivot is the nth 
largest number and we are done..
If it's bigger than we need, keep searching in the subarray until the index, because we need less numbers to the pivot's left. (this is the divide/partitioning step)
If it's smaller, keep searching from the index onwards, because we need more numbers.

It might be a good idea to check the input. If we want te nth largest number where n is bigger than length/2, we could find the (length-n) smallest number, as it is less to search.
*/

import java.util.Arrays;

public class ex3 {
	int[] arr;

	public ex3(int[] arr) {
		this.arr = arr;
	}

	public int nthLargest(int n) throws Exception {
		if (arr.length < n || n == 0) {
			throw new Exception("Choose a different number");
		} else {
			return select(arr, 0, arr.length - 1, n - 1);
        //pass n-1 instead of n to convert to array conventions
		}
	}

	public int select(int[] arr, int lo, int hi, int n) {
		if (lo < hi)  //only make sense if we have more than 1 element in the subarray
         {
			int p = partition(arr, lo, hi);

			if (n < p)
            //we need a number with less numbers that are bigger than it, continue on the left
				return select(arr, lo, p - 1, n);
			else if (n > p)
            //we need a bigger position, continue on the right
				return select(arr, p + 1, hi, n);
			else
            //we found it
				return arr[p];

		}
		return arr[lo];
	}

	private int partition(int[] arr, int lo, int hi) {
		int p = arr[lo];
		int i = lo;
		int j = hi + 1;
		while (true) {
			while (arr[++i] > p)
				if (i == hi)
					break;
			while (arr[--j] < p)
				if (j == lo)
					break;
			if (i >= j)
				break;
			swap(arr, i, j);
		}
       //at this point, j is where the pivot's right place is, so swap them
		swap(arr, lo, j);
       //return pivot's position to be used by select()
		return j;
	}

   //method to swap 2 array elements

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) throws Exception {
       //run some tests
		int[] arr = { 12, 6, 5, 34, 8, 9, 2, 32, 1, 67 };
		// 1 2 5 6 8 9 12 32 34 67
		ex3 test = new ex3(arr);
		int n = test.nthLargest(7);
		System.out.println(n);
		int[] arr2 = { 5, 3, 9, 4, 3, 3, 8, 3, 3 };
		ex3 test2 = new ex3(arr2);
		n = test2.nthLargest(1);
		System.out.println(n);
		n = test2.nthLargest(4);
		System.out.println(n);
        int[] a=new int[10000];  //big array
		for(int i=0;i<10000;i++)
			a[i]=0;
		a[200]=67;
		ex3 test4 = new ex3(arr);
		n = test4.nthLargest(1);
		System.out.println(n);
	}
}

