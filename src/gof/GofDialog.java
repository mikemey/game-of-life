
package gof;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GofDialog {

	Canvas canvas;

	GofCalc gofCalc = null;

	Color backgroundColor = null;
	Color foregroundColor = null;
	Color edenColor = null;


	public GofDialog(GofCalc gc) {
		gofCalc = gc;
	}

	/**
	 * Runs the application
	 */
	public void run() {
		final Display display = new Display();
		backgroundColor = display.getSystemColor(SWT.COLOR_WHITE);
		foregroundColor = display.getSystemColor(SWT.COLOR_BLACK);
		edenColor = display.getSystemColor(SWT.COLOR_YELLOW);
		Shell shell = new Shell(display);
		shell.setSize(GofMain.FIELD_X * GofMain.ZOOM_FACTOR + 9,// 
				GofMain.FIELD_Y * GofMain.ZOOM_FACTOR + 29);

		int dispWidth = Display.getCurrent().getBounds().width;
		int shellX = (dispWidth - shell.getSize().x) / 2;
		shell.setLocation(shellX, 10);

		shell.setText("Game of Life");
		createContents(shell);
		shell.open();

		// Set up the timer for the animation
		Runnable runnable = new Runnable() {

			public void run() {
				animate();
				display.timerExec(GofMain.TIMER_INTERVAL, this);
			}
		};

		// Launch the timer
		display.timerExec(GofMain.TIMER_INTERVAL, runnable);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		// Kill the timer
		display.timerExec(-1, runnable);
		display.dispose();
	}

	/**
	 * Creates the main window's contents
	 * 
	 * @param shell the main window
	 */
	private void createContents(final Shell shell) {
		shell.setLayout(new FillLayout());

		// Create the canvas for drawing
		canvas = new Canvas(shell, SWT.NO_BACKGROUND);
		canvas.setBackground(backgroundColor);
		canvas.setSize(GofMain.FIELD_X * GofMain.ZOOM_FACTOR + 2, //
				GofMain.FIELD_Y * GofMain.ZOOM_FACTOR + 2);
		final Image image = new Image(shell.getDisplay(), canvas.getBounds());
		final GC gcImage = new GC(image);

		// Draw the background
		gcImage.setBackground(backgroundColor);

		if (GofMain.GARDEN_EDEN) {
			gcImage.setForeground(edenColor);
			int iStart = (GofMain.EDEN_X_START + 1) * GofMain.ZOOM_FACTOR;
			int iEnd = GofMain.EDEN_X_END * GofMain.ZOOM_FACTOR;
			int jStart = (GofMain.EDEN_Y_START + 1) * GofMain.ZOOM_FACTOR;
			int jEnd = GofMain.EDEN_Y_END * GofMain.ZOOM_FACTOR;

			for (int i = iStart; i < iEnd; i++) {
				for (int j = jStart; j < jEnd; j++) {
					gcImage.drawPoint(i, j);
				}
			}
		}
		canvas.addPaintListener(new PaintListener() {

			public void paintControl(PaintEvent event) {
				boolean[][] field = gofCalc.getField();
				boolean[][] changeMatrix = gofCalc.getChangedMatrix();
				for (int i = 0; i < GofMain.FIELD_X; i++) {
					for (int j = 0; j < GofMain.FIELD_Y; j++) {
						if (!changeMatrix[i][j]) {
							continue;
						}
						setColorOnGC(gcImage, field[i][j], i, j);
						drawOneCell(gcImage, i, j);
					}
				}

				// Draw the offscreen buffer to the screen
				event.gc.drawImage(image, 0, 0);
			}
		});

		canvas.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(@SuppressWarnings("unused")
			DisposeEvent e) {
				// Clean up
				image.dispose();
				gcImage.dispose();
			}
		});

		canvas.addFocusListener(new FocusListener() {

			@SuppressWarnings("unused")
			public void focusLost(FocusEvent e) {
			}

			@SuppressWarnings("unused")
			public void focusGained(FocusEvent e) {
				// full repaint
				boolean[][] field = gofCalc.getField();
				for (int i = 0; i < GofMain.FIELD_X; i++) {
					for (int j = 0; j < GofMain.FIELD_Y; j++) {
						setColorOnGC(gcImage, field[i][j], i, j);
						drawOneCell(gcImage, i, j);
					}
				}
			}

		});
	}

	/**
	 * Animates the next frame
	 */
	public void animate() {
		gofCalc.recalculateField();

		// Force a redraw
		canvas.redraw();
	}

	void setColorOnGC(GC gc, boolean isAlive, int i, int j) {
		if (isAlive) {
			gc.setForeground(foregroundColor);
		}
		else {
			gc.setForeground(backgroundColor);
			if (GofMain.GARDEN_EDEN) {
				if (i > GofMain.EDEN_X_START && //
						i < GofMain.EDEN_X_END && //
						j > GofMain.EDEN_Y_START && //
						j < GofMain.EDEN_Y_END) {//
					gc.setForeground(edenColor);
				}
			}
		}
	}

	void drawOneCell(GC gc, int i, int j) {
		int startX = i * GofMain.ZOOM_FACTOR;
		int startY = j * GofMain.ZOOM_FACTOR;

		for (int k = 0; k < GofMain.ZOOM_FACTOR; k++) {
			for (int m = 0; m < GofMain.ZOOM_FACTOR; m++) {
				gc.drawPoint(startX + k, startY + m);
			}
		}
	}
}
