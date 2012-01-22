// Copyright (c) 2011, Harsh P. Raval
// All rights reserved.
//
// Redistribution and use in source and binary forms, 
// with or without modification, are permitted provided that the 
// following conditions are met:
//
// Redistributions of source code must retain the above copyright notice, 
// this list of conditions and the following disclaimer.
// 
// Redistributions in binary form must reproduce the above copyright notice, 
// this list of conditions and the following disclaimer in the documentation 
// and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
// THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE 
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
// (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS 
// OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY 
// OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
// OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF 
// THE POSSIBILITY OF SUCH DAMAGE.
package harsh.p.raval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * To make star shaped button.
 * <b>arms</b> - Number of arms in star shape.
 * @author harsh
 */
public class StarButton extends JButton {
	private static final long serialVersionUID = -3109179877986977250L;
	private Shape shape;
	private int arms;
	public StarButton(String label, int arms) {
		super(label);
		this.arms = arms;
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public StarButton(String label, Icon icon, int arms) {
		super(label, icon);
		this.arms = arms;
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public StarButton(Icon icon, int arms) {
		super(icon);
		this.arms = arms;
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public StarButton(Action action, int arms) {
		super(action);
		this.arms = arms;
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	private static Shape makeStarDesign(int arms, Point center, double r_out, double r_in) {
		double angle = Math.PI / arms;
		GeneralPath path = new GeneralPath();
		for (int i = 0; i < 2 * arms; i++) {
			double r = (i & 1) == 0 ? r_out : r_in;
			Point2D.Double p = new Point2D.Double(center.x + Math.cos(i * angle) * r, center.y + Math.sin(i * angle) * r);
			if (i == 0)
				path.moveTo(p.getX(), p.getY());
			else
				path.lineTo(p.getX(), p.getY());
		}
		path.closePath();
		return path;
	}
	protected void paintComponent(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			graphics2d.setColor(Color.lightGray);
		} else {
			graphics2d.setColor(getBackground());
		}
		graphics2d.fill(makeStarDesign(arms, new Point(50, 50), 50, 30));
		super.paintComponent(g);
	}
	protected void paintBorder(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setColor(getForeground());
		graphics2d.draw(makeStarDesign(arms, new Point(50, 50), 50, 30));
	}
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Area(makeStarDesign(arms, new Point(50, 50), 50, 30));
		}
		return shape.contains(x, y);
	}
}