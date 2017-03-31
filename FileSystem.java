/**
 *
 * @author andreis
 */

public class FileSystem {
	private static final FileSystem INSTANCE = new FileSystem();
	private static Folder root;
	private static Folder folder;
	
	private FileSystem() {
		root = new Folder("/");
		folder = root;
	}
	
	public static final FileSystem getInstance() {
		return INSTANCE;
	}
	
	public static Folder getCurrentFolder() {
		return folder;
	}
	public static Folder getRootFolder(){
		return root;
	}
	
	public Folder getRoot() {
		return root;
	}
	
	public static void setCurrentFolder(Folder folder) {
		FileSystem.folder = folder;
	}
}
