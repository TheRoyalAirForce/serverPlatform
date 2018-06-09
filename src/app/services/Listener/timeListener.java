package app.services.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import app.util.dateTimeUtil;

public class timeListener implements ServletContextListener {
	public boolean flag;
	public TimeThread myThread;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//myThread.stop();
		if (myThread != null && myThread.isInterrupted()) {
			myThread.interrupt();
		}
		System.out.println("Time Listener destroy.");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Time Listener initialize.");
		flag = false;
		myThread = new TimeThread();
		myThread.start();
	}

	class TimeThread extends Thread{
		final int yq = 1000;
		@Override
		public void run() {
			try {
				sleep(60 * 5 * yq);
				String mTime = dateTimeUtil.getChangeTime(dateTimeUtil.getTimel());
				System.out.println("Time Listener says: Current Time: " + mTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
