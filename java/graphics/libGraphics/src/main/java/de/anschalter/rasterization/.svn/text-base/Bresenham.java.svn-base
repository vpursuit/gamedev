package de.anschalter.rasterization;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Bresenham extends Frame {

	private void lineGen(Graphics2D g2d, int x1, int y1, int x2, int y2) {

		boolean reflectD = false;
		boolean reflectX = false;
		boolean reflectY = false;

		if (x1 > x2) {
			reflectY = true;
			x1 *= -1;
			x2 *= -1;
		}

		if (y1 > y2) {
			reflectX = true;
			y1 *= -1;
			y2 *= -1;
		}

		final int dy = y2 - y1;
		final int dx = x2 - x1;

		if (dy == 0) {
			for (int i = x1; i <= x2; i++)
				drawPixel(g2d, i, y1, false, reflectX, reflectY);
		} else if (dx == 0) {
			for (int i = y1; i <= y2; i++)
				drawPixel(g2d, x1, i, false, reflectX, reflectY);
		} else {

			if (dx < dy) {
				reflectD = true;
				int tmp = y1;
				y1 = x1;
				x1 = tmp;
				tmp = y2;
				y2 = x2;
				x2 = tmp;
			}
		}
		bresenham(g2d, x1, y1, x2, y2, reflectD, reflectX, reflectY);

	}

	private void bresenham(Graphics2D g2d, int x1, int y1, int x2, int y2,
			boolean reflectD, boolean reflectX, boolean reflectY) {
		final int dy = y2 - y1;
		final int dx = x2 - x1;
		int xBegin = x1;
		int yBegin = y1;
		int E = (dy << 1) - dx;

		for (int i = 0; i < dx; i++) {
			drawPixel(g2d, xBegin, yBegin, reflectD, reflectX, reflectY);
			xBegin++;
			if (E > 0) {
				yBegin++;
				E += (dy - dx) << 1;
			} else {
				E += dy << 1;
			}
		}
		drawPixel(g2d, xBegin, yBegin, reflectD, reflectX, reflectY);
	}

	private void bresenham2(Graphics2D g2d, int x1, int y1, int x2, int y2) {

		int x = x1;
		int y = y1;

		final int dy = y2 - y1;
		final int dx = x2 - x1;

		int dxAbs = (dx > 0) ? dx : -dx;
		int dyAbs = (dy > 0) ? dy : -dy;

		final int s1 = Integer.signum(dx);
		final int s2 = Integer.signum(dy);

		boolean swap = false;
		if (dyAbs > dxAbs) {
			swap = true;
			final int tmp = dxAbs;
			dxAbs = dyAbs;
			dyAbs = tmp;
		}

		int D = (dyAbs << 1) - dxAbs;

		for (int i = 0; i < dxAbs; i++) {
			drawPixel(g2d, x, y, false, false, false);
			if (D >= 0) {
				D = D - (dxAbs << 1);
				if (swap) {
					x += s1;
				} else {
					y += s2;
				}
			}
			D = D + (dyAbs << 1);
			if (swap) {
				y += s2;
			} else {
				x += s1;
			}
		}

	}

	private void testLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
		bresenham2(g2d, x1, y1, x2, y2);
		// lineGen(g2d, x1, y1, x2, y2);
		bresenham2(g2d, x2, y2, x1, y1);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// g2d.drawString("Hello World", 30, 50);
		// for (int i = 0; i < 400; i++) {
		// drawPixel(g2d, i, i);
		// }
		super.paint(g);
		drawAxis(g2d, 40);

		testLine(g2d, 300, 100, 0, 0); // 1. Oktant
		testLine(g2d, 100, 300, 0, 0); // 2. Oktant
		testLine(g2d, -100, 300, 0, 0);// 3. Oktant
		testLine(g2d, -300, 100, 0, 0); // 4. Oktant
		testLine(g2d, -300, -100, 0, 0); // 5. Oktant
		testLine(g2d, -100, -300, 0, 0); // 6.Oktant
		testLine(g2d, 100, -300, 0, 0); // 7. Oktant
		testLine(g2d, 300, -100, 0, 0); // 8.Oktant

		testLine(g2d, -300, 100, 300, 100);
		testLine(g2d, -300, -100, 300, -100);
		testLine(g2d, -100, 300, -100, -300);
		testLine(g2d, 100, 300, 100, -300);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bresenham br = new Bresenham();
		br.setTitle("Bresenham");
		br.setSize(1000, 700);
		br.setVisible(true);

	}

	public Bresenham() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void drawGrid(Graphics2D g2d, int d) {
		Dimension dim = getSize();
		for (int i = 0; i < dim.height; i++) {
			if (i % d == 0)
				g2d.drawLine(0, i, dim.width, i);
		}
		for (int i = 0; i < dim.width; i++) {
			if (i % d == 0)
				g2d.drawLine(i, 0, i, dim.height);
		}
	}

	private void drawAxis(Graphics2D g2d, int d) {
		Dimension dim = getSize();

		int yMid = dim.height >> 1;
		int xMid = dim.width >> 1;

		// draw x-axis:
		g2d.drawLine(0, yMid, dim.width, yMid);
		for (int i = xMid; i < dim.width; i++) {
			if (i % d == 0) {
				g2d.drawLine(i, yMid - 5, i, yMid + 5);
				if (i - xMid > 0)
					g2d.drawString(Integer.toString(i - xMid), i, yMid - 8);
			}
		}
		for (int i = xMid; i > 0; i--) {
			if (i % d == 0) {
				g2d.drawLine(i, yMid - 5, i, yMid + 5);
				if (i - xMid < 0)
					g2d.drawString(Integer.toString(i - xMid), i, yMid - 8);
			}
		}

		// draw y-axis
		g2d.drawLine(xMid, 0, xMid, dim.height);
		for (int i = yMid; i < dim.height; i++) {
			if (i % d == 0) {
				g2d.drawLine(xMid - 5, i, xMid + 5, i);
				if (i - yMid > 0)
					g2d.drawString(Integer.toString(yMid - i), xMid + 8, i);
			}
		}
		for (int i = yMid; i > 0; i--) {
			if (i % d == 0) {
				g2d.drawLine(xMid - 5, i, xMid + 5, i);
				if (i - yMid < 0)
					g2d.drawString(Integer.toString(yMid - i), xMid + 8, i);
			}
		}

	}

	private void drawPixel(Graphics2D g2d, int x, int y, boolean reflectD,
			boolean reflectX, boolean reflectY) {

		int a = x;
		int b = y;

		if (reflectD) {
			int tmp = b;
			b = a;
			a = tmp;
		}

		if (reflectY)
			a *= -1;

		if (reflectX)
			b *= -1;

		final Dimension dim = getSize();
		final int xOrg = dim.width >> 1;
		final int yOrg = dim.height >> 1;

		g2d.drawLine(a + xOrg, yOrg - b, a + xOrg, yOrg - b);
	}
}
