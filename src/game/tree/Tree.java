package game.tree;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Status;
import game.actions.JumpActorAction;

import java.util.ArrayList;
import java.util.Random;

public abstract class Tree extends Ground {
    private int turn = 0;

    /**
     * Constructor.
     *
     * @param displayChar to display for this type of terrain
     */
    public Tree(char displayChar) {
        super(displayChar);
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn() {
        this.turn += 1;
    }

    public void tick(Location location) {
        this.incrementTurn();
    }

    public boolean chance(int chancePercent) {
        Random r = new Random();
        return r.nextInt(100) < (chancePercent);
    }

    public Exit chooseRandomExit(ArrayList<Exit> exits) {
        Random r = new Random();
        int randomIndex = r.nextInt(exits.size());
        return exits.get(randomIndex);
    }

    public ActionList allowableActions(Player actor, Location location, String direction) {
        ActionList actions = new ActionList();
        for (Exit exit : location.getExits()) {
            if (exit.getDestination().getDisplayChar() == "+".charAt(0)) {
                if (actor.successfulJump("+".charAt(0))) {
                    actor.addCapability(Status.CAN_JUMP);
                }
                actions.add(new JumpActorAction(exit.getDestination(), exit.getName(), exit.getHotKey()));
//                    actor.jumped(location);
            }
            if (exit.getDestination().getDisplayChar() == "t".charAt(0)) {
                if (actor.successfulJump("t".charAt(0))) {
                    actor.addCapability(Status.CAN_JUMP);
                }
                actions.add(new JumpActorAction(exit.getDestination(), exit.getName(), exit.getHotKey()));
            }
            if (exit.getDestination().getDisplayChar() == "T".charAt(0)) {
                if (actor.successfulJump("T".charAt(0))) {
                    actor.addCapability(Status.CAN_JUMP);
                }
                actions.add(new JumpActorAction(exit.getDestination(), exit.getName(), exit.getHotKey()));
            }
        }

        return actions;
    }


//    public abstract TreeState checkState();


    // Abstract method to check the turns
    //public abstract TreeState checkState();
}
