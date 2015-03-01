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
		generateWorld();
	}
	
	public World(Vector2 gravity, int WorldHeight, int WorldWidth){
		setGravity(gravity);
		blocks = new Block[WorldHeight][WorldWidth];
		generateWorld();
	}

	private void generateWorld(){
		for(int x = 0; x < blocks.length; x++){
			for(int y = 0; y < blocks[x].length; y++){
				blocks[x][y] = new Block(new Vector2(x * 16, y * 16), Block.DIRT);
				if(y > 53) blocks[x][y] = new Block(new Vector2(x * 16, y * 16), Block.AIR);
			}
		}
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
	
	private void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}

	
}
