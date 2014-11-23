package com.eakjb.phys.behaviors;

import java.awt.geom.Rectangle2D;

import com.eakjb.phys.Behavior;
import com.eakjb.phys.Particle;
import com.eakjb.phys.World;

public class CollisionChecking implements Behavior {

	@Override
	public void step(long step, Particle p, World w) {
		Rectangle2D.Double hbounds=p.getBounds(p.getHsp(),0);
		Rectangle2D.Double vbounds=p.getBounds(0,p.getVsp());
		for (Particle other : w.getParticles()) {
			if (!other.equals(p)) {
				if (hbounds.intersects(other.getBounds())||
						p.getBounds(Math.signum(p.getHsp()),0).intersects(other.getBounds())) {
					while(!p.getBounds(Math.signum(p.getHsp())*2,0).intersects(other.getBounds())) {
						p.setX(p.getX()+Math.signum(p.getHsp()));
					}
					p.setHsp(0);
				}
				if (vbounds.intersects(other.getBounds())||
						p.getBounds(0,Math.signum(p.getVsp())).intersects(other.getBounds())) {
					while(!p.getBounds(0,Math.signum(p.getVsp())*2).intersects(other.getBounds())) {
						p.setY(p.getY()+Math.signum(p.getVsp()));
					}
					p.setVsp(0);
				}
			}
		}

	}

}
