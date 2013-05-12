package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;

import com.siren.tank.Tank;
import com.siren.tank.impl.FriendTank.Direction;

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
				g.drawLine(x + FriendTank.WIDTH / 2, y + FriendTank.HEIGHT / 2, x - FriendTank.WIDTH / 2, y + FriendTank.HEIGHT
						/ 2);
				break;
			case LEFT_UP :
				g.drawLine(x + FriendTank.WIDTH / 2, y + FriendTank.HEIGHT / 2, x , y );
				break;
			case UP :
				g.drawLine(x + FriendTank.WIDTH / 2, y + FriendTank.HEIGHT / 2, x + FriendTank.WIDTH / 2, y - FriendTank.HEIGHT
						/ 2);
				break;
			case RIGHT_UP :
				g.drawLine(x + FriendTank.WIDTH / 2, y + FriendTank.HEIGHT / 2, x + FriendTank.WIDTH , y);
				break;
			case RIGHT :
				g.drawLine(x + FriendTank.WIDTH / 2, y + FriendTank.HEIGHT / 2, x + FriendTank.WIDTH + FriendTank.WIDTH/2  , y + FriendTank.HEIGHT
						/ 2);
				break;
			case RIGHT_DOWN :
				g.drawLine(x + FriendTank.WIDTH / 2, y + FriendTank.HEIGHT / 2, x + FriendTank.WIDTH , y + FriendTank.HEIGHT
						);
				break;
			case DOWN :
				g.drawLine(x + FriendTank.WIDTH / 2, y + FriendTank.HEIGHT / 2, x + FriendTank.WIDTH / 2, y + FriendTank.HEIGHT + FriendTank.HEIGHT/2
						);
				break;
			case LEFT_DOWN :
				g.drawLine(x + FriendTank.WIDTH / 2, y + FriendTank.HEIGHT / 2, x , y + FriendTank.HEIGHT
						);
				break;		
				
			default :
				break;
		}
		
		g.setColor(c);
		

	}

}
