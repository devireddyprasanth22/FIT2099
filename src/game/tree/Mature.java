package game.tree;

import edu.monash.fit2099.engine.positions.Location;
import game.Coin;
import game.Dirt;
import game.Goomba;

public class Mature extends Tree {
    public Mature() {
        super('T');
    }

    @Override
    public void tick(Location location) {
        this.incrementTurn();
        if (chance(15)&& !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
        if (this.getTurn() % 5 == 0) {

        }
        if(chance(20)){
            location.setGround(new Dirt());
        }
    }
//    @Override
//    public TreeState checkState() {
//        if (this.chance(20)) {
//            return TreeState.DIE;
//        }
//        if (this.getTurn() % 5 == 0) {
//            return TreeState.GROW;
//        } else if (this.chance(15)) {
//            return TreeState.SPAWN;
//        } else {
//            return TreeState.UNKNOWN_ERROR;
//        }
//    }
}