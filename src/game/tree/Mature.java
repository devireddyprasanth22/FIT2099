package game.tree;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.groundItems.Dirt;
import game.enemies.Koopa;

import java.util.ArrayList;

public class Mature extends Tree {
    /**
     * Constructor for Mature
     */
    public Mature() {
        super('T');
        this.addCapability(Status.MATURE);
        this.addCapability(Status.IS_JUMPABLE);
    }

    /**
     * Method called tick that helps keep track of turns and gets location
     * Adds a koopa at a 15% chance; sets a new sprout at a random fertile exit every 5 turns; becomes dirt at a 20% chance
     *
     * @param location
     */
    @Override
    public void tick(Location location) {
        this.incrementTurn();
        if (chance(15) && !location.containsAnActor()) {
            if(chance(50)) {
                location.addActor(new Koopa());
            }
            else{
                location.addActor(new Koopa(true));
            }
        }
        if (this.getTurn() % 5 == 0) {
            ArrayList<Exit> exits = new ArrayList<>();
            Dirt dirt = new Dirt();
            for (Exit exit : location.getExits()) {
                if (exit.getDestination().getGround().getClass().equals(dirt.getClass())) {
                    exits.add(exit);
                }
            }
            Exit randomExit = this.chooseRandomExit(exits);
            if (randomExit != null) {
                randomExit.getDestination().setGround(new Sprout());
            }
        }
        if (chance(20)) {
            location.setGround(new Dirt());
        }
    }


}