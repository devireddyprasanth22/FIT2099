package game.tree;

public class Mature extends Tree {
    public Mature() {
        super('T');
    }

    @Override
    public TreeState checkState() {
        if (this.chance(20)) {
            return TreeState.DIE;
        }
        if (this.getTurn() % 5 == 0) {
            return TreeState.GROW;
        } else if (this.chance(15)) {
            return TreeState.SPAWN;
        } else {
            return TreeState.UNKNOWN_ERROR;
        }
    }
}