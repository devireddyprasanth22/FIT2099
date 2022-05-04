package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeItemAction;
import game.actions.JumpActorAction;
import game.magicalItems.SuperMushroom;

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
        Toad toad = (Toad) map.getActorAt(new Location(map, 45, 10));

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        // return/print the console menu;

        // PLayer picks up coin -> it is added to the inventory
        if (actorLocation.getDisplayChar() == '$') {
            this.addItemToInventory(new Coin());
            actorLocation.setGround(new Dirt());
        }

        boolean hasSuperMushroom = false;
        for (Item item : this.getInventory()) {
            if (item.getDisplayChar() == '^') {
                hasSuperMushroom = true;
                break;
            }
        }
        if (hasSuperMushroom || actorLocation.getDisplayChar() == '^') {
            actions.add(new ConsumeItemAction("SuperMushroom"));
        }

        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();

            if (
                    destination.getDisplayChar() == '#' ||
                            destination.getDisplayChar() == '+' ||
                            destination.getDisplayChar() == 't' ||
                            destination.getDisplayChar() == 'T'
            ) {
                actions.add(new JumpActorAction(destination, exit.getName(), exit.getHotKey()));
                Action actionToRemove = null;
                for (int i = 0; i < actions.size(); i++) {
                    if (actions.get(i).hotkey() == exit.getHotKey()) {
                        actionToRemove = actions.get(i);
                    }
                }
                actions.remove(actionToRemove);
            }

            if (destination.getDisplayChar() == 'O') {
                String spoken = toad.speak(this);
                System.out.println("TOAD SAYS ::: " + spoken);
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
