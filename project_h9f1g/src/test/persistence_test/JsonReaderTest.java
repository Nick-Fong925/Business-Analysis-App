package persistence_test;

import model.StockList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            StockList stocklist = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        ArrayList<String> result = new ArrayList<>();
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            StockList stocklist = reader.read();
            assertEquals(0, stocklist.getNumOfStock());
            assertEquals(0, stocklist.getProfit());
            assertEquals(result, stocklist.returnListP());
            assertEquals(result, stocklist.returnListQ());
            assertEquals(result, stocklist.returnNameList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
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
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            StockList stocklist = reader.read();
            assertEquals(1, stocklist.getNumOfStock());
            assertEquals(0, stocklist.getProfit());
            assertEquals(resultP, stocklist.returnListP());
            assertEquals(resultQ, stocklist.returnListQ());
            assertEquals(nameList, stocklist.returnNameList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderFilledWorkRoom() {
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
        JsonReader reader = new JsonReader("./data/testReaderFilledWorkRoom.json");
        try {
            StockList stocklist = reader.read();
            assertEquals(2, stocklist.getNumOfStock());
            assertEquals(0, stocklist.getProfit());
            assertEquals(resultP, stocklist.returnListP());
            assertEquals(resultQ, stocklist.returnListQ());
            assertEquals(nameList, stocklist.returnNameList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

