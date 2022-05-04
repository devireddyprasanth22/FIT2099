package game.magicalItems;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

import java.util.Random;

public abstract class MagicalItems extends Item {
    private int turn = 0;

    /**
     * Constructor for MagicalItems
     * @param name
     * @param displayChar
     * @param portable
     */
    public MagicalItems(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addCapability(Status.IS_RESETTABLE);
    }

    /**
     * getter for turn
     * @return
     */
    public int getTurn() {
        return turn;
    }

    /**
     * method that increments turn
     */
    public void incrementTurn() {
        this.turn += 1;
    }

    /**
     * tick takes in location and calls incrementTurn()
     * @param location
     */
    public void tick(Location location){
        this.incrementTurn();
    }

    /**
     * chance takes in the chancePercent and returns a boolean value
     * @param chancePercent
     * @return
     */
    public boolean chance(int chancePercent) {
        Random r = new Random();
        return r.nextInt(100) < (chancePercent);
    }

}
