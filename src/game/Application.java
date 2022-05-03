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
import game.magicalItems.PowerStar;
import game.magicalItems.SuperMushroom;
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
//        HashMap<int[], Sprout> treeHashMap = Application.setTrees(map);
        world.addGameMap(gameMap);
        Actor mario = new Player("Player", 'm', 100);
        mario.addItemToInventory(new Coin());
        world.addPlayer(mario, gameMap.at(42, 10));

        gameMap.at( 45, 10).addActor(new Toad());

        // FIXME: the Goomba should be generated from the Tree
        gameMap.at(42, 15).addItem(new PowerStar());
        gameMap.at(20, 4).addItem(new SuperMushroom());
//        world.setTreeList(map);
//        treeHashMap.forEach((key, value) -> System.out.println(Arrays.toString(key) + " " + value));
        world.run();
    }

}








