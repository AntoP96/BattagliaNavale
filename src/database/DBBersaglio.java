package database;

public class DBBersaglio {
	private final int numRiga;
	private final int numColonna;

	public DBBersaglio(int numRiga, int numColonna) {
		this.numRiga = numRiga;
		this.numColonna = numColonna;
	}

	public int salvaSuDB() {
		String query = "INSERT INTO bersaglio (numRiga,numColonna) VALUES ( \'" + this.numRiga + "\','"
				+ this.numColonna + "')";
		try {
			return DBConnectionManager.updateQuery(query);
		} catch (Exception exc) {
			exc.printStackTrace();

			return 0;
		}
	}
}
