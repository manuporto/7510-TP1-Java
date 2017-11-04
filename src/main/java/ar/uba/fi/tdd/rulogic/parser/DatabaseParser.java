package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDatabaseException;
import ar.uba.fi.tdd.rulogic.model.Database;
import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.Rule;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseParser {

    private FactParser factParser;
    private RuleParser ruleParser;

    public DatabaseParser() {
        this.factParser = new FactParser();
        this.ruleParser = new RuleParser();
    }

    public boolean valid(List<String> rawDb) {
        return !rawDb
                .stream()
                .map(statement -> this.factParser.valid(statement) || this.ruleParser.valid(statement))
                .collect(Collectors.toList())
                .contains(false);
    }

    public Database parse(List<String> rawDb) throws InvalidDatabaseException {
        List<Fact> facts = new ArrayList<>();
        List<Rule> rules = new ArrayList<>();

        if (!this.valid(rawDb)) {
            throw new InvalidDatabaseException("The selected database contains rules or facts with invalid syntax.");
        }
        rawDb.forEach(statement -> {
            if (this.factParser.valid(statement)) {
                facts.add(this.factParser.parse(statement));
            } else {
                rules.add(this.ruleParser.parse(statement));
            }
        });

        return new Database(facts, rules);
    }
}
