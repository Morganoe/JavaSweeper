package main;

import java.awt.Dimension;
import java.util.Random;
import org.newdawn.slick.Graphics;

public class Board {

    private Tile[][] grid;
    private Dimension size;
    private int numBombs;
    private Random tilePicker;

    public Board(String difficulty) {
	if (difficulty.equals("EASY")) {
	    numBombs = 10;
	    size = new Dimension(9, 9);
	}
	if (difficulty.equals("MEDIUM")) {
	    numBombs = 40;
	    size = new Dimension(13, 13);
	}
	if (difficulty.equals("HARD")) {
	    numBombs = 99;
	    size = new Dimension(20, 20);
	}
	grid = new Tile[(int) size.getWidth()][(int) size.getHeight()];
	populateBoard();
    }

    private void populateBoard() {
	tilePicker = new Random();
	generateBombs();
	generateOtherTiles();
    }

    private void generateBombs() {
	int x;
	int y;
	for (int i = 0; i < numBombs; i++) {
	    x = tilePicker.nextInt((int) size.getWidth());
	    y = tilePicker.nextInt((int) size.getHeight());
	    while (grid[x][y] != null) {
		x = tilePicker.nextInt((int) size.getWidth());
		y = tilePicker.nextInt((int) size.getHeight());
	    }
	    grid[x][y] = new Tile(x * 40, y * 40, x, y, 0, true);
	}
    }

    private void generateOtherTiles() {
	for (int x = 0; x < size.getWidth(); x++) {
	    for (int y = 0; y < size.getHeight(); y++) {
		if (grid[x][y] == null) {
		    grid[x][y] = new Tile(x * 40, y * 40, x, y, getBombsAround(
			    x, y), false);
		}
	    }
	}
    }

    private int getBombsAround(int x, int y) {
	int numBombs = 0;
	for (int i = -1; i < 2; i++) {
	    for (int j = -1; j < 2; j++) {
		try {
		    if (grid[x + i][y + j] != null) {
			if (grid[x + i][y + j].isBomb()) {
			    numBombs++;
			}
		    }
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	    }
	}

	return numBombs;
    }

    public void draw(Graphics g) {
	for (int x = 0; x < size.getWidth(); x++) {
	    for (int y = 0; y < size.getHeight(); y++) {
		grid[x][y].draw(g);
	    }
	}
    }

    public int getNumBombs() {
	return numBombs;
    }

    public Dimension getSize() {
	return size;
    }

    public Tile[][] getBoard() {
	return grid;
    }
}
