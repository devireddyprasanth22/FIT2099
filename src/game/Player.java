package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpActorAction;

import java.util.Random;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Jump {

    private final Menu menu = new Menu();

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location actorLocation = new Location(map, 0, 0).map().locationOf(this);
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        // return/print the console menu;

        // PLayer picks up coin -> it is added to the inventory
        if (actorLocation.getDisplayChar() == "$".charAt(0)) {
            this.addItemToInventory(new Coin());
        }

        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();

            if (
                    destination.getDisplayChar() == "#".charAt(0) ||
                            destination.getDisplayChar() == "+".charAt(0) ||
                            destination.getDisplayChar() == "t".charAt(0) ||
                            destination.getDisplayChar() == "T".charAt(0)
            ) {
                actions.add(new JumpActorAction(destination, exit.getName(), exit.getHotKey()));
                System.out.println(exit.getHotKey());
                Action actionToRemove = null;
                for(int i=0; i< actions.size(); i++){
                    if (actions.get(i).hotkey() == exit.getHotKey()){
                        actionToRemove =  actions.get(i);
                    }
                }
                actions.remove(actionToRemove);
            }
        }
        // Print inventory
//        this.getInventory().forEach(item -> {
//            System.out.println(item.getClass());
//        });

        System.out.println(this.printHp());


        return menu.showMenu(this, actions, display);
    }

    @Override
    public char getDisplayChar() {
        return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()) : super.getDisplayChar();
    }

    @Override
    public boolean successfulJump(char jumpOn) {
        return false;
    }
}
