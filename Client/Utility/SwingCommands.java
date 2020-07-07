package Utility;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SwingCommands {

    public static ExecutorService threadsForEverybody = Executors.newCachedThreadPool();

public  static class MyBar extends  JMenuBar implements Localizeable{
    private JMenu language = new JMenu();

    public MyBar(int size){
        createLocale();
        LocaleController.regist(this);
        Font font = new Font("Century Gothic", Font.PLAIN, size);

        language.setFont(font);
        JMenuItem rus = new JMenuItem("Русский");
        rus.setFont(font);
        rus.setIcon(new ImageIcon("images/rus.png"));
        JMenuItem english = new JMenuItem("English");
        english.setFont(font);
        english.setIcon(new ImageIcon("images/eng.png"));
        JMenuItem português = new JMenuItem("Português");
        português.setIcon(new ImageIcon("images/port.png"));
        português.setFont(font);
        JMenuItem lietuvis = new JMenuItem("Lietuvis");
        lietuvis.setIcon(new ImageIcon("images/lit.png"));
        lietuvis.setFont(font);
        language.add(rus);
        language.add(english);
        language.add(português);
        language.add(lietuvis);
        add(language);

        rus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LocaleController.getLocal("Russian") != Locale.getDefault())
                    LocaleController.changeLocale(LocaleController.getLocal("Russian"));
            }
        });
        english.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LocaleController.getLocal("English") != Locale.getDefault())
                    LocaleController.changeLocale(LocaleController.getLocal("English"));
            }
        });
        português.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LocaleController.getLocal("Portugal") != Locale.getDefault())
                    LocaleController.changeLocale(LocaleController.getLocal("Portugal"));
            }
        });
        lietuvis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LocaleController.getLocal("Lithuanian") != Locale.getDefault())
                    LocaleController.changeLocale(LocaleController.getLocal("Lithuanian"));
            }
        });

    }

    @Override
    public void createLocale() {
        ResourceBundle resourceBundle = LocaleController.getResourceBundle();
        language.setText(resourceBundle.getString("Language"));
    }
    }


    public static class CustomButton extends  JButton{
        CustomButton(int xloc, int yloc, int width, int height, String text, int size, int font) throws IOException {
            Image im = ImageIO.read(new File("images\\button180x40.png"));
            Image im1 = ImageIO.read(new File("images\\pressedbutton180x40.png"));
            ImageIcon icon = new ImageIcon(getScaledImage(im, width, height));
            setIcon(icon);
           setVerticalTextPosition(JButton.BOTTOM);
           setHorizontalTextPosition(JButton.CENTER);
           setIconTextGap(-height * 6 / 7);
           setText(text);
           setFont(new Font("Century Gothic", font, size));
           addMouseListener(new MouseListener() {
                @Override
                synchronized public void mouseClicked(MouseEvent e) {
                    threadsForEverybody.execute(new Thread(() -> {
                        setIcon(new ImageIcon(getScaledImage(im1, width, height)));
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        setIcon(new ImageIcon(getScaledImage(im, width, height)));
                    }));

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    setIcon(new ImageIcon(getScaledImage(im1, width, height)));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    setIcon(new ImageIcon(getScaledImage(im, width, height)));
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
           setLayout(null);
           setLocation(xloc, yloc);
           setOpaque(false);
           setContentAreaFilled(false);
           setSize(width, height);
           setBorderPainted(false);
        }
    }


    public static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }


    public static class HintTextFieldUI extends BasicTextFieldUI implements FocusListener {

        private String hint;
        private boolean hideOnFocus;
        private Color color;

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
            repaint();
        }

        private void repaint() {
            if (getComponent() != null) {
                getComponent().repaint();
            }
        }

        public boolean isHideOnFocus() {
            return hideOnFocus;
        }

        public void setHideOnFocus(boolean hideOnFocus) {
            this.hideOnFocus = hideOnFocus;
            repaint();
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
            repaint();
        }

        public HintTextFieldUI(String hint) {
            this(hint, false);
        }

        public HintTextFieldUI(String hint, boolean hideOnFocus) {
            this(hint, hideOnFocus, null);
        }

        public HintTextFieldUI(String hint, boolean hideOnFocus, Color color) {
            this.hint = hint;
            this.hideOnFocus = hideOnFocus;
            this.color = color;
        }

        @Override
        protected void paintSafely(Graphics g) {
            super.paintSafely(g);
            JTextComponent comp = getComponent();
            if (hint != null && comp.getText().length() == 0 && (!(hideOnFocus && comp.hasFocus()))) {
                if (color != null) {
                    g.setColor(color);
                } else {
                    g.setColor(comp.getForeground().brighter().brighter().brighter());
                }
                int padding = (comp.getHeight() - comp.getFont().getSize()) / 2;
                g.drawString(hint, 1, comp.getHeight() - padding - 1);

            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (hideOnFocus) repaint();

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (hideOnFocus) repaint();
        }

        @Override
        protected void installListeners() {
            super.installListeners();
            getComponent().addFocusListener(this);
        }

        @Override
        protected void uninstallListeners() {
            super.uninstallListeners();
            getComponent().removeFocusListener(this);
        }
    }

    public static class Background extends JPanel {
        Image im = null;
        String file;

        public Background(String filepath) {
            file = filepath;

        }

        @Override
        public void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;
            super.paintComponent(graphics2D);
            try {
                im = ImageIO.read(new File(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
            graphics2D.drawImage(im, 0, 0, null);
        }
    }

    public class Size {
        Dimension dimension;

        public Size(Dimension dimension) {
            this.dimension = dimension;
        }

        public int getSize(int px) {
            int size = 0;


            return size;
        }
    }



    public static class TextField extends JTextField {
        public TextField(int width, int height, int x, int y, String text) {
            String text1 = text;
            setText(text);
            setSize(width, height);
            setLocation(x, y);
            setBorder(null);
            setForeground(Color.gray);
            Font test = new Font("Century Gothic", Font.PLAIN, 20);
            Font font = new Font("Century Gothic", Font.PLAIN, returnSize(this));
            setFont(font);

            addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    setFont(test);
                    setForeground(Color.BLACK);
                    setText("");
                    setFont(test);
                    setForeground(Color.BLACK);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().equals("") || getText() == null) {
                        setFont(font);
                        setForeground(Color.gray);
                        setText(text);
                    }
                }
            });

        }
    }

    public static class Text extends JLabel {
        Text(int x, int y, int width, int height, String text) {
            setText(text);
            setSize(width, height);
            setLocation(x, y);
            Font font = new Font("Century Gothic", Font.PLAIN, 16);
            setFont(font);
            try {
                MainWindow.setFontSize(this);
            } catch (NullPointerException e){}
        }
    }

    public static int returnSize(JTextField jTextField) {
        Font labelFont = jTextField.getFont();
        String labelText = jTextField.getText();
        int stringWidth = jTextField.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = jTextField.getWidth();
        double widthRatio = (double) componentWidth / (double) stringWidth;
        int newFontSize = (int) (labelFont.getSize() * widthRatio);
        int componentHeight = jTextField.getHeight();
        int fontSizeToUse = Math.min(newFontSize, componentHeight) - 1;
        return fontSizeToUse;
    }

    public static class Human extends JLabel {
        int x, y, width, height;
        Color color;

        public Human(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
            this.color = color;
            setSize(width, height * 2);
            setLocation(x, y);
        }

        public void draw(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            Shape rect = new Rectangle(0, (int) (height * 0.7), width, (int) (height * 0.8));
            g2d.setColor(color);
            g2d.fill(rect);

            Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);
            g2d.setColor(new Color(255, 199, 161));
            g2d.fill(circle);
            Ellipse2D.Double eye1 = new Ellipse2D.Double(width * 0.15, height * 0.4, width * 0.2, height * 0.15);
            Ellipse2D.Double eye2 = new Ellipse2D.Double(width * 0.66, height * 0.4, width * 0.2, height * 0.15);
            g2d.setColor(new Color(16, 133, 255));
            g2d.fill(eye1);
            g2d.fill(eye2);

            Polygon mustache = new Polygon();
            mustache.addPoint((int) (width * 0.3), (int) (height * 0.7));
            mustache.addPoint((int) (width * 0.4), (int) (height * 0.6));
            mustache.addPoint((int) (width * 0.6), (int) (height * 0.6));
            mustache.addPoint((int) (width * 0.7), (int) (height * 0.7));
            g2d.setColor(Color.BLACK);
            g2d.fill(mustache);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }

    }

    public static class Animation {
        public static void startAnime(JLabel humans) {
            threadsForEverybody.execute(
                    new Thread(() -> {
                        while (true) {
                            try {
                                humans.setLocation(humans.getX(), humans.getY() + 3);
                                Thread.sleep(500);
                                humans.setLocation(humans.getX(), humans.getY() - 3);
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }));
        }

    }
}
