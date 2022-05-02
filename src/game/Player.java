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
//		if()
        // return/print the console menu;

        System.out.println(this.printHp());


        return menu.showMenu(this, actions, display);
    }

    @Override
    public char getDisplayChar() {
        return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()) : super.getDisplayChar();
    }

//    private boolean jumpResult(int chance, int damage) {
//        boolean successful = false;
//        boolean probability = new Random().nextInt(100) < chance;
//        if (probability) {
//            successful = true;
//        } else {
//            this.hurt(damage);
//        }
//        return successful;
//    }

//	public boolean successfulJump(Location location) {
//		boolean successful = true;
//		if(location.getDisplayChar() == "#".charAt(0)){
//			// WALL
//			this.jumpResult(80, 20);
//		}else if(location.getDisplayChar() == "+".charAt(0)){
//			// SPROUT
//			this.jumpResult(90, 10);
//		}else if(location.getDisplayChar() == "t".charAt(0)){
//			// SAPLING
//			this.jumpResult(80, 20);
//		}else if(location.getDisplayChar() == "T".charAt(0)){
//			// MATURE
//			this.jumpResult(70, 30);
//		}
//		return successful;
//	}

    private boolean jumpResult(int chance) {
        return new Random().nextInt(100) < chance;
    }

    @Override
    public boolean successfulJump(char jumpOn) {
        boolean successful = false;
        if (jumpOn == "#".charAt(0)) {
            // WALL
            successful = this.jumpResult(80);
        } else if (jumpOn == "+".charAt(0)) {
            // SPROUT
            successful = this.jumpResult(0);
        } else if (jumpOn == "t".charAt(0)) {
            // SAPLING
            successful = this.jumpResult(80);
        } else if (jumpOn == "T".charAt(0)) {
            // MATURE
            successful = this.jumpResult(70);
        }
        return successful;
    }


//	public ActionList allowableActions(Actor actor, String direction, GameMap map){
//		ActionList actions = new ActionList();
//		if actio
//	}
}
