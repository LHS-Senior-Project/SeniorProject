package com.S.P;

import com.badlogic.gdx.math.Vector2;

public class Moveable{
	int posZ;
	Vector2 position;
	//TODO max accelerations and stuff
	Vector2 velocity;
	Vector2 acceleration;
	Vector2 facing;
	World world;
	
	public Moveable(){
		position = new Vector2(0f,0f);
		acceleration = new Vector2(0f,0f);
		velocity = new Vector2(0f, 0f);
		facing = new Vector2(0f,0f);
		world = null;
	}
	
	public Moveable(World world){
		position = new Vector2(0f,0f);
		acceleration = new Vector2(0f,0f);
		velocity = new Vector2(0f, 0f);
		facing = new Vector2(0f,0f);
		this.world = world;
	}
	
	public void addAccel(float x, float y){
		acceleration.add(x, y);
	}
	
	public void move(float deltaTime){
		velocity.add(acceleration);
//		if(Math.abs(velocity.x) <= .10f) velocity.set(0, velocity.y);
//		if(Math.abs(velocity.y) <= .10f) velocity.set( velocity.x, 0);
//		velocity.add(velocity.x / 5000000, 0);
		//velocity.add(world.gravity);
		position.add(velocity);
		velocity.setZero();
		acceleration.setZero();
	}
	
}
