package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Query;

import java.util.Arrays;
import java.util.List;

public class QueryParser {

    public boolean valid(String rawQuery) {
        return true;
    }

    public Query parse(String rawQuery) {
        String name = rawQuery.substring(0, rawQuery.indexOf("("));
        List<String> args = Arrays.asList(
                rawQuery.substring(rawQuery.indexOf("(") + 1, rawQuery.indexOf(")")).split(", "));

        return new Query(name, args);
    }
}
