package yd.zipcodes.zpsorter;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Top level test cases.
 * 
 * @author Yuan Ding
 *
 */
public class ZipcodeSorterTest {

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidBounds() {
		ZipcodeSorter sorter = new ZipcodeSorter();
		sorter.sort(new String[]{"[aavcd,99222]", "[12, 35]"});
	}
	
	
	@Test
	public void testSortNoOverlapping() {
		ZipcodeSorter sorter = new ZipcodeSorter();
		String result = sorter.sort(new String[]{
				"[90500,90600]",
				"[90100,90200]",
				"[90300,90400]",
				"[90010,90020]"
		});
		assertEquals("[90010,90020] [90100,90200] [90300,90400] [90500,90600]", result);
	}
	
	@Test
	public void testSortCombineToOne() {
		ZipcodeSorter sorter = new ZipcodeSorter();
		String result = sorter.sort(new String[]{
				"[90500,90600]",
				"[90100,90200]",
				"[90300,90400]",
				"[90090,90590]"
		});
		assertEquals("[90090,90600]", result);
	}
	
	@Test
	public void testSortExtendingUpper() {
		ZipcodeSorter sorter = new ZipcodeSorter();
		String result = sorter.sort(new String[]{
				"[90500,90600]",
				"[90100,90200]",
				"[90300,90400]",
				"[90150,90260]"
		});
		assertEquals("[90100,90260] [90300,90400] [90500,90600]", result);
	}
	
	@Test
	public void testSortConnectRanges() {
		ZipcodeSorter sorter = new ZipcodeSorter();
		String result = sorter.sort(new String[]{
				"[90500,90600]",
				"[90700,90800]",
				"[90100,90200]",
				"[90300,90400]",
				"[90250,90560]"
		});
		assertEquals("[90100,90200] [90250,90600] [90700,90800]", result);
	}
	
	@Test
	public void testSortConnectReverseRanges() {
		ZipcodeSorter sorter = new ZipcodeSorter();
		String result = sorter.sort(new String[]{
				"[90500,90600]",
				"[90700,90800]",
				"[90100,90200]",
				"[90300,90400]",
				"[90150,90660]"
		});
		assertEquals("[90100,90660] [90700,90800]", result);
	}
}
