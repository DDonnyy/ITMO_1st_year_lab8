package Utility;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;


public class LoginWindow extends JFrame implements Localizeable {
    JTextField loginField = new JTextField();
    JLabel logText = new JLabel();
    JLabel pasText = new JLabel();
    JPasswordField jPasswordField;
    JLabel enterText = new JLabel();
    JLabel status = new JLabel();
    String enter;
    String registerin;
    String Noconnection;
    String wrongData;
    String noDash; //НИКАКИХ ТИРЕ
    String noUser;
    String userExist;
    JPanel jPanel1 = new JPanel();
    SwingCommands.MyBar jMenuBar = new SwingCommands.MyBar(15);
    SwingCommands.CustomButton login = new SwingCommands.CustomButton(0, 0, 180, 40, enter, 20, 0);
    SwingCommands.CustomButton register = new SwingCommands.CustomButton(248, 0, 180, 40, registerin, 20, 0);
    public LoginWindow() throws InterruptedException, IOException {
        ReceiverAndCanvasChanger receiverAndCanvasChanger = new ReceiverAndCanvasChanger();
        LoginWindow loginWindow = this;
        LocaleController.regist(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setContentPane(new SwingCommands.Background("images\\logreg.png"));
        ImageIcon imageIcon = new ImageIcon("images\\panel2.png");
        ImageIcon wrong = new ImageIcon("images\\panelwrong.png");
        //Font font = new Font("Century Gothic", Font.PLAIN, 15);

        createLocale();


        jPanel1.setLayout(null);
        jPanel1.setSize(460, 100);
        jPanel1.setLocation(60, 130);
        jPanel1.setOpaque(false);


        JLabel panel1 = new JLabel(imageIcon);
        panel1.setSize(452, 38);
        panel1.setLocation(0, 0);


        JLabel panel2 = new JLabel(imageIcon);
        panel2.setSize(452, 38);
        panel2.setLocation(0, 50);


        loginField.setSize(300, 32);
        loginField.setLocation(140, 2);
        loginField.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        loginField.setBorder(null);

        logText.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        logText.setLocation(20, 0);
        logText.setSize(100, 36);

        pasText.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        pasText.setLocation(20, 50);
        pasText.setSize(100, 36);

        jPasswordField = new JPasswordField();
        jPasswordField.setBorder(null);
        jPasswordField.setEchoChar('\u2663');
        jPasswordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        jPasswordField.setSize(300, 32);
        jPasswordField.setLocation(140, 52);

        jPanel1.add(jPasswordField);
        jPanel1.add(loginField);
        jPanel1.add(logText);
        jPanel1.add(pasText);
        jPanel1.add(panel1);
        jPanel1.add(panel2);


        JLabel enterTextBack = new JLabel(new ImageIcon(SwingCommands.getScaledImage(new ImageIcon("images\\panel.png").getImage(), 120, 40)));
        enterTextBack.setSize(120, 40);
        enterTextBack.setLocation(60, 80);


        enterText.setSize(120, 40);
        enterText.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        enterText.setLocation(90, 80);


        status.setSize(200, 40);
        status.setFont(new Font("Century Gothic", Font.BOLD, 20));
        MainWindow.setFontSize(status);
        status.setLocation(250, 80);
        status.setForeground(Color.RED);

        JPanel jPanel2 = new JPanel();
        jPanel2.setOpaque(false);
        jPanel2.setLayout(null);
        jPanel2.setSize(430, 45);
        jPanel2.setLocation(72, 230);

        jPanel2.add(login);
        jPanel2.add(register);

        setJMenuBar(jMenuBar);
        add(jPanel1);
        add(enterText);
        add(enterText);
        add(jPanel2);
        add(status);

        Toolkit.getDefaultToolkit().sync();


        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingCommands.threadsForEverybody.execute(new Thread(() -> {
                    checkConnection(status);
                    String login;
                    String password;
                    String key;
                    login = loginField.getText();
                    password = jPasswordField.getText();
                    if (!ClientSender.serverisconnected) {
                        status.setText(Noconnection);
                        status.setVisible(true);
                        MainWindow.setFontSize(status);
                    } else if (login.equals("") || password.equals("")) {
                        status.setText(wrongData);
                        status.setVisible(true);
                        MainWindow.setFontSize(status);
                        setWrong(panel1, panel2, wrong, imageIcon);

                    } else if (login.contains("-") || password.contains("-")) {
                        status.setText(noDash);
                        status.setVisible(true);
                        MainWindow.setFontSize(status);
                        setWrong(panel1, panel2, wrong, imageIcon);
                    } else {
                        try {
                            String logpas = "login-" + login + "-" + password;
                            ClientSender.send(logpas);
                            ReceiverAndCanvasChanger.receivereg();
                            key = receiverAndCanvasChanger.getAnswer();
                            ReceiverAndCanvasChanger.result.set(null);
                            String[] kek = key.split("-");
                            key = kek[0];
                            if (key.equals("yes")) {
                                status.setVisible(false);
                                MainWindow mainWindow = new MainWindow(login, kek[1],loginWindow);
                                loginWindow.setVisible(false);

                            } else if (key.equals("no")) {
                                status.setText(noUser);
                                MainWindow.setFontSize(status);
                                status.setVisible(true);
                                setWrong(panel1, panel2, wrong, imageIcon);
                            }
                        } catch (IOException ex) {
                            status.setText(Noconnection);
                            MainWindow.setFontSize(status);
                            status.setVisible(true);
                            checkConnection(status);
                        }
                    }
                }));

            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingCommands.threadsForEverybody.execute(
                        new Thread(() -> {
                            checkConnection(status);
                            String login;
                            String password;
                            String key;
                            login = loginField.getText();
                            password = jPasswordField.getText();
                            if (!ClientSender.serverisconnected) {
                                status.setText(Noconnection);
                                MainWindow.setFontSize(status);
                                status.setVisible(true);
                            } else if (login.equals("") || password.equals("")) {
                                status.setText(wrongData);
                                MainWindow.setFontSize(status);
                                status.setVisible(true);
                                setWrong(panel1, panel2, wrong, imageIcon);
                            } else if (login.contains("-") || password.contains("-")) {
                                status.setText(noDash);
                                MainWindow.setFontSize(status);
                                status.setVisible(true);
                                setWrong(panel1, panel2, wrong, imageIcon);
                            } else {
                                try {
                                    String logpas = "reg-" + login + "-" + password;
                                    ClientSender.send(logpas);
                                    ReceiverAndCanvasChanger.receivereg();
                                    key = receiverAndCanvasChanger.getAnswer();
                                    ReceiverAndCanvasChanger.result.set(null);
                                    String[] kek = key.split("-");
                                    key = kek[0];
                                    if (key.equals("yes")) {
                                        status.setVisible(false);
                                        MainWindow mainWindow = new MainWindow(login, kek[1],loginWindow);
                                        loginWindow.setVisible(false);

                                    } else if (key.equals("no")) {
                                        status.setText(userExist);
                                        MainWindow.setFontSize(status);
                                        status.setVisible(true);
                                        setWrong(panel1, panel2, wrong, imageIcon);
                                    }
                                } catch (IOException ex) {
                                    //НЕТ ПОДКЛЮЧНИЯ К СЕРВЕРУ,ВЫВЕСТИ КРАСНЫМ НА ЭКРАНЧИК
                                    ex.printStackTrace();
                                }
                            }
                        }));
            }
        });
        setBounds(dimension.width / 2 - 300, dimension.height / 2 - 200, 600, 400);
        setLayout(null);
        setTitle("Login");
        setResizable(false);
        checkConnection(status);
