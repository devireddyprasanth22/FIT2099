package game.tree;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

public abstract class Tree extends Ground {
    private int turn = 0;

    /**
     * Constructor.
     *
     * @param displayChar to display for this type of terrain
     */
    public Tree(char displayChar) {
        super(displayChar);
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn() {
        this.turn += 1;
    }

    public void tick(Location location){
        this.incrementTurn();
    }

    public boolean chance(int chancePercent) {
        Random r = new Random();
        return r.nextInt(100) < (chancePercent);
    }

    public Exit chooseRandomExit(ArrayList<Exit> exits){
        Random r = new Random();
        int randomIndex = r.nextInt(exits.size());
        return exits.get(randomIndex);
    }


//    public abstract TreeState checkState();


    // Abstract method to check the turns
    //public abstract TreeState checkState();
}
