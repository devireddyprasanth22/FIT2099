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
		Location actorLocation = new Location(map, 0,0).map().locationOf(this);
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
//		if()
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	private boolean jumpResult(int chance, int damage){
		boolean successful = false;
		boolean probability = new Random().nextInt(100) < chance;
		if(probability){
			successful = true;
		}else{
			this.hurt(damage);
		}
		return successful;
	}

	@Override
	public boolean successfulJump(Location location) {
		boolean successful = true;
		if(location.getDisplayChar() == "#".charAt(0)){
			// WALL
			this.jumpResult(80, 20);
		}else if(location.getDisplayChar() == "+".charAt(0)){
			// SPROUT
			this.jumpResult(90, 10);
		}else if(location.getDisplayChar() == "t".charAt(0)){
			// SAPLING
			this.jumpResult(80, 20);
		}else if(location.getDisplayChar() == "T".charAt(0)){
			// MATURE
			this.jumpResult(70, 30);
		}
		return successful;
	}
}
