package football_game;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 *  @author Hansi Karunarathna
 */
public class PlayerProfile {
    
    
    //two array lists are used  to store players of two teams
    private ArrayList<Person> players_T1;
    private ArrayList<Person> players_T2;
    public ArrayList<Person> reserve_T1;
    public ArrayList<Person> reserve_T2;
    
    
     
    public PlayerProfile() {
        reserve_T1 = new ArrayList();
        
        reserve_T2 = new ArrayList();
        StringTokenizer playerTokens1 = new StringTokenizer(playerListOne, ","); //get players of team 1 
        players_T1 = new ArrayList();
        
        //store players to array list
        while(playerTokens1.hasMoreTokens()) {
            players_T1.add(new Person(playerTokens1.nextToken()));
        }
        
        StringTokenizer playerTokens2 = new StringTokenizer(playerListTwo, ","); //get players of team 2 
         players_T2 = new ArrayList();
        
         //store players to array list
        while(playerTokens2.hasMoreTokens()) {
            players_T2.add(new Person(playerTokens2.nextToken()));
        }
        
    }
    
    public Person[] getTeam(int numberOfPlayers,int teamNum) throws PlayerProfileException {
        
        Person[] teamPlayers = new Person[numberOfPlayers]; //create an array of type Person to store the team
         ArrayList<Person> temp = null;
         //playerlist is chosen according to the team number
         if (teamNum==0) 
             temp=players_T1; //team 01
         else if (teamNum==1)
             temp=players_T2; //team 02
         
       
        //select 10 players randomly to form the team (Goal keeper is not randomly choosen)
        for(int i = 0; i < numberOfPlayers-1; i++) {
            
                //add players randomly form the list to team 
                int playerIndex = (int) (Math.random() * temp.size());
                
                if (i==0)
                     playerIndex = 0; //Captain is in the 0 th index of the arrayList
            
                try {
                    teamPlayers[i] = temp.get(playerIndex);
                    temp.remove(playerIndex);
                }
                catch (IndexOutOfBoundsException e) {              
                    throw new PlayerProfileException("Not enough players!");
                }
           }
        
        
        //store other players into the reserved list
        if (teamNum==0){
             for(int j=0;j<temp.size();j++)
                reserve_T1.add(temp.get(j));
        }
        
        else  if (teamNum==1){
             for(int j=0;j<temp.size();j++)
                reserve_T2.add(temp.get(j));
         
         }
       
        return teamPlayers; //return the formed team
    
    }
    
    
    //players of the team one
    String playerListOne = "Lionel Messi(Captain),"+"David Beckham,"+"Deigo Maradona,"+"Neymar JR,"+"Riqui Puig,"+
            "Frankie de Jong,"+"Paolo Maldini,"+"Sergio Busquets,"+"Andres Iniesta,"+"Gerard Pique,"+"Ansu Fati,"+"Thiago Silva,"
            +"Kevin Bruyne,"+"Ronaldinho,"+"Harry Kane,"+"Mesut Ozil,"+"Sadio Mane";
    
    //players of the team two
    String playerListTwo = "Christiano Ronaldo(Captain),"+"Sergio Ramos,"+"Thomas Muller,"+"Kylian Mbappe,"+
            "Luka Modric,"+"Andrea Pirlo,"+"Sven Bender,"+"Marcelo Vieira,"+"Gareth Bale,"+"Luiz Figo,"+"Luiz Suarez,"+"Eden Hazard,"
            +"Toni Kroos,"+"Mohamed Salah,"+"Paulo Dybala,"+"Braut Haland,"+"Dani Alves";
}


class PlayerProfileException extends Exception{
    public PlayerProfileException() {}
    
    public PlayerProfileException(String message) {
        super(message);
    }
}