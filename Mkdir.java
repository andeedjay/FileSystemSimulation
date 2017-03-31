/**
 *
 * @author andreis
 */

public class Mkdir extends WriteCommand implements Command {

	@Override
	public void execute(Fisier f) {
		//
	}

	@Override
	public void execute(Folder d) {
		String comanda = MyConsole.getCommand();
		String comenzi[] = comanda.split(" ");
		Folder f = null;
		if(comenzi.length == 2) {
			f = new Folder(comenzi[1]);
		}
		else
			if(comenzi.length == 3) {
				if(comenzi[2].contains("r") && comenzi[2].contains("w")) {
					f = new Folder(comenzi[1], new Permisiuni(UserManagement.getCurrentUser(), true, true));
				}
				else
					if(comenzi[2].contains("r")) {
						f = new Folder(comenzi[1], new Permisiuni(UserManagement.getCurrentUser(), true, false));
					}
					else
						if(comenzi[2].contains("w")) {
							f = new Folder(comenzi[1], new Permisiuni(UserManagement.getCurrentUser(), false, true));
						}
			}
		if(f != null)
			d.add(f);
		MyConsole.setText(UserManagement.getCurrentUser().getUsername() + "@vmachine " + MyConsole.getStack().toString() + " $ ");
	}
}
