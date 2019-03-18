import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MazeSolver {

	private int mazeWidth;
	private int mazeHeight;
	private int startX;
	private int startY;
	private char[][] inputMaze;
	private char[][] outputMaze;
	private boolean[][] visited;
	private boolean[][] wall;


	public void printMethod() {
		//printing input maze
		for (int i = 0; i < mazeHeight; i++) {
			for (int j = 0; j < mazeWidth; j++) {
				if ((j + 1) % mazeWidth == 0) {
					System.out.print(inputMaze[i][j]);
					System.out.print("\n");
				} else {
					System.out.print(inputMaze[i][j]);
				}
			}
		}
		//printing output maze
		for (int i = 0; i < mazeHeight; i++) {
			for (int j = 0; j < mazeWidth; j++) {
				if ((j + 1) % mazeWidth == 0) {
					System.out.print(outputMaze[i][j]);
					System.out.print("\n");
				} else {
					System.out.print(outputMaze[i][j]);
				}
			}
		}

	}

	public void readMaze(String path) throws IOException {
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bReader = new BufferedReader(fileReader);

			List<String> lines = new ArrayList<>();
			String line;
			while ((line = bReader.readLine()) != null) {
				lines.add(line);
			}
			bReader.close();

			String[] mazeWH = lines.get(0).split("\\s");
			String[] startXY = lines.get(1).split("\\s");
			String[] endXY = lines.get(2).split("\\s");

			mazeWidth = Integer.parseInt(mazeWH[0]);
			mazeHeight = Integer.parseInt(mazeWH[1]);
			startX = Integer.parseInt(startXY[0]);
			startY = Integer.parseInt(startXY[1]);
			int endX = Integer.parseInt(endXY[0]);
			int endY = Integer.parseInt(endXY[1]);

			ArrayList<String> mazeList = new ArrayList<String>();
			
			//skip first three lines as they are metadata
			for (int i = 0; i < lines.size() - 3; i++) {
				String tempLine = lines.get(i + 3).replaceAll("\\s", "");
				mazeList.add(tempLine);
			}

			inputMaze = new char[mazeHeight][mazeWidth];
			outputMaze = new char[mazeHeight][mazeWidth];
			visited = new boolean[mazeHeight][mazeWidth];
			wall = new boolean[mazeHeight][mazeWidth];
			
			//populating input array
			for (int i = 0; i < mazeHeight; i++) {
				for (int j = 0; j < mazeWidth; j++) {
					if (i == startY && j == startX) {
						inputMaze[i][j] = 'S';
					} else if (i == endY && j == endX) {
						inputMaze[i][j] = 'E';
					} else {
						inputMaze[i][j] = mazeList.get(i).charAt(j);
					}
				}
			}

			System.out.println(findPath(startX, startY));

		} catch (IOException e) {
			System.out.println("That file wasn't found. Please check the full file path is correct");
		}
		
	}

	public boolean findPath(int x, int y) {
		//wrapping logic
		if (x + 1 >= mazeWidth) {
			x = 0;
		}
		if (x - 1 < 0) {
			x = mazeWidth - 1;
		}
		if (y + 1 >= mazeHeight) {
			y = 0;
		}
		if (y - 1 < 0) {
			y = mazeHeight - 1;
		}

		if (inputMaze[y][x] == 'E') {
			outputMaze[y][x] = 'E';
			outputMaze[startY][startX] = 'S';
			printMethod();
			return true;
		}

		if (inputMaze[y][x] == '1') {
			outputMaze[y][x] = '#';
			wall[y][x] = true;
			return false;
		}
		if ((visited[y][x] == true) || (wall[y][x] == true)) {
			return false;
		}

		else {
			visited[y][x] = true;
			outputMaze[y][x] = 'X';

			if (findPath((x + 1), y) || findPath((x - 1), y) || findPath(x, (y + 1)) || findPath(x, (y - 1))) {
				return true;
			}

			visited[y][x] = false;
			outputMaze[y][x] = ' ';
		}

		return false;
	}

	public static void main(String args[]) throws IOException {
		System.out.println("Please give the path of the maze");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();
		MazeSolver mazeSolver = new MazeSolver();
		mazeSolver.readMaze(path);
	}
}
