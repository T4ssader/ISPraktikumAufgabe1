package AStar;

public class EmptyHeuristic implements Heuristic<PuzzleNode>{

    @Override
    public int getH(PuzzleNode h) {
        return 0;
    }
}
