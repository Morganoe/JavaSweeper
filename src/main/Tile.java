package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

public class Tile extends Button {

    private final boolean bomb;
    private final int bombsSurrounding;
    private boolean isClicked, isFlagged;
    private int stringX;
    private int stringY;
    private int boardX;
    private int boardY;
    private Polygon borderShapeLeft, borderShapeRight, borderShapeTop,
	    borderShapeBottom;

    public Tile(int x, int y, int boardX, int boardY, int bombsSurrounding,
	    boolean isBomb) {
	super("", x + 10, y + 70, 40, 40);
	this.boardX = boardX;
	this.boardY = boardY;
	this.bomb = isBomb;
	this.bombsSurrounding = bombsSurrounding;
	this.isClicked = false;
	this.isFlagged = false;
	borderShapeLeft = new Polygon();
	borderShapeRight = new Polygon();
	borderShapeTop = new Polygon();
	borderShapeBottom = new Polygon();

	setBorder();
    }

    @Override
    public void draw(Graphics g) {
	computeStringCenterCoords(g);

	if (!isFlagged) {
	    g.setColor(Color.lightGray);
	} else {
	    g.setColor(new Color(220, 20, 60));
	}
	g.fillRect(getX(), getY(), 40, 40);

	g.setColor(Color.black);
	if (isClicked) {
	    g.drawRect(getX(), getY(), 40, 40);
	} else {
	    drawTileBorder(g);
	}
	if (isClicked) {
	    g.setColor(getStringColor());
	    if (!bomb) {
		if (this.bombsSurrounding == 0) {
		    g.drawString("", stringX, stringY);
		} else {
		    g.drawString(this.bombsSurrounding + "", stringX, stringY);
		}
	    } else {
		g.drawString("B", stringX, stringY);
	    }
	}
    }

    private void computeStringCenterCoords(Graphics g) {
	stringX = getX() + (getWidth() / 2)
		- (g.getFont().getWidth(this.bombsSurrounding + "") / 2);
	stringY = getY() + (getHeight() / 2)
		- (g.getFont().getHeight(this.bombsSurrounding + "") / 2);
    }

    private void drawTileBorder(Graphics g) {
	if (!isFlagged) {
	    g.setColor(Color.white);
	    g.draw(borderShapeLeft);
	    g.draw(borderShapeTop);

	    g.setColor(Color.black);
	    g.draw(borderShapeBottom);
	    g.draw(borderShapeRight);
	} else {
	    g.setColor(new Color(255, 182, 193));
	    g.draw(borderShapeLeft);
	    g.draw(borderShapeTop);

	    g.setColor(new Color(176, 23, 31));
	    g.draw(borderShapeBottom);
	    g.draw(borderShapeRight);
	}
    }

    private Color getStringColor() {
	switch (bombsSurrounding) {
	case 1:
	    return Color.blue;
	case 2:
	    return Color.green;
	case 3:
	    return Color.red;
	case 4:
	    return new Color(46, 12, 181);
	case 5:
	    return new Color(102, 52, 0);
	case 6:
	    return Color.cyan;
	case 7:
	    return Color.black;
	case 8:
	    return Color.gray;
	default:
	    return Color.black;
	}
    }

    private void setBorder() {
	// Left Border
	borderShapeLeft.addPoint(getX() + 2, getY());
	borderShapeLeft.addPoint(getX() + 2, getY() + getHeight());
	borderShapeLeft.addPoint((int) (getX() + 2 + getWidth() * 0.05),
		(int) (getY() + getHeight() * 0.03));
	borderShapeLeft.addPoint((int) (getX() + 2 + getWidth() * 0.05),
		(int) (getY() + getHeight() * 0.97));

	// Top Border
	borderShapeTop.addPoint(getX(), getY() + 1);
	borderShapeTop.addPoint(getX() + getWidth(), getY() + 1);
	borderShapeTop.addPoint((int) (getX() + getWidth() * 0.03),
		(int) (getY() + 1 + getHeight() * 0.05));
	borderShapeTop.addPoint((int) (getX() + getWidth() * 0.97),
		(int) (getY() + 1 + getHeight() * 0.05));

	// Bottom Border
	borderShapeBottom.addPoint(getX(), getY() + 1 + getHeight());
	borderShapeBottom.addPoint(getX() + getWidth(), getY() + 1
		+ getHeight());
	borderShapeBottom.addPoint((int) (getX() + getWidth() * 0.03),
		(int) (getY() + 1 + getHeight() * 0.95));
	borderShapeBottom.addPoint((int) (getX() + getWidth() * 0.97),
		(int) (getY() + 1 + getHeight() * 0.95));

	// Right Border
	borderShapeRight.addPoint(getX() + getWidth(), getY());
	borderShapeRight.addPoint(getX() + getWidth(), getY() + getHeight());
	borderShapeRight.addPoint((int) (getX() + getWidth() * 0.95),
		(int) (getY() + getHeight() * 0.03));
	borderShapeRight.addPoint((int) (getX() + getWidth() * 0.95),
		(int) (getY() + getHeight() * 0.97));

    }

    public boolean isBomb() {
	return this.bomb;
    }

    public int getBombsSurrounding() {
	return bombsSurrounding;
    }

    public void clicked() {
	this.isClicked = true;
    }
    
    public void unclick(){
	this.isClicked = false;
    }

    public boolean getClicked() {
	return isClicked;
    }

    public void flag() {
	this.isFlagged = true;
    }

    public void unflag() {
	this.isFlagged = false;
    }

    public boolean getFlagged() {
	return isFlagged;
    }

    public int getBoardX() {
	return boardX;
    }

    public int getBoardY() {
	return boardY;
    }
}
