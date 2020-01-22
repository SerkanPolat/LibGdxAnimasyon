package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Karakter {

	TextureAtlas texture;
	float KarakterX;
	float KarakterY;
	float KarakterWidth;
	float KarakterHeight;
	private Animation anim;
	private float timePass = 0;
	Rectangle EtkiAlani;
	float KarakterHizi;
	int Durum;
	
	public Karakter(float KarakterX,float KarakterY,TextureAtlas KarakterTexture,float KarakterHizi){
		this.KarakterHizi = KarakterHizi;
		this.Durum = 2;
		KarakterWidth = 50;
		KarakterHeight = 75;
		EtkiAlani = new Rectangle(KarakterX+5,KarakterY,KarakterWidth-25,KarakterHeight-25);
		this.KarakterX = KarakterX;
		this.KarakterY = KarakterY;
		this.texture = KarakterTexture;
		anim = new Animation(1/13f,texture.getRegions());
		
	}
	public void KarakterKonumuGuncelle(){
		KarakterX +=KarakterHizi;
		EtkiAlani.x += KarakterHizi;
	}
	
	public void KarakterHizGuncelle(float Hiz){
		
		KarakterHizi = Hiz;
		
	}
	
	public void KarakteriCiz(SpriteBatch batch){
		
		timePass += Gdx.graphics.getDeltaTime();
		
		batch.draw((TextureRegion)anim.getKeyFrame(timePass, true), KarakterX-KarakterHizi,
				KarakterY,KarakterWidth,KarakterHeight);
		
	}
	
	public void KarakterDurumKontrolcusu(int Durum){
		
		if(Durum==0){
			
			KarakterHeight = 175;
			KarakterWidth = 80;
			this.Durum = 0;
			
		}else if(Durum==1){
			
			KarakterHeight = 130;
			KarakterWidth = 70;
			this.Durum = 1;
		}else{

			KarakterHeight = 75;
			KarakterWidth = 55;
			this.Durum = 2;
		}
	}
}

