package tycoonBasic;
//parent class for employee positions.
public class Employee {
   private String position = "blank";

    private int morale = 50; //starting morale
    private int salary; //salary can vary depending on experience
    private int exFromLastRaise, experience; //varies and more is better. 0-50
                            //the more experience they gain, the more likely they are to ask for a raise.  Failure to give them one drops their morale
                            //exFromLastRaise = experience gained since their previous raise
    private boolean wantRaise = false;
    private int contributionMoney;

    protected Employee(){
        //default
    }

    protected Employee(int salary, int experience){

       // ..this.position = position;
        this.salary = salary;
        this.exFromLastRaise = exFromLastRaise;
        this.experience = experience;
    }

    protected boolean askingForRaise(int exFromLastRaiseChild, int experienceChild){
        if(exFromLastRaiseChild - experienceChild > 10){
            exFromLastRaiseChild = experienceChild;
            return true;
        }
        return false;
    }
    //if they want a raise, and are not paid, then they work less hard

    protected void receivedRaise(){
        wantRaise = false;
    }

    protected int getExperience(){
        return this.experience;
    }
    protected String getPosition(){
       return this.position;
    }
    public void changeMorale(int morale){
        this.morale = morale;
    }
    protected int getMorale(){
        return this.morale;
    }
    protected int getSalary(){
        return this.salary;
    }
}
