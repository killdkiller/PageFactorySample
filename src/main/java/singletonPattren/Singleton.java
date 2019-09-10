package singletonPattren;

public class Singleton {
	private static int Count=0;
	private static Singleton uniqueInstance=null;
	
	public static Singleton getMyInstance()
	{
		uniqueInstance = (uniqueInstance == null?new Singleton():uniqueInstance) ;
		return uniqueInstance;
	}
	
	private Singleton()
	{
		Count++;
		System.out.println(Count);
	}

}
