package harsh.p.raval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class AngularButton extends JButton {
	private static final long serialVersionUID = 3033206151993876392L;
	private Polygon polygon;
	private int numberOfAngles = 6;
	private int x[] = new int[numberOfAngles];
	private int y[] = new int[numberOfAngles];
	private double angle = 2 * Math.PI / numberOfAngles;

	public AngularButton(String label) {
		super(label);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public AngularButton(String label, Icon icon, int numberOfAngles) {
		super(label, icon);
		this.numberOfAngles = numberOfAngles;
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public AngularButton(Icon icon, int numberOfAngles) {
		super(icon);
		this.numberOfAngles = numberOfAngles;
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public AngularButton(Action action, int numberOfAngles) {
		super(action);
		this.numberOfAngles = numberOfAngles;
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			g2.setColor(Color.lightGray);
		} else {
			g2.setColor(getBackground());
		}
		int x0 = getSize().width / 2;
		int y0 = getSize().height / 2;
		for (int i = 0; i < numberOfAngles; i++) {
			double v = i * angle;
			x[i] = x0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
			y[i] = y0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
		}
		g2.fillPolygon(x, y, numberOfAngles);
		super.paintComponent(g2);
	}
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getForeground());
		int x0 = getSize().width / 2;
		int y0 = getSize().height / 2;
		for (int i = 0; i < numberOfAngles; i++) {
			double v = i * angle;
			x[i] = x0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
			y[i] = y0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
		}
		g2.drawPolygon(x, y, numberOfAngles);
	}
	public boolean contains(int x1, int y1) {
		if (polygon == null || !polygon.getBounds().equals(getBounds())) {
			int x0 = getSize().width / 2;
			int y0 = getSize().height / 2;
			for (int i = 0; i < numberOfAngles; i++) {
				double v = i * angle;
				x[i] = x0 + (int) Math.round((getWidth() / 2) * Math.cos(v));
				y[i] = y0 + (int) Math.round((getHeight() / 2) * Math.sin(v));
			}
			polygon = new Polygon(x, y, numberOfAngles);
		}
		return polygon.contains(x1, y1);
	}
}