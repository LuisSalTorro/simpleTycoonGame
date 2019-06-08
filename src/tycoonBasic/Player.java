package tycoonBasic;

import javax.swing.*;

public class Player {
    String busName;
    int cash;
    int[] date = new int[3];
    int timeGoneby; //current date - original date. (for month, mod12. for day mod31????.)
    int numOfDevs,
            numOfDesigners;
    int numOfEmployees = 0;
    int debt = 0, interest;
    int rentTier,//starts out at one
        monthlyRent = 250; //base monthly rent for base office
    int morale = 50,
        reputation = 1;

    String salaryPaid = "You paid all your employee's $", salaryPaid2 = " for this month.";
    //private String dateSTR = "Year: " + date[0]+"\nMonth:"+date[1]+"\nWeek: "+date[2];  //month, day, year
//    private String date = day+"/"+month+"/"+year;

    //loads up saved data
    public Player(fileHandler savedData){
        this.busName = savedData.getBusName();
        this.cash = savedData.getCash();
        this.date = savedData.getDate();
        //this.dateSTR = date[0]+"/"+date[1]+"/"+date[2];  //month, day, year
        //number of employee positions
        this.numOfDevs = savedData.getNumOfDevs();
        this.numOfDesigners = savedData.getNumOfDesigners();
        this.numOfEmployees = savedData.getNumOfEmployees();
        this.debt = savedData.getDebt();
        this.interest = savedData.getInterest();
        this.rentTier = savedData.getRentTier();
        this.monthlyRent = savedData.getMonthlyRent();
        this.morale = savedData.getMorale();
        this.reputation = savedData.getReputation();
    }

    public void addCash(int cash){
        this.cash += cash;
    }

    public void addLoan(int loan){
        this.debt += loan;
    }

    public void payment(int payment){
        this.cash -= payment;
    }

    public void loanPayment(int loanPayment){
        this.cash -= loanPayment;
        this.debt -= loanPayment;
    }

    public void paySalary(){
        Developer developer = new Developer();
        Designer designer = new Designer();
        int totalSalaryPay = (numOfDevs*developer.getSalary())+(numOfDesigners*designer.getSalary());
        payment(totalSalaryPay);
        JOptionPane.showMessageDialog(null,salaryPaid+totalSalaryPay+salaryPaid2);
    }
    public void payRent(){
        payment(this.monthlyRent);
        JOptionPane.showMessageDialog(null,"You paid $" + this.monthlyRent +" for your office this month.");
    }

    public void nextWeek(){
        //weeks go up to 4
        if(date[2] < 4){
            date[2]++;
            return;
        }
        date[2] = 1;//revert week back to 1
        //months go up to 12
        if(date[1] < 12){
            date[1]++;
            //since its a new month, that means salary pay! and Rent!
            paySalary();
            //and pay rent
            payRent();
            return;
        }
        date[1] = 1; //revert months back to 1
        date[0]++; //incremeants the year

        //years go up to how many ever they go up to
    }

    public void setMonthlyRent(int rent){
        this.monthlyRent = rent;
    }
    public int getMonthlyRent(){
        return this.monthlyRent;
    }
    public void incrementRentTier(){
        rentTier++;
    }

    public int getReputation(){
        return this.reputation;
    }

    public void increaseReputation(int reputation){
        this.reputation += reputation;
    }
    public void addEmployees(){
        this.numOfEmployees += 1;
    }

    public int getMorale(){
        return this.morale;
    }

    public void addMorale(int moraleAdder){
        this.morale+=moraleAdder;
    }

    public int getCash(){
        return this.cash;
    }

    public String getCashSTR(){
        return Integer.toString(this.cash);
    }

    public int[] getDate(){
        return date;
    }

    public int getWeek(){
        return this.date[2];
    }

    public int getMonth(){
        return this.date[1];
    }
    public int getYear(){
        return this.date[0];
    }

    public String getWeekSTR(){
        String week = "Week: " + this.date[2];
        return week;
    }

    public String getMonthSTR(){
        String month = "Month: " + this.date[1];
        return month;
    }
    public String getYearSTR(){
        String Year = "Year: " + this.date[0];
        return Year;
    }


    public String getDateSTR(){
        //return this.dateSTR;
        return "Year: " + date[0]+"\nMonth: "+date[1]+"\nWeek: "+date[2];
    }

    public void addDev(){
        this.numOfDevs += 1;
    }
    public int getNumOfDevs(){
        return this.numOfDevs;
    }
    public void addDesigner(){
        this.numOfDesigners += 1;
    }
    public int getNumOfDesigners(){
        return this.numOfDesigners;
    }
    public int getNumOfEmployees(){
        return (this.numOfEmployees);
    }
    public int getDebt(){
        return this.debt;
    }
    public int getInterest(){
        return this.interest;
    }
    public int getRentTier(){return this.rentTier;}


    public String getName(){
        return this.busName;
    }
}
