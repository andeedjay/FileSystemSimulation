/**
 *
 * @author andreis
 */

public class LS extends ReadCommand implements Command {
	private String s = "";
	private String args;
	
	public void parameters(String args){
		this.args = args;
	}
	
	@Override
	public void execute(Repository r) {
		r.accept((Command) this);
	}

	@Override
	public void execute(Fisier fis) {
		parameters(MyConsole.getArgs());
		if(args != null && args.contains("a"))
			s = fis.toString();
		else
			s = fis.getNume() + "\n";
		MyConsole.setText(s);
	}

	@Override
	public void execute(Folder d) {
		if(d.getLinkedList().size() == 0) {
			MyConsole.setText("");
		}
		parameters(MyConsole.getArgs());
		for(int i = 0; i < d.getLinkedList().size(); i++) {
			if(args != null && args.contains("a") && args.contains("r")) {
				d.afisare();
					break;
			}
			else if(args != null && args.contains("a")) {
				s += ((Repository)(d.getLinkedList().get(i))).toString();
			}
			else
				if(args != null && args.contains("r")) {
					d.detalii();
					break;
				}
			else
				s += ((Repository)(d.getLinkedList().get(i))).getNume() + "\n";
		}
		MyConsole.setText(s);
	}
}
