package com.eakjb.phys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;


public class PhysicsSimulationCanvas extends JComponent {
	private static final long serialVersionUID = 4970663600229776961L;

	private World world;
	private long repaintTime=33;
	private long lastRepaint=0;
	
	private boolean paintVelocity = true;

	private Thread repaintThread;

	public PhysicsSimulationCanvas(World world) {
		this.world=world;
		this.repaintThread=new Thread(new Runnable() {
			public void run() {
				while (getWorld().isRunning()) {
					repaint();
					while (System.currentTimeMillis()<lastRepaint+repaintTime) {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		this.repaintThread.start();
	}

	@Override
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;

		for (Particle p : getWorld().getParticles()) {
			if (isPaintVelocity()) {
				g.setColor(Color.GREEN);
				g.draw(p.getBounds(p.getHsp(),p.getVsp()));
			}
			g.setColor(p.getColor());
			g.draw(p.getShape());
		}

		setLastRepaint(System.currentTimeMillis());
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Thread getRepaintThread() {
		return repaintThread;
	}

	public long getRepaintTime() {
		return repaintTime;
	}

	public void setRepaintTime(long repaintTime) {
		this.repaintTime = repaintTime;
	}

	public long getLastRepaint() {
		return lastRepaint;
	}

	private void setLastRepaint(long lastRepaint) {
		this.lastRepaint=lastRepaint;
	}

	public boolean isPaintVelocity() {
		return paintVelocity;
	}

	public void setPaintVelocity(boolean paintVelocity) {
		this.paintVelocity = paintVelocity;
	}
}
