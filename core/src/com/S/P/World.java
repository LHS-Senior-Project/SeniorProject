package com.S.P;

import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class World {

	Vector2 gravity;
	Vector<Moveable> moveables;
	Block[][] blocks;
	Player player;
	
	
	
	public World(){
		setGravity(new Vector2(0, -9.8f));
		blocks = new Block[50][50];
		generateWorld();
		moveables = new Vector<Moveable>();
	}
	
	public World(Vector2 gravity, int WorldHeight, int WorldWidth){
		setGravity(gravity);
		blocks = new Block[WorldHeight+2][WorldWidth];
		generateWorld();
		moveables = new Vector<Moveable>();
	}

	public void addMoveable(Moveable m){
		this.moveables.add(m);
	}
	
	public void setPlayer(Player p){
		this.player = p;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	private void generateWorld(){
		for(int x = 0; x < blocks.length; x++){
			for(int y = 0; y < blocks[x].length; y++){
				blocks[x][y] = new Block(new Vector2(x * 16, y * 16), Block.DIRT);
				if(y > 53) blocks[x][y] = new Block(new Vector2(x * 16, y * 16), Block.AIR);
			}
		}
		for(int i = 0; i<blocks.length;i++){
			blocks[i][0] = new Block(new Vector2(i * 16, 0), Block.VOID);
			blocks[i][blocks[0].length-1] = new Block(new Vector2(i * 16, (blocks[0].length-1) * 16), Block.VOID);
		}
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}
	
	public void setBlock(int x, int y, TextureRegion material){
		blocks[x][y] = new Block(new Vector2(x*16,y*16), material);
	}
	
	private void setGravity(Vector2 gravity) {
		this.gravity = gravity;
	}

	
}
