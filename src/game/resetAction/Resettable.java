package game.resetAction;

import edu.monash.fit2099.engine.positions.GameMap;

public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     * HINT: play around with capability, the actual implementation happens in the tick or playTurn method.
     * TODO: execute this method in a reset manager later.
     * @param map
     */
    void resetInstance(GameMap map);

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     * TODO: Use this method at the constructor of `this` instance.
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
