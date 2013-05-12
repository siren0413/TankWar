package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.siren.client.TankClient;

public class EnemyTank extends GeneralTank {

	
	
	
	
	public EnemyTank(int x, int y, TankClient tc) {
		super(x, y, tc);
	}
	
	public void drawTank(Graphics g) {

		move();

		Color c = g.getColor();
		g.setColor(Color.BLUE);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);

		if (friendMissiles != null) {
			for (int i = 0; i < friendMissiles.size(); i++) {
				if (!friendMissiles.get(i).isLive())
					friendMissiles.remove(friendMissiles.get(i));
				else
					friendMissiles.get(i).drawMissile(g);

			}
		}

		if (friendPT != null) {
			friendPT.x = x;
			friendPT.y = y;
			friendPT.ptDir = lastDirection;
			friendPT.drawPT(g);
		}

	}

	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	

	/**
	 * change the value of x and y by direction.
	 */
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

		if (x < 0)
			x = 0;
		if (y < 20)
			y = 20;
		if (x + WIDTH > TankClient.SIZE_X)
			x = TankClient.SIZE_X - WIDTH;
		if (y + HEIGHT > TankClient.SIZE_Y)
			y = TankClient.SIZE_Y - HEIGHT;
		
			
	}
	
	
}
