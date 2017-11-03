package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RuleTest {

    @Test
    public void testEqualsOnSameRule() throws Exception {
        Rule rule = new Rule("son",
                Arrays.asList("X", "Y"),
                Arrays.asList(
                        new Fact("male", Arrays.asList("X")),
                        new Fact("male", Arrays.asList("Y")),
                        new Fact("father", Arrays.asList("Y", "X"))));

        Assert.assertTrue(rule.equals(rule));
    }

    @Test
    public void testEqualsOnTwoEqualRules() throws Exception {
        Rule rule1 = new Rule("son",
                Arrays.asList("X", "Y"),
                Arrays.asList(
                        new Fact("male", Arrays.asList("X")),
                        new Fact("male", Arrays.asList("Y")),
                        new Fact("father", Arrays.asList("Y", "X"))));
        Rule rule2 = new Rule("son",
                Arrays.asList("X", "Y"),
                Arrays.asList(
                        new Fact("male", Arrays.asList("X")),
                        new Fact("male", Arrays.asList("Y")),
                        new Fact("father", Arrays.asList("Y", "X"))));

        Assert.assertTrue(rule1.equals(rule2));
    }

    @Test
    public void testEqualsOnTwoDifferentRules() throws Exception {
        Rule rule1 = new Rule("son",
                Arrays.asList("X", "Y"),
                Arrays.asList(
                        new Fact("male", Arrays.asList("X")),
                        new Fact("male", Arrays.asList("Y")),
                        new Fact("father", Arrays.asList("Y", "X"))));
        Rule rule2 = new Rule("daughter",
                Arrays.asList("X", "Y"),
                Arrays.asList(
                        new Fact("female", Arrays.asList("X")),
                        new Fact("female", Arrays.asList("Y")),
                        new Fact("mother", Arrays.asList("Y", "X"))));

        Assert.assertFalse(rule1.equals(rule2));
    }
}