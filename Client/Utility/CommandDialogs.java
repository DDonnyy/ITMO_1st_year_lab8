package Utility;

import Common.Command;
import Common.Commands.*;
import Common.Ticket;
import Common.TicketCollection;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static Utility.SwingCommands.*;

public class CommandDialogs {

    private static ImageIcon CoordFieldPanel;
    private static ImageIcon TextFieldPanel;
    private static ImageIcon TextFieldPanelWrong;
    private static ImageIcon WrongCoordField;
    private static ImageIcon panelBack;
    private static String fillInLocale;
    private static String inserTtext;
    private static String nameTextLocale;
    private static String pricetext;
    private static String ticketTypetext;
    private static String kommentTextLocale;
    private static String CityTextLocale;
    private static String hairColorLocale;
    private static String nationalityLocale;
    private static String ticketCoordLocale;
    private static String ownerCoordLocal;
    private static String updateLocale;
    private static String ownerLocale;
    private static String ticketToDeletelocale;
    private static String priceToDeletLocale;
    private static String fillIdLocale;
    private static String fillNameLocale;
    private static String fillPriceLocale;
    private static String fillCommentLocale;
    private static String fillNameLocLocale;
    private static String xcoordLocale;
    private static String ycoordlocale;
    private static String xlocaLocale;
    private static String ylocLocale;
    private static String removeupdate;
    private static String wrongData;
    private static String reguiredField;
    private static String priceWrongLocale;
    private static String removeLocale;
    private static String noTicketFound;
    static {
        try {
            WrongCoordField = new ImageIcon(ImageIO.read(new File("images\\CoordPanelWrong.png")));
            TextFieldPanelWrong = new ImageIcon(ImageIO.read(new File("images\\panelwrong.png")));
            TextFieldPanel = new ImageIcon(ImageIO.read(new File("images\\panel2.png")));
            panelBack = new ImageIcon(ImageIO.read(new File("images\\panel.png")));
            CoordFieldPanel = new ImageIcon(ImageIO.read(new File("images\\CoordPanel.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class UpdatePanel extends JPanel implements Localizeable {
        SwingCommands.Text owner = new SwingCommands.Text(470, 112, 235, 40, ownerLocale);
        SwingCommands.Text idText = new SwingCommands.Text(81, 115, 60, 30, "ID");
        SwingCommands.Text nameText = new SwingCommands.Text(56, 168, 85, 40, nameTextLocale);
        SwingCommands.Text priceText = new SwingCommands.Text(65, 220, 50, 40, pricetext);
        SwingCommands.Text typeText = new SwingCommands.Text(56, 283, 85, 40, ticketTypetext);
        SwingCommands.Text kommentText = new SwingCommands.Text(54, 339, 85, 40, kommentTextLocale);
        SwingCommands.Text coordText1 = new SwingCommands.Text(150, 405, 150, 20, ticketCoordLocale);
        SwingCommands.Text coordText2 = new SwingCommands.Text(520, 405, 150, 20, ownerCoordLocal);
        SwingCommands.Text xCoordText1 = new SwingCommands.Text(76, 435, 36, 20, "X");
        SwingCommands.Text yCoordText1 = new SwingCommands.Text(347, 435, 36, 20, "Y");
        SwingCommands.Text locNameText = new SwingCommands.Text(427, 190, 90, 20, CityTextLocale);
        SwingCommands.Text hairText = new SwingCommands.Text(427, 256, 90, 15, hairColorLocale);
        SwingCommands.Text nationText = new SwingCommands.Text(427, 315, 80, 40, nationalityLocale);
        SwingCommands.Text xCoordText2 = new SwingCommands.Text(441, 435, 36, 20, "X");
        SwingCommands.Text yCoordText2 = new SwingCommands.Text(717, 435, 36, 20, "Y");
        SwingCommands.TextField id =       new SwingCommands.TextField(226, 36, 150, 115, fillIdLocale);
        SwingCommands.TextField name =    new SwingCommands.TextField(226, 36, 150, 171, fillNameLocale);
        SwingCommands.TextField price =    new SwingCommands.TextField(226, 36, 150, 226, fillPriceLocale);
        SwingCommands.TextField komment =   new SwingCommands.TextField(226, 36, 150, 340, fillCommentLocale);
        SwingCommands.TextField locname =   new SwingCommands.TextField(226, 36, 520, 186, fillNameLocLocale);
        SwingCommands.TextField xcoord =    new SwingCommands.TextField(100, 32, 110, 428, xcoordLocale);
        SwingCommands.TextField ycoord = new SwingCommands.TextField(100, 32, 225, 428, ycoordlocale);
        SwingCommands.TextField xloccoord = new SwingCommands.TextField(100, 32, 478, 428, xlocaLocale);
        SwingCommands.TextField yloccoord = new SwingCommands.TextField(100, 32, 595, 428, ylocLocale);
        JLabel title = new JLabel(fillInLocale);
        CustomButton sendButton = new CustomButton(134, 486, 180, 40, updateLocale, 20, 0);
        public UpdatePanel(Long idvalue, String user) throws IOException {
            LocaleController.regist(this);
            createLocale();
            setLayout(null);
            setOpaque(false);
            setBorder(null);
            setSize(800, 600);
            setLocation(0, 0);
            Font font = new Font("Century Gothic", Font.PLAIN, 20);
            ImageIcon textFieldPanel = new ImageIcon(getScaledImage(TextFieldPanel.getImage(), 335, 46));
            ImageIcon textFieldPanelWrong = new ImageIcon(getScaledImage(TextFieldPanelWrong.getImage(), 335, 46));
            JLabel idBack = new JLabel(textFieldPanel);
            idBack.setSize(335, 46);
            idBack.setLocation(51, 109);

            JLabel nameBack = new JLabel(textFieldPanel);
            nameBack.setSize(335, 46);
            nameBack.setLocation(51, 165);
            JLabel priceBack = new JLabel(textFieldPanel);
            priceBack.setSize(335, 46);
            priceBack.setLocation(51, 221);
            JLabel typeBack = new JLabel(textFieldPanel);
            typeBack.setSize(335, 46);
            typeBack.setLocation(51, 280);
            JLabel commentBack = new JLabel(textFieldPanel);
            commentBack.setSize(335, 46);
            commentBack.setLocation(51, 336);
            JLabel locNameBack = new JLabel(textFieldPanel);
            locNameBack.setSize(335, 46);
            locNameBack.setLocation(420, 179);
            JLabel hairBack = new JLabel(textFieldPanel);
            hairBack.setSize(335, 46);
            hairBack.setLocation(420, 245);
            JLabel nationBack = new JLabel(textFieldPanel);
            nationBack.setSize(335, 46);
            nationBack.setLocation(420, 315);

            JLabel coordsBack = new JLabel(CoordFieldPanel);
            coordsBack.setSize(336, 63);
            coordsBack.setLocation(51, 400);

            JLabel locCoordsBack = new JLabel(CoordFieldPanel);
            locCoordsBack.setSize(336, 63);
            locCoordsBack.setLocation(420, 400);

            JLabel panelTextBack = new JLabel(new ImageIcon(getScaledImage(panelBack.getImage(), 452, 38)));
            panelTextBack.setSize(452, 38);
            panelTextBack.setLocation(174, 36);

            JLabel panelTextBack2 = new JLabel(new ImageIcon(getScaledImage(panelBack.getImage(), 335, 52)));
            panelTextBack2.setSize(335, 52);
            panelTextBack2.setLocation(420, 109);


            JComboBox type = new JComboBox(new Object[]{Ticket.TicketType.USUAL, Ticket.TicketType.BUDGETARY, Ticket.TicketType.CHEAP, Ticket.TicketType.VIP});
            type.setBounds(150, 285, 226, 36);
            type.setUI(new BasicComboBoxUI());
            type.setFont(font);
            JComboBox hair = new JComboBox(new Object[]{Ticket.Color.BLACK, Ticket.Color.BLUE, Ticket.Color.DARKBROWN, Ticket.Color.GREEN});
            hair.setBounds(520, 249, 226, 36);
            hair.setUI(new BasicComboBoxUI());
            hair.setFont(font);
            JComboBox nationality = new JComboBox(new Object[]{Ticket.Country.RUSSIA, Ticket.Country.FRANCE, Ticket.Country.JAPAN, Ticket.Country.NORTH_KOREA, Ticket.Country.UNITED_KINGDOM});
            nationality.setBounds(520, 320, 226, 36);
            nationality.setUI(new BasicComboBoxUI());
            nationality.setFont(font);

            title.setSize(428, 38);
            title.setLocation(186, 36);
            title.setFont(font);
            MainWindow.setFontSize(title);

            add(nationality);
            add(hair);
            add(type);
            add(idText);
            add(nameText);
            add(priceText);
            add(typeText);
            add(kommentText);
            add(coordText1);
            add(coordText2);
            add(xCoordText1);
            add(xCoordText2);
            add(yCoordText1);
            add(yCoordText2);
            add(hairText);
            add(nationText);
            add(locNameText);
            add(price);
            add(komment);
            add(locname);
            add(xcoord);
            add(ycoord);
            add(xloccoord);
            add(yloccoord);
            add(name);
            add(id);
            add(title);
            add(owner);
            add(panelTextBack2);
            add(panelTextBack);
            add(sendButton);
            add(locCoordsBack);
            add(coordsBack);
            add(idBack);
            add(nameBack);
            add(priceBack);
            add(typeBack);
            add(commentBack);
            add(locNameBack);
            add(hairBack);
            add(nationBack);

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean okay = true;

                    String ticketName;
                    Float ticketPrice = -1F;
                    Object ticketType;
                    String ticketKomment;
                    int ticketX = 0;
                    int ticketY = 0;
                    String ownerloc;
                    Object ownerColor;
                    Object ownerCountry;
                    int ownerX = 0;
                    Double ownerY = 0D;
                    Long ticketId = 0L;
                    try {
                        ticketId = Long.parseLong(id.getText());
                        Long finalTicketId = ticketId;
                        if (new TicketCollection().getTickets().entrySet().stream().noneMatch(x ->
                                (x.getKey().equals(finalTicketId) && x.getValue().getUser().equals(user))
                        ))
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        id.setText(wrongData);
                        id.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(id)));
                        id.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            idBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                            }
                            idBack.setIcon(textFieldPanel);
                        }));
                    }
                    ticketName = name.getText();
                    if ((ticketName.equals("") || ticketName == null || ticketName.equals(fillNameLocale) || ticketName.equals((reguiredField)))) {
                        okay = false;
                        name.setText(reguiredField);
                        name.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(name)));
                        name.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            nameBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            nameBack.setIcon(textFieldPanel);
                        }));
                    }
                    try {
                        ticketPrice = Float.parseFloat(price.getText());
                        if (ticketPrice < 0 || ticketPrice == null) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        price.setText(priceWrongLocale);
                        price.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(price)));
                        price.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            priceBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            priceBack.setIcon(textFieldPanel);
                        }));
                    }
                    ticketType = type.getSelectedItem();
                    ownerColor = hair.getSelectedItem();
                    ownerCountry = nationality.getSelectedItem();

                    ticketKomment = komment.getText();
                    if ((ticketKomment.equals("") || ticketKomment == null || ticketKomment.equals(fillCommentLocale) || ticketKomment.equals(reguiredField))) {
                        okay = false;
                        komment.setText(reguiredField);
                        komment.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(komment)));
                        komment.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            commentBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            commentBack.setIcon(textFieldPanel);
                        }));
                    }

                    try {
                        ticketX = Integer.parseInt(xcoord.getText());
                        if (ticketX > 297 || ticketX < -297) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        xcoord.setText(xcoordLocale);
                        xcoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(xcoord)));
                        xcoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            coordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            coordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ticketY = Integer.parseInt(ycoord.getText());
                        if (ticketY > 500 || ticketY < -500) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        ycoord.setText(ycoordlocale);
                        ycoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(ycoord)));
                        ycoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            coordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            coordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ownerX = Integer.parseInt(xloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        xloccoord.setText(wrongData);
                        xloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(xloccoord)));
                        xloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ownerY = Double.parseDouble(yloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        yloccoord.setText(wrongData);
                        yloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(yloccoord)));
                        yloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    ownerloc = locname.getText();
                    if ((ownerloc.equals("") || ownerloc == null || ownerloc.equals(fillNameLocLocale) || ownerloc.equals(reguiredField))) {
                        okay = false;
                        locname.setText(reguiredField);
                        locname.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(locname)));
                        locname.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locNameBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locNameBack.setIcon(textFieldPanel);
                        }));
                    }
                    if (okay) {
                        Ticket ticket = new Ticket();
                        ticket.setMapKey(ticketId);
                        ticket.setCreationDate(new TicketCollection().getTicket(ticketId).getCreationDate());
                        ticket.setName(ticketName);
                        ticket.setPrice(ticketPrice);
                        ticket.setType(Ticket.TicketType.valueOf(ticketType.toString()));
                        ticket.setComment(ticketKomment);
                        Ticket.Coordinates coords = ticket.new Coordinates();
                        Ticket.Person person = ticket.new Person();
                        person.setPassportID(Long.parseLong(new TicketCollection().getTicket(ticketId).getPerson().getPassportID()));
                        Ticket.Location location = ticket.new Location();
                        coords.setX((long) ticketX);
                        coords.setY(ticketY);
                        person.setHairColor(Ticket.Color.valueOf(ownerColor.toString()));
                        person.setNationality(Ticket.Country.valueOf(ownerCountry.toString()));
                        location.setName(ownerloc);
                        location.setX(ownerX);
                        location.setY(ownerY);
                        person.setLocation(location);
                        ticket.setPerson(person);
                        ticket.setCoordinates(coords);
                        Map<Command, Object> commandStringMap = new HashMap<>();
                        commandStringMap.put(new UpdateBykey(), ticket);
                        try {
                            ClientSender.send(commandStringMap);
                        } catch (SocketException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }

        @Override
        public void createLocale() {
            ResourceBundle language = LocaleController.getResourceBundle();
            fillInLocale = language.getString("fillInLocale");
            nameTextLocale = language.getString("nameTextLocale");
            pricetext = language.getString("pricetext");
            ticketTypetext = language.getString("ticketTypetext");
            kommentTextLocale = language.getString("kommentTextLocale");
            CityTextLocale = language.getString("CityTextLocale");
            hairColorLocale = language.getString("hairColorLocale");
            nationalityLocale = language.getString("nationalityLocale");
            ticketCoordLocale = language.getString("ticketCoordLocale");
            ownerCoordLocal = language.getString("ownerCoordLocal");
            updateLocale = language.getString("updateLocale");
            ownerLocale = language.getString("ownerLocale");
            ticketToDeletelocale = language.getString("ticketToDeletelocale");
            priceToDeletLocale = language.getString("priceToDeleteLocale");
            fillIdLocale = language.getString("fillIdLocale");
            fillNameLocale = language.getString("fillNameLocale");
            fillPriceLocale = language.getString("fillPriceLocale");
            fillCommentLocale = language.getString("fillCommentLocale");
            fillNameLocLocale = language.getString("fillNameLocLocale");
            xcoordLocale = language.getString("xcoordLocale");
            ycoordlocale = language.getString("ycoordlocale");
            xlocaLocale = language.getString("xlocaLocale");
            ylocLocale = language.getString("ylocLocale");
            removeupdate = language.getString("removeupdate");
            wrongData = language.getString("wrongData");
            reguiredField = language.getString("reguiredField");
            priceWrongLocale = language.getString("priceWrongLocale");
            noTicketFound=language.getString("noTicketFound");
            inserTtext=language.getString("addlocale");
            owner.setText(ownerLocale);MainWindow.setFontSize(owner);
            idText.setText("ID");MainWindow.setFontSize(idText);
            nameText.setText(nameTextLocale);MainWindow.setFontSize(nameText);
            priceText.setText(pricetext);MainWindow.setFontSize(priceText);
            typeText.setText(ticketTypetext);MainWindow.setFontSize(typeText);
            kommentText.setText(kommentTextLocale);MainWindow.setFontSize(kommentText);
            coordText1.setText(ticketCoordLocale);MainWindow.setFontSize(coordText1);
            coordText2.setText(ownerCoordLocal);MainWindow.setFontSize(coordText2);
            xCoordText1.setText("X");
            yCoordText1.setText("Y");
            locNameText.setText(CityTextLocale);MainWindow.setFontSize(locNameText);
            hairText.setText(hairColorLocale);MainWindow.setFontSize(hairText);
            nationText.setText(nationalityLocale);MainWindow.setFontSize(nationText);
            xCoordText2.setText("X");
            yCoordText2.setText("Y");
            sendButton.setText(updateLocale);
            id .setText( fillIdLocale); id.setFont(new Font(id.getFont().getFontName(),id.getFont().getStyle(),SwingCommands.returnSize(id)));
            name  .setText(fillNameLocale); name.setFont(new Font(name.getFont().getFontName(),name.getFont().getStyle(),SwingCommands.returnSize(name)));
            price.setText(  fillPriceLocale); price.setFont(new Font(price.getFont().getFontName(),price.getFont().getStyle(),SwingCommands.returnSize(price)));
            komment .setText( fillCommentLocale); komment.setFont(new Font(komment.getFont().getFontName(),komment.getFont().getStyle(),SwingCommands.returnSize(komment)));
            locname .setText( fillNameLocLocale); locname.setFont(new Font(locname.getFont().getFontName(),locname.getFont().getStyle(),SwingCommands.returnSize(locname)));
            xcoord .setText( xcoordLocale); xcoord.setFont(new Font(xcoord.getFont().getFontName(),xcoord.getFont().getStyle(),SwingCommands.returnSize(xcoord)));
            ycoord .setText( ycoordlocale); ycoord.setFont(new Font(ycoord.getFont().getFontName(),ycoord.getFont().getStyle(),SwingCommands.returnSize(ycoord)));
            xloccoord.setText(  xlocaLocale); xloccoord.setFont(new Font(xloccoord.getFont().getFontName(),xloccoord.getFont().getStyle(),SwingCommands.returnSize(xloccoord)));
            yloccoord.setText(ylocLocale); yloccoord.setFont(new Font(yloccoord.getFont().getFontName(),yloccoord.getFont().getStyle(),SwingCommands.returnSize(yloccoord)));
            title.setText(fillInLocale);
            MainWindow.setFontSize(title);
        }
    }

    public static class InsertPanel extends JPanel implements Localizeable {
        SwingCommands.Text owner = new SwingCommands.Text(470, 112, 235, 40, ownerLocale);
        SwingCommands.Text idText = new SwingCommands.Text(81, 115, 60, 30, "ID");
        SwingCommands.Text nameText = new SwingCommands.Text(56, 168, 85, 40, nameTextLocale);
        SwingCommands.Text priceText = new SwingCommands.Text(65, 220, 50, 40, pricetext);
        SwingCommands.Text typeText = new SwingCommands.Text(56, 283, 85, 40, ticketTypetext);
        SwingCommands.Text kommentText = new SwingCommands.Text(54, 339, 85, 40, kommentTextLocale);
        SwingCommands.Text coordText1 = new SwingCommands.Text(150, 405, 150, 20, ticketCoordLocale);
        SwingCommands.Text coordText2 = new SwingCommands.Text(520, 405, 150, 20, ownerCoordLocal);
        SwingCommands.Text xCoordText1 = new SwingCommands.Text(76, 435, 36, 20, "X");
        SwingCommands.Text yCoordText1 = new SwingCommands.Text(347, 435, 36, 20, "Y");
        SwingCommands.Text locNameText = new SwingCommands.Text(427, 190, 90, 20, CityTextLocale);
        SwingCommands.Text hairText = new SwingCommands.Text(427, 256, 90, 15, hairColorLocale);
        SwingCommands.Text nationText = new SwingCommands.Text(427, 315, 80, 40, nationalityLocale);
        SwingCommands.Text xCoordText2 = new SwingCommands.Text(441, 435, 36, 20, "X");
        SwingCommands.Text yCoordText2 = new SwingCommands.Text(717, 435, 36, 20, "Y");
        SwingCommands.TextField id =       new SwingCommands.TextField(226, 36, 150, 115, fillIdLocale);
        SwingCommands.TextField name =    new SwingCommands.TextField(226, 36, 150, 171, fillNameLocale);
        SwingCommands.TextField price =    new SwingCommands.TextField(226, 36, 150, 226, fillPriceLocale);
        SwingCommands.TextField komment =   new SwingCommands.TextField(226, 36, 150, 340, fillCommentLocale);
        SwingCommands.TextField locname =   new SwingCommands.TextField(226, 36, 520, 186, fillNameLocLocale);
        SwingCommands.TextField xcoord =    new SwingCommands.TextField(100, 32, 110, 428, xcoordLocale);
        SwingCommands.TextField ycoord = new SwingCommands.TextField(100, 32, 225, 428, ycoordlocale);
        SwingCommands.TextField xloccoord = new SwingCommands.TextField(100, 32, 478, 428, xlocaLocale);
        SwingCommands.TextField yloccoord = new SwingCommands.TextField(100, 32, 595, 428, ylocLocale);
        CustomButton sendButton = new CustomButton(134, 486, 180, 40, inserTtext, 20, 0);
        JLabel title = new JLabel(fillInLocale);
        public InsertPanel() throws IOException {
            LocaleController.regist(this);
            createLocale();
            setLayout(null);
            setOpaque(false);
            setBorder(null);
            setSize(800, 600);
            setLocation(0, 0);
            Font font = new Font("Century Gothic", Font.PLAIN, 20);

            ImageIcon textFieldPanel = new ImageIcon(getScaledImage(TextFieldPanel.getImage(), 335, 46));
            ImageIcon textFieldPanelWrong = new ImageIcon(getScaledImage(TextFieldPanelWrong.getImage(), 335, 46));
            JLabel idBack = new JLabel(textFieldPanel);
            idBack.setSize(335, 46);
            idBack.setLocation(51, 109);

            JLabel nameBack = new JLabel(textFieldPanel);
            nameBack.setSize(335, 46);
            nameBack.setLocation(51, 165);
            JLabel priceBack = new JLabel(textFieldPanel);
            priceBack.setSize(335, 46);
            priceBack.setLocation(51, 221);
            JLabel typeBack = new JLabel(textFieldPanel);
            typeBack.setSize(335, 46);
            typeBack.setLocation(51, 280);
            JLabel commentBack = new JLabel(textFieldPanel);
            commentBack.setSize(335, 46);
            commentBack.setLocation(51, 336);
            JLabel locNameBack = new JLabel(textFieldPanel);
            locNameBack.setSize(335, 46);
            locNameBack.setLocation(420, 179);
            JLabel hairBack = new JLabel(textFieldPanel);
            hairBack.setSize(335, 46);
            hairBack.setLocation(420, 245);
            JLabel nationBack = new JLabel(textFieldPanel);
            nationBack.setSize(335, 46);
            nationBack.setLocation(420, 315);

            JLabel coordsBack = new JLabel(CoordFieldPanel);
            coordsBack.setSize(336, 63);
            coordsBack.setLocation(51, 400);

            JLabel locCoordsBack = new JLabel(CoordFieldPanel);
            locCoordsBack.setSize(336, 63);
            locCoordsBack.setLocation(420, 400);

            JLabel panelTextBack = new JLabel(new ImageIcon(getScaledImage(panelBack.getImage(), 452, 38)));
            panelTextBack.setSize(452, 38);
            panelTextBack.setLocation(174, 36);

            JLabel panelTextBack2 = new JLabel(new ImageIcon(getScaledImage(panelBack.getImage(), 335, 52)));
            panelTextBack2.setSize(335, 52);
            panelTextBack2.setLocation(420, 109);



            JComboBox type = new JComboBox(new Object[]{Ticket.TicketType.USUAL, Ticket.TicketType.BUDGETARY, Ticket.TicketType.CHEAP, Ticket.TicketType.VIP});
            type.setBounds(150, 285, 226, 36);
            type.setUI(new BasicComboBoxUI());
            type.setFont(font);
            JComboBox hair = new JComboBox(new Object[]{Ticket.Color.BLACK, Ticket.Color.BLUE, Ticket.Color.DARKBROWN, Ticket.Color.GREEN});
            hair.setBounds(520, 249, 226, 36);
            hair.setUI(new BasicComboBoxUI());
            hair.setFont(font);
            JComboBox nationality = new JComboBox(new Object[]{Ticket.Country.RUSSIA, Ticket.Country.FRANCE, Ticket.Country.JAPAN, Ticket.Country.NORTH_KOREA, Ticket.Country.UNITED_KINGDOM});
            nationality.setBounds(520, 320, 226, 36);
            nationality.setUI(new BasicComboBoxUI());
            nationality.setFont(font);





            title.setSize(428, 38);
            title.setLocation(186, 36);
            title.setFont(font);
            MainWindow.setFontSize(title);

            add(nationality);
            add(hair);
            add(type);
            add(idText);
            add(nameText);
            add(priceText);
            add(typeText);
            add(kommentText);
            add(coordText1);
            add(coordText2);
            add(xCoordText1);
            add(xCoordText2);
            add(yCoordText1);
            add(yCoordText2);
            add(hairText);
            add(nationText);
            add(locNameText);
            add(price);
            add(komment);
            add(locname);
            add(xcoord);
            add(ycoord);
            add(xloccoord);
            add(yloccoord);
            add(name);
            add(id);
            add(title);
            add(owner);
            add(panelTextBack2);
            add(panelTextBack);
            add(sendButton);
            add(locCoordsBack);
            add(coordsBack);
            add(idBack);
            add(nameBack);
            add(priceBack);
            add(typeBack);
            add(commentBack);
            add(locNameBack);
            add(hairBack);
            add(nationBack);

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean okay = true;
                    Long ticketId = 0L;
                    String ticketName;
                    Float ticketPrice = -1F;
                    Object ticketType;
                    String ticketKomment;
                    int ticketX = 0;
                    int ticketY = 0;
                    String ownerloc;
                    Object ownerColor;
                    Object ownerCountry;
                    int ownerX = 0;
                    Double ownerY = 0D;
                    try {
                        ticketId = Long.parseLong(id.getText());
                        Long finalTicketId = ticketId;
                        if (new TicketCollection().getTickets().entrySet().stream().anyMatch(x -> x.getKey().equals(finalTicketId)))
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        id.setText(wrongData);
                        id.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(id)));
                        id.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            idBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                            }
                            idBack.setIcon(textFieldPanel);
                        }));
                    }
                    ticketName = name.getText();
                    if ((ticketName.equals("") || ticketName == null || ticketName.equals(fillNameLocale) || ticketName.equals((reguiredField)))) {
                        okay = false;
                        name.setText(reguiredField);
                        name.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(name)));
                        name.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            nameBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            nameBack.setIcon(textFieldPanel);
                        }));
                    }
                    try {
                        ticketPrice = Float.parseFloat(price.getText());
                        if (ticketPrice < 0 || ticketPrice == null) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        price.setText(priceWrongLocale);
                        price.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(price)));
                        price.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            priceBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            priceBack.setIcon(textFieldPanel);
                        }));
                    }
                    ticketType = type.getSelectedItem();
                    ownerColor = hair.getSelectedItem();
                    ownerCountry = nationality.getSelectedItem();

                    ticketKomment = komment.getText();
                    if ((ticketKomment.equals("") || ticketKomment == null || ticketKomment.equals(fillCommentLocale) || ticketKomment.equals(reguiredField))) {
                        okay = false;
                        komment.setText(reguiredField);
                        komment.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(komment)));
                        komment.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            commentBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            commentBack.setIcon(textFieldPanel);
                        }));
                    }

                    try {
                        ticketX = Integer.parseInt(xcoord.getText());
                        if (ticketX > 297 || ticketX < -297) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        xcoord.setText(xcoordLocale);
                        xcoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(xcoord)));
                        xcoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            coordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            coordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ticketY = Integer.parseInt(ycoord.getText());
                        if (ticketY > 500 || ticketY < -500) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        ycoord.setText(ycoordlocale);
                        ycoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(ycoord)));
                        ycoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            coordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            coordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ownerX = Integer.parseInt(xloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        xloccoord.setText(reguiredField);
                        xloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(xloccoord)));
                        xloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ownerY = Double.parseDouble(yloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        yloccoord.setText(wrongData);
                        yloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(yloccoord)));
                        yloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    ownerloc = locname.getText();
                    if ((ownerloc.equals("") || ownerloc == null || ownerloc.equals(fillNameLocLocale) || ownerloc.equals(reguiredField))) {
                        okay = false;
                        locname.setText(reguiredField);
                        locname.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(locname)));
                        locname.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locNameBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locNameBack.setIcon(textFieldPanel);
                        }));
                    }
                    if (okay) {
                        Ticket ticket = new Ticket();
                        ticket.setMapKey(ticketId);
                        ticket.setName(ticketName);
                        ticket.setPrice(ticketPrice);
                        ticket.setType(Ticket.TicketType.valueOf(ticketType.toString()));
                        ticket.setComment(ticketKomment);
                        Ticket.Coordinates coords = ticket.new Coordinates();
                        Ticket.Person person = ticket.new Person();
                        Ticket.Location location = ticket.new Location();
                        coords.setX((long) ticketX);
                        coords.setY(ticketY);
                        person.setHairColor(Ticket.Color.valueOf(ownerColor.toString()));
                        person.setNationality(Ticket.Country.valueOf(ownerCountry.toString()));
                        location.setName(ownerloc);
                        location.setX(ownerX);
                        location.setY(ownerY);
                        person.setLocation(location);
                        ticket.setPerson(person);
                        ticket.setCoordinates(coords);
                        Map<Command, Object> commandStringMap = new HashMap<>();
                        commandStringMap.put(new InsertKey(), ticket);
                        try {
                            ClientSender.send(commandStringMap);
                        } catch (SocketException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

        }

        @Override
        public void createLocale() {
            ResourceBundle language = LocaleController.getResourceBundle();
            fillInLocale = language.getString("fillInLocale");
            nameTextLocale = language.getString("nameTextLocale");
            pricetext = language.getString("pricetext");
            ticketTypetext = language.getString("ticketTypetext");
            kommentTextLocale = language.getString("kommentTextLocale");
            CityTextLocale = language.getString("CityTextLocale");
            hairColorLocale = language.getString("hairColorLocale");
            nationalityLocale = language.getString("nationalityLocale");
            ticketCoordLocale = language.getString("ticketCoordLocale");
            ownerCoordLocal = language.getString("ownerCoordLocal");
            updateLocale = language.getString("updateLocale");
            ownerLocale = language.getString("ownerLocale");
            ticketToDeletelocale = language.getString("ticketToDeletelocale");
            priceToDeletLocale = language.getString("priceToDeleteLocale");
            fillIdLocale = language.getString("fillIdLocale");
            fillNameLocale = language.getString("fillNameLocale");
            fillPriceLocale = language.getString("fillPriceLocale");
            fillCommentLocale = language.getString("fillCommentLocale");
            fillNameLocLocale = language.getString("fillNameLocLocale");
            xcoordLocale = language.getString("xcoordLocale");
            ycoordlocale = language.getString("ycoordlocale");
            xlocaLocale = language.getString("xlocaLocale");
            ylocLocale = language.getString("ylocLocale");
            removeupdate = language.getString("removeupdate");
            wrongData = language.getString("wrongData");
            reguiredField = language.getString("reguiredField");
            priceWrongLocale = language.getString("priceWrongLocale");
            noTicketFound=language.getString("noTicketFound");
            inserTtext=language.getString("addlocale");
            owner.setText(ownerLocale);MainWindow.setFontSize(owner);
            idText.setText("ID");MainWindow.setFontSize(idText);
            nameText.setText(nameTextLocale);MainWindow.setFontSize(nameText);
            priceText.setText(pricetext);MainWindow.setFontSize(priceText);
            typeText.setText(ticketTypetext);MainWindow.setFontSize(typeText);
            kommentText.setText(kommentTextLocale);MainWindow.setFontSize(kommentText);
            coordText1.setText(ticketCoordLocale);MainWindow.setFontSize(coordText1);
            coordText2.setText(ownerCoordLocal);MainWindow.setFontSize(coordText2);
            xCoordText1.setText("X");
            yCoordText1.setText("Y");
            locNameText.setText(CityTextLocale);MainWindow.setFontSize(locNameText);
            hairText.setText(hairColorLocale);MainWindow.setFontSize(hairText);
            nationText.setText(nationalityLocale);MainWindow.setFontSize(nationText);
            xCoordText2.setText("X");
            yCoordText2.setText("Y");
            sendButton.setText(inserTtext);
            id .setText( fillIdLocale); id.setFont(new Font(id.getFont().getFontName(),id.getFont().getStyle(),SwingCommands.returnSize(id)));
            name  .setText(fillNameLocale); name.setFont(new Font(name.getFont().getFontName(),name.getFont().getStyle(),SwingCommands.returnSize(name)));
            price.setText(  fillPriceLocale); price.setFont(new Font(price.getFont().getFontName(),price.getFont().getStyle(),SwingCommands.returnSize(price)));
            komment .setText( fillCommentLocale); komment.setFont(new Font(komment.getFont().getFontName(),komment.getFont().getStyle(),SwingCommands.returnSize(komment)));
            locname .setText( fillNameLocLocale); locname.setFont(new Font(locname.getFont().getFontName(),locname.getFont().getStyle(),SwingCommands.returnSize(locname)));
            xcoord .setText( xcoordLocale); xcoord.setFont(new Font(xcoord.getFont().getFontName(),xcoord.getFont().getStyle(),SwingCommands.returnSize(xcoord)));
            ycoord .setText( ycoordlocale); ycoord.setFont(new Font(ycoord.getFont().getFontName(),ycoord.getFont().getStyle(),SwingCommands.returnSize(ycoord)));
            xloccoord.setText(  xlocaLocale); xloccoord.setFont(new Font(xloccoord.getFont().getFontName(),xloccoord.getFont().getStyle(),SwingCommands.returnSize(xloccoord)));
            yloccoord.setText(ylocLocale); yloccoord.setFont(new Font(yloccoord.getFont().getFontName(),yloccoord.getFont().getStyle(),SwingCommands.returnSize(yloccoord)));
            title.setText(fillInLocale);
            MainWindow.setFontSize(title);
        }
    }


    public static class RemoveByPersonTable extends JTable implements Localizeable {
        SwingCommands.Text owner = new SwingCommands.Text(60, 112, 235, 40, ownerLocale);
        SwingCommands.Text coordText2 = new SwingCommands.Text(70, 405, 150, 20, ownerCoordLocal);
        SwingCommands.Text locNameText = new SwingCommands.Text(35, 190, 90, 20, CityTextLocale);
        SwingCommands.Text hairText = new SwingCommands.Text(30, 256, 90, 15, hairColorLocale);
        SwingCommands.Text nationText = new SwingCommands.Text(30, 315, 85, 40, nationalityLocale);
        SwingCommands.Text xCoordText2 = new SwingCommands.Text(45, 435, 36, 20, "X");
        SwingCommands.Text yCoordText2 = new SwingCommands.Text(330, 435, 36, 20, "Y");
        SwingCommands.TextField locname = new SwingCommands.TextField(226, 36, 130, 186, fillNameLocLocale);
        SwingCommands.TextField xloccoord = new SwingCommands.TextField(100, 32, 85, 428, xlocaLocale);
        SwingCommands.TextField yloccoord = new SwingCommands.TextField(100, 32, 205, 428, ylocLocale);
        JLabel title = new JLabel(fillInLocale);
        CustomButton sendButton = new CustomButton(10, 486, 180, 40, removeLocale, 20, 0);

        public RemoveByPersonTable() throws IOException {
            LocaleController.regist(this);
            createLocale();
            setLayout(null);
            setOpaque(false);
            setBorder(null);
            setSize(400, 600);
            setLocation(0, 0);
            Font font = new Font("Century Gothic", Font.PLAIN, 20);

            ImageIcon textFieldPanel = new ImageIcon(getScaledImage(TextFieldPanel.getImage(), 335, 46));
            ImageIcon textFieldPanelWrong = new ImageIcon(getScaledImage(TextFieldPanelWrong.getImage(), 335, 46));

            SwingCommands.Text idText = new SwingCommands.Text(81, 115, 60, 30, "ID");

            JLabel locNameBack = new JLabel(textFieldPanel);
            locNameBack.setSize(335, 46);
            locNameBack.setLocation(25, 179);
            JLabel hairBack = new JLabel(textFieldPanel);
            hairBack.setSize(335, 46);
            hairBack.setLocation(25, 245);
            JLabel nationBack = new JLabel(textFieldPanel);
            nationBack.setSize(335, 46);
            nationBack.setLocation(25, 315);

            JLabel locCoordsBack = new JLabel(CoordFieldPanel);
            locCoordsBack.setSize(336, 63);
            locCoordsBack.setLocation(25, 400);

            JLabel panelTextBack2 = new JLabel(new ImageIcon(getScaledImage(panelBack.getImage(), 335, 52)));
            panelTextBack2.setSize(335, 52);
            panelTextBack2.setLocation(25, 109);





            JComboBox hair = new JComboBox(new Object[]{Ticket.Color.BLACK, Ticket.Color.BLUE, Ticket.Color.DARKBROWN, Ticket.Color.GREEN});
            hair.setBounds(130, 249, 226, 36);
            hair.setUI(new BasicComboBoxUI());
            hair.setFont(font);
            JComboBox nationality = new JComboBox(new Object[]{Ticket.Country.RUSSIA, Ticket.Country.FRANCE, Ticket.Country.JAPAN, Ticket.Country.NORTH_KOREA, Ticket.Country.UNITED_KINGDOM});
            nationality.setBounds(130, 320, 226, 36);
            nationality.setUI(new BasicComboBoxUI());
            nationality.setFont(font);





            title.setSize(350, 38);
            title.setLocation(20, 36);
            title.setFont(font);
            MainWindow.setFontSize(title);

            add(nationality);
            add(hair);
            add(coordText2);
            add(xCoordText2);
            add(yCoordText2);
            add(hairText);
            add(nationText);
            add(locNameText);
            add(locname);
            add(xloccoord);
            add(yloccoord);
            add(title);
            add(owner);
            add(panelTextBack2);
            add(sendButton);
            add(locCoordsBack);
            add(locNameBack);
            add(hairBack);
            add(nationBack);

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean okay = true;
                    String ownerloc;
                    Object ownerColor;
                    Object ownerCountry;
                    int ownerX = 0;
                    Double ownerY = 0D;
                    ownerColor = hair.getSelectedItem();
                    ownerCountry = nationality.getSelectedItem();
                    try {
                        ownerX = Integer.parseInt(xloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        xloccoord.setText(wrongData);
                        xloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(xloccoord)));
                        xloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ownerY = Double.parseDouble(yloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        yloccoord.setText(wrongData);
                        yloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(yloccoord)));
                        yloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    ownerloc = locname.getText();
                    if ((ownerloc.equals("") || ownerloc == null || ownerloc.equals(fillNameLocLocale) || ownerloc.equals(wrongData))) {
                        okay = false;
                        locname.setText(wrongData);
                        locname.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(locname)));
                        locname.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locNameBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locNameBack.setIcon(textFieldPanel);
                        }));
                    }
                    if (okay) {
                        Ticket ticket = new Ticket();
                        Ticket.Person person = ticket.new Person();
                        Ticket.Location location = ticket.new Location();
                        person.setHairColor(Ticket.Color.valueOf(ownerColor.toString()));
                        person.setNationality(Ticket.Country.valueOf(ownerCountry.toString()));
                        location.setName(ownerloc);
                        location.setX(ownerX);
                        location.setY(ownerY);
                        person.setLocation(location);
                        ticket.setPerson(person);
                        Map<Command, Object> commandStringMap = new HashMap<>();
                        commandStringMap.put(new RemoveAllByPerson(), ticket);
                        try {
                            ClientSender.send(commandStringMap);
                        } catch (SocketException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }

        @Override
        public void createLocale() {
            ResourceBundle language = LocaleController.getResourceBundle();
            removeLocale = language.getString("removeLocale");
            fillInLocale = language.getString("fillInLocale");
            nameTextLocale = language.getString("nameTextLocale");
            pricetext = language.getString("pricetext");
            ticketTypetext = language.getString("ticketTypetext");
            kommentTextLocale = language.getString("kommentTextLocale");
            CityTextLocale = language.getString("CityTextLocale");
            hairColorLocale = language.getString("hairColorLocale");
            nationalityLocale = language.getString("nationalityLocale");
            ticketCoordLocale = language.getString("ticketCoordLocale");
            ownerCoordLocal = language.getString("ownerCoordLocal");
            updateLocale = language.getString("updateLocale");
            ownerLocale = language.getString("ownerLocale");
            ticketToDeletelocale = language.getString("ticketToDeletelocale");
            priceToDeletLocale = language.getString("priceToDeleteLocale");
            fillIdLocale = language.getString("fillIdLocale");
            fillNameLocale = language.getString("fillNameLocale");
            fillPriceLocale = language.getString("fillPriceLocale");
            fillCommentLocale = language.getString("fillCommentLocale");
            fillNameLocLocale = language.getString("fillNameLocLocale");
            xcoordLocale = language.getString("xcoordLocale");
            ycoordlocale = language.getString("ycoordlocale");
            xlocaLocale = language.getString("xlocaLocale");
            ylocLocale = language.getString("ylocLocale");
            removeupdate = language.getString("removeupdate");
            wrongData = language.getString("wrongData");
            reguiredField = language.getString("reguiredField");
            priceWrongLocale = language.getString("priceWrongLocale");
            noTicketFound=language.getString("noTicketFound");
            owner.setText(ownerLocale);MainWindow.setFontSize(owner);
            coordText2.setText(ownerCoordLocal);MainWindow.setFontSize(coordText2);
            locNameText.setText(CityTextLocale);MainWindow.setFontSize(locNameText);
            hairText.setText(hairColorLocale);MainWindow.setFontSize(hairText);
            nationText.setText(nationalityLocale);MainWindow.setFontSize(nationText);
            xCoordText2.setText("X");
            yCoordText2.setText("Y");

            locname .setText( fillNameLocLocale); locname.setFont(new Font(locname.getFont().getFontName(),locname.getFont().getStyle(),SwingCommands.returnSize(locname)));
            xloccoord.setText(  xlocaLocale); xloccoord.setFont(new Font(xloccoord.getFont().getFontName(),xloccoord.getFont().getStyle(),SwingCommands.returnSize(xloccoord)));
            yloccoord.setText(ylocLocale); yloccoord.setFont(new Font(yloccoord.getFont().getFontName(),yloccoord.getFont().getStyle(),SwingCommands.returnSize(yloccoord)));
            title.setText(fillInLocale);
            sendButton.setText(removeLocale);
        }
    }


    public static class RemovePanel extends JPanel implements Localizeable {
        CustomButton sendButton = new CustomButton(10, 110, 180, 40, removeLocale, 20, 0);
        SwingCommands.TextField id = new SwingCommands.TextField(226, 36, 125, 60, fillIdLocale);
        SwingCommands.Text idText = new SwingCommands.Text(50, 65, 60, 30, "ID");
        JLabel title = new JLabel(fillInLocale);
        public RemovePanel(String user) throws IOException {
            LocaleController.regist(this);
            createLocale();
            setLayout(null);
            setOpaque(false);
            setBorder(null);
            setSize(400, 200);
            setLocation(0, 0);
            Font font = new Font("Century Gothic", Font.PLAIN, 20);

            ImageIcon textFieldPanel = new ImageIcon(getScaledImage(TextFieldPanel.getImage(), 335, 46));
            ImageIcon textFieldPanelWrong = new ImageIcon(getScaledImage(TextFieldPanelWrong.getImage(), 335, 46));

            JLabel idBack = new JLabel(textFieldPanel);
            idBack.setSize(335, 46);
            idBack.setLocation(25, 55);


            title.setSize(350, 38);
            title.setLocation(15, 10);
            title.setFont(font);
            MainWindow.setFontSize(title);
            add(idText);
            add(idBack);
            add(id);
            add(title);
            add(sendButton);


            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean okay = true;
                    Long ticketId = 0L;
                    try {
                        ticketId = Long.parseLong(id.getText());
                        Long finalTicketId = ticketId;
                        if (new TicketCollection().getTickets().entrySet().stream().noneMatch(x ->
                                (x.getKey().equals(finalTicketId) && x.getValue().getUser().equals(user))
                        ))
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        id.setText(noTicketFound);
                        id.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(id)));
                        id.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            idBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                            }
                            idBack.setIcon(textFieldPanel);
                        }));
                    }

                    if (okay) {
                        Map<Command, Object> commandStringMap = new HashMap<>();
                        commandStringMap.put(new RemoveByKey(), ticketId);
                        try {
                            ClientSender.send(commandStringMap);
                        } catch (SocketException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            });

        }

        @Override
        public void createLocale() {
            ResourceBundle language = LocaleController.getResourceBundle();
            removeLocale = language.getString("removeLocale");
            fillInLocale = language.getString("fillInLocale");
            nameTextLocale = language.getString("nameTextLocale");
            pricetext = language.getString("pricetext");
            ticketTypetext = language.getString("ticketTypetext");
            kommentTextLocale = language.getString("kommentTextLocale");
            CityTextLocale = language.getString("CityTextLocale");
            hairColorLocale = language.getString("hairColorLocale");
            nationalityLocale = language.getString("nationalityLocale");
            ticketCoordLocale = language.getString("ticketCoordLocale");
            ownerCoordLocal = language.getString("ownerCoordLocal");
            updateLocale = language.getString("updateLocale");
            ownerLocale = language.getString("ownerLocale");
            ticketToDeletelocale = language.getString("ticketToDeletelocale");
            priceToDeletLocale = language.getString("priceToDeleteLocale");
            fillIdLocale = language.getString("fillIdLocale");
            fillNameLocale = language.getString("fillNameLocale");
            fillPriceLocale = language.getString("fillPriceLocale");
            fillCommentLocale = language.getString("fillCommentLocale");
            fillNameLocLocale = language.getString("fillNameLocLocale");
            xcoordLocale = language.getString("xcoordLocale");
            ycoordlocale = language.getString("ycoordlocale");
            xlocaLocale = language.getString("xlocaLocale");
            ylocLocale = language.getString("ylocLocale");
            removeupdate = language.getString("removeupdate");
            wrongData = language.getString("wrongData");
            reguiredField = language.getString("reguiredField");
            priceWrongLocale = language.getString("priceWrongLocale");
            noTicketFound=language.getString("noTicketFound");
            sendButton.setText(removeLocale);
id.setText(fillIdLocale);
title.setText(fillInLocale);

        }
    }

    public static class RemoveLowerPanel extends JPanel implements Localizeable {
        CustomButton sendButton = new CustomButton(10, 110, 180, 40, removeLocale, 20, 0);
        SwingCommands.TextField price = new SwingCommands.TextField(226, 36, 125, 60, fillPriceLocale);
        SwingCommands.Text idText = new SwingCommands.Text(45, 65, 60, 30, pricetext);
        JLabel title = new JLabel(priceToDeletLocale);
        public RemoveLowerPanel(String user) throws IOException {
            LocaleController.regist(this);
            createLocale();
            setLayout(null);
            setOpaque(false);
            setBorder(null);
            setSize(400, 200);
            setLocation(0, 0);
            Font font = new Font("Century Gothic", Font.PLAIN, 20);

            ImageIcon textFieldPanel = new ImageIcon(getScaledImage(TextFieldPanel.getImage(), 335, 46));
            ImageIcon textFieldPanelWrong = new ImageIcon(getScaledImage(TextFieldPanelWrong.getImage(), 335, 46));

            JLabel priceBack = new JLabel(textFieldPanel);
            priceBack.setSize(335, 46);
            priceBack.setLocation(25, 55);

            title.setSize(350, 38);
            title.setLocation(15, 10);
            title.setFont(font);
            MainWindow.setFontSize(title);
            add(idText);
            add(priceBack);
            add(price);
            add(title);
            add(sendButton);


            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean okay = true;
                    Float ticketPrice = 0F;
                    try {
                        ticketPrice = Float.parseFloat(price.getText());
                        if (ticketPrice < 0 || ticketPrice == null) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        price.setText(priceWrongLocale);
                        price.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(price)));
                        price.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            priceBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            priceBack.setIcon(textFieldPanel);
                        }));
                    }

                    if (okay) {
                        Map<Command, Object> commandStringMap = new HashMap<>();
                        commandStringMap.put(new Remove_lower(), ticketPrice);
                        try {
                            ClientSender.send(commandStringMap);
                        } catch (SocketException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            });
        }

        @Override
        public void createLocale() {
            ResourceBundle language = LocaleController.getResourceBundle();
            fillInLocale = language.getString("fillInLocale");
            nameTextLocale = language.getString("nameTextLocale");
            pricetext = language.getString("pricetext");
            ticketTypetext = language.getString("ticketTypetext");
            kommentTextLocale = language.getString("kommentTextLocale");
            CityTextLocale = language.getString("CityTextLocale");
            hairColorLocale = language.getString("hairColorLocale");
            nationalityLocale = language.getString("nationalityLocale");
            ticketCoordLocale = language.getString("ticketCoordLocale");
            ownerCoordLocal = language.getString("ownerCoordLocal");
            updateLocale = language.getString("updateLocale");
            ownerLocale = language.getString("ownerLocale");
            ticketToDeletelocale = language.getString("ticketToDeletelocale");
            priceToDeletLocale = language.getString("priceToDeleteLocale");
            fillIdLocale = language.getString("fillIdLocale");
            fillNameLocale = language.getString("fillNameLocale");
            fillPriceLocale = language.getString("fillPriceLocale");
            fillCommentLocale = language.getString("fillCommentLocale");
            fillNameLocLocale = language.getString("fillNameLocLocale");
            xcoordLocale = language.getString("xcoordLocale");
            ycoordlocale = language.getString("ycoordlocale");
            xlocaLocale = language.getString("xlocaLocale");
            ylocLocale = language.getString("ylocLocale");
            removeupdate = language.getString("removeupdate");
            wrongData = language.getString("wrongData");
            reguiredField = language.getString("reguiredField");
            priceWrongLocale = language.getString("priceWrongLocale");
            noTicketFound=language.getString("noTicketFound");
            idText.setText(pricetext);MainWindow.setFontSize(idText);
            sendButton.setText(removeLocale);
            price.setText( fillPriceLocale); price.setFont(new Font(price.getFont().getFontName(),price.getFont().getStyle(),SwingCommands.returnSize(price)));
           title.setText(priceToDeletLocale);

        }
    }


    public static class ReplaceIfGreaterPanel extends JPanel implements Localizeable {
        SwingCommands.Text owner = new SwingCommands.Text(470, 112, 235, 40, ownerLocale);
        SwingCommands.Text idText = new SwingCommands.Text(81, 115, 60, 30, "ID");
        SwingCommands.Text nameText = new SwingCommands.Text(56, 168, 85, 40, nameTextLocale);
        SwingCommands.Text priceText = new SwingCommands.Text(65, 220, 50, 40, pricetext);
        SwingCommands.Text typeText = new SwingCommands.Text(56, 283, 85, 40, ticketTypetext);
        SwingCommands.Text kommentText = new SwingCommands.Text(54, 339, 85, 40, kommentTextLocale);
        SwingCommands.Text coordText1 = new SwingCommands.Text(150, 405, 150, 20, ticketCoordLocale);
        SwingCommands.Text coordText2 = new SwingCommands.Text(520, 405, 150, 20, ownerCoordLocal);
        SwingCommands.Text xCoordText1 = new SwingCommands.Text(76, 435, 36, 20, "X");
        SwingCommands.Text yCoordText1 = new SwingCommands.Text(347, 435, 36, 20, "Y");
        SwingCommands.Text locNameText = new SwingCommands.Text(427, 190, 90, 20, CityTextLocale);
        SwingCommands.Text hairText = new SwingCommands.Text(427, 256, 90, 15, hairColorLocale);
        SwingCommands.Text nationText = new SwingCommands.Text(427, 315, 80, 40, nationalityLocale);
        SwingCommands.Text xCoordText2 = new SwingCommands.Text(441, 435, 36, 20, "X");
        SwingCommands.Text yCoordText2 = new SwingCommands.Text(717, 435, 36, 20, "Y");
        CustomButton sendButton = new CustomButton(134, 486, 180, 40, updateLocale, 20, 0);
        JLabel title = new JLabel(fillInLocale);
       SwingCommands.TextField id =       new SwingCommands.TextField(226, 36, 150, 115, fillIdLocale);
       SwingCommands.TextField name =    new SwingCommands.TextField(226, 36, 150, 171, fillNameLocale);
       SwingCommands.TextField price =    new SwingCommands.TextField(226, 36, 150, 226, fillPriceLocale);
       SwingCommands.TextField komment =   new SwingCommands.TextField(226, 36, 150, 340, fillCommentLocale);
       SwingCommands.TextField locname =   new SwingCommands.TextField(226, 36, 520, 186, fillNameLocLocale);
       SwingCommands.TextField xcoord =    new SwingCommands.TextField(100, 32, 110, 428, xcoordLocale);
       SwingCommands.TextField ycoord = new SwingCommands.TextField(100, 32, 225, 428, ycoordlocale);
       SwingCommands.TextField xloccoord = new SwingCommands.TextField(100, 32, 478, 428, xlocaLocale);
       SwingCommands.TextField yloccoord = new SwingCommands.TextField(100, 32, 595, 428, ylocLocale);
        public ReplaceIfGreaterPanel(Long idvalue, String user) throws IOException {
            LocaleController.regist(this);
            createLocale();
            setLayout(null);
            setOpaque(false);
            setBorder(null);
            setSize(800, 600);
            setLocation(0, 0);
            Font font = new Font("Century Gothic", Font.PLAIN, 20);
            ImageIcon textFieldPanel = new ImageIcon(getScaledImage(TextFieldPanel.getImage(), 335, 46));
            ImageIcon textFieldPanelWrong = new ImageIcon(getScaledImage(TextFieldPanelWrong.getImage(), 335, 46));
            JLabel idBack = new JLabel(textFieldPanel);
            idBack.setSize(335, 46);
            idBack.setLocation(51, 109);

            JLabel nameBack = new JLabel(textFieldPanel);
            nameBack.setSize(335, 46);
            nameBack.setLocation(51, 165);
            JLabel priceBack = new JLabel(textFieldPanel);
            priceBack.setSize(335, 46);
            priceBack.setLocation(51, 221);
            JLabel typeBack = new JLabel(textFieldPanel);
            typeBack.setSize(335, 46);
            typeBack.setLocation(51, 280);
            JLabel commentBack = new JLabel(textFieldPanel);
            commentBack.setSize(335, 46);
            commentBack.setLocation(51, 336);
            JLabel locNameBack = new JLabel(textFieldPanel);
            locNameBack.setSize(335, 46);
            locNameBack.setLocation(420, 179);
            JLabel hairBack = new JLabel(textFieldPanel);
            hairBack.setSize(335, 46);
            hairBack.setLocation(420, 245);
            JLabel nationBack = new JLabel(textFieldPanel);
            nationBack.setSize(335, 46);
            nationBack.setLocation(420, 315);

            JLabel coordsBack = new JLabel(CoordFieldPanel);
            coordsBack.setSize(336, 63);
            coordsBack.setLocation(51, 400);

            JLabel locCoordsBack = new JLabel(CoordFieldPanel);
            locCoordsBack.setSize(336, 63);
            locCoordsBack.setLocation(420, 400);

            JLabel panelTextBack = new JLabel(new ImageIcon(getScaledImage(panelBack.getImage(), 452, 38)));
            panelTextBack.setSize(452, 38);
            panelTextBack.setLocation(174, 36);

            JLabel panelTextBack2 = new JLabel(new ImageIcon(getScaledImage(panelBack.getImage(), 335, 52)));
            panelTextBack2.setSize(335, 52);
            panelTextBack2.setLocation(420, 109);



            JComboBox type = new JComboBox(new Object[]{Ticket.TicketType.USUAL, Ticket.TicketType.BUDGETARY, Ticket.TicketType.CHEAP, Ticket.TicketType.VIP});
            type.setBounds(150, 285, 226, 36);
            type.setUI(new BasicComboBoxUI());
            type.setFont(font);
            JComboBox hair = new JComboBox(new Object[]{Ticket.Color.BLACK, Ticket.Color.BLUE, Ticket.Color.DARKBROWN, Ticket.Color.GREEN});
            hair.setBounds(520, 249, 226, 36);
            hair.setUI(new BasicComboBoxUI());
            hair.setFont(font);
            JComboBox nationality = new JComboBox(new Object[]{Ticket.Country.RUSSIA, Ticket.Country.FRANCE, Ticket.Country.JAPAN, Ticket.Country.NORTH_KOREA, Ticket.Country.UNITED_KINGDOM});
            nationality.setBounds(520, 320, 226, 36);
            nationality.setUI(new BasicComboBoxUI());
            nationality.setFont(font);





            title.setSize(428, 38);
            title.setLocation(186, 36);
            title.setFont(font);
            MainWindow.setFontSize(title);

            add(nationality);
            add(hair);
            add(type);
            add(idText);
            add(nameText);
            add(priceText);
            add(typeText);
            add(kommentText);
            add(coordText1);
            add(coordText2);
            add(xCoordText1);
            add(xCoordText2);
            add(yCoordText1);
            add(yCoordText2);
            add(hairText);
            add(nationText);
            add(locNameText);
            add(price);
            add(komment);
            add(locname);
            add(xcoord);
            add(ycoord);
            add(xloccoord);
            add(yloccoord);
            add(name);
            add(id);
            add(title);
            add(owner);
            add(panelTextBack2);
            add(panelTextBack);
            add(sendButton);
            add(locCoordsBack);
            add(coordsBack);
            add(idBack);
            add(nameBack);
            add(priceBack);
            add(typeBack);
            add(commentBack);
            add(locNameBack);
            add(hairBack);
            add(nationBack);

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean okay = true;

                    String ticketName;
                    Float ticketPrice = -1F;
                    Object ticketType;
                    String ticketKomment;
                    int ticketX = 0;
                    int ticketY = 0;
                    String ownerloc;
                    Object ownerColor;
                    Object ownerCountry;
                    int ownerX = 0;
                    Double ownerY = 0D;
                    Long ticketId = 0L;
                    try {
                        ticketId = Long.parseLong(id.getText());
                        Long finalTicketId = ticketId;
                        if (new TicketCollection().getTickets().entrySet().stream().noneMatch(x ->
                                (x.getKey().equals(finalTicketId) && x.getValue().getUser().equals(user))
                        ))
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        id.setText(noTicketFound);
                        id.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(id)));
                        id.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            idBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                            }
                            idBack.setIcon(textFieldPanel);
                        }));
                    }
                    ticketName = name.getText();
                    if ((ticketName.equals("") || ticketName == null || ticketName.equals(fillNameLocale) || ticketName.equals((reguiredField)))) {
                        okay = false;
                        name.setText(reguiredField);
                        name.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(name)));
                        name.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            nameBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            nameBack.setIcon(textFieldPanel);
                        }));
                    }
                    try {
                        ticketPrice = Float.parseFloat(price.getText());
                        if (ticketPrice < 0 || ticketPrice == null) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        price.setText(priceWrongLocale);
                        price.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(price)));
                        price.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            priceBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            priceBack.setIcon(textFieldPanel);
                        }));
                    }
                    ticketType = type.getSelectedItem();
                    ownerColor = hair.getSelectedItem();
                    ownerCountry = nationality.getSelectedItem();

                    ticketKomment = komment.getText();
                    if ((ticketKomment.equals("") || ticketKomment == null || ticketKomment.equals(fillCommentLocale) || ticketKomment.equals(reguiredField))) {
                        okay = false;
                        komment.setText(reguiredField);
                        komment.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(komment)));
                        komment.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            commentBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            commentBack.setIcon(textFieldPanel);
                        }));
                    }

                    try {
                        ticketX = Integer.parseInt(xcoord.getText());
                        if (ticketX > 297 || ticketX < -297) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        xcoord.setText(xcoordLocale);
                        xcoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(xcoord)));
                        xcoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            coordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            coordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ticketY = Integer.parseInt(ycoord.getText());
                        if (ticketY > 500 || ticketY < -500) throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        ycoord.setText(ycoordlocale);
                        ycoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(ycoord)));
                        ycoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            coordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            coordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ownerX = Integer.parseInt(xloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        xloccoord.setText(wrongData);
                        xloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(xloccoord)));
                        xloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ownerY = Double.parseDouble(yloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        yloccoord.setText(wrongData);
                        yloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(yloccoord)));
                        yloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    ownerloc = locname.getText();
                    if ((ownerloc.equals("") || ownerloc == null || ownerloc.equals(fillNameLocale) || ownerloc.equals(reguiredField))) {
                        okay = false;
                        locname.setText(reguiredField);
                        locname.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(locname)));
                        locname.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locNameBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locNameBack.setIcon(textFieldPanel);
                        }));
                    }
                    if (okay) {
                        Ticket ticket = new Ticket();
                        ticket.setMapKey(ticketId);
                        ticket.setCreationDate(new TicketCollection().getTicket(ticketId).getCreationDate());
                        ticket.setName(ticketName);
                        ticket.setPrice(ticketPrice);
                        ticket.setType(Ticket.TicketType.valueOf(ticketType.toString()));
                        ticket.setComment(ticketKomment);
                        Ticket.Coordinates coords = ticket.new Coordinates();
                        Ticket.Person person = ticket.new Person();
                        person.setPassportID(Long.parseLong(new TicketCollection().getTicket(ticketId).getPerson().getPassportID()));
                        Ticket.Location location = ticket.new Location();
                        coords.setX((long) ticketX);
                        coords.setY(ticketY);
                        person.setHairColor(Ticket.Color.valueOf(ownerColor.toString()));
                        person.setNationality(Ticket.Country.valueOf(ownerCountry.toString()));
                        location.setName(ownerloc);
                        location.setX(ownerX);
                        location.setY(ownerY);
                        person.setLocation(location);
                        ticket.setPerson(person);
                        ticket.setCoordinates(coords);
                        Map<Command, Object> commandStringMap = new HashMap<>();
                        commandStringMap.put(new ReplaceIfGreater(), ticket);
                        try {
                            ClientSender.send(commandStringMap);
                        } catch (SocketException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

        }

        @Override
        public void createLocale() {
            ResourceBundle language = LocaleController.getResourceBundle();
            fillInLocale = language.getString("fillInLocale");
            nameTextLocale = language.getString("nameTextLocale");
            pricetext = language.getString("pricetext");
            ticketTypetext = language.getString("ticketTypetext");
            kommentTextLocale = language.getString("kommentTextLocale");
            CityTextLocale = language.getString("CityTextLocale");
            hairColorLocale = language.getString("hairColorLocale");
            nationalityLocale = language.getString("nationalityLocale");
            ticketCoordLocale = language.getString("ticketCoordLocale");
            ownerCoordLocal = language.getString("ownerCoordLocal");
            updateLocale = language.getString("updateLocale");
            ownerLocale = language.getString("ownerLocale");
            ticketToDeletelocale = language.getString("ticketToDeletelocale");
            priceToDeletLocale = language.getString("priceToDeleteLocale");
            fillIdLocale = language.getString("fillIdLocale");
            fillNameLocale = language.getString("fillNameLocale");
            fillPriceLocale = language.getString("fillPriceLocale");
            fillCommentLocale = language.getString("fillCommentLocale");
            fillNameLocLocale = language.getString("fillNameLocLocale");
            xcoordLocale = language.getString("xcoordLocale");
            ycoordlocale = language.getString("ycoordlocale");
            xlocaLocale = language.getString("xlocaLocale");
            ylocLocale = language.getString("ylocLocale");
            removeupdate = language.getString("removeupdate");
            wrongData = language.getString("wrongData");
            reguiredField = language.getString("reguiredField");
            priceWrongLocale = language.getString("priceWrongLocale");
            noTicketFound=language.getString("noTicketFound");
            owner.setText(ownerLocale);MainWindow.setFontSize(owner);
            idText.setText("ID");MainWindow.setFontSize(idText);
            nameText.setText(nameTextLocale);MainWindow.setFontSize(nameText);
            priceText.setText(pricetext);MainWindow.setFontSize(priceText);
            typeText.setText(ticketTypetext);MainWindow.setFontSize(typeText);
            kommentText.setText(kommentTextLocale);MainWindow.setFontSize(kommentText);
            coordText1.setText(ticketCoordLocale);MainWindow.setFontSize(coordText1);
            coordText2.setText(ownerCoordLocal);MainWindow.setFontSize(coordText2);
            xCoordText1.setText("X");
            yCoordText1.setText("Y");
            locNameText.setText(CityTextLocale);MainWindow.setFontSize(locNameText);
            hairText.setText(hairColorLocale);MainWindow.setFontSize(hairText);
            nationText.setText(nationalityLocale);MainWindow.setFontSize(nationText);
            xCoordText2.setText("X");
            yCoordText2.setText("Y");
            sendButton.setText(updateLocale);
            id .setText( fillIdLocale); id.setFont(new Font(id.getFont().getFontName(),id.getFont().getStyle(),SwingCommands.returnSize(id)));
            name  .setText(fillNameLocale); name.setFont(new Font(name.getFont().getFontName(),name.getFont().getStyle(),SwingCommands.returnSize(name)));
            price.setText(  fillPriceLocale); price.setFont(new Font(price.getFont().getFontName(),price.getFont().getStyle(),SwingCommands.returnSize(price)));
            komment .setText( fillCommentLocale); komment.setFont(new Font(komment.getFont().getFontName(),komment.getFont().getStyle(),SwingCommands.returnSize(komment)));
            locname .setText( fillNameLocLocale); locname.setFont(new Font(locname.getFont().getFontName(),locname.getFont().getStyle(),SwingCommands.returnSize(locname)));
            xcoord .setText( xcoordLocale); xcoord.setFont(new Font(xcoord.getFont().getFontName(),xcoord.getFont().getStyle(),SwingCommands.returnSize(xcoord)));
            ycoord .setText( ycoordlocale); ycoord.setFont(new Font(ycoord.getFont().getFontName(),ycoord.getFont().getStyle(),SwingCommands.returnSize(ycoord)));
            xloccoord.setText(  xlocaLocale); xloccoord.setFont(new Font(xloccoord.getFont().getFontName(),xloccoord.getFont().getStyle(),SwingCommands.returnSize(xloccoord)));
            yloccoord.setText(ylocLocale); yloccoord.setFont(new Font(yloccoord.getFont().getFontName(),yloccoord.getFont().getStyle(),SwingCommands.returnSize(yloccoord)));

        }
    }

    public static class TicketMenuPanel extends JPanel implements Localizeable {
        Ticket ticket;
        SwingCommands.Text owner = new SwingCommands.Text(470, 112, 235, 40, ownerLocale);
        SwingCommands.Text idText = new SwingCommands.Text(81, 115, 60, 30, "ID");
        SwingCommands.Text nameText = new SwingCommands.Text(56, 168, 85, 40, nameTextLocale);
        SwingCommands.Text priceText = new SwingCommands.Text(65, 220, 50, 40, pricetext);
        SwingCommands.Text typeText = new SwingCommands.Text(56, 283, 85, 40, ticketTypetext);
        SwingCommands.Text kommentText = new SwingCommands.Text(54, 339, 85, 40, kommentTextLocale);
        SwingCommands.Text coordText1 = new SwingCommands.Text(150, 405, 150, 20, ticketCoordLocale);
        SwingCommands.Text coordText2 = new SwingCommands.Text(520, 405, 150, 20, ownerCoordLocal);
        SwingCommands.Text xCoordText1 = new SwingCommands.Text(76, 435, 36, 20, "X");
        SwingCommands.Text yCoordText1 = new SwingCommands.Text(347, 435, 36, 20, "Y");
        SwingCommands.Text locNameText = new SwingCommands.Text(427, 190, 90, 20, CityTextLocale);
        SwingCommands.Text hairText = new SwingCommands.Text(427, 256, 90, 15, hairColorLocale);
        SwingCommands.Text nationText = new SwingCommands.Text(427, 315, 80, 40, nationalityLocale);
        SwingCommands.Text xCoordText2 = new SwingCommands.Text(441, 435, 36, 20, "X");
        SwingCommands.Text yCoordText2 = new SwingCommands.Text(717, 435, 36, 20, "Y");
        CustomButton sendButton = new CustomButton(310, 486, 180, 40, updateLocale, 20, 0);
        CustomButton deleteButton = new CustomButton(100, 486, 180, 40, removeLocale, 20, 0);
        JLabel title = new JLabel(removeupdate+" ");

        public TicketMenuPanel(Long idvalue, String user) throws IOException {

            setLayout(null);
            LocaleController.regist(this);
            ticket = new TicketCollection().getTickets().get(idvalue);
            setOpaque(false);
            setBorder(null);
            setSize(800, 600);
            setLocation(0, 0);
            Font font = new Font("Century Gothic", Font.PLAIN, 20);
            ImageIcon textFieldPanel = new ImageIcon(getScaledImage(TextFieldPanel.getImage(), 335, 46));
            ImageIcon textFieldPanelWrong = new ImageIcon(getScaledImage(TextFieldPanelWrong.getImage(), 335, 46));
            JLabel idBack = new JLabel(textFieldPanel);
            idBack.setSize(335, 46);
            idBack.setLocation(51, 109);
            createLocale();

            JLabel nameBack = new JLabel(textFieldPanel);
            nameBack.setSize(335, 46);
            nameBack.setLocation(51, 165);
            JLabel priceBack = new JLabel(textFieldPanel);
            priceBack.setSize(335, 46);
            priceBack.setLocation(51, 221);
            JLabel typeBack = new JLabel(textFieldPanel);
            typeBack.setSize(335, 46);
            typeBack.setLocation(51, 280);
            JLabel commentBack = new JLabel(textFieldPanel);
            commentBack.setSize(335, 46);
            commentBack.setLocation(51, 336);
            JLabel locNameBack = new JLabel(textFieldPanel);
            locNameBack.setSize(335, 46);
            locNameBack.setLocation(420, 179);
            JLabel hairBack = new JLabel(textFieldPanel);
            hairBack.setSize(335, 46);
            hairBack.setLocation(420, 245);
            JLabel nationBack = new JLabel(textFieldPanel);
            nationBack.setSize(335, 46);
            nationBack.setLocation(420, 315);

            JLabel coordsBack = new JLabel(CoordFieldPanel);
            coordsBack.setSize(336, 63);
            coordsBack.setLocation(51, 400);

            JLabel locCoordsBack = new JLabel(CoordFieldPanel);
            locCoordsBack.setSize(336, 63);
            locCoordsBack.setLocation(420, 400);

            JLabel panelTextBack = new JLabel(new ImageIcon(getScaledImage(panelBack.getImage(), 452, 38)));
            panelTextBack.setSize(452, 38);
            panelTextBack.setLocation(174, 36);

            JLabel panelTextBack2 = new JLabel(new ImageIcon(getScaledImage(panelBack.getImage(), 335, 52)));
            panelTextBack2.setSize(335, 52);
            panelTextBack2.setLocation(420, 109);


            SwingCommands.TextField id;
            SwingCommands.TextField name;
            SwingCommands.TextField price;
            SwingCommands.TextField komment;
            SwingCommands.TextField locname;
            SwingCommands.TextField xcoord;
            SwingCommands.TextField ycoord;
            SwingCommands.TextField xloccoord;
            SwingCommands.TextField yloccoord;

            JComboBox type = new JComboBox(new Object[]{Ticket.TicketType.USUAL, Ticket.TicketType.BUDGETARY, Ticket.TicketType.CHEAP, Ticket.TicketType.VIP});
            type.setBounds(150, 285, 226, 36);
            type.setUI(new BasicComboBoxUI());
            type.setFont(font);
            JComboBox hair = new JComboBox(new Object[]{Ticket.Color.BLACK, Ticket.Color.BLUE, Ticket.Color.DARKBROWN, Ticket.Color.GREEN});
            hair.setBounds(520, 249, 226, 36);
            hair.setUI(new BasicComboBoxUI());
            hair.setFont(font);
            JComboBox nationality = new JComboBox(new Object[]{Ticket.Country.RUSSIA, Ticket.Country.FRANCE, Ticket.Country.JAPAN, Ticket.Country.NORTH_KOREA, Ticket.Country.UNITED_KINGDOM});
            nationality.setBounds(520, 320, 226, 36);
            nationality.setUI(new BasicComboBoxUI());
            nationality.setFont(font);

            id = new SwingCommands.TextField(226, 36, 150, 115, String.valueOf(idvalue));
            id.setEditable(false);id.setFocusable(false);
            name = new SwingCommands.TextField(226, 36, 150, 171, ticket.getName());
            price = new SwingCommands.TextField(226, 36, 150, 226, String.valueOf(ticket.getPrice()));
            komment = new SwingCommands.TextField(226, 36, 150, 340, ticket.getComment());
            locname = new SwingCommands.TextField(226, 36, 520, 186, ticket.getPerson().getLocation().getName());
            xcoord = new SwingCommands.TextField(100, 32, 110, 428, String.valueOf(ticket.getCoordinates().getX()));
            ycoord = new SwingCommands.TextField(100, 32, 225, 428, String.valueOf(ticket.getCoordinates().getY()));
            xloccoord = new SwingCommands.TextField(100, 32, 478, 428, String.valueOf(ticket.getPerson().getLocation().getX()));
            yloccoord = new SwingCommands.TextField(100, 32, 595, 428, String.valueOf(ticket.getPerson().getLocation().getY()));
            type.setSelectedItem(ticket.getType());
            hair.setSelectedItem(ticket.getPerson().getHairColor());
            nationality.setSelectedItem(ticket.getPerson().getNationality());
            if (!(user.equals(ticket.getUser()))) {

                sendButton.setEnabled(false);
                deleteButton.setEnabled(false);
                name.setEditable(false);
                price.setEditable(false);
                komment.setEditable(false);
                locname.setEditable(false);
                xcoord.setEditable(false);
                ycoord.setEditable(false);
                xloccoord.setEditable(false);
                yloccoord.setEditable(false);
                type.setEditable(false);
                hair.setEditable(false);
                nationality.setEditable(false);
            }


            title.setSize(428, 38);
            title.setLocation(186, 36);
            title.setFont(font);
            MainWindow.setFontSize(title);

            add(deleteButton);
            add(nationality);
            add(hair);
            add(type);
            add(idText);
            add(nameText);
            add(priceText);
            add(typeText);
            add(kommentText);
            add(coordText1);
            add(coordText2);
            add(xCoordText1);
            add(xCoordText2);
            add(yCoordText1);
            add(yCoordText2);
            add(hairText);
            add(nationText);
            add(locNameText);
            add(price);
            add(komment);
            add(locname);
            add(xcoord);
            add(ycoord);
            add(xloccoord);
            add(yloccoord);
            add(name);
            add(id);
            add(title);
            add(owner);
            add(panelTextBack2);
            add(panelTextBack);
            add(sendButton);
            add(locCoordsBack);
            add(coordsBack);
            add(idBack);
            add(nameBack);
            add(priceBack);
            add(typeBack);
            add(commentBack);
            add(locNameBack);
            add(hairBack);
            add(nationBack);

            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean okay = true;

                    String ticketName;
                    Float ticketPrice = -1F;
                    Object ticketType;
                    String ticketKomment;
                    int ticketX = 0;
                    int ticketY = 0;
                    String ownerloc;
                    Object ownerColor;
                    Object ownerCountry;
                    int ownerX = 0;
                    Double ownerY = 0D;
                    Long ticketId = 0L;
                    try {
                        ticketId = Long.parseLong(id.getText());
                        Long finalTicketId = ticketId;
                        if (new TicketCollection().getTickets().entrySet().stream().noneMatch(x ->
                                (x.getKey().equals(finalTicketId) && x.getValue().getUser().equals(user))
                        ))
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        id.setText("Не найдено такого билета.");
                        id.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(id)));
                        id.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            idBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                            }
                            idBack.setIcon(textFieldPanel);
                        }));
                    }
                    ticketName = name.getText();
                    if ((ticketName.equals("") || ticketName == null || ticketName.equals("Введите название нового билета.") || ticketName.equals(("Обязательное поле.")))) {
                        okay = false;
                        name.setText("Обязательное поле.");
                        name.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(name)));
                        name.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            nameBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            nameBack.setIcon(textFieldPanel);
                        }));
                    }
                    try {
                        ticketPrice = Float.parseFloat(price.getText());
                        if (ticketPrice < 0 || ticketPrice == null)
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        price.setText("Цена - положительное число");
                        price.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(price)));
                        price.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            priceBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            priceBack.setIcon(textFieldPanel);
                        }));
                    }
                    ticketType = type.getSelectedItem();
                    ownerColor = hair.getSelectedItem();
                    ownerCountry = nationality.getSelectedItem();

                    ticketKomment = komment.getText();
                    if ((ticketKomment.equals("") || ticketKomment == null || ticketKomment.equals("Введите комментарий.") || ticketKomment.equals("Обязательное поле."))) {
                        okay = false;
                        komment.setText("Обязательное поле.");
                        komment.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(komment)));
                        komment.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            commentBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            commentBack.setIcon(textFieldPanel);
                        }));
                    }

                    try {
                        ticketX = Integer.parseInt(xcoord.getText());
                        if (ticketX > 297 || ticketX < -297)
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        xcoord.setText("Число <= +-297");
                        xcoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(xcoord)));
                        xcoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            coordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            coordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ticketY = Integer.parseInt(ycoord.getText());
                        if (ticketY > 500 || ticketY < -500)
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        ycoord.setText("Число <= +-500");
                        ycoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(ycoord)));
                        ycoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            coordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            coordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ownerX = Integer.parseInt(xloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        xloccoord.setText("Неверный формат.");
                        xloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(xloccoord)));
                        xloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    try {
                        ownerY = Double.parseDouble(yloccoord.getText());
                    } catch (NumberFormatException ex) {
                        okay = false;
                        yloccoord.setText("Неверный формат.");
                        yloccoord.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(yloccoord)));
                        yloccoord.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locCoordsBack.setIcon(WrongCoordField);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locCoordsBack.setIcon(CoordFieldPanel);
                        }));
                    }
                    ownerloc = locname.getText();
                    if ((ownerloc.equals("") || ownerloc == null || ownerloc.equals("Введите имя локации.") || ownerloc.equals("Обязательное поле."))) {
                        okay = false;
                        locname.setText("Обязательное поле.");
                        locname.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(locname)));
                        locname.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            locNameBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                                exc.printStackTrace();
                            }
                            locNameBack.setIcon(textFieldPanel);
                        }));
                    }
                    if (okay) {
                        Ticket ticket = new Ticket();
                        ticket.setMapKey(ticketId);
                        ticket.setCreationDate(new TicketCollection().getTicket(ticketId).getCreationDate());
                        ticket.setName(ticketName);
                        ticket.setPrice(ticketPrice);
                        ticket.setType(Ticket.TicketType.valueOf(ticketType.toString()));
                        ticket.setComment(ticketKomment);
                        Ticket.Coordinates coords = ticket.new Coordinates();
                        Ticket.Person person = ticket.new Person();
                        person.setPassportID(Long.parseLong(new TicketCollection().getTicket(ticketId).getPerson().getPassportID()));
                        Ticket.Location location = ticket.new Location();
                        coords.setX((long) ticketX);
                        coords.setY(ticketY);
                        person.setHairColor(Ticket.Color.valueOf(ownerColor.toString()));
                        person.setNationality(Ticket.Country.valueOf(ownerCountry.toString()));
                        location.setName(ownerloc);
                        location.setX(ownerX);
                        location.setY(ownerY);
                        person.setLocation(location);
                        ticket.setPerson(person);
                        ticket.setCoordinates(coords);
                        Map<Command, Object> commandStringMap = new HashMap<>();
                        commandStringMap.put(new UpdateBykey(), ticket);
                        try {
                            ClientSender.send(commandStringMap);
                        } catch (SocketException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean okay = true;
                    Long ticketId = 0L;
                    try {
                        ticketId = Long.parseLong(id.getText());
                        Long finalTicketId = ticketId;
                        if (new TicketCollection().getTickets().entrySet().stream().noneMatch(x ->
                                (x.getKey().equals(finalTicketId) && x.getValue().getUser().equals(user))
                        ))
                            throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        okay = false;
                        id.setText("Не найдено такого билета.");
                        id.setFont(new Font("Century Gothic", Font.PLAIN, returnSize(id)));
                        id.setForeground(Color.RED);
                        threadsForEverybody.execute(new Thread(() -> {
                            idBack.setIcon(textFieldPanelWrong);
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException exc) {
                            }
                            idBack.setIcon(textFieldPanel);
                        }));
                    }

                    if (okay) {
                        Map<Command, Object> commandStringMap = new HashMap<>();
                        commandStringMap.put(new RemoveByKey(), ticketId);
                        try {
                            ClientSender.send(commandStringMap);
                        } catch (SocketException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            });


        }

        @Override
        public void createLocale() {
            ResourceBundle language = LocaleController.getResourceBundle();
            fillInLocale = language.getString("fillInLocale");
            nameTextLocale = language.getString("nameTextLocale");
            pricetext = language.getString("pricetext");
            ticketTypetext = language.getString("ticketTypetext");
            kommentTextLocale = language.getString("kommentTextLocale");
            CityTextLocale = language.getString("CityTextLocale");
            hairColorLocale = language.getString("hairColorLocale");
            nationalityLocale = language.getString("nationalityLocale");
            ticketCoordLocale = language.getString("ticketCoordLocale");
            ownerCoordLocal = language.getString("ownerCoordLocal");
            updateLocale = language.getString("updateLocale");
            ownerLocale = language.getString("ownerLocale");
            ticketToDeletelocale = language.getString("ticketToDeletelocale");
            priceToDeletLocale = language.getString("priceToDeleteLocale");
            fillIdLocale = language.getString("fillIdLocale");
            fillNameLocale = language.getString("fillNameLocale");
            fillPriceLocale = language.getString("fillPriceLocale");
            fillCommentLocale = language.getString("fillCommentLocale");
            fillNameLocLocale = language.getString("fillNameLocLocale");
            xcoordLocale = language.getString("xcoordLocale");
            ycoordlocale = language.getString("ycoordlocale");
            xlocaLocale = language.getString("xlocaLocale");
            ylocLocale = language.getString("ylocLocale");
            removeupdate = language.getString("removeupdate");
            wrongData = language.getString("wrongData");
            reguiredField = language.getString("reguiredField");
            priceWrongLocale = language.getString("priceWrongLocale");
            noTicketFound=language.getString("noTicketFound");
            owner.setText(ownerLocale);MainWindow.setFontSize(owner);
            idText.setText("ID");MainWindow.setFontSize(idText);
            nameText.setText(nameTextLocale);MainWindow.setFontSize(nameText);
            priceText.setText(pricetext);MainWindow.setFontSize(priceText);
            typeText.setText(ticketTypetext);MainWindow.setFontSize(typeText);
            kommentText.setText(kommentTextLocale);MainWindow.setFontSize(kommentText);
            coordText1.setText(ticketCoordLocale);MainWindow.setFontSize(coordText1);
            coordText2.setText(ownerCoordLocal);MainWindow.setFontSize(coordText2);
            xCoordText1.setText("X");
            yCoordText1.setText("Y");
            locNameText.setText(CityTextLocale);MainWindow.setFontSize(locNameText);
            hairText.setText(hairColorLocale);MainWindow.setFontSize(hairText);
            nationText.setText(nationalityLocale);MainWindow.setFontSize(nationText);
            xCoordText2.setText("X");
            yCoordText2.setText("Y");
            sendButton.setText(updateLocale);
            deleteButton.setText(removeLocale);
            title.setText(removeupdate+" "+ticket.getUser());
            MainWindow.setFontSize(title);
        }
    }
}
