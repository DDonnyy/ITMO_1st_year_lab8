package Utility;

import Common.Ticket;
import Common.TicketCollection;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ReceiverAndCanvasChanger {
    public static Thread anime;
    public static Socket sock;
    public static AtomicReference<String> result = new AtomicReference<>();
    public static HashMap<Long, TicketImage> addedTickets = new HashMap<>();

    public static void receivereg() throws IOException {
        byte[] buffer = new byte[1000];
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(sock.getInputStream());
            HashMap<Integer, Object> map = (HashMap<Integer, Object>) objectInputStream.readObject();
            String message = (String) map.get(1);
            result.set(message);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    public  String getAnswer() {
        try {
            while (result.get() == null && ClientSender.serverisconnected) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String answer = result.get();
            return answer;
        } finally {
            result.set(null);
        }
    }

    public void receive(TicketTable table, JFrame jframe, String user) {
        TicketCollection ticketCollection = new TicketCollection();
        SwingCommands.threadsForEverybody.execute(
                new Thread(() -> {
                    boolean stop = false;
                    while (!stop) {
                        try {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ObjectInputStream objectInputStream = new ObjectInputStream(sock.getInputStream());
                            HashMap<Integer, Object> map = (HashMap<Integer, Object>) objectInputStream.readObject();
                            String message = (String) map.get(1);
                            TreeMap<Long, Ticket> tickets = (TreeMap<Long, Ticket>) map.get(2);

                            String toDoWithCollection = (String) map.get(3);
                            if (message == null && tickets != null) {
                                toDoWithCollection(tickets, toDoWithCollection,  jframe, user);
                                changeTable(table);

                            } else if (message != null && tickets == null) {
                                result.set(message);
                            } else if (message != null && tickets != null) {
                                result.set(message);
                                toDoWithCollection(tickets, toDoWithCollection,  jframe, user);
                                changeTable(table);
                            }
                        } catch (SocketException e) {
                            stop = true;
                        } catch (ClassNotFoundException | IOException e) {
                            e.printStackTrace();

                        }
                    }
                }));
    }

    private void changeTable(TicketTable table) {
        TicketCollection ticketCollection = new TicketCollection();
        if (ticketCollection.getSize() > 0) {
         table.clearCollection();
         table.fillCollection();
        }
    }
    public static class TicketTable extends JTable implements Localizeable {
        TicketCollection ticketCollection = new TicketCollection();
        JTable table = this;
        HashMap<Long, Color> tableColors = new HashMap<>();
        public TicketTable(DefaultTableModel defaultTableModel){
            super(defaultTableModel);
            LocaleController.regist(this);
            setRowHeight(getRowHeight() + 5);
            setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
            setFont(new Font("Century Gothic", Font.BOLD, 12));
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(defaultTableModel);
            table.setRowSorter(sorter);
            setPreferredScrollableViewportSize(new Dimension(450, 63));
            setFillsViewportHeight(true);
            getTableHeader().setReorderingAllowed(false);
            setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            setCellsAlignment(SwingConstants.CENTER);
            this.fillCollection();
            createLocale();
            getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent event) {
                    if (table.getSelectedRow() > 0) {
                        addedTickets.get(Long.parseLong((String) table.getValueAt(table.getSelectedRow(), 0))).updateDialog.setVisible(true);
                    }
                }
            });
        }
        public void fillCollection() {
            this.clearCollection();
            DefaultTableModel model = (DefaultTableModel) this.getModel();
            int i = 0;
            Object[] data = new Object[15];
            ticketCollection.getTickets().entrySet().stream().forEach(x -> {
                Ticket ticket = x.getValue();
                data[0] = ticket.getMapKey().toString();
                data[1] = ticket.getCreationDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
                data[2] = ticket.getName();
                data[3] = String.valueOf(ticket.getCoordinates().getX());
                data[4] = String.valueOf(ticket.getCoordinates().getY());
                data[5] = ticket.getPrice().toString();
                data[6] = ticket.getComment();
                data[7] = ticket.getType().toString();
                data[8] = ticket.getPerson().getHairColor().toString();
                data[9] = ticket.getPerson().getNationality().toString();
                data[10] = String.valueOf(ticket.getPerson().getLocation().getX());
                data[11] = String.valueOf(ticket.getPerson().getLocation().getY());
                data[12] = ticket.getPerson().getLocation().getName();
                data[13] = ticket.getPerson().getPassportID();
                data[14] = ticket.getUser();
                tableColors.put(ticket.getMapKey(), ticket.getColor());
                model.addRow(data);
            });
        }
        private String[] emptyRow = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "",""};
        private String[][] emptyRow1 = new String[][]{};
        public void clearCollection() {
            DefaultTableModel dm = (DefaultTableModel) getModel();
            int rowCount = dm.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
                dm.removeRow(i);
            }
        }
        public void setCellsAlignment(int alignment) {
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(alignment);
            TableModel tableModel = this.getModel();
            for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
                this.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
            }
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintEmptyRows(g);
        }


        public void paintEmptyRows(Graphics g) {
            Graphics newGraphics = g.create();


            Rectangle rectOfLastRow = getCellRect(getRowCount() - 1, 0, true);
            int firstNonExistentRowY = rectOfLastRow.y; //the top Y-coordinate of the first empty tablerow

            if (getVisibleRect().height > firstNonExistentRowY) //only paint the grid if empty space is visible
            {
                //fill the rows alternating and paint the row-lines:
                int rowYToDraw = (firstNonExistentRowY - 1) + getRowHeight(); //minus 1 otherwise the first empty row is one pixel to high
                int actualRow = getRowCount() - 1; //to continue the stripes from the area with table-data

                while (rowYToDraw < getHeight()) {
                        newGraphics.setColor(Color.WHITE); //change this to another color (Color.YELLOW, anyone?) to show that only the free space is painted
                        newGraphics.fillRect(0, rowYToDraw, getWidth(), getRowHeight());
                    newGraphics.drawLine(0, rowYToDraw, getWidth(), rowYToDraw);

                    rowYToDraw += getRowHeight();
                    actualRow++;
                }


                //paint the column-lines:
                int x = 0;
                for (int i = 0; i < getColumnCount(); i++) {
                    TableColumn column = getColumnModel().getColumn(i);
                    x += column.getWidth(); //add the column width to the x-coordinate

                    newGraphics.drawLine(x - 1, firstNonExistentRowY, x - 1, getHeight());
                }

                newGraphics.dispose();

            } //if empty space is visible

        } //paintEmptyRows


        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component c = super.prepareRenderer(renderer, row, column);

            if (!isRowSelected(row)) {
                c.setBackground(row % 2 == 0 ? getBackground() : getColorBrighter(Color.CYAN));
            }

            return c;
        }

        @Override
        public void createLocale() {
            ResourceBundle rb = LocaleController.getResourceBundle();
            getColumnModel().getColumn(0).setHeaderValue(rb.getString("id"));
            getColumnModel().getColumn(1).setHeaderValue(rb.getString("date"));
            getColumnModel().getColumn(2).setHeaderValue(rb.getString("name"));
            getColumnModel().getColumn(3).setHeaderValue(rb.getString("xcoord"));
            getColumnModel().getColumn(4).setHeaderValue(rb.getString("ycoord"));
            getColumnModel().getColumn(5).setHeaderValue(rb.getString("price"));
            getColumnModel().getColumn(6).setHeaderValue(rb.getString("comment"));
            getColumnModel().getColumn(7).setHeaderValue(rb.getString("type"));
            getColumnModel().getColumn(8).setHeaderValue(rb.getString("haircolor"));
            getColumnModel().getColumn(9).setHeaderValue(rb.getString("country"));
            getColumnModel().getColumn(10).setHeaderValue(rb.getString("xloc"));
            getColumnModel().getColumn(11).setHeaderValue(rb.getString("yloc"));
            getColumnModel().getColumn(12).setHeaderValue(rb.getString("locname"));
            getColumnModel().getColumn(13).setHeaderValue(rb.getString("passportid"));
            getColumnModel().getColumn(14).setHeaderValue(rb.getString("user"));

            clearCollection();
            fillCollection();
           TableColumnModel columnModel =  this.getColumnModel();
               columnModel.getColumn(0).setPreferredWidth(40);
            columnModel.getColumn(1).setPreferredWidth(150);
            columnModel.getColumn(6).setPreferredWidth(150);
            columnModel.getColumn(7).setPreferredWidth(80);
            columnModel.getColumn(8).setPreferredWidth(100);
               this.revalidate();
        }
    }

    private void toDoWithCollection(TreeMap<Long, Ticket> tickets, String toDoWithCollection , JFrame jframe, String user) {

        switch (toDoWithCollection) {
            case "add":
                addToCollection(tickets, jframe, user);
                break;
            case "remove":
                removeFromCollection(tickets,jframe);
                break;
            case "update":
                updateTicket(tickets, jframe, user);
                break;
        }
    }

    private void addToCollection(TreeMap<Long, Ticket> tickets, JFrame jFrame, String user) {
        TicketCollection ticketCollection = new TicketCollection();
        tickets.entrySet().stream().forEach(x -> {
            ticketCollection.putTicket(x.getKey(), x.getValue());
            TicketImage ticketImage = addToPanel(x.getValue(), jFrame, user);
            jFrame.add(ticketImage);
            jFrame.repaint();
           // jFrame.repaint(ticketImage.getX()+150 - 1, ticketImage.getY()+18 - 1, ticketImage.getWidth() + 2, ticketImage.getHeight() + 2);
            addedTickets.put(x.getKey(), ticketImage);
        });

    }

    private void removeFromCollection(TreeMap<Long, Ticket> tickets,JFrame jFrame) {
        TicketCollection ticketCollection = new TicketCollection();

        tickets.entrySet().stream().forEach(x -> {
            TicketImage ticketImage = addedTickets.get(x.getKey());
            jFrame.remove(ticketImage);
            jFrame.repaint();
            jFrame.repaint(ticketImage.getX()+150 - 1, ticketImage.getY()+18 - 1, ticketImage.getWidth() + 2, ticketImage.getHeight() + 2);
            addedTickets.remove(x.getKey());
            ticketCollection.removeTicket(x.getKey());

        });
    }

    private void updateTicket(TreeMap<Long, Ticket> tickets, JFrame jFrame, String user) {
        TicketCollection ticketCollection = new TicketCollection();
        tickets.entrySet().stream().forEach(x -> {
            TicketImage ticketImage = addedTickets.get(x.getKey());
            TicketImage jticket = addToPanel(x.getValue(), jFrame, user);
            jFrame.remove(ticketImage);
            jFrame.add(jticket);
            jFrame.repaint();
//            jFrame.repaint(jticket.getX()+150 - 1, jticket.getY()+18 - 1, jticket.getWidth() + 2, jticket.getHeight() + 2);
//            jFrame.repaint(ticketImage.getX()+150 - 1, ticketImage.getY()+18 - 1, ticketImage.getWidth() + 2, ticketImage.getHeight() + 2);
            addedTickets.replace(x.getKey(), ticketImage, jticket);
            ticketCollection.replaceTicket(x.getKey(), x.getValue());
        });
    }

    private void setCellsAlignment(int alignment, JTable table) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);
        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    private double getX(double x) {

        if (x > 0) {
            return 465 + (x * 1.45);
        } else if (x < 0) {
            return 465 + (x * 1.58);
        } else if (x == 0) {
            return 465;
        }
        return 0;
    }

    private double getY(double y) {

        if (y > 0) {
            return 259 - y * 0.5;
        } else if (y < 0) {
            return 259 - y * 0.49;
        } else if (y == 0) {
            return 259;
        }
        return 0;
    }

    private TicketImage addToPanel(Ticket ticket, JFrame jFrame, String user) {
        return new TicketImage((int) getX(ticket.getCoordinates().getX())+230, (int) getY(ticket.getCoordinates().getY())+8, 40, 20, ticket.getColor(), ticket, jFrame, user);
    }


    public class TicketImage extends JLabel implements Localizeable  {
        int x, y, width, height;
        Color color;
        Ticket ticket;
        String pressForInfo;
        String removeupdate;
        JFrame jFrame;
        JDialog updateDialog = new JDialog(jFrame, removeupdate, true);
        String exit;
        SwingCommands.CustomButton closeButton2;

        {
            try {
                closeButton2 = new SwingCommands.CustomButton(520, 486, 180, 40, exit, 20, 0);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public TicketImage(int x, int y, int width, int height, Color color, Ticket ticket, JFrame jFrame, String user) {
            LocaleController.regist(this);

            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
            this.color = color;
            this.ticket = ticket;
            this.jFrame = jFrame;
            setSize(width, height);
            setLocation(x, y);
            setLayout(null);
            setOpaque(false);
            updateDialog.setContentPane(new SwingCommands.Background("images\\logreg.png"));
            updateDialog.setLayout(null);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize(); createLocale();
            updateDialog.setBounds(dimension.width / 2 - 400, dimension.height / 2 - 300, 800, 600);
            updateDialog.add(closeButton2);
            try {
                updateDialog.add(new CommandDialogs.TicketMenuPanel(ticket.getMapKey(), user));
            } catch (IOException e) {
                e.printStackTrace();
            }
            closeButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateDialog.setVisible(false);

                }
            });
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    updateDialog.setVisible(true);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }

        public void changeColor(int iy) {
try {
    SwingCommands.threadsForEverybody.execute(new Thread(() -> {
        final int i = iy;
        try {
            Image1 image1 = new Image1();
            Imageback imageback = new Imageback();
            add(imageback);
            repaint();
            if (i == 1) {
                Thread.sleep(800);
            }
            add(image1);
            remove(imageback);
            repaint();
            if (i == 1) {
                Thread.sleep((long) (1000 + Math.random() * 1000));
                remove(image1);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }));
}catch (RejectedExecutionException ex){
    Thread.currentThread().interrupt();
}
        }

        @Override
        public void createLocale() {
            ResourceBundle language = LocaleController.getResourceBundle();
            removeupdate = language.getString("removeupdate");
            exit= language.getString("exit");
            pressForInfo = language.getString("pressForInfo");
            updateDialog.setTitle(removeupdate);
            closeButton2.setText(exit);
            setToolTipText("Ticket#" + ticket.getMapKey() + pressForInfo);
        }


        public class Image1 extends JLabel {
            public Image1() {
                setSize(width, height);
                setLocation(0, 0);
                setVisible(true);

            }

            public void draw(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Shape rect = new Rectangle(0, 0, width, height);
                g2d.setColor(color);
                g2d.fill(rect);
                Color newcolor = getColorBrighter(color);
                BasicStroke bs = new BasicStroke((float) (width * 0.1));
                g2d.setStroke(bs);
                g2d.setColor(newcolor);
                g2d.drawRect(0, 0, width, height);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        }

        public class Imageback extends JLabel {
            public Imageback() {
                setSize((int) (width * 0.8), (int) (height * 0.8));
                setLocation(5, 5);
            }

            public void draw(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Shape rect = new Rectangle(0, 0, width, height);
                g2d.setColor(getColorDarker(color));
                g2d.fill(rect);
                Color newcolor = (getColorBrighter(getColorDarker(color)));
                BasicStroke bs = new BasicStroke((float) (width * 0.1));
                g2d.setStroke(bs);
                g2d.setColor(newcolor);
                g2d.drawRect(0, 0, width, height);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                draw(g);
            }
        }
    }

    public static void startAnime() throws RejectedExecutionException {
        addedTickets.entrySet().stream().forEach(x->x.getValue().changeColor(0));
        anime = (new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                if (ReceiverAndCanvasChanger.addedTickets.size() > 0) {
                    int size = addedTickets.size();
                    addedTickets.entrySet().stream().forEach(x -> {
                        x.getValue().changeColor(1);
                    });
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }));
        SwingCommands.threadsForEverybody.execute(anime);
    }


    public static Color getColorBrighter(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        float[] hsv = new float[3];
        Color.RGBtoHSB(r, g, b, hsv);
        hsv[1] = (float) (hsv[2] * 0.4);
        return new Color(Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]));
    }

    public static Color getColorDarker(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        float[] hsv = new float[3];
        Color.RGBtoHSB(r, g, b, hsv);
        // hsv[1] = (float) (hsv[1] * 1.2);
        hsv[2] = (float) (hsv[2] * 0.8);
        return new Color(Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]));
    }
}
