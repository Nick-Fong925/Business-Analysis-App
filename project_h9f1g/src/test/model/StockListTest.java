package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockListTest {
    private StockList testList;

    Stock stock1 = new Stock("hat", 1,1,1,1,1,1);
    Stock stock2 = new Stock("shirt",1,1,1,1,1,1);
    Stock stock3 = new Stock("hoodie", 1,1,1,1,1,1);

    @BeforeEach
    void runBefore(){
        testList = new StockList();
    }

    @Test
    public void emptyListStock() {
        ArrayList<String> result = new ArrayList<>();
        assertEquals(result, testList.returnListP());
        assertEquals(result, testList.returnListQ());
        assertEquals(0, testList.getNumOfStock());

    }

    @Test
    public void SomeList() {
        testList.addStock(stock1);
        ArrayList<String> result = new ArrayList<>();
        assertEquals(result, testList.returnListP());
        assertEquals(result, testList.returnListQ());
    }

    @Test
    public void makeLists() {
        testList.makeList(stock1);
        ArrayList<String> resultP = new ArrayList<>();
        resultP.add("\nItem name:" + " " + "hat");
        resultP.add("Profit:" + " " + "0.0");
        resultP.add("Revenue:" + " " + "1.0");
        ArrayList<String> resultQ = new ArrayList<>();
        resultQ.add("\nItem name:" + " " + "hat");
        resultQ.add("Quantity:" + " " + "1");
        resultQ.add("Profit Margin:" + " " + "0.0");
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("\n" + "hat");
        assertEquals(nameList, testList.returnNameList());
        assertEquals(resultP, testList.returnListP());
        assertEquals(resultQ, testList.returnListQ());
        assertEquals(0, testList.getNumOfStock());
    }

    @Test
    public void makeLists2() {
        testList.makeList(stock1);
        testList.makeList(stock2);
        ArrayList<String> resultP = new ArrayList<>();
        resultP.add("\nItem name:" + " " + "hat");
        resultP.add("Profit:" + " " + "0.0");
        resultP.add("Revenue:" + " " + "1.0");
        resultP.add("\nItem name:" + " " + "shirt");
        resultP.add("Profit:" + " " + "0.0");
        resultP.add("Revenue:" + " " + "1.0");
        ArrayList<String> resultQ = new ArrayList<>();
        resultQ.add("\nItem name:" + " " + "hat");
        resultQ.add("Quantity:" + " " + "1");
        resultQ.add("Profit Margin:" + " " + "0.0");
        resultQ.add("\nItem name:" + " " + "shirt");
        resultQ.add("Quantity:" + " " + "1");
        resultQ.add("Profit Margin:" + " " + "0.0");
        assertEquals(resultP, testList.returnListP());
        assertEquals(resultQ, testList.returnListQ());
    }

    @Test
    public void makeAllAtOnce() {
        testList.makeList(stock1);
        testList.makeList(stock2);
        testList.makeAllList();
        ArrayList<String> resultP = new ArrayList<>();
        resultP.add("\nItem name:" + " " + "hat");
        resultP.add("Profit:" + " " + "0.0");
        resultP.add("Revenue:" + " " + "1.0");
        resultP.add("\nItem name:" + " " + "shirt");
        resultP.add("Profit:" + " " + "0.0");
        resultP.add("Revenue:" + " " + "1.0");
        ArrayList<String> resultQ = new ArrayList<>();
        resultQ.add("\nItem name:" + " " + "hat");
        resultQ.add("Quantity:" + " " + "1");
        resultQ.add("Profit Margin:" + " " + "0.0");
        resultQ.add("\nItem name:" + " " + "shirt");
        resultQ.add("Quantity:" + " " + "1");
        resultQ.add("Profit Margin:" + " " + "0.0");
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("\n" + "hat");
        nameList.add("\n" + "shirt");
        testList.setProfit(1000);
        testList.setProfit(0);
        testList.setProfit(10);
        testList.getProfit();
        assertEquals(resultP, testList.returnListP());
        assertEquals(resultQ, testList.returnListQ());
        assertEquals(nameList, testList.returnNameList());
        assertEquals(10, testList.getProfit());
    }

    @Test
    public void makeOneList() {
        testList.makeList(stock1);
        testList.makeAllList();
        testList.getProfit();
        ArrayList<String> resultQ = new ArrayList<>();
        resultQ.add("\nItem name:" + " " + "hat");
        resultQ.add("Quantity:" + " " + "1");
        resultQ.add("Profit Margin:" + " " + "0.0");
        ArrayList<String> resultP = new ArrayList<>();
        resultP.add("\nItem name:" + " " + "hat");
        resultP.add("Profit:" + " " + "0.0");
        resultP.add("Revenue:" + " " + "1.0");
        assertEquals(resultP, testList.returnListP());
        assertEquals(resultQ, testList.returnListQ());
        assertEquals(0, testList.getProfit());

    }
    @Test
    public void makeEmptyList() {
        testList.makeAllList();
        ArrayList<String> resultQ = new ArrayList<>();
        ArrayList<String> resultP = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        assertEquals(nameList, testList.returnNameList());
        assertEquals(resultP, testList.returnListP());
        assertEquals(resultQ, testList.returnListQ());
        assertEquals(0, testList.getProfit());

    }


    @Test
    public void addProfit() {
        testList.addProfit(1000);
        testList.addProfit(0);
        testList.addProfit(1);
        testList.addProfit(2);
        assertEquals(1003, testList.getProfit());
    }

    @Test
    public void numStock() {
        testList.addStock(stock1);
        testList.addStock(stock2);
        testList.addStock(stock3);
        assertEquals(3, testList.getNumOfStock());
    }

    @Test

    public void oneStock() {
        testList.addStock(stock1);
        assertEquals(1, testList.getNumOfStock());
    }


}
