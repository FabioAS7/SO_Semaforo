package controller;
import java.util.concurrent.Semaphore;

public class Threads extends Thread
{
	private int id;
	Semaphore semaforo;
	
	public Threads(int id, Semaphore semaforo) 
	{
		this.id = id;
		this.semaforo = semaforo;
	}
	
	public Threads()
	{
		super();
	}
	
	public void run()
	{
		if(id%3==1) 
		{
			for(int i=0;i<3;i++)
			{
				calculo(1001,200);		
				try
				{
					semaforo.acquire();
					transacao(1000);
				}
				catch(Exception e)
				{
					System.err.println(e.getMessage());
				}
				finally
				{
					semaforo.release();
				}
			}
		}
		else if(id%3==2)
		{
			for(int i=0;i<4;i++)
			{
				calculo(1501,500);
				try
				{
					semaforo.acquire();
					transacao(1500);
				}
				catch(Exception e)
				{
					System.err.println(e.getMessage());
				}
				finally
				{
					semaforo.release();
				}
			}
		}
		else if(id%3==0)
		{
			for(int i=0;i<4;i++)
			{
				calculo(2001,1000);
				try
				{
					semaforo.acquire();
					transacao(1500);
				}
				catch(Exception e)
				{
					System.err.println(e.getMessage());
				}
				finally
				{
					semaforo.release();
				}
			}
		}
	}
	
	void calculo(int max, int min)
	{
		int tempo;
		tempo = (int)(Math.random()*(max-min)+min);
		System.out.println("Thread #" + getId() + ", calculando...  tempo estimado: " + tempo + "ms");
		try 
		{
			sleep(tempo);
		} catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
			
	}
	
	void transacao(int espera)
	{
		System.out.println("Thread #" + getId() + ", Realizando Transacao... tempo estimado: " + espera + "s");
		try 
		{
			sleep(espera);
		} catch (InterruptedException e) 
		{
			System.err.println(e.getMessage());
		}

	}
}