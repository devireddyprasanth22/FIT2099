package game.tree;


import edu.monash.fit2099.engine.positions.Location;
import game.Coin;


public class Sapling extends Tree {

    public Sapling() {
        super('t');
    }

    @Override
    public void tick(Location location) {
        this.incrementTurn();
        if (chance(10)) {
            location.addItem(new Coin());
        }
        if (this.getTurn() == 10) {
            location.setGround(new Mature());
        }
    }

}