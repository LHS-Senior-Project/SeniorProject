package com.S.P;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Mob extends Moveable {
	private static final Texture ss = new Texture("notPlayer.png");
	TextureRegion mob = TextureRegion.split(ss, 32, 48)[0][0];
	public Vector2 staticTarget;
	public Vector2 lastPosition;
	public Moveable moveableTarget;
	public boolean collideLeft;
	public boolean collideRight;
	public boolean collideUp;
	
	public void setTarget(Vector2 blockLocation) {
		this.staticTarget = blockLocation;
		this.moveableTarget = null;
	}

	public Vector2 getStaticTarget() {
		return this.staticTarget;
	}

	public void setTarget(Moveable movingObject) {
		this.moveableTarget = movingObject;
		this.staticTarget = null;
	}

	public Moveable getMoveableTarget() {
		return this.moveableTarget;
	}

	public Vector2 getTarget() {
		if (this.staticTarget == null)
			return this.moveableTarget.position;
		else
			return this.staticTarget;

	}

	public void AIUpdate() {
		boolean up, right;
		right = (this.position.x <= getTarget().x);
		up = (this.position.y <= getTarget().y);
		//System.out.println("last position: " + this.lastPosition + "\tthis position: " + this.position);
		if (!collideRight&&!collideLeft||collideUp) {
			if (up) {
				this.addAccel(0.0f, 10.0f);
			}
			if (right) {
				this.addAccel(5.0f, 0.0f);
			} else {
				this.addAccel(-5.0f, 0.0f);
			}
		}
		else{
			//System.out.println(right);
			if (right) {
				this.addAccel(0.0f, 50.0f);
			} else {
				this.addAccel(0.0f, 50.0f);
			}
		}

	}

	public Mob() {
		super();
	}

	public Mob(World world, Vector2 Position) {
		super(world);
		this.position = Position;
		this.lastPosition = this.position;
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
		collideLeft = false;
		collideUp = false;
		collideRight = false;
		if (up) {
			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].isSolid()) {
				if (this.position.y + this.getTexture().getRegionHeight() >= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].position.y) {
					velocity.set(velocity.x, 0);
					collideUp = true;
					position.set(position.x, world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].position.y - this.getTexture().getRegionHeight());
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 1)][(int) ((this.position.y / 16) + 3)].isSolid()) {
				if (this.position.y + this.getTexture().getRegionHeight() >= world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16) + 3)].position.y) {
					velocity.set(velocity.x, 0);
					collideUp = true;
					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16) + 3)].position.y - this.getTexture().getRegionHeight());
				}
			}
//			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16) + 3)].isSolid()) {
//				if (this.position.y + this.getTexture().getRegionHeight() >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 3)].position.y) {
//					velocity.set(velocity.x, 0);
//					collideUp = true;
//					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 3)].position.y - this.getTexture().getRegionHeight());
//				}
//			}
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
//			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16))].isSolid()) {
//				if (this.position.y - this.getTexture().getRegionHeight() <= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.y) {
//					velocity.set(velocity.x, 0);
//					position.set(position.x, world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.y + 16);
//				}
//			}
		}
		if (right) {
			if (world.blocks[(int) (this.position.x / 16 + 2)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.x + this.getTexture().getRegionWidth() >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.x) {
					velocity.set(0, velocity.y);
					collideRight = true;
					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.x - this.getTexture().getRegionWidth(), position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16) + 1)].isSolid()) {
				if (this.position.x + this.getTexture().getRegionWidth() >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 1)].position.x) {
					velocity.set(0, velocity.y);
					collideRight = true;
					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 1)].position.x - this.getTexture().getRegionWidth(), position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16) + 2)][(int) ((this.position.y / 16) + 2)].isSolid()) {
				if (this.position.x + this.getTexture().getRegionWidth() >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 2)].position.x) {
					velocity.set(0, velocity.y);
					collideRight = true;
					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16) + 2)].position.x - this.getTexture().getRegionWidth(), position.y);
				}
			}
		} else {
			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.x - this.getTexture().getRegionWidth() <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x) {
					velocity.set(0, velocity.y);
					collideLeft = true;
					position.set(world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x + 16, position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16))][(int) ((this.position.y / 16) + 1)].isSolid()) {
				if (this.position.x - this.getTexture().getRegionWidth() <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 1)].position.x) {
					velocity.set(0, velocity.y);
					collideLeft = true;
					position.set(world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 1)].position.x + 16, position.y);
				}
			}
			if (world.blocks[(int) ((this.position.x / 16))][(int) ((this.position.y / 16) + 2)].isSolid()) {
				if (this.position.x - this.getTexture().getRegionWidth() <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 2)].position.x) {
					velocity.set(0, velocity.y);
					collideLeft = true;
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
