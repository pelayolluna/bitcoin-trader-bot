/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pelayolluna.bitcointraderbot.datamanagers;

import com.pelayolluna.bitcointraderbot.beans.BtcPrice;
import com.pelayolluna.bitcointraderbot.beans.OrderBook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pelayo Jose Lluna Gonzalez
 */
public class BitstampDataTest {

    public BitstampDataTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTickerData method, of class BitstampData.
     */
    @Test
    public void testGetTickerData() {
        System.out.println("getTickerData");
        BtcPrice expResult = null;
        BtcPrice result = BitstampData.getTickerData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrderBookData method, of class BitstampData.
     */
    @Test
    public void testGetOrderBookData() {
        System.out.println("getOrderBookData");
        OrderBook expResult = null;
        OrderBook result = BitstampData.getOrderBookData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConversionRate method, of class BitstampData.
     */
    @Test
    public void testGetConversionRate() {
        System.out.println("getConversionRate");
        float expResult = 0.0F;
        float result = BitstampData.getConversionRate();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
