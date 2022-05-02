package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;

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

        if(actorLocation.getDisplayChar() == "$".charAt(0)){
            this.addItemToInventory(new Coin());
        }
        this.getInventory().forEach(item -> {
            System.out.println(item.getClass());
        });

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
