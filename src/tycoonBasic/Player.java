package tycoonBasic;

public class Player {
    String busName;
    int cash;
    int[] date = new int[3];
    int timeGoneby; //current date - original date. (for month, mod12. for day mod31????.)

    private String dateSTR = date[0]+"/"+date[1]+"/"+date[2];  //month, day, year
//    private String date = day+"/"+month+"/"+year;

    //loads up saved data
    public Player(fileHandler savedData){
        this.busName = savedData.getBusName();
        this.cash = savedData.getCash();
        this.date = savedData.getDate();
        this.dateSTR = date[0]+"/"+date[1]+"/"+date[2];  //month, day, year
    }

    public void addCash(int cash){
        this.cash += cash;
    }

    public int getCash(){
        return this.cash;
    }

    public int[] getDate(){
        return date;
    }

    public String getDateSTR(){
        return this.dateSTR;
    }

    public String getName(){
        return this.busName;
    }
}
