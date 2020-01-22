package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class World {

	Texture b1,b2,b3;
	Texture ArkaPlan;
	Karakter karakter;
	float EkranX;
	float EkranY;
	float ArkaPlanKatsayisi = 1;
	float DunyaHizi;
	OrthographicCamera kamera;
	ArrayList<Engel>Engeller;
	
	
	public World(OrthographicCamera kamera){
		b1 = new Texture("isin1.png");
		b2 = new Texture("isin2.png");
		b3 = new Texture("isin3.png");
		Engeller= new ArrayList<>();
		this.kamera = kamera;
		DunyaHizi = 4;
		ArkaPlan = new Texture("arkaplan.png");
		karakter = new Karakter(50,203,new TextureAtlas("charset.atlas"),DunyaHizi);
		this.EkranX = kamera.viewportWidth;
		this.EkranY = kamera.viewportHeight;
	}
	
	public void DunyaKontrolleri(){
		ArkaPlanKontrolcusu();
		HareketKontrolcusu();
		EngelKontrolcusu();
		CarpismaKontrolcusu();
	}

	private void CarpismaKontrolcusu() {
		
		for(Engel engel : Engeller){
			
			if(engel.EtkiAlani.overlaps(karakter.EtkiAlani)){

				
				if(karakter.Durum!=engel.Durum){
					MyGdxGame.Oyun_Durum = MyGdxGame.OYUN_DURDU;
					return;
				}
			}
			
		}
	}

	private void HareketKontrolcusu() {
		
		kamera.position.x +=DunyaHizi;
		karakter.KarakterKonumuGuncelle();
		
	}

	public void HizKontrolcusu(){
		
		DunyaHizi++;
		karakter.KarakterHizGuncelle(DunyaHizi);
		System.out.println(DunyaHizi);
		
	}
	
	
	private void ArkaPlanKontrolcusu() {

		if(karakter.KarakterX>EkranX){
			
			if(karakter.KarakterX>(EkranX*ArkaPlanKatsayisi+55)){
				
				ArkaPlanKatsayisi++;
				EngelKoy();
				MyGdxGame.OyunSkor++;
				
				if(ArkaPlanKatsayisi%6==0){

					HizKontrolcusu();

				}
			}
		}
		
	}

	private void EngelKontrolcusu() {
		
		for(Engel engel : Engeller){
			
			if(karakter.KarakterX-engel.x>250){
				
				Engeller.remove(engel);
				return;
			}
			
		}
		
	}
	Random rnd = new Random();
	private void EngelKoy() {
		
		Engeller.add(new Engel(ArkaPlanKatsayisi*EkranX+220,karakter.KarakterY,rnd.nextInt(3)));
		
	}

	public void DunyayiCiz(SpriteBatch batch){
		
		batch.draw(ArkaPlan, (ArkaPlanKatsayisi-1)*EkranX, 0,EkranX,EkranY);
		batch.draw(ArkaPlan, (ArkaPlanKatsayisi)*EkranX-1, 0,EkranX,EkranY);
		batch.draw(ArkaPlan, (ArkaPlanKatsayisi+1)*EkranX*2-1, 0,EkranX,EkranY);
		
		batch.draw(b1, karakter.KarakterX-30, karakter.KarakterY-70,90,65);
		batch.draw(b2, karakter.KarakterX-30, karakter.KarakterY-135,90,65);
		batch.draw(b3, karakter.KarakterX-30+5, karakter.KarakterY-200,90,65);

		batch.draw(b1, karakter.KarakterX+kamera.viewportWidth-150, karakter.KarakterY-70,90,65);
		batch.draw(b2, karakter.KarakterX+kamera.viewportWidth-150, karakter.KarakterY-135,90,65);
		batch.draw(b3, karakter.KarakterX+kamera.viewportWidth-150+5, karakter.KarakterY-200,90,65);
		
		karakter.KarakteriCiz(batch);
		for(Engel engel : Engeller){
			engel.EngelCiz(batch);
		}
		
	}
	
	public void DunyaEtkiAlanlariCiz(ShapeRenderer shape){
		shape.rect(karakter.EtkiAlani.x, karakter.EtkiAlani.y, 
				karakter.EtkiAlani.width, karakter.EtkiAlani.height);
		
		for(Engel engel : Engeller){
			shape.rect(engel.x, engel.y, 
					engel.Width, engel.Height);
		}
		
		
	}

	public void TiklamaKontrolcusu(Vector3 worldCoordinates) {
		
		if(worldCoordinates.y<65){
			
			karakter.KarakterDurumKontrolcusu(2);
			
		}else if(worldCoordinates.y>65&&worldCoordinates.y<135){
			

			karakter.KarakterDurumKontrolcusu(1);
			
		}else if(worldCoordinates.y>135&&worldCoordinates.y<200){
			

			karakter.KarakterDurumKontrolcusu(0);
		}
		
	}
	
}
