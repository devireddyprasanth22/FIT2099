package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.enemies.PiranhaPlant;

public class WarpPipe extends Item {
    private int turn = 0;
    public WarpPipe() {
        super("Warp Pipe", 'c', false);
        this.addCapability(Status.TELEPORT);
    }

    public void addSampleAction(Action newAction) {
        this.addAction(newAction);
    }
    /**
     * A getter for turn
     *
     * @return turn
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

    @Override
    public void tick(Location currentLocation) {
        this.incrementTurn();
        if (this.getTurn() == 1 && currentLocation.x() != 0 && currentLocation.y() != 0) // make functionality better, maybe play around with status of lava map
        {
            currentLocation.addActor(new PiranhaPlant());
        }
    }
}

