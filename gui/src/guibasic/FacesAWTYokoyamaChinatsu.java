package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FacesAWTYokoyamaChinatsu {

    public static void main(String[] args) {
        new FacesAWTYokoyamaChinatsu();
    }

    FacesAWTYokoyamaChinatsu() {
        FaceFrame f = new FaceFrame();
        f.setSize(800, 800);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    class FaceFrame extends Frame {
        private int w;
        private int h;

        FaceFrame() {
        }

        public void paint(Graphics g) {
            w = 200;
            h = 200;

            FaceObj[] fobjs = new FaceObj[9];

            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    fobjs[i + 3 * j] = new FaceObj();
                    int x = 200 * i + 50;
                    int y = 200 * j + 50;
                    fobjs[i + 3 * j].setPosition(x, y);
                    fobjs[i + 3 * j].setEmotionLevel(i, j);
                    fobjs[i + 3 * j].drawFace(g, w, h);
                }
            }
        }
    }

    private class FaceObj {
        private int x, y;
        private Color faceColor;
        private Color eyeColor;
        private Color mouthColor;
        private String expression;

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setEmotionLevel(int i, int j) {
            if (i == 0 && j == 0) {
                faceColor = Color.RED; // 上段左の顔の色を赤に設定
            } else {
                faceColor = new Color((i * 50) % 255, (j * 80) % 255, ((i + j) * 100) % 255);
            }
            eyeColor = new Color(255 - ((i * 50) % 255), 255 - ((j * 80) % 255), 255 - (((i + j) * 100) % 255));
            mouthColor = Color.BLACK;

            switch (i + 3 * j) {
                case 0:
                    expression = "smile";
                    break;
                case 1:
                    expression = "laugh";
                    break;
                case 2:
                    expression = "sad";
                    break;
                case 3:
                    expression = "angry";
                    break;
                case 4:
                    expression = "surprised";
                    break;
                case 5:
                    expression = "wink";
                    break;
                case 6:
                    expression = "neutral";
                    break;
                case 7:
                    expression = "confused";
                    break;
                case 8:
                    expression = "excited";
                    break;
            }
        }

        public void drawFace(Graphics g, int w, int h) {
            drawRim(g, w, h);
            drawEyes(g, 35);
            drawMouth(g, w, h, 100);
        }

        public void drawRim(Graphics g, int w, int h) {
            g.setColor(faceColor);
            g.fillOval(x, y, w, h);
        }

        public void drawEyes(Graphics g, int r) {
            g.setColor(eyeColor);
            g.fillOval(x + 45, y + 65, r, r);
            if (!expression.equals("wink")) {
                g.fillOval(x + 120, y + 65, r, r);
            } else {
                g.fillRect(x + 120, y + 65 + r / 2, r, r / 4);
            }

            
            switch (expression) {
                case "angry":
                    g.setColor(Color.BLACK);
                    g.drawLine(x + 35, y + 55, x + 75, y + 45); // left brow
                    g.drawLine(x + 115, y + 45, x + 155, y + 55); // right brow
                    break;
                case "sad":
                    g.setColor(Color.BLACK);
                    g.drawLine(x + 35, y + 55, x + 75, y + 65); // left brow
                    g.drawLine(x + 115, y + 65, x + 155, y + 55); // right brow
                    break;
                case "surprised":
                    g.setColor(Color.BLACK);
                    g.drawOval(x + 55, y + 65, r / 2, r); // left eye
                    g.drawOval(x + 130, y + 65, r / 2, r); // right eye
                    break;
                case "wink":
                    g.setColor(Color.BLACK);
                    g.drawLine(x + 120, y + 75, x + 155, y + 75); // right eye
                    break;
                case "confused":
                    g.setColor(Color.BLACK);
                    g.drawArc(x + 55, y + 65, r / 2, r / 2, 0, 180); // left eye
                    g.drawArc(x + 130, y + 65, r / 2, r / 2, 0, 180); // right eye
                    break;
                case "excited":
                    g.setColor(Color.BLACK);
                    g.drawOval(x + 55, y + 65, r / 2, r / 2); // left eye
                    g.drawOval(x + 130, y + 65, r / 2, r / 2); // right eye
                    break;
            }
        }

        public void drawMouth(Graphics g, int w, int h, int mx) {
            int xMiddle = x + w / 2;
            int yMiddle = y + h - 30;

            g.setColor(mouthColor);

            switch (expression) {
                case "smile":
                    g.drawArc(xMiddle - mx / 2, yMiddle - 10, mx, 20, 0, -180);
                    break;
                case "laugh":
                    g.drawArc(xMiddle - mx / 2, yMiddle - 20, mx, 40, 0, -180);
                    g.drawLine(xMiddle - mx / 2, yMiddle, xMiddle + mx / 2, yMiddle);
                    break;
                case "sad":
                    g.drawArc(xMiddle - mx / 2, yMiddle, mx, 20, 0, 180);
                    break;
                case "angry":
                    g.drawLine(xMiddle - mx / 2, yMiddle, xMiddle + mx / 2, yMiddle);
                    break;
                case "surprised":
                    g.drawOval(xMiddle - mx / 4, yMiddle - 10, mx / 2, 20);
                    break;
                case "wink":
                    g.drawArc(xMiddle - mx / 2, yMiddle - 10, mx, 20, 0, -180);
                    break;
                case "neutral":
                    g.drawLine(xMiddle - mx / 2, yMiddle, xMiddle + mx / 2, yMiddle);
                    break;
                case "confused":
                    g.drawArc(xMiddle - mx / 2, yMiddle + 5, mx, 10, 0, 180); // mouth
                    break;
                case "excited":
                    g.drawArc(xMiddle - mx / 2, yMiddle - 20, mx, 40, 0, -180);
                    break;
            }
        }
    }
}
