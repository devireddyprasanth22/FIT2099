package game.groundItems;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.Status;
import game.enemies.Enemy;

public class Fire extends Ground {

    private int turn = 0;

    /**
     * Constructor for creating Fire
     */
    public Fire() {
        super('v');
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn() {
        this.turn += 1;
    }

    @Override
    public void tick(Location location) {
        int damage = 20;
        this.incrementTurn();
        if (location.containsAnActor() && location.getActor().hasCapability(Status.ENEMY)) {
            Enemy enemy = (Enemy) location.getActor();
            enemy.hurt(damage);
            System.out.println("enemy hp::" + enemy.getHp());
            if (!enemy.isConscious()) {
                location.map().removeActor(enemy);
                System.out.println(enemy + " is killed by fire");
            }
        } else if (location.containsAnActor() && location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
            Player player = (Player) location.getActor();
            player.hurt(damage);
            if (!player.isConscious()) {
                location.map().removeActor(player);
                System.out.println(player + " is killed by fire");
            }

        }
        if (this.getTurn() == 4) {
            location.setGround(new Dirt());
        }
    }
}
