package com.siren.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.siren.tank.Tank;
import com.siren.tank.impl.EnemyTank;
import com.siren.tank.impl.GeneralTank;
import com.siren.tank.impl.GoodTank;

@SuppressWarnings("serial")
public class TankClient extends Frame {

	// Tank
	GoodTank friendTank = new GoodTank(50, 50);
	EnemyTank enemyTank = new EnemyTank(100, 100);
	// frame attributes
	public static final int LOC_X = 400;
	public static final int LOC_Y = 300;
	public static final int SIZE_X = 800;
	public static final int SIZE_Y = 600;
	public static final String TITLE = "Tank War";
	// image attributes
	private Image offScreenImage = null;

	/**
	 * main function, the entry point of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();

		// start all thread
		tc.startThread();

	}

	/**
	 * initialize and launch of the main frame.
	 */
	private void launchFrame() {

		// initialize frame
		this.setLocation(LOC_X, LOC_Y);
		this.setSize(SIZE_X, SIZE_Y);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setBackground(Color.GREEN);

		// add listener
		addFrameListener();

	}

	/**
	 * set frame action, such as closing window.
	 */
	private void addFrameListener() {

		// close window
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// keyboard listener
		addKeyListener(new KeyMonitor());
	}

	/**
	 * start thread
	 */
	private void startThread() {
		new Thread(new RepaintThread()).start();
		new Thread(new TankHitThread()).start();
	}

	/**
	 * paint 1. get the original color of graphics. 2. set the graphics to our
	 * customized color. 3. draw a oval in paint with our customized color. 4.
	 * set back the color to its original color.
	 */
	@Override
	public void paint(Graphics g) {
		if (friendTank != null)
			friendTank.drawTank(g);
		if (enemyTank != null)
			enemyTank.drawTank(g);
	}

	/**
	 * double buffer to display image. 1. create an image. 2. paint the image.
	 * 3. draw the image on canvas.
	 */
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null)
			offScreenImage = this.createImage(SIZE_X, SIZE_Y);

		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, SIZE_X, SIZE_Y);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	/**
	 * keyboard monitor.
	 * 
	 * @author Siren
	 * 
	 */
	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			friendTank.keyPressed(e);

		}

		@Override
		public void keyReleased(KeyEvent e) {
			friendTank.keyReleased(e);
		}

	}

	/**
	 * RepaintThread, which repaint the canvas after some time duration.
	 * 
	 * @author Siren
	 * 
	 */
	private class RepaintThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	private class TankHitThread implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for (int i = 0; i < friendTank.friendMissiles.size(); i++) {

					if (enemyTank != null && friendTank.friendMissiles.get(i).hitTank(enemyTank)) {
						friendTank.friendMissiles.remove(i);
						enemyTank = null;
					}
				}
			}

		}

	}

}
