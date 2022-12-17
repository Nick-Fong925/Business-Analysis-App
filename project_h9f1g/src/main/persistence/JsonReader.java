package persistence;

import model.Stock;
import model.StockList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads StockList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public StockList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStockList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses StockList from JSON object and returns it
    private StockList parseStockList(JSONObject jsonObject) {
        StockList stocklist = new StockList();
        addStockList(stocklist, jsonObject);

        Double totalP = jsonObject.getDouble("totalProfit");
        stocklist.setProfit(totalP);
        stocklist.makeAllList();
        return stocklist;

    }

    // MODIFIES: StockList
    // EFFECTS: parses stock from JSON object and adds them to StockList
    private void addStockList(StockList stocklist, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("StockList");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addStocks(stocklist, nextThingy);
        }
    }

    // MODIFIES: StockList
    // EFFECTS: parses stock from JSON object and adds it to StockList
    private void addStocks(StockList stocklist, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double buyP = jsonObject.getDouble("buyP");
        Double sellP = jsonObject.getDouble("sellP");
        Integer quantity = jsonObject.getInt("quantity");
        Double profit = jsonObject.getDouble("profit");
        Double margin = jsonObject.getDouble("margin");
        Double revenue = jsonObject.getDouble("revenue");

        Stock stock = new Stock(name, buyP, sellP, quantity, profit, margin, revenue);
        stocklist.addStock(stock);
    }
}
