package tycoonBasic;
import javax.swing.JFrame;
import javax.swing.*;

import java.awt.*;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{ //JPanel

    private JPanel panel, panel2, panel3, panel4, panel5, panel6, panel7, panel8;
    private final int WIN_WIDTH = 450, WIN_HEIGHT = 450;
    private JButton hireButton, rentLocation, bankButton, saveButton;

    String moraleCheck = "Morale: ",
            workersCheck = "Employees: ";
    String hire = "Hire more employees",
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
    String cashGeneratingPerDay = "You're generating $", cashGeneratingPerDay2 = " per day.",
            saveThisGame = "Do you want to save your game?", saveGameSTR = "Save Game",
            amountOfLoansSTR ="Amount of loans: $";
    Player player;

    private JLabel cashLabel, workers, devoloperNumberLabel, designerNumberLabel,
                    totalLoans;

    public void buildPanel(Player player) {
        this.player = player;
        //JLabel playerName = new JLabel(player.getName());
        JLabel moraleLabel = new JLabel(moraleCheck);
        JLabel dateLabel = new JLabel(this.player.getDateSTR());
        workers = new JLabel(workersCheck + player.numOfEmployees);
        String cashSTR = Integer.toString(this.player.getCash());
        cashLabel = new JLabel("$" + cashSTR);
        devoloperNumberLabel = new JLabel("Developers Hired: " + player.getNumOfDevs());
        designerNumberLabel = new JLabel("Designers Hired: "  + player.getNumOfDesigners());
        JLabel saveGameLabel = new JLabel(saveThisGame);

        String numEmployees = Integer.toString(this.player.getNumOfEmployees());
        JLabel totalNumOfEmployees = new JLabel(employeeNumStr+ numEmployees + employeeNumStr2);
        JLabel cashGeneratingLabel = new JLabel(cashGeneratingPerDay + 0 +cashGeneratingPerDay2);
        JLabel buyWorkers = new JLabel(hire);

        totalLoans = new JLabel(amountOfLoansSTR + player.getDebt());
        buyWorkers.setHorizontalAlignment(SwingConstants.CENTER);

        hireButton = new JButton(hire);
        hireButton.addActionListener(new PayButton());

        rentLocation = new JButton(rent);
        rentLocation.addActionListener(new PayButton());

        bankButton = new JButton(bank);
        bankButton.addActionListener(new PayButton());

        saveButton = new JButton(saveGameSTR);
        saveButton.addActionListener(new PayButton());

        panel = new JPanel(new GridLayout(4,0));
        panel2 = new JPanel(); //bank
        panel3 = new JPanel(); //location
        panel4 = new JPanel(); //employees
        panel5 = new JPanel();
        panel6 = new JPanel(); //
        panel7 = new JPanel(); //will have the location picture
        panel8 = new JPanel(); //be the new save option

        // panel.add(playerName);
        panel.add(cashLabel);
        panel.add(dateLabel);
        panel.add(workers);
        panel.add(moraleLabel);

        panel2.add(totalLoans);
        panel2.add(bankButton);

        panel3.add(rentLocation);

        panel4.add(devoloperNumberLabel);
        panel4.add(designerNumberLabel);
        panel4.add(hireButton);

        panel5.add(cashGeneratingLabel);

        panel6.add(saveGameLabel);
        panel6.add(saveButton);

    }
    String devHire = "Developer for $2,000", designHire = "Designer for $1,000", cancel = "Cancel", interview = "Which will you hire?";
    String hired = "You have hired a ", dev = "developer", des = "designer";
    String insuffecientFunds = "You do not have enough funds. Try taking a loan from the bank.";

    private class PayButton implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == hireButton){
                //JOptionPane.showMessageDialog(null,"Do you want to buy employees?");
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
                if(ans == 0){ //hires developer
                   devHire();
                }
                if(ans == 1){ //hires designer
                    designHire();
                }
            }
            if(event.getSource() == rentLocation){
                JOptionPane.showMessageDialog(null,"Do you want to buy property?");
            }
            if(event.getSource() == bankButton){
                //JOptionPane.showMessageDialog(null,"Do you want to pull a loan?"); // give options to pull loans or pay them off
                atBank();
            }

            if(event.getSource() == saveButton){
                JOptionPane.showMessageDialog(null, "You're game has been saved.");
                play.saveGame(player);
            }
        }

        public void devHire(){
            Developer devMan = new Developer("Rick", 2000, 0);
            if(player.getCash() - devMan.getSalary() < 0){
                JOptionPane.showMessageDialog(null, insuffecientFunds);
            }else{
                player.payment(devMan.getSalary());

                JOptionPane.showMessageDialog(null,hired+dev);
                String afterPay = Integer.toString(player.getCash());
                cashLabel.setText("$" + afterPay);
                player.addEmployees();
                player.addDev();
                workers.setText(workersCheck + player.getNumOfEmployees());
                devoloperNumberLabel.setText("Developers Hired: " + player.getNumOfDevs());

            }
        }

        public void designHire(){
            Designer designer = new Designer("Rick", 1000, 0);
            if(player.getCash() - designer.getSalary() < 0){
                JOptionPane.showMessageDialog(null, insuffecientFunds);
            }else{
                player.payment(designer.getSalary());
                JOptionPane.showMessageDialog(null,hired+des);
                String salaryPay = Integer.toString(player.getCash());
                cashLabel.setText("$" + salaryPay);
                player.addEmployees();
                player.addDesigner();
                workers.setText(workersCheck + player.getNumOfEmployees());
                designerNumberLabel.setText("Designers Hired: "  + player.getNumOfDesigners());
            }
        }
        String takeLoan = "Take out a loan", payLoan = "Pay a loan",
            howMuchForLoan = "How much do you want to pull out for a loan?";
        public void atBank(){
            Object[] options = {payLoan,
                    takeLoan,
                    cancel};
            int ans = JOptionPane.showOptionDialog(null,
                    interview,
                    hire,
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
                        loanPayment  = Integer.valueOf(JOptionPane.showInputDialog("You have $" + player.getDebt() + " in loans."));
                        if(player.debt - loanPayment < 0 || loanPayment < 0){
                            JOptionPane.showMessageDialog(null, "Make sure to only pay off your loans and no more.");
                            continue;
                        }
                        else{
                            break;
                        }
                    }catch(IllegalArgumentException e){
                        JOptionPane.showMessageDialog(null, "Please type in numbers.");
                    }
                }

                player.loanPayment(loanPayment);
                String afterPay = Integer.toString(player.getCash());
                cashLabel.setText("$" + afterPay);

            }else if(ans == 0 && player.getDebt() == 0){
                JOptionPane.showMessageDialog(null, "You have no loans to pay.");
            }
            if(ans == 1){ //
               // JOptionPane.showMessageDialog(null, "How much money do you want to pull?");
                //have the amount to pull based on how much money you're generating
                //JTextField loanPull = new JTextField();
                int loan;
                while(true){
                    try{
                        loan = Integer.valueOf(JOptionPane.showInputDialog(howMuchForLoan, 1000));
                        if(loan < 0){
                            JOptionPane.showMessageDialog(null,"Make sure to pull out loans, not give free money.");
                            continue;
                        }

                        break;
                    }catch(IllegalArgumentException e){
                        JOptionPane.showMessageDialog(null, "Type in only numbers.");
                    }
                }
                player.addCash(loan);
                player.addLoan(loan);
                String afterPay = Integer.toString(player.getCash());
                cashLabel.setText("$" + afterPay);

            }

            totalLoans.setText(amountOfLoansSTR + player.getDebt());
        }

    }

}
