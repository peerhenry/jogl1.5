package main;

import menutest.MenuRenderer;
import peergl.Context;
import jogltut.MySceneRenderer;

public class Main
{

	public static void main(String[] args)
	{
		Context ct = new Context(1024, 768, new MenuRenderer());
		ct.start();
	}
	
}
