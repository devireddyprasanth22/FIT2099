package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

public class WarpPipe extends Item {
    public WarpPipe() {
        super("Warp Pipe", 'c', false);
        this.addCapability(Status.TELEPORT);
    }

    public void addSampleAction(Action newAction) {
        this.addAction(newAction);
    }
}

