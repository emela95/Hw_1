import java.util.*;
import java.io.*;

/**
 * MazeTester uses recursion to determine if a maze can be traversed.
 *
 * @author Lewis and Chase, Chiemela Nwoke
 * @version 4.0
 */
public class MazeTester
{
    /**
     * Creates a new maze, prints its original form, attempts to
     * solve it, and prints out its final form.
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the file containing the maze: ");
        String filename = scan.nextLine();

        Maze labyrinth = new Maze("filename");

        System.out.println(labyrinth);

        System.out.println("Enter the starting position (Two integers separated by a comma): ");
        String start = scan.nextLine();

        System.out.print("Enter the end position (Two integers separated by a comma): ");
        String end = scan.nextLine();

        String[] value = start.split(",");
        int[] StartPoint = new int[value.length];
        for (int i = 0; i < StartPoint.length; i++){
            StartPoint[i] = Integer.parseInt(value[i]);
        }

        System.out.println("Start Position is x: " + StartPoint[0] + "y: "+ StartPoint[1]);
        String[] data1 = end.split(",");
        int[] endPosition = new int[data1.length];
        for (int i = 0; i < endPosition.length; i++){
            endPosition[i] = Integer.parseInt(data1[i]);
        }

        System.out.println("End Position is x: " + endPosition[0] + "y: "+ endPosition[1] );


        MazeSolver solver = new MazeSolver(labyrinth);
        solver.setMazeStart(StartPoint[0], StartPoint[1]);
        solver.setMazeEnd(endPosition[0], endPosition[1]);

        if (solver.traverse())
            System.out.println("The maze was successfully traversed!");
        else
            System.out.println("There is no possible path.");

        solver.display();
        System.out.println(labyrinth);

    }
}