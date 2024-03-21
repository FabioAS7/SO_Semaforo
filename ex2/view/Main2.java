package view;

import java.util.concurrent.Semaphore;
import controller.Thread_comida;

public class Main2 
{
	public static void main(String[] arg)
	{
		Semaphore semaforo = new Semaphore(1);
		
		for(int i=1;i<6;i++)
		{
			Thread_comida thread = new Thread_comida(i,semaforo);
			thread.start();
		}
	}
}
