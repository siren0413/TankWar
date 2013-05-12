package com.siren.tank.impl;

import com.siren.client.TankClient;
import com.siren.tank.impl.GeneralTank.Direction;

public class EnemyMissile extends GeneralMissile {

	public EnemyMissile(int x, int y, Direction direction, TankClient tc) {
		super(x, y, direction, tc);
	}

	
}
