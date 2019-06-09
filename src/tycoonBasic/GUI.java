package tycoonBasic;
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GUI extends JFrame{

    private JPanel panel, panel2, panel3, panel4, panel5, panel6, panel7, panel8;
    private final int WIN_WIDTH = 450, WIN_HEIGHT = 450;
    private JButton hireButton, rentLocation, bankButton, saveButton, nextWeekButton, moraleBoosterButton;

    final String moraleCheck = "Total Morale: ",
            workersCheck = "Employees: ",
            DEVELOPER = "Developer",
            DESIGNER = "Designer",
            dev = "developer", des = "designer", //lowercase versions
            hire = "Hire more employees",
            rent = "Rent new location",
            bank = "Go to the bank"; //filler
    Play play;

    public GUI(){

         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         play = new Play();
         Player player = play.getPlayer();
         setLayout(new GridLayout(4,2));
         setTitle(player.getName());

         buildPanel(player);//goes to the method that creates the panel

         add(panel);
         add(panel2);
         add(panel3);
         add(panel4);
         add(panel5);
         add(panel6);
         add(panel7);
         add(panel8);
         setSize(WIN_WIDTH,WIN_HEIGHT);
         setVisible(true);
    }

    /**
     * creates the panel
     */
    String employeeNumStr = "You have ", employeeNumStr2 = " employees.";
    String cashGeneratingPerDay = "You're generating $",
            cashGeneratingPerDay2 = " per day.",
            cashInWallet = "Cash in wallet: $",
            saveThisGame = "Do you want to save your game?",
            saveGameSTR = "Save Game",
            amountOfLoansSTR ="Amount of loans: $",
            devsNumSTR = "Developers hired: ",
            designNumSTR = "Designers hired: ",
            nextWeek = "Next Week",
            boostMoraleSTR = "Boost Morale";
    Player player;

    private JLabel cashLabel, workers, developerNumberLabel, designerNumberLabel,
                    totalLoans,imgLabel,
                    weekLabel, monthLabel, yearLabel,
                    repLabel, moraleLabel;

    //private ImageIcon img;

    public void buildPanel(Player player) {
        this.player = player;
        moraleLabel = new JLabel(moraleCheck + player.getMorale() + " (Out of 100)");

        weekLabel = new JLabel(player.getWeekSTR());
        monthLabel = new JLabel(player.getMonthSTR());
        yearLabel = new JLabel(player.getYearSTR());

        repLabel = new JLabel("Reputation: "+player.getReputation());

        workers = new JLabel(workersCheck + player.numOfEmployees);
        String cashSTR = Integer.toString(this.player.getCash());
        cashLabel = new JLabel(cashInWallet + cashSTR);
        developerNumberLabel = new JLabel(devsNumSTR + player.getNumOfDevs());
        designerNumberLabel = new JLabel(designNumSTR  + player.getNumOfDesigners());
        JLabel saveGameLabel = new JLabel(saveThisGame);

        JLabel buyWorkers = new JLabel(hire);

        totalLoans = new JLabel(amountOfLoansSTR + player.getDebt());
        buyWorkers.setHorizontalAlignment(SwingConstants.CENTER);

        hireButton = new JButton(hire);
        hireButton.addActionListener(new PayButton());

        rentLocation = new JButton(rent);
        rentLocation.addActionListener(new PayButton());

        bankButton = new JButton(bank);
        bankButton.addActionListener(new PayButton());

        moraleBoosterButton = new JButton(boostMoraleSTR);
        moraleBoosterButton.addActionListener(new PayButton());

        saveButton = new JButton(saveGameSTR);
        saveButton.addActionListener(new PayButton());

        nextWeekButton= new JButton(nextWeek);
        nextWeekButton.addActionListener(new PayButton());

        //add the images
        imgLabel = new JLabel(addImg(player.getRentTier()));

        panel = new JPanel(new GridLayout(4,0));
        panel2 = new JPanel(); //bank
        panel3 = new JPanel(); //location
        panel4 = new JPanel(); //employees
        panel5 = new JPanel();
        panel6 = new JPanel(); //
        panel7 = new JPanel(new GridLayout(4,0)); //will have the location picture
        panel8 = new JPanel(); //be the new save option

        panel.add(cashLabel);
        panel.add(workers);
        panel.add(repLabel);

        panel2.add(totalLoans);
        panel2.add(bankButton);

        panel3.add(imgLabel);
        panel5.add(rentLocation); //Currently in a new panel

        panel4.add(developerNumberLabel);
        panel4.add(designerNumberLabel);
        panel4.add(hireButton);

        panel6.add(moraleLabel);
        panel6.add(moraleBoosterButton);

        panel7.add(yearLabel);
        panel7.add(monthLabel);
        panel7.add(weekLabel);
        panel7.add(nextWeekButton);

        panel8.add(saveGameLabel);
        panel8.add(saveButton);
    }


    String devHire = DEVELOPER + " for $2,000", designHire =  DESIGNER +" for $1,000", cancel = "Cancel", interview = "Which will you hire?";
    String hired = "You have hired a ";
    String insuffecientFunds = "You do not have enough funds. Try taking a loan from the bank.";


    String buyPropertySTR = "Do you want to buy property?",
            gameSavedConfirmation = "Your game has been saved.";

    //bank method
    String takeLoan = "Take out a loan",
            payLoan = "Pay a loan",
            bankGreet = "How can we help you today?",
            bankTitle = "Bank",
            howMuchForLoan = "How much do you want to pull out for a loan?",
            loanAmountSTR1 = "You have $", loanAmountSTR2 = " in loans",
            excessivePay = "Make sure to only pay off your loans and no more.",
            noLoansToPay = "You have no loans to pay.",
            negativeNumInput = "Don't pay the bank extra!",
            illegalArgError = "Please type in numbers only.";

    private class PayButton implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == hireButton){
                if(checkForSpace()){
                    interviewing();
                }
            }
            if(event.getSource() == rentLocation){
                realEstate();
            }
            if(event.getSource() == bankButton){
                atBank();
            }
            if(event.getSource() == saveButton){
                play.saveGame(player);
                JOptionPane.showMessageDialog(null, gameSavedConfirmation);
            }
            if(event.getSource() == nextWeekButton){
                newWeek();
            }
            if(event.getSource() == moraleBoosterButton){
                moralePress();
            }
        }

        public void moralePress(){
            //maybe put into arrays and then randomly pull one out from each String array
            String[] upMorale = {"Give more breaks.", "Install modern bathrooms.", "Install a new common room.", "Create party committee.", "Install private parking lot.", "Buy new keyboards."},
                    lowerMorale = {"Initiate tight deadlines.", "Mandatory Overtime.", "Block distracting websites.", "Daily Meetings."}; //lowers morale but gives short term bonuses like tons of cash flow
            Random rand = new Random();

            Object[] options = {upMorale[rand.nextInt((upMorale.length - 0))], //options
                            lowerMorale[rand.nextInt((lowerMorale.length - 0))],//options
                            cancel};
            int ans = JOptionPane.showOptionDialog(null,
                    "How would you like to proceed?", //screen
                    "Morale",//title
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
            switch(ans){
                case 0:
                    if(player.getMorale() >= 100){
                        JOptionPane.showMessageDialog(null, "Morale can't go higher.");

                    }else{
                        JOptionPane.showMessageDialog(null, "Morale increased by 10.\n$10,000 spent.");
                        player.changeMorale(10);
                        player.payment(10000);
                        cashLabel.setText(cashInWallet + player.getCash());
                        moraleLabel.setText(moraleCheck + player.getMorale() + " (Out of 100)");
                    }
                    break;
                case 1:
                    if(player.getMorale() <= 10){
                        JOptionPane.showMessageDialog(null, "Morale can't go lower.");
                    }else{
                        JOptionPane.showMessageDialog(null, "Morale decreased by 10. \n$10,000 earned.");
                        player.changeMorale(-10);
                        player.addCash(10000);
                        cashLabel.setText(cashInWallet + player.getCash());
                        moraleLabel.setText(moraleCheck + player.getMorale() + " (Out of 100)");
                        player.changeReputation(1);
                        repLabel.setText("Reputation: "+ player.getReputation());
                    }
                    break;
            }
        }

        public void interviewing(){
            Object[] options = {devHire,
                    designHire,
                    cancel};
            int ans = JOptionPane.showOptionDialog(null,
                    interview,
                    hire,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
            switch (ans){
                case 0:
                    devHire();
                    break;
                case 1:
                    designHire();
                    break;
            }
        }

        public boolean checkForSpace(){
            /*
                the player can only hire a certain number of employees depending on their office
                first office: 5 total
                second office: 9 total
                third office: 16 total
                fourth office: 35 total
                 */
            if(player.getRentTier() == 1 && player.getNumOfEmployees() >= 5){
                JOptionPane.showMessageDialog(null,"You cannot hire more employees until you upgrade offices.");
                return false;
            }
            if(player.getRentTier() == 2 && player.getNumOfEmployees() >= 9){
                JOptionPane.showMessageDialog(null,"You cannot hire more employees until you upgrade offices.");
                return false;
            }
            if(player.getRentTier() == 3 && player.getNumOfEmployees() >= 16){
                JOptionPane.showMessageDialog(null,"You cannot hire more employees until you upgrade offices.");
                return false;
            }
            if(player.getRentTier() == 4 && player.getNumOfEmployees() >= 35){
                JOptionPane.showMessageDialog(null,"You cannot hire more employees until you upgrade offices.");
                return false;
            }
            return true;
        }

        public void devHire(){
            Developer developer = new Developer("Rick", 2000, 0);
            if(player.getCash() - developer.getSalary() < 0){
                JOptionPane.showMessageDialog(null, insuffecientFunds);
            }else{
                employeeCheck(developer);
            }
        }

        public void designHire(){
            Designer designer = new Designer("Rick", 1000, 0);
            if(player.getCash() - designer.getSalary() < 0){
                JOptionPane.showMessageDialog(null, insuffecientFunds);
            }else{
                employeeCheck(designer);
            }
        }

        public void employeeCheck(Employee employee){
            player.payment(employee.getSalary());

            JOptionPane.showMessageDialog(null,hired+employee.getPosition());
            String afterPay = Integer.toString(player.getCash());
            cashLabel.setText(cashInWallet + afterPay);
            player.addEmployees();
            if(employee.getPosition().equals(DEVELOPER)){
                player.addDev();
                developerNumberLabel.setText(devsNumSTR + player.getNumOfDevs());
            }else if(employee.getPosition().equals(DESIGNER)){
                player.addDesigner();
                designerNumberLabel.setText(designNumSTR  + player.getNumOfDesigners());
            }
            workers.setText(workersCheck + player.getNumOfEmployees());
        }


        public void atBank(){
            Object[] options = {payLoan,
                    takeLoan,
                    cancel};
            int ans = JOptionPane.showOptionDialog(null,
                    bankGreet,
                    bankTitle,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);
            if(ans == 0 && player.getDebt() != 0){ //Pay off a loan
                //pay off loan
                int loanPayment;
                while(true){
                    try{
                        loanPayment  = Integer.valueOf(JOptionPane.showInputDialog(loanAmountSTR1 + player.getDebt() + loanAmountSTR2, 1000));
                        if(player.debt - loanPayment < 0 || loanPayment < 0 || (player.getCash() - loanPayment) < 0){
                            JOptionPane.showMessageDialog(null, excessivePay);
                            continue;
                        }
                        else{
                            break;
                        }
                    }catch(IllegalArgumentException e){
                        JOptionPane.showMessageDialog(null, illegalArgError);
                    }
                }

                player.loanPayment(loanPayment);
                String afterPay = Integer.toString(player.getCash());
                cashLabel.setText(cashInWallet + afterPay);

            }else if(ans == 0 && player.getDebt() == 0){
                JOptionPane.showMessageDialog(null, noLoansToPay);
            }
            if(ans == 1){
                //have the amount to pull based on how much money you're generating
                int loan;
                while(true){
                    try{
                        loan = Integer.valueOf(JOptionPane.showInputDialog(howMuchForLoan, 1000));
                        if(loan < 0){
                            JOptionPane.showMessageDialog(null,negativeNumInput);
                            continue;
                        }

                        break;
                    }catch(IllegalArgumentException e){
                        JOptionPane.showMessageDialog(null, illegalArgError);
                    }
                }
                player.addCash(loan);
                player.addLoan(loan);
                String afterPay = Integer.toString(player.getCash());
                cashLabel.setText(cashInWallet + afterPay);

            }

            totalLoans.setText(amountOfLoansSTR + player.getDebt());
        }

    }//bank method

    String realEstateGreet = "Would you like to upgrade your office for $",
            monthlyRent = "With a monthly rent of $", //rent = downpayment/5
            rentTitle = "Real Estate Agency";
    int[] downPayment = {5000, 20000, 100000};  //5K, 20K, and 100K
    int constantRent = 5; //so I don't lose it later

    public void realEstate(){

        if(player.getRentTier() >= 4){
            JOptionPane.showMessageDialog(null, "You have bought the best property you can afford!");
            return;
        }else{
            int rentTier = player.getRentTier();
            int setPayment = downPayment[rentTier-1];
            int rent = setPayment/constantRent;
            Object[] options = {"Yes","Come back later"};
            int ans = JOptionPane.showOptionDialog(null,
                    realEstateGreet + setPayment + "?\n" +
                            monthlyRent + rent + ".",
                    rentTitle,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if(ans == 0){  //yes
                if(player.getCash() - setPayment >= 0){
                    player.payment(setPayment);
                    player.setMonthlyRent(rent);
                    cashLabel.setText(cashInWallet + player.getCash());
                    player.incrementRentTier();
                    imgLabel.setIcon(addImg(player.getRentTier()));
                    return;
                }
            }
            JOptionPane.showMessageDialog(null,"Save up some more money.");

        }

    }

    String location = "location",
            jpgFile = ".jpg";
    int width = WIN_WIDTH/3,
        height = WIN_HEIGHT/4;
    public ImageIcon addImg(int rentTier){
        ImageIcon icon = new ImageIcon(location + rentTier + jpgFile);
        Image scaleImage = icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT);
        //img = new ImageIcon("location1.jpg");
        ImageIcon img = new ImageIcon(scaleImage);
        return img;
    }

    Developer developer;
    Designer designer;

    public void newWeek(){
        int weeklyEarnings = 0;
        /*
            Add up all weekly contributions
        */
        if(player.getNumOfDevs() > 0){
            developer = new Developer();
            developer.changeMorale(player.getMorale());
            int totalDeveloperContribution =  (player.getNumOfDevs()*developer.getContribution());
            weeklyEarnings += totalDeveloperContribution;
        }
        if(player.getNumOfDesigners() > 0){
            designer = new Designer();
            designer.changeMorale(player.getMorale());
            int totalDesignerContribution = player.getNumOfDesigners()*designer.getContribution();
            weeklyEarnings += totalDesignerContribution;
        }


        player.addCash(weeklyEarnings);
        JOptionPane.showMessageDialog(null, "You made $" + weeklyEarnings + " this week.");
        player.nextWeek();
        cashLabel.setText(cashInWallet + player.getCash());
        yearLabel.setText(player.getYearSTR());
        monthLabel.setText(player.getMonthSTR());
        weekLabel.setText(player.getWeekSTR());

    } //new week method

}