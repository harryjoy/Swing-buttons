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

public class TriangleButton extends JButton {
	private static final long serialVersionUID = -8965685552214188953L;
	private Polygon polygon;
	public TriangleButton(String label) {
		super(label);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public TriangleButton(String label, Icon icon) {
		super(label, icon);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public TriangleButton(Icon icon) {
		super(icon);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public TriangleButton(Action action) {
		super(action);
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
		int xPoints[] = { getSize().width / 2, 0, getSize().width };
		int yPoints[] = { 0, getSize().height, getSize().height };
		g2.fillPolygon(xPoints, yPoints, xPoints.length);
		super.paintComponent(g2);
	}
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getForeground());
		int xPoints[] = { getSize().width / 2, 0, getSize().width };
		int yPoints[] = { 0, getSize().height, getSize().height };
		g2.drawPolygon(xPoints, yPoints, xPoints.length);
	}
	public boolean contains(int x, int y) {
		if (polygon == null || !polygon.getBounds().equals(getBounds())) {
			int xPoints[] = { getSize().width / 2, 0, getSize().width };
			int yPoints[] = { 0, getSize().height, getSize().height };
			polygon = new Polygon(xPoints, yPoints, xPoints.length);
		}
		return polygon.contains(x, y);
	}
}