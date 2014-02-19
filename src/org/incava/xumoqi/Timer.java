package org.incava.xumoqi;

import android.util.Log;

public class Timer {
	private final String component;
	private final String activity;
	private final long start;

	public Timer(String component, String activity) {
		this.component = component;
		this.activity = activity;
		start = System.currentTimeMillis();
		log("start");
	}

	public void done(String msg) {
		long end = System.currentTimeMillis();
		log("duration: " + msg, end - start);
	}

	public void done() {
		long end = System.currentTimeMillis();
		log("duration", end - start);
	}
	
	private void log(String what, long time) {
		Log.i(component, activity + ": " + what + ": " + time);
	}
	
	private void log(String what) {
		Log.i(component, activity + ": " + what);
	}
}
