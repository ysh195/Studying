package ch14.sec05;

public class WorkThread extends Thread{
	
	public boolean work = true;
	
	public WorkThread(String name) {
		setName(name);
	}

	@Override
	public void run() {
		while(true) {
			if(work) {
				System.out.println(getName()+" : 작업 중");
			}
			else {
				WorkThread.yield();
			}
		}
	}
}
