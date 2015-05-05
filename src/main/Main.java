package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {

    private GUIMenu guiMenu;
    private GUIGame guiGame;

    public static final int MENU_STATE = 0;
    public static final int GAME_STATE = 1;

    public Main(String name) {
	super(name);
	guiMenu = new GUIMenu(MENU_STATE);
	guiGame = new GUIGame(GAME_STATE);
	this.addState(guiMenu);
	this.addState(guiGame);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
	this.getState(MENU_STATE).init(container, this);
	this.getState(GAME_STATE).init(container, this);
	this.enterState(GAME_STATE);
    }

    public static void main(String[] args) {
	String title = "Java Minesweeper";
	String version = " v0.0.5";
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	try {
	    AppGameContainer appgc = new AppGameContainer(new Main(title
		    + version));
	    appgc.setShowFPS(true);
	    appgc.setDisplayMode((int) (screenSize.getWidth() * 0.3),
		    (int) (screenSize.getHeight() * 0.55), false);
	    appgc.start();
	} catch (SlickException e) {
	    e.printStackTrace();
	}
	;

    }

}
