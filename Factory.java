/**
 *
 * @author andreis
 */

public class Factory {
	public static Command getCommand(String comanda) {
		switch(comanda) {
			case "ls":
				return new LS();
			case "cd":
				return new CD();
			case "pwd":
				return new Pwd();
			case "cat":
				return new Cat();
			case "mkdir":
				return new Mkdir();
			case "touch":
				return new Touch();
			case "rm":
				return new RM();
		}
		return null;
	}
}
