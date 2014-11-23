package com.eakjb.phys.behaviors;

import com.eakjb.phys.Behavior;
import com.eakjb.phys.Particle;

public class HyperbolicRepulsion extends Force {
	private double strength;
	public HyperbolicRepulsion(double strength) {
		this.setStrength(strength);
	}
	@Override
	protected void forEachBehavior(Particle p, Particle other, Behavior b) {}

	@Override
	protected double fixedForce(Particle p,Particle other) {
		return strength/Force.distance(p, other);
	}
	public double getStrength() {
		return strength;
	}
	public void setStrength(double strength) {
		this.strength = strength;
	}

}
