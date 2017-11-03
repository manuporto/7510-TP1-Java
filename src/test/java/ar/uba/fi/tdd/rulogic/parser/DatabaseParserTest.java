package ar.uba.fi.tdd.rulogic.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DatabaseParserTest {

    private DatabaseParser databaseParser;
    private List<String> validDatabase;
    private List<String> nonValidDatabase1;
    private List<String> nonValidDatabase2;

    @Before
    public void setUp() throws Exception {
        databaseParser = new DatabaseParser();
        validDatabase = Arrays.asList(
                "male(seldon).",
                "male(toran).",
                "female(arkady).",
                "father(toran, arkady).",
                "daughter(X, Y) :- female(X), father(Y, X)."
        );
        nonValidDatabase1 = Arrays.asList(
                "male(seldon.",
                "male(toran).",
                "female(arkady).",
                "father(toran, arkady).",
                "daughter(X, Y) :- female(X), father(Y, X)."
        );
        nonValidDatabase2 = Arrays.asList(
                "male(seldon).",
                "male(toran).",
                "female(arkady).",
                "father(toran, arkady).",
                "daughter(X, Y) :- female(X) father(Y, X)."
        );
    }

    @Test
    public void testValidWithValidDatabase() throws Exception {
        Assert.assertTrue(databaseParser.valid(validDatabase));
    }

    @Test
    public void testValidWithNonValidDatabase() throws Exception {
        Assert.assertFalse(databaseParser.valid(nonValidDatabase1));
        Assert.assertFalse(databaseParser.valid(nonValidDatabase2));
    }
}