package com.mygdx.game;

import android.view.View;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import static com.mygdx.game.AndroidLauncher.conOyun;
import static com.mygdx.game.AndroidLauncher.contraitOyunSonu;
import static com.mygdx.game.AndroidLauncher.textSon;


public class MyGdxGame extends ApplicationAdapter implements InputProcessor{
	static boolean OYUN_BASLA = true;
	static boolean OYUN_DURDU = false;
	static boolean Oyun_Durum;
	SpriteBatch batch;
	OrthographicCamera kamera;
	World Dunya;
	ShapeRenderer shape;
    int KameraGenislik;
    int KameraUzunluk;
	private Vector3 worldCoordinates;
	BitmapFont font;
	static int OyunSkor;
	@Override
	public void create () {
		OyunSkor = 0;
		Oyun_Durum = OYUN_BASLA;
		Gdx.input.setInputProcessor(this);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		kamera = new OrthographicCamera();
        KameraGenislik = 200*4;
        KameraUzunluk = 200*3;
        kamera = new OrthographicCamera(KameraGenislik,KameraUzunluk);
        kamera.position.set(kamera.viewportWidth/2,kamera.viewportHeight/2,0);
        kamera.update();
		Dunya = new World(kamera);
        font = new BitmapFont();
		font.getData().setScale(1.5f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(kamera.combined);
		kamera.update();
		batch.begin();
		
		
		OyunGrafikleriGuncelle();
		
		if(Oyun_Durum == OYUN_BASLA) {
			OyunMekanigiGuncelle();
		}
		
		
		batch.end();
		/*
		shape.setProjectionMatrix(kamera.combined);
		shape.begin(ShapeType.Line);
		
		Dunya.DunyaEtkiAlanlariCiz(shape);
		
		shape.end();
		*/
	}
	
	private void OyunMekanigiGuncelle() {
		
		Dunya.DunyaKontrolleri();

		if(MyGdxGame.Oyun_Durum == OYUN_DURDU){


			textSon.post(new Runnable() {
				@Override
				public void run() {
					textSon.setText("Skor :"+OyunSkor);
				}
			});
			conOyun.post(new Runnable() {
				@Override
				public void run() {
					conOyun.removeView(conOyun.findViewById(R.id.conFrame));
				}
			});
			contraitOyunSonu.post(new Runnable() {
				@Override
				public void run() {
					contraitOyunSonu.setVisibility(View.VISIBLE);
				}
			});
			Dunya.Engeller.clear();

			if(Dunya.karakter.KarakterHizi>7){
				Dunya.karakter.KarakterHizi -=3;
				Dunya.DunyaHizi-=3;
			}

		}
	}

	private void OyunGrafikleriGuncelle() {
		
		Dunya.DunyayiCiz(batch);
		font.draw(batch,"Oyun Skor: "+OyunSkor,kamera.position.x+(kamera.viewportWidth/4),kamera.viewportHeight-25);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		worldCoordinates = kamera.unproject(new Vector3(0, screenY, 0));
		//System.out.println(worldCoordinates.x+" | "+worldCoordinates.y);
		Dunya.TiklamaKontrolcusu(worldCoordinates);
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
