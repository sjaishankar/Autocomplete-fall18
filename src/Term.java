
/*************************************************************************
 * @author Kevin Wayne
 *
 * Description: A term and its weight.
 * 
 *************************************************************************/

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private final String myWord;
	private final double myWeight;

	/**
	 * The constructor for the Term class. Should set the values of word and
	 * weight to the inputs, and throw the exceptions listed below
	 * 
	 * @param word
	 *            The word this term consists of
	 * @param weight
	 *            The weight of this word in the Autocomplete algorithm
	 * @throws NullPointerException
	 *             if word is null
	 * @throws IllegalArgumentException
	 *             if weight is negative
	 */
	public Term(String word, double weight) {
		//throw exceptions for invalid word inputs or invalid weights
		if(word == null) throw new NullPointerException("no word inputted");
		if(weight < 0) throw new IllegalArgumentException("negative weight " + weight);
		
		myWord = word;
		myWeight = weight;
	}
	
	/**
	 * The default sorting of Terms is lexicographical ordering.
	 */
	public int compareTo(Term that) {
		return myWord.compareTo(that.myWord);
	}

	/**
	 * Getter methods, use these in other classes which use Term
	 */
	public String getWord() {
		return myWord;
	}

	public double getWeight() {
		return myWeight;
	}

	public String toString() {
		return String.format("(%2.1f,%s)", myWeight, myWord);
	}
	
	@Override
	public boolean equals(Object o) {
		Term other = (Term) o;
		return this.compareTo(other) == 0;
	}

	/**
	 * A Comparator for comparing Terms using a set number of the letters they
	 * start with. This Comparator may be useful in writing your implementations
	 * of Autocompletors.
	 *
	 */
	public static class PrefixOrder implements Comparator<Term> {
		private final int myPrefixSize;

		public PrefixOrder(int r) {
			this.myPrefixSize = r;
		}

		/**
		 * Compares v and w lexicographically using only their first r letters.
		 * If the first r letters are the same, then v and w should be
		 * considered equal. This method should take O(r) to run, and be
		 * independent of the length of v and w's length. You can access the
		 * Strings to compare using v.word and w.word.
		 * 
		 * @param v/w
		 *            - Two Terms whose words are being compared
		 *          
		 */
		public int compare(Term v, Term w) {
			/*Checks to see if both Terms are shorter than the prefix length, and if so and they are equal, return 0.
			If both Terms are shorter than prefix length but are not equal, the third if block runs.*/
			if(v.getWord().length() < myPrefixSize && w.getWord().length() < myPrefixSize) {
				if(v.getWord().equals(w.getWord())) return 0;
			}
			
			//If both Terms are longer than prefix size, compares the first prefix-length characters of the two Terms
			if(v.getWord().length() >= myPrefixSize && w.getWord().length() >= myPrefixSize) {
				for(int i = 0; i < myPrefixSize; i++) {
					if(v.getWord().charAt(i) != w.getWord().charAt(i)) {
						return v.getWord().charAt(i) - w.getWord().charAt(i);
					}
				}
				return 0;
			}
			
			//compares two Terms up to Term v's length if Term v's length is shorter than the prefix size
			if(v.getWord().length() < myPrefixSize) {
				for(int i = 0; i < v.getWord().length(); i++) {
					if(v.getWord().charAt(i) != w.getWord().charAt(i)) {
						return v.getWord().charAt(i) - w.getWord().charAt(i);
					}
				}
				return -1;
			}
			
			//compares two Terms up to Term w's length if Term w's length is shorter than the prefix size
			if(w.getWord().length() < myPrefixSize) {
				for(int i = 0; i < w.getWord().length(); i++) {
					if(v.getWord().charAt(i) != w.getWord().charAt(i)) {
						return v.getWord().charAt(i) - w.getWord().charAt(i);
					}
				}
				return 1;
			}
			
			return 0;
			
		}
	
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in descending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 *
	 *compareTo method that sorts Terms based solely on their weights in descending order
	 */
	public static class ReverseWeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			if(v.getWeight() > w.getWeight())
				return -1;
			if(v.getWeight() < w.getWeight())
				return 1;
			
			return 0;
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in ascending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 * 
	 * compareTo method that sorts Terms based solely on their weights in ascending order
	 *
	 */
	public static class WeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			if(v.getWeight() < w.getWeight())
				return -1;
			if(v.getWeight() > w.getWeight())
				return 1;
			
			return 0;
		}
	}
}
