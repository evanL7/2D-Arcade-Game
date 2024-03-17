package Helpers;

import java.util.ArrayList;

import Display.Game;
import Display.GamePanel;
import Gamestates.Playing;

public class PathFinder {

    GamePanel gp;
    Playing playing;
    TileNode nodeMap[][];
    TileNode startNode, goalNode, currentNode;
    ArrayList<TileNode> openList = new ArrayList<>();
    public ArrayList<TileNode> pathList = new ArrayList<>();
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp, Playing playing) {
        this.gp = gp;
        this.playing = playing;
        setNodes();

    }

    public void setNodes() {
        nodeMap = new TileNode[Game.maxScreenRow][Game.maxScreenCol];

        // place nodes
        int col = 0;
        int row = 0;
        while (col < Game.maxScreenCol && row < Game.maxScreenRow) {
            nodeMap[row][col] = new TileNode(col, row);

            col++;
            if (col == Game.maxScreenCol) {
                col = 0;
                row++;
            }

        }
    }

    public void resetNodes() {
        int col = 0;
        int row = 0;
        while (col < Game.maxScreenCol && row < Game.maxScreenRow) {
            nodeMap[row][col].resetNode();
            col++;
            if (col == Game.maxScreenCol) {
                col = 0;
                row++;
            }

        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        // set start and goal node
        startNode = nodeMap[startRow][startCol];
        currentNode = startNode;
        goalNode = nodeMap[goalRow][goalCol];
        openList.add(currentNode);

        int col = 0;
        int row = 0;
        int tileNum;

        while (col < Game.maxScreenCol && row < Game.maxScreenRow) {

            tileNum = playing.tileManager.mapTileNum[row][col];

            // check for walls
            if (playing.tileManager.tile[tileNum].collision == true) {
                nodeMap[row][col].setWall();
            }

            // CHECK FOR INTERACTIVE TILES (TRAPS AND REWARDS)
            // TO DO ONCE DONE (11:27)

            getCost(nodeMap[row][col]);

            col++;
            if (col == Game.maxScreenCol) {
                col = 0;
                row++;
            }

        }

    }

    private void getCost(TileNode node) { // if having issues, test the col and row for x and y
        // G COST
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;

        // H COST
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;

        // F COST
        node.fCost = node.gCost + node.hCost;

    }

    public boolean search() {
        while (goalReached == false && step < 500) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            openList.remove(currentNode);

            // CHECK THESE ROW AND COL VALS
            // open the above node
            if (col - 1 >= 0) {
                openNode(nodeMap[row][col - 1]);
            }
            // open the left node
            if (row - 1 >= 0) {
                openNode(nodeMap[row - 1][col]);
            }
            // open the below node
            if (col + 1 < Game.maxScreenCol) {
                openNode(nodeMap[row][col + 1]);
            }
            // open the right node
            if (row + 1 < Game.maxScreenRow) {
                openNode(nodeMap[row + 1][col]);
            }

            // FIND THE BEST NODE
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < openList.size(); i++) { // check for the best F cost
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if (openList.get(i).fCost == bestNodefCost) { // the F cost is equal, check G cost
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }

            if (openList.size() == 0) { // no nodes in openList
                break;
            }

            // After the loop finishes, we get the best Node which is our next step
            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trackPath();
            }

            step++;
        }
        return goalReached;
    }

    private void openNode(TileNode node) {
        if (node.open == false && node.checked == false && node.wall == false) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    private void trackPath() {
        // backtrack to find the best path
        TileNode current = goalNode;

        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }
}
