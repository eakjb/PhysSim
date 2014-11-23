package com.eakjb.phys.behaviors;

import com.eakjb.phys.Behavior;
import com.eakjb.phys.Particle;
import com.eakjb.phys.World;

public abstract class Force implements Behavior {
	protected void applyForce(double force,Particle p,Particle other) {
		double B = Math.atan2(p.getY()-other.getY(), p.getX()-other.getX());
		if (p.getY()<other.getY()||p.getX()<other.getX()) B*=-1;
		p.setVsp(p.getVsp()+Math.sin(B)*force);
		p.setHsp(p.getHsp()+Math.cos(B)*force);
	}

	@Override
	public final void step(long step, Particle p, World w) {
		for (Particle other : w.getParticles()) {
			if (!other.equals(p)) {
				for (Behavior b : other.getBehaviors()) {
					forEachBehavior(p,other,b);
				}
				double fixedForce=fixedForce(p,other);
				if (fixedForce>0) applyForce(fixedForce,p,other);
			}
		}
	}
	protected abstract void forEachBehavior(Particle p,Particle other,Behavior b);
	protected abstract double fixedForce(Particle p,Particle other);
	public static double distance(Particle p, Particle other) {
		return p.getPoint().distance(other.getPoint());
	}
}
