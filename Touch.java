/**
 *
 * @author andreis
 */

public class Touch extends WriteCommand implements Command {

	@Override
	public void execute(Fisier f) {
		//
	}
	
	@Override
	public void execute(Folder d) {
		String comanda = MyConsole.getCommand();
		String comenzi[] = comanda.split(" ");
		Fisier f = null;
		if(comenzi.length == 2) {
			f = new Fisier(comenzi[1]);
		}
			else
				if(comenzi.length == 3) {
					f = new Fisier(comenzi[1], Integer.parseInt(comenzi[2]));
				}
				else
					if(comenzi.length == 4) {
						if(comenzi[3].contains("r") && comenzi[3].contains("w")) {
							f = new Fisier(comenzi[1], Integer.parseInt(comenzi[2]), new Permisiuni(UserManagement.getCurrentUser(), true, true));
							System.out.println(f);
						}
						else
							if(comenzi[3].contains("r")) {
								f = new Fisier(comenzi[1], Integer.parseInt(comenzi[2]), new Permisiuni(UserManagement.getCurrentUser(), true, false));
								System.out.println(f);
							}
							else
								if(comenzi[3].contains("w")) {
									f = new Fisier(comenzi[1], Integer.parseInt(comenzi[2]), new Permisiuni(UserManagement.getCurrentUser(), false, true));
									System.out.println(f);
							}
					}
		if(f != null)
			d.add(f);
		MyConsole.setText(UserManagement.getCurrentUser().getUsername() + "@vmachine " + MyConsole.getStack() + " $ ");
	}
}
