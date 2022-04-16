package t1;

import java.util.ArrayList;
import java.util.Scanner;

public class T1 {

    public static boolean checkLength(String pass) {
        if (pass.length() >= 8) {
            return true;
        }
        return false;
    }

    public static boolean checkLen(String name) {
        if (name.length() >= 5) {
            return true;
        }
        return false;
    }

    public static boolean checkNum(int age) {
        if (age >= 10) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<UnoCard> playerdeck = new ArrayList<UnoCard>();
        ArrayList<UnoCard> compdeck = new ArrayList<UnoCard>();
        int win; // 0 - no result; 1 - win; -1 - loss. 
        Scanner input;
        UnoCard topCard; // card on top of the "pile"
        int choiceIndex; // Index of chosen card for both player and computer
        String currentColor; // Mainly used for wild cards

        Scanner k = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = k.next();
        System.out.print("Enter your age: ");
        int age = k.nextInt();
        System.out.print("Enter your password: ");
        String pass = k.next();

        if (checkLength(pass)) {
            System.out.println("The password is valid ");
        } else {
            System.out.println("Password is not valid");
        }

        if (checkNum(age)) {
            System.out.println("The age is valid ");
        } else {
            System.out.println("Age is not valid");
        }

        if (checkLen(name)) {
            System.out.println("The name is valid ");
        } else {
            System.out.println("Name is not valid");
        }

        gameLoop:
        while (true) {
            playerdeck.clear();
            compdeck.clear();
            win = 0;
            topCard = new UnoCard();
            currentColor = topCard.color;

            System.out.println("\nWelcome to Uno! Initialising decks...");

            draw(7, playerdeck);
            draw(7, compdeck);

            /**
             * ***************Turns****************
             */
            for (boolean playersTurn = true; win == 0; playersTurn ^= true) {
                choiceIndex = 0;
                System.out.println("\nThe top card is: " + topCard.getFace());

                if (playersTurn) /**
                 * ***Player's turn*****
                 */
                {
                    // Displaying user's deck
                    System.out.println("Your turn! Your choices:");
                    for (int i = 0; i < playerdeck.size(); i++) {
                        System.out.print(String.valueOf(i + 1) + ". "
                                + ((UnoCard) playerdeck.get(i)).getFace() + "\n");
                    }
                    System.out.println(String.valueOf(playerdeck.size() + 1) + ". " + "Draw card" + "\n"
                            + String.valueOf(playerdeck.size() + 2) + ". " + "Quit");
                    // Repeats every time the user doesn't input a number
                    do {
                        System.out.print("\nPlaease input the number of your choice: ");
                        input = new Scanner(System.in);
                    } while (!input.hasNextInt());
                    // The choices were incremented to make them seem more natural (i.e not starting with 0)
                    choiceIndex = input.nextInt() - 1;

                    // Taking action
                    if (choiceIndex == playerdeck.size()) {
                        draw(1, playerdeck);
                    } else if (choiceIndex == playerdeck.size() + 1) {
                        break gameLoop;
                    } else if (((UnoCard) playerdeck.get(choiceIndex)).canPlace(topCard, currentColor)) {
                        topCard = (UnoCard) playerdeck.get(choiceIndex);
                        playerdeck.remove(choiceIndex);
                        currentColor = topCard.color;
                        // Producing the action of special cards                        
                        if (topCard.value == 10) {
                            playersTurn = false; // Skipping turn

                            switch (topCard.value) {
                                case 10: // Wild cards                         
                                    do // Repeats every time the user doesn't input a valid color
                                    {
                                        System.out.print("\nEnter the color you want: ");
                                        input = new Scanner(System.in);
                                    } while (!input.hasNext("R..|r..|G....|g....|B...|b...|Y.....|y.....")); //Something I learned recently
                                    if (input.hasNext("R..|r..")) {
                                        currentColor = "Red";
                                    } else if (input.hasNext("G....|g....")) {
                                        currentColor = "Green";
                                    } else if (input.hasNext("B...|b...")) {
                                        currentColor = "Blue";
                                    } else if (input.hasNext("Y.....|y.....")) {
                                        currentColor = "Yellow";
                                    }

                                    System.out.println("You chose " + currentColor);
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Invalid choice. Turn skipped.");
                    }

                } else /**
                 * ********** computer's turn *************
                 */
                {
                    System.out.println("My turn! I have " + String.valueOf(compdeck.size())
                            + " cards left!" + ((compdeck.size() == 1) ? "...Uno!" : ""));
                    // Finding a card to place
                    for (choiceIndex = 0; choiceIndex < compdeck.size(); choiceIndex++) {
                        if (((UnoCard) compdeck.get(choiceIndex)).canPlace(topCard, currentColor)) // Searching for playable cards
                        {
                            break;
                        }
                    }

                    if (choiceIndex == compdeck.size()) {
                        System.out.println("I've got nothing! Drawing cards...");
                        draw(1, compdeck);
                    } else {
                        topCard = (UnoCard) compdeck.get(choiceIndex);
                        compdeck.remove(choiceIndex);
                        currentColor = topCard.color;
                        System.out.println("I choose " + topCard.getFace() + " !");

                        // Must do as part of each turn because topCard can stay the same through a round
                        if (topCard.value == 10) {
                            playersTurn = true; // Skipping turn

                            switch (topCard.value) {
                                case 10: // Wild cards                         
                                    do // Picking a random color that's not none
                                    {
                                        currentColor = new UnoCard().color;
                                    } while (currentColor == "none");

                                    System.out.println("New color is " + currentColor);
                                    break;
                            }
                        }
                    }

                    // If decks are empty
                    if (playerdeck.size() == 0) {
                        win = 1;
                    } else if (compdeck.size() == 0) {
                        win = -1;
                    }
                }

            } // turns loop end

            /**
             * ***********Results*************
             */
            if (win == 1) {
                System.out.println("You win :)");
            } else {
                System.out.println("You lose :(");
            }

            System.out.print("\nPlay again? ");
            input = new Scanner(System.in);

            if (input.next().toLowerCase().contains("n")) {
                break;
            }
        } // game loop end

        System.out.println("Bye bye");
    }

    // For drawing cards
    public static void draw(int cards, ArrayList<UnoCard> deck) {
        for (int i = 0; i < cards; i++) {
            deck.add(new UnoCard());
        }
    }
}
