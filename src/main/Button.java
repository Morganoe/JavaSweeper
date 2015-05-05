package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Button
{

	private final int x;
	private final int y;
	private final int width;
	private final int height;
	private final int roundedness;
	private String text;

	public Button(String text, int x, int y, int width, int height)
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.roundedness = 0;
	}

	public Button(String text, int x, int y, int width, int height, int roundedness)
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.roundedness = roundedness;
	}

	public boolean isClicked(Input input, int mouseX, int mouseY)
	{
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			return mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y
							&& mouseY < this.y + this.height;
		}
		return false;
	}

	public boolean mouseIsOver(Input input, int mouseX, int mouseY)
	{
		return mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y
						&& mouseY < this.y + this.height;
	}

	public void draw(Graphics g)
	{
		int stringWidth = g.getFont().getWidth(text);
		int stringHeight = g.getFont().getHeight(text);
		int centerX = x + (width / 2) - (stringWidth / 2);
		int centerY = y + (height / 2) - (stringHeight / 2);
		if (roundedness > 0)
		{
			drawRounded(g, centerX, centerY);
		} else
		{
			drawNonRounded(g, centerX, centerY);
		}
	}

	public void fill(Graphics g, Color buttonColor, Color stringColor)
	{
		int stringWidth = g.getFont().getWidth(text);
		int stringHeight = g.getFont().getHeight(text);
		int centerX = x + (width / 2) - (stringWidth / 2);
		int centerY = y + (height / 2) - (stringHeight / 2);
		if (roundedness > 0)
		{
			fillRounded(g, centerX, centerY, buttonColor, stringColor);
		} else
		{
			fillNonRounded(g, centerX, centerY, buttonColor, stringColor);
		}
	}

	private void drawRounded(Graphics g, int stringX, int stringY)
	{
		g.drawRoundRect(x, y, width, height, roundedness);
		g.drawString(text, stringX, stringY);
	}

	private void drawNonRounded(Graphics g, int stringX, int stringY)
	{
		g.drawRect(x, y, width, height);
		g.drawString(text, stringX, stringY);
	}

	private void fillRounded(Graphics g, int stringX, int stringY, Color buttonColor,
					Color stringColor)
	{
		Color prevColor = g.getColor();
		g.setColor(buttonColor);
		g.fillRoundRect(x, y, width, height, roundedness);
		g.setColor(stringColor);
		g.drawString(text, stringX, stringY);
		g.setColor(prevColor);
	}

	private void fillNonRounded(Graphics g, int stringX, int stringY, Color buttonColor,
					Color stringColor)
	{
		Color prevColor = g.getColor();
		g.setColor(buttonColor);
		g.fillRect(x, y, width, height);
		g.setColor(stringColor);
		g.drawString(text, stringX, stringY);
		g.setColor(prevColor);
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getRoundedness()
	{
		return roundedness;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String newText)
	{
		this.text = newText;
	}

}
