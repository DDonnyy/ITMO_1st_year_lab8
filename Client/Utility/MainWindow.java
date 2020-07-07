package Utility;

import Common.Command;
import Common.Commands.AverageOfPrice;
import Common.Commands.Clear;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class MainWindow extends JFrame implements Localizeable {
    String averageText;
    String clearText;
    String executeText;
    String updateText;
    String insertText;
    String removePersonText;
    String removeText;
    String removelowerText;
    String replacegreaterText;
    String averageTool;
    String clearTool;
    String executeTool;
    String updateTool;
    String insertTool;
    String removePersonTool;
    String removeTool;
    String removelowerTool;
    String empty;
    String averagePriceText;
    String exit;
    String workingtext;
    String Noconnection;
    int buttonWidth = 220;
    int buttonHeight = 40;
    int buttonXloc = 15;
    int buttonYloc = 100;
    int fontkek = 1;
    static JPanel jPanel = new JPanel();
    SwingCommands.MyBar jMenuBar = new SwingCommands.MyBar(20);
    SwingCommands.CustomButton averagePrice = new SwingCommands.CustomButton(buttonXloc, buttonYloc, buttonWidth, buttonHeight, averageText, 15, fontkek);
    SwingCommands.CustomButton removeAllTickets = new SwingCommands.CustomButton(buttonXloc, buttonYloc += 50, buttonWidth, buttonHeight, clearText, 15, fontkek);
    SwingCommands.CustomButton execute = new SwingCommands.CustomButton(buttonXloc, buttonYloc += 50, buttonWidth, buttonHeight, executeText, 15, fontkek);
    SwingCommands.CustomButton update = new SwingCommands.CustomButton(buttonXloc, buttonYloc += 50, buttonWidth, buttonHeight, updateText, 15, fontkek);
    SwingCommands.CustomButton insert = new SwingCommands.CustomButton(buttonXloc, buttonYloc += 50, buttonWidth, buttonHeight, insertText, 15, fontkek);
    SwingCommands.CustomButton removeAllByPerson = new SwingCommands.CustomButton(buttonXloc, buttonYloc += 50, buttonWidth, buttonHeight, removePersonText, 15, fontkek);
    SwingCommands.CustomButton remove = new SwingCommands.CustomButton(buttonXloc, buttonYloc += 50, buttonWidth, buttonHeight, removeText, 15, fontkek);
    SwingCommands.CustomButton removeLower = new SwingCommands.CustomButton(buttonXloc, buttonYloc += 50, buttonWidth, buttonHeight, removelowerText, 15, fontkek);
    SwingCommands.CustomButton replaceIfGreater = new SwingCommands.CustomButton(buttonXloc, buttonYloc + 50, buttonWidth, buttonHeight, replacegreaterText, 15, fontkek);
    SwingCommands.CustomButton closeButton6 = new SwingCommands.CustomButton(498, 486, 180, 40, exit, 20, 0);
    SwingCommands.CustomButton closeButton5 = new SwingCommands.CustomButton(190, 110, 180, 40, exit, 20, 0);
    SwingCommands.CustomButton closeButton4 = new SwingCommands.CustomButton(190, 110, 180, 40, exit, 20, 0);
    SwingCommands.CustomButton closeButton3 = new SwingCommands.CustomButton(190, 486, 180, 40, exit, 20, 0);
    SwingCommands.CustomButton closeButton2 = new SwingCommands.CustomButton(498, 486, 180, 40, exit, 20, 0);
    SwingCommands.CustomButton closeButton1 = new SwingCommands.CustomButton(498, 486, 180, 40, exit, 20, 0);
    SwingCommands.CustomButton closeButton = new SwingCommands.CustomButton(150, 100, 90, 40, exit, 20, 0);
    SwingCommands.CustomButton closeButton8 = new SwingCommands.CustomButton(520, 486, 180, 40, exit, 20, 0);
    JDialog replaceIfGreaterDialog = new JDialog(this, replacegreaterText, true);
    JDialog removeLowerDialog = new JDialog(this, removelowerTool, true);
    JDialog removeidDialog = new JDialog(this, removeTool, true);
    JDialog removeAllDialog = new JDialog(this, removePersonTool, true);
    JDialog insertDialog = new JDialog(this, insertTool, true);
    JDialog updateDialog = new JDialog(this, updateTool, true);
    JDialog averageDialog = new JDialog(this, averageText, true);
    JLabel status = new JLabel();

    public MainWindow(String login, String color,LoginWindow loginWindow) throws IOException {
        try {

            replaceIfGreaterDialog.setResizable(false);
            removeLowerDialog.setResizable(false);
            removeidDialog.setResizable(false);
            removeAllDialog.setResizable(false);
            insertDialog.setResizable(false);
            updateDialog.setResizable(false);
            averageDialog.setResizable(false);
            LocaleController.regist(this);
            createLocale();
            ReceiverAndCanvasChanger receiverAndCanvasChanger = new ReceiverAndCanvasChanger();
            setContentPane(new SwingCommands.Background("images\\main.png"));
            setVisible(true);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimension = toolkit.getScreenSize();
            Font font = new Font("Century Gothic", Font.BOLD, 20);

            setJMenuBar(jMenuBar);

            JLabel back = new JLabel(new ImageIcon(SwingCommands.getScaledImage(ImageIO.read(new File("images\\panel.png")), 300, 30)));
            back.setSize(300, 30);
            averageDialog.setContentPane(new SwingCommands.Background("images\\logreg.png"));
            averageDialog.setLayout(null);
            averageDialog.setBounds(dimension.width / 2 - 200, dimension.height / 2 - 100, 400, 200);
            averageDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            averageDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    averageDialog.setVisible(false);
                    averageDialog.getContentPane().removeAll();
                    averageDialog.revalidate();
                    averageDialog.repaint();


                }
            });
            averagePrice.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    averageDialog.add(closeButton);
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            averageDialog.setVisible(false);
                            averageDialog.getContentPane().removeAll();
                            averageDialog.revalidate();
                            averageDialog.repaint();
                        }
                    });
                    AverageOfPrice averageOfPrice = new AverageOfPrice();
                    Map<Command, Object> commandStringMap = new HashMap<>();
                    commandStringMap.put(averageOfPrice, null);
                    try {
                        ClientSender.send(commandStringMap);
                    } catch (SocketException ex) {
                        closeAllAndWaitConnection(loginWindow);
                    }
                    String answer = receiverAndCanvasChanger.getAnswer();
                    if (answer==null||answer.equals(null)){}
                    else {
                        if (answer.equals("no")) {
                            answer = empty;
                        } else answer = averagePriceText + String.format("%.2f", (Double.valueOf(answer)));
                        back.setLocation(50, 50);
                        JLabel answerlab = new JLabel(answer);
                        answerlab.setSize(292, 30);
                        answerlab.setFont(font);
                        setFontSize(answerlab);
                        answerlab.setLocation(54, 51);
                        averageDialog.add(answerlab);
                        averageDialog.add(back);
                        averageDialog.setVisible(true);
                    }
                }
            });


            removeAllTickets.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Map<Command, Object> commandStringMap = new HashMap<>();
                    commandStringMap.put(new Clear(), null);
                    try {
                        ClientSender.send(commandStringMap);
                    } catch (SocketException ex) {
                        closeAllAndWaitConnection(loginWindow);
                    }
                }
            });


            execute.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FileHandler fileHandler = new FileHandler();
                }
            });


            updateDialog.setContentPane(new SwingCommands.Background("images\\logreg.png"));
            updateDialog.setLayout(null);
            updateDialog.setBounds(dimension.width / 2 - 400, dimension.height / 2 - 300, 800, 600);
            updateDialog.add(closeButton2);
            updateDialog.add(new CommandDialogs.UpdatePanel(null, login));

            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    closeButton2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            updateDialog.setVisible(false);

                        }
                    });
                    updateDialog.setVisible(true);
                }
            });


            insertDialog.setContentPane(new SwingCommands.Background("images\\logreg.png"));
            insertDialog.setLayout(null);
            insertDialog.setBounds(dimension.width / 2 - 400, dimension.height / 2 - 300, 800, 600);
            insertDialog.add(new CommandDialogs.InsertPanel());
            insertDialog.add(closeButton1);
            closeButton1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    insertDialog.setVisible(false);
                }
            });
            insert.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    insertDialog.setVisible(true);
                }
            });


            removeAllDialog.setContentPane(new SwingCommands.Background("images\\logreg.png"));
            removeAllDialog.setLayout(null);
            removeAllDialog.setBounds(dimension.width / 2 - 200, dimension.height / 2 - 300, 400, 600);
            removeAllDialog.add(new CommandDialogs.RemoveByPersonTable());


            removeAllByPerson.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeAllDialog.add(closeButton3);
                    closeButton3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            removeAllDialog.setVisible(false);
                        }
                    });
                    removeAllDialog.setVisible(true);
                }
            });


            removeidDialog.setContentPane(new SwingCommands.Background("images\\logreg.png"));
            removeidDialog.setLayout(null);
            removeidDialog.setBounds(dimension.width / 2 - 200, dimension.height / 2 - 150, 400, 200);
            removeidDialog.add(new CommandDialogs.RemovePanel(login));
            removeidDialog.add(closeButton4);
            closeButton4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeidDialog.setVisible(false);
                }
            });
            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeidDialog.setVisible(true);
                }
            });

            removeLowerDialog.setContentPane(new SwingCommands.Background("images\\logreg.png"));
            removeLowerDialog.setLayout(null);
            removeLowerDialog.setBounds(dimension.width / 2 - 200, dimension.height / 2 - 150, 400, 200);
            removeLowerDialog.add(new CommandDialogs.RemoveLowerPanel(login));
            removeLowerDialog.add(closeButton5);
            closeButton5.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeLowerDialog.setVisible(false);
                }
            });
            removeLower.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeLowerDialog.setVisible(true);
                }
            });

            replaceIfGreaterDialog.setContentPane(new SwingCommands.Background("images\\logreg.png"));
            replaceIfGreaterDialog.setLayout(null);
            replaceIfGreaterDialog.setBounds(dimension.width / 2 - 400, dimension.height / 2 - 300, 800, 600);
            replaceIfGreaterDialog.add(new CommandDialogs.ReplaceIfGreaterPanel(null, login));
            replaceIfGreaterDialog.add(closeButton6);
            closeButton6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replaceIfGreaterDialog.setVisible(false);
                }
            });
            replaceIfGreater.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replaceIfGreaterDialog.setVisible(true);
                }
            });

            JLabel loginback = new JLabel(new ImageIcon(SwingCommands.getScaledImage(ImageIO.read(new File("images\\panel.png")), buttonWidth, 80)));
            loginback.setSize(buttonWidth, 80);
            loginback.setLocation(buttonXloc, 10);

            JLabel loginText = new JLabel(login);
            loginText.setSize(155, 40);
            this.setFontSize(loginText);
            loginText.setLocation(90, 30);
            String[] kek = (color).replace("[", "").replace("]", "").replace(" ", "").split(",");
            int l1 = Integer.parseInt(kek[0]);
            int l2 = Integer.parseInt(kek[1]);
            int l3 = Integer.parseInt(kek[2]);
            Color color1 = new Color(l1, l2, l3);

            SwingCommands.Human avatar = new SwingCommands.Human(30, 20, 40, 40, color1);
            SwingCommands.Animation.startAnime(avatar);

            Object[] columnNames = {"id", "date", "name", "xcoord", "ycoord", "price", "comment", "type", "haircolor", "country", "xloc",
                    "yloc", "locname", "passportid", "user"};
            Object[][] data = {};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            ReceiverAndCanvasChanger.TicketTable table = new ReceiverAndCanvasChanger.TicketTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setSize(1148, 218);
            scrollPane.setLocation(26, 556);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setOpaque(false);
            scrollPane.getViewport().setBorder(null);

            scrollPane.setBorder(BorderFactory.createEmptyBorder());
            status.setSize(100, 30);
            status.setLocation(250, 30);
            status.setVisible(false);
            add(avatar);
            add(status);
            getContentPane().add(scrollPane);
            add(loginText);
            add(loginback);
            add(averagePrice);
            add(removeAllTickets);
            add(execute);
            add(update);
            add(insert);
            add(removeAllByPerson);
            add(remove);
            add(removeLower);
            add(replaceIfGreater);
            setBounds(dimension.width / 2 - 600, dimension.height / 2 - 400, 1200, 860);
            setLayout(null);
            setTitle(workingtext);
            setResizable(false);
            receiverAndCanvasChanger.receive(table, this, login);

            receiverAndCanvasChanger.startAnime();
        } catch (SocketException e) {
            System.out.println(1);
            status.setVisible(true);
            closeAllAndWaitConnection(loginWindow);

        }
    }

    public void closeAllAndWaitConnection(LoginWindow loginWindow) {
        replaceIfGreaterDialog.setVisible(false);
        removeLowerDialog.setVisible(false);
        removeidDialog.setVisible(false);
        removeAllDialog.setVisible(false);
        insertDialog.setVisible(false);
        updateDialog.setVisible(false);
        averageDialog.setVisible(false);
        averagePrice.setEnabled(false);
        removeAllTickets.setEnabled(false);
        execute.setEnabled(false);
        update.setEnabled(false);
        insert.setEnabled(false);
        removeAllByPerson.setEnabled(false);
        remove.setEnabled(false);
        removeLower.setEnabled(false);
        replaceIfGreater.setEnabled(false);

        ReceiverAndCanvasChanger.addedTickets.entrySet().stream().forEach(x->this.remove(x.getValue()));
        ReceiverAndCanvasChanger.addedTickets.clear();
        this.removeAll();
        this.dispose();

        loginWindow.setVisible(true);
        loginWindow.revalidate();
        loginWindow.repaint();


    }

    public static void setFontSize(JLabel label) {
        try {
            Font labelFont = label.getFont();
            String labelText = label.getText();
            int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
            int componentWidth = label.getWidth();
            double widthRatio = (double) componentWidth / (double) stringWidth;
            int newFontSize = (int) (labelFont.getSize() * widthRatio);
            int componentHeight = label.getHeight();
            int fontSizeToUse = Math.min(newFontSize, componentHeight);
            label.setFont(new Font(labelFont.getName(), labelFont.getStyle(), fontSizeToUse));
        } catch (NullPointerException e) {

        }
    }

    @Override
    public void createLocale() {
        ResourceBundle language = LocaleController.getResourceBundle();
        averageText = language.getString("averageText");
        averagePrice.setText(averageText);
        clearText = language.getString("clearText");
        removeAllTickets.setText(clearText);
        executeText = language.getString("executeText");
        execute.setText(executeText);
        updateText = language.getString("updateText");
        update.setText(updateText);
        insertText = language.getString("insertText");
        insert.setText(insertText);
        removePersonText = language.getString("removePersonText");
        removeAllByPerson.setText(removePersonText);
        removeText = language.getString("removeText");
        remove.setText(removeText);
        removelowerText = language.getString("removelowerText");
        removeLower.setText(removelowerText);
        replacegreaterText = language.getString("replacegreaterText");
        replaceIfGreater.setText(replacegreaterText);
        averageTool = language.getString("averageTool");
        clearTool = language.getString("clearTool");
        executeTool = language.getString("executeTool");
        updateTool = language.getString("updateTool");
        insertTool = language.getString("insertTool");
        removePersonTool = language.getString("removePersonTool");
        removeTool = language.getString("removeTool");
        removelowerTool = language.getString("removelowerTool");

        empty = language.getString("empty");
        averagePriceText = language.getString("averagePriceText");
        exit = language.getString("exit");
        workingtext = language.getString("workingtext");
        Noconnection = language.getString("Noconnection");
        status.setText(Noconnection);
        setFontSize(status);
        replaceIfGreater.setToolTipText(replacegreaterText);
        removeLower.setToolTipText(removelowerTool);
        remove.setToolTipText(removeTool);
        removeAllByPerson.setToolTipText(removePersonTool);
        insert.setToolTipText(insertTool);
        update.setToolTipText(updateTool);
        execute.setToolTipText(executeTool);
        removeAllTickets.setToolTipText(clearTool);
        averagePrice.setToolTipText(averageTool);
        closeButton6.setText(exit);
        closeButton5.setText(exit);
        closeButton4.setText(exit);
        closeButton3.setText(exit);
        closeButton2.setText(exit);
        closeButton1.setText(exit);
        closeButton.setText(exit);
        closeButton8.setText(exit);
    }
}