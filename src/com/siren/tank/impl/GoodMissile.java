package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.siren.client.TankClient;
import com.siren.tank.Tank;
import com.siren.tank.impl.GeneralTank.Direction;

public class GoodMissile extends GeneralMissile {

	public GoodMissile(int x, int y, Direction direction, TankClient tc) {
		super(x, y, direction, tc);
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

	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	

}
