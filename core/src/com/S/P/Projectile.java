package com.S.P;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity{
	private static final Texture ss = new Texture("Magic Missile.png");
	TextureRegion projectile = TextureRegion.split(ss, 16, 16)[0][0];
	public World thisWorld;
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
	
	public Projectile(Vector2 Position, Vector2 target, World world) {
		this.position = Position.cpy();
		this.staticTarget = target;
		this.speed = 0.1f;
		this.thisWorld = world;
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
		this.addAccel((this.staticTarget.x - this.position.x)*speed, (this.staticTarget.y - this.position.y)*speed);
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
		if(((int)(this.position.x + 8)/ 16 ) == ((int)this.staticTarget.x / 16 ) && (int)(this.position.y + 8)/16 == (int)this.staticTarget.y/16) destroy();
		if (up) {
			System.out.println("X: " + ((int)(this.position.x + 8)/ 16 )+ "Y: " + (int)(this.position.y + 8)/16 + "     " +  "X: " + ((int)this.staticTarget.x / 16 )+ "Y: " + (int)this.staticTarget.y/16);
			if (thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.y + 16 >= thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y - 16);
					this.destroy();
				}
			}
		} else {
			if (thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.y - 16 <= thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y) {
					velocity.set(velocity.x, 0);
					position.set(position.x, thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.y + 16);
					this.destroy();
				}
			}
		}
		if (right) {
			if (thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.x + 16 >= thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x) {
					velocity.set(0, velocity.y);
					position.set(thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x - 16, position.y);
					this.destroy();
				}
			}
		} else {
			if (thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].isSolid()) {
				if (this.position.x - 16 <= thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x) {
					velocity.set(0, velocity.y);
					position.set(thisWorld.blocks[(int) (this.position.x / 16)][(int) ((this.position.y / 16))].position.x + 16, position.y);
					this.destroy();
				}
			}
		}

		velocity.setZero();
		acceleration.setZero();
	}
	
	public TextureRegion getTexture(){
		return this.projectile;
	}
	
	public void destroy(){
		Vector2 block = this.position.cpy();
		thisWorld.breakBlock(new Vector2((int)block.x/16,(int)(block.y/16)+1));
		thisWorld.placeBlock(new Vector2((int)block.x/16,(int)(block.y/16)+1), 3);
		thisWorld.removeMoveable(this);
	}
}
