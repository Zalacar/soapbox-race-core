package com.soapboxrace.core.bo.util;

import java.util.ArrayList;
import java.util.List;

import com.soapboxrace.core.jpa.CustomCarEntity;
import com.soapboxrace.core.jpa.OwnedCarEntity;
import com.soapboxrace.core.jpa.PaintEntity;
import com.soapboxrace.core.jpa.VinylEntity;
import com.soapboxrace.jaxb.http.ArrayOfCustomPaintTrans;
import com.soapboxrace.jaxb.http.ArrayOfCustomVinylTrans;
import com.soapboxrace.jaxb.http.ArrayOfPerformancePartTrans;
import com.soapboxrace.jaxb.http.ArrayOfSkillModPartTrans;
import com.soapboxrace.jaxb.http.ArrayOfVisualPartTrans;
import com.soapboxrace.jaxb.http.CustomCarTrans;
import com.soapboxrace.jaxb.http.CustomPaintTrans;
import com.soapboxrace.jaxb.http.CustomVinylTrans;
import com.soapboxrace.jaxb.http.OwnedCarTrans;

public class OwnedCarConverter {

	public static OwnedCarTrans Entity2Trans(OwnedCarEntity ownedCarEntity) {
		OwnedCarTrans ownedCarTrans = new OwnedCarTrans();
		ownedCarTrans.setId(ownedCarEntity.getId());
		ownedCarTrans.setDurability(ownedCarEntity.getDurability());
		ownedCarTrans.setHeat(ownedCarEntity.getHeat());
		ownedCarTrans.setOwnershipType(ownedCarEntity.getOwnershipType());

		CustomCarEntity customCarEntity = ownedCarEntity.getCustomCar();
		CustomCarTrans customCarTrans = new CustomCarTrans();
		customCarTrans.setBaseCar(customCarEntity.getBaseCar());
		customCarTrans.setCarClassHash(customCarEntity.getCarClassHash());
		customCarTrans.setId(customCarEntity.getId().intValue());
		customCarTrans.setIsPreset(customCarEntity.isPreset());
		customCarTrans.setLevel(customCarEntity.getLevel());
		customCarTrans.setName(customCarEntity.getName());
		customCarTrans.setPhysicsProfileHash(customCarEntity.getPhysicsProfileHash());
		customCarTrans.setRating(customCarEntity.getRating());
		customCarTrans.setResalePrice(customCarEntity.getResalePrice());
		customCarTrans.setRideHeightDrop(customCarEntity.getRideHeightDrop());
		customCarTrans.setVersion(customCarEntity.getVersion());

		ArrayOfCustomPaintTrans arrayOfCustomPaintTrans = new ArrayOfCustomPaintTrans();
		List<CustomPaintTrans> customPaintTransList = arrayOfCustomPaintTrans.getCustomPaintTrans();
		List<PaintEntity> paints = customCarEntity.getPaints();
		for (PaintEntity paintEntity : paints) {
			CustomPaintTrans customPaintTransTmp = new CustomPaintTrans();
			customPaintTransTmp.setGroup(paintEntity.getGroup());
			customPaintTransTmp.setHue(paintEntity.getHue());
			customPaintTransTmp.setSat(paintEntity.getSat());
			customPaintTransTmp.setSlot(paintEntity.getSlot());
			customPaintTransTmp.setVar(paintEntity.getVar());
			customPaintTransList.add(customPaintTransTmp);
		}
		customCarTrans.setPaints(arrayOfCustomPaintTrans);

		customCarTrans.setPerformanceParts(new ArrayOfPerformancePartTrans());
		customCarTrans.setSkillModParts(new ArrayOfSkillModPartTrans());

		ArrayOfCustomVinylTrans arrayOfCustomVinylTrans = new ArrayOfCustomVinylTrans();
		customCarTrans.setVinyls(arrayOfCustomVinylTrans);
		customCarTrans.setVisualParts(new ArrayOfVisualPartTrans());

		ownedCarTrans.setCustomCar(customCarTrans);
		return ownedCarTrans;
	}

	public static void Trans2Entity(OwnedCarTrans ownedCarTrans, OwnedCarEntity ownedCarEntity) {
		ownedCarEntity.setDurability(ownedCarTrans.getDurability());
		// ownedCarEntity.setExpirationDate(expirationDate);
		ownedCarEntity.setHeat(ownedCarTrans.getHeat());
		ownedCarEntity.setOwnershipType(ownedCarEntity.getOwnershipType());
		CustomCarTrans customCarTrans = ownedCarTrans.getCustomCar();
		CustomCarEntity customCarEntity = ownedCarEntity.getCustomCar();
		customCarEntity.setBaseCar(customCarTrans.getBaseCar());
		customCarEntity.setCarClassHash(customCarTrans.getCarClassHash());
		customCarEntity.setLevel(customCarTrans.getLevel());
		customCarEntity.setName(customCarTrans.getName());
		customCarEntity.setPhysicsProfileHash(customCarTrans.getPhysicsProfileHash());
		customCarEntity.setPreset(customCarTrans.isIsPreset());
		customCarEntity.setRating(customCarTrans.getRating());
		customCarEntity.setResalePrice(customCarTrans.getResalePrice());
		customCarEntity.setRideHeightDrop(customCarTrans.getRideHeightDrop());
		customCarEntity.setVersion(customCarTrans.getVersion());
	}

	public static void Details2NewEntity(OwnedCarTrans ownedCarTrans, OwnedCarEntity ownedCarEntity) {
		CustomCarTrans customCarTrans = ownedCarTrans.getCustomCar();
		CustomCarEntity customCarEntity = ownedCarEntity.getCustomCar();

		ArrayList<PaintEntity> paintEntityList = new ArrayList<>();
		List<CustomPaintTrans> customPaintTrans = customCarTrans.getPaints().getCustomPaintTrans();
		for (CustomPaintTrans customPaintTransTmp : customPaintTrans) {
			PaintEntity paintEntity = new PaintEntity();
			paintEntity.setCustomCar(customCarEntity);
			paintEntity.setGroup(customPaintTransTmp.getGroup());
			paintEntity.setHue(customPaintTransTmp.getHue());
			paintEntity.setSat(customPaintTransTmp.getSat());
			paintEntity.setSlot(customPaintTransTmp.getSlot());
			paintEntity.setVar(customPaintTransTmp.getVar());
			paintEntityList.add(paintEntity);
		}
		customCarEntity.setPaints(paintEntityList);

		// customCarEntity.setPerformanceParts("");
		// customCarEntity.setSkillModParts(skillModParts);

		ArrayList<VinylEntity> vinylEntityList = new ArrayList<>();
		List<CustomVinylTrans> customVinylTrans = customCarTrans.getVinyls().getCustomVinylTrans();
		for (CustomVinylTrans customVinylTransTmp : customVinylTrans) {
			VinylEntity vinylEntity = new VinylEntity();
			vinylEntity.setCustomCar(customCarEntity);
			vinylEntity.setHash(customVinylTransTmp.getHash());
			vinylEntity.setHue1(customVinylTransTmp.getHue1());
			vinylEntity.setHue2(customVinylTransTmp.getHue2());
			vinylEntity.setHue3(customVinylTransTmp.getHue3());
			vinylEntity.setHue4(customVinylTransTmp.getHue4());
			vinylEntity.setLayer(customVinylTransTmp.getLayer());
			vinylEntity.setMir(customVinylTransTmp.isMir());
			vinylEntity.setRot(customVinylTransTmp.getRot());
			vinylEntity.setSat1(customVinylTransTmp.getSat1());
			vinylEntity.setSat2(customVinylTransTmp.getSat2());
			vinylEntity.setSat3(customVinylTransTmp.getSat3());
			vinylEntity.setSat4(customVinylTransTmp.getSat4());
			vinylEntity.setScalex(customVinylTransTmp.getScaleX());
			vinylEntity.setScaley(customVinylTransTmp.getScaleY());
			vinylEntity.setShear(customVinylTransTmp.getShear());
			vinylEntity.setTranx(customVinylTransTmp.getTranX());
			vinylEntity.setTrany(customVinylTransTmp.getTranY());
			vinylEntity.setVar1(customVinylTransTmp.getVar1());
			vinylEntity.setVar2(customVinylTransTmp.getVar2());
			vinylEntity.setVar3(customVinylTransTmp.getVar3());
			vinylEntity.setVar4(customVinylTransTmp.getVar4());
			vinylEntityList.add(vinylEntity);
		}
		customCarEntity.setVinyls(vinylEntityList);

		// customCarEntity.setVisualParts(visualParts);
	}

}
