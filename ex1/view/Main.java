package view;

import controller.Threads;
import java.util.concurrent.Semaphore;

public class Main 
{
	public static void main(String arg[])
	{
		Semaphore semaforo = new Semaphore(1);
	
		for(int i=1; i<22; i++)
		{
			Threads Threads = new Threads(i,semaforo);
			Threads.start();
		}
	}
}