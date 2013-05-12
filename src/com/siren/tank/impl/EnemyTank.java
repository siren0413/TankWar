package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyTank extends GeneralTank {

	
	
	
	
	public EnemyTank(int x, int y) {
		super(x, y);
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
	
	
}
