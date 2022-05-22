package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Speak;
import game.Status;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.behaviour.Behaviour;
import game.behaviour.WanderBehaviour;
import game.groundItems.Fire;
import game.magicalItems.Key;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bowser extends Enemy implements Speak {
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour
    private final int hitRate;
    private final int punchDamage;

    private int turn = 0;

    private final String[] monologueLines = new String[]
            {
                    "What was that sound? Oh, just a fire.",

                    "Princess Peach! You are formally invited... to the creation of my new kingdom!",

                    "Never gonna let you down!",

                    "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!"
            };

    /**
     * Constructor.
     */
    public Bowser() {
        super("bowser", 'B', 10);
        this.behaviours.put(10, new WanderBehaviour());
        this.addCapability(Status.ENEMY);
        this.hitRate = 50;
        this.punchDamage = 80;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location myLocation = map.locationOf(this);

        turn++;
        speak();

        if(!this.isConscious()){
            map.at(myLocation.x(), myLocation.y()).addItem(new Key());
            map.removeActor(this);
        }
        boolean hitChance = this.hitRate < (Math.random() * 100);
        Player player = (Player) this.getPlayerObj(myLocation);
        if (this.isPlayerInAttackRange(myLocation) && hitChance) {
            player.hurt(this.punchDamage);
        }
        if(this.isPlayerInAttackRange(myLocation)){
            map.locationOf(player).setGround(new Fire());
        }
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (otherActor.hasCapability(Status.FIRE_ATTACK)) {
                actions.add(new FireAttackAction(this, direction));
            }
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    @Override
    public void tick(Location currentLocation) {

    }

    @Override
    public void speak(){
        int max = monologueLines.length-1;
        int min = 0;
        if(turn % 2 == 0){
            Random random = new Random();
            int randomNum = random.nextInt(max + 1 - min) + min;
            System.out.println(monologueLines[randomNum]);
        }
    }
}
