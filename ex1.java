import java.util.HashMap;

//some idea of a TrivialDictionary using a HashMap, just for testing
class TrivialDictionary {
	private static HashMap<Integer, String> map = new HashMap<Integer, String>();
	private static String[] words = { "a", "ab", "ax", "b", "c", "cr", "d",
			"dx", "fa", "fas", "fast", "r", "ra", "rab", "rc", "t", "w", "we",
			"wer" };
    // If you are doing all this work to organise them, would it not be more
    // convenient to just add all the words into a `HashSet` and just look them
    // up from there?
	static {
		for (int i = 0; i < words.length; i++) {
			String w = words[i];
			map.put(i, w);
		}
	}

	public static String wordAt(int n) {
        // Is this not the same as returning `words[n]` while catching
        // index OOB exceptions?
		return map.get(n);
		// returns null if no mapping for the key. Since our indexes are in
		// order, it is
		// the same thing as being out of bounds
	}

}

public class ex1 {
	public static boolean isInDictionary(String searched) {
		// I assumed the dictionary is sorted(by the definition of a
		// dictionary).
		// Since we do not know the size of the dictionary, start with a
		// superior limit that can halve very fast if it's out of bounds
		//[min,max] is the interval of searching
		int max = 1000; // upper limit
		int min = 0; // lower limit
		boolean found = false;
		while (found == false && min < max) 
		// stop when word is found or when  the lower limit becomes
		// bigger than the upper one
		{
			String word = TrivialDictionary.wordAt(max);
            // Note that in practice you would probably not write to STDOUT
            // unless it was requested by the problem. If this is for debugging,
            // it should probably go to STDERR.
			System.out.println(max + " = " + word + " -> " + min); 
			// this helps for debugging, shows what happens at every step
			if (word == null)
				max = (max+min) / 2; 
			// if index is out of bounds, set upper limit at half the interval
			else { // this works like the binary search algorithm
				if (word.equals(searched))
					found = true;
				if (searched.compareTo(word) > 0)
				// our searched word is "bigger" than the upper limit, set lower
				// limit there and double upper limit
				{
					min = max;
					max = max * 2;
				}
				if (searched.compareTo(word) < 0)
				// the other case, set upper limit at half the interval
				// and let the lower limit as it was
				{
					max = (max+min) / 2;

				}

				
			}

		}
		System.out.println("final:"); // show last call before stopping
		String word = TrivialDictionary.wordAt(max);
		System.out.println(max + " = " + word + " -> " + min);

		return found;
	}
	
	//we could also use the usual binary search, with Integer.MAX_VALUE as an upper bound
	//this is optimal if the TrivialDictionary has a lot of entries
	 public static boolean binarySearch(String searched) {
	        int min = 0;
	        int max = Integer.MAX_VALUE;
	        while (min <= max) {
	            int mid = min + (max - min) / 2;
	            String word=TrivialDictionary.wordAt(mid);
	            System.out.println(mid); //to see the halving process
	            if(word==null) max=mid-1; //out of bounds, try first half
	            else{
	            if      (searched.compareTo(word)<0) max = mid - 1;
	            else if (searched.compareTo(word)>0) min = mid + 1;
	            else return true;
	            }
	        }
	        return false;
	    }

        //do the same thing as isInDictionary, but save results in a hashmap
    // Will `knownWords` approximate the map inside the dictionary as you do more queries?
    // If so, you may as well use that directly, right?
	private static HashMap<Integer,String> knownWords=new HashMap<Integer,String>();
	public static boolean isInDictionaryOptimized(String searched) {
		int max = 1000; // upper limit
		int min = 0; // lower limit
		boolean found = false;
		while (found == false && min < max) 
		{  String word=knownWords.get(max);  //see if previously searched
			if(word==null) word = TrivialDictionary.wordAt(max);  //if not, use TrivialDictionary
			if (word == null)   //out of bounds, evend in TrivialDictionary, halve the interval
				max = (max+min) / 2; 
			else { 
				knownWords.put(max,word);
				
				if (word.equals(searched))
					found = true;
				if (searched.compareTo(word) > 0)
			
				{
					min = max;
					max = max * 2;
				}
				if (searched.compareTo(word) < 0)
				{
					max = (max+min) / 2;

				}

				
			}

		}

		String word = TrivialDictionary.wordAt(max);
		System.out.println(max + " = " + word + " -> " + min);

		return found;
	}
	
	
	public static void main(String[] args) {
		// run some tests
		boolean found = isInDictionary("ax");
		System.out.println("ax is in dictionay? " + found);
		found = isInDictionary("random");
		System.out.println("random is in dictionay? " + found);
		found = isInDictionary("c");
		System.out.println("c is in dictionay? " + found);
		found = isInDictionary("rc");
		System.out.println("rc is in dictionay? " + found);
		//
		String[] words = { "a", "ab", "ax", "b", "c", "cr", "d",
			"dx", "fa", "fas", "fast", "r", "ra", "rab", "rc", "t", "w", "we",
			"wer" };
		boolean all=true;
		for(String s:words){
			all=true&&isInDictionary(s);
		System.out.println("this should be true and is "+all);
		found = isInDictionaryOptimized("rab");
		System.out.println(found);
		System.out.println(knownWords.toString());
		found = isInDictionaryOptimized("r");
		System.out.println(found);
		System.out.println(knownWords.toString());
		System.out.println(binarySearch("fast")); //not nice
		}
	}

}



