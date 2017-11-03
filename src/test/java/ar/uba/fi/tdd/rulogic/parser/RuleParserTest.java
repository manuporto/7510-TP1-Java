package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Fact;
import ar.uba.fi.tdd.rulogic.model.Rule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class RuleParserTest {

    private RuleParser ruleParser;
    @Before
    public void setUp() throws Exception {
        ruleParser = new RuleParser();
    }

    @Test
    public void testValidWithValidRule() throws Exception {
        Assert.assertTrue(ruleParser.valid("son(X, Y) :- male(X), male(Y), father(Y, X)."));
    }

    @Test
    public void testValidWithNonValidRule() throws Exception {
        Assert.assertFalse(ruleParser.valid("son(X,Y :- male(X), father(Y, X)."));
        Assert.assertFalse(ruleParser.valid("son(X,Y) : male(X), father(Y, X)."));
    }

    @Test
    public void testParse() throws Exception {
        Rule expected = new Rule("son",
                Arrays.asList("X", "Y"),
                Arrays.asList(
                        new Fact("male", Arrays.asList("X")),
                        new Fact("male", Arrays.asList("Y")),
                        new Fact("father", Arrays.asList("Y", "X"))));
        Rule actual = ruleParser.parse("son(X, Y) :- male(X), male(Y), father(Y, X).");

        Assert.assertEquals(expected, actual);
    }
}