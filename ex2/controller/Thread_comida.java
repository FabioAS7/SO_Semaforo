package controller;

import java.util.concurrent.Semaphore;

public class Thread_comida extends Thread
{
	private int id;
	Semaphore semaforo;
	
	public Thread_comida(int id, Semaphore semaforo)
	{
		this.id = id;
		this.semaforo = semaforo;
	}
	
	public void run()
	{
		preparar_pratos();
		try 
		{
			semaforo.acquire();
			entregar_prato();
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
		finally
		{
			semaforo.release();
		}
	}

	private void preparar_pratos()
	{
		String prato;
		int tempo__de_preparo, max, min, tempo_no_fogo = 0;
		float porcentagem_de_preparo;
		
		max = (id%2==0)?1200:800;
		min = (id%2==0)?600:500;
		prato = (id%2==0)?"Lasanha a Bolonhesa":"Sopa de cebola";
		tempo__de_preparo = (int)(Math.random()*(max-min)+min);
		
		System.out.println("Thread #" + getId() + ", Prato: " + prato + " INICIOU SE!");
		
		while(tempo_no_fogo<=tempo__de_preparo)
		{
			tempo_no_fogo = tempo_no_fogo + 100;
			porcentagem_de_preparo = (float)tempo_no_fogo/(float)tempo__de_preparo;
			
			try 
			{
				sleep(100);
				System.out.printf("Thread #%d, Prato: %s, %.2f \n",getId(),prato,porcentagem_de_preparo);
			} 
			catch (Exception e) 
			{
				System.err.println(e.getMessage());
			}
		}
		
		System.out.println("Thread #" + getId() + ", Prato: " + prato + ", PRECISA SER SEVIDO!");
	}
	
	public void entregar_prato()
	{
		String prato;
		prato = (id%2==0)?"Lasanha a Bolonhesa":"Sopa de cebola";
		
		System.err.println("Thread #" + getId() + ", prato: " + prato + ", Esta sendo entrgue");
		
		try 
		{
			sleep(500);
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
	}
}
