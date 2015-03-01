package com.S.P;

import java.util.Vector;

import com.badlogic.gdx.math.Vector2;

public class World {

	Vector2 gravity;
	Vector<Moveable> moveables;
	Block[][] blocks;
	
	
	
	public World(){
		setGravity(new Vector2(0, -9.8f));
		blocks = new Block[50][50];
	}
	
	public World(Vector2 gravity, int WorldHeight, int WorldWidth){
		setGravity(gravity);
		//TODO check x and y
		blocks = new Block[WorldHeight][WorldWidth];
	}

	private void generateWorld(){
		for(int x = 0; x < blocks.length; x++){
			for(int y = 0; y < blocks[x].length; y++){
				if(y < 53) blocks[x][y] = new Block(new Vector2(x, y), Block.DIRT);
				if(y > 53) blocks[x][y] = new Block(new Vector2(x, y), Block.AIR);
			}
		}
	}
	
	private void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}

	
}
