package com.S.P;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.Ray;

public class SP2 extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
	World thisWorld;
	
	boolean up,down,left,right,zIn,zOut;
	boolean pup,pdown,pleft,pright;
	boolean freeMove, jump;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		camera = new OrthographicCamera(700f, 700f * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
		camera.position.set(250f, 750f, 0f);
		camera.update();
		Gdx.input.setInputProcessor(this);
		Gdx.input.setOnscreenKeyboardVisible(true);
		thisWorld = new World(new Vector2(0.0f, -9.8f), 100, 80);
		thisWorld.setPlayer(new Player(thisWorld, new Vector2(50f,50f)));
		thisWorld.setBlock(30, 56, Block.DIRT);
		thisWorld.addMoveable(thisWorld.getPlayer());
		Mob cancerSprite = new Mob(thisWorld, new Vector2(50f,50f));
		thisWorld.addMoveable(cancerSprite);
		cancerSprite.setTarget(thisWorld.getPlayer());
		thisWorld.loadFromFile("map.txt");
	}

	@Override
	public void resize(int width, int height){
		camera = new OrthographicCamera(700f, 700f * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
		camera.update();
	}
	
	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		for(Block[] blocks : thisWorld.blocks){
			for(Block b : blocks){
				batch.draw(b.blockTexture, b.position.x, b.position.y);
			}
		}
		
		for(Moveable m : thisWorld.moveables){
			if(m instanceof Player){
				batch.draw(((Player) m).getTexture(), m.position.x, m.position.y);
//				batch.draw(Block.DIRT,m.position.x,m.position.y,1.0f,1.0f);
//				batch.draw(Block.DIRT,thisWorld.blocks[(int) (m.position.x / 16)][(int) ((m.position.y / 16) + 3)].position.x,thisWorld.blocks[(int) (m.position.x / 16)][(int) ((m.position.y / 16) + 3)].position.y,16.0f,16.0f);
//				batch.draw(Block.DIRT,thisWorld.blocks[(int) (m.position.x / 16)][(int) ((m.position.y / 16) - 1)].position.x,thisWorld.blocks[(int) (m.position.x / 16)][(int) ((m.position.y / 16) - 1)].position.y,16.0f,16.0f);
//				
			}
			else if(m instanceof Mob){
				batch.draw(((Mob) m).getTexture(), m.position.x, m.position.y);
			}
			else if(m instanceof Projectile){
				//batch.draw(((Projectile) m).getTexture(), m.position.x, m.position.y, m.position.x + 8, m.position.y + 8, 16, 16, 1, 1, ((Projectile) m).getAngle());
				batch.draw(((Projectile) m).getTexture(), m.position.x, m.position.y);
			}
		}
		
		batch.end();
	}
	
	public void update(){
		if (up) {
			camera.position.set(camera.position.x, camera.position.y + 16f, 0);
		}
		if (down) {
			camera.position.set(camera.position.x, camera.position.y - 16f, 0);
		}
		if (left) {
			camera.position.set(camera.position.x - 16f, camera.position.y, 0);
		}
		if (right) {
			camera.position.set(camera.position.x + 16f, camera.position.y, 0);
		}
		if(zIn){
			camera.viewportHeight = camera.viewportHeight - 16f;
			camera.viewportWidth = camera.viewportWidth - 16f;
		}
		if(zOut){
			camera.viewportHeight = camera.viewportHeight + 16f;
			camera.viewportWidth = camera.viewportWidth + 16f;
		}
		
		if (pup) {
			thisWorld.getPlayer().addAccel(0.0f, 10.0f);
		}
		if (pdown) {
			thisWorld.getPlayer().addAccel(0.0f, -10.0f);
		}
		if (pleft) {
			thisWorld.getPlayer().addAccel(-10.0f, 0.0f);
		}
		if (pright) {
			thisWorld.getPlayer().addAccel(10.0f, 0.0f);
		}
		
		if(jump){
			thisWorld.getPlayer().addAccel(0.0f, 45f);
			jump = false;
		}
		
		for(Moveable m : thisWorld.moveables){
			if(m instanceof Mob){
				((Mob) m).AIUpdate();
			}
			if(m instanceof Projectile){
				((Projectile) m).AIUpdate();
			}
			m.move(Gdx.graphics.getDeltaTime());
		}
		
		if(!freeMove){
			camera.position.set(thisWorld.player.position, 0f);
		}

		camera.update();
		thisWorld.removeMoveables();
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
		
		Vector2 block = new Vector2((int)(camera.getPickRay(screenX, screenY).origin.x / 16),(int)(camera.getPickRay(screenX, screenY).origin.y / 16));
//		Gdx.graphics.setTitle("X: " + screenX + "    Y: " + screenY + "         pointer: " + pointer + "      Button: " + button);
//		System.out.println(  + " " + (int)(camera.getPickRay(screenX, screenY).origin.y / 16));

		if(button == 0){
			thisWorld.breakBlock(block);
		}else if(button == 1){
			thisWorld.placeBlock(block);
		}else if(button == 2){
			thisWorld.addMoveable(new Projectile(new Vector2(thisWorld.getPlayer().position.x + 32,thisWorld.getPlayer().position.y + 20),new Vector2(block.x*16,block.y * 16),thisWorld));
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
