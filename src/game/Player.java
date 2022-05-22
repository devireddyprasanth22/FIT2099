package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.ConsumeItemAction;
import game.actions.JumpActorAction;
import game.actions.RescueAction;
import game.actions.TeleportAction;
import game.allies.Toad;
import game.groundItems.Dirt;
import game.magicalItems.*;
import game.resetAction.Reset;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Actor {
    /**
     * a menu object
     */
    private final Menu menu = new Menu();

    /**
     *
     */
    private int turn = -1;

    private int hpBonusIncrease = 0;


    private boolean usingSuperMushroom = false;

    private boolean hasReset = false;
    private ArrayList<GameMap> maps = new ArrayList<>();
    private Location locationOnMainMap;


    /**
     * setHasReset takes a boolean value and sets the hasReset attribute if true
     *
     * @param hasReset
     */
    public void setHasReset(boolean hasReset) {
        this.hasReset = hasReset;
    }

    /**
     * A getter for turn
     *
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * method that increments turn
     */
    public void incrementTurn() {
        this.turn += 1;
    }


    public void setTurn(int value) {
        this.turn = value;
    }

    private final Item[] consumableItems = new Item[]{
            new PowerStar(), new PowerStar(), new FireFlower(), new SuperMushroom()
    };

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
        this.addCapability(Status.TELEPORT_TO_LAVAZONE);
    }

    /**
     * Determines if toad is in range of player to purchase items
     *
     * @param map current map both player and toad are on
     * @returns true or false depending on if toad is in range of player
     */
    public boolean isPlayerInRange(GameMap map) {
        int[] xArr = {-1, 0, 1};
        int[] yArr = {-1, 0, 1};

        Location currentLocation = map.locationOf(this);

        int x = currentLocation.x();
        int y = currentLocation.y();
        // a for loop that goes through the x and y location
        for (int i = 0; i < xArr.length; i++) {
            for (int j = 0; j < yArr.length; j++) {
                try {
                    Location newLocation = currentLocation.map().at(x + xArr[i], y + yArr[j]);
                    if (newLocation.containsAnActor()) {
                        if (newLocation.getActor().getDisplayChar() == 'O') {
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

    /**
     * Does all necessary functions for a player's turn
     *
     * @param actions A list of actions the player can do
     * @param lastAction Last action player did
     * @param map Current map player is on
     * @param display display object
     *
     * @return Action the player is doing this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location actorLocation = new Location(map, 0, 0).map().locationOf(this);
        Toad toad = (Toad) map.getActorAt(new Location(map, 45, 10));
        this.deactivateMagicalItem(Status.FIRE_ATTACK, 20);
        if (!hasReset) {
            actions.add(new Reset(this, map));
        }
        // inflict 15 damage to player per turn if player on Lava
        if (actorLocation.getGround().hasCapability(Status.LAVA)) {
            this.hurt(15);
        }
        if (isPlayerInRange(map)) {
            //add power star
            actions.add(new Action() {
                @Override
                public String execute(Actor actor, GameMap map) {
                    //get wallet balance
                    int walletBalance = 0;
                    for (Item item : actor.getInventory()) {
                        if (item.toString().equals("Coin")) {
                            walletBalance += 200;
                        }
                    }
                    //check if player has enough money to buy item
                    if (walletBalance < 600) {
                        return "Mario doesn't have enough money! Collect more coins!";
                    } else {
                        actor.addItemToInventory(new PowerStar());
                        return "Mario bought a Power Star for $600!";
                    }
                }

                @Override
                public String menuDescription(Actor actor) {
                    return "Mario buys Power Star ($600)";
                }
            });
            //Add super mushroom
            actions.add(new Action() {
                @Override
                public String execute(Actor actor, GameMap map) {
                    //get wallet balance
                    int walletBalance = 0;
                    for (Item item : actor.getInventory()) {
                        if (item.toString().equals("Coin")) {
                            walletBalance += 200;
                        }
                    }
                    //check if player has enough money to buy item
                    if (walletBalance < 400) {
                        return "Mario doesn't have enough money! Collect more coins!";
                    } else {
                        actor.addItemToInventory(new SuperMushroom());
                        return "Mario bought a Super Mushroom for $400!";
                    }
                }

                @Override
                public String menuDescription(Actor actor) {
                    return "Mario buys Super Mushroom ($400)";
                }
            });
            actions.add(new Action() {
                @Override
                public String execute(Actor actor, GameMap map) {
                    //get wallet balance
                    int walletBalance = 0;
                    for (Item item : actor.getInventory()) {
                        if (item.toString().equals("Coin")) {
                            walletBalance += 200;
                        }
                    }
                    //check if player has enough money to buy item
                    if (walletBalance < 200) {
                        return "Mario doesn't have enough money! Collect more coins!";
                    } else {
                        actor.addItemToInventory(new Wrench());
                        actor.addCapability(Status.WRENCH);
                        return "Mario bought a Wrench for $200!";
                    }
                }

                @Override
                public String menuDescription(Actor actor) {
                    return "Mario buys Wrench ($200)";
                }
            });
        }


        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
        // return/print the console menu;

        // PLayer picks up coin -> it is added to the inventory
        if (actorLocation.getDisplayChar() == '$') {
            this.addItemToInventory(new Coin());
            actorLocation.setGround(new Dirt());
        }

        System.out.println(new PowerStar().toString());

        // Consume Items
//        boolean hasSuperMushroom = (boolean) this.inventoryContains("Super Mushroom").get(1);
//        boolean hasPowerStar = (boolean) this.inventoryContains("Power Star").get(1);
//        boolean hasFireFlower = (boolean) this.inventoryContains("Fire Flower").get(1);
//
//        if (hasSuperMushroom || actorLocation.getDisplayChar() == '^') {
//            actions.add(new ConsumeItemAction("SuperMushroom"));
//        }
//        if (hasPowerStar || actorLocation.getDisplayChar() == '*') {
//            actions.add(new ConsumeItemAction("PowerStar"));
//        }
//        if (hasFireFlower || actorLocation.getDisplayChar() == 'f') {
//            actions.add(new ConsumeItemAction("FireFlower"));
//        }

        this.getInventory().forEach(item -> {
            boolean isConsumableItem = false;
            for(Item consumable: consumableItems){
                if (item.toString() == consumable.toString()) {
                    isConsumableItem = true;
                    break;
                }
            }
            if (isConsumableItem){
                actions.add(new ConsumeItemAction(item.toString()));
            }
        });


        System.out.print("POWERSTAR IN USE :::" + this.hasCapability(Status.POWER_STAR));

        if (this.hasCapability(Status.SUPER_MUSHROOM)) {
            this.setDisplayChar('M');
        } else {
            this.setDisplayChar('m');
        }

        Status[] possibleStatusForJump = new Status[]{
                Status.WALL, Status.SPROUT, Status.SAPLING, Status.MATURE, Status.TELEPORT
        };

        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();
            boolean canJump = false;
            for (Status s : possibleStatusForJump) {
                if (destination.getGround().hasCapability(s) ||
                        destination.getDisplayChar() == 'c') {
                    canJump = true;
                }
            }

            if (canJump) {
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

        System.out.println((boolean) inventoryContains("Bottle").get(1));
        System.out.println(map.locationOf(this).getGround().getDisplayChar() == 'H');
        //Health fountain
        boolean hasHealthFountain = false;
        boolean hasPowerFountain = false;
        for(Item item : map.locationOf(this).getItems()){
            if(item.hasCapability(Status.POWERFOUNTAIN)){
                hasPowerFountain = true;
                break;
            } else if(item.hasCapability((Status.HEALTHFOUNTAIN))){
                hasHealthFountain = true;
                break;
            }
        }
        if((boolean) inventoryContains("Bottle").get(1) && hasPowerFountain){
            actions.add(new Action() {
                @Override
                public String execute(Actor actor, GameMap map) {
                    for(Item item: actor.getInventory()){
                        if(item.hasCapability(Status.BOTTLE)){
                            Bottle i = (Bottle) item;
                            i.setPowerWaterInBottle();
                            break;
                        }
                    }
                    return "Player fills bottle with power water";
                }

                @Override
                public String menuDescription(Actor actor) {
                    return "Fill bottle with power water";
                }
            });
        }
        if((boolean) inventoryContains("Bottle").get(1) && hasHealthFountain){
            actions.add(new Action() {
                @Override
                public String execute(Actor actor, GameMap map) {
                    for(Item item: actor.getInventory()){
                        if(item.hasCapability(Status.BOTTLE)){
                            Bottle i = (Bottle) item;
                            i.setHealthWaterInBottle();
                            break;
                        }
                    }
                    return "Player fills bottle with health water";
                }

                @Override
                public String menuDescription(Actor actor) {
                    return "Fill bottle with health water";
                }
            });
        }

        //DRINKING FUNCTION HANDLING
        if((boolean) inventoryContains("Bottle").get(1)){
            Bottle invBottle = (Bottle) inventoryContains("Bottle").get(0);
            if(invBottle.getWaterInBottle() != Bottle.differentWaters.NO_WATER) {
                actions.add(new Action() {
                    @Override
                    public String execute(Actor actor, GameMap map) {
                        if (invBottle.getWaterInBottle() == Bottle.differentWaters.POWER_WATER) {
                            setHpBonusIncrease();
                            return "Player increased intrinsic weapon attack damage!";

                        } else if (invBottle.getWaterInBottle() == Bottle.differentWaters.HEALTH_WATER) {
                            heal(50);
                            return "Player healed!";
                        }

                        return null;
                    }
                    @Override
                    public String menuDescription(Actor actor) {
                        if(invBottle.getWaterInBottle() == Bottle.differentWaters.POWER_WATER){
                            return "Drink power water";

                        } else if(invBottle.getWaterInBottle() == Bottle.differentWaters.HEALTH_WATER){
                            return "Drink health water";
                        }
                        return null;
                    }
                });
            }
        }

        System.out.println(this.printHp());

//        TELEPORT
        List<Item> itemsAtGround = actorLocation.getItems();
        itemsAtGround.forEach(item -> {
            if (item.hasCapability(Status.TELEPORT)) {
                this.teleport(actions);
            }
        });

//        Rescue Princess
        for (Exit exit : map.locationOf(this).getExits()) {
            if (exit.getDestination().getDisplayChar() == 'P') {
                actions.add(new RescueAction());
            }
        }

        return menu.showMenu(this, actions, display);
    }


    @Override
    /**
     * getDisplayChar gets the char
     */
    public char getDisplayChar() {
        return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()) : super.getDisplayChar();
    }

    /**
     * deactivateSuperMushroom checks if player has super mushroom or fire flower and removes the capability if true
     */

    public void deactivateMagicalItem(Status status, int counter) {
        if (this.hasCapability(status) && counter == 0) {
            this.removeCapability(status);
        } else {
            this.incrementTurn();
            if (this.getTurn() == counter) {
                this.removeCapability(status);
                this.setTurn(0);
            }
        }
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        int damage = hpBonusIncrease * 15;
        return new IntrinsicWeapon(damage, "punches");
    }

    public void setHpBonusIncrease(){
        hpBonusIncrease++;
    }

    public void teleport(ActionList actions) {
//        WarpPipe warpPipe = new WarpPipe();
        if (this.hasCapability(Status.TELEPORT_TO_LAVAZONE)) {
//            Teleport to lavazone
            locationOnMainMap = maps.get(0).locationOf(this);
            System.out.println(locationOnMainMap.x() + " " + locationOnMainMap.y());
            actions.add(new TeleportAction(maps.get(1).at(0, 0), "To LavaZone"));
        } else if (this.hasCapability(Status.TELEPORT_TO_MAINMAP)) {
//            Teleport to main map
            actions.add((new TeleportAction(maps.get(0).at(locationOnMainMap.x(), locationOnMainMap.y()), "To MainMap")));
        } else {
//            Raise map not found
        }
    }

    public ArrayList<Object> inventoryContains(String objectName) {
        ArrayList<Object> val = new ArrayList<Object>();
        for (int i = 0; i < this.getInventory().size(); i++) {
            if (this.getInventory().get(i).toString().equals(objectName)) {
                val.add(this.getInventory().get(i));
                val.add(true);
                return val;
            }
        }
        val.add(null);
        val.add(false);
        return val;
    }

    public void accessToMaps(GameMap... mapParams) {
        maps.addAll(List.of(mapParams));
    }
}
