import edu.salle.url.maze.business.MazeSolver;
import edu.salle.url.maze.business.enums.Cell;
import edu.salle.url.maze.business.enums.Direction;
import edu.salle.url.maze.presentation.MazeRenderer;

import java.util.ArrayList;
import java.util.List;

public class MazeBacktracking implements MazeSolver {

    private Cell[][] cells;
    private int initialRow;
    private int initialColumn;
    private List<Direction> finalList = new ArrayList<>();
    private final List<int[]> visitedCells = new ArrayList<>();
    MazeRenderer mazeRe;
    public MazeBacktracking() {
    }
    /**
     * Resol el laberint mitjançant la tècnica de backtracking.
     *
     * @param cells         Matriu que representa el laberint.
     * @param mazeRenderer  Objecte que renderitza el laberint.
     * @return              Llista de direccions que representa la solució del laberint.
     */
    @Override
    public List<Direction> solve(Cell[][] cells, MazeRenderer mazeRenderer) {
        this.cells = cells;
        getStart();
        mazeRe = mazeRenderer;
        List<Direction> config = new ArrayList<>();
        backtracking(config, initialRow, initialColumn);
        mazeRenderer.render(cells, finalList);
        return finalList;
    }
    /**
     * Obté la posició inicial del laberint.
     */
    private void getStart() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                Cell currentCell = cells[i][j];

                if (currentCell == Cell.START) {
                    initialRow = i;
                    initialColumn = j;
                }
            }
        }
    }
    /**
     * Implementa l'algorisme de backtracking per trobar una solució.
     *
     * @param config    Configuració actual de direccions.
     * @param row       Fila actual.
     * @param column    Columna actual.
     */
    private void backtracking(List<Direction> config, int row, int column) {
        if (!finalList.isEmpty() && config.size() >= finalList.size()) {
            return;
        }

        if (cells[row][column] == Cell.EXIT) {
            finalList = new ArrayList<>(config);
            return;
        }

        for (Direction direction : Direction.values()) {
            int newRow = 0, newColumn = 0;
            switch (direction) {
                case UP -> newRow = -1;
                case DOWN -> newRow = 1;
                case LEFT -> newColumn = -1;
                case RIGHT -> newColumn = 1;
            }
            int nextRow = row + newRow;
            int nextColumn = column + newColumn;
            //mazeRe.render(cells,config, 50);
            if (!cells[nextRow][nextColumn].equals(Cell.WALL) && !isVisited(nextRow, nextColumn)) {
                visitedCells.add(new int[]{nextRow, nextColumn});
                config.add(direction);
                backtracking(config, nextRow, nextColumn);
                config.remove(config.size() - 1);
                visitedCells.remove(visitedCells.size() - 1);
            }
        }
    }

    /**
     * Verifica si una casella ja ha estat visitada.
     *
     * @param row       Fila de la cel·la a comprovar.
     * @param column    Columna de la cel·la a comprovar.
     * @return          Cert si la cel·la ja ha estat visitada, fals en cas contrari.
     */
    private boolean isVisited(int row, int column) {
        for (int[] visitedCell : visitedCells) {

            if (visitedCell[0] == row && visitedCell[1] == column) {
                return true;
            }
        }
        return false;
    }
}
