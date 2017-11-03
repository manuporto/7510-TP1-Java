package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

public class Fact {

    private String name;
    private List<String> args;

    public Fact(String name, List<String> args) {
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public List<String> getArgs() {
        return args;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fact fact = (Fact) o;

        return getName().equals(fact.getName()) && getArgs().equals(fact.getArgs());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getArgs().hashCode();
        return result;
    }
}
