import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.Date;

 public class ToDo extends JPanel {
 private static String curr_user;
 private static String curr_pass;
 public static void main(String[] args) {

  JFrame window = new JFrame("TO DO LIST");
  JPanel content = new JPanel();

  content.setLayout(new BorderLayout());
  JLabel login_instruct = new JLabel("<html><h1>ACCOUNT LOGIN</h1></html>",SwingConstants.CENTER);
  JLabel user1 = new JLabel("<html><h1>USERNAME</h1></html>");
  JLabel pass1 = new JLabel("<html><h1>PASSWORD</h1></html>");
  JTextField u1 = new JTextField("");
  JPasswordField p = new JPasswordField("");
  JLabel user2 = new JLabel("<html><h1>USERNAME</h1></html>");
  JLabel pass2 = new JLabel("<html><h1>PASSWORD</h1></html>");
  JTextField u2 = new JTextField("");
  JTextField newp1 = new JTextField("");
  JButton login = new JButton("<html><h1>LOGIN</h1></html>");
  JButton clear1 = new JButton("<html><h1>CLEAR</h1></html>");
  JButton clear2 = new JButton("<html><h1>CLEAR</h1></html>");
  JLabel newacct = new JLabel("<html><h1>CREATE AN ACCOUNT</h1></html>",SwingConstants.CENTER);
  JButton create = new JButton("<html><h1>CREATE</h1></html>");
  JLabel about = new JLabel("<html><h1>USERNAME AND PASSWORD MUST BE 5-8 LETTERS<html><h1>");
  JLabel blank1 = new JLabel("");
  JLabel blank2 = new JLabel("");
  JLabel blank3 = new JLabel("");
	JLabel blank4 = new JLabel("");
  JTextArea text = new JTextArea(50, 70);
  JButton enter = new JButton("<html><h1>ENTER</h1></html>");
  JLabel logresult1 = new JLabel("");
  JLabel logresult2 = new JLabel("");
	JLabel answer = new JLabel("");
  JLabel error = new JLabel("");
  JLabel memo_text = new JLabel("");
  JButton done = new JButton("<html><h1>I FINISHED EVERYTHING!</h1></html>");
  JLabel todo = new JLabel("<html><h1>TO DO LIST</h1></html>",SwingConstants.CENTER);

  JPanel signin = new JPanel();
  signin.setLayout(new GridLayout(5, 2));
  signin.setBackground(Color.BLUE);
  signin.add(login_instruct);
  signin.add(todo);
  signin.add(user1);
  signin.add(u1);
  signin.add(pass1);
  signin.add(p);
  signin.add(login);
  signin.add(clear1);
  signin.add(logresult1);
  content.add(signin, BorderLayout.PAGE_START);

  JPanel signup = new JPanel();
  signup.setLayout(new GridLayout(6, 2));
  signup.setBackground(Color.BLUE);
  signup.add(newacct);
  signup.add(error);
  signup.add(about);
  signup.add(blank3);
  signup.add(user2);
  signup.add(u2);
  signup.add(pass2);
  signup.add(newp1);
  signup.add(create);
  signup.add(clear2);
  signup.add(logresult2);
  content.add(signup, BorderLayout.PAGE_END);

  JPanel play = new JPanel();
  play.setLayout(new GridLayout(2,2));
  play.setBackground(Color.BLUE);
  play.add(text);
  play.add(memo_text);
  play.add(enter);
  play.add(done);
  content.add(play, BorderLayout.CENTER);
  Data.init();

  clear1.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent event) {
    u1.setText("");
    p.setText("");
   }
  });

  clear2.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent event) {
    u2.setText("");
    newp1.setText("");

   }
  });

  login.addActionListener(new ActionListener() {
   String found = "";
   String notfound = "";
   String words = "";
   public void actionPerformed(ActionEvent event) {
    String username = u1.getText();
    String password = p.getText();
    if (Data.checkUser(username, password)) {
     found = "Login succesful";
     logresult1.setText(found);
     words = Data.readFile(username, password);
     memo_text.setText("<html>"+words+"</html>");
     curr_user=username;
     curr_pass=password;
    } else {
     notfound = "Login unsuccesful.Try again.";
     logresult1.setText(notfound);
    }
   }
  });

  create.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent event) {
    String saved = "";
    String notsaved = "";
    String username = u2.getText();
    String password = newp1.getText();
    if (StoreInfo.checkInfo(username, password)) {
     saved = "Your account has been created";
     logresult2.setText(saved);
     Data.addUser(new StoreInfo(username, password));
     Data.writeTo(new StoreInfo(username, password));
    } else {
     notsaved = "Incorrect password or username. Please reenter";
     logresult2.setText(notsaved);
    }
   }
  });

  enter.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent event) {
     String memo = text.getText();
     Data.writeResults(memo, curr_user, curr_pass);
    }
  });

  done.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent event) {
     Data.removeMemo(curr_user,curr_pass);
     memo_text.setText("");
    }
  });

  window.setContentPane(content);
  window.pack();
  window.setLocation(100, 100);
  window.setVisible(true);

 }

}
