package com.S.P;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Mob extends Moveable implements AI{
	private static final Texture ss = new Texture("notPlayer.png");
	TextureRegion mob = TextureRegion.split(ss, 32, 48)[0][0];
	
	public Mob() {
		super();
	}

	public Mob(World world, Vector2 Position) {
		super(world);
		this.position = Position;
	}
	
	public TextureRegion getTexture() {
		return mob;
	}

	@Override
	public void move(float deltaTime) {
		boolean up, right;
		acceleration.add(world.gravity);
		velocity.add(acceleration);
		position.add(velocity);
		up = (velocity.y >= 0);
		right = (velocity.x >= 0);
		if (up) {
			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].isSolid()) {
				if (this.position.y + this.getTexture().getRegionHeight() >= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].position.y - this.getTexture().getRegionHeight());
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 1)][(int) ((this.position.y / 16) + 3)].isSolid()) {
				if (this.position.y + this.getTexture().getRegionHeight() >= world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16) + 3)].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16) + 3)].position.y - this.getTexture().getRegionHeight());
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16) + 3)].isSolid()) {
				if (this.position.y + this.getTexture().getRegionHeight() >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 3)].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 3)].position.y - this.getTexture().getRegionHeight());
				}
			}
		} else {
			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.y - this.getTexture().getRegionHeight() <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y + 16);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 1)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.y - this.getTexture().getRegionHeight() <= world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16))].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16))].position.y + 16);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.y - this.getTexture().getRegionHeight() <= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.y + 16);
				}
			}
		}
		if (right) {
			if (world.blocks[(int) (this.position.x / 16 + 2)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.x + this.getTexture().getRegionWidth() >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.x) {
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.x - this.getTexture().getRegionWidth(), position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16) + 1)].isSolid()) {
				if (this.position.x + this.getTexture().getRegionWidth() >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 1)].position.x) {
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 1)].position.x - this.getTexture().getRegionWidth(), position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16) + 2)].isSolid()) {
				if (this.position.x + this.getTexture().getRegionWidth() >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 2)].position.x) {
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 2)].position.x - this.getTexture().getRegionWidth(), position.y);
				}
			}
		} else {
			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.x - this.getTexture().getRegionWidth() <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x) {
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x + 16,position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16))][(int) ((this.position.y / 16) + 1)].isSolid()) {
				if (this.position.x - this.getTexture().getRegionWidth() <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 1)].position.x){
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 1)].position.x + 16, position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16))][(int) ((this.position.y / 16) + 2)].isSolid()) {
				if (this.position.x - this.getTexture().getRegionWidth() <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 2)].position.x) {
					velocity.set(0, velocity.y);
					position.set(world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 2)].position.x + 16, position.y);
				}
			}
		}

		velocity.setZero();
		acceleration.setZero();
	}

	public Rectangle getBounds() {
		return new Rectangle(this.position.x + 16, this.position.y + 24, 32, 48);
	}
}
