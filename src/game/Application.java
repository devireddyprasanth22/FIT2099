package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.groundItems.Dirt;
import game.groundItems.Floor;
import game.groundItems.Wall;
import game.magicalItems.PowerStar;
import game.magicalItems.SuperMushroom;
import game.tree.Sprout;


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

        world.addGameMap(gameMap);
        Actor mario = new Player("Player", 'm', 100);
        world.addPlayer(mario, gameMap.at(42, 10));
        // adding a toad character to the game
        gameMap.at( 45, 10).addActor(new Toad());
        // adding power star and super mushroom to the game
        gameMap.at(42, 15).addItem(new PowerStar());
        gameMap.at(20, 4).addItem(new SuperMushroom());
         // adding power star and super mushroom to inventory
        mario.addItemToInventory(new SuperMushroom());
        mario.addItemToInventory(new PowerStar());
        // runs the game
        world.run();
    }

}








