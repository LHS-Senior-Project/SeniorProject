package com.S.P;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player extends Moveable {

	private static final Texture ss = new Texture("player.png");
	TextureRegion player = TextureRegion.split(ss, 32, 48)[0][0];

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

	@Override
	public void move(float deltaTime) {
		boolean up, right;
		acceleration.add(world.gravity);
		velocity.add(acceleration);
		// if(Math.abs(velocity.x) <= .10f) velocity.set(0, velocity.y);
		// if(Math.abs(velocity.y) <= .10f) velocity.set( velocity.x, 0);
		// velocity.add(velocity.x / 5000000, 0);
		// velocity.add(world.gravity);
		// velocity.add(world.gravity);
		position.add(velocity);
		up = (velocity.y >= 0);
		right = (velocity.x >= 0);
		if (up) {
			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].isSolid()) {
				if (this.position.y + this.player.getRegionHeight() >= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].position.y) {
					// if(this.getBounds().contains(50f,100f)){
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].position.y - 48);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 1)][(int) ((this.position.y / 16) + 3)].isSolid()) {
				if (this.position.y + this.player.getRegionHeight() >= world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16) + 3)].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16) + 3)].position.y - 48);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16) + 3)].isSolid()) {
				if (this.position.y + this.player.getRegionHeight() >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 3)].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 3)].position.y - 48);
				}
			}
			// check blocks above
		} else {
			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
				// if (this.position.y <= world.blocks[(int) (this.position.x /
				// 16)][(int) ((this.position.y / 16) - 1)].position.y + 16) {
				if (this.position.y - 48 <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y) {
					// if(this.getBounds().contains(50f,100f)){
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y + 16);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 1)][(int) ((this.position.y / 16))].isSolid()) {
				// if (this.position.y <= world.blocks[(int) (this.position.x /
				// 16) + 1][(int) ((this.position.y / 16) - 1)].position.y + 16)
				// {
				if (this.position.y - 48 <= world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16))].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16))].position.y + 16);
				}
			}
//			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16))].isSolid()) {
//				// if (this.position.y <= world.blocks[(int) (this.position.x /
//				// 16) + 2][(int) ((this.position.y / 16) - 1)].position.y + 16)
//				// {
//				if (this.position.y - 48 <= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.y) {
//					velocity.set(velocity.x, 0);
//					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.y + 16);
//				}
//			}
			// check blocks below
		}
		if (right) {
			if (world.blocks[(int) (this.position.x / 16 + 2)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.x + 32 >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.x) {
					// if(this.getBounds().contains(50f,100f)){
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.x - 32, position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16) + 1)].isSolid()) {
				if (this.position.x + 32 >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 1)].position.x) {
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 1)].position.x - 32, position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16) + 2)].isSolid()) {
				if (this.position.x + 32 >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 2)].position.x) {
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 2)].position.x - 32, position.y);
				}
			}
			// if (world.blocks[(int) ((this.position.x / 16) + 2)][(int)
			// ((this.position.y / 16) + 3)].isSolid()) {
			// if (this.position.x + 32 >= world.blocks[(int) (this.position.x /
			// 16) + 2][(int) ((this.position.y / 16) + 3)].position.x) {
			// velocity.set(0, velocity.y);
			// position.set(world.blocks[(int) (this.position.x / 16) + 2][(int)
			// ((this.position.y / 16) + 3)].position.x-32,position.y);
			// }
			// }
			// check blocks right
		} else {
			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
				// if (this.position.y <= world.blocks[(int) (this.position.x /
				// 16)][(int) ((this.position.y / 16) - 1)].position.y + 16) {
				if (this.position.x - 32 <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x) {
					// if(this.getBounds().contains(50f,100f)){
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x + 16,position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16))][(int) ((this.position.y / 16) + 1)].isSolid()) {
				// if (this.position.y <= world.blocks[(int) (this.position.x /
				// 16) + 1][(int) ((this.position.y / 16) - 1)].position.y + 16)
				// {
				if (this.position.x - 32 <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 1)].position.x){
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 1)].position.x + 16, position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16))][(int) ((this.position.y / 16) + 2)].isSolid()) {
				// if (this.position.y <= world.blocks[(int) (this.position.x /
				// 16) + 2][(int) ((this.position.y / 16) - 1)].position.y + 16)
				// {
				if (this.position.x - 32 <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 2)].position.x) {
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 2)].position.x + 16, position.y);
				}
			}
			// check blocks below
		}

		velocity.setZero();
		acceleration.setZero();
	}

	public Rectangle getBounds() {
		return new Rectangle(this.position.x + 16, this.position.y + 24, 32, 48);
	}
}
