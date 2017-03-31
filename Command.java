/**
 *
 * @author andreis
 */

public interface Command {
	public void execute(Repository r);
	public void execute(Fisier f);
	public void execute(Folder d);
}
