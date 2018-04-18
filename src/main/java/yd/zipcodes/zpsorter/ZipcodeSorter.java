package yd.zipcodes.zpsorter;

/**
 * This class is a holder for the method that sort and rearrange zip code ranges
 * so that the overlaps are removed to form a minimum ranges equivalent to the 
 * original ranges.
 * @author Yuan Ding
 *
 */
public class ZipcodeSorter {
	
	/**
	 * Given the zip code ranges, which may or may not have overlapping, find the 
	 * minimum ranges to represent the original
	 * e.g. if given [94133,94133] [94200,94299] [94600,94699]
	 *  the output should be = [94133,94133] [94200,94299] [94600,94699]
	 *  If the input = [94133,94133] [94200,94299] [94226,94399] 
	 *  the output should be = [94133,94133] [94200,94399]
	 * @param ranges - lower and upper bounds. Assuming each range is between '[' and ']'
	 * without white spaces - for parsing simplicity, and all ranges are separated with 
	 * white spaces.
	 * @return - sorted ranges with overlapping removed
	 */
	public String sort(String[] ranges) {
		ZipcodeRangeList rangeList = new ZipcodeRangeList();
		for (int cur = 0; cur < ranges.length; cur++) {
			Range oneRange = new RangeParser(ranges[cur]).parse();
			rangeList.buildRange(oneRange);
		}
		return new RangeFormatter(rangeList).format();
	}

    public static void main( String[] args ) {
        System.out.println( "Sorting zip code ranges." );
    	ZipcodeSorter sorter = new ZipcodeSorter();
    	try {
    		System.out.println("Minimum ranges equivalent to the original is: ");
    		System.out.println(sorter.sort(args));
    	} catch (IllegalArgumentException ex) {
    		usage();
    	}
    }
    
    public static void usage() {
    	System.out.println("Zip code ranges in the format [lower,upper] ...");
    	System.out.println("E.g. [00001,00022] [94133,94133] [94200,94299]");
    }

}
