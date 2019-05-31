package tycoonBasic;

public class Player {
    String busName;
    int cash;
    int[] date = new int[3];
    int timeGoneby; //current date - original date. (for month, mod12. for day mod31????.)
    int numOfDevs, numOfDesigners;
    int numOfEmployees = 0;
    int debt = 0, interest;

    private String dateSTR = date[0]+"/"+date[1]+"/"+date[2];  //month, day, year
//    private String date = day+"/"+month+"/"+year;

    //loads up saved data
    public Player(fileHandler savedData){
        this.busName = savedData.getBusName();
        this.cash = savedData.getCash();
        this.date = savedData.getDate();
        this.dateSTR = date[0]+"/"+date[1]+"/"+date[2];  //month, day, year
        //number of employee positions
        this.numOfDevs = savedData.getNumOfDevs();
        this.numOfDesigners = savedData.getNumOfDesigners();
        this.numOfEmployees = savedData.getNumOfEmployees();
        this.debt = savedData.getDebt();
        this.interest = savedData.getInterest();
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

    public void addEmployees(){
        this.numOfEmployees += 1;
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

    public String getDateSTR(){
        return this.dateSTR;
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


    public String getName(){
        return this.busName;
    }
}
