package tycoonBasic;

import javax.swing.*;
import java.lang.*;

public class Play extends JPanel {
    private Player player;
    private fileHandler gameData = new fileHandler();
    private String name;
    //Strings
    String askingForBusName = "What would you like to name your business?",
            askForNewGame = "Would you like to continue your business ventures?",
            answeredYes = "Are you sure you want to start a new game?",
            //y = "Y", yes = "YES", n = "N", no = "NO",
            welcomeBack = "Welcome back to ", bankCheck = "Cash in bank: $",
            //displayDate = "Date: ",
            titleForNewGame = "New Game?",
            createNewGame = "Create New Game", loadGame = "Load Game";

    Play() {
        openGame(); //loads or creates new game'
        player = new Player(gameData);
        name = player.getName();

        JOptionPane.showMessageDialog(null, welcomeBack + name +  "\n" +
                bankCheck + player.getCash() + "\n" +
                player.getDateSTR());

        saveGame(player);
    }

    /**
     * Saves current playthrough of the game
     * @param player
     */
    public void saveGame(Player player){
        gameData.saveData(player);
        gameData.closeFile();
    }

    /**
     * Loads up previous game
     * or
     * Creates a new game
     */
    public void openGame(){
        int cash = 5000;        //$5,000
        //check for saved data and ask to overwrite if it exists
        boolean makeNewFile = false;
        if(gameData.saveDataExists()){
            Object[] options = {createNewGame,
                    loadGame};
            int ans = JOptionPane.showOptionDialog(null,
                    askForNewGame,
                    titleForNewGame,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title

            //0 = yes, 1 = no
            if(ans == 0){
                ans = JOptionPane.showConfirmDialog(
                        null,
                        answeredYes,
                        titleForNewGame,
                        JOptionPane.YES_NO_OPTION);
             }

            if(ans == 0){
                makeNewFile = true;
                //answerIsClear = true;
            }
        }//if (checking game data)
         /*
         if game data does not exists, or the user wants to restart
         then we create a new game
         else, we load up previous game data.
         */
        if (!gameData.saveDataExists() || makeNewFile) {
            name = JOptionPane.showInputDialog(askingForBusName);
            gameData.createSaveData(name, cash);
        }else{ //saved data already exists and does not want to be overridden
            gameData.loadSaveData();
        }
    }

    public Player getPlayer(){
        return this.player;
    }


}
