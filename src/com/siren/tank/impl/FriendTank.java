package com.siren.tank.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.siren.client.TankClient;
import com.siren.tank.Missile;
import com.siren.tank.Tank;

public class FriendTank implements Tank {

	// tank attributes
	private int x = 50;
	private int y = 50;
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	public static final int SPEED = 5;
	// keyboard attributes
	private boolean left = false, up = false, right = false, down = false;
	enum Direction {
		LEFT, LEFT_UP, UP, RIGHT_UP, RIGHT, RIGHT_DOWN, DOWN, LEFT_DOWN, STOP
	}
	private Direction direction = Direction.STOP;
	private Direction lastDirection = Direction.DOWN;
	// component attributes
	public List<FriendMissile> friendMissiles = new LinkedList<FriendMissile>();
	private PT friendPT = null;

	/**
	 * constructor
	 * 
	 * @param x
	 * @param y
	 */
	public FriendTank(int x, int y) {
		this.x = x;
		this.y = y;
		friendPT = new PT(x, y, lastDirection);
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

		drawTankInfo(g);
	}
	/**
	 * draw tank information.
	 */
	public void drawTankInfo(Graphics g) {
		g.drawString("missiles count:" + friendMissiles.size(), 10, 50);
	}

	/**
	 * change the value of x and y by direction.
	 */
	private void move() {
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
	/**
	 * when the key pressed, tank moves.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// set direction keys.
		setKeys(e, true);
		// set the direction of tank.
		setTankDirection();
		// set the last direction.
		if (direction != Direction.STOP)
			lastDirection = direction;
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
	private void setKeys(KeyEvent e, boolean flag) {
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
	private void setTankDirection() {
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

	private Missile fire() {
		int x = this.x + FriendTank.WIDTH / 2 - FriendMissile.WIDTH / 2;
		int y = this.y + FriendTank.HEIGHT / 2 - FriendMissile.HEIGHT / 2;
		FriendMissile m = new FriendMissile(x, y, lastDirection);
		friendMissiles.add(m);
		return m;
	}

}
