package com.S.P.Math;

public class Vector2 {
	public float x;
	public float y;
	
	public Vector2(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public Vector2(){
		this.x = 0;
		this.y = 0;
	}

	public Vector2 addVector(Vector2 vector) {

		Vector2 vec = new Vector2();

		vec.x = vector.x + this.x;
		vec.y = vector.y + this.y;

		return vec;
	}

	public Vector2 multiplyVector(float scale){
		
		Vector2 vect = new Vector2();
		
		vect.x = this.x * scale;
		vect.y = this.y * scale;
		
		return vect;
		
	}
	
	public Vector2 subtractVector(Vector2 vector) {

		Vector2 vect = new Vector2();

		vect.x = this.x - vector.x;
		vect.y = this.y - vector.y;

		return vect;
	}

	public Vector2 getUnitVector() {

		Vector2 unitVector = new Vector2();

		float length = 0.0f;

		length = (float) Math.sqrt((this.x * this.x) + (this.y * this.y));

		unitVector.x = this.x / length;
		unitVector.y = this.y / length;

		return unitVector;

	}

	public static Vector2 getUnitVector(Vector2 vector) {

		Vector2 unitVector = new Vector2();

		float length = 0.0f;

		length = (float) Math.sqrt((vector.x * vector.x) + (vector.y * vector.y));

		unitVector.x = vector.x / length;
		unitVector.y = vector.y / length;

		return unitVector;
	}
}
