package yd.zipcodes.zpsorter;

import java.util.LinkedList;

/**
 * Given zip code range in the linked list, produce the output in the format of
 * [lower,upper] [lower,upper] ...
 * @author Yuan Ding
 *
 */
public class RangeFormatter {

	private LinkedList<Range> rangeList;
	
	public RangeFormatter(LinkedList<Range> rangeList) {
		this.rangeList = rangeList;
	}
	
	/**
	 * produce result of the range list in the format [lower,upper] ...
	 * if list is empty, return empty string
	 */
	public String format() {
		if (rangeList.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int len = rangeList.size();
		for (int cur = 0; cur < len; cur++) {
			if (sb.length() > 0) {
				sb.append(' ');
			}
			sb.append(format(rangeList.get(cur)));
		}
		return sb.toString();
	}
	
	/**
	 * Format one range to string, in the format of [lower,upper]
	 * If zip code is less than  5 digits, prepend leading zeros.
	 * @param range
	 * @return formatted zip code range
	 */
	protected String format(Range range) {
		StringBuilder sb = new StringBuilder();
		sb.append('[')
		.append(String.format("%05d",  range.lower))
		.append(',')
		.append(String.format("%05d",  range.upper))
		.append(']');
		return sb.toString();
	}
}
