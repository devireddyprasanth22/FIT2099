package game.tree;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.ResetManager;
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
        this.addCapability(Status.IS_RESETTABLE);
    }

    /**
     * A getter for turn
     *
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * method that increments turn
     */
    public void incrementTurn() {
        this.turn += 1;
    }

    /**
     * method that takes in location and keeps track of turn
     *
     * @param location The location of the Ground
     */
    public void tick(Location location) {
        this.incrementTurn();
    }

    /**
     * a method that returns a boolean value if chancePercent is within the acceptable range
     *
     * @param chancePercent
     * @return
     */
    public boolean chance(int chancePercent) {
        Random r = new Random();
        return r.nextInt(100) < (chancePercent);
    }

    /**
     * method called Exit that chooses a random exit point
     *
     * @param exits
     * @return
     */
    public Exit chooseRandomExit(ArrayList<Exit> exits) {
        Random r = new Random();
        if (exits.size() >= 1) {
            int randomIndex = r.nextInt(exits.size());
            return exits.get(randomIndex);
        }
        return null;
    }


}
