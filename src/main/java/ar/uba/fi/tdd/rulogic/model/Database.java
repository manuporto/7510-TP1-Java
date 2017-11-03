package ar.uba.fi.tdd.rulogic.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Database {

    private List<Fact> facts;
    private Set<String> factsNames;
    private List<Rule> rules;
    private Set<String> rulesNames;

    public Database(List<Fact> facts, List<Rule> rules) {
        this.facts = facts;
        this.rules = rules;

        this.factsNames = this.facts.stream().map(fact -> fact.getName()).collect(Collectors.toSet());
        this.rulesNames = this.rules.stream().map(rule -> rule.getName()).collect(Collectors.toSet());
    }

    private boolean factExists(Query query) {
        return this.facts.contains(new Fact(query.getName(), query.getArgs()));
    }

    private boolean ruleExists(Query query) {
        return false;
    }

    public boolean evaluateQuery(Query query) {
        return factExists(query) || ruleExists(query);
    }
}
