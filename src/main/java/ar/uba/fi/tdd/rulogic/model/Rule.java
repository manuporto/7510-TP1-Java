package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

public class Rule {

    private String name;
    private List<String> args;
    private List<Fact> facts;

    public Rule(String name, List<String> args, List<Fact> facts) {
        this.name = name;
        this.args = args;
        this.facts = facts;
    }

    public String getName() {
        return name;
    }

    public List<String> getArgs() {
        return args;
    }

    public List<Fact> getFacts() {
        return facts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (!getName().equals(rule.getName())) return false;
        if (!getArgs().equals(rule.getArgs())) return false;
        return getFacts().equals(rule.getFacts());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getArgs().hashCode();
        result = 31 * result + getFacts().hashCode();
        return result;
    }
}