//        loginField.setText("admin");
//        jPasswordField.setText("admin");
  //      Thread.sleep(1500);

        //login.doClick();
    }


    public static void checkConnection(JLabel jLabel) {
        SwingCommands.threadsForEverybody.execute(
                new Thread(() -> {
                    while (!ClientSender.serverisconnected) {
                        try {
                            Thread.sleep(1000);
                            ClientSender.tryToConnect();
                            jLabel.setVisible(false);

                        } catch (InterruptedException | IOException e) {
                        }
                    }
                }));
    }

    public static void setWrong(JLabel jLabel1, JLabel jLabel2, ImageIcon wrong, ImageIcon notWrong) {
        SwingCommands.threadsForEverybody.execute(
                new Thread(() -> {
                    jLabel1.setIcon(wrong);
                    jLabel2.setIcon(wrong);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    jLabel1.setIcon(notWrong);
                    jLabel2.setIcon(notWrong);

                }));

    }

    @Override
    public void createLocale() {
        ResourceBundle language = LocaleController.getResourceBundle();
        loginField.setUI(new SwingCommands.HintTextFieldUI(language.getString("loginField"), true));
        logText.setText(language.getString("logText"));
        pasText.setText(language.getString("pasText"));
        enterText.setText(language.getString("enterText"));
        enter = language.getString("enter");
        login.setText(enter);
        registerin = language.getString("registerin");
        register.setText(registerin);
        Noconnection = language.getString("Noconnection");
        wrongData = language.getString("wrongData");
        noDash = language.getString("noDash"); //НИКАКИХ ТИРЕ
        noUser = language.getString("noUser");
        userExist = language.getString("userExist");
        loginField.setBorder(null);

    }

}

