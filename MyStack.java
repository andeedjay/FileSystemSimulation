import java.util.Stack;

/**
 *
 * @author andreis
 * @param <T>
 */

public class MyStack <T> extends Stack {
	
	MyStack() {
		super();
	}
	
	@Override
	public String toString() {
		String s = ((Folder)(this.get(0))).getNume();
		for(int i = 1; i < this.size(); i++)
			s += ((Folder)this.get(i)).getNume() + '/';
		return s;
	}
}
