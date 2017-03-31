import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Stack;
import javax.swing.*;

/**
 *
 * @author andreis
 */

public class MyConsole extends JFrame implements KeyListener {
	private static JTextArea text1;
	private JTextField text2;
	private static JPanel panel;
	private JScrollPane pane;
	private static String argumente;
	private FileSystem fs = FileSystem.getInstance();
	private static MyStack <Folder> st;
	private static ArrayList <String> l;
	private static String comanda;
	int i;
	int j;
	
	{
		st = new MyStack();
		st.add(fs.getRoot());
		l = new ArrayList(20);
		i = l.size() - 1;
		j = 0;
	}

	public MyConsole() {
		super("My Console");
		String s = UserManagement.getCurrentUser().getUsername() + "@vmachine " + st + " $ ";
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout(0, 0));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width / 4, screenSize.height / 4, screenSize.width / 2, screenSize.height / 2);
		this.setBackground(Color.gray);
		
		text1 = new JTextArea(s);
		text1.setBackground(new Color(195, 195, 195));
		text1.setForeground(Color.BLACK);
		text1.setFont(new Font("MyFont", Font.ROMAN_BASELINE, 14));
		text1.setEditable(false);
		
		text2 = new JTextField();
		panel = new JPanel();
		pane = new JScrollPane();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		pane.setViewportView(panel);
		panel.add(text1);

		this.add(pane, BorderLayout.NORTH);
		
		text2.setEditable(true);
		text2.addKeyListener(this);

		this.add(text2, BorderLayout.SOUTH);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				text2.requestFocus();
			}
		});
		this.add(pane);
		this.show();
	}
	
	public void prepareGUI() {
		
		text1 = new JTextArea(UserManagement.getCurrentUser().getUsername() + "@vmachine " + st + " $ ");
		text1.setBackground(new Color(195, 195, 195));
		text1.setForeground(Color.BLACK);
		text1.setFont(new Font("MyFont", Font.ROMAN_BASELINE, 14));
		text1.setEditable(false);
		panel.add(text1);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public Repository cautare(String nume) {
		for(int k = 0; k < FileSystem.getCurrentFolder().getLinkedList().size(); k++) {
			 if(((Repository) FileSystem.getCurrentFolder().getLinkedList().get(k)).getNume().equals(nume)) {
				 return ((Repository) FileSystem.getCurrentFolder().getLinkedList().get(k));
			 }
		}
		return null;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		Command com;
		text1.select(Integer.MAX_VALUE, 0);
			
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			comanda = text2.getText();
			if(l.size() != 10) {
				l.add(comanda);
				i++;
			}
			else {
				l.clear();
				i = 0;
				l.add(comanda);
			}
			text1.append(comanda + "\n");
			text2.setText("");
			String comenzi[] = comanda.split(" ");
			
			com = Factory.getCommand(comenzi[0]);
			switch(comenzi[0]) {
				case "userinfo":
					if(comenzi.length == 2 && comenzi[1].contains("-POO")) {
						JList list = new JList();
						DefaultListModel model = new DefaultListModel();
						list.setModel(model);
						panel.add(list);
						model.addElement(UserManagement.getCurrentUser().getUsername());
						model.addElement(UserManagement.getCurrentUser().getParola());
						model.addElement(UserManagement.getCurrentUser().getNume());
						model.addElement(UserManagement.getCurrentUser().getPrenume());
						model.addElement(UserManagement.getCurrentUser().getCreat());
						model.addElement(UserManagement.getCurrentUser().getLogat());
						prepareGUI();
					}
					else {
						text1.append(UserManagement.getInstance().userinfo(UserManagement.getCurrentUser()));
						text1.append(UserManagement.getCurrentUser().getUsername() + "@vmachine " + FileSystem.getCurrentFolder().getNume() + " $ ");
					}
					break;
				
				case "newuser":
					String realinfo[] = new String[comenzi.length - 1];
					for(int k = 0; k < comenzi.length - 1; k++)
						realinfo[k] = comenzi[k + 1];
					text1.append(UserManagement.getInstance().newUser(realinfo));
					text1.append(UserManagement.getCurrentUser().getUsername() + "@vmachine " + FileSystem.getCurrentFolder().getNume() + " $ ");
					break;
					
				case "login":
					st.clear();
					st.add(fs.getRoot());
					text1.append(UserManagement.getInstance().login(comenzi[1], comenzi[2]));
					FileSystem.setCurrentFolder(fs.getRoot());
					text1.append(UserManagement.getCurrentUser().getUsername() + "@vmachine " + FileSystem.getCurrentFolder().getNume() + " $ ");
					break;
					
				case "logout":
					st.clear();
					st.add(fs.getRoot());
					text1.append(UserManagement.getInstance().logout());
					FileSystem.setCurrentFolder(fs.getRoot());
					text1.append(UserManagement.getCurrentUser().getUsername() + "@vmachine " + FileSystem.getCurrentFolder().getNume() + " $ ");
					break;
				
				case "echo":
					String s = "";
					
					for(int k = 1; k < comenzi.length - 1; k++)
						s += comenzi[k] + " ";
					if(comenzi[comenzi.length - 1].contains("-POO"))
						JOptionPane.showMessageDialog(this, s);
					else
						text1.append(s + comenzi[comenzi.length - 1] + "\n");
					text1.append(UserManagement.getCurrentUser().getUsername() + "@vmachine " + FileSystem.getCurrentFolder().getNume() + " $ ");
					break;
					
				case "clear":
					text1.setText(UserManagement.getCurrentUser().getUsername() + "@vmachine " + st.toString() + "$ ");
					break;
					
				case "history":
					for(int k = 0; k < l.size(); k++)
						text1.append(l.get(k) + "\n");
					text1.append(UserManagement.getCurrentUser().getUsername() + "@vmachine " + FileSystem.getCurrentFolder().getNume() + " $ ");
					break;
				
				case "ls":
					if(comenzi.length == 3) {
						argumente = comenzi[2];
					}
					if(comenzi.length == 1) {
						com.execute((Repository)FileSystem.getCurrentFolder());
					}
					else 
						if(cautare(comenzi[1]) != null) {
							com.execute(cautare(comenzi[1]));
						}
						else {
							Date data = new Date();
							SimpleDateFormat ft = new SimpleDateFormat(" 'pe' dd.MM.yyyy 'la' HH:mm:ss");
							Subject.setState(new MyInvalidPathException(FileSystem.getCurrentFolder().getNume() + " " + UserManagement.getCurrentUser().getUsername() + ft.format(data)));
						}
					text1.append(UserManagement.getCurrentUser().getUsername() + "@vmachine " + st.toString() + " $ ");
					break;

				case "cd":
					if(comenzi.length == 1 || comenzi[1].equals("/") || comenzi[1].equals("root")) {
						com.execute((Repository)fs.getRoot());
						st.clear();
						st.add(fs.getRoot());
					}
					else
						if(comenzi[1].contains("..")) {
							String puncte[] = comenzi[1].split("/");
							for(int k = 1; k < puncte.length; k++) {
								if(st.size() > 0)
									st.remove(st.lastElement());
								else {
									Date data = new Date();
									SimpleDateFormat ft = new SimpleDateFormat(" 'pe' dd.MM.yyyy 'la' HH:mm:ss");
									Subject.setState(new MyInvalidPathException(FileSystem.getCurrentFolder().getNume() + " " + UserManagement.getCurrentUser().getUsername() + ft.format(data)));
								}
							}
							st.remove(st.lastElement());
							com.execute((Repository)st.lastElement());
							st.remove(st.lastElement());
							}
							else
								if(comenzi[1].contains("/")) {
									String foldere[] = comenzi[1].split("/");
									for(int k = 0; k < foldere.length; k++) {
										if(cautare(foldere[k]) != null) {
											com.execute((Repository)cautare(foldere[k]));
										}
										else {
											Date data = new Date();
											SimpleDateFormat ft = new SimpleDateFormat(" 'pe' dd.MM.yyyy 'la' HH:mm:ss");
											Subject.setState(new MyInvalidPathException(FileSystem.getCurrentFolder().getNume() + " " + UserManagement.getCurrentUser().getUsername() + ft.format(data)));
										}
									}
								}
							else
								if(cautare(comenzi[1]) != null){
									com.execute(cautare(comenzi[1]));	
								}
								else {
									Date data = new Date();
									SimpleDateFormat ft = new SimpleDateFormat(" 'pe' dd.MM.yyyy 'la' HH:mm:ss");
									Subject.setState(new MyInvalidPathException(FileSystem.getCurrentFolder().getNume() + " " + UserManagement.getCurrentUser().getUsername() + ft.format(data)));
								}
					text1.append(UserManagement.getCurrentUser().getUsername() + "@vmachine " + st.toString() + " $ ");
					break;
				
				case "cat":
					if(cautare(comenzi[1]) != null) {
						com.execute((Repository)cautare(comenzi[1]));
					}
					break;
					
				case "pwd":
					com.execute((Repository)FileSystem.getCurrentFolder());
					break;
					
				case "touch":
					if(cautare(comenzi[1]) == null)
						com.execute((Repository)FileSystem.getCurrentFolder());
					break;
					
				case "mkdir":
					if(cautare(comenzi[1]) == null)
						com.execute((Repository)FileSystem.getCurrentFolder());
					break;
					
				case "rm":
					if(cautare(comenzi[1]) != null && comenzi.length == 2)
						com.execute(cautare(comenzi[1]));
					else
						if(cautare(comenzi[1]) != null && comenzi.length == 3) {
							argumente = comenzi[2];
							com.execute(cautare(comenzi[1]));
						}
					break;
					
				default:
					text1.append("Comanda nerecunoscuta de sistem" + "\n");
					text1.append(UserManagement.getCurrentUser().getUsername() + "@vmachine " + st.toString() + "$ ");
					break;
			}
		}
			if(e.getKeyCode() == KeyEvent.VK_UP && i >= 0) {
				text2.setText(l.get(i));
				i--;

			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN && j < l.size() - 1) {
				j++;
				text2.setText(l.get(j));
			}
		}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	public static String getArgs() {
		return argumente;
	}
	
	public static void setText(String s) {
		text1.append(s);
	}
	
	public static JTextArea getText() {
		return text1;
	}
	
	public static JPanel getJPanel() {
		return panel;
	}
	
	public static String getCommand() {
		return comanda;
	}
	
	public static Stack getStack() {
		return st;
	}
}

