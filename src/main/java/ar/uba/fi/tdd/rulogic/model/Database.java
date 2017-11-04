package ar.uba.fi.tdd.rulogic.model;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private Map<String, String> zipToMap(List<String> keys, List<String> values) {
        return IntStream.range(0, keys.size()).boxed()
                .collect(Collectors.toMap(keys::get, values::get));
    }

    private Rule findRule(String name) {
        List<Rule> mRules = this.rules.stream().filter(rule -> rule.getName().equals(name))
                .collect(Collectors.toList());
        if (mRules.size() == 0) return null;
        return mRules.get(0);
    }

    private boolean factExists(Query query) {
        return this.facts.contains(new Fact(query.getName(), query.getArgs()));
    }

    private boolean ruleExists(Query query) {
        Rule matchingRule = this.findRule(query.getName());
        if (matchingRule == null) return false;
        Map<String, String> mappedArgs = this.zipToMap(matchingRule.getArgs(), query.getArgs());
        List<Query> queries = matchingRule.getFacts().stream().map(fact -> {
            List<String> factArgs = fact.getArgs();
            factArgs.retainAll(matchingRule.getArgs());
            List<String> matchedArgs = factArgs.stream().map(arg -> mappedArgs.get(arg)).collect(Collectors.toList());
            return new Query(fact.getName(), matchedArgs);
        }).collect(Collectors.toList());
        return !queries.stream().map(q -> this.factExists(q)).collect(Collectors.toList()).contains(false);
    }

    public boolean evaluateQuery(Query query) {
        return factExists(query) || ruleExists(query);
    }
}
