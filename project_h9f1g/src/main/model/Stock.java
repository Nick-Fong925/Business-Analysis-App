package model;

import org.json.JSONObject;

//Represents an item having, a name, a purchase price > 0, selling price > 0,
//quantity sold > 0, profit , and profit margin
public class Stock {
    private String name;
    private double buyP;
    private double sellP;
    private int quantity;
    private double profit;
    private double margin;
    private double revenue;


    //REQUIRES: itemName has a non 0 length
    //EFFECTS: name of the item is set to itemName;
    //set buyP, sellP, quantity, and profit to 0


    public Stock(String itemName, double buyP, double sellP, int quantity,
                 double profit, double margin, double revenue) {
        this.name = itemName;
        this.buyP = buyP;
        this.sellP = sellP;
        this.quantity = quantity;
        this.profit = profit;
        this.margin = margin;
        this.revenue = revenue;
    }


    //MODIFIES: this
    //EFFECTS: changes buyP

    public void changeBuyP(double buyP) {
        this.buyP = buyP;
    }


    //MODIFIES: this
    //EFFECTS: changes sellP

    public void changeSellP(double sellP) {
        this.sellP = sellP;
    }


    //MODIFIES: this
    //EFFECTS: changes sellQ

    public void changeSellQ(int sellQ) {
        this.quantity = sellQ;
    }


    //EFFECTS: returns name

    public String getName() {
        return this.name;
    }

    //EFFECTS: returns buyP

    public double getBuyP() {
        return this.buyP;
    }


    //EFFECTS: returns sellP

    public double getSellP() {
        return this.sellP;
    }


    //EFFECTS: returns sellQ

    public int getQuantity() {
        return this.quantity;
    }


    //REQUIRES: quantity > 0, sellP > 0, buyP > 0
    //MODIFIES: this
    //EFFECTS: individual profit is calculated by (sellP - buyP) * quantity

    public double calcProfit() {
        this.profit = (this.sellP - this.buyP) * quantity;
        return this.profit;
    }


    //EFFECTS: returns profit

    public double getProfit() {
        return this.profit;
    }


    //REQUIRES: quantity > 0, sellP > 0, buyP > 0
    //MODIFIES: this
    //EFFECTS: individual profit margin is calculated by (1 - (buyP / sellP))

    public double calcMargin() {
        this.margin = (1 - (this.buyP / this.sellP));
        return this.margin;
    }


    //EFFECTS: returns margin

    public double getMargin() {
        return this.margin;
    }


    //REQUIRES: quantity > 0, sellP > 0, buyP > 0
    //MODIFIES: this
    //EFFECTS: individual revenue is calculated by sellP * quantity


    public double calcRev() {
        this.revenue = this.sellP * quantity;
        return this.revenue;
    }


    //EFFECTS: returns revenue

    public double getRevenue() {
        return this.revenue;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("buyP", buyP);
        json.put("sellP", sellP);
        json.put("quantity", quantity);
        json.put("profit", profit);
        json.put("margin", margin);
        json.put("revenue", revenue);
        return json;
    }
}


