/**
 *
 * @author andreis
 */

public abstract class WriteCommand {
	public void execute(Repository r) {
		for(int i = 0; i < r.getPermisiuni().size(); i++)
			if(((Permisiuni)r.getPermisiuni().get(i)).getWrite() == true && ((Permisiuni)r.getPermisiuni().get(i)).getUser().getNume().equals(UserManagement.getCurrentUser().getNume())) {
				r.accept((Command) this);
				return;
			}
	}
}