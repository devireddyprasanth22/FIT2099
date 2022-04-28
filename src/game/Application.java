package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.tree.Sprout;
import game.tree.Tree;


/**
 * The main class for the Mario World game.
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

        List<String> map = Arrays.asList(
                "..........................................##..........+.........................",
                "............+............+..................#...................................",
                "............................................#...................................",
                ".............................................##......................+..........",
                "...............................................#................................",
                "................................................#...............................",
                ".................+................................#.............................",
                ".................................................##.............................",
                "................................................##..............................",
                ".........+..............................+#____####.................+............",
                ".......................................+#_____###++.............................",
                ".......................................+#______###..............................",
                "........................................+#_____###..............................",
                "........................+........................##.............+...............",
                "...................................................#............................",
                "....................................................#...........................",
                "...................+.................................#..........................",
                "......................................................#.........................",
                ".......................................................##.......................");
        GameMap gameMap = new GameMap(groundFactory, map);
        HashMap<int[], Sprout> treeHashMap = Application.setTrees(map);
        world.addGameMap(gameMap);
        Actor mario = new Player("Player", 'm', 100);
        world.addPlayer(mario, gameMap.at(42, 10));

        // FIXME: the Goomba should be generated from the Tree
        gameMap.at(35, 10).addActor(new Goomba());

        treeHashMap.forEach((key, value) -> System.out.println(Arrays.toString(key) + " " + value));
        world.run();
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








