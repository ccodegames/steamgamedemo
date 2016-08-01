package com.ccode.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The screen that displays the game action. Box2d measures the world in the metric system.
 * 
 * @author gabemac
 *
 *	Copyright c.code 2016
 */


public class GameScreen implements Screen, InputProcessor{
	
	private World physicsWorld; // Used for box2d to determin world physics properties
	private Box2DDebugRenderer debugRenderer; // Renders simple shapes and lines
	private OrthographicCamera camera; // A camera for rendering 2d
	private double vpMRatio = 6/100; // Viewport units per meter
	private int screenWidth;
	private int screenHeight;
	private final float TIMESTEP = 1/60f; // Number of times per second the physics are updated
	private final int VELOCITYITERATIONS = 8; // Higher value = higher quality of simulation
	private final int POSITIONITERATIONS = 3; // Higher value = higher quality of simulation
	private Body box;
	
	
	
	@Override
	public void show() {
		physicsWorld = new World(new Vector2(0, -20f), true);
		debugRenderer = new Box2DDebugRenderer();
		
		Gdx.input.setInputProcessor(this);
		
		
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		int ratio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(screenWidth / 25, screenHeight / 25);
		//camera.position.set(new Vector2(6/2, 3/2), 0);
		
		System.out.println(screenWidth / 25);
		
		/**
		 * Floor
		 */
		
		// Body definition
		BodyDef floorbodydef = new BodyDef();
		floorbodydef.type = BodyDef.BodyType.StaticBody;
		floorbodydef.position.set(new Vector2(0, 0 - 3));
		
		// Shape of object
		ChainShape floor = new ChainShape();
		Vector2[] vertices = { new Vector2(-25.5f,0f), new Vector2(25.5f,0), new Vector2(25.5f, 20.5f), new Vector2(-25.5f, 20.5f), new Vector2(-25.5f, 0)};
		floor.createChain(vertices);
		
		// Fixture Definition
		FixtureDef floorfixdef = new FixtureDef();
		floorfixdef.shape = floor;
		floorfixdef.density = 2;
		floorfixdef.friction = .7f;
		floorfixdef.restitution = 0f;
		
		Body floorbody = physicsWorld.createBody(floorbodydef);
		floorbody.createFixture(floorfixdef);
		
		floor.dispose();
		
		
		
		/**
		 *  Box
		 */
		
		// Body definition
		BodyDef shapedef = new BodyDef();
		shapedef.type = BodyDef.BodyType.DynamicBody;
		shapedef.position.set(new Vector2(0, 0));
	
		// Shape of object
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1, 2);
		
		// Fixture Definition
		FixtureDef fixdef = new FixtureDef();
		fixdef.shape = shape;
		fixdef.density = 1;
		fixdef.friction = .05f;
		fixdef.restitution = .09f;
		
		box = physicsWorld.createBody(shapedef);
		box.createFixture(fixdef);
		
		shape.dispose();
		
		
		/**
		 *  Box
		 */
		
		// Body definition
		BodyDef shape2def = new BodyDef();
		shape2def.type = BodyDef.BodyType.DynamicBody;
		shape2def.position.set(new Vector2(3, 0));
	
		// Shape of object
		PolygonShape shape2 = new PolygonShape();
		shape2.setAsBox(1, 1);
		
		// Fixture Definition
		FixtureDef fix2def = new FixtureDef();
		fix2def.shape = shape2;
		fix2def.density = .67f;
		fix2def.friction = .5f;
		fix2def.restitution = .09f;
		
		Body box2 = physicsWorld.createBody(shape2def);
		box2.createFixture(fix2def);
		
		shape2.dispose();
		
		/**
		 *  Ball
		 */
		
		// Body definition
		BodyDef shapeballdef = new BodyDef();
		shapeballdef.type = BodyDef.BodyType.DynamicBody;
		shapeballdef.position.set(new Vector2(3, 0));
	
		// Shape of object
		CircleShape circle = new CircleShape();
		circle.setRadius(1);
		
		// Fixture Definition
		FixtureDef fixbdef = new FixtureDef();
		fixbdef.shape = circle;
		fixbdef.density = .67f;
		fixbdef.friction = .5f;
		fixbdef.restitution = .09f;
		
		Body ball = physicsWorld.createBody(shapeballdef);
		ball.createFixture(fixbdef);
		
		circle.dispose();
	}

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		debugRenderer.render(physicsWorld, camera.combined);
		
		physicsWorld.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
	}

	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void hide() {
		dispose();
	}

	
	@Override
	public void dispose() {
		physicsWorld.dispose();
		debugRenderer.dispose();
	}
	
	
	// Converts the from viewport units to meters
	private double viewportUnitsToMeters(int viewportUnits){
		return (vpMRatio / viewportUnits);
	}
	
	
	// Converts the meters to pixels
	private double metersToViewportUnits(int meters){
		return (meters * vpMRatio);
	}


	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.RIGHT){
			box.setLinearVelocity(new Vector2(10, 0));
			System.out.println("Key Pressed");
		}else if(keycode == Keys.LEFT){
			box.applyLinearImpulse(new Vector2(-100, 0), box.getPosition(), true);
		}else if(keycode == Keys.UP){
			box.applyLinearImpulse(new Vector2(0, 150f), box.getPosition(), true);
		}
		
		System.out.println("Done");
		return true;
	}


	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
		return false;
	}


	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}


}
