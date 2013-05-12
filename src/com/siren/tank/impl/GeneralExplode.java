package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;

public class GeneralExplode {
	public int x, y;
	public boolean live = true;

	int[] diameter = {4, 7, 12, 18, 22, 26, 32, 40, 45, 49, 30, 14, 6};
	int step = 0;

	public GeneralExplode(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void drawExplode(Graphics g) {
		if (!live)
			return;
		if (step == diameter.length) {
			live = false;
			step = 0;
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x, y, diameter[step], diameter[step]);
		g.setColor(c);
		step++;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

}
