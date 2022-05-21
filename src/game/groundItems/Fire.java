package game.groundItems;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.enemies.Enemy;

public class Fire extends Ground {

    private int turn = 0;

    /**
     * Constructor for creating Fire
     */
    /**
     * @param status status of type status
     */
    public Fire() {
        super('v');
        this.addCapability(Status.FIRE);
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn() {
        this.turn += 1;
    }

    @Override
    public void tick(Location location) {
        this.incrementTurn();
        if (location.containsAnActor()) {
            Enemy enemy = (Enemy) location.getActor();
            enemy.hurt(20);
            System.out.println("enemy hp::" + enemy.getHp());
            if(!enemy.isConscious())
            {
                location.map().removeActor(enemy);
                System.out.println(enemy + " is killed by fire");
            }
        }
        if (this.getTurn() == 4) {
            location.setGround(new Dirt());
        }
    }
}
