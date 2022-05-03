package game.magicalItems;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public abstract class MagicalItems extends Item {
    private int turn = 0;
    public MagicalItems(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
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

}
