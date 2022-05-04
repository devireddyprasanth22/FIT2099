package game.magicalItems;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Location;
import game.Dirt;
import game.Status;

import java.util.List;

public class PowerStar extends MagicalItems{
    public PowerStar() {

        super("Power Star", '*', true);
        this.addCapability(Status.PASS_HIGH_GROUND);
        this.addCapability(Status.CONVERT_COINS);
        this.addCapability(Status.INVINCIBLE);
        this.addCapability(Status.INSTANT_KILL);
        this.addCapability(Status.POWER_STAR);
    }

    public void tick(Location location){
        this.incrementTurn();
        if(this.getTurn() == 10){
            location.removeItem(this);
            location.setGround(new Dirt());
            // add functionality to remove from inventory
        }
    }
}
