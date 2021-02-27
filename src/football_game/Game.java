package football_game;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *@author Hansi Karunarathna
 */
  
public class Game {
   
    
    Welcome welcome =new Welcome(); //to print the welcome game screen
    /**
     * @param args the command line arguments
     */
    private static final Person refree = new Person("Refree"); //refree to take charge of the game
    private static final Person doctor = new Person("Doctor"); //incharge of medical needs
    private Person GK_Team01 = new Person(" David de Gea"); //goalkeeper of the Team One
    private Person GK_Team02 = new Person(" Samir Handanovic"); //goal keeper of the Team Two
    
    public static void main(String[] args) {
        
    	
        //create a newgame
        Game newGame = new Game();
        
        try {
            Teams[] theTeams = newGame.createTeams("Team 01","Team 02", 11); //create two teams with 11 players
            PlayGame currGame = new  PlayGame(theTeams[0],theTeams[1],refree,doctor); //create a object of playgame (a game to play)
            currGame.playGame(); //start to play the game
            currGame.showInjured(); //show th injured players
           //show the best players at the end => according to the goals they scored
            currGame.showBestPlayers(theTeams);
         }
        
        //Catch the error if the game can not be created
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    //create and display the two teams
    public Teams[] createTeams(String team01,String team02, int teamSize) throws PlayerProfileException {
        
        PlayerProfile playerData = new PlayerProfile(); //create an object to create two teams
         
       
        Teams[] theTeams = new Teams[2]; //create an array to store the two teams
    
        //create team 01
        Person[] players1 ;
        players1= playerData.getTeam(teamSize, 0);
        theTeams[0]=new Teams(team01,players1,playerData);
        theTeams[0].addName(GK_Team01);//last player is the goal keeper
        
        //create team 02
        Person[] players2 ;
        players2= playerData.getTeam(teamSize, 1);
        theTeams[1]=new Teams(team02,players2,playerData);
        theTeams[1].addName(GK_Team02); //last player is the goal keeper
        
        //Display the welcome screen of the game
        try {
            welcome.Screen("image");
            welcome.pressAnyKeyToContinue();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //Display the player of two teams
        System.out.println("\t\t**************************************MATCH BEGINS**************************************\n");
        System.out.println("\t\t======================================TEAM PLAYERS=======================================\n\n");
        
        System.out.print("\t\t\t\t\t  "+theTeams[0].getTeamName()+"\t    VS   \t"+theTeams[1].getTeamName()+"\n\n");
        
         System.out.println("\t\t\t\t\t\t\t"+theTeams[0].getTeamName());
        for(int i=0;i<teamSize;i++){
            if (i==10)
                System.out.println("\t\t\t\t\t\tPlayer "+ (i+1)+" : "+theTeams[0].displayTeamPlayers(i).getPlayerName()+" (Goalie)");
            else
                System.out.println("\t\t\t\t\t\tPlayer "+ (i+1)+" : "+theTeams[0].displayTeamPlayers(i).getPlayerName());
        }
        
         System.out.println("\n\t\t\t\t\t\t\t"+theTeams[1].getTeamName());
         for(int i=0;i<teamSize;i++){
            if(i==10)
               System.out.println("\t\t\t\t\t\tPlayer "+ (i+1)+" : "+theTeams[1].displayTeamPlayers(i).getPlayerName()+" (Goalie)");     
            else
                System.out.println("\t\t\t\t\t\tPlayer "+ (i+1)+" : "+theTeams[1].displayTeamPlayers(i).getPlayerName());
        }
        
        System.out.println("\n\t\t==============================================================================================");
        welcome.pressAnyKeyToContinue();//press ant key to continue
        
        return theTeams; //return the created teams
    }
        

    
}
