package database;

public class DBGriglia {
	private final int numRighe;
	private final int numColonne;

	public DBGriglia(int numRighe, int numColonne) {
		this.numRighe = numRighe;
		this.numColonne = numColonne;
	}

	public int salvaSuDB() {
		String query = "INSERT INTO griglia (numRighe,numColonne) VALUES ( \'" + this.numRighe + "\','"
				+ this.numColonne + "')";
		try {
			return DBConnectionManager.updateQuery(query);
		} catch (Exception exc) {
			exc.printStackTrace();

			return 0;
		}
	}
}
