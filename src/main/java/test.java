import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.io.*;
import java.sql.*;
import java.io.File;


public class test {

    private JPanel panel1;
    private JButton connectButton;
    private JButton testConnactionButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPasswordField passwordField1;

    public test() {
        JFrame frame = new JFrame("test");
        frame.setSize(8900,5000);
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    File myObj = new File("table_file.txt");
                    PrintWriter writer = new PrintWriter("table_file.txt", "UTF-8");
                    Class.forName("oracle.jdbc.driver.OracleDriver");



                    Connection con= DriverManager.getConnection("jdbc:oracle:thin:@192.168.30.14:1521:TEST",
                            "dia",
                            "admin");


                    Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);


                    ResultSet rs=stmt.executeQuery("select * from ad_user");

                    ResultSetMetaData rsmd = rs.getMetaData();

                    int columnsNumber = rsmd.getColumnCount();
                    int counter = 1;
                    while (rs.next()) {

                        for(int i = 1 ; i <= columnsNumber; i++){

                            System.out.print(rs.getString(i) + " ;"); //Print one element of a row
                            writer.print(rs.getString(i) + " ;");
                        }

                        System.out.println();//Move to the next line to print the next row.
                        writer.println();
                    }

                }catch(IOException | ClassNotFoundException exx)
                {
                    exx.printStackTrace();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                testwo ts2 = new testwo();
                frame.setVisible(false);
            }
        });
        testConnactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:oracle:thin:@"+textField3.getText()+":"+textField2.getText()+":"+textField4.getText(), textField1.getText(), passwordField1.getText());
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from ad_user");
                    if(rs.next()){
                        JOptionPane.showMessageDialog(frame,"Connection Established");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,ex.getMessage());
                    //lblMessage.setText(e.getMessage());
                    //lblMessage.setTextFill(Color.RED);
                    System.out.println(ex);
                }
            }
        });
    }
}
