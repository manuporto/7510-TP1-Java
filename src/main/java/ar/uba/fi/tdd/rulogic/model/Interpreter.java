package ar.uba.fi.tdd.rulogic.model;

import ar.uba.fi.tdd.rulogic.parser.DatabaseParser;
import ar.uba.fi.tdd.rulogic.parser.QueryParser;

import java.util.List;

public class Interpreter {

	private Database db;
	private QueryParser queryParser;

    public Interpreter(List<String> rawDb) {
        DatabaseParser databaseParser = new DatabaseParser();
        this.queryParser = new QueryParser();
        this.db = databaseParser.parse(rawDb);
    }

    public boolean answer(String rawQuery) {
		return this.db.evaluateQuery(this.queryParser.parse(rawQuery));
	}

}
