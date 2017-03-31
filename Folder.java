import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author andreis
 */

public class Folder implements Repository {
	private String nume;
	private String creat;
	private double dim;
	private LinkedList <Repository> l;
	private Vector <Permisiuni> p;
	
	public Folder(String nume) {
		this.l = new LinkedList <Repository> ();
		this.nume = nume;
		dim = 0;
		p = new Vector <> (3);
		p.add(new Permisiuni((User) UserManagement.getVector().get(0), false, false));
		p.add(new Permisiuni((User) UserManagement.getVector().get(1), true, true));
		p.add(new Permisiuni(UserManagement.getCurrentUser(), true, true));
		
		Date data = new Date();
		SimpleDateFormat ft = new SimpleDateFormat(" 'pe' dd.MM.yyyy 'la' HH:mm:ss");
		creat = ft.format(data);
	}
	
	public Folder(String nume, Permisiuni p) {
		this(nume);
		this.l = new LinkedList <Repository> ();
		this.p.set(2, p);
	}
	
	@Override
	public void accept(Command c) {
		c.execute(this);
	}
	
	@Override
	public void add(Repository r) {
		if(!l.contains(r))
			l.add(r);	
	}
	
	@Override
	public void remove(Repository r) {
		l.remove(r);
	}
	
	@Override
	public String getNume() {
		return nume;
	}
	
	@Override
	public int getDim() {
		int x = 0;
		for(int i = 0; i < l.size(); i++)
			x += l.get(i).getDim();
		this.dim = x;
		return x;
	}
	
	public LinkedList getLinkedList() {
		return l;
	}
	
	@Override
	public String toString() {
		String s = new String();
		s += nume + " " + getDim() + " " + "creat" + creat + " " + p.get(2) + "\n";
		return s;
	}
	
	@Override
	public Vector getPermisiuni() {
		return p;
	}
	
	@Override
	public void afisare() {
		Iterator it = l.iterator();
		while(it.hasNext()) {
			Repository f = (Repository)it.next();
			MyConsole.setText(f.toString());
			f.afisare();
		}
	}
	@Override
	public void detalii() {
		Iterator it = l.iterator();
		while(it.hasNext()) {
			Repository f = (Repository)it.next();
			MyConsole.setText(f.getNume() + "\n");
			f.detalii();
		}
	}

	@Override
	public void detaliiRecursiv() {
		Iterator it = l.iterator();
		while(it.hasNext()) {
			Repository f = (Repository)it.next();
			MyConsole.setText(f.getNume() + "\n");
			f.detaliiRecursiv();
		}
	}
}
