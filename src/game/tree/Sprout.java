package game.tree;

import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.enemies.Goomba;
import game.magicalItems.FireFlower;

public class Sprout extends Tree {

    private final int upgradeSpawnTurns = 10;

    /**
     * Constructor for Sprout
     */
    public Sprout() {
        super('+');
        this.addCapability(Status.SPROUT);
    }

    /**
     * Method called tick that helps keep track of turns and gets location
     * Adds goomba at a 10% chance and becomes a sapling if turn is 10
     * @param location Location object
     */
    @Override
    public void tick(Location location) {
        this.incrementTurn();
        if (chance(10) && !location.containsAnActor()) {
            location.addActor(new Goomba());
        }
        if (this.getTurn() == upgradeSpawnTurns) {
            location.setGround(new Sapling());
            if (chance(50) && !location.containsAnActor()) {
                location.addItem(new FireFlower());
            }
        }
    }

}