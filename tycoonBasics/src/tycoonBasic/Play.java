package tycoonBasic;

import java.util.Scanner;
import java.lang.*;

public class Play {
    private Player player;
    private fileHandler gameData = new fileHandler();
    private String name;
    //Strings
    String askingForBusName = "What would you like to name your business?";
    String askForNewGame = "Do you want to create a new game? (y/n)";
    String useYorN = "Type y or n,  please.", y = "Y", yes = "YES", n = "N", no = "NO";
    String welcomeBack = "Welcome back to ", bankCheck = "Cash in bank: $";


    Play() {
        intro();
        player = new Player(gameData);
        name = player.getName();
        System.out.println(welcomeBack + name);
        System.out.println(bankCheck + player.getCash());

        gameData.saveData(player);
        gameData.closeFile();
    }

    public void intro(){
        int cash = 5000;//$5,000
        //check for saved data and ask to overwrite if it exists
        boolean makeNewFile = false;
        if(gameData.saveDataExists()){
            System.out.println(askForNewGame);
            Scanner key;
            String overWriteGame;
            boolean answer = false;
            while(!answer){
                key = new Scanner(System.in);
                overWriteGame = key.nextLine();
                if(overWriteGame.toUpperCase().equals(this.y) || overWriteGame.toUpperCase().equals(this.yes)){
                    makeNewFile = true;
                    answer = true;
                }else if(overWriteGame.toUpperCase().equals(this.n) || overWriteGame.toUpperCase().equals(this.no)){
                    answer = true;
                }else{
                    System.out.println(useYorN);
                    answer = false;
                }
            }

        }
        if (!gameData.saveDataExists() || makeNewFile) {
            System.out.println(askingForBusName);
            Scanner key = new Scanner(System.in);
            name = key.nextLine();
            gameData.createSaveData(name, cash);
        }else{ //saved data already exists and does not want to be overridden
            gameData.loadSaveData();
        }
    }

}
