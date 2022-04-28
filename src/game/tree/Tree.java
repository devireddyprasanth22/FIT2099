package game.tree;

import edu.monash.fit2099.engine.positions.Ground;

import java.util.Random;

public abstract class Tree extends Ground {

    private int turn = 0;

    /**
     * Constructor.
     *
     * @paramdisplayCharcharacter to display for this type of terrain
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

    public boolean chance(int chancePercent) {
        Random r = new Random();
        return r.nextInt(100) < (chancePercent);
    }

    // Abstract method to check the turns
    public abstract TreeState checkState();
}
