package com.ccode.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
/**
 * MenuScreen.java implements Screen and represents the menu in which you can start the demo
 * 
 * @author c.code
 *
 */

public class MenuScreen implements Screen {
	/*
	 * GUI Components
	 */
	Stage menuStage;
	
	Label demoLabel;
	TextButton startButton;
	TextButton quitButton;
	boolean inFullScreen = false;
	
	@Override
	public void show() {
		System.out.println("show method");
		menuStage = new Stage();
		Gdx.input.setInputProcessor(menuStage);
		
		// Demo Label
		LabelStyle lStyle = new LabelStyle();
		lStyle.font = new BitmapFont();
		lStyle.fontColor = Color.GOLD;
		lStyle.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		demoLabel = new Label("Awesome Game Demo", lStyle);
		demoLabel.setFontScale(2);
		demoLabel.setAlignment(Align.center, Align.center);
		demoLabel.setPosition(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.75f, Align.center);
		menuStage.addActor(demoLabel);
		
		
		// Start Button
		TextButtonStyle bStyle = new TextButtonStyle();
		bStyle.font = new BitmapFont();
		bStyle.fontColor = Color.GOLD;
		bStyle.font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		startButton = new TextButton("Start", bStyle);
		startButton.setSize(Gdx.graphics.getWidth() * 0.125f, Gdx.graphics.getWidth() * 0.0625f); // 1/8 , 1/16
		startButton.setPosition(Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.5f, Align.center);
		startButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("clicked");
			}
			
		});
		menuStage.addActor(startButton);
		
		
		// setup exit button
		quitButton = new TextButton("Quit", bStyle);
		quitButton.setSize(Gdx.graphics.getWidth() * 0.125f, Gdx.graphics.getWidth() * 0.0625f);
		quitButton.setPosition(Gdx.graphics.getWidth() * 0.5f, startButton.getY() - 10f, Align.center);
		quitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.exit(0);
			}
		});
		menuStage.addActor(quitButton);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 1, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		menuStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		menuStage.act();
		menuStage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		menuStage.dispose();
	}

}
