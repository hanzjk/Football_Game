package football_game;

//package FootBall;

/**
 *
 * @author Hansi Karunarathna
 */
public abstract class GameContent {
    private Person player;
    private Teams team;
 
   
     /**
     * @return the thePlayer
     */
    public Person getThePlayer() {
        return player;
    }

   //set the player
    public void setThePlayer(Person player) {
        this.player = player;
    }
    
    /**
     * @return the theTeam
     */
    public Teams getTheTeam() {
        return team;
    }
    
    
    //set the team
    public void setTheTeam(Teams team) {
        this.team = team;
    }

   

   
}
