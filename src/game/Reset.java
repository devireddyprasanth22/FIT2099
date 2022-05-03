package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public class Reset{

    private final Actor actor;
    private final GameMap map;

    public Reset(Actor actor, GameMap map) {
        this.actor = actor;
        this.map = map;
    }

    public void execute(){
        this.actor.resetMaxHp(100);
        for(int i = 0; i< this.map.getXRange().max(); i++){
            for(int j = 0; j < this.map.getYRange().max(); j++){
                if(this.map.isAnActorAt(new Location(this.map, i, j))){
                    Actor currActor = this.map.getActorAt(new Location(this.map, i, j))
                    if(currActor.getDisplayChar() != 'm' || currActor.getDisplayChar() != 'M'){
//                        KILL ACTOR
                        currActor.allowableActions().add()
                        currActor.hurt(20);
                    }
                }
            }
        }

    }
}
