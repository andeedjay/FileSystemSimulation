/**
 *
 * @author andreis
 */

public class RM extends WriteCommand implements Command {
	private String args;
	
	public void parameters(String args) {
		this.args = args;
	}
	
	@Override
	public void execute(Fisier f) {
		FileSystem.getCurrentFolder().getLinkedList().remove(f);
	}

	@Override
	public void execute(Folder d) {
		parameters(MyConsole.getArgs());
		if(d.getLinkedList().size() == 0)
			FileSystem.getCurrentFolder().remove(d);
		else 
			if(args != null && args.contains("r")) {
				d.getLinkedList().clear();
				System.out.println(d.getLinkedList());
				FileSystem.getCurrentFolder().remove(d);
		}
		MyConsole.setText(UserManagement.getCurrentUser().getUsername() + "@vmachine " + MyConsole.getStack() + "$ ");
	}
	
}
