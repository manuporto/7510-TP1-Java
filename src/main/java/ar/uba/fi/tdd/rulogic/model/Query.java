package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

public class Query {

    private String name;
    private List<String> args;

    public Query(String name, List<String> args) {
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public List<String> getArgs() {
        return args;
    }
}
