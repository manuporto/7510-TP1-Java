package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FactTest {

    @Test
    public void testEqualsOnSameFact() throws Exception {
        Fact fact1 = new Fact("male", Arrays.asList("seldon"));

        Assert.assertTrue(fact1.equals(fact1));
    }

    @Test
    public void testEqualsOnTwoEqualFacts() throws Exception {
        Fact fact1 = new Fact("male", Arrays.asList("seldon"));
        Fact fact2 = new Fact("male", Arrays.asList("seldon"));

        Assert.assertTrue(fact1.equals(fact2));
    }

    @Test
    public void testEqualsOnTwoDifferentFacts() throws Exception {
        Fact fact1 = new Fact("male", Arrays.asList("seldon"));
        Fact fact2 = new Fact("female", Arrays.asList("arkady"));

        Assert.assertFalse(fact1.equals(fact2));
    }
}