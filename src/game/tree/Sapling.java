package game.tree;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Coin;
import game.Goomba;

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
        if (this.getTurn() == 1) {
            location.setGround(new Mature());
        }
    }
//    @Override
//    public TreeState checkState() {
//        if (this.getTurn() == 10){
//            return TreeState.GROW;
//        }
//        else if(this.chance(10)){
//            return TreeState.SPAWN;
//        }
//        else{
//            return TreeState.UNKNOWN_ERROR;
//        }
//    }
}