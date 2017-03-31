/**
 *
 * @author andreis
 */

public class Permisiuni {
	private boolean read;
	private boolean write;
	private User u;
	
	public Permisiuni() {
		read = false;
		write = false;
	}
	
	public Permisiuni(User u, boolean read, boolean write) {
		this.u = u;
		this.read = read;
		this.write = write;
	}
	
	@Override
	public String toString() {
		String s = "";
		s = read + " " + write + " " + u.getUsername();
		return s;
	}
	
	public boolean getRead() {
		return read;
	}
	
	public boolean getWrite() {
		return write;
	}
	
	public User getUser() {
		return u;
	}
}
