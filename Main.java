import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author andrei
 */

public class Main {
	
	public static void main(String args[]) throws IOException {
		
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			System.setProperty("SeaGlass.JTextArea.drawLineSeparator", "false");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MyConsole c = new MyConsole();
			}
		});
		
		UserManagement x = UserManagement.getInstance();
		FileSystem y = FileSystem.getInstance();
		Subject sub = new Subject();
		new ExceptionObserver(sub);
				
		Fisier f = new Fisier("ana.java", 100);
		Fisier h = new Fisier("banana.op", 50);
		Fisier j = new Fisier("prietena.jpg", 75);
		Fisier i = new Fisier("kiwi.bin", 10);
		
		Folder d = new Folder("Iocla");
		Folder e = new Folder("Java");
		Folder g = new Folder("Toria");
		
		x.login("guest", "");
		//d.add(e);
		//d.add(g);
		//d.add(f);
		//e.add(h);
		//e.add(j);
		//g.add(i);
		//d.add(h);
	
		e.add(f);
		e.add(d);
		d.add(h);

		y.getRoot().add(f);
		y.getRoot().add(e);
		y.getRoot().add(i);
		y.getRoot().add(g);
		y.getRoot().add(d);

	}
}
