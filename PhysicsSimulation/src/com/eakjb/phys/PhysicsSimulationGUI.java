package com.eakjb.phys;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;

import com.eakjb.phys.behaviors.Charge;
import com.eakjb.phys.behaviors.CollisionChecking;
import com.eakjb.phys.behaviors.HyperbolicRepulsion;

public class PhysicsSimulationGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7615462036350516798L;
	private JPanel contentPane;
	private PhysicsSimulationCanvas canvas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					World w = new World();
					Particle p1 =new Particle(50,150,Color.RED,50,w);
					//p1.setHsp(5);
					p1.getBehaviors().add(new Charge(100));
					p1.getBehaviors().add(new HyperbolicRepulsion(50));
					
					Particle p2 =new Particle(250,150,Color.BLUE,50,w);
					//p2.setHsp(-7);
					p2.getBehaviors().add(new Charge(-100));
					p2.getBehaviors().add(new HyperbolicRepulsion(50));
					
					//Particle p3 =new Particle(150,50,Color.CYAN,50,w);
					//p3.setVsp(3);
					
					Behavior c = new CollisionChecking();
					p1.getBehaviors().add(c);
					p2.getBehaviors().add(c);
					//p3.getBehaviors().add(c);
					
					w.getParticles().add(p1);
					w.getParticles().add(p2);
					//w.getParticles().add(p3);
					PhysicsSimulationGUI frame = new PhysicsSimulationGUI(new PhysicsSimulationCanvas(w));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PhysicsSimulationGUI(PhysicsSimulationCanvas canvas) {
		this.setCanvas(canvas);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		splitPane.setRightComponent(canvas);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BorderLayout());
		
		JLabel label = new JLabel("PhysicsSimulation");
		panel.add(label,BorderLayout.NORTH);
		
		JPanel mainPanel = new JPanel();
		panel.add(mainPanel,BorderLayout.CENTER);
		
		JCheckBox velocityBox = new JCheckBox("Render Velocity");
		velocityBox.setSelected(canvas.isPaintVelocity());
		velocityBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				canvas.setPaintVelocity(e.getStateChange()==ItemEvent.SELECTED);
			}
			
		});
		mainPanel.add(velocityBox);
		
		JPanel btnPanel = new JPanel();
		panel.add(btnPanel,BorderLayout.SOUTH);
		
		JButton stepBtn = new JButton("Step");
		stepBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCanvas().getWorld().step();
				getCanvas().repaint();
			}
		});
		stepBtn.setEnabled(!getCanvas().getWorld().isPlaying());
		btnPanel.add(stepBtn);
		
		JToggleButton goBtn = new JToggleButton("Play");
		goBtn.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				getCanvas().getWorld().setPlaying(e.getStateChange()==ItemEvent.SELECTED);	
				stepBtn.setEnabled(!getCanvas().getWorld().isPlaying());			
			}
			
		});
		btnPanel.add(goBtn);
	}

	public PhysicsSimulationCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(PhysicsSimulationCanvas canvas) {
		this.canvas = canvas;
	}

}
