package game;

import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.tree.Sprout;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainWorld extends World {
    private HashMap<int[], Sprout> treeList = new HashMap<int[], Sprout>();

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public MainWorld(Display display) {
        super(display);
    }

    public void setTreeList(List<String> map){
        treeList = setTrees(map);
    }

    public HashMap<int[], Sprout> getTreeList() {
        return treeList;
    }


    public void run(){
        if (player == null)
            throw new IllegalStateException();

        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        // This loop is basically the whole game
        while (stillRunning()) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);
//            System.out.println("Helllo Woooorlrld!!");
            treeList.forEach((key, value) -> System.out.println(Arrays.toString(key) + " " + value));
//            System.out.println(treeList);
            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (stillRunning())
                    processActorTurn(actor);
            }

            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                gameMap.tick();
            }
        }
        display.println(endGameMessage());
    }

    public static HashMap<int[], Sprout> setTrees(List<String> map) {
        HashMap<int[], Sprout> treeHashMap = new HashMap<>();
        for (int i = 0; i < map.size(); i++) {
            for(int j = 0; j < map.get(i).length(); j++){
                if (map.get(i).charAt(j) == "+".charAt(0)){
                    int[] location = new int[]{i, j};
                    treeHashMap.put(location, new Sprout());
                }
            }
        }
        return treeHashMap;
    }
}
