/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football_game;

import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author Hansi Karunarathna
 */
public class Welcome {
    
    //print ASCII images to screen
    public void Screen(String name) throws IOException{
Scanner scanner = new Scanner(getClass().getResourceAsStream(name+".txt"));


while( scanner.hasNextLine()){
    System.out.println(scanner.nextLine());
}
      
 }
    
    
  public void pressAnyKeyToContinue()
 { 
        System.out.println("Press Enter key to continue...");
        try{
            System.in.read();
        }  
        catch(Exception e)
        {}  
 }
    
    
}
