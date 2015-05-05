package main;

import java.util.Stack;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GUIGame extends BasicGameState {

    private final int STATE;
    private int mouseX;
    private int mouseY;
    private Input input;
    private Board board;
    private Stack<Tile> revealStack;
    private Button resetButton;
    private Clock gameClock;
    private Button clockButton;
    private int numBombsFlagged;

    public GUIGame(int state) {
	STATE = state;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
	    throws SlickException {
	numBombsFlagged = 0;
	clockButton = new Button(0 + "", 10, 10, 50, 50);
	gameClock = new Clock(clockButton);
	board = new Board("EASY");
	revealStack = new Stack<Tile>();
	input = container.getInput();
	resetButton = new Button("RESET",
		(int) (container.getWidth() * 0.50) - 25,
		(int) (container.getHeight() * 0.03), 50, 50);
	gameClock.start();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
	    throws SlickException {
	g.setBackground(Color.lightGray);
	board.draw(g);
	resetButton.draw(g);
	clockButton.draw(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
	    throws SlickException {
	mouseX = Mouse.getX();
	mouseY = container.getHeight() - Mouse.getY();
	if(numBombsFlagged == board.getNumBombs()){
	    setWinState();
	}
	checkTileClick(container, game);
    }

    private void checkTileClick(GameContainer container, StateBasedGame game) {
	if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
	    if (resetButton.isMouseOver(input, mouseX, mouseY)) {
		reset(container, game);
	    }
	    for (Tile[] t : board.getBoard()) {
		for (Tile tile : t) {
		    if (tile.isMouseOver(input, mouseX, mouseY)
			    && !tile.getFlagged()) {
			tile.clicked();
			if (tile.isBomb()) {
			    setLoseState();
			}
			if (tile.getBombsSurrounding() == 0) {
			    revealEmptyRegion(tile);
			}
		    }
		}
	    }
	}
	if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)) {
	    for (Tile[] t : board.getBoard()) {
		for (Tile tile : t) {
		    if (tile.isMouseOver(input, mouseX, mouseY)) {
			if (!tile.getFlagged()) {
			    if(tile.isBomb()){
				numBombsFlagged++;
			    }
			    tile.flag();
			} else {
			    if(tile.isBomb()){
				numBombsFlagged--;
			    }
			    tile.unflag();
			}
		    }
		}
	    }
	}
    }

    @Override
    public int getID() {
	return STATE;
    }

    private void reset(GameContainer container, StateBasedGame game) {
	try {
	    this.init(container, game);
	    game.enterState(STATE);
	} catch (SlickException e) {
	}
    }

    private void setLoseState() {
	gameClock.stop();
	for (Tile[] t : board.getBoard()) {
	    for (Tile tile : t) {
		tile.clicked();
	    }
	}
    }
    
    private void setWinState() {
	gameClock.stop();
	for (Tile[] t : board.getBoard()) {
	    for (Tile tile : t) {
		tile.clicked();
	    }
	}
    }

    private void revealEmptyRegion(Tile tile) {
	Tile currTile;
	int x;
	int y;
	revealStack.push(tile);
	while (revealStack.size() > 0) {
	    currTile = revealStack.pop();
	    currTile.clicked();
	    x = currTile.getBoardX();
	    y = currTile.getBoardY();
	    for (int i = -1; i < 2; i++) {
		for (int j = -1; j < 2; j++) {
		    try {
			if (board.getBoard()[x + i][y + j]
				.getBombsSurrounding() == 0
				&& !board.getBoard()[x + i][y + j].getClicked()
				&& !(i == 0 && j == 0)
				&& !board.getBoard()[x + i][y + j].getFlagged()) {
			    revealStack.push(board.getBoard()[x + i][y + j]);
			} else {
			    board.getBoard()[x + i][y + j].clicked();
			}
		    } catch (ArrayIndexOutOfBoundsException e) {
		    }
		}
	    }
	}
    }
}
