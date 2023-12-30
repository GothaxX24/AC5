import edu.salle.url.maze.Maze;
import edu.salle.url.maze.MazeBuilder;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Salle Maze Solver Generator");
        System.out.println("1. Cave");
        System.out.println("2. Dungeon");
        System.out.print("Choose (1 or 2): ");

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1 || option == 2) {
            System.out.print("Enter the dimensions of the maze (from 5 to 255): ");
            int dimensions = scanner.nextInt();

            if (dimensions >= 5 && dimensions <= 255) {
                MazeBacktracking mazeBacktracking = new MazeBacktracking();
                MazeBuilder mazeBuilder = new MazeBuilder()
                        .setMazeColumns(dimensions)
                        .setMazeRows(dimensions)
                        .setMazeSolver(mazeBacktracking);
                Maze maze;
                if (option == 1) {
                    maze = mazeBuilder.buildCaveMaze();
                } else {
                    maze = mazeBuilder.buildDungeonMaze();
                }                maze.run();
            } else {
                System.out.println("Dimensiones entre 5 y 255.");
            }
        } else {
            System.out.println("Opcion no valida.");
        }

        scanner.close();
    }
}
