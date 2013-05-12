package com.siren.tank;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface Tank {

	void drawTank(Graphics g);
	void keyPressed(KeyEvent e);
	void keyReleased(KeyEvent e);
}
