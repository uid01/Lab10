import java.util.*;

//Fully implement the 4 given method frames using the specifications with the given signatures and without without adding any additional methods
public class MergeSort
{
    private static int[][] data;  //first dimenion should be 2, second dimension should be the first command line argument
    private static int comps =0;
    
    public static void main(String[] args)
    {
        setup(args);
        System.out.println("Initially: "+getContent(0));
        iterativeMergeSort();
        System.out.println("Sorted:    "+getContent(0)); // assumes sorted array is in data[0] otherwise update to 1
        System.out.println("Performed "+comps+" item comparisons.\n\n");
    } //end main

// uses the first 3 command line arguments as follows: 
// first argument = num (number of items to be sorted)
// second argument = low (the beginning(low end) of the range)
// third argument = high (the upper(high) end of the range)
// allocates the needed amount of memory for data[0] and data[1]
// initializes the array data[0] with num randomly generated integers in the range [low,high]
    public static void setup( String[] args) {   
          //complete this method
          data = new int[2][Integer.parseInt(args[0])];
          int num = Integer.parseInt(args[0]);
          int low = Integer.parseInt(args[1]);
          int high = Integer.parseInt(args[2]);
          Random rand = new Random();
          for (int i = 0; i < num; i++) {
            data[0][i] = rand.nextInt(high - low + 1) + low;
        }
          
    } //end setup

// iteratively merge sorts the input in array data as described in class -> by repeatedly calling method merge 
// and using data[0] and data[1] as merge source and merge destination respectively by passing the correct integer 0 or 1 as "sindex" parameter
    public static void iterativeMergeSort() { 
    //complete this method
    
        int start = 0;
        int len = 1;
        int sindex = 0;
        while (len < data[sindex].length) {
            while (start < data[sindex].length) {
                merge(sindex, start, len);
                start += len * 2;
                
            }
            start = 0;
            len *= 2;
            sindex = sindex == 1 ? 0 : 1;
        }
        
        if (sindex == 1) {
            
            for (int i = 0; i < data[1].length; i++) {
                data[0][i] = data[1][i];
            }
        }
    
      
    } //end iterativeMergeSort
    
    
// merges the subarrays from the array index in "data" designated as source by "sindex" into the other array in "data" (destination)
// first subarray in source array starts at index "start" and is of size "len", the second subarray starts exactly after the first subarray and is of size "len" *unless that length would exceed the length of the array*
// merges the 2 subarrays from source array into a subarray in array destination starting at index "start"  
public static void merge(int sindex, int start, int len) {
    // Set the index of the destination array based on the source array index
    int dindex = (sindex == 1 ? 0 : 1);
    // Initialize pointers for the two subarrays
    int i = start;
    int j = (start+len<data[sindex].length? start+len: data[sindex].length-1);
    int k = start;
    int n = (2*len + start < data[sindex].length? 2*len + start : data[sindex].length);
    // Merge the two subarrays by comparing elements from both subarrays and copying them to the destination array in sorted order
    while (i < start + len && j < n && i < data[sindex].length) {
        comps++;
        if (data[sindex][i] <= data[sindex][j]) {
            data[dindex][k] = data[sindex][i];
            i++;
        } else {
            data[dindex][k] = data[sindex][j];
            j++;
        }
        k++;
    }
    if(k < data[sindex].length){
    // Copy the remaining elements from the first subarray to the destination array
    while (i < (start + len < data[sindex].length? start + len: data[sindex].length) ) {
        data[dindex][k] = data[sindex][i];
        i++;
        k++;
    }
    
    // Copy the remaining elements from the second subarray to the destination array
    while (j < (start + 2*len < data[sindex].length? start +2*len: data[sindex].length)) {
        data[dindex][k] = data[sindex][j];

        j++;
        k++;
    }
}
}
//end merge

// parameter sindex is array index in data where information is located
// returns a StringBuilder representation of the collection in the data[sindex] array (collects first to last)
// you cannot use any String variables, only StringBuilder
    public static StringBuilder getContent (int sindex) {   
        //complete this method
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < data[sindex].length; i++){
        str.append(data[sindex][i] + " ");
    }
    return str;
}


} //end MergeSort