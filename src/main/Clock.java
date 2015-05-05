package main;

public class Clock {
    private int displayTime;
    private int startTime;
    private int currTime;
    private Button displayField;
    private boolean ticked;
    private int oldCurrTime;
    private boolean running;

    public Clock(Button displayField) {
	oldCurrTime = 0;
	ticked = false;
	running = false;
	this.displayField = displayField;
	displayTime = 0;
    }

    public void start() {
	startTime = (int) System.currentTimeMillis();
	running = true;
	new Thread(new Runnable() {
	    @Override
	    public void run() {
		while (running) {
		    currTime = (int) System.currentTimeMillis();
		    if (currTime != oldCurrTime) {
			ticked = false;
		    }
		    if ((currTime - startTime) % 1000 == 0 && !ticked) {
			oldCurrTime = currTime;
			tick();
		    }
		}
	    }
	}).start();
    }

    public void stop() {
	running = false;
    }

    public void tick() {
	ticked = true;
	displayTime++;
	displayField.setText(toString());
    }

    public boolean isRunning() {
	return running;
    }

    @Override
    public String toString() {
	return displayTime + "";
    }
}
