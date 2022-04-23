package game.tree;

public class Sprout extends Tree {
    /**
     * Constructor.
     *
     */
    public Sprout() {
        super('+');
    }

    @Override
    public TreeState checkState() {
        if (this.getTurn() == 10){
            return TreeState.GROW;
        }
        else if(this.chance(10)){
            return TreeState.SPAWN;
        }
        else{
            return TreeState.UNKNOWN_ERROR;
        }
    }
}