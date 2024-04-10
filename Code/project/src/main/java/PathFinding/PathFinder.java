package PathFinding;

import java.util.ArrayList;

import Display.Game;
import Display.GamePanel;
import Gamestates.Playing;

/**
 * The PathFinder class provides methods for finding the shortest path between
 * two points on the game map.
 * It uses the A* algorithm to calculate the path.
 */
public class PathFinder {

    GamePanel gp;
    Playing playing;
    TileNode nodeMap[][];
    TileNode startNode, goalNode, currentNode;
    ArrayList<TileNode> openList = new ArrayList<>();
    public ArrayList<TileNode> pathList = new ArrayList<>();
    boolean goalReached = false;
    int step = 0;

    /**
     * Constructs a PathFinder object with the specified playing state.
     * 
     * @param playing The current playing state of the game.
     */
    public PathFinder(Playing playing) {
        this.playing = playing;
        initiateNodes();

    }

    /**
     * Initializes the nodes of the game map.
     */
    public void initiateNodes() {
        nodeMap = new TileNode[Game.maxWorldRow][Game.maxWorldCol];

        // place nodes
        int col = 0;
        int row = 0;
        while (col < Game.maxWorldCol && row < Game.maxWorldRow) {
            nodeMap[col][row] = new TileNode(col, row);

            col++;
            if (col == Game.maxWorldCol) {
                col = 0;
                row++;
            }

        }
    }

    /**
     * Resets the nodes and other necessary variables.
     */
    public void resetNodes() {
        int col = 0;
        int row = 0;
        while (col < Game.maxWorldCol && row < Game.maxWorldRow) {
            nodeMap[col][row].resetNode();
            col++;
            if (col == Game.maxWorldCol) {
                col = 0;
                row++;
            }

        }
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    /**
     * Sets the start and goal nodes for the pathfinding algorithm.
     * 
     * @param startCol The column index of the starting position.
     * @param startRow The row index of the starting position.
     * @param goalCol  The column index of the goal position.
     * @param goalRow  The row index of the goal position.
     */
    public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
        resetNodes();

        // set start and goal node
        startNode = nodeMap[startCol][startRow];
        currentNode = startNode;
        goalNode = nodeMap[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;
        int tileNum;

        while (col < Game.maxWorldCol && row < Game.maxWorldRow) {

            tileNum = playing.tileManager.mapTileNum[col][row];

            // check for walls
            if (playing.tileManager.tile[tileNum].collision == true) {
                nodeMap[col][row].setWall();
            }

            // CHECK FOR INTERACTIVE TILES (TRAPS AND REWARDS)
            // TO DO ONCE DONE (11:27)

            getCost(nodeMap[col][row]);

            col++;
            if (col == Game.maxWorldCol) {
                col = 0;
                row++;
            }

        }

    }

    /**
     * Calculates the cost (G, H, and F) for the specified node.
     * 
     * @param node The node for which to calculate the cost.
     */
    private void getCost(TileNode node) { // if having issues, test the col and row for x and y
        // G COST
        int yDistance = Math.abs(node.row - startNode.row);
        int xDistance = Math.abs(node.col - startNode.col);
        node.gCost = xDistance + yDistance;

        // H COST
        yDistance = Math.abs(node.row - goalNode.row);
        xDistance = Math.abs(node.col - goalNode.col);
        node.hCost = xDistance + yDistance;

        // F COST
        node.fCost = node.gCost + node.hCost;

    }

    /**
     * Searches for the shortest path using the A* algorithm.
     * 
     * @return true if the goal is reached and a path is found; otherwise, false.
     */
    public boolean search() {
        while (goalReached == false && step < 500) {
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            openList.remove(currentNode);

            // CHECK THESE ROW AND COL VALS
            // open the above node
            if (col - 1 >= 0) {
                openNode(nodeMap[col - 1][row]);
            }
            // open the left node
            if (row - 1 >= 0) {
                openNode(nodeMap[col][row - 1]);
            }
            // open the below node
            if (col + 1 < Game.maxWorldCol) {
                openNode(nodeMap[col + 1][row]);
            }
            // open the right node
            if (row + 1 < Game.maxWorldRow) {
                openNode(nodeMap[col][row + 1]);
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

    /**
     * Opens the specified node for consideration in the pathfinding process.
     * 
     * @param node The node to be opened.
     */
    private void openNode(TileNode node) {
        if (node.open == false && node.checked == false && node.wall == false) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }

    /**
     * Backtracks from the goal node to determine the shortest path.
     */
    private void trackPath() {
        // backtrack to find the best path
        TileNode current = goalNode;

        while (current != startNode) {
            pathList.add(0, current);
            current = current.parent;
        }
    }
}
