package ui;

import model.EventLog;
import model.Stock;
import model.StockList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class BusinessAnalysisAppGUI extends JPanel implements ActionListener {

    StockList stockList;
    Stock item;
    private ArrayList<String> nameList;
    private JFrame frame;
    private JFrame loadFrame;
    private JFrame saveFrame;

    private JPanel panel;

    private JLabel itemNameLabel;
    private JLabel itemBuyP;
    private JLabel itemSellP;
    private JLabel itemSellQ;
    private JLabel totalProfitText;
    private JLabel quanRevListLabel;
    private JLabel profitMargListLabel;
    private JLabel itemListNames;
    private JLabel loadData;
    private JLabel pictureBG;

    private JTextField nameText;
    private JTextField itemBuyPText;
    private JTextField itemSellPText;
    private JTextField itemSellQText;

    private JButton addItemButton;
    private JButton profitButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton createListP;
    private JButton createListQ;
    private JButton closeButton;
    private JButton yesButton;
    private JButton noButton;

    private JTextArea profitMargListText;
    private JTextArea quanRevListText;
    private JTextArea stocksAdded;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workroom.json";

    //EFFECTS: initializes BusinessAppGUI

    public BusinessAnalysisAppGUI() {

        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(1800, 1800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        ImageIcon img = new ImageIcon("./data/RichMan.jpg");
        pictureBG = new JLabel("", img, JLabel.CENTER);
        pictureBG.setBounds(230, 500, 250, 250);
        panel.add(pictureBG);

        initLabels();
        initText();
        initButtons();
        initTextArea();
        frame.setVisible(true);
        createLoadFrame();
        createSaveFrame();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        stockList = new StockList();
        nameList = new ArrayList<>();


    }

    //MODIFIES: Labels and Panel
    //EFFECTS: Initializes all labels to the panel

    public void initLabels() {

        initListLabels();

        totalProfitText = new JLabel("Total Profit: 0");
        totalProfitText.setBounds(10, 420, 300, 25);
        panel.add(totalProfitText);

        itemSellQ = new JLabel("Sale Quantity: ");
        itemSellQ.setBounds(10, 110, 150, 25);
        panel.add(itemSellQ);

        itemSellP = new JLabel("Selling Price: ");
        itemSellP.setBounds(10, 80, 150, 25);
        panel.add(itemSellP);

        itemBuyP = new JLabel("Acquisition Cost: ");
        itemBuyP.setBounds(10, 50, 150, 25);
        panel.add(itemBuyP);

        itemNameLabel = new JLabel("Item name: ");
        itemNameLabel.setBounds(10, 20, 80, 25);
        panel.add(itemNameLabel);

        itemListNames = new JLabel("Item List");
        itemListNames.setBounds(10, 460, 100, 25);
        panel.add(itemListNames);

    }

    public void initListLabels() {
        profitMargListLabel = new JLabel("Profit and Revenue for each item");
        profitMargListLabel.setBounds(1000, 10, 300, 25);
        panel.add(profitMargListLabel);

        quanRevListLabel = new JLabel("Sell Quantity and Profit Margin for each item");
        quanRevListLabel.setBounds(500, 10, 300, 25);
        panel.add(quanRevListLabel);
    }

    //MODIFIES: TextFeilds and Panel
    //EFFECTS: Initializes all TextFields to the panel

    public void initText() {
        nameText = new JTextField(30);
        nameText.setBounds(100, 20, 165, 25);
        panel.add(nameText);

        itemBuyPText = new JTextField(30);
        itemBuyPText.setBounds(130, 50, 200, 25);
        panel.add(itemBuyPText);

        itemSellPText = new JTextField(30);
        itemSellPText.setBounds(110, 80, 200, 25);
        panel.add(itemSellPText);

        itemSellQText = new JTextField(30);
        itemSellQText.setBounds(110, 110, 200, 25);
        panel.add(itemSellQText);

    }


    //MODIFIES: Buttons and Panels
    //EFFECTS: Initializes all Buttons to the panel

    public void initButtons() {

        initListButtons();
        initLoadSaveButtons();

        addItemButton = new JButton("Add Stock");
        addItemButton.setBounds(10, 140, 300, 25);
        addItemButton.setActionCommand("AddStockButton");
        addItemButton.addActionListener(this);
        panel.add(addItemButton);


        profitButton = new JButton("Calculate Total Profit");
        profitButton.setBounds(10, 180, 300, 25);
        profitButton.setActionCommand("Calculate Total Profit");
        profitButton.addActionListener(this);
        panel.add(profitButton);

        closeButton = new JButton("Close Session");
        closeButton.setBounds(10, 380, 300, 25);
        closeButton.setActionCommand("Close");
        closeButton.addActionListener(this);
        panel.add(closeButton);

    }

    public void initListButtons() {
        createListP = new JButton("Profit and Revenue for individual items");
        createListP.setBounds(10, 220, 450, 25);
        createListP.setActionCommand("CreateListP");
        createListP.addActionListener(this);
        panel.add(createListP);

        createListQ = new JButton("Sell Quantity and Profit Margin for individual items");
        createListQ.setBounds(10, 260, 450, 25);
        createListQ.setActionCommand("CreateListQ");
        createListQ.addActionListener(this);
        panel.add(createListQ);
    }

    public void initLoadSaveButtons() {

        saveButton = new JButton("Save item entries");
        saveButton.setBounds(10, 300, 300, 25);
        saveButton.setActionCommand("SaveState");
        saveButton.addActionListener(this);
        panel.add(saveButton);

        loadButton = new JButton("Load item entries");
        loadButton.setBounds(10, 340, 300, 25);
        loadButton.setActionCommand("LoadState");
        loadButton.addActionListener(this);
        panel.add(loadButton);

    }
    //MODIFIES: TextArea and Panel
    //EFFECTS: Initializes all TextArea to the panel

    public void initTextArea() {

        quanRevListText = new JTextArea("");
        quanRevListText.setBounds(500, 50, 400, 600);
        panel.add(quanRevListText);

        profitMargListText = new JTextArea("");
        profitMargListText.setBounds(1000, 50, 400, 600);
        panel.add(profitMargListText);

        stocksAdded = new JTextArea("");
        stocksAdded.setBounds(10, 500, 200, 400);
        panel.add(stocksAdded);

    }

    //MODIFIES: Frame, Button, Panel
    //EFFECTS: Initializes the Load Item Frame
    public void createLoadFrame() {
        loadFrame = new JFrame();
        loadFrame.setLayout(null);
        loadFrame.setSize(220, 150);
        loadFrame.setLocationRelativeTo(null);

        loadData = new JLabel(" Load Saved Data");
        loadData.setBounds(50, 30, 200, 25);
        loadFrame.add(loadData);
        loadFrame.setVisible(true);

        yesButton = new JButton("Yes");
        yesButton.setBounds(10, 60, 100, 25);
        yesButton.setActionCommand("YesLoad");
        yesButton.addActionListener(this);
        loadFrame.add(yesButton);

        noButton = new JButton("No");
        noButton.setBounds(100, 60, 100, 25);
        noButton.setActionCommand("NoLoad");
        noButton.addActionListener(this);
        loadFrame.add(noButton);
    }


    //MODIFIES: Frame, Button, Panel
    //EFFECTS: Initializes the Save Item Frame

    public void createSaveFrame() {
        saveFrame = new JFrame();
        saveFrame.setLayout(null);
        saveFrame.setSize(220, 150);
        saveFrame.setLocationRelativeTo(null);

        loadData = new JLabel("Save Data");
        loadData.setBounds(80, 30, 200, 25);
        saveFrame.add(loadData);
        saveFrame.setVisible(false);

        yesButton = new JButton("Yes");
        yesButton.setBounds(10, 60, 100, 25);
        yesButton.setActionCommand("YesSave");
        yesButton.addActionListener(this);
        saveFrame.add(yesButton);

        noButton = new JButton("No");
        noButton.setBounds(100, 60, 100, 25);
        noButton.setActionCommand("NoSave");
        noButton.addActionListener(this);
        saveFrame.add(noButton);
    }


    //EFFECTS: processes user inputs
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("AddStockButton")) {
            addStockInput();
        } else if (e.getActionCommand().equals("Calculate Total Profit")) {
            totalProfitText.setText("Total Profit: " + stockList.getProfit());
        } else if (e.getActionCommand().equals("SaveState")) {
            saveItemEntries();
        } else if (e.getActionCommand().equals("LoadState")) {
            loadState();
        } else if (e.getActionCommand().equals("CreateListP")) {
            showR();
        } else if (e.getActionCommand().equals("CreateListQ")) {
            showQ();
        } else if (e.getActionCommand().equals("Close")) {
            saveFrame.show();
        } else if (e.getActionCommand().equals("YesLoad")) {
            loadStateFrame();
        } else if (e.getActionCommand().equals("NoLoad")) {
            loadFrame.hide();
        } else if (e.getActionCommand().equals("YesSave")) {
            saveEntries();
        } else if (e.getActionCommand().equals("NoSave")) {
            closeProgram();
        }
    }

    private void closeProgram() {
        stockList.closeSession();
        System.exit(0);
    }

    //EFFECTS: closes system and saves current data
    private void saveEntries() {
        saveItemEntries();
        stockList.closeSession();
        System.exit(0);
    }

    //MODIFIES: nameText, itemBuyPText, itemSellPText, itemSellQText
    //EFFECTS: changes text to empty string and creates new item
    private void addStockInput() {
        setFieldsFromText();
        nameText.setText("");
        itemBuyPText.setText("");
        itemSellPText.setText("");
        itemSellQText.setText("");
        showNameList();
    }

    //EFFECTS: loads saved data to the JFrame
    private void loadStateFrame() {
        loadItemEntries();
        showQ();
        showR();
        showNameList();
        totalProfitText.setText("Total Profit: " + stockList.getProfit());
        loadFrame.hide();
    }

    //EFFECTS: loads saved data to the JFrame
    private void loadState() {
        loadItemEntries();
        showQ();
        showR();
        showNameList();
        totalProfitText.setText("Total Profit: " + stockList.getProfit());
    }

    //MODIFIES: the item field
    //EFFECTS:  create a new item based on user input

    public void setFieldsFromText() {

        String stockname = nameText.getText();
        item = new Stock(stockname, 0, 0, 0, 0, 0, 0);
        Double buyP = Double.parseDouble(itemBuyPText.getText());
        Double sellP = Double.parseDouble(itemSellPText.getText());
        Integer sellQ = Integer.parseInt(itemSellQText.getText());
        item.changeBuyP(buyP);
        item.changeSellP(sellP);
        item.changeSellQ(sellQ);
        double currentProfit = item.calcProfit();
        stockList.addProfit(currentProfit);
        stockList.addStock(item);
        stockList.makeList(item);
    }

    //EFFECTS: if no item has been entered there will be an error message
    // creates the list for Quantity and Profit Margin for each item

    private void showQ() {
        ArrayList<String> listOfStock = stockList.returnListQ();
        String formatQuanMarg = listOfStock.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        quanRevListText.setText(formatQuanMarg);

    }

    //EFFECTS: if no item has been entered there will be an error message
    // creates the list for Profit and Revenue for each item

    private void showR() {
        ArrayList<String> listOfStock = stockList.returnListP();
        String formatRevProfit = listOfStock.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        profitMargListText.setText(formatRevProfit);

    }

    //EFFECTS: if no item has been entered there will be an error message
    // creates the list for Names for each item

    private void showNameList() {
        ArrayList<String> nameList1 = stockList.returnNameList();
        String formatNameList = nameList1.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        String commaRemoved = formatNameList.replace(",", " ");
        stocksAdded.setText(commaRemoved);
    }


    // MODIFIES: JSON Save files
    // EFFECTS: saves current state of GUI

    private void saveItemEntries() {
        try {
            jsonWriter.open();
            jsonWriter.write(stockList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println();

        }
    }

    // MODIFIES: JSON Save files
    // EFFECTS: loads current state of GUI

    private void loadItemEntries() {
        try {
            stockList = jsonReader.read();
        } catch (IOException e) {
            System.out.println();
        }
    }

}











