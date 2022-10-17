package AStar;

import java.util.List;
import java.util.PriorityQueue;

public class AStar {

    private PuzzleNode startingPuzzle;

    private Heuristic<PuzzleNode> heuristic;

    private PriorityQueue<PuzzleNode> closedList = new PriorityQueue<>();
    private PriorityQueue<PuzzleNode> openList = new PriorityQueue<>();

    private Integer stepsRequired = 0;


    public AStar(PuzzleNode startingPuzzle, Heuristic<PuzzleNode> heuristic) {
        this.startingPuzzle = startingPuzzle;
        this.heuristic = heuristic;
    }


    public PuzzleNode calculatePath(PuzzleNode goalNode) {
        if(!checkIfSolvable(startingPuzzle)){
            throw new IllegalArgumentException("Invalid starting Puzzle, not solvable!");
        }


        startingPuzzle.setG(0);
        startingPuzzle.setF(startingPuzzle.getG() + heuristic.getH(startingPuzzle));
        startingPuzzle.setNeighbors();
        openList.add(startingPuzzle);

        while (!openList.isEmpty()) {
            PuzzleNode n = openList.peek();

            if (n.equals(goalNode)) {
                return n;
            }

            for (PuzzleNode neighbor : n.getNeighbors()) {
                stepsRequired += 1;
                int weight = n.getG() + neighbor.getDepth();

                if (!openList.contains(neighbor) && !closedList.contains(neighbor)) {
                    neighbor.setG(weight);
                    neighbor.setF(neighbor.getG() + heuristic.getH(neighbor));

                    neighbor.setNeighbors();
                    openList.add(neighbor);

                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public boolean checkIfSolvable(PuzzleNode puzzle) {
        int inversionCount = 0;
        List<Integer> puzzleList = puzzle.getPuzzle();
        for (int i = 0; i < 9-1; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (puzzleList.get(i) > puzzleList.get(j)) {
                    inversionCount++;
                }
            }
        }
        return inversionCount % 2 == 0;
    }

    public Integer getStepsRequired() {
        return stepsRequired;
    }

}
