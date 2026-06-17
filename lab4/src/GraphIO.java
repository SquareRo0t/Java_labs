
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GraphIO {
	static public void readFile(Graph g, String filename) throws IOException{
		
		Scanner scanner = null;
		try {
			// Create a new Scanner to read data from the file
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			// If the file is not found, throw an IOException
			throw new IOException();
		}
		
		// Read the number of nodes from the first line of the file
		int numNodes = Integer.parseInt(scanner.nextLine());
		
		// Iterate through each node data and add nodes to the graph
		for(int i = 0; i < numNodes; i++) {
			// Read node id, x-coordinate, and y-coordinate from each line
			int id = scanner.nextInt();
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			// Add the node to the graph
			g.addNode(id, x, y);
		}
		
		// Read edge data until no more integers are available
		while(scanner.hasNextInt()) {
			// Read edge information, node id1, node id2, and edge weight
			int id1 = scanner.nextInt();
			int id2 = scanner.nextInt();
			int weight = scanner.nextInt();
			// Add the edge to the graph
			g.addEdge(id1, id2, weight);
		}
		
		scanner.close();
	}
}
