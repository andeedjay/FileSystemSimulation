/**
 *
 * @author andreis
 */

public class CD extends ReadCommand implements Command {

	@Override
	public void execute(Fisier f) {
		
	}

	@Override
	public void execute(Folder d) {
		FileSystem.setCurrentFolder(d);
		MyConsole.getStack().add(d);
	}
	
}
