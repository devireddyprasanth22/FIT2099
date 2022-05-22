package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public abstract class Enemy extends Actor {
    private int hp;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.hp = hitPoints;
        this.addCapability(Status.REMOVE);
        this.addCapability(Status.ENEMY);
    }

    public int getHp() {
        return this.hp;
    }

    @Override
    public void hurt(int points){
        this.setHp(this.hp - points);
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    @Override
    abstract public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);

    @Override
    abstract public ActionList allowableActions(Actor otherActor, String direction, GameMap map);

    @Override
    public boolean isConscious(){
        return this.hp > 0;
    }
    /**
     * currentLocation: current location of Goomba object
     * returns: Actor object in range of player. If not in range, return null
     */
    public Actor getPlayerObj(Location currentLocation) {
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for (int i = 0; i < xArr.length; i++) {
            for (int j = 0; j < yArr.length; j++) {
                try {
                    Location newLocation = currentLocation.map().at(x + xArr[i], y + yArr[j]);
                    if (newLocation.containsAnActor()) {
                        if (newLocation.getActor().getDisplayChar() == 'm' || newLocation.getActor().getDisplayChar() == 'M') {
                            return newLocation.getActor();
                        }
                    }
                } catch (Exception e) {
                    //do nothing
                }
            }
        }
        return null;
    }

    abstract public void tick(Location currentLocation);

    /**
     * currentLocation: location of Koopa on the map
     * returns: true or false if Mario is in attack range or not
     */
    public boolean isPlayerInAttackRange(Location currentLocation) {
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for (int k : xArr) {
            for (int i : yArr) {
                try {
                    Location newLocation = currentLocation.map().at(x + k, y + i);
                    if (newLocation.containsAnActor()) {
                        if (newLocation.getActor().getDisplayChar() == 'm' || newLocation.getActor().getDisplayChar() == 'M') {
                            return true;
                        }
                    }
                } catch (Exception e) {
                    //doing nothing
                }

            }
        }
        return false;
    }
}
