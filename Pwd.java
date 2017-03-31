/**
 *
 * @author andreis
 */

public class Pwd extends ReadCommand implements Command {

	@Override
	public void execute(Fisier f) {
		//
	}

	@Override
	public void execute(Folder d) {
		int n = 0;
		for(int i = 0; i < MyConsole.getStack().size(); i++)
			n += ((Folder)MyConsole.getStack().get(i)).getNume().length();
		if(MyConsole.getStack().size() == 0) {
			MyConsole.setText(UserManagement.getCurrentUser().getUsername() + "@vmachine " + MyConsole.getStack() + " $ ");
		}
		else {
			for(int i = 0; i < MyConsole.getStack().size(); i++)
				MyConsole.setText(((Folder)MyConsole.getStack().get(i)).getNume() + " / ");
			MyConsole.setText("\n");
			MyConsole.setText(UserManagement.getCurrentUser().getUsername() + "@vmachine " + MyConsole.getStack() + " $ ");
		}
	}
}
