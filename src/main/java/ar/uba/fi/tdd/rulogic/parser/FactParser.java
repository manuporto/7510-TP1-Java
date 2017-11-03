package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Fact;

import java.util.Arrays;
import java.util.List;

public class FactParser {

    public boolean valid(String rawFact) {

        return rawFact.matches("^[a-z]+\\(([a-z]+, )*[a-z]+\\)\\.");
    }

    public Fact parse(String rawFact) {
        String name = rawFact.substring(0, rawFact.indexOf("("));
        List<String> args = Arrays.asList(
                rawFact.substring(rawFact.indexOf("(") + 1, rawFact.indexOf(")")).split(", "));

        return new Fact(name, args);
    }
}
