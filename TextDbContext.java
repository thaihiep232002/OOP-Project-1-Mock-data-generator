public class TextDbContext {
	private String _filenames;

	public DbSet Students;
	public DbSet RandomStudents;	
	
	public TextDbContext(String filenames) {
		String[] files = filenames.split(", ");
		Students = new DbSet((files[0].split("="))[1]);
		RandomStudents = new DbSet((files[1].split("="))[1]);
	}
}
