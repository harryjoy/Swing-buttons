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