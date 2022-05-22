package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.behaviour.Behaviour;
import game.Player;
import game.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Koopa extends AbstractKoopa {
    /**
     * Constructor for Koopa
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.addCapability(Status.ENEMY);
        System.out.println("Koopa HP: " + this.getHp());
    }
}
