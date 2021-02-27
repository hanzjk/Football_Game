package football_game;

/**
 *
 * @author Hansi Karunarathna 
 */
public class Person {
    
    private String playerName;
    private int goal_Scored;
    
    public Person() {} //default constructor
    
    //parameterized constructor to store player's name
    public Person(String playerName) {
        this.playerName = playerName;
    }
    
     //Return the  players names
    public String getPlayerName() {
        return playerName;
    }

    //set the players names
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    //refree announcemnets
    public void startTime(int i){
        System.out.println("\n------------------------------------Refree : Time Started (" +i+" min)-----------------------------------------\n");
    }
    
    public void endTime(int i){
        System.out.println("\n----------------------------------Refree : Time Ended (" +i+ "min)--------------------------------------------\n");
    }
    
    //Display messages from different people
    public void Msg(String message){
        System.out.println(message);
    }
    
    
     //Set the no of  goals scored
    public void setGoalsScored(int goal_Scored) {
        this.goal_Scored = goal_Scored;
    }
    
   //increase the number of goals scored by a team
    public void incGoalsScored() {
        this.goal_Scored++;
    }

    
    //Return the no of goals scored
    public int getGoalsScored() {
        return goal_Scored;
    }

   
}