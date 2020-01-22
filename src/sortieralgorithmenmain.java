import java.util.Random;

public class sortieralgorithmenmain 
{
	static Random r = new Random();
	static final int LAENGE=5;
	static long sortierZeit;
	static long bogoCounter = 0;

	public static void main(String[] args) 
	{
		int[] menge = new int[LAENGE];	
		for (int i = 0; i < menge.length; i++) 
		{
			menge[i] = r.nextInt(menge.length);
		}
		ausgabe("Original Array:", menge);
		
		int[] bubbleS = bubbleSort(menge);
		ausgabe("BubbleSort:", bubbleS, sortierZeit);
		
		int[] insertionS = insertionSort(menge);
		ausgabe("InsertionSort:", insertionS, sortierZeit);
		
		int[] selectionS = selectionSort(menge);
		ausgabe("SelectionSort:", selectionS, sortierZeit);
		
		int[] quickS = quickSort(menge);
		ausgabe("QuickSort:", quickS, sortierZeit);
		
		int[] bogoS = bogoSort(menge);
		ausgabe("BogoSort: ("+bogoCounter+" Sortierschritte)", bogoS, sortierZeit);
		
		int[] mergeS = merge(menge);
		ausgabe("MergeSort:", mergeS, sortierZeit);
	}
	
	//--BubbleSort--
	
	public static int[] bubbleSort(int[] menge) 
	{
		int[] a = arrayKopieren(menge);
		long startZeit = aktuelleZeit();
		int temp;
		for(int i = 1; i < a.length; i++) 
		{
			for(int j = 0; j < a.length - i; j++) 
			{
				if(a[j] > a[j + 1]) 
				{
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		sortierZeit = aktuelleZeit() - startZeit;
		return a;
	}
	
	//--InsertionSort--
	
	public static int[] insertionSort(int[] menge) 
	{
		int[] a = arrayKopieren(menge);
		long startZeit = aktuelleZeit();
		int temp;
		for (int i = 1; i < a.length; i++) 
		{
			temp = a[i];
			int j = i;
			while (j > 0 && a[j - 1] > temp) 
			{
				a[j] = a[j - 1];
				j--;
			}
			a[j] = temp;
		}
		sortierZeit = aktuelleZeit() - startZeit;
		return a;
	}
	
	//--SelectionSort--
	
	public static int[] selectionSort(int[] menge) {
		int[] a = arrayKopieren(menge);
		long startZeit = aktuelleZeit();
		for (int i = 0; i < a.length - 1; i++) 
		{
			for (int j = i + 1; j < a.length; j++) 
			{
				if (a[i] > a[j]) 
				{
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		sortierZeit = aktuelleZeit() - startZeit;
		return a;
	}
	
	//--BogoSort-- (German Wiki)
	
	public static int[] bogoSort(int[] menge)
	{
		int[] a = arrayKopieren(menge);
		long startZeit = aktuelleZeit();
		while(!sortiert(a)) { //check if the array is sorted, if not swap 2 random positions
			bogoCounter++;
			//Select 2 random positions of the array
			int index1 = r.nextInt(a.length);
			int index2 = r.nextInt(a.length);
			//swap the selected positions
			int temp = a[index1];
			a[index1] = a[index2];
			a[index2] = temp;
		}
		sortierZeit = aktuelleZeit() - startZeit;
		return a;
	}
		
		

	public static boolean sortiert(int[] a) 
	{
		boolean sortiert = true;
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				sortiert = false;
				break;
			}
		}
		return sortiert;
	}
	
	//--QuickSort--
	
	public static int[] quickSort(int[] menge) {
		int[] a = arrayKopieren(menge);
		long startZeit = aktuelleZeit();
		quickSortLoop(a, 0, LAENGE - 1);
		sortierZeit = aktuelleZeit() - startZeit;
		return a;
	}
	
	//Loops the Sorting-Algorithm of QuickSort
	
	public static void quickSortLoop(int[] a, int start, int end) {
		if (start >= end) {
			return;
		}
		int p = partition(a, start, end);
		quickSortLoop(a, start, p-1);
		quickSortLoop(a, p+1, end);
	}
	
	//Sorting of an partition of a quickSort-Array
	
	public static int partition(int[] a, int start, int end) {
		int pivot = a[start];
		int low = start + 1;
		int high = end;
		while (true) {
			while ((low <= high) && (a[high] >= pivot)) {
				high = high - 1;
			}
			while ((low <= high) && (a[low] <= pivot)) {
				low = low + 1;
			}
			if (low <= high) {
				int[] copyArray = arrayKopieren(a);
				a[low] = copyArray[high];
				a[high] = copyArray[low];
			}
			else {
				break;
			}
		}
		int[] copyArray = arrayKopieren(a);
		a[high] = copyArray[start];
		a[start] = copyArray[high];
		return high;
	}
	
	//--MergeSort--
	
	void merge(int menge[], int l, int m, int r) 
	{ 
		int[] a = arrayKopieren(menge);
		long startZeit = aktuelleZeit();
	    int i, j, k; 
	    int n1 = m - l + 1; 
	    int n2 =  r - m; 
	  
	    /* create temp arrays */
	    int L[n1], R[n2]; 
	  
	    /* Copy data to temp arrays L[] and R[] */
	    for (i = 0; i < n1; i++) 
	        L[i] = a[l + i]; 
	    for (j = 0; j < n2; j++) 
	        R[j] = a[m + 1+ j]; 
	  
	    /* Merge the temp arrays back into arr[l..r]*/
	    i = 0; // Initial index of first subarray 
	    j = 0; // Initial index of second subarray 
	    k = l; // Initial index of merged subarray 
	    while (i < n1 && j < n2) 
	    { 
	        if (L[i] <= R[j]) 
	        { 
	            a[k] = L[i]; 
	            i++; 
	        } 
	        else
	        { 
	            a[k] = R[j]; 
	            j++; 
	        } 
	        k++; 
	    } 
	  
	    /* Copy the remaining elements of L[], if there 
	       are any */
	    while (i < n1) 
	    { 
	        a[k] = L[i]; 
	        i++; 
	        k++; 
	    } 
	  
	    /* Copy the remaining elements of R[], if there 
	       are any */
	    while (j < n2) 
	    { 
	        a[k] = R[j]; 
	        j++; 
	        k++; 
	    } 
	} 
	  
	/* l is for left index and r is right index of the 
	   sub-array of arr to be sorted */
	void mergeSort(int a[], int l, int r) 
	{ 
	    if (l < r) 
	    { 
	        // Same as (l+r)/2, but avoids overflow for 
	        // large l and h 
	        int m = l+(r-l)/2; 
	  
	        // Sort first and second halves 
	        mergeSort(a, l, m); 
	        mergeSort(a, m+1, r); 
	  
	        merge(a, l, m, r); 
	    } 
	} 
	
	

	public static long aktuelleZeit() 
	{
		return System.nanoTime();
	}

	public static int[] arrayKopieren(int[] menge) {
		int[] arrayKopie = new int[menge.length];
		for(int i=0;i<menge.length;i++)
		{
			arrayKopie[i] = menge[i];
		}
		return arrayKopie;
	}
	
	public static void ausgabe(String s, int[] a) {
		System.out.println(s);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println("\n");
	}
	
	public static void ausgabe(String s, int[] a, long zeit) {
		System.out.println(s+" ("+zeit+"ns)");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println("\n");
	}

}
