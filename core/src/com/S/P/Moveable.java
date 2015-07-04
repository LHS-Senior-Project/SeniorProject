package com.S.P;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Moveable {
	
	int		posZ;
	Vector2	position;

	boolean	collideable;
	Vector2	size;

	Vector2	velocity;
	Vector2	acceleration;
	Vector2	facing;
	World	world;

	public Moveable() {

		position = new Vector2(0f, 0f);
		acceleration = new Vector2(0f, 0f);
		velocity = new Vector2(0f, 0f);
		facing = new Vector2(0f, 0f);
		world = null;
	}

	public Moveable(World world) {
		position = new Vector2(0f, 0f);
		acceleration = new Vector2(0f, 0f);
		velocity = new Vector2(0f, 0f);
		facing = new Vector2(0f, 0f);
		this.world = world;
	}

	public void addAccel(float x, float y) {
		acceleration.add(x, y);
	}

	public void setCollideable(Vector2 size) {
		this.size = size;
		collideable = true;
	}

	public void move(float deltaTime) {

		acceleration.add(world.gravity);

		velocity.add(acceleration);

		velocity.add(new Vector2((acceleration.x - (.1f * velocity.x)), (acceleration.y * -.85f * deltaTime)));
		collide();
		position.add(new Vector2(velocity.x * deltaTime, velocity.y * deltaTime));
		acceleration.setZero();
	}

	public void collide() {

		if (!collideable)
			return;

		boolean up, right;

		int blockWidth = (int) (this.size.x / 16);
		int blockHeight = (int) (this.size.y / 16);
		int xIndex = 0;
		int yIndex = 0;

		up = (velocity.y >= 0);
		right = (velocity.x >= 0);

		if (up) {
			for (int x = 0; x < blockWidth; x++) {
				if (world.blocks[(int) (this.position.x / 16) + x][(int) ((this.position.y / 16) + blockHeight)].isSolid()) {
					if (this.position.y + this.size.y >= world.blocks[(int) (this.position.x / 16) + x][(int) ((this.position.y / 16) + blockHeight)].position.y) {
						velocity.set(velocity.x, 0);
						acceleration.set(acceleration.x, 0);
						position.set(position.x, world.blocks[(int) (this.position.x / 16) + x][(int) ((this.position.y / 16) + blockHeight)].position.y - this.size.y);
					}
				}
			}
		} else {
			for (int x = 0; x < blockWidth; x++) {
				xIndex = (int) (this.position.x / 16) + x;
				yIndex = (int) (this.position.y / 16);
				System.out.println(xIndex + " " + yIndex);
				if (world.blocks[xIndex][yIndex].isSolid()) {
					if (this.position.y >= world.blocks[xIndex][yIndex].position.y) {
						velocity.set(velocity.x, 0);
						acceleration.set(acceleration.x, 0);
						position.set(position.x, world.blocks[xIndex][yIndex].position.y + 16);

					}
				}
			}

		}
		if (right) {
			for (int y = 0; y < blockHeight; y++) {
				if (world.blocks[(int) (this.position.x / 16) + blockWidth][(int) ((this.position.y / 16) + y)].isSolid()) {
					if (this.position.x + this.size.x >= world.blocks[(int) (this.position.x / 16) + blockWidth][(int) ((this.position.y / 16) + y)].position.y) {
						velocity.set(0, velocity.y);
						acceleration.set(0, acceleration.y);
						position.set(world.blocks[(int) (this.position.x / 16) + blockWidth][(int) ((this.position.y / 16) + y)].position.x - this.size.x, position.y);
					}
				}
			}

		} else {
			for (int y = 0; y < blockHeight; y++) {
				if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + y)].isSolid()) {
					if (this.position.x + this.size.x >= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + y)].position.y) {
						velocity.set(0, velocity.y);
						acceleration.set(0, acceleration.y);
						position.set(world.blocks[(int) (this.position.x / 16) + 1][(int) ((this.position.y / 16) + y)].position.x, position.y);
					}
				}
			}
		}

	}
}
