package Test;

import AStar.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


public class TestAStar {

    PuzzleNode goalPuzzle;

    PuzzleNode simplePuzzle;

    PuzzleNode difficultPuzzle;

    PuzzleNode unsolvablePuzzle;

    @BeforeEach
    public void initialize() {


        //GOAL PUZZLE INITIALIZATION
        List<Integer> goalList = new ArrayList<>();

        goalList.add(1);
        goalList.add(2);
        goalList.add(3);
        goalList.add(8);
        goalList.add(0);
        goalList.add(4);
        goalList.add(7);
        goalList.add(6);
        goalList.add(5);
        goalPuzzle = new PuzzleNode(goalList, null);

        //SIMPLE PUZZLE INITIALIZATION
        List<Integer> simpleList = new ArrayList<>();

        simpleList.add(0);
        simpleList.add(2);
        simpleList.add(3);
        simpleList.add(1);
        simpleList.add(8);
        simpleList.add(4);
        simpleList.add(7);
        simpleList.add(6);
        simpleList.add(5);
        simplePuzzle = new PuzzleNode(simpleList, null);


        //DIFFICULT PUZZLE INITIALIZATION
        List<Integer> difficultList = new ArrayList<>();
        difficultList.add(8);
        difficultList.add(3);
        difficultList.add(5);
        difficultList.add(4);
        difficultList.add(1);
        difficultList.add(6);
        difficultList.add(2);
        difficultList.add(7);
        difficultList.add(0);
        difficultPuzzle = new PuzzleNode(difficultList, null);

        //UNSOLVABLE PUZZLE INITIALIZATION
        List<Integer> unsolvableList = new ArrayList<>();
        unsolvableList.add(1);
        unsolvableList.add(2);
        unsolvableList.add(3);
        unsolvableList.add(8);
        unsolvableList.add(0);
        unsolvableList.add(4);
        unsolvableList.add(7);
        unsolvableList.add(5);
        unsolvableList.add(6);
        unsolvablePuzzle = new PuzzleNode(unsolvableList, null);
    }


    @Test
    public void testCorrectnessSimple() {
        ManhattenHeuristic heuristic1 = new ManhattenHeuristic();

        AStar aStar = new AStar(simplePuzzle, heuristic1);
        PuzzleNode resultPuzzle = aStar.calculatePath(goalPuzzle);
        System.out.println("Expected: ");
        goalPuzzle.printPuzzle();
        System.out.println("\nResult:");
        resultPuzzle.printPuzzle();

        Assertions.assertEquals(goalPuzzle, resultPuzzle);
    }


    @Test
    public void testCorrectnessDifficult() {
        ManhattenHeuristic heuristic1 = new ManhattenHeuristic();

        AStar aStar = new AStar(difficultPuzzle, heuristic1);
        PuzzleNode resultPuzzle = aStar.calculatePath(goalPuzzle);
        System.out.println("Expected: ");
        goalPuzzle.printPuzzle();
        System.out.println("\nResult:");
        resultPuzzle.printPuzzle();

        Assertions.assertEquals(goalPuzzle, resultPuzzle);
    }


    @Test
    public void testBasicPuzzle() {
        ManhattenHeuristic heuristic1 = new ManhattenHeuristic();

        AStar aStar = new AStar(simplePuzzle, heuristic1);
        PuzzleNode resultPuzzle = aStar.calculatePath(goalPuzzle);
        resultPuzzle.printPath();
        Assertions.assertEquals(2, resultPuzzle.getDepth());
    }


    @Test
    public void testDifficultPuzzle() {

        ManhattenHeuristic heuristic1 = new ManhattenHeuristic();

        AStar aStar = new AStar(difficultPuzzle, heuristic1);
        PuzzleNode resultPuzzle = aStar.calculatePath(goalPuzzle);
        resultPuzzle.printPath();

        Assertions.assertEquals(14, resultPuzzle.getDepth());
    }


    @Test
    public void testUnsolvablePuzzle(){
        ManhattenHeuristic heuristic1 = new ManhattenHeuristic();

        AStar aStar = new AStar(unsolvablePuzzle, heuristic1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> aStar.calculatePath(goalPuzzle));
    }
//    @Test
//    public void testIncorrectHeuristic() {
//        IncorrectSimpleHeuristic heuristic = new IncorrectSimpleHeuristic();
//
//        AStar aStar = new AStar(difficultPuzzle, heuristic);
//        PuzzleNode resultPuzzle = aStar.calculatePath(goalPuzzle);
//        resultPuzzle.printPath();
//
//        Assertions.assertEquals(14, resultPuzzle.getDepth());
//    }


    @Test
    public void testIncorrectHeuristic() {
        IncorrectSimpleHeuristic heuristic = new IncorrectSimpleHeuristic();

        AStar aStar = new AStar(difficultPuzzle, heuristic);
        PuzzleNode resultPuzzle = aStar.calculatePath(goalPuzzle);
        resultPuzzle.printPath();

        Assertions.assertEquals(14, resultPuzzle.getDepth());
    }


    @Test
    public void testCompareHeuristics() {


        ManhattenHeuristic heuristic1 = new ManhattenHeuristic();

        AStar aStar = new AStar(difficultPuzzle, heuristic1);
        PuzzleNode resultPuzzle = aStar.calculatePath(goalPuzzle);

        System.out.println("Steps required with AStar, manhatten metric:\t " + aStar.getStepsRequired());
        Assertions.assertEquals(goalPuzzle.getPuzzle(), resultPuzzle.getPuzzle());

        ///////////////////

        SimpleHeuristic heuristic2 = new SimpleHeuristic();

        AStar aStar2 = new AStar(difficultPuzzle, heuristic2);
        PuzzleNode resultPuzzle2 = aStar2.calculatePath(goalPuzzle);

        System.out.println("Steps required with AStar, simple metric:\t\t " + aStar2.getStepsRequired());
        Assertions.assertEquals(goalPuzzle.getPuzzle(), resultPuzzle2.getPuzzle());

        ///////////////////

        EmptyHeuristic heuristic3 = new EmptyHeuristic();

        AStar aStar3 = new AStar(difficultPuzzle, heuristic3);
        PuzzleNode resultPuzzle3 = aStar3.calculatePath(goalPuzzle);

        System.out.println("Steps required with AStar, no metric:     \t\t " + aStar3.getStepsRequired());
        Assertions.assertEquals(goalPuzzle.getPuzzle(), resultPuzzle3.getPuzzle());

        //////////////////
        IncorrectSimpleHeuristic heuristic4 = new IncorrectSimpleHeuristic();
        AStar aStar4 = new AStar(difficultPuzzle, heuristic4);
        PuzzleNode resultPuzzle4 = aStar4.calculatePath(goalPuzzle);

        System.out.println("Steps required with AStar, incorrect metric:\t " + aStar4.getStepsRequired());
        Assertions.assertEquals(goalPuzzle.getPuzzle(), resultPuzzle4.getPuzzle());
    }
}
