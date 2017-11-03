package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.Rule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RuleParser {

    private FactParser factParser;

    public RuleParser() {
        factParser = new FactParser();
    }

    public boolean valid(String rawRule) {
        return  rawRule.matches("^[a-z]+\\(([A-Z]+, )*[A-Z]+\\) :- (([a-z]+\\(([A-Z]+, )*[A-Z]+\\)), )*([a-z]+\\(([A-Z]+, )*[A-Z]+\\))\\.");
    }

    public Rule parse(String rawRule) {
        String name = rawRule.substring(0, rawRule.indexOf("("));
        List<String> args = Arrays.asList(
                rawRule.substring(rawRule.indexOf("(") + 1, rawRule.indexOf(")")).split(", "));
        List<String> rawFacts = Arrays.asList(rawRule
                .replace("), ", "); ")
                .substring(rawRule.indexOf(":- ") + ":- ".length(), rawRule.indexOf("."))
                .split("; "));
        List<Fact> facts = rawFacts.stream().map(rawFact -> factParser.parse(rawFact)).collect(Collectors.toList());

        return new Rule(name, args, facts);
    }
}
