package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;

import com.siren.tank.Tank;
import com.siren.tank.impl.GeneralTank.Direction;

public class PT {
	public int x;
	public int y;
	// paotong attributes
	public Direction ptDir = Direction.DOWN;

	public PT(int x, int y, Direction ptDir) {
		this.x = x;
		this.y = y;
		this.ptDir = ptDir;
	}

	public void drawPT(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);

		switch (ptDir) {
			case LEFT :
				g.drawLine(x + GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT / 2, x - GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT
						/ 2);
				break;
			case LEFT_UP :
				g.drawLine(x + GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT / 2, x , y );
				break;
			case UP :
				g.drawLine(x + GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT / 2, x + GeneralTank.WIDTH / 2, y - GeneralTank.HEIGHT
						/ 2);
				break;
			case RIGHT_UP :
				g.drawLine(x + GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT / 2, x + GeneralTank.WIDTH , y);
				break;
			case RIGHT :
				g.drawLine(x + GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT / 2, x + GeneralTank.WIDTH + GeneralTank.WIDTH/2  , y + GeneralTank.HEIGHT
						/ 2);
				break;
			case RIGHT_DOWN :
				g.drawLine(x + GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT / 2, x + GeneralTank.WIDTH , y + GeneralTank.HEIGHT
						);
				break;
			case DOWN :
				g.drawLine(x + GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT / 2, x + GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT + GeneralTank.HEIGHT/2
						);
				break;
			case LEFT_DOWN :
				g.drawLine(x + GeneralTank.WIDTH / 2, y + GeneralTank.HEIGHT / 2, x , y + GeneralTank.HEIGHT
						);
				break;		
				
			default :
				break;
		}
		
		g.setColor(c);
		

	}

}
