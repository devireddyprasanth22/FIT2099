package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.behaviour.Behaviour;
import game.behaviour.FollowBehaviour;

import java.util.HashMap;
import java.util.Map;

public class FlyingKoopa extends AbstractKoopa{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Constructor.
     *
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
    }

    /**
     * actions: ActionList of allowable actions of Koopa
     * lastAction: Previous action of Koopa
     * map: current GameMap
     * display: current Display object
     * <p>
     * returns: current action of Koopa. If none, returns DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location location = map.locationOf(this);
        tick(location);
        for (game.behaviour.Behaviour Behaviour : this.behaviours.values()) {
            if (location.map().locationOf(this) != null || this.getDisplayChar() != 'D') {
                Action action = Behaviour.getAction(this, map);
                if (action != null) {
                    return action;
                }
            }
        }
        // Adding follow behaviour
        if(this.isPlayerInAttackRange(location)){
            Player player = (Player) this.getPlayerObj(location);
            this.behaviours.put(9, new FollowBehaviour(player));
        }
        return new DoNothingAction();
    }
}
