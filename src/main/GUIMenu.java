package main;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

public class GUIMenu extends BasicGameState {

    private final int STATE;
    private Button startBtn;
    private Input input;

    public GUIMenu(int state) {
	STATE = state;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
	    throws SlickException {
	input = container.getInput();
	startBtn = new Button("Start", container.getWidth() / 2 - 50,
		container.getHeight() / 2 - 50, 100, 100);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
	    throws SlickException {
	int mouseX = Mouse.getX();
	int mouseY = container.getHeight() - Mouse.getY();
	startBtn.draw(g);
	g.drawString("JavaSweeper", container.getWidth() / 2
		- g.getFont().getWidth("JavaSweeper") / 2,
		(int) (container.getHeight() * 0.35));
	g.drawString("Author: Morgan Eckenroth", container.getWidth() / 2
		- g.getFont().getWidth("Author: Morgan Eckenroth") / 2,
		(int) (container.getHeight() * 0.65));
	if (startBtn.isClicked(input, mouseX, mouseY)) {
	    game.enterState(Main.GAME_STATE, null,
		    new VerticalSplitTransition());
	}

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
	    throws SlickException {

    }

    @Override
    public int getID() {
	return STATE;
    }

}
