package persistence_test;

import model.Stock;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.StockList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            StockList stocklist = new StockList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            StockList stocklist = new StockList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(stocklist);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            stocklist = reader.read();
            assertEquals(0, stocklist.getNumOfStock());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        ArrayList<String> resultQ = new ArrayList<>();
        resultQ.add("\nItem name:" + " " + "hat");
        resultQ.add("Quantity:" + " " + "1");
        resultQ.add("Profit Margin:" + " " + "0.0");
        ArrayList<String> resultP = new ArrayList<>();
        resultP.add("\nItem name:" + " " + "hat");
        resultP.add("Profit:" + " " + "0.0");
        resultP.add("Revenue:" + " " + "1.0");
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("\n" + "hat");
        Stock stock1 = new Stock("hat",1,1,1,0,0,1);
        try {
            StockList stocklist = new StockList();
            stocklist.addStock(stock1);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(stocklist);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            stocklist = reader.read();
            assertEquals(1, stocklist.getNumOfStock());
            assertEquals(0, stocklist.getProfit());
            assertEquals(resultP, stocklist.returnListP());
            assertEquals(resultQ, stocklist.returnListQ());
            assertEquals(nameList, stocklist.returnNameList());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterFilledWorkroom() {
        ArrayList<String> resultQ = new ArrayList<>();
        resultQ.add("\nItem name:" + " " + "hat");
        resultQ.add("Quantity:" + " " + "1");
        resultQ.add("Profit Margin:" + " " + "0.0");
        resultQ.add("\nItem name:" + " " + "shirt");
        resultQ.add("Quantity:" + " " + "1");
        resultQ.add("Profit Margin:" + " " + "0.0");
        ArrayList<String> resultP = new ArrayList<>();
        resultP.add("\nItem name:" + " " + "hat");
        resultP.add("Profit:" + " " + "0.0");
        resultP.add("Revenue:" + " " + "1.0");
        resultP.add("\nItem name:" + " " + "shirt");
        resultP.add("Profit:" + " " + "0.0");
        resultP.add("Revenue:" + " " + "1.0");
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("\n" + "hat");
        nameList.add("\n" + "shirt");
        Stock stock1 = new Stock("hat",1,1,1,0,0,1);
        Stock stock2 = new Stock("shirt",1,1,1,0,0,1);
        try {
            StockList stocklist = new StockList();
            stocklist.addStock(stock1);
            stocklist.addStock(stock2);
            JsonWriter writer = new JsonWriter("./data/testWriterFilledWorkroom.json");
            writer.open();
            writer.write(stocklist);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterFilledWorkroom.json");
            stocklist = reader.read();
            assertEquals(2, stocklist.getNumOfStock());
            assertEquals(0, stocklist.getProfit());
            assertEquals(resultP, stocklist.returnListP());
            assertEquals(resultQ, stocklist.returnListQ());
            assertEquals(nameList, stocklist.returnNameList());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
