package game.magicalItems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public class Bottle extends MagicalItems{

    private enum differentWaters {
        NO_WATER,
        HEALTH_WATER,
        POWER_WATER
    }
    private differentWaters waterInBottle;

    public Bottle(String name, char displayChar, boolean portable) {
        /**
         * Constructor for MagicalItems
         *
         * @param name
         * @param displayChar
         * @param portable
         * @param waterInBottle
         */
        super(name, displayChar, portable);
        waterInBottle = differentWaters.NO_WATER;
    }

    public differentWaters getWaterInBottle(){
        return waterInBottle;
    }

    public void setWaterInBottle(differentWaters val){
        waterInBottle = val;
    }

    @Override
    public void tick(Location location, Actor actor){

    }
}
