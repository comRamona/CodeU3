public class ex1{
public static isInDictionary(String w){
int n=1000;

boolean found=false;
while(n!=0||found==true) {
String word=TrivialDictionary.wordAt(n);
if(word==null) n=n/2;
   else
   {
    if(word.equals(w)) found=true;
    if(w.compareTo(word)>0) n=n*2;
    if(w.compareTo(word)<0) n=n/2;	
   }

}
if(TrivialDictionary.wordAt(0).equals(w)) found=true;
return found;
}
public static void main(String[] args){
boolean found=isInDictionary("ax");
System.out.println("ax is in dictionay? "+found);
found=isInDictionary("random");
System.out.println("random is in dictionay? "+found);
}
}

class TrivialDictionary()}
private static HashMap<Int,String> map=new HashMap<Int,String>();
private static String[] words={"a","ab","ax","b","c","d","dx","r"};
for(int i=0;i<words.length;i++){
    w=words[i];
    HashMap.put(i,w);
	}
private static wordAt(int n){
  return map.get(n); 
 //returns null if no mapping for the key. Since our indexes are in order, it is 
 //the same thing as being out of bounds
}

}


