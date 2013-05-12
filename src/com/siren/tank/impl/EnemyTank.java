package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.siren.client.TankClient;
import com.siren.tank.Missile;

public class EnemyTank extends GeneralTank {

	private static Random random = new Random(System.currentTimeMillis());
	
	public EnemyTank(int x, int y, TankClient tc) {
		super(x, y, tc);
		speed = 2;
	}

	public void drawTank(Graphics g) {
		move();
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);

		if (missiles != null) {
			for (int i = 0; i < missiles.size(); i++) {
				if (!missiles.get(i).isLive()) {
					missiles.remove(missiles.get(i));
				}
				else
					missiles.get(i).drawMissile(g);

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

	public void randomMove() {
		int index = random.nextInt(9);
		switch (index) {
			case 0 :
				direction = Direction.LEFT;
				break;
			case 1 :
				direction = Direction.LEFT_UP;
				break;
			case 2 :
				direction = Direction.UP;
				break;
			case 3 :
				direction = Direction.RIGHT_UP;
				break;
			case 4 :
				direction = Direction.RIGHT;
				break;
			case 5 :
				direction = Direction.RIGHT_DOWN;
				break;
			case 6 :
				direction = Direction.DOWN;
				break;
			case 7 :
				direction = Direction.LEFT_DOWN;
				break;
			case 8 :
				direction = Direction.STOP;
				break;

			default :
				break;
		}
	}
	
	protected Missile fire() {
		int x = this.x + GeneralTank.WIDTH / 2 - GoodMissile.WIDTH / 2;
		int y = this.y + GeneralTank.HEIGHT / 2 - GoodMissile.HEIGHT / 2;
		EnemyMissile m = new EnemyMissile(x, y, lastDirection, tc);
		missiles.add(m);
		return m;
	}

	public void startThread() {
		new Thread(new EnemyTankThread()).start();
		new Thread(new EnemyTankFireThread()).start();
	}

	private class EnemyTankThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(random.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!live)
					return;
				randomMove();
			}

		}

	}

	private class EnemyTankFireThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(200 + random.nextInt(800));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fire();
			}

		}

	}
	
	public boolean hitTank(GoodTank t) {
		if (this.getRect().intersects(t.getRect())) {
			tc.friendTank.explodes.add(new GeneralExplode(x, y));
			return true;
		}
		return false;
	}
	


}
