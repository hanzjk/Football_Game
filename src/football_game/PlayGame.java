package football_game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * 
 */
public class PlayGame {
    
    //private attributes to store data
    private Teams teamOne;
    private Teams teamTwo;
    private Person refree;
    private Person doctor;
    private Person lastGoalplayer;
    private Teams lastGoalTeam;
    private GameContent currentGame;
    private GameContent tempCurrentGame;
    private int teamOneGoals;
    private int teamTwoGoals;
    public ArrayList<Person> injured_T1;
    public ArrayList<Person> injured_T2;
    
    
     //to make delay when displaying game data
    public void wait(int i){
     try {
          TimeUnit.SECONDS.sleep(i);
    } catch (InterruptedException ex) {
          Logger.getLogger(PlayGame.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    public PlayGame(){} //default constructor
    
    //parameterized constructor to store tha game data
    public PlayGame (Teams teamOne, Teams teamTwo, Person refree, Person doctor) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.refree = refree;
        this.doctor = doctor;
    }
    

    public void playGame() {
        
        currentGame = null; //object of gameContent => handle the the game
        tempCurrentGame = null; //temporary object of gameContent to store gamedetails when a goal is missed
        int noOfGoals = 0;
        
        //number of goals in both teams
        teamOneGoals = 0;
        teamTwoGoals = 0;
        
        boolean interval1=false;
        boolean interval2=false;
        boolean missed; //variable to check whether the goal is missed
        boolean goal;  //variable to check for a goal
        
        injured_T1 = new ArrayList();
        injured_T2 = new ArrayList();
        for(int i = 1; i <= 90; i++) { 
            
            //intiallty all the 3 variables are assigned false
            goal = false;
            missed = false;
                      
            //start of first half
            if(i == 1){   
                refree.Msg("-----------------------------------------FIRST HALF----------------------------------------------------------");
                wait(1);
                refree.startTime(0);//print refree's message
                
            }
            
            if(Math.random()>=0.5 && i%10==0){
                System.out.println("BALL GOES OUT OF FIELD");
            }
            
            //generate a random number to check whether an action(goal, injury, ball passing) occured in that minute(i)
            if(Math.random() > 0.7) {
                
                
                   currentGame =  new Action(); 
                    //select the team who gets the ball and set a random player(excluded the goal keeper) from the team to find who has the ball
                   currentGame.setTheTeam( Math.random() > 0.5 ? teamOne: teamTwo);   
                   Random rand=new Random();
                   int randVal=(int) (rand.nextInt(10));
                   currentGame.setThePlayer(currentGame.getTheTeam().getPlayerArray()[randVal]);
                
                 //generate a random number to check whether a player has tried to score a goal
                if(Math.random() > 0.7){    
                    //if a goal is scored
                    if(Math.random() > 0.6){ //generate another random number to check whether a player scored a goal
                        goal=true;
                        noOfGoals++;
                     if (currentGame.getTheTeam() == teamOne) {
                        lastGoalTeam = teamOne; //store the team name who scored the last goal
                        teamOneGoals++;
                        teamOne.incGoalsTotal(1);
                    }
                    
                     else {
                        lastGoalTeam = teamTwo; //store the team name who scored the last goal
                        teamTwoGoals++;
                        teamTwo.incGoalsTotal(1);
                    }
                    wait(1); //display who scored a goal
                    System.out.println(((Action)currentGame).Goal()+" after "+i+" mins by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+"!");
                    
                    //increase the goals scored by the player
                    currentGame.getThePlayer().incGoalsScored();
                    lastGoalplayer = currentGame.getThePlayer(); //store the name of player who scored last goal
                    
                    }
                    
                    //if the goal is missed
                    else{
                      missed=true;
                   //copy the current stage to temporary created stage
                    tempCurrentGame = currentGame;
                     wait(1);
                    System.out.print( ((Action)currentGame).GoalSaved()+" after "+i+" mins by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+" and Goal was saved by " );
                
                    //store the goal keeper's team using temporary created game stage 
                    if(currentGame.getTheTeam() == teamOne)
                        tempCurrentGame.setTheTeam(teamTwo);
                
                    else
                        tempCurrentGame.setTheTeam(teamOne);
                
                    //set the goal keepers name of the opponent team
                    tempCurrentGame.setThePlayer(currentGame.getTheTeam().getPlayer(10));
                    
                    //display which goalkeeper from which team saved the goal
                    System.out.print(tempCurrentGame.getThePlayer().getPlayerName()+" of "+tempCurrentGame.getTheTeam().getTeamName()+"!\n"
                            + "");
                    }
                }
                
                //if the ball is passed
                else{
                     wait(1); //display who possess the ball
                    System.out.println(((Action)currentGame).Possession()+" after "+i+" mins by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+"!");
                   
                    //to selct a player to pass the ball
                    int randVal2;
                    do{
                    randVal2=(int) (rand.nextInt(5));
                    }
                    while (randVal2==java.util.Arrays.asList(currentGame.getTheTeam().getPlayerArray()).indexOf(currentGame.getThePlayer()));
                     int j;      
                     j=(i+randVal2+1);
                     if(j<90 && j!=45  ){
                        if((j>45 && interval1==true) || j<45){
                            i=j;
                    System.out.println(((Action)currentGame).Pass()+" after "+i+" mins by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+" to "+ currentGame.getTheTeam().getPlayer(randVal2).getPlayerName()+" of "+ currentGame.getTheTeam().getTeamName()+"!");
                    
                    if(Math.random()>=0.5){
                             //probability of the player receives the ball
                        j=(j+rand.nextInt(3)+1);
                        if(j<90 && j!=45 ){
                            if((j>45 && interval1==true) || j<45){
                                i=j;
                                 System.out.println(((Action)currentGame).Receive()+" after "+i+ " mins by "+currentGame.getTheTeam().getPlayer(randVal2).getPlayerName()+" of "+ currentGame.getTheTeam().getTeamName()+"!");
                    }
                        }
                    }
                     }
                }
                }
                   
                
                //if an player gets injury 
                if(Math.random() > 0.9 && !goal && !missed){
                    
                     wait(1);                     
                    doctor.Msg(((Action)currentGame).Injured()+" , "+currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()
                  +" was injured after "+i+" mins and "+ doctor.getPlayerName()+" treated him! and he was replaced by ");
                                    
                    
                    if(currentGame.getTheTeam() == teamOne){
                        injured_T1.add(currentGame.getThePlayer()); 
                       
                        currentGame.setThePlayer(currentGame.getTheTeam().replacePlayerOne(randVal));
                    }
                
                    else{
                        
                         injured_T2.add(currentGame.getThePlayer());
                        currentGame.setThePlayer(currentGame.getTheTeam().replacePlayerTwo(randVal));
                    }
                    
                    doctor.Msg(currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+"!");
                }
                
                }
            
            //display the half time and the stats of the match
            
            if(i == 45){
                try {
                    interval1=true;
                      wait(1);
                    refree.endTime(45);
                      wait(1);
                  refree.Msg("--------------------------------------END OF FIRST HALF-----------------------------------------------------");
                   
                  System.out.print("\n\tFIRST HALF >> "+ teamOne.getTeamName()+" - "+ teamTwo.getTeamName()+" : "+ teamOneGoals + " - " + teamTwoGoals);
                    
                  if(noOfGoals == 0)
                        System.out.println("\n\tNo goals were scored by any team");
                  else
                        System.out.println("\n\tLast Goal was scored by "+lastGoalplayer.getPlayerName()+" of "+lastGoalTeam.getTeamName());
                    System.out.println("\tTIME LEFT: "+(90-i)+" mins");
                      wait(1);
                    refree.Msg("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>INTERVAL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PlayGame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //display the second half
                refree.Msg("\n---------------------------------------------SECOND HALF-------------------------------------------------------");
                 wait(1);
                refree.startTime(46);
                
            }
            
            //end of the match and display the stats
            if(i == 90){
                  wait(1);
               refree.endTime(90);
                 wait(1);
               refree.Msg("-----------------------------------------END OF SECOND HALF----------------------------------------------------");
                System.out.print("\n\tSECOND HALF >> "+ teamOne.getTeamName()+" - "+ teamTwo.getTeamName()+" : "+ teamOneGoals + " - " + teamTwoGoals);
                if(noOfGoals == 0)
                    System.out.println("\n\tNo goals were scored by any team");
                else
                    System.out.println("\n\tLast Goal was scored by "+lastGoalplayer.getPlayerName()+" of "+lastGoalTeam.getTeamName());
                System.out.println("\tTIME LEFT: "+(90-i)+" mins");    
            }
        }
        
        
        //if the game is drawn play extra time
        if(teamOneGoals == teamTwoGoals) {
           
          
                 wait(1);
               refree.Msg("\n\nThe game  ended in a draw!\n");
                 wait(1);
             refree.Msg("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>EXTRA TIME<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
            for(int i=91;i<=120;i++){
               
                goal = false;
                missed = false;
                //first half of the extra time
                if(i == 91){
                 refree.Msg("------------------------------------------EXTRA TIME : FIRST HALF--------------------------------------------------");
                  wait(1);
                 refree.startTime(i);
                }
                
                if(Math.random()>=0.5 && i%10==0){
                System.out.println("BALL GOES OUT OF FIELD");
                }
                if(Math.random() > 0.7) { //check whether an action occured
                    
                    //select the team who gets the ball and set a random player(excluded the goal keeper) from the team to find who has the ball
                   currentGame.setTheTeam( Math.random() > 0.5 ? teamOne: teamTwo); 
                    Random rand=new Random(); 
                   int randVal=(int) (rand.nextInt(10));
                   currentGame.setThePlayer(currentGame.getTheTeam().getPlayerArray()[randVal]);
                 
                 
              
                 //generate a random number to check whether a player has tried to score a goal
                if(Math.random() > 0.7){    
                    //if a goal is scored
                    if(Math.random() > 0.6){  //generate another random number to check whether a player scored a goal
                     wait(1);
                     goal=true;
                     noOfGoals++;
                     System.out.println(((Action)currentGame).Goal()+" after "+i+" mins by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+"!");
                    
                     if (currentGame.getTheTeam() == teamOne) {
                        lastGoalTeam = teamOne; //store the team name who scored the last goal
                        teamOneGoals++;
                        teamOne.incGoalsTotal(1);
                    }
                    
                     else {
                        lastGoalTeam = teamTwo; //store the team name who scored the last goal
                        teamTwoGoals++;
                        teamTwo.incGoalsTotal(1);
                    }
                    
                    //increase the goals scored by the player
                    currentGame.getThePlayer().incGoalsScored();
                    lastGoalplayer = currentGame.getThePlayer(); //store the name of player who scored last goal
                    
                    }
                    
                    //if the goal is missed
                    else{
                    missed=true;  
                   //copy the current stage to temporary created stage
                    tempCurrentGame = currentGame;
                     wait(1);
                    System.out.print( ((Action)currentGame).GoalSaved()+" after "+i+" mins by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+" and Goal was saved by " );
                
                    //store the goal keeper's team using temporary created game stage 
                    if(currentGame.getTheTeam() == teamOne)
                        tempCurrentGame.setTheTeam(teamTwo);
                
                    else
                        tempCurrentGame.setTheTeam(teamOne);
                
                    //set the goal keepers name of the opponent team
                    tempCurrentGame.setThePlayer(currentGame.getTheTeam().getPlayer(10));
                    
                    //display which goalkeeper from which team saved the goal
                    System.out.print(tempCurrentGame.getThePlayer().getPlayerName()+" of "+tempCurrentGame.getTheTeam().getTeamName()+"!\n"
                            + "");
                    }
                }
                
                //if the ball is passed
                else{
                     wait(1);
                    System.out.println(((Action)currentGame).Possession()+" after "+i+" mins by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+"!");
                    
                     //to selct a player to pass the ball
                    int randVal3;
                    do{
                    randVal3=(int) (rand.nextInt(5));
                    }
                    while (randVal3==java.util.Arrays.asList(currentGame.getTheTeam().getPlayerArray()).indexOf(currentGame.getThePlayer()));
                    
                     int k;      
                     k=(i+randVal3+1);
                     if(k<120 && k!=105  ){
                        if((k>105 && interval2==true)|| k<105){
                            i=k;
                    System.out.println(((Action)currentGame).Pass()+" after "+i+" mins by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+" to "+ currentGame.getTheTeam().getPlayer(randVal3).getPlayerName()+" of "+ currentGame.getTheTeam().getTeamName()+"!");
                    
                    if(Math.random()>=0.5){
                             //probability of the player receives the ball
                        k=(k+rand.nextInt(3)+1);
                        if(k<120 && k!=105 ){
                            if((k>105 && interval2==true)|| k<105){
                                i=k;
                                 System.out.println(((Action)currentGame).Receive()+" after "+i+ " mins by "+currentGame.getTheTeam().getPlayer(randVal3).getPlayerName()+" of "+ currentGame.getTheTeam().getTeamName()+"!");
                    }
                        }
                    }
                     }
                }
                }
                   
                
                //if an player gets injury 
                if(Math.random() > 0.9 && !goal && !missed){
                        wait(1);                     
                    doctor.Msg(((Action)currentGame).Injured()+" , "+currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()
                  +" was injured after "+i+" mins and "+ doctor.getPlayerName()+" treated him! and he was replaced by ");
                                    
                    
                    if(currentGame.getTheTeam() == teamOne){
                        injured_T1.add(currentGame.getThePlayer());     
                        currentGame.setThePlayer(currentGame.getTheTeam().replacePlayerOne(randVal));                    }
                
                    else{
                        
                        injured_T2.add(currentGame.getThePlayer());
                        currentGame.setThePlayer(currentGame.getTheTeam().replacePlayerTwo(randVal));                    }
                    
                    doctor.Msg(currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+"!");
                }
                
                }
                
                
                //half time of the extra time
                if(i == 105){
                    try {
                        interval2=true;
                          wait(1);
                      refree.endTime(i);
                        wait(1);
                      refree.Msg("-----------------------------END OF FIRST HALF IN EXTRA TIME---------------------------------------------");
                       wait(1);
                      System.out.print("\n\tEXTRA TIME : FIRST HALF >> "+ teamOne.getTeamName()+" - "+ teamTwo.getTeamName()+" : "+ teamOneGoals + " - " + teamTwoGoals);
                        
                        if(noOfGoals == 0)
                          
                            System.out.println("\n\tNo goals were scored by any team");
                        else
                            System.out.println("\n\tLast Goal was scored by "+lastGoalplayer.getPlayerName()+" of "+lastGoalTeam.getTeamName());
                     
                        System.out.println("\tTIME LEFT: "+(120-i)+" mins");
                          
                        wait(1);
                       refree.Msg("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>INTERVAL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PlayGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      wait(1);
                   refree.Msg("------------------------------------------EXTRA TIME : SECOND HALF--------------------------------------------------");
                    wait(1);
                   refree.startTime(i);
                }
                
                ///end of extra time given
                if(i == 120){
                      wait(1);
                 refree.endTime(i);
                   wait(1);
                      refree.Msg("-------------------------------------END OF SECOND HALF IN EXTRA TIME---------------------------------------------");
                     System.out.print("\n\tEXTRA TIME : SECOND HALF >> "+ teamOne.getTeamName()+" - "+ teamTwo.getTeamName()+" : "+ teamOneGoals + " - " + teamTwoGoals);
                    if(noOfGoals == 0)
                        System.out.println("\n\tNo goals were scored by any team");
                    else
                        System.out.println("\n\tLast Goal was scored by "+lastGoalplayer.getPlayerName()+" of "+lastGoalTeam.getTeamName());
                    System.out.println("\tTIME LEFT: "+(120-i)+" mins");
                }
            }
            
        }
        
         
        //if the game is still drawn go for a penalty shootout
        if(teamOneGoals == teamTwoGoals) {
       
              wait(1);
            refree.Msg("\n\nThe game  ended in a draw!\n");
              wait(1);
            refree.Msg("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>PENALTY SHOOT-OUT<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n\n");
            currentGame.setTheTeam(Math.random() > 0.5 ? teamOne: teamTwo); //select a side to penalty shoot first
            
            System.out.println("Penalty Shootout was first taken by "+currentGame.getTheTeam().getTeamName()+"\n\n");
            for(int i=1;i<=10;i++){
                  wait(1);
                System.out.print("Penalty Shot "+ i+" : ");
                //probability to set whether a player hits a goal
                if(Math.random() > 0.8) {
                    Random p = new Random();
                    currentGame.setThePlayer(currentGame.getTheTeam().getPlayer(p.nextInt(9)));
                
                    System.out.print("Goal by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+"!\n");
                
                    //increase the goals scored if the  penalty shootout succeed
                    if (currentGame.getTheTeam() == teamOne) {
                        teamOneGoals++;
                    }
                    
                    else {
                        teamTwoGoals++;
                    }
                    
                    currentGame.getThePlayer().incGoalsScored();
                    
                    //change the team for next shootout
                    if(currentGame.getTheTeam() == teamOne)
                        currentGame.setTheTeam(teamTwo);
                
                    else
                        currentGame.setTheTeam(teamOne);
                }
                
                //if the player missed the goal
                else{
                    
                    //set the opponent team as the goal keeper of the opponent team saved the goal
                    if(currentGame.getTheTeam() == teamOne)
                        currentGame.setTheTeam(teamTwo);
                
                    else
                        currentGame.setTheTeam(teamOne);
                    
                    //display the message that the goal keeper saved the goal 
                    currentGame.setThePlayer(currentGame.getTheTeam().getPlayer(10));
                    System.out.print("Saved the goal by "+
                    currentGame.getThePlayer().getPlayerName()+" of "+currentGame.getTheTeam().getTeamName()+"!\n");       
                }              
            }
              wait(1);
            refree.Msg("\n-----------------------------------------------------END OF SHOOT-OUT------------------------------------------------------------\n");
            System.out.print("\tPENALTY SHOOT-OUT>> "+ teamOne.getTeamName()+" - "+ teamTwo.getTeamName()+" : "+ teamOneGoals + " - " + teamTwoGoals);

           
         
            
        } 
        
        //Print the final results
            wait(1);
           System.out.println("\n\n**************************************************************FINAL RESULTS****************************************************************************\n");
             wait(2);
           System.out.println("NO OF GOALS\n"+teamOne.getTeamName()+" : "+ teamOneGoals);
           System.out.println(teamTwo.getTeamName()+" : "+ teamTwoGoals);
            wait(1);
 
               
            if(teamOneGoals > teamTwoGoals) 
                System.out.println("\nCONGRATULATIONS!!! "+(teamOne.getTeamName() + " WON!\n"));
         
            else if (teamOneGoals<teamTwoGoals) 
                System.out.println("\nCONGRATULATIONS!!! "+(teamTwo.getTeamName() + " WON!\n"));
            else
                System.out.println("\nGAME ENDED IN A DRAW\n\n");
           
            wait(1);
        
    
        
        
     }
    
    
    public void showInjured(){
     System.out.println("\n\nINJURED PLAYERS"  );
            
            try{
                    if(!injured_T1.isEmpty()){
                        for(int j=0;j<injured_T1.size()
                                ;j++)
                            System.out.println(injured_T1.get(j).getPlayerName()+"("+teamOne.getTeamName()+") ");
                    }
                        
                    if(!injured_T2.isEmpty()){
                        for(int j=0;j<injured_T2.size();j++)
                            System.out.println(injured_T2.get(j).getPlayerName()+"("+teamTwo.getTeamName()+")");
                    }
                            
                //  }
            }
            catch(NullPointerException e )
            {
            System.out.println("No players were injured in both teams!");
            }
            System.out.println();
    }
    
    public void showBestPlayers(Teams[] theTeams) {
        ArrayList <Person> thePlayers = new ArrayList();
        
        for(Teams currTeam: theTeams) { //save the players to the arraylist
            thePlayers.addAll(Arrays.asList(currTeam.getPlayerArray()));
        }
        
         
         try{   
        //to print the scores of injured players
        for (int i=0;i<injured_T1.size();i++){
            thePlayers.add(injured_T1.get(i));
        }
        
         for (int i=0;i<injured_T2.size();i++){
            thePlayers.add(injured_T2.get(i));
        }
        }
         
          catch(NullPointerException e )
            {
            
            }
        
        //sort the arraylist according to the no of goals they scored 
        Collections.sort(thePlayers, (p1, p2) ->  
            Double.valueOf(p2.getGoalsScored()).compareTo
            (Double.valueOf(p1.getGoalsScored())));
        System.out.println("NUMBER OF GOALS PLAYERS SCORED: ");
        
        thePlayers.forEach((currPlayer) -> {
            System.out.println(currPlayer.getPlayerName() + " : " +  //diplsay player name along with their ascore
                    currPlayer.getGoalsScored());
        });   
        }
   
}