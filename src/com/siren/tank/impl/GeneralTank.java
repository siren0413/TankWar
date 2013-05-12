package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.siren.client.TankClient;
import com.siren.tank.Missile;
import com.siren.tank.Tank;

public class GeneralTank implements Tank {

	// tank attributes
	protected int x = 50;
	protected int y = 50;
	public int speed = 5;
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	public boolean live = true;
	// keyboard attributes
	protected boolean left = false, up = false, right = false, down = false;
	enum Direction {
		LEFT, LEFT_UP, UP, RIGHT_UP, RIGHT, RIGHT_DOWN, DOWN, LEFT_DOWN, STOP
	}
	protected Direction direction = Direction.STOP;
	protected Direction lastDirection = Direction.DOWN;
	// component attributes
	public List<GeneralMissile> missiles = new LinkedList<GeneralMissile>();
	public List<GeneralExplode> explodes = new LinkedList<GeneralExplode>();
	protected PT friendPT = null;
	public TankClient tc = null;

	/**
	 * constructor
	 * 
	 * @param x
	 * @param y
	 */
	public GeneralTank(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		friendPT = new PT(x, y, lastDirection);
		this.tc = tc;
	}

	/**
	 * draw a friend tank on canvas.
	 */
	@Override
	public void drawTank(Graphics g) {

		move();

		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);

		if (missiles != null) {
			for (int i = 0; i < missiles.size(); i++) {
				if (!missiles.get(i).isLive())
					missiles.remove(missiles.get(i));
				else
					missiles.get(i).drawMissile(g);

			}
		}

		if (explodes != null) {
			for (int i = 0; i < explodes.size(); i++) {
				if (!explodes.get(i).isLive())
					explodes.remove(explodes.get(i));
				else
					explodes.get(i).drawExplode(g);

			}
		}

		if (friendPT != null) {
			friendPT.x = x;
			friendPT.y = y;
			friendPT.ptDir = lastDirection;
			friendPT.drawPT(g);
		}

		drawTankInfo(g);
	}
	/**
	 * draw tank information.
	 */
	public void drawTankInfo(Graphics g) {
		g.drawString("missiles count:" + missiles.size(), 10, 40);
		g.drawString("explodes count:" + explodes.size(), 10, 55);
	}
	/**
	 * change the value of x and y by direction.
	 */
	protected void move() {
		switch (direction) {
			case LEFT :
				x -= speed;
				break;
			case LEFT_UP :
				x -= speed;
				y -= speed;
				break;
			case UP :
				y -= speed;
				break;
			case RIGHT_UP :
				x += speed;
				y -= speed;
				break;
			case RIGHT :
				x += speed;
				break;
			case RIGHT_DOWN :
				x += speed;
				y += speed;
				break;
			case DOWN :
				y += speed;
				break;
			case LEFT_DOWN :
				x -= speed;
				y += speed;
				break;
			case STOP :
				break;

			default :
				break;
		}

		// set the last direction.
		if (direction != Direction.STOP)
			lastDirection = direction;

		if (x < 0)
			x = 0;
		if (y < 20)
			y = 20;
		if (x + WIDTH > TankClient.SIZE_X)
			x = TankClient.SIZE_X - WIDTH;
		if (y + HEIGHT > TankClient.SIZE_Y)
			y = TankClient.SIZE_Y - HEIGHT;
	}
	/**
	 * when the key pressed, tank moves.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// set direction keys.
		setKeys(e, true);
		// set the direction of tank.
		setTankDirection();
		// set control key
		switch (e.getKeyCode()) {
			case KeyEvent.VK_CONTROL :
				fire();
				break;
			default :
				break;
		}
	}

	/**
	 * when the key released, tank direction changes.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// set direction keys.
		setKeys(e, false);
		// set the direction of tank.
		setTankDirection();

	}

	/**
	 * set the boolean value of direction keys.
	 * 
	 * @param e
	 * @param flag
	 */
	protected void setKeys(KeyEvent e, boolean flag) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_RIGHT :
				right = flag;
				break;
			case KeyEvent.VK_DOWN :
				down = flag;
				break;
			case KeyEvent.VK_LEFT :
				left = flag;
				break;
			case KeyEvent.VK_UP :
				up = flag;
				break;
			default :
				break;
		}
	}

	/**
	 * set the direction of tanks. there are 8 direction and 1 stop.
	 */
	protected void setTankDirection() {
		if (left && !up && !right && !down)
			direction = Direction.LEFT;
		else if (left && up && !right && !down)
			direction = Direction.LEFT_UP;
		else if (!left && up && !right && !down)
			direction = Direction.UP;
		else if (!left && up && right && !down)
			direction = Direction.RIGHT_UP;
		else if (!left && !up && right && !down)
			direction = Direction.RIGHT;
		else if (!left && !up && right && down)
			direction = Direction.RIGHT_DOWN;
		else if (!left && !up && !right && down)
			direction = Direction.DOWN;
		else if (left && !up && !right && down)
			direction = Direction.LEFT_DOWN;
		else if (!left && !up && !right && !down)
			direction = Direction.STOP;

	}

	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}

	protected Missile fire() {
		int x = this.x + GeneralTank.WIDTH / 2 - GoodMissile.WIDTH / 2;
		int y = this.y + GeneralTank.HEIGHT / 2 - GoodMissile.HEIGHT / 2;
		GoodMissile m = new GoodMissile(x, y, lastDirection, tc);
		missiles.add(m);
		return m;
	}

}
