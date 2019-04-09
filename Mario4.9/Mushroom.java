package com.tutorial.entity.powerup;

import java.awt.Graphics;
import java.util.Random;

import com.tutoial.mario.tile.Tile;
import com.tutorial.entity.Entity;
import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

public class Mushroom extends Entity {
	private Random random = new Random();

	public Mushroom(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		
		int dir = random.nextInt(2);
		
		switch (dir) {
		case 0:
			setVelX(-1);
			break;
		case 1:
			setVelX(1);
			break;
		}
		}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.mushroom.getBufferedImage(), x, y, width, height, null);
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		for(Tile t:handler.tile) {
			if(!t.solid)break;
			if(t.getId()==Id.mushroom) {
				if(getBoundsBottom().intersects(t.getBounds())) {
					setVelY(0);
					//y = t.getY()-t.height;
					if(falling) falling = false;
				}else {
					if(!falling) {
						gravity = 0.8;
						falling = true;
					}
				}
				if(getBoundsLeft().intersects(t.getBounds())) {
					setVelX(1);
				}
				if(getBoundsRight().intersects(t.getBounds())) {
					setVelX(-1);
				}
			}
		}
		if(falling) {
			gravity+=0.1;
			setVelY((int)gravity);
		}
	}

}
