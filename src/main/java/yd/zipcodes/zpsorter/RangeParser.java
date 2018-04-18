package yd.zipcodes.zpsorter;

import java.util.StringTokenizer;

/**
 * This is a class that parse the range given in the format of [lower,upper],
 * with lower and upper a 5 digits ziup code.
 * @author Yuan Ding
 *
 */
public class RangeParser {

	private String rangeStr;
	
	public RangeParser(String rangeStr) {
		this.rangeStr = rangeStr;
	}
	
	/**
	 * @throws IllegalArgumentException if its not in the format [lower,upper]
	 *   or NumberFormatException if lower upper bounds are not integer, both
	 *   RuntimeException
	 * @param rangeStr
	 * @return parsed zip code range
	 */
	public Range parse() {
		if (rangeStr.length() < 2) {
			throw new IllegalArgumentException("Not enough for a range in the format [lower,upper],==>"+rangeStr);			
		}
		int len = rangeStr.length();
		if (rangeStr.charAt(0) != '[' || rangeStr.charAt(len-1) != ']') {
			throw new IllegalArgumentException("Zipcode range should be in format [lower,upper],==>"+rangeStr);
		}
		StringTokenizer st = new StringTokenizer(rangeStr.substring(1, len-1), ",");
		String lowerStr = st.nextToken();
		if (lowerStr.length() != 5) {
			throw new IllegalArgumentException("Not a zip code ==> "+lowerStr);
		}
		int lower = Integer.parseInt(lowerStr);
		if (!st.hasMoreTokens()) {
			throw new IllegalArgumentException("Zipcode range should be in format [lower,upper],==>"+rangeStr);
		}
		String upperStr = st.nextToken();
		if (upperStr.length() != 5) {
			throw new IllegalArgumentException("Not a zip code ==> "+upperStr);
		}
		int upper = Integer.parseInt(upperStr); 
		if (upper <= lower) {
			throw new IllegalArgumentException("Upper bound should be greater than lower bound ==> "+rangeStr);
		}
		if (st.hasMoreTokens()) {
			throw new IllegalArgumentException("Excessive string in zip code bounds ==> "+rangeStr);
		}
		return new Range(lower, upper);
	}
}
