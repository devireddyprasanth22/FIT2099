package game.groundItems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the lava ground types
 */
public class Lava extends Ground {
    public Lava() {
        super('L');
        this.addCapability(Status.LAVA);
    }
    @Override
    public boolean canActorEnter(Actor actor) {
        if(actor.hasCapability(Status.ENEMY)) {
            return false;
        }
        else{
            return true;
        }

    }


}
