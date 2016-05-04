import java.io.*;
import java.util.*;

public class Edit{

	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.print("Give me name for input file: ");
		String namef = console.next();
		Scanner readFile = new Scanner(new File(namef));
		Map<String,List<String>> editDistance = new HashMap<String,List<String>>();
		// Key will be String and values will be list<string>
		makeMap(readFile, editDistance, namef);
		// map is created
		System.out.print(editDistance.get("swab"));
		String x,y;
		do{
			System.out.println("Give me two words separated by a white space.");
			System.out.println("Type \"quit\" if you want to quit. Length of both words must be the same.");
			x = console.next();
			if (!x.equals("quit")){
				y = console.next();
				if((!editDistance.keySet().contains(x) || !editDistance.keySet().contains(y)) && x.length()==y.length()){
					System.out.println("Word does not exist.");
					x = "quit";
				} else if((editDistance.keySet().contains(x) || editDistance.keySet().contains(y)) && x.length()!=y.length()){
					System.out.println("No solution");
					x = "quit";
				} else if((!editDistance.keySet().contains(x) || !editDistance.keySet().contains(y)) && x.length()!=y.length()){
					System.out.println("No solution");
					x = "quit";
				} else{
					findPath(editDistance,x,y);
				}
			}
		} while (!x.equals("quit"));
	}
	
	public static void findPath(Map<String,List<String>> editDistance, String x, String y){
		int count = 0;
		int count1 = 1;
		String t = x;
		List<String>closest = new ArrayList<String>();
		int max = 0;
		int done = 0;
		Set <String> keyset1 = editDistance.keySet();
		System.out.print("Path = " + x + ", ");
		while(done != 1){
			Iterator<String> itr = editDistance.get(t).iterator();
			while(itr.hasNext()){
				String k = itr.next();
				for(int i = 0; i < x.length(); i++){
					if(y.charAt(i) == k.charAt(i)){
						count++;
					}
				}
				if (max < count){
					closest.add(k);
				}
				count = 0;
			}
			if (y.equals(closest.get(closest.size()-1))){
				System.out.println(y);
				System.out.print("Edit distance = " + count1);
				System.out.println("");
				done = 1;
			} else{
				System.out.print(closest.get(closest.size()-1)+", ");
				t = closest.get(closest.size()-1);
				count1++;
			}
		}
		
		
	}
	
	public static void makeMap(Scanner readFile, Map<String,List<String>> editDistance, String namef) throws FileNotFoundException{
		int count = 0;
		while(readFile.hasNext()){
			List <String> neighbors = new LinkedList<String>();
			// linkedlist will be used to store each value
			String word = readFile.next();
			// picks a word for example, dog
			editDistance.put(word, makeValues(word,neighbors,namef));
		}
	}
	
	public static List<String> makeValues(String word, List<String> neighbors, String namef) throws FileNotFoundException {
		List<String> dict = new ArrayList<String>();
		// list to be used to store words from dictionary excluding selected word
		makeDict(dict,word,namef);
		// dict does not include key
		Iterator<String> itr = dict.iterator();
		int count = 0;
		while (itr.hasNext()){
			String x = itr.next();
			// first word on dict
			if(x.length()==word.length()){
				// if their length matches
				for(int i = 0; i < x.length();i++){
					if(x.charAt(i) == word.charAt(i)){
						count++;
					}
				}
				// for loop from 0 to length - 1. x is neighbor of word if their characters differ by only one.
				if ((word.length()-1) == count){
					neighbors.add(x);
				}
			}
			count = 0;
		}
		return neighbors;
	}
	
	public static void makeDict(List<String> dict, String word, String namef) throws FileNotFoundException{
		Scanner readFile2 = new Scanner(new File(namef));
		while(readFile2.hasNext()){
			String x = readFile2.next();
			if (!x.equals(word)){
				dict.add(x);
			}
		}
	}
	
}
