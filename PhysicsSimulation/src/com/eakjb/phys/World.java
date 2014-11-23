package com.eakjb.phys;

import java.util.ArrayList;

public class World {
	private long step = 0;
	/**
	 * If physics is currently running on the world (I.E. in its physics thread)
	 */
	private boolean isPlaying = false;
	/**
	 * Used to signal the physics thread to stop running
	 */
	private boolean isRunning = true;
	private long stepLength = 33; //33 milliseconds
	private Thread physicsThread;
	private ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public World(boolean startPlaying) {
		this.isPlaying=startPlaying;
		physicsThread=new Thread(new Runnable() {
			public void run() {
				while (isRunning()) {
					if (isPlaying()) {
						step();
						try {
							Thread.sleep(getStepLength());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		physicsThread.start();
	}
	
	public World() {
		this(false);
	}
	
	protected void step() {
		for (Particle p : particles) {
			p.step(step);
		}
		step++;
	}

	public ArrayList<Particle> getParticles() {
		return particles;
	}

	public void setParticles(ArrayList<Particle> particles) {
		this.particles = particles;
	}

	public long getStep() {
		return step;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public Thread getPhysicsThread() {
		return physicsThread;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public long getStepLength() {
		return stepLength;
	}

	public void setStepLength(long stepLength) {
		this.stepLength = stepLength;
	}
}
