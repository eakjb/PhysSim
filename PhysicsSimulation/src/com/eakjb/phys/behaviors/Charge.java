package com.eakjb.phys.behaviors;

import com.eakjb.phys.Behavior;
import com.eakjb.phys.Particle;

public class Charge extends Force {
	private double charge;
	public Charge(double charge) {
		this.setCharge(charge);
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	@Override
	protected void forEachBehavior(Particle p, Particle other, Behavior b) {
		if (b instanceof Charge) {
			Charge charge_other = (Charge) b;
			double distance=Force.distance(p, other);
			double force = charge_other.getCharge()*getCharge()/Math.pow(distance, 2);
			applyForce(force,p,other);
		}

	}
	@Override
	protected double fixedForce(Particle p,Particle other) {
		return 0;
	}


}
