package tycoonBasic;

import javax.swing.*;

public class Developer extends Employee{

    private String position = "Developer";
    private int morale = 50,//starting morale, range 1-100
                    moraleMultiplyer = 50; //at 50/50 no bonus or penalty
    private int salary; //salary can vary depending on experience
    private int exFromLastRaise,
                experience; //varies and more is better. 0-50
    //the more experience they gain, the more likely they are to ask for a raise.  Failure to give them one drops their morale
    //exFromLastRaise = experience gained since their previous raise
    private boolean wantRaise = false;
    private int contribution; //per week

    Developer(){
        //default constructor
        this.salary = 2000;
    }

    Developer(int salary, int experience) {
        super(salary,experience);
        //this.position = position;
        this.salary = salary;
        this.exFromLastRaise = experience; //sets starting experience.
        this.experience = experience;
    }

    public int getContribution(){
        this.contribution = salary/3; //4 being weeks per month, and at base morale barely break even

        double moraleD = Double.valueOf(this.morale),
                moraleMultD = Double.valueOf(this.moraleMultiplyer);
        double contMult = moraleD/moraleMultD;
        double totalCont =this.contribution*contMult;
        totalCont = (double)Math.round(totalCont* 100d)/100d;

        return (int) Math.round(totalCont);
    }

    public void wantsRaise(){
        this.wantRaise = super.askingForRaise(exFromLastRaise, experience);
    }

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
