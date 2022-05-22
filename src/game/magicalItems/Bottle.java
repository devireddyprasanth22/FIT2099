package game.magicalItems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Bottle extends MagicalItems{

    private enum differentWaters {
        NO_WATER,
        HEALTH_WATER,
        POWER_WATER
    }
    private differentWaters waterInBottle;

    public Bottle() {
        /**
         * Constructor for MagicalItems
         *
         * @param name
         * @param displayChar
         * @param portable
         * @param waterInBottle
         */

        super("Bottle", 'b', true);
        this.addCapability(Status.BOTTLE);
        waterInBottle = differentWaters.NO_WATER;
    }

    public differentWaters getWaterInBottle(){
        return waterInBottle;
    }

    public void setHealthWaterInBottle(){
        waterInBottle = differentWaters.HEALTH_WATER;
    }

    public void setPowerWaterInBottle(){
        waterInBottle = differentWaters.POWER_WATER;
    }

    public void setNoWaterInBottle(){
        waterInBottle = differentWaters.NO_WATER;
    }

    @Override
    public void tick(Location location, Actor actor){

    }
}
