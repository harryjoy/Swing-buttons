package harsh.p.raval;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class RoundButton extends JButton {
	private static final long serialVersionUID = -8876306477193123552L;
	private Shape shape;
	public RoundButton(String label) {
		super(label);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public RoundButton(String label, Icon icon) {
		super(label, icon);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public RoundButton(Icon icon) {
		super(icon);
		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);
	}
	public RoundButton(Action action) {
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
		g2.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
		super.paintComponent(g2);
	}
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getForeground());
		g2.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
	}
	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
}