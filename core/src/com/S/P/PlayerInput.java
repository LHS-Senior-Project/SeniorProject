package com.S.P;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class PlayerInput implements InputProcessor {

	World thisWorld;
	Camera camera;
	SP2 sp2;
	
	boolean up,down,left,right,zIn,zOut;
	boolean pup,pdown,pleft,pright;
	boolean freeMove, jump;
	
	PlayerInput(World world, Camera Camera){
		this.camera = Camera;
		thisWorld = world;
	}
	
	public PlayerInput(SP2 sp2) {
		this.sp2 = sp2;
		this.thisWorld = sp2.thisWorld;
		this.camera = sp2.camera;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.W:
			up = true;
			break;
		case Keys.UP:
			pup = true;
			break;
		case Keys.A:
			left = true;
			break;
		case Keys.LEFT:
			pleft = true;
			break;
		case Keys.S:
			down = true;
			break;
		case Keys.DOWN:
			pdown = true;
			break;
		case Keys.D:
			right = true;
			break;
		case Keys.RIGHT:
			pright = true;
			break;
		case Keys.E:
			zIn = true;
			break;
		case Keys.Q:
			zOut = true;
			break;
		case Keys.SPACE:
			jump = true;
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.TAB:
			freeMove = !freeMove;
			break;
		case Keys.W:
			up = false;
			break;
		case Keys.UP:
			pup = false;
			break;
		case Keys.A:
			left = false;
			break;
		case Keys.LEFT:
			pleft = false;
			break;
		case Keys.S:
			down = false;
			break;
		case Keys.DOWN:
			pdown = false;
			break;
		case Keys.D:
			right = false;
			break;
		case Keys.RIGHT:
			pright = false;
			break;
		case Keys.E:
			zIn = false;
			break;
		case Keys.Q:
			zOut = false;
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	//screenX = x location of mouse
	//screenY = y location of mouse
	//pointer = ??? (maybe if mulptiple mice)
	//button  = what was presses (0 = left, 1 = right, 2 = middle) 
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		System.out.println(pointer);
		camera.update();
		Vector2 block = new Vector2((int)(camera.getPickRay(screenX, screenY).origin.x / 16),(int)(camera.getPickRay(screenX, screenY).origin.y / 16));
		System.out.println(block);
//		Gdx.graphics.setTitle("X: " + screenX + "    Y: " + screenY + "         pointer: " + pointer + "      Button: " + button);
//		System.out.println(  " " + (int)(camera.getPickRay(screenX, screenY).origin.y / 16));
		
		if(button == 0){
			thisWorld.breakBlock(block);
		}else if(button == 1){
			thisWorld.placeBlock(block, 2);
		}else if(button == 2){
			Moveable project = new Projectile(new Vector2(thisWorld.getPlayer().position.x + 32,thisWorld.getPlayer().position.y + 20),new Vector2(block.x*16,block.y * 16),thisWorld);
			project.setCollideable(new Vector2(16,16));
			thisWorld.addMoveable(project);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		Gdx.graphics.setTitle("camX: " + this.camera.position.x + "      camY: " + this.camera.position.y);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		if(amount == -1){
			camera.viewportHeight = camera.viewportHeight - 64f;
			camera.viewportWidth = camera.viewportWidth - 64f;
		}
		else{
			camera.viewportHeight = camera.viewportHeight + 64f;
			camera.viewportWidth = camera.viewportWidth + 64f;
		}
		return false;
	}
	
}
