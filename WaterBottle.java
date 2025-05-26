// Radhika Agrawal
// HW 6 - CS 270

import java.util.*;

public class WaterBottle{
    private int maxAmount; // max capacity
    private int curAmount; // current amount of water

    // Constructor - 
    // Initializes water bottle with given max and cur values
    public WaterBottle(int max, int cur){
        maxAmount = max; 
        curAmount = cur; 
    }

    // Fucntion to return the maxAmount
    public int getmaxAmount(){
        return maxAmount;
    }

    // Fucntion to return the curAmount
    public int getcurAmount(){
        return curAmount;
    }

    // Function to update curAmount to the new_cur value. 
    // Sets the current amount of water in the bottle
    public void setcurAmount(int new_cur){
        curAmount = new_cur;
    }

    // Function to set curAmount equal to the maxAmount.
    // Fills the bottle to its max capacity
    public void fill(){
        curAmount = maxAmount;
    }

    // Function to set curAmount to 0. 
    // Empties the bottle.
    public void empty(){
        curAmount = 0;
    }

    // Fucntion to display in the form curAmount/maxAmount of a bottle
    // Tells the current status of the bottle in terms of its current and max amount
    public static void printAmount(ArrayList<WaterBottle> water_bottles){
        for(int i = 0; i < water_bottles.size(); i++){
            System.out.println(water_bottles.get(i).getcurAmount() + "/" + water_bottles.get(i).getmaxAmount());
        }
    }

    // Fucntion to return true if the curAmount in a bottle is equal to the goal, false otherwise.
    // Checks if the current amount in a bottle is equal to the goal. 
    // If yes, return true. otherwise false
    public static boolean isSolved(ArrayList<WaterBottle> water_bottles, int goal){
        for(int i = 0; i < water_bottles.size(); i++){
            if(water_bottles.get(i).getcurAmount() == goal){
                return true;
            }
        }
        return false;
    }

    // Function to pour water from one bottle to another until 
    // either the from bottle is empty or the to bottle is full
    public static void pourWater(ArrayList<WaterBottle> water_bottles, int pour_from, int pour_to){
        WaterBottle from = water_bottles.get(pour_from);
        WaterBottle to = water_bottles.get(pour_to);

        int amount_to_pour = from.getcurAmount();
        int space_available = to.getmaxAmount() - to.getcurAmount();

        if(amount_to_pour > space_available){
            from.setcurAmount(from.getcurAmount() - space_available);
            to.setcurAmount(to.getmaxAmount());
        }
        else{
            from.setcurAmount(0);
            to.setcurAmount(to.getcurAmount() + amount_to_pour);
        }
    } 

    public static void main (String[] args){
        Scanner in = new Scanner(System.in);

        System.out.print("How many bottles? ");
        int num_bottles = in.nextInt();

        ArrayList<WaterBottle> water_bottles = new ArrayList<>();

        // Get the size of each bottle
        for(int i = 0; i < num_bottles; i++){
            System.out.print("Enter size of bottle # " + i + ": ");
            int size = in.nextInt();
            water_bottles.add(new WaterBottle(size, 0)); // Initially, all bottles are empty. 
        }

        System.out.print("What's the goal amount? ");
        int goal = in.nextInt();

        boolean solved = false; 

        while(!solved){
            int from;
            int to;

            // Get valid "from" value
            System.out.print("Which bottle do you want to pour from? (-1 for faucet): ");
            from = in.nextInt();
            while (from != -1 && (from < 0 || from >= water_bottles.size())) {
                System.out.print("Illegal bottle number, try again: ");
                from = in.nextInt();
            }

            // Get valid "to" value
            System.out.print("Which bottle do you want to fill? (-1 for no bottle): ");
            to = in.nextInt();
            while (to != -1 && (to < 0 || to >= water_bottles.size())) {
                System.out.print("Illegal bottle number, try again: ");
                to = in.nextInt();
            }

            if (from == -1 && to >= 0) {
                water_bottles.get(to).fill();
            } else if (from >= 0 && to == -1) {
                water_bottles.get(from).empty();
            } else if (from >= 0 && to >= 0) {
                pourWater(water_bottles, from, to);
            }

            // Display the updated amount after each valid move
            printAmount(water_bottles);

            if (isSolved(water_bottles, goal)) {
                System.out.println("Solved!");
                solved = true;
            }
        }
    }
}
