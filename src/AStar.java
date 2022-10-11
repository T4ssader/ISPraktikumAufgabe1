import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;

public class AStar {

    private PuzzleNode startingPuzzle;

    private Heuristic<PuzzleNode> heuristic;

    private PriorityQueue<PuzzleNode> closedList = new PriorityQueue<>();
    private PriorityQueue<PuzzleNode> openList = new PriorityQueue<>();

    private PuzzleNode puzzleGoal;


    public AStar(PuzzleNode startingPuzzle, Heuristic<PuzzleNode> heuristic) {
        this.startingPuzzle = startingPuzzle;
        this.heuristic = heuristic;
    }


    public PuzzleNode calculatePath(PuzzleNode startingNode, PuzzleNode goalNode) {
        startingNode.setG(0);
        startingNode.setF(startingNode.getG() + heuristic.getH(startingNode));
        startingNode.setNeighbors();
        openList.add(startingNode);

        int x = 0;
        ManhattenHeuristic heuristic2 = new ManhattenHeuristic();
        while (!openList.isEmpty()) {
            PuzzleNode n = openList.peek();
            if (n.equals(goalNode)) {
                return n;
            }

            for (PuzzleNode neighbor : n.getNeighbors()) {
                int weight = n.getG() + neighbor.getDepth();

                if (!openList.contains(neighbor) && !closedList.contains(neighbor)) {
                    neighbor.setG(weight);
                    neighbor.setF(neighbor.getG() + heuristic.getH(neighbor));

                    neighbor.setNeighbors();
                    openList.add(neighbor);

                } else {

                    if (weight < neighbor.getG()) {
                        neighbor.setG(weight);
                        neighbor.setF(neighbor.getG() + heuristic.getH(neighbor));
//                        if (closedList.contains(neighbor)) {
//                            closedList.remove(neighbor);
//                            openList.add(neighbor);
//                        }
                    }
                }
                System.out.println( heuristic2.getH(neighbor));
            }

            openList.remove(n);
            closedList.add(n);
            x +=1;


            System.out.println("RUN: " + x +"\nOpen: "+ openList+ "\nClosed: " + closedList+"\n\n\n");
        }
        return null;
    }




    public static void main(String[] args) {
        List<Integer> goal = new ArrayList<>();
        goal.add(1);
        goal.add(2);
        goal.add(3);
        goal.add(8);
        goal.add(0);
        goal.add(4);
        goal.add(7);
        goal.add(6);
        goal.add(5);
        PuzzleNode goalPuzzle = new PuzzleNode(goal,null);

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(0);
        list.add(3);
        list.add(1);
        list.add(8);
        list.add(4);
        list.add(7);
        list.add(6);
        list.add(5);

        PuzzleNode puzzle = new PuzzleNode(list,null);


        ManhattenHeuristic heuristic1 = new ManhattenHeuristic();


        AStar aStar = new AStar(puzzle,heuristic1);
        System.out.println(aStar.calculatePath(puzzle,goalPuzzle));


    }
}
