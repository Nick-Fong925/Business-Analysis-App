package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockTest {
    private Stock testStock;

    @BeforeEach
    void runBefore() {
        testStock = new Stock("tester", 0,0,0,0,0,0);
    }

    @Test
    void TestConstructor() {
        assertEquals("tester", testStock.getName());
        assertEquals(0, testStock.getBuyP());
        assertEquals(0, testStock.getSellP());
        assertEquals(0, testStock.getQuantity());
        assertEquals(0, testStock.getProfit());
        assertEquals(0, testStock.getMargin());
        assertEquals(0, testStock.getRevenue());
    }

    @Test
    void negativeP() {
        testStock.changeBuyP(1);
        testStock.changeSellP(10);
        testStock.changeSellQ(100);
        assertEquals(900, testStock.calcProfit());
        assertEquals(0.9, testStock.calcMargin());
        assertEquals(1000, testStock.calcRev());
    }

    @Test
    void zeroEverything() {
        testStock.changeBuyP(0);
        testStock.changeSellP(1);
        testStock.changeSellQ(0);
        assertEquals(0, testStock.calcProfit());
        assertEquals(0, testStock.calcRev());
        assertEquals(1.0, testStock.calcMargin());
    }


    @Test
    void lossP() {
        testStock.changeBuyP(100);
        testStock.changeSellP(10);
        testStock.changeSellQ(10);
        testStock.calcRev();
        testStock.calcProfit();
        testStock.calcMargin();
        assertEquals(-900, testStock.calcProfit());
        assertEquals(100, testStock.calcRev());
        assertEquals(-9.0,testStock.getMargin());
        assertEquals(100, testStock.getRevenue());
        assertEquals(-900, testStock.getProfit());
    }

    @Test
    void normP() {
        testStock.changeBuyP(10);
        testStock.changeSellP(100);
        testStock.changeSellQ(100);
        testStock.calcRev();
        testStock.calcProfit();
        testStock.calcMargin();
        assertEquals(9000, testStock.calcProfit());
        assertEquals(0.9, testStock.calcMargin());
        assertEquals(10000, testStock.calcRev());
        assertEquals(0.9,testStock.getMargin());
        assertEquals(10000, testStock.getRevenue());
        assertEquals(9000, testStock.getProfit());
        assertEquals(100, testStock.getSellP());
        assertEquals(10, testStock.getBuyP());
    }

    @Test
    void decP() {
        testStock.changeBuyP(2.99);
        testStock.changeSellP(10.99);
        testStock.changeSellQ(100);
        testStock.calcRev();
        testStock.calcProfit();
        testStock.calcMargin();
        assertEquals(800, testStock.calcProfit());
        assertEquals(0.7279344858962693, testStock.calcMargin());
        assertEquals(1099, testStock.calcRev());
        assertEquals(0.7279344858962693, testStock.getMargin());
        assertEquals(1099, testStock.getRevenue());
        assertEquals(800, testStock.getProfit());
    }


    @Test
    void changeBuyP() {
        testStock.changeBuyP(2.99);
        testStock.changeSellP(10.99);
        testStock.changeSellQ(100);
        testStock.changeBuyP(10);
        testStock.changeSellP(100);
        testStock.changeSellQ(100);
        assertEquals(9000, testStock.calcProfit());
        assertEquals(0.9, testStock.calcMargin());
        assertEquals(10000, testStock.calcRev());
        assertEquals(0.9,testStock.getMargin());
        assertEquals(10000, testStock.getRevenue());
        assertEquals(9000, testStock.getProfit());
        assertEquals(100, testStock.getSellP());
        assertEquals(10, testStock.getBuyP());
    }
}
