/**
 *
 * @author andreis
 */

public abstract class ReadCommand {
	public void execute(Repository r) {
		for(int i = 0; i < r.getPermisiuni().size(); i++)
			if(((Permisiuni)r.getPermisiuni().get(i)).getRead() == true && ((Permisiuni)r.getPermisiuni().get(i)).getUser().getNume().equals(UserManagement.getCurrentUser().getNume())) {
				r.accept((Command) this);
				return;
			}
	}
}
