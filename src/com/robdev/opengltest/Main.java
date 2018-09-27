package com.robdev.opengltest;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import com.robdev.opengltest.util.ShaderUtils;

public class Main implements Runnable {

	private final int width = 1280;
	private final int height = 720;
	private String windowTitle = "OpenGL Test Window!";
	
	private boolean isRunning = false;
	private Thread thread;
	
	private void start() {
		isRunning = true;
		thread = new Thread(this, "Window");
		thread.start();
	}
	
	private void init() {
		//check for openGL success
		String version = glGetString(GL_VERSION);
		System.out.println("Using openGL version " + version);
		glClearColor(1.0f,1.0f,1.0f,1.0f);
	}
	
	public void run()
	{
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(windowTitle);
			ContextAttribs contextAttribs = new ContextAttribs(3,3);
			//enforce openGL 3.3
			Display.create(new PixelFormat(), contextAttribs.withProfileCore(true));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int shader = ShaderUtils.load("shaders/shader.vert", "shaders/shader.frag");
		glUseProgram(shader);
		init();
		
		int vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		while(isRunning) {
			render();
			Display.update();
			if(Display.isCloseRequested()) isRunning = false;
		}
		
		Display.destroy();
	}
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		glDrawArrays(GL_TRIANGLES, 0, 3);
	}
	
	public static void main(String[] args) {
		System.out.println("Starting Program!");
		new Main().start();
		
	}
	
}
