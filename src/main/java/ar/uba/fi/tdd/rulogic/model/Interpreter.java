package ar.uba.fi.tdd.rulogic.model;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDatabaseException;
import ar.uba.fi.tdd.rulogic.exceptions.InvalidQueryException;
import ar.uba.fi.tdd.rulogic.parser.DatabaseParser;
import ar.uba.fi.tdd.rulogic.parser.QueryParser;

import java.util.List;

public class Interpreter {

	private Database db;
	private QueryParser queryParser;

    public Interpreter(List<String> rawDb) throws InvalidDatabaseException {
        DatabaseParser databaseParser = new DatabaseParser();
        this.queryParser = new QueryParser();
        this.db = databaseParser.parse(rawDb);
    }

    public boolean answer(String rawQuery) throws InvalidQueryException {
		if (!this.queryParser.valid(rawQuery)) {
		    throw new InvalidQueryException("Invalid query syntax.");
        }
        return this.db.evaluateQuery(this.queryParser.parse(rawQuery));
	}

}
