package AStar;

import java.util.HashMap;
import java.util.List;

public class IncorrectSimpleHeuristic implements Heuristic<PuzzleNode>{

    HashMap<Integer,Integer> goalIndex = new HashMap<>();

    public IncorrectSimpleHeuristic() {
        goalIndex.put(1,0);
        goalIndex.put(2,1);
        goalIndex.put(3,2);
        goalIndex.put(4,5);
        goalIndex.put(5,8);
        goalIndex.put(6,7);
        goalIndex.put(7,6);
        goalIndex.put(8,3);
        goalIndex.put(0,4);
    }

    @Override
    public int getH(PuzzleNode puzzle) {
        int counter = 0;
        List<Integer> puzzleList = puzzle.getPuzzle();

        for (int i = 0; i < 9; i++) {
            if(i==(goalIndex.get(puzzleList.get(i)))){
                counter+=1;
            }
        }
        return counter;
    }
}
