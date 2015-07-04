package com.S.P;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Moveable {

	private static final Texture	ss		= new Texture("player.png");
	TextureRegion					player	= TextureRegion.split(ss, 32, 48)[0][0];

	public Player() {
		super();
	}

	public Player(World world, Vector2 Position) {
		super(world);
		this.position = Position;
	}

	public TextureRegion getTexture() {
		return player;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.position.x + 16, this.position.y + 24, 32, 48);
	}
}
