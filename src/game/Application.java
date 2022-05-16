package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
//import game.actions.TeleportAction;
import game.allies.PrincessPeach;
import game.allies.Toad;
import game.enemies.Bowser;
import game.groundItems.Dirt;
import game.groundItems.Floor;
import game.groundItems.Lava;
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
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Lava());

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
        List<String> lavaMap = Arrays.asList(
                "..........................................##..........+......",
                "............+............+.......L..........#................",
                "......L.....................................#................",
                "....................L........................##.......L......",
                "...............................................#.............",
                "......................................L.........#............",
                ".................+................................#..........",
                "........................L........................##..........",
                "................................................##...........",
                ".........+..............................+#____####......L....",
                ".......L...............................+#_____###++..........",
                ".......................................+#______###...........",
                "...................L....................+#_____###...........",
                "........................+........................##..........");
        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap lavaZone = new GameMap(groundFactory, lavaMap);

        world.addGameMap(gameMap);
        world.addGameMap(lavaZone);

        Player mario = new Player("Player", 'm', 100);
        mario.accessToMaps(gameMap, lavaZone);
        world.addPlayer(mario, gameMap.at(42, 10));

        WarpPipe warpPipe = new WarpPipe();
        Location[] warpPipeLocations = {
                new Location(gameMap, 1, 1),
                new Location(gameMap, 10, 4),
                new Location(gameMap, 5, 18),
                new Location(gameMap, 55, 6),
                new Location(gameMap, 8,15)
        };

        // Adding items and actors to main map
        for(Location loc: warpPipeLocations){
            gameMap.at(loc.x(), loc.y()).addItem(warpPipe);
        }
        gameMap.at(45, 10).addActor(new Toad()); // add toad
        gameMap.at(42, 15).addItem(new PowerStar()); // add power star
        gameMap.at(20, 4).addItem(new SuperMushroom()); // add super mushroom

        // Adding items and actors to lazazone map
        PrincessPeach princess = new PrincessPeach();
        lavaZone.at(0, 0).addItem(warpPipe);
        lavaZone.at(20, 6).addActor(princess);
        lavaZone.at(19, 5).addActor(new Bowser());


        // Adding items to inventory
        // adding power star and super mushroom to inventory
        mario.addItemToInventory(new SuperMushroom());
        mario.addItemToInventory(new PowerStar());
        mario.addItemToInventory(new Wrench());


        // runs the game
        world.run();
    }

}







