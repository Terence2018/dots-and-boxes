# dots-and-boxes

A Dots and Boxes game meant to be played in the terminal. (WIP)

## Files

- `Driver.java`: Handles User Input and Debug mode
	- Prompts user to chose between Single-player and 2-Player modes
	- Prompts user for the difficulty level of CPU Opponent in Single-player mode
	- Prompts user for the number of rows and number of columns (minimum 1 row/column, maximum 9 rows/columns)
	- Prompts user for coordinate to place wall
	- Prints out the current game board and declares a winner
- `Board.java`: Handles Board Creation, Editing, and Printing
	- `clearBoard()`: Clears the board of all claimed boxes and walls
	- `getOccupancy()`: Returns the number of walls that can be placed
	- `debugOff()`: Turns off debug mode
	- `debugOn()`: Turns on debug mode
	- `toString()`: Prints out the current game board with coordinate labels
	- `printNoBorders()`: Prints out the current game board with no walls and coordinate labels
	- `testPosition()`: Tests whether or not a string input is a valid coordinate on the board
	- `fillPosition()`: Places a wall at the given coordinate on the board
	- `setBoxValue()`: Sets a box to the value of the number of walls that surround it
	- `countScore()`: tallies the number of ‘X’ and ‘O’ found on the board
- `Computer.java`: Handles the CPU Opponent Algorithms
	- `pickRandColIndex()`: Chooses a random column index
	- `pickRandRowIndex()`: Chooses a random row index
	- `randomCoord()`: Easy CPU algorithm. Chooses a random coordinate on the board that is empty
	- `searchForBox()`: Searches for a box with a certain weight
	- `priorityFill()`:  Normal CPU algorithm. Prioritizes boxes with a weight of 3. If no box exists, randomly chooses a coordinate and tries to avoid creating walls next to boxes with a weight of 2.