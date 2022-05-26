import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class test extends JFrame{

    private JPanel panel1;
    private JButton connectButton;
    private JButton testConnactionButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JPasswordField passwordField1;

    public test() {
        setSize(8900, 5000);
        setContentPane(this.panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@"+textField3.getText()+":"+textField2.getText()+":"+textField4.getText(),
                            textField1.getText(),
                            passwordField1.getText());
                } catch (ClassNotFoundException exx) {
                    exx.printStackTrace();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                testwo ts2 = new testwo();
                test.this.dispose();
            }
        });
    }
}
