package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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

    private List<Item> playerInventory;

    public Toad() {
        super("Toad", 'O', 100);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public String speak(Player player) {
        this.playerInventory = player.getInventory();

        String spokenLine = null;

        if (this.playerInventory.isEmpty()) {
            int randomIndex = new Random().nextInt(this.monologueLines.length);
            // display everything
             spokenLine = this.monologueLines[randomIndex];

        } else if (this.playerInventory.contains(new Coin())) {
            // TODO: Replace with Wrench

            List<String> copyMonologueLines = new ArrayList<>(){};
            copyMonologueLines.addAll(List.of(this.monologueLines));

            copyMonologueLines.remove(2);
            int randomIndex = new Random().nextInt(copyMonologueLines.size());
            spokenLine = copyMonologueLines.get(randomIndex);
        }
        return spokenLine;
    }

//    public void sayStuff(Player player){
//        this.playerInventory = player.getInventory();
//
//        if(this.playerInventory.isEmpty()){
//            // display everything
//        }else if(this.playerInventory.contains(new Coin())){
////
//        }
//    }
}
