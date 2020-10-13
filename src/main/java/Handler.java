import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Kamil Wyszyński
 */

public class Handler {

    public static void main(String[] args) {
        ArrayList<Vertex> inputVertices = new ArrayList<>();

        Scanner initialInput = new Scanner(System.in);

        welcomeMessage();

        inputValidation(initialInput, "Number of vertices must be an integer, please enter an integer: ");

        int numberOfVertices = initialInput.nextInt();

        System.out.println("Please enter some x coordinate,y coordinate and vertex name. In format:\n -Integer\n -Integer\n -String");
        for (int i = 1; i <= numberOfVertices; ++i) {
            System.out.println("***************************\nData for vertex number: " + i + "\n***************************\nEnter X coordinate: ");

            inputValidation(initialInput, "X coordinate must be an integer, please enter an integer: ");

            System.out.println("Enter Y coordinate: ");

            int xCoordinate = initialInput.nextInt();

            inputValidation(initialInput, "Y coordinate must be an integer, please enter an integer: ");

            int yCoordinate = initialInput.nextInt();

            System.out.println("Please enter vertex name: ");
            Scanner nameInput = new Scanner(System.in);
            String vertexName = nameInput.nextLine();
            inputVertices.add(new Vertex(vertexName, xCoordinate, yCoordinate));
        }

        System.out.println("Which vertex do you want as a starting point?: ");
        inputVertices.forEach(element -> System.out.println(element.getName()));
        System.out.println("Enter name of starting vertex:");
        Scanner inputVertexName = new Scanner(System.in);


        List<Vertex> chosenVertex = new ArrayList<>();

        while (chosenVertex.isEmpty()) {
            String vertexName = inputVertexName.nextLine();
            chosenVertex = inputVertices.stream()
                    .filter(element -> element.getName().toLowerCase().trim().equals(vertexName.toLowerCase().trim()))
                    .collect(Collectors.toList());
            System.out.println("Please enter valid vertex name, one of: ");
            inputVertices.forEach(element -> System.out.println(element.getName()));
        }

        int startingVertexIndex = inputVertices.indexOf(chosenVertex.get(0));

        Handler handler = new Handler();
        NearestNeighbor nearestNeighbor = new NearestNeighbor();
        nearestNeighbor.setInitialIndex(startingVertexIndex);
        handler.printShortestRoute(nearestNeighbor.findShortestRoute(inputVertices));

    }

    private static void welcomeMessage() {
        System.out.println("***************************************************************************************\n" +
                "Welcome to the application that solves the TSP problem with the nearest neighbor algorithm.\n" +
                "***************************************************************************************\n" +
                "Please enter how many vertices do you want to check: ");
    }

    private static void inputValidation(final Scanner input, final String message) {
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.print(message);
        }
    }

    private void printShortestRoute(final Route shortestRoute) {
        System.out.println("************************************************\nShortest route: " + shortestRoute);
        System.out.println("total distance: " + shortestRoute.calculateTotalDistance() + "\n************************************************");
    }
}
