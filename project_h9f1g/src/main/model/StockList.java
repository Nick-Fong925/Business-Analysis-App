package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class StockList {
    private ArrayList<Stock> listOfStock;
    private ArrayList<String> revAndProfit;
    private ArrayList<String> quanAndMarg;
    private ArrayList<String> nameList;
    private double totalProfit;

    public StockList() {
        listOfStock = new ArrayList<>();
        revAndProfit = new ArrayList<>();
        quanAndMarg = new ArrayList<>();
        nameList = new ArrayList<>();
        totalProfit = 0;
    }

    public void addStock(Stock stock) {
        listOfStock.add(stock);
        EventLog.getInstance()
                .logEvent(new Event(stock.getName() + " has been added to the list of stocks"));
    }

    //EFFECTS: creates all both revAndProfit and quanAndMarg lists from list
    //MODIFIES: revAndProfit and quanAndMarg
    public void makeAllList() {
        for (Stock s : listOfStock) {
            makeList(s);
        }
    }

    //MODIFIES: this
    //EFFECTS: transfers data types to string to be utilized in makeList functions
    private void makeVal(String name, int sellQ, double totalP, double margin, double rev) {
        String sellQString = String.valueOf(sellQ);
        String currentProfitString = String.valueOf(totalP);
        String sellMargString = String.valueOf(margin);
        String revString = String.valueOf(rev);

        makeListQ(name, sellQString, sellMargString);
        makeListP(name, currentProfitString, revString);
        makeNameList(name);

    }

    //EFFECTS: creates all both revAndProfit and quanAndMarg lists
    //MODIFIES: revAndProfit and quanAndMarg
    public void makeList(Stock stocks) {
        makeVal(stocks.getName(), stocks.getQuantity(),
                stocks.calcProfit(), stocks.calcMargin(), stocks.calcRev());
    }


    public void makeNameList(String name) {
        nameList.add("\n" + name);
    }
    //EFFECTS: make list of profit and revenue
    //MODIFIES: revAndProfit and quanAndMarg

    public void makeListP(String name, String profit, String revenue) {
        revAndProfit.add("\nItem name:" + " " + name);
        revAndProfit.add("Profit:" + " " + profit);
        revAndProfit.add("Revenue:" + " " + revenue);
    }

    //EFFECTS: make list of quantity and profit margin
    public void makeListQ(String name, String sellQ, String profitMargin) {
        quanAndMarg.add("\nItem name:" + " " + name);
        quanAndMarg.add("Quantity:" + " " + sellQ);
        quanAndMarg.add("Profit Margin:" + " " + profitMargin);
    }

    public ArrayList<String> returnListP() {
        EventLog.getInstance()
                .logEvent(new Event("Profit and Revenue Data has been generated"));
        return revAndProfit;
    }

    public ArrayList<String> returnListQ() {
        EventLog.getInstance()
                .logEvent(new Event("Quantity and Profit Margin Data has been Generated"));
        return quanAndMarg;
    }

    public ArrayList<String> returnNameList() {
        return nameList;
    }

    public void addProfit(double p) {
        totalProfit = totalProfit + p;
    }

    public double getProfit() {
        return totalProfit;
    }

    public void setProfit(double p) {
        totalProfit = p;
    }

    public int getNumOfStock() {
        return listOfStock.size();
    }


    //EFFECTS: Print EventLog based on actions on user
    public void closeSession() {
        printLog(EventLog.getInstance());
    }


    public void printLog(EventLog eventLog) {
        for (Event e: eventLog) {
            System.out.println(e.toString() + "\n");
        }
    }


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("StockList", listOfStockToJson());
        json.put("revAndProfit", revAndProfit);
        json.put("quanAndMarg", quanAndMarg);
        json.put("totalProfit", totalProfit);
        json.put("nameList", nameList);
        return json;
    }

    public JSONArray listOfStockToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Stock stock : listOfStock) {
            jsonArray.put(stock.toJson());
        }

        return jsonArray;
    }

}

