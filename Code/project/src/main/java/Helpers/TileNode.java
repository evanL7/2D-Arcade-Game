package Helpers;

import javax.swing.JButton;

public class TileNode {

    TileNode parent;
    int col;
    int row;
    int gCost; // distance between starting and current node
    int hCost; // distance between current and goal node
    int fCost; // total cost (G+H) of the node
    boolean start;
    boolean goal;
    boolean wall;
    boolean open;
    boolean checked;

    public TileNode(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public void setAsStart() {
        start = true;
    }

    public void setAsGoal() {
        goal = true;
    }

    public void setAsOpen() {
        open = true;
    }

    public void setAsChecked() {
        checked = true;
    }

    public void setWall() {
        wall = true;
    }

    public void resetNode() {
        checked = false;
        open = false;
        wall = false;
    }

}
