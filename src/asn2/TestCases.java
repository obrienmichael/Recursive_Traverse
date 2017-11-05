
package asn2;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class TestCases
{
    public void TestCases(){}

    //test to see if point is out of bounds
    public void testLength()
    {
        LinearSearch ls = new LinearSearch();

        assertFalse(ls.validLength(int [15] haystack));
    }

    //test to see if point is an obstacle or not
    public void isStartNeedleTest()
    {
        LinearSearch ls = new LinearSearch();
        int [] haystack1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        assertTrue(ls.isStartNeedle(5, haystack1, 5, 10));
    }

    public void isFinishNeedleTest()
    {
        LinearSearch ls = new LinearSearch();
        int [] haystack1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        assertTrue(ls.isStartNeedle(5, haystack1, 1, 5));
    }

    public void isNeedleInMiddleTest()
    {
        LinearSearch ls = new LinearSearch();
        int [] haystack1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        assertTrue(ls.isNeedleInMiddle(9, haystack1, 1, 15));
    }