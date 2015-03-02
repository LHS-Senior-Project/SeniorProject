package com.S.P;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity{
	private static final Texture ss = new Texture("Magic Missile.png");
	TextureRegion projectile = TextureRegion.split(ss, 16, 16)[0][0];
	public Vector2 startPosition;
	public Vector2 staticTarget;
	public float damage;
	public float knockback;
	public float speed;
	public float AoE;
	public float penetration;
	public Item itemDrop;
	
	
	public Projectile(){
	}
	
	public Projectile(Vector2 Position, Vector2 target) {
		this.position = Position;
		this.staticTarget = target;
	}
	
	/**
	 * 
	 * @param blockLocation
	 */
	public void setTarget(Vector2 blockLocation) {
		this.staticTarget = blockLocation;
	}

	public Vector2 getTarget() {
		return this.staticTarget;
	}
	
	public void AIUpdate(){
		
	}
	
	public float getAngle(){
		return this.position.angle(this.staticTarget);
	}
	
	/**
	 * 
	 */
	@Override
	public void move(float deltaTime) {
		boolean up, right;
		//acceleration.add(world.gravity);
		velocity.add(acceleration);
		position.add(velocity);
		up = (velocity.y >= 0);
		right = (velocity.x >= 0);
//		if (up) {
//			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].isSolid()) {
//				if (this.position.y + 16 >= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].position.y) {
//					velocity.set(velocity.x, 0);
//					position.set(position.x, world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16) + 3)].position.y - 16);
//				}
//			}
//		} else {
//			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
//				if (this.position.y - 16 <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y) {
//					velocity.set(velocity.x, 0);
//					position.set(position.x, world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y + 16);
//				}
//			}
//		}
//		if (right) {
//			if (world.blocks[(int) (this.position.x / 16 + 2)][(int) ((this.position.y / 16))].isSolid()) {
//				if (this.position.x + 16 >= world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.x) {
//					velocity.set(0, velocity.y);
//					position.set(world.blocks[(int) (this.position.x / 16) + 2][(int) ((this.position.y / 16))].position.x - 16, position.y);
//				}
//			}
//		} else {
//			if (world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
//				if (this.position.x - 16 <= world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x) {
//					velocity.set(0, velocity.y);
//					position.set(world.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x + 16, position.y);
//				}
//			}
//		}

		velocity.setZero();
		acceleration.setZero();
	}
	
	public TextureRegion getTexture(){
		return this.projectile;
	}
}
