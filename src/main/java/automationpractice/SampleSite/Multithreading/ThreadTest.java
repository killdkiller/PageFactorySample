package automationpractice.SampleSite.Multithreading;

public class ThreadTest {

	public static void main(String[] args) {
		
		ThreadClass objThreadClass= new ThreadClass();
		objThreadClass.start();
		System.out.println(objThreadClass.getName());
		
	}

}
