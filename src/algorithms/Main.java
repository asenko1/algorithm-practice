package algorithms;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.PrintStream;

public class Main {
	public static void main(String args[]){
		Random rand = new Random();
		final int size = 10;
		int[] unSorted = new int[size];
		for(int i = 0; i < size; i++)
			unSorted[i] = rand.nextInt(50);
		
		System.out.println("MergeSort");
		printArray(unSorted);
		System.out.println("------");
		unSorted = MergeSort(unSorted);
		printArray(unSorted);
		
		System.out.println("QuickSort");
		for(int i = 0; i < size; i++)
			unSorted[i] = rand.nextInt(50);
		printArray(unSorted);
		System.out.println("--------");
		quickSort(unSorted, 0, 4);
		printArray(unSorted);
		
		BinarySearchTree bst = new BinarySearchTree();
		bst.popRandData(50, 100);
		PrintStream out = System.out;
		bst.BFS(out);
		}
	
	
	//Takes an integer array and sorts it using the merge sort algorithm
	public static int[] MergeSort(int[] anArray){
		//split operation
		if(anArray.length == 1)
			return anArray; //anArray of size 1 is sorted. "base case"
		
		int mid = anArray.length / 2; //if this is odd, will truncate but this is caught later
		int[] firstHalf = new int[mid];
		int[] secondHalf = new int[anArray.length - mid];
 
		for(int i = 0; i < mid; i++)
			firstHalf[i] = anArray[i];
		for(int i = 0; i < (anArray.length - mid); i++)
			secondHalf[i] = anArray[i+mid];
		firstHalf = MergeSort(firstHalf);
		secondHalf = MergeSort(secondHalf);

		//merge operation
		int[] sortedArray = new int[firstHalf.length + secondHalf.length];
		int i = 0; //firstHalf index
		int j = 0;	//secondHalf index
		int k = 0; //sorted index
		while(k < sortedArray.length){
			if(i < firstHalf.length && j < secondHalf.length){ //not out of bounds of one of the arrays
				if(firstHalf[i] <= secondHalf[j]){
					sortedArray[k] = firstHalf[i];
					k++;
					i++;
				}
				else{
					sortedArray[k] = secondHalf[j];
					k++;
					j++;
				}
			}
			else if(i >= firstHalf.length) //out of bounds on firstHalf, add rest of secondHalf
				for(; j < secondHalf.length; j++){
					sortedArray[k] = secondHalf[j];
					k++;
				}
			else{ //out of bounds on secondHalf, add rest of firstHalf
				for(; i < firstHalf.length; i++){
					sortedArray[k] = firstHalf[i]; 
					k++;
				}
			}
		}
		return sortedArray;
	}
	
	//Takes an array and sorts using quick sort algorithm
	//Uses random partitioning to reduce chance of worst case time complexity
	public static int[] quickSort(int[] Arr, int start, int end){
		if(start >= end)
			return Arr;
		int pIndex = partition(Arr, start, end);
		Arr = quickSort(Arr, start, pIndex -1);
		Arr = quickSort(Arr, pIndex + 1, end);
		return Arr;
	}
	
	private static int partition(int[] Arr, int start, int end){
		int pivot = ThreadLocalRandom.current().nextInt(start,end); //getting random pivot point in the array
		
		//Swap pivot index to end of array
		swap(Arr, pivot, end);
		
		pivot = Arr[end];
		int pIndex = start;
		for(int i = start; i < end; i++){
			if(Arr[i] <= pivot){
				swap(Arr, i, pivot);
				pIndex++;
			}
		}
		swap(Arr, pIndex, end);
		return pIndex;
	}
	
	private static int[] swap(int[] Arr, int i1, int i2){
		int swap = Arr[i1];
		Arr[i1] = Arr[i2];
		Arr[i2] = swap;
		
		return Arr;
	}
	
	public static void printArray(int[] anArray){
		for(int i = 0; i < anArray.length; i++)
			System.out.println(anArray[i]);
	}
}
