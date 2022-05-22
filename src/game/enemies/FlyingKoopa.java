package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.behaviour.Behaviour;
import game.behaviour.FollowBehaviour;
import game.behaviour.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FlyingKoopa extends AbstractKoopa{
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    private int turn = 0;

    private final String[] monologueLines = new String[]
            {
                    "Never gonna make you cry!",

                    "Koopi koopi koopii~!",

                    "Pam pam pam!"
            };

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

    public void speak(){
        int max = monologueLines.length-1;
        int min = 0;
        if(turn % 2 == 0){
            Random random = new Random();
            int randomNum = random.nextInt(max + 1 - min) + min;
            System.out.println(monologueLines[randomNum]);
        }
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        turn++;
        speak();
        Location location = map.locationOf(this);
        tick(location);

        // Adding follow behaviour
        if(this.isPlayerInAttackRange(location)) {
            for (Exit exit : location.getExits()) {
                try {
                    Player player = (Player) this.getPlayerObj(exit.getDestination());
                    System.out.println("Player in vicinity");
                    this.behaviours.put(9, new FollowBehaviour(player));
                    break;
                } catch (Exception e) {

                    //  do nothing
                }
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            if (location.map().locationOf(this) != null || this.getDisplayChar() != 'D') {
                Action action = behaviour.getAction(this, map);
                if (action != null) {
                    return action;
                }
            }
        }
        return new DoNothingAction();
    }
}
