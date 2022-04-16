/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1;

import java.util.Random;


public class UnoCard {
    
    public String color;
    public int value;
    private Random rand;
    private String face;

    public UnoCard(int v, String c)
    {
        value = v;
        color = c; 
    }

    // Creates a random card
    public UnoCard()
    {
        rand = new Random();
        value = rand.nextInt(20); // 108 cards in a standard Uno deck. Can be reduced to 27 (disregarding colors)
        // Assigning value
        if (value > 10) // Some cards are more common than others
            value -= 10;
        // Assigning color
        rand = new Random();
        switch(rand.nextInt(4) )
        {
            case 0: color = "Red"; 
                break;
            case 1: color = "Green"; 
                break;
            case 2: color = "Blue"; 
                break;
            case 3: color = "Yellow"; 
                break;
        }
        // If the card is a wild card
        if (value >= 10)
            color = "none";
    }

    public String getFace()
    {
        /* Returns the face of the card (what the player sees)
         * Ex. [Red 5]
         */
        face = "[";
        if (color != "none")
        {
            face += this.color + " ";
        }

        switch(this.value)
        {
            default: face += String.valueOf(this.value); 
                break;            
            case 10: face += "Wild"; 
                break;
        }
        face += "]";
        return face;
    }

    // Determines if you can place this card on top of a given card
    // The color needs to be specified because if a wild card was chosen in the previous round, the color would have changed, but the card staying the same
    public boolean canPlace(UnoCard o, String c)
    {
        if (this.color == c)
            return true;
        else if (this.value == o.value)
            return true;
        else if (this.color == "none") // Wild cards
            return true;
        return false;
    }
}
