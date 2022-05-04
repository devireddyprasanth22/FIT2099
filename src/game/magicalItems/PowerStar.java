package game.magicalItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.Status;

import java.util.List;

public class PowerStar extends MagicalItems{
    /**
     * Constructor for PowerStar
     */
    public PowerStar() {
        super("Power Star", '*', true);
//        this.addCapability(Status.POWER_STAR);
    }

    /**
     * tick takes in location, calls incrementTurn() and if the turn is 10, removes the item
     * @param location
     */
    public void tick(Location location){
        this.incrementTurn();
        if(this.getTurn() == 10){
            location.removeItem(this);
            location.setGround(new Dirt());
            // add functionality to remove from inventory
        }
    }
}
