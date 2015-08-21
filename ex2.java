import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;


public class ex2 {
  public static Integer hasMajority(ArrayList<Integer> list){
    
	Collections.sort(list);
	 int size=list.size();
	 ListIterator<Integer> i=list.listIterator();
	 Integer nr=(Integer) i.next();
	 while(i.hasNext()){
		//according to documentation,
		//alternating calls to next and previous will return the same element repeatedly.
		//get previous element in case the next one does not match anymore after calling nr==i.next() 
		 
            nr=(Integer) i.previous();
	    int count=0;
	    while(nr==i.next()){
	    	count++;
	    	if(count==size/2+1) {
	    		
	    		return nr;   //no point in searching any further
	    	}
	    }
	 }
	 return null;  //no majority
	 
	  
  }
  public static void main(String[] args) {
	//tests
	Integer arr[]={ 5, 3, 9, 4, 3, 3, 8, 3, 3 };
	ArrayList<Integer> list=new ArrayList<Integer>(Arrays.asList(arr));
	System.out.println(hasMajority(list));
	Integer arr2[]= { 5, 3, 9, 4, 3, 3, 8, 3 };
	list=new ArrayList<Integer>(Arrays.asList(arr2));
	System.out.println(hasMajority(list));
}
}

