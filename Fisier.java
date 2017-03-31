import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author andreis
 */

public class Fisier implements Repository {
	private String nume;
	private int dim;
	private String tip;
	private String creat;
	private byte array[] = new byte[20];
	private Vector <Permisiuni> p;
	
	public Fisier(String nume) {
		this.nume = nume;
		dim = 0;
		p = new Vector <Permisiuni> (3);
		p.add(new Permisiuni((User) UserManagement.getVector().get(0), false, false));
		p.add(new Permisiuni((User) UserManagement.getVector().get(1), true, true));
		p.add(new Permisiuni(UserManagement.getCurrentUser(), true, true));
		Date data = new Date();
		SimpleDateFormat ft = new SimpleDateFormat(" 'la' HH:mm:ss");
		creat = ft.format(data);
		if(nume.endsWith(".bin")) {
			tip = "binar";
			for(int i = 0; i < 10; i++) {
				Random r = new Random();
				int x = r.nextInt(2);
				array[i] = (byte) x;
			}
		}
		else {
			tip = "text";
			String s = "";
			for(int i = 0; i < 10; i++) {
				Random r = new Random();
				s += (char)(r.nextInt(26) + 'a');
			}
			array = s.getBytes();
		}

	}
	
	public Fisier(String nume, int dim) {
		this(nume);
		this.dim = dim;
	}
	
	public Fisier(String nume, int dim, Permisiuni p) {
		this(nume, dim);
		this.p.set(2, p);
	}
	
	@Override
	public String getNume() {
		return nume;
	}
	
	@Override
	public int getDim() {
		return dim;
	}
	
	@Override
	public void accept(Command c) {
		c.execute(this);
	}
	
	@Override
	public String toString() {
		String s = "";
		s += nume + " " + dim + " " + tip + " " + "creat" + creat + " " + p.get(2) + "\n";
		return s;
	}
	
	@Override
	public Vector getPermisiuni() {
		return p;
	}
	
	public byte[] getBytes() {
		return array;
	}
	
	@Override
	public void add(Repository r) {
		//
	}
	
	@Override
	public void remove(Repository r) {
		//
	}
	
	@Override
	public void afisare() {
		//
	}
	
	@Override
	public void detalii() {
		//
	}
	
	@Override
	public void detaliiRecursiv() {
		//
	}
}
