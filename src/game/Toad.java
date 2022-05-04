package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.magicalItems.PowerStar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Toad extends Actor {
    /**
     * an array of string types called monologueLines that has the dialogues of Toad
     */
    private final String[] monologueLines = new String[]
            {
                    "You might need a wrench to smash Koopa's hard shells.",

                    "You better get back to finding the Power Stars.",

                    "The Princess is depending on you! You are our only hope.",

                    "Being imprisoned in these walls can drive a fungus crazy :("
            };
    /**
     * a private List of items called playerInventory
     */
    private List<Item> playerInventory;

    /**
     * Constructor of the Toad class
     */
    public Toad() {
        super("Toad", 'O', 100);
    }

    /**
     * playTurn method returns an action (in this instance, Toad does nothing as he is a NPC
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * speak is a method that returns a string from the monologueLines based off on some conditions
     * @param player
     * @return String
     */
    public String speak(Player player) {
        this.playerInventory = player.getInventory();

        String spokenLine = null;

        boolean hasWrench = false;
        for (Item item : this.playerInventory) {
            if (item.getDisplayChar() == 'W') {
                hasWrench = true;
                break;
            }
        }

        boolean hasPowerStar = false;
        for (Item item : this.playerInventory) {
            if (item.getDisplayChar() == '*') {
                hasPowerStar = true;
                break;
            }
        }


        if (this.playerInventory.isEmpty()) {
            int randomIndex = new Random().nextInt(this.monologueLines.length);
            // display everything
            spokenLine = this.monologueLines[randomIndex];

        }
        if (hasWrench) {
            List<String> copyMonologueLines = new ArrayList<>() {
            };
            copyMonologueLines.addAll(List.of(this.monologueLines));

            copyMonologueLines.remove(0);
            int randomIndex = new Random().nextInt(copyMonologueLines.size());
            spokenLine = copyMonologueLines.get(randomIndex);
        }
        if (hasPowerStar) {
            List<String> copyMonologueLines = new ArrayList<>() {
            };
            copyMonologueLines.addAll(List.of(this.monologueLines));

            copyMonologueLines.remove(1);
            int randomIndex = new Random().nextInt(copyMonologueLines.size());
            spokenLine = copyMonologueLines.get(randomIndex);
        }
        return spokenLine;
    }
}