package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Koopa extends Actor{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private int hp;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Koopa() {
        super("Koopa", 'K', 100);

    }

    public void tick(Location currentLocation){
        int r1 = (int)(Math.random()*(11-1)+1);
        System.out.println(r1);
        //Dormant mechanic
        if(this.getHp() < 0){
            this.setDisplayChar('D');
        }
        //attack mechanic
        else if(isPlayerInAttackRange(currentLocation)){
            //koopa is in attack range
            if(r1 > 5 && this.getDisplayChar() != 'D') {
                //koopa attacks player
                System.out.println("Koopa attacks player");
                getPlayerObj(currentLocation).hurt(30);
            } else if(this.getDisplayChar() != 'D'){
                //koopa misses player
                System.out.println("koopa misses player");
            }
        }
    }

    public int getHp(){
        return hp;
    }

    public boolean isPlayerInAttackRange(Location currentLocation){
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for(int i = 0; i < xArr.length; i++){
            for(int j = 0; j<yArr.length; j++){
                try{
                    Location newLocation = currentLocation.map().at(x + xArr[i], y + yArr[j]);
                    if(newLocation.containsAnActor()){
                        if(newLocation.getActor().getDisplayChar() == 'm'){
                            return true;
                        }
                    }
                } catch(Exception e){
                    //doing nothing
                }

            }
        }
        return false;
    }
    public Actor getPlayerObj(Location currentLocation){
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for(int i = 0; i < xArr.length; i++){
            for(int j = 0; j<yArr.length; j++){
                try{
                    Location newLocation = currentLocation.map().at(x + xArr[i], y + yArr[j]);
                    if(newLocation.containsAnActor()){
                        if(newLocation.getActor().getDisplayChar() == 'm'){
                            return newLocation.getActor();
                        }
                    }
                } catch(Exception e){
                    //do nothing
                }
            }
        }
        return null;
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location location = map.locationOf(this);
        tick(location);
        for(Behaviour Behaviour : behaviours.values()) {
            if(location.map().locationOf(this) != null || this.getDisplayChar() != 'D') {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }
}
