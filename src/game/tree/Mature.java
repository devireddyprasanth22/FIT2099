package game.tree;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.Goomba;
import game.Koopa;

import java.util.ArrayList;

public class Mature extends Tree {
    public Mature() {
        super('T');
    }

    @Override
    public void tick(Location location) {
        this.incrementTurn();
        if (chance(15)&& !location.containsAnActor()) {
            location.addActor(new Koopa());
        }
        if (this.getTurn() % 5 == 0) {
            ArrayList<Exit> exits = new ArrayList<>();
            Dirt dirt = new Dirt();
            for(Exit exit: location.getExits()){
                if (exit.getDestination().getGround().getClass().equals(dirt.getClass())){
                    exits.add(exit);
                }
            }
            Exit randomExit = this.chooseRandomExit(exits);
            randomExit.getDestination().setGround(new Sprout());
        }
        if(chance(20)){
            location.setGround(new Dirt());
        }
    }


}