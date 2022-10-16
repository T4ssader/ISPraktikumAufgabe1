package AStar;

import java.util.HashMap;
import java.util.List;

public class ManhattenHeuristic implements Heuristic<PuzzleNode>{

    HashMap<Integer,Integer> goalIndex = new HashMap<>();

    public ManhattenHeuristic() {
        /*
        1 2 3
        8 0 4
        7 6 5
         */
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
    public int getH(PuzzleNode h) {
        List<Integer> puzzle = h.getPuzzle();

        int sumStepsAway = 0;

        for (int i = 0; i < 9; i++) {
            Integer content = puzzle.get(i);
            Integer xContent = i%3;
            Integer yContent = i/3;

            Integer goalI = goalIndex.get(content);
            Integer xGoal = goalI%3;
            Integer yGoal = goalI/3;

            int stepsX = xContent-xGoal;
            int stepsY = yContent-yGoal;

            sumStepsAway+= Math.abs(stepsX);
            sumStepsAway+= Math.abs(stepsY);

        }
        return sumStepsAway;
    }
}
