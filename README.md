# dots-and-boxes

██████╗  ██████╗ ████████╗███████╗     █████╗ ███╗   ██╗██████╗     ██████╗  ██████╗ ██╗  ██╗███████╗███████╗
██╔══██╗██╔═══██╗╚══██╔══╝██╔════╝    ██╔══██╗████╗  ██║██╔══██╗    ██╔══██╗██╔═══██╗╚██╗██╔╝██╔════╝██╔════╝
██║  ██║██║   ██║   ██║   ███████╗    ███████║██╔██╗ ██║██║  ██║    ██████╔╝██║   ██║ ╚███╔╝ █████╗  ███████╗
██║  ██║██║   ██║   ██║   ╚════██║    ██╔══██║██║╚██╗██║██║  ██║    ██╔══██╗██║   ██║ ██╔██╗ ██╔══╝  ╚════██║
██████╔╝╚██████╔╝   ██║   ███████║    ██║  ██║██║ ╚████║██████╔╝    ██████╔╝╚██████╔╝██╔╝ ██╗███████╗███████║
╚═════╝  ╚═════╝    ╚═╝   ╚══════╝    ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝     ╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝

By Terence Ting

Font courtesy of Patorjk.com

A Dots and Boxes game meant to be played in the terminal. (WIP)

## Files

- `Driver.java`: Handles User Input
	- Prompts user to chose between Singleplayer and 2-Player modes
	- Prompts user for the number of rows and number of columns (minimum 1 row/column, maximum 9 rows/columns)
	- Prompts user for coordinate to place wall
	- Prints out the current game board and declares a winner
- `Board.java`: Handles Board Creation, Editing, and Printing
	- `clearBoard()`: Clears the board of all claimed boxes and walls
	- `getOccupancy()`: Returns the number of walls that can be placed
	- `debugOff()`: Turns off debug mode
	- `debugOn()`: Turns on debug mode
	- `toString()`: Prints out the current game board with coordinate labels
	- `testPosition()`: Tests whether or not a string input is a valid coordinate on the board
	- `fillPosition()`: Places a wall at the given coordinate on the board
	- `setBoxValue`: Sets a box to the value of the number of walls that surround it
	- `countScore`: tallies the number of ‘X’ and ‘O’ found on the board

