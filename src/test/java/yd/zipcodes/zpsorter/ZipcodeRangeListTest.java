package yd.zipcodes.zpsorter;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * Self descriptive test cases cover the corner cases.
 */
public class ZipcodeRangeListTest 
{
    @Test
    public void testInsertPosition() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 150));
    	
    	int pos = list.insertPosition(new Range(200, 250));
    	assertEquals(1, pos);
    	
    	pos = list.insertPosition(new Range(10, 20));
    	assertEquals(0, pos);
    	
    	list.add(new Range(200, 250));
    	list.add(new Range(300, 350));
    	list.add(new Range(400, 450));
    	
    	pos = list.insertPosition(new Range(10, 20));
    	assertEquals(0, pos);
    	
    	pos = list.insertPosition(new Range(130, 140));
    	assertEquals(1, pos);
    	
    	pos = list.insertPosition(new Range(230, 240));
    	assertEquals(2, pos);
    	
    	pos = list.insertPosition(new Range(330, 340));
    	assertEquals(3, pos);
    	
    	pos = list.insertPosition(new Range(430, 440));
    	assertEquals(4, pos);
    	
    	pos = list.insertPosition(new Range(100, 190));
    	assertEquals(0, pos);
    	
    	pos = list.insertPosition(new Range(200, 290));
    	assertEquals(1, pos);
    	
    	pos = list.insertPosition(new Range(300, 390));
    	assertEquals(2, pos);
    	
    	pos = list.insertPosition(new Range(400, 490));
    	assertEquals(3, pos);
    }
    
    @Test
    public void testNewRangeAddToEmpty() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.buildRange(new Range(100, 200));
    	assertEquals(1, list.size());
    	Range r = list.get(0);
    	assertEquals(100, r.lower);
    	assertEquals(200, r.upper);
    }
    
    @Test
    public void testNewRangeNoOverlappingAddFront() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.buildRange(new Range(10, 20));
    	assertEquals(4, list.size());
    	Range r = list.get(0);
    	assertEquals(10, r.lower);
    	assertEquals(20, r.upper);
    }
    
    @Test
    public void testNewRangeNoOverlappingAddTail() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.buildRange(new Range(700, 800));
    	assertEquals(4, list.size());
    	Range r = list.get(3);
    	assertEquals(700, r.lower);
    	assertEquals(800, r.upper);
    }
    
    @Test
    public void testNewRangeNoOverlappingAddMiddle() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.buildRange(new Range(450, 460));
    	assertEquals(4, list.size());
    	Range r = list.get(2);
    	assertEquals(450, r.lower);
    	assertEquals(460, r.upper);
    }
    
    @Test
    public void testNewRangeCombineToOne() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.buildRange(new Range(90, 590));
    	assertEquals(1, list.size());
    	Range r = list.get(0);
    	assertEquals(90, r.lower);
    	assertEquals(600, r.upper);
    }
    
    @Test
    public void testNewRangeCombineToOneExtendingUpperBound() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.buildRange(new Range(90, 700));
    	assertEquals(1, list.size());
    	Range r = list.get(0);
    	assertEquals(90, r.lower);
    	assertEquals(700, r.upper);
    }
    
    @Test
    public void testNewRangeNoAction() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.buildRange(new Range(150, 190));
    	assertEquals(3, list.size());
    	Range r1 = list.get(0);
    	assertEquals(100, r1.lower);
    	assertEquals(200, r1.upper);
    	Range r2 = list.get(1);
    	assertEquals(300, r2.lower);
    	assertEquals(400, r2.upper);
    }
    
    @Test
    public void testNewRangeNoInsertButExtending() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.buildRange(new Range(150, 260));
    	assertEquals(3, list.size());
    	Range r1 = list.get(0);
    	assertEquals(100, r1.lower);
    	assertEquals(260, r1.upper);
    	Range r2 = list.get(1);
    	assertEquals(300, r2.lower);
    	assertEquals(400, r2.upper);
    }
    
    @Test
    public void testNewRangeConnectRanges() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.add(new Range(700, 800));
    	list.buildRange(new Range(250, 560));
    	assertEquals(3, list.size());
    	Range r1 = list.get(0);
    	assertEquals(100, r1.lower);
    	assertEquals(200, r1.upper);
    	Range r2 = list.get(1);
    	assertEquals(250, r2.lower);
    	assertEquals(600, r2.upper);
    	Range r3= list.get(2);
    	assertEquals(700, r3.lower);
    	assertEquals(800, r3.upper);
    }
    
    @Test
    public void testNewRangeConnectRangesFurther() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.add(new Range(700, 800));
    	list.buildRange(new Range(350, 660));
    	assertEquals(3, list.size());
    	Range r1 = list.get(0);
    	assertEquals(100, r1.lower);
    	assertEquals(200, r1.upper);
    	Range r2 = list.get(1);
    	assertEquals(300, r2.lower);
    	assertEquals(660, r2.upper);
    	Range r3= list.get(2);
    	assertEquals(700, r3.lower);
    	assertEquals(800, r3.upper);
    }
    
    @Test
    public void testNewRangeConnectRangesReverse() {
    	ZipcodeRangeList list = new ZipcodeRangeList();
    	list.add(new Range(100, 200));
    	list.add(new Range(300, 400));
    	list.add(new Range(500, 600));
    	list.add(new Range(700, 800));
    	list.buildRange(new Range(150, 380));
    	assertEquals(3, list.size());
    	Range r1 = list.get(0);
    	assertEquals(100, r1.lower);
    	assertEquals(400, r1.upper);
    	Range r2 = list.get(1);
    	assertEquals(500, r2.lower);
    	assertEquals(600, r2.upper);
    	Range r3= list.get(2);
    	assertEquals(700, r3.lower);
    	assertEquals(800, r3.upper);
    }
}
