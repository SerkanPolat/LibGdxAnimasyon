package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Engel {
	
	float x;
	float y;
	float Width;
	float Height;
	Rectangle EtkiAlani;
	Texture texture;
	int Durum;
	
	public Engel(float x,float y,int Durum){
		this.Durum = Durum;
		this.x = x;
		this.y = y;
		Width = 50;
		if(Durum==0){
			
			this.texture = new Texture("isin1.png");
			Height = 165;
			
		}else if(Durum==1){
			
			this.texture = new Texture("isin2.png");
			Height = 120;
			
		}else if(Durum==2){
			
			this.texture = new Texture("isin3.png");
			Height = 65;
			
		}
		EtkiAlani = new Rectangle(x+10,y,Width,Height);
	}
	public void EngelCiz(SpriteBatch batch){
		
		batch.draw(texture, x, y,Width,Height);
		
	}
	
}
