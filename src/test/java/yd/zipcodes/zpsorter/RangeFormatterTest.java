package yd.zipcodes.zpsorter;

import static org.junit.Assert.*;

import org.junit.Test;

public class RangeFormatterTest {

	@Test
	public void testFormatRange() {
		RangeFormatter formatter = new RangeFormatter(null);
		assertEquals("[00012,00345]", formatter.format(new Range(12, 345)));
	}
	
	@Test
	public void testFormatRange2() {
		RangeFormatter formatter = new RangeFormatter(null);
		assertEquals("[94521,95332]", formatter.format(new Range(94521,95332)));
	}
	
	@Test
	public void testFormat() {
		ZipcodeRangeList list = new ZipcodeRangeList();
		list.add(new Range(12, 345));
		list.add(new Range(94521,95332));
		RangeFormatter formatter = new RangeFormatter(list);
		assertEquals("[00012,00345] [94521,95332]", formatter.format());
	}
}
