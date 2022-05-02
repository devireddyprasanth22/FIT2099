package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Toad extends Actor {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    private final String[] monologueLines = new String[]
            {
                    "You might need a wrench to smash Koopa's hard shells.",

                    "You better get back to finding the Power Stars.",

                    "The Princess is depending on you! You are our only hope.",

                    "Being imprisoned in these walls can drive a fungus crazy :("
            };


    public Toad() {
        super("Toad", 'O', 100);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public String speak(){
        int randomIndex = new Random().nextInt(this.monologueLines.length);
        return this.monologueLines[randomIndex];
    }

    public void sayStuff(Player player){
        if(player.getInventory().isEmpty()){
            // display everything
        }
    }
}
