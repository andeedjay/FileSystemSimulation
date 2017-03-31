import java.util.Vector;

/**
 *
 * @author andreis
 */

public interface Repository {
	public void accept(Command c);
	@Override
	public String toString();
	public int getDim();
	public String getNume();
	public void add(Repository r);
	public void remove(Repository r);
	public void afisare();
	public void detalii();
	public void detaliiRecursiv();
	public Vector getPermisiuni();
}
