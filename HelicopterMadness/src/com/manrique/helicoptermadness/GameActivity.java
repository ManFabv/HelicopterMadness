package com.manrique.helicoptermadness;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.util.adt.color.Color;


public class GameActivity extends org.andengine.ui.activity.BaseGameActivity 
{
	//estas dos variables seran las que usare para dise�ar el juego, son las dimensiones que deseo,
	//luego, andengine mediante el objeto resolution policy, va a encargarse de ajustar el juego
	//desde estas dimensiones, hasta las dimensiones del dispositivo
	public static final int CAMERA_WIDTH = 800;
	public static final int CAMERA_HEIGHT = 480;
	
	//esta variable sera el jugador, es un tipo de sprite animado
	public AnimatedPlayer jugador;

	@Override
	public EngineOptions onCreateEngineOptions() 
	{
		//esta camara define la parte de la escena que sera visible, y para
		//crearla tengo que definir el origen de la camara y el ancho y alto que usare como referencia al crear el juego
		//En este caso le estoy diciendo que ocupe desde el origen (0,0) hasta un cierto ancho y alto (definido por dos constantes)
		//NOTA: No hay que confundir la porcion visible de la escena que toma la camara, con la porcion 
		//de la pantalla que ocupa el juego (esto ultimo lo define el tipo de resolution policy mas adelante). 
		//Por decirlo con un ejemplo, puedo hacer que
		//la camara muestre todo el nivel del juego y sin embargo, todo se dibuja solo en una
		//porcion de la pantalla del dispositivo (como las peliculas con franjas negras arriba y abajo)
		Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		//esta variable me dira como va a escalar andengine el juego de acuerdo al tama�o de pantalla del dispositivo
		//donde va a correr el juego. En este caso va a cubrir toda la pantalla sin importar la relacion de aspecto, 
		//por lo que el juego puede verse un poco estirado en ciertas pantallas que no tengan la relacion de aspecto
		//con la que cree el juego (definida como camera_width y camera_height).
		//Hay otros tipos de resolution policy que tienen en cuenta la relacion de aspecto, entre otras opciones.
		IResolutionPolicy resolutionPolicy = new FillResolutionPolicy();

		//este objeto me sirve para configurar el engine, y toma como parametros si usara la pantalla completa (sin barra de estado),
		//el tipo de orientacion sobre como se dibujara (en este caso usara el sensor para ubicarla en apaisada, pero puede ser en una direccion
		//o en otra), que tipo de politica usara para escalar o contraer la resolucion en dispositivos que no tengan mi resolucion de dise�o y
		//por ultimo la camara con la que dibujare el juego
		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, resolutionPolicy, camera);

		//siguiendo con la configuracion diremos que usaremos musica y sonido
		engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
		
		//usamos la siguiente opcion para evitar que el dispositivo se apague cuando el jugador no esta interactuando con el juego
		//De esta forma podemos evitar que el dispositivo se apague cuando el jugador esta viendo una escena cinematica
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

		//regreso las opciones del engine para que andengine siga con el siguiente paso en el ciclo de vida del juego
		return engineOptions;
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException 
	{		
		//con este callback notifico a andengine que se termino la creacion de recursos (la llamada al metodo OnCreateResources en realidad)
		//Esta llamada es necesaria para que continue la correcta inicializacion del engine
		//Este callback a su vez llama internamente al metodo oncreatescene para continuar la inicializacion.
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException 
	{
		//el objeto base en el que van incluidos todos los objetos del juego es la escena
		//Las escenas pueden tener a su vez otras escenas como hijos, como por ejemplo una escena de opciones (hija de la escena de juego)
		//A las escenas se le anclan (attach) entidades, que son el objeto basico (con representacion grafica) de construccion del juego
		//NOTA: No hay que olvidar que andengine solo puede mostrar una escena por vez, es decir, no se pueden tener dos escenas padre al mismo
		//tiempo en pantalla
		Scene scene = new Scene(); //creamos una escena vacia
		
		//Hacemos que el fondo de pantalla sea de color cyan
		scene.getBackground().setColor(Color.CYAN);
		
		//con este callback notifico a andengine que se termino la creacion de la escena (la llamada al metodo OnCreateScene en realidad)
		//Esta llamada es necesaria para que continue la correcta inicializacion del engine
		//Este callback a su vez llama internamente al metodo onpopulatescene para continuar la inicializacion.
		pOnCreateSceneCallback.onCreateSceneFinished(scene);
	}

	@Override
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException 
	{
		//con este callback notifico a andengine que se termino de poblar la escena (la llamada al metodo OnPopulateScene en realidad)
		//Esta llamada es necesaria para terminar la correcta inicializacion del engine
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
}