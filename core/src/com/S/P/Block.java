package com.S.P;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {

	Item itemProduced;
	Vector2 position;
	TextureRegion blockTexture;
	boolean solid;
	
	private static final Texture blocks = new Texture("sky (1).png"); 
	static final TextureRegion AIR = TextureRegion.split(blocks, 16, 16)[0][14];
	static final TextureRegion DIRT = TextureRegion.split(blocks, 16, 16)[0][1];
	static final TextureRegion VOID = TextureRegion.split(blocks, 16, 16)[1][1];
	
	public Block(){
		
	}
		
	public Block(Vector2 position, TextureRegion dirt2){
		this.blockTexture = dirt2;
		this.position = position;
		this.itemProduced = null;
		this.solid = !blockTexture.equals(AIR);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(this.position.x+8, this.position.y+8, 16, 16);
	}
	
	public boolean isSolid(){
		return solid;
	}
	
}
