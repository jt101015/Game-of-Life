# Game-of-Life
Game of Life in Smartphone Application Development Course

# Requirements
1. This screen consists of the main display for the grid of cells. The grid is at least 12 by 12, and you have the freedom to make it bigger.
2. Each cell is either live or dead, with live filled with a solid red circle, and dead being empty.
3. Each cell in the grid is clickable at any time. If clicked, its state flips from live to dead, or dead to live.
4. Recommended implementation for rendering the grid: Android 2D graphics
5. Besides the main display, there are two buttons, Next and Reset.<br>
   a. Clicking on Next brings the grid to its next generation.<br>
   b. Clicking on Reset clears all live cells, making the whole grid empty.<br>
   c. Before clearing everything, your app prompts the user to confirm that he/she wants to reset the game.

# Evolution rules

1. We follow the rules as described in the referenced wiki page
  a. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation. <br>
	b. Any live cell with two or three live neighbours lives on to the next generation.<br>
	c. Any live cell with more than three live neighbours dies, as if by overpopulation.<br>
	d. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.<br>
  
2. Border condition
   If a neighbor’s position is outside the grid, then we do not consider that neighbor as existent.

# Android Screenshots


# iOS Screenshots
