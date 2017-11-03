package ar.uba.fi.tdd.rulogic.parser;

import ar.uba.fi.tdd.rulogic.model.Fact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class FactParserTest {

    private FactParser factParser;

    @Before
    public void setUp() throws Exception{
       factParser = new FactParser();
    }

    @Test
    public void testValidWithValidFact() throws Exception {
        Assert.assertTrue(factParser.valid("male(seldon)."));
        Assert.assertTrue(factParser.valid("father(toran, arkady)."));
    }

    @Test
    public void testValidWithNonValidFacts() throws Exception {
        Assert.assertFalse(factParser.valid("male(seldon)"));
        Assert.assertFalse(factParser.valid("male(seldon."));
        Assert.assertFalse(factParser.valid("father(toran,)."));
    }

    @Test
    public void testParseWithSingleArgumentFact() throws Exception {
        Fact expected = new Fact("male", Arrays.asList("seldon"));
        Fact actual = factParser.parse("male(seldon).");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testParseWithMultipleArgumentsFact() throws Exception {
        Fact expected = new Fact("father", Arrays.asList("toran", "arkady"));
        Fact actual = factParser.parse("father(toran, arkady).");

        Assert.assertEquals(expected, actual);
    }
}