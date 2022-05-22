package game.allies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Speak;
import game.actions.RescueAction;

import java.util.Random;

public class PrincessPeach extends Actor implements Speak {

    private int turn = 0;
    public PrincessPeach() {
        super("princess peach", 'P', 100);
    }

    private final String[] monologueLines = new String[]
            {
                    "Dear Mario, I'll be waiting for you...",

                    "Never gonna give you up!",

                    "Release me, or I will kick you!",
            };

    public Actor getPlayerObj(Location currentLocation) {
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for (int j : xArr) {
            for (int k : yArr) {
                try {
                    Location newLocation = currentLocation.map().at(x + j, y + k);
                    if (newLocation.containsAnActor()) {
                        if (newLocation.getActor().getDisplayChar() == 'm' || newLocation.getActor().getDisplayChar() == 'M') {
                            return newLocation.getActor();
                        }
                    }
                } catch (Exception e) {
                    //do nothing
                }
            }
        }
        return null;
    }

    /**
     * currentLocation: location of Koopa on the map
     * returns: true or false if Mario is in attack range or not
     */
    public boolean isPlayerInRange(Location currentLocation) {
        int x = currentLocation.x();
        int y = currentLocation.y();
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        for (int k : xArr) {
            for (int i : yArr) {
                try {
                    Location newLocation = currentLocation.map().at(x + k, y + i);
                    if (newLocation.containsAnActor()) {
                        if (newLocation.getActor().getDisplayChar() == 'm' || newLocation.getActor().getDisplayChar() == 'M') {
                            return true;
                        }
                    }
                } catch (Exception e) {
                    //doing nothing
                }

            }
        }
        return false;
    }

    @Override
    public void speak(){
        int max = monologueLines.length-1;
        int min = 0;
        if(turn % 2 == 0){
            Random random = new Random();
            int randomNum = random.nextInt(max + 1 - min) + min;
            System.out.println(monologueLines[randomNum]);
        }
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
//        Location myLocation = map.locationOf(this);
//
//        if (this.isPlayerInRange(myLocation)) {
//            Player player = (Player) this.getPlayerObj(myLocation);
////            Move rescue action here
//        }
//        actions.add(new RescueAction());
        turn++;
        speak();
        return new DoNothingAction();
    }
}
