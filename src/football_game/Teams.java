package football_game;

import java.util.Random;




/**
 *
 * @author 
 */
public class Teams implements Comparable {
    
    private String teamName;
    private Person[] playerArray;
    private int pointsTotal;
    private int goalsTotal;
   private  PlayerProfile data=new PlayerProfile();
    

    public Teams() {} //default constructor
    
    /*parameterized constructors to store team data*/
    public Teams(String teamName) {
        this.teamName = teamName;
    }
    
    public Teams(String teamName, Person[] players,PlayerProfile data) {
        this(teamName);
        this.playerArray = players;
        this.data=data;
    }
    
    //function to increase the goals
    public void incGoalsTotal(int goals) {
        this.setGoalsTotal(this.getGoalsTotal() + goals);
    }
    
    public int compareTo(Object theTeam) {
        int returnValue = -1;
        
        if(this.getPointsTotal() < ((Teams)theTeam).getPointsTotal()) {
            returnValue = 1;
        } else if (this.getPointsTotal() == ((Teams)theTeam).getPointsTotal()) {
            if(this.getGoalsTotal() < ((Teams)theTeam).getGoalsTotal()) {
                returnValue = 1;
            }
        }
        
        return returnValue;
    }

    //function to display team players
    public Person displayTeamPlayers(int i){
        return playerArray[i];
    }
    
    /**
     * @return the teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName the teamName to set
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
   

    /**
     * @return the playerArray
     */
    public Person[] getPlayerArray() {
        return playerArray;
    }
    
    public Person getPlayer(int i){
        return playerArray[i];
    }
    
    public void addName(Person goalKeeper){
        this.playerArray[10] = goalKeeper;
    }

    /**
     * @param playerArray the playerArray to set
     */
    public void setPlayerArray(Person[] playerArray) {
        this.playerArray = playerArray;
    }

    /**
     * @return the pointsTotal
     */
    public int getPointsTotal() {
        return pointsTotal;
    }

    /**
     * @param pointsTotal the pointsTotal to set
     */
    public void setPointsTotal(int pointsTotal) {
        this.pointsTotal = pointsTotal;
    }

    /**
     * @return the goalsTotal
     */
    public int getGoalsTotal() {
        return goalsTotal;
    }

    /**
     * @param goalsTotal the goalsTotal to set
     */
    public void setGoalsTotal(int goalsTotal) {
        this.goalsTotal = goalsTotal;
    }
    
    
    public Person replacePlayerOne(int i){
        Random rand=new Random();   //1
        int randVal=rand.nextInt(data.reserve_T1.size());
        playerArray[i] = data.reserve_T1.get(randVal);
        data.reserve_T1.remove(randVal);
        return playerArray[i];
    }
    
     public Person replacePlayerTwo(int i){
        Random rand=new Random();  
        int randVal=rand.nextInt(data.reserve_T2.size());
        playerArray[i] = data.reserve_T2.get(randVal);
        data.reserve_T2.remove(randVal);
        return playerArray[i];
    }
   
    
}