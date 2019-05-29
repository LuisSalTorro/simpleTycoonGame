package tycoonBasic;
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

    //Global Strings
    private String fileName = "saveData.txt";
    private String problemCreatingFile = "There was an error saving data.";
    private String problemLoadingData = "Could not properly load saved data.";

    public fileHandler(){

    }

    public void saveData(Player player){
        try{
            this.file = new Formatter(this.fileName);
            this.file.format("%s\n%d %d %d %d", player.getName(), player.getCash(), player.getDate()[0], player.getDate()[1],player.getDate()[2]); //adds name and money into save data.
        }catch(Exception e){
            System.out.println(this.problemCreatingFile);
        }
    }

    public void createSaveData(String busName, int cashInHand){
        this.busName = busName;
        this.cashInHand = cashInHand;
        try{
            this.file = new Formatter(this.fileName);
            this.file.format("%s\n%d %d %d %d", this.busName, this.cashInHand, this.date[0], this.date[1],this.date[2]); //adds name and money into save data.
        }catch(Exception e){
            System.out.println(this.problemCreatingFile);
        }
    }

    public void openData(String problem){
        try{
            this.fileReader = new Scanner(new File(this.fileName));
        }catch(Exception e){
            System.out.print(problem);
        }
    }

    public void loadSaveData(){
        openData(this.problemLoadingData+"\n");
        while(fileReader.hasNext()){
            //this.busName = fileReader.next(); //reads the first thing before whitespace
            this.busName = fileReader.nextLine(); //reads the full line as String
            this.cashInHand = fileReader.nextInt();
            this.date[0] = fileReader.nextInt();
            this.date[1] = fileReader.nextInt();
            this.date[2] = fileReader.nextInt();
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

    public void closeFile(){
        try{
            file.close();
        }
        catch(Exception e){
            //when the file is not opened, throws error.
        }
    }

}
