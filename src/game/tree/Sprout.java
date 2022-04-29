package game.tree;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Goomba;

public class Sprout extends Tree {
    /**
     * Constructor.
     */
    public Sprout() {
        super('+');
    }

    @Override
    public void tick(Location location) {
        this.incrementTurn();
        if (chance(10) && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
        if (this.getTurn() == 10) {
            location.setGround(new Sapling());
        }
    }


//    @Override
//    public TreeState checkState() {
//        if (this.getTurn() == 10) {
//            return TreeState.GROW;
//        } else if (this.chance(10)) {
//            return TreeState.SPAWN;
//        } else {
//            return TreeState.UNKNOWN_ERROR;
//        }
//    }
//}

}