package com.manrique.helicoptermadness;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.entity.scene.Scene;


public class GameActivity extends org.andengine.ui.activity.BaseGameActivity 
{
	public static final int CAMERA_WIDTH = 480;
	public static final int CAMERA_HEIGHT = 800;

	@Override
	public EngineOptions onCreateEngineOptions() 
	{
		Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		IResolutionPolicy resolutionPolicy = new FillResolutionPolicy();

		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, resolutionPolicy, camera);

		engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

		return engineOptions;
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback)
		throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
		throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback)
		throws IOException {
		// TODO Auto-generated method stub
	}
}