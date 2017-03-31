import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author a
 */

public class UserManagement {
	
	private static final UserManagement INSTANCE = new UserManagement();
	private static User u;
	private User root;
	private User guest;
	private static Vector <User> v;
	
	{
		v = new Vector <> ();
	}
	
	private UserManagement() {
		root = new User("root", "student", "Global", "Elite");
		guest = new User("guest", "", "Silver", "One");
		guest.setLogat(guest.getCreat());
		v.add(guest);
		v.add(root);
		this.u = root;
	}
	
	public static final UserManagement getInstance() {
		return INSTANCE;
	}
	
	public void add(User u) {
		v.add(u);
	}
	
	public static Vector getVector() {
		return v;
	}
	
	public static User getCurrentUser() {
		return u;
	}
	
	public String newUser(String...args) {
		User r;
		switch(args.length) {
			case 1:
				r = new User(args[0], "", "", "");
				this.add(r);
				break;
			case 2:
				r = new User(args[0], args[1], "", "");
				this.add(r);
				break;
			case 3:
				r = new User(args[0], args[1], args[2], "");
				this.add(r);
				break;
			case 4:
				r = new User(args[0], args[1], args[2], args[3]);
				this.add(r);
				break;
			default:
				return "Trebuie sa introduceti cel putin numele utilizatorului" + "\n";
		}
		FileSystem.getRootFolder().getPermisiuni().add(new Permisiuni(r, true, true));
		return "Ati creat cu succes un nou utilizator" + "\n" + "cu detaliile " + r.toString();
	}
	
	public String userinfo(User u) {
		return u.toString();
	}
	
	public String login(String nume, String parola) {

		boolean gasit = false;
		int index = 0;

		for(int i = 0; i < v.size(); i++)
			if(v.get(i).getUsername().equals(nume) && v.get(i).getParola().equals(parola)) {
				gasit = true;
				index = i;
			}
		if(gasit == true)
		{
			this.u = v.get(index);
			Date data = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("'pe' dd.MM.yyyy 'la' HH:mm:ss");
			this.u.setLogat(ft.format(data));
			return ("Welcome user " + u.getUsername() + " to the System" + "\n");
		}	
		else
			return ("Ati introdus gresit userul sau parola" + "\n");
	}
	
	public String login(User u) {
		boolean gasit = false;
		int index = 0;

		for(int i = 0; i < v.size(); i++)
			if(v.get(i).getUsername().equals(u.getUsername()) && v.get(i).getParola().equals(u.getParola())) {
				gasit = true;
				index = i;
			}
		if(gasit == true)
		{
			this.u = v.get(index);
			Date data = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("'pe' dd.MM.yyyy 'la' HH:mm:ss");
			this.u.setLogat(ft.format(data));
			return ("Welcome user " + u.getUsername() + " to the System" + "\n");
		}	
		else
			return ("Ati introdus gresit userul sau parola" + "\n");
	}
	
	public String logout() {
		this.u = guest;
		UserManagement.getInstance().login(guest);
		return "V-ati delogat cu succes din sistem" + "\n";
	}
}
