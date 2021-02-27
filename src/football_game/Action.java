/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football_game;

/**
 *
 *  @author Hansi Karunarathna
 */
public class Action extends GameContent {
    
    public String Goal() {
        return "Goal scored";
    }
    
    public String GoalSaved() {
        return "Attempted to goal ";
    }
    
    public String Possession() {
        return "Possession";
    }
    
     public String Pass() {
        return "Passed the boal";
    }
     
     public String Receive() {
        return "Received the boal";
    }
    public String Injured() {
        return "INJURED!";
    }
}
