package yd.zipcodes.zpsorter;

import org.junit.Test;
import static org.junit.Assert.*;

public class RangeParserTest {

	@Test(expected=IllegalArgumentException.class)
	public void testParseInvalidBounds() {
		RangeParser parser = new RangeParser("99123");
		parser.parse();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseTooMany() {
		RangeParser parser = new RangeParser("[99123,99222,99999]");
		parser.parse();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseNotZip() {
		RangeParser parser = new RangeParser("[99,99222]");
		parser.parse();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseNotNumber() {
		RangeParser parser = new RangeParser("[aavcd,99222]");
		parser.parse();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseUpperLessThanLower() {
		RangeParser parser = new RangeParser("[99999,99222]");
		parser.parse();
	}
	
	@Test
	public void testParseValid() {
		RangeParser parser = new RangeParser("[99123,99222]");
		Range r = parser.parse();
		assertEquals(99123, r.lower);
		assertEquals(99222, r.upper);
	}
}
