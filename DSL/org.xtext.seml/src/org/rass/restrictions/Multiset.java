package org.rass.restrictions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Multisets (combinations with multiple instances of the same element)
 * The output sequences are ordered for convenience
 *
 * @author Alex Salauyou github.com/Salauyou
 * @author Miguel Abreu (extended from Combinations to Multisets)
 */
public class Multiset {
	
	private static List<int[]> subsets = null;

	public static List<int[]> Get(List<Integer> input, int k) {
		
		//The input has to be sorted to:
		//- Allow fast removal of same sequences
		//- Obtain a sorted output
		Collections.sort(input); 
		
		//Initialize list to be returned
		subsets = new ArrayList<>();
		
		int[] s = new int[k];   // here we'll keep indices 
        						// pointing to elements in input array

		if (k <= input.size()) {
		    // first index sequence: 0, 1, 2, ...
		    for (int i = 0; (s[i] = i) < k - 1; i++);  
		    addSubset(input, s);
		    for(;;) {
		        int i;
		        // find position of item that can be incremented
		        for (i = k - 1; i >= 0 && s[i] == input.size() - k + i; i--); 
		        if (i < 0) {
		            break;
		        }
		        s[i]++;                    // increment this item
		        for (++i; i < k; i++) {    // fill up remaining items
		            s[i] = s[i - 1] + 1; 
		        }
		        addSubset(input, s);
		    }
		}

		return subsets;
	}
	
	  // generate actual subset by index sequence
	private static void addSubset(List<Integer> input, int[] subset) {
	     int[] result = new int[subset.length]; 
	     for (int i = 0; i < subset.length; i++) 
	         result[i] = input.get(subset[i]);
	     
	     //add sequence if unique
	     if(!subsets.stream().anyMatch(s -> {
	    	 if(result.length != s.length) return false;
	    	 for(int w=0; w<s.length; w++){
	    		 if(s[w]!=result[w]) return false;
	    	 }
	    	 return true;
	     })) subsets.add(result);
	 }
}
