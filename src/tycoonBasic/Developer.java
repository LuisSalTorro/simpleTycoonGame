package tycoonBasic;

public class Developer extends Employee{

    private String position = "Developer";
    private String name;
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

    Developer(String name, int salary, int experience) {
        super(name,salary,experience);
        this.name = name;
        //this.position = position;
        this.salary = salary;
        this.exFromLastRaise = experience; //sets starting experience.
        this.experience = experience;
    }

    public int getContribution(){
        this.contribution = salary/3; //4 being weeks per month, and at base morale barely break even
        return this.contribution*(morale/moraleMultiplyer);
    }

    public void wantsRaise(){
        this.wantRaise = super.askingForRaise(exFromLastRaise, experience);
    }

    protected void receivedRaise(){
        wantRaise = false;
    }

    protected String getName(){
        return this.name;
    }

    protected int getExperience(){
        return this.experience;
    }
    protected String getPosition(){
      return this.position;
   }
    protected int getMorale(){
        return this.morale;
    }
    protected int getSalary(){
        return this.salary;
    }

}
