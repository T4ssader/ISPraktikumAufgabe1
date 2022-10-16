package AStar;

import java.util.*;

public class PuzzleNode implements Comparable<PuzzleNode> {


    private List<Integer> puzzle = new ArrayList<>();

    private List<PuzzleNode> neighbors = new ArrayList<>();

    private int nullInteger;
    private int f;

    private int g;

    private int depth = 0;
    private PuzzleNode parent;


    public PuzzleNode(List<Integer> puzzle,PuzzleNode parent) {
        this.puzzle = puzzle;
        this.nullInteger = puzzle.indexOf(0);
        this.f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
        this.parent = parent;
        if (parent != null) {
            this.depth = parent.getDepth() + 1;
        } else {
            this.depth = 0;
        }
    }


    @Override
    public int compareTo(PuzzleNode n) {
        return Integer.compare(this.f, n.getF());
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        PuzzleNode p = (PuzzleNode) o;
        for (int i = 0; i < 9; i++) {
            if (!puzzle.get(i).equals(p.getPuzzle().get(i))) {
                return false;
            }
        }
        return true;
    }

    public void printPuzzle() {
        System.out.print(puzzle.get(0)==0 ? "  ": puzzle.get(0) + " ");
        System.out.print(puzzle.get(1)==0 ? "  ": puzzle.get(1) + " ");
        System.out.print(puzzle.get(2)==0 ? "  ": puzzle.get(2) + " " +"\n");

        System.out.print(puzzle.get(3)==0 ? "  ": puzzle.get(3) + " ");
        System.out.print(puzzle.get(4)==0 ? "  ": puzzle.get(4) + " ");
        System.out.print(puzzle.get(5)==0 ? "  ": puzzle.get(5) + " " +"\n");

        System.out.print(puzzle.get(6)==0 ? "  ": puzzle.get(6) + " ");
        System.out.print(puzzle.get(7)==0 ? "  ": puzzle.get(7) + " ");
        System.out.print(puzzle.get(8)==0 ? "  ": puzzle.get(8) + " " +"\n");
    }


    //getter
    public List<PuzzleNode> getNeighbors() {
        return neighbors;
    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getDepth() {
        return depth;
    }

    public PuzzleNode getParent() {
        return parent;
    }

    public int getNullInteger() {
        return nullInteger;
    }

    public List<Integer> getPuzzle(){
        return puzzle;
    }

    //Setter

    public void setNeighbors() {
        switch (nullInteger) {
            case 0 -> {
                PuzzleNode neighborDown = new PuzzleNode(swapDown(),this);
                PuzzleNode neighborRight = new PuzzleNode(swapRight(),this);
                neighbors.add(neighborDown);
                neighbors.add(neighborRight);
            }
            case 1 -> {
                PuzzleNode neighborLeft = new PuzzleNode(swapLeft(),this);
                PuzzleNode neighborRight = new PuzzleNode(swapRight(),this);
                PuzzleNode neighborDown = new PuzzleNode(swapDown(),this);
                neighbors.add(neighborLeft);
                neighbors.add(neighborRight);
                neighbors.add(neighborDown);
            }
            case 2 -> {
                PuzzleNode neighborLeft = new PuzzleNode(swapLeft(),this);
                PuzzleNode neighborDown = new PuzzleNode(swapDown(),this);
                neighbors.add(neighborLeft);
                neighbors.add(neighborDown);
            }
            case 3 -> {
                PuzzleNode neighborUp = new PuzzleNode(swapUp(),this);
                PuzzleNode neighborRight = new PuzzleNode(swapRight(),this);
                PuzzleNode neighborDown = new PuzzleNode(swapDown(),this);
                neighbors.add(neighborUp);
                neighbors.add(neighborRight);
                neighbors.add(neighborDown);
            }
            case 4 -> {
                PuzzleNode neighborUp = new PuzzleNode(swapUp(),this);
                PuzzleNode neighborRight = new PuzzleNode(swapRight(),this);
                PuzzleNode neighborDown = new PuzzleNode(swapDown(),this);
                PuzzleNode neighborLeft = new PuzzleNode(swapLeft(),this);
                neighbors.add(neighborUp);
                neighbors.add(neighborRight);
                neighbors.add(neighborDown);
                neighbors.add(neighborLeft);
            }
            case 5 -> {
                PuzzleNode neighborUp = new PuzzleNode(swapUp(),this);
                PuzzleNode neighborDown = new PuzzleNode(swapDown(),this);
                PuzzleNode neighborLeft = new PuzzleNode(swapLeft(),this);
                neighbors.add(neighborUp);
                neighbors.add(neighborDown);
                neighbors.add(neighborLeft);
            }
            case 6 -> {
                PuzzleNode neighborUp = new PuzzleNode(swapUp(),this);
                PuzzleNode neighborRight = new PuzzleNode(swapRight(),this);
                neighbors.add(neighborUp);
                neighbors.add(neighborRight);
            }
            case 7 -> {
                PuzzleNode neighborUp = new PuzzleNode(swapUp(),this);
                PuzzleNode neighborRight = new PuzzleNode(swapRight(),this);
                PuzzleNode neighborLeft = new PuzzleNode(swapLeft(),this);
                neighbors.add(neighborUp);
                neighbors.add(neighborRight);
                neighbors.add(neighborLeft);
            }
            case 8 -> {
                PuzzleNode neighborUp = new PuzzleNode(swapUp(),this);
                PuzzleNode neighborLeft = new PuzzleNode(swapLeft(),this);
                neighbors.add(neighborUp);
                neighbors.add(neighborLeft);
            }
        }
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setParent(PuzzleNode parent) {
        this.parent = parent;
    }


    private List<Integer> swapUp() {
        List<Integer> tempPuzzle = new ArrayList<>(puzzle);
        Collections.swap(tempPuzzle, nullInteger, nullInteger - 3);
        return tempPuzzle;
    }

    private List<Integer> swapRight() {
        List<Integer> tempPuzzle = new ArrayList<>(puzzle);
        Collections.swap(tempPuzzle, nullInteger, nullInteger + 1);
        return tempPuzzle;
    }

    private List<Integer> swapDown() {
        List<Integer> tempPuzzle = new ArrayList<>(puzzle);
        Collections.swap(tempPuzzle, nullInteger, nullInteger + 3);
        return tempPuzzle;
    }

    private List<Integer> swapLeft() {
        List<Integer> tempPuzzle = new ArrayList<>(puzzle);
        Collections.swap(tempPuzzle, nullInteger, nullInteger - 1);
        return tempPuzzle;
    }


    public void printPath(){
        System.out.println("Step: " + (this.getDepth()));
        this.printPuzzle();
        System.out.println("\n\n\n");
        if(this.parent!=null){
            this.parent.printPath();
        }
    }

    @Override
    public String toString(){
        return (puzzle.toString());
    }
}
