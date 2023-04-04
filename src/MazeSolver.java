/**
 * Solves the given maze using DFS or BFS
 * @author Ms. Namasivayam
 * @version 03/10/2023
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MazeSolver {
    private Maze maze;
    private Stack<MazeCell> DFS = new Stack<MazeCell>();
    private Queue<MazeCell> BFS = new LinkedList<MazeCell>();

    public MazeSolver() {
        this.maze = null;
    }

    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    /**
     * Starting from the end cell, backtracks through
     * the parents to determine the solution
     * @return An arraylist of MazeCells to visit in order
     */
    public ArrayList<MazeCell> getSolution() {
        ArrayList<MazeCell> arr = new ArrayList<MazeCell>();
        checkParent(maze.getEndCell(), arr);
        return arr;
    }

    public void checkParent(MazeCell cell, ArrayList<MazeCell> arr){
        if (cell.equals(maze.getStartCell())) {
            arr.add(cell);
            return;
        }
        checkParent(cell.getParent(), arr);
        arr.add(cell);
    }

    /**
     * Performs a Depth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */
    public ArrayList<MazeCell> solveMazeDFS() {
        return DFSHelper(maze.getStartCell());
    }

    public ArrayList<MazeCell> DFSHelper(MazeCell m) {
        if(m.equals(maze.getEndCell())){
            return this.getSolution();
        }
        if(m.getRow()-1 >= 0 &&maze.isValidCell(m.getRow()-1,m.getCol())){
            DFS.push(maze.getCell(m.getRow()-1,m.getCol()));
            maze.getCell(m.getRow()-1,m.getCol()).setParent(m);
            maze.getCell(m.getRow()-1,m.getCol()).setExplored(true);
        }
        if(m.getCol()+1 < maze.getNumCols() && maze.isValidCell(m.getRow(),m.getCol()+1)){
            DFS.push(maze.getCell(m.getRow(),m.getCol()+1));
            maze.getCell(m.getRow(),m.getCol()+1).setParent(m);
            maze.getCell(m.getRow(),m.getCol()+1).setExplored(true);
        }
        if (m.getRow()+1 < maze.getNumRows() && maze.isValidCell(m.getRow()+1,m.getCol())){
            DFS.push(maze.getCell(m.getRow()+1,m.getCol()));
            maze.getCell(m.getRow()+1,m.getCol()).setParent(m);
            maze.getCell(m.getRow()+1,m.getCol()).setExplored(true);
        }
        if(m.getCol()-1 >= 0 && maze.isValidCell(m.getRow(),m.getCol()-1)){
            DFS.push(maze.getCell(m.getRow(),m.getCol()-1));
            maze.getCell(m.getRow(),m.getCol()-1).setParent(m);
            maze.getCell(m.getRow(),m.getCol()-1).setExplored(true);
        }

        return DFSHelper(DFS.pop());
    }

    /**
     * Performs a Breadth-First Search to solve the Maze
     * @return An ArrayList of MazeCells in order from the start to end cell
     */

    public ArrayList<MazeCell> solveMazeBFS() {
        return BFSHelper(maze.getStartCell());
    }

    public ArrayList<MazeCell> BFSHelper(MazeCell m) {
        if(m.equals(maze.getEndCell())){
            return this.getSolution();
        }




        if(m.getRow()-1 >= 0 &&maze.isValidCell(m.getRow()-1,m.getCol())){
            BFS.add(maze.getCell(m.getRow()-1,m.getCol()));
            maze.getCell(m.getRow()-1,m.getCol()).setParent(m);
            maze.getCell(m.getRow()-1,m.getCol()).setExplored(true);
        }
        if(m.getCol()+1 < maze.getNumCols() && maze.isValidCell(m.getRow(),m.getCol()+1)){
            BFS.add(maze.getCell(m.getRow(),m.getCol()+1));
            maze.getCell(m.getRow(),m.getCol()+1).setParent(m);
            maze.getCell(m.getRow(),m.getCol()+1).setExplored(true);
        }
        if (m.getRow()+1 < maze.getNumRows() && maze.isValidCell(m.getRow()+1,m.getCol())){
            BFS.add(maze.getCell(m.getRow()+1,m.getCol()));
            maze.getCell(m.getRow()+1,m.getCol()).setParent(m);
            maze.getCell(m.getRow()+1,m.getCol()).setExplored(true);
        }
        if(m.getCol()-1 >= 0 && maze.isValidCell(m.getRow(),m.getCol()-1)){
            BFS.add(maze.getCell(m.getRow(),m.getCol()-1));
            maze.getCell(m.getRow(),m.getCol()-1).setParent(m);
            maze.getCell(m.getRow(),m.getCol()-1).setExplored(true);
        }

        return BFSHelper(BFS.remove());
    }

    public static void main(String[] args) {
        // Create the Maze to be solved
        Maze maze = new Maze("Resources/maze3.txt");

        // Create the MazeSolver object and give it the maze
        MazeSolver ms = new MazeSolver();
        ms.setMaze(maze);

        // Solve the maze using DFS and print the solution
        ArrayList<MazeCell> sol = ms.solveMazeDFS();
        maze.printSolution(sol);

        // Reset the maze
        maze.reset();

        // Solve the maze using BFS and print the solution
        sol = ms.solveMazeBFS();
        maze.printSolution(sol);
    }
}
