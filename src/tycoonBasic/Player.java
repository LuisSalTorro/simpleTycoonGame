package tycoonBasic;

import javax.swing.*;
import java.util.Random;

public class Player {
    String busName;
    int cash;
    int[] date = new int[3];
    int numOfDevs,
            numOfDesigners;
    int numOfEmployees = 0;
    int debt = 0;
    double interestRate; //interest = interest rate
    int rentTier,//starts out at one
        monthlyRent = 250; //base monthly rent for base office
    int morale = 50,
        reputation = 1;
    private boolean moraleChangedToday;

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
        this.interestRate = savedData.getInterest();
        this.rentTier = savedData.getRentTier();
        this.monthlyRent = savedData.getMonthlyRent();
        this.morale = savedData.getMorale();
        this.reputation = savedData.getReputation();
        this.moraleChangedToday = savedData.getMoraleChanged();
    }

    public void addCash(int cash){
        this.cash += cash;
    }

    public void addLoan(int loan){
        this.debt += loan;
    }

    public void totalDebt(){
        double totalDebt = (this.interestRate*this.debt)+this.debt;
        this.debt = (int) Math.round(totalDebt);
    }

    public void payment(int payment){
        this.cash -= payment;
    }

    public void loanPayment(int loanPayment){
        this.cash -= loanPayment;
        this.debt -= loanPayment;
    }

    public boolean getMoraleChanged(){
        return this.moraleChangedToday;
    }

    public void moraleChangedToday(){
        this.moraleChangedToday = true;
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
        this.moraleChangedToday = false;
        if(debt == 0){
            setInterestRate(); //sets a new interest rate at the start of every week whenever user has not pulled a loan.
        }
        sponsorShips();

        //weeks go up to 4
        if(date[2] < 4){
            date[2]++;
            return;
        }
        date[2] = 1;//revert week back to 1
        totalDebt();
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

    /**
     * At the start of every month there is a chance for the user to get a sponsorship.
     * This chance is weighed on the users reputation.  (The higher it is, the more likely they'll get an offer)
     */
    public void sponsorShips(){
        if(reputation < 12){
            return; //reputation is too low, will not get sponsorships
        }
        Random rand = new Random();
        int chance = rand.nextInt(99 - 1) + 1;
        //for now, lets hardcode a 25% chance of success
        double ceiling = this.reputation; //as reputation increases, luck increases
        if(chance <= ceiling){
            double sponsorMoney = (this.reputation*chance)*((ceiling/100)*10); //This formula gives bigger rewards for lucky rolls and higher reputation.
            int freeMoney = (int) Math.round(sponsorMoney);
            addCash(freeMoney);
            JOptionPane.showMessageDialog(null, "A company has offered to sponsor you and paid you $" + freeMoney);
        }
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

    public void changeReputation(int reputation){
        this.reputation += reputation;
    }
    public void addEmployees(){
        this.numOfEmployees += 1;
    }

    public int getMorale(){
        return this.morale;
    }

    public void changeMorale(int moraleAdder){
        if(moraleAdder < 0){
            if(this.morale >= 20){
                this.morale += moraleAdder;
            }
        }
        if(0 < moraleAdder){
            if(this.morale <= 90){
                this.morale += moraleAdder;
            }
        }

    }

    public int getCash(){
        return this.cash;
    }

    public int[] getDate(){
        return date;
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

    public double getInterest(){
        return this.interestRate;
    }
    public void setInterestRate(){
        double rate;
        //hard code to be 15% for now
        rate = .15;
        this.interestRate = rate;
    }
    public int getRentTier(){return this.rentTier;}


    public String getName(){
        return this.busName;
    }
}
