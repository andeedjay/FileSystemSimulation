import java.util.Arrays;

/**
 *
 * @author andreis
 */

public class Cat extends ReadCommand implements Command {

	@Override
	public void execute(Fisier f) {
		if(f.getNume().endsWith(".bin"))
			MyConsole.setText(Arrays.toString(f.getBytes()) + "\n");
		else
			MyConsole.setText(f.getBytes() + "\n");

		MyConsole.setText(UserManagement.getCurrentUser().getUsername() + "@vmachine " + MyConsole.getStack() + " $ ");
	}

	@Override
	public void execute(Folder d) {
		//
	}
	
}
