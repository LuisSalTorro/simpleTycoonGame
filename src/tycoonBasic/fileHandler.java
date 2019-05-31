package tycoonBasic;
import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.io.File;

public class fileHandler {
    private String busName;
    private int cashInHand;
    private int[] date = {5, 28, 2019}; //month, day,  year
    private Formatter file;
    private Scanner fileReader;
    private int numOfEmployees, numOfDevs, numOfDesigners; //currently dev and designer.
    private int debt, interest;

    private String dataInputs =  "%s\n%d\n%d %d %d\n" +          // +    //name, money, month, day, year.
                                    //  "%d%d" +             //add loans and morale(0-100)
                                    "%d\n%d %d" + //number of employees and then devs and designers
                                    "\n%d %d"; //debt and interest
                                    //  "%d%d%d%d";         // Add more stuff, like numbers of workers in certain positions (secretary, dev, accoutant, HR, and so on.)
    //secretary boosts daily earnings by a small fraction.
    //dev brings in more earnings, period
    //accountant makes
    //HR boosts morale.
    //High morale generates 20% more cash, while low morale decreases earnings by 20%

    //Global Strings
    private String fileName = "saveData.txt";

    //errors
    private String errorSavingGame = "There was an error saving data.",
                   errorLoadingGame = "Could not properly load saved data.";

    public fileHandler(){ }

    /**
     * Saves current data of playthrough
     * @param player
     */
    public void saveData(Player player){
        try{
            this.file = new Formatter(this.fileName);
            this.file.format(dataInputs, player.getName(), player.getCash(), //adds name and money into save data.
                    player.getDate()[0], player.getDate()[1],player.getDate()[2], //adds date
                    player.numOfEmployees, player.numOfDevs, player.numOfDesigners,
                    player.getDebt(), player.getInterest()); //number of employees by each position.
        }catch(Exception e){
           // System.out.println(this.errorSavingGame);
            JOptionPane.showMessageDialog(null, this.errorSavingGame);
        }
    }

    /**
     * Creates a new save data
     * @param busName
     * @param cashInHand
     */
    public void createSaveData(String busName, int cashInHand){
        this.busName = busName;
        this.cashInHand = cashInHand;
        try{
            this.file = new Formatter(this.fileName);
            this.file.format(dataInputs, this.busName, this.cashInHand,
                                this.date[0], this.date[1],this.date[2], //adds name and money into save data.
                                this.numOfEmployees, this.numOfDevs, this.numOfDesigners,
                                this.debt, this.interest);   //adds number of employees split by position
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, this.errorSavingGame);
        }
    }

    public void openData(String problem){
        try{
            this.fileReader = new Scanner(new File(this.fileName));
        }catch(Exception e){
            System.out.print(problem);
        }
    }

    /**
     * Loads data from saved data
     */

    public void loadSaveData(){
        openData(this.errorLoadingGame+"\n");
        while(fileReader.hasNext()){
            //this.busName = fileReader.next(); //reads the first thing before whitespace
            this.busName = fileReader.nextLine(); //reads the full line as String
            this.cashInHand = fileReader.nextInt();
            //dates
            this.date[0] = fileReader.nextInt();
            this.date[1] = fileReader.nextInt();
            this.date[2] = fileReader.nextInt();

            this.numOfEmployees = fileReader.nextInt();

            this.numOfDevs = fileReader.nextInt();
            this.numOfDesigners = fileReader.nextInt();

            this.debt = fileReader.nextInt();
            this.interest = fileReader.nextInt();

            //add morale
        }
    }

    //checks to see if a save data exists
    public boolean saveDataExists(){
        File f = new File(fileName);
        if(f.exists()) return true;
        else return false;
    }

    public String getBusName(){
        return this.busName;
    }

    public int getCash(){
        return this.cashInHand;
    }

    public int[] getDate(){
        return this.date;
    }

    public int getNumOfDevs(){
        return this.numOfDevs;
    }

    public int getNumOfDesigners(){
        return this.numOfDesigners;
    }

    public int getNumOfEmployees(){
        return this.numOfEmployees;
    }

    public int getDebt(){
        return this.debt;
    }
    public int getInterest(){
        return this.interest;
    }

    public void closeFile(){
        try{
            file.close();
        }
        catch(Exception e){
            //when the file is not opened, throws error.
        }
    }

}