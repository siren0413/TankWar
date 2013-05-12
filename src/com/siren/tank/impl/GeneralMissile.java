package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;

import com.siren.client.TankClient;
import com.siren.tank.Missile;
import com.siren.tank.Tank;
import com.siren.tank.impl.GeneralTank.Direction;

public class GeneralMissile implements Missile {

	public int x;
	public int y;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;
	public static final int SPEED = 10;
	public GeneralTank.Direction direction;
	protected boolean live = true;

	public GeneralMissile(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public void drawMissile(Graphics g) {
		move();
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
	}

	protected void move() {
		switch (direction) {
			case LEFT :
				x -= SPEED;
				break;
			case LEFT_UP :
				x -= SPEED;
				y -= SPEED;
				break;
			case UP :
				y -= SPEED;
				break;
			case RIGHT_UP :
				x += SPEED;
				y -= SPEED;
				break;
			case RIGHT :
				x += SPEED;
				break;
			case RIGHT_DOWN :
				x += SPEED;
				y += SPEED;
				break;
			case DOWN :
				y += SPEED;
				break;
			case LEFT_DOWN :
				x -= SPEED;
				y += SPEED;
				break;
			case STOP :
				break;

			default :
				break;
		}

		if (x < 0 || y < 0 || x > TankClient.SIZE_X || y > TankClient.SIZE_Y) {
			live = false;
		}
	}

	/**
	 * getters and setters.
	 * 
	 * @return
	 */
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

}
