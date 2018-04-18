package yd.zipcodes.zpsorter;

import java.util.LinkedList;

/**
 * Extends the linked list for handling zip code range.
 * At any time the linked list is a sorted list, with smaller lower bounds at the front 
 * of the list, and overlapping are removed.
 *  
 * @author Yuan Ding
 */
public class ZipcodeRangeList extends LinkedList<Range> {
	
	private static final long serialVersionUID = 1111100000L;

	public void buildRange(Range range) {
		// add to the list the first time
		if (isEmpty()) {
			add(range);
			return;
		}
		
		int len = size();
		
		// find the position that new range can be inserted, i.e. range 
		// after new range has lower bound greater or equal to new range's lower bound
		int pos = insertPosition(range);
		
		if (pos == len) {
			// add to the last of the list
			add(range);
			return;
		}
		
		// if lower bounds is the same as the one in the list, check upper bounds, 
		if (get(pos).lower == range.lower) {
			// if new range's upper bounds is greater than existing range's upper bound,
			// do not add new range, just update the existing range's upper bound.
			// otherwise no need to add just return
			if (range.upper > get(pos).upper) {
				get(pos).upper = range.upper;
			}
			return;
		}
		
		// if new range's lower bound is less than prevLink's upper bound, adjust pos to prevLink
		if (pos > 0) {
			Range prevLink = get(pos-1);
			if (prevLink.upper > range.lower) {
				pos--;
				range.lower = prevLink.lower;
			}
		}
		
		// now find range m after pos (range n) that has upper bound less than or equal to n's lower bound, 
		// if n's upper bound is less than the first lower bound, insert new range at pos.
		// otherwise find m repeatedly until there is m2 after m that m2.lower is less than n's upper bound.
		// if no such m2 found (n's upper is greater than all range's lower) remove all the range in the middle 
		// except last one and change its upper bound to the new range's upper bound. 
		// If m's lower bound is  equal to n's upper bound remove all middle range and change
		// m's lower bound  to n's lower bound.
		Range prevRange = null;
		int curPos = pos;
		Range curRange = get(curPos);
		while (curPos < (len-1) && curRange.lower <= range.upper) {
			if (prevRange != null) {
				remove(prevRange);
				len--;
				curPos--;
			}
			prevRange = curRange;
			curRange = get(++curPos);
		}
		if (curRange.lower == range.upper) {
			if (prevRange != null) {
				remove(prevRange);
				len--;
				curPos--;
			}
			curRange.lower = range.lower;
		} else if (curRange.lower < range.upper) {
			if (prevRange != null) {
				remove(prevRange);
				len--;
				curPos--;
			}
			// last range is the only one remains after pos that takes new range's lower bound.
			curRange.lower = range.lower;
			if (curRange.upper < range.upper) {
				curRange.upper = range.upper;
			}
		} else {
			// found m (curRange that has lower greater than range's upper
			if (prevRange == null) { // first one after pos, need to insert new range
				this.add(pos, range);
			} else {
				prevRange.lower = range.lower; // change existing one's lower and upper using new range's accordingly
				if (prevRange.upper < range.upper) {
					prevRange.upper = range.upper;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param range
	 * @return the position of the list that has lower bounds after this position greater
	 * or equal to the given lower bound in the range. Use binary search.
	 */
	protected int insertPosition(Range range) {
		return insertPosition(range, 0, size() - 1);
 	}
	
	/**
	 * 
	 * @param range
	 * @return the position of the list that has lower bounds after this position greater
	 * or equal to the given lower bound in the range. Use binary search.
	 */
	protected int insertPosition(Range range, int first, int last) {
		if (first == 0 && last == 0) {
			if (range.lower <= get(first).lower) {
				return first;
			} else {
				return 1;
			}
		}
		if (last == first + 1) {
			if (range.lower <= get(first).lower) {
				return first;
			} else if (range.lower <= get(last).lower) {
				return last;
			} else {
				return last + 1;
			}
		}
		int mid = first + (last - first) / 2;
		if (mid == 0 || range.lower == get(mid).lower) {
			return mid;
		}
		
		if (range.lower < get(mid).lower) {
			return insertPosition(range, first, mid);
		} else {
			return insertPosition(range, mid, last);
		}
 	}
}
