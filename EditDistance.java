import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EditDistance {

	Set<String> dictionary = new HashSet<String>();  //define a new Hashset as Dictionary which whill include the words.
	public static void main(String[] args) throws FileNotFoundException {
		Set<String> dictionary  = intro(); //return dictionary by using intro method.
		words(dictionary); //main method which runs with the dictionary.
	}//main gwalho
	
	public static Set<String> intro() throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		System.out.println("Enter name of dictionary file: ");
		String f = console.next();
		
		Set<String> dictionary = new HashSet<String>();
		Scanner input = new Scanner(new File(f)); //Scanner for the dictionary text file.
		while (input.hasNextLine()){ 
			dictionary.add(input.nextLine()); //add all the dictionary words into the set.
		}
		return dictionary; //return the completed set.
	}
	
	public static void words(Set<String> dictionary){
		Scanner console = new Scanner(System.in);
		int quit = 0; //integer that decides to quit or not.
		
		while(quit==0){
			
			
			System.out.println("Enter the two words seperated by space: ");
			String first = console.next();
			String second = console.next();
			System.out.println("First word: "+first+" // Second word: "+second);
			/*
			 * if the words are in good condition, they run the path method.
			 */
			if (dictionary.contains(first) && dictionary.contains(second) && second.length()==first.length()){ 
				Map<String, String> next = path(dictionary, first, second);
				print(next, first, second);
				/*
				 * if the words are not in same length, they give no solution.
				 */
			} else if (dictionary.contains(first) && (dictionary.contains(second)) && first.length()!= second.length()){
				System.out.println("No Solution.");
			} 
			/*
			 * if the word does not exist, put does not exist.
			 */
			else {
				System.out.println("Word does not exist");
			}
			
			/*
			 * asks if the user wants to quit the edit distance.
			 */
			System.out.println("Do you want to quit?  Y/N");
			String answer = console.next();
			if(answer.equals("Y")||answer.equals("y")){
				quit();
				quit=-1;
			}
			
		}
	}
	
	
	/*
	 * the method which takes the parameter first and second words and the dictionary set.
	 */
	public static Map<String, String> path(Set<String> dictionary, String first, String second){
		Map<String,String> next = new HashMap<String, String>(); //Map that keeps the next word.

		
		List<String> step = new LinkedList<String>(); // the step which gathers the steps of the word.
		step.add(second);
		
		/*
		 * do the loop to the LinkedList and then loop through characters of the word.
		 */
		do{
			next.put(second, null);
			Set<String> newWords = new HashSet<String>();
			for (String word : step){
				for(int i =0; i<word.length();i++){
					char[] chars = word.toCharArray(); //by using toCharArray method, break down the String into characters array.
					for (char c = 'a'; c <= 'z'; c++){ //run through the for loop.
						chars[i] = c; //put the character in the chars array.
						String newWord = new String(chars); //***String(char[]value) sets the initial value of the array which can make to the String form. This part was tricky.
						
						/*
						 * update the next word. from line 84. and keeps the process of matching.
						 */
						if(dictionary.contains(newWord)&& !next.containsKey(newWord)){
							next.put(newWord, word);
							newWords.add(newWord);
						}
						
						/*
						 * if the words are equal, return the next.
						 */
						if (newWord.equals(first)) {
							return next;
						}
					}
				}
			}
			step.addAll(newWords); //reset the step list which contains the new Words of done.
		}
		while(true);
	}
	
	public static void print(Map<String, String> next, String first, String second){
		int count = 0;
		String changing = first;
		System.out.print("Path = ");
		while(changing != null){ //while changing, display the path.
			System.out.print(changing+ " ");
			changing = next.get(changing); //get the next change.
			count++; //total count for editting increases.
		}
		System.out.println();
		System.out.println("Edit distance = " + (count-1));
		
	}
	
	/*
	 * method that indicates the program has been terminated.
	 */
	public static void quit(){
		System.out.println("Done with Edit Distance.");
	}
	
	
	
	
	

} // class gwalho
