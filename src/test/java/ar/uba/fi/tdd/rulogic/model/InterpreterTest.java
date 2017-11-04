package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.List;

public class InterpreterTest {

	private List<String> validDb;
	private Interpreter interpreter;
	@Before
	public void setUp() throws Exception {
	    this.validDb = Arrays.asList(
                "varon(juan).",
                "varon(pepe).",
                "varon(hector).",
                "varon(roberto).",
                "varon(alejandro).",
                "mujer(maria).",
                "mujer(cecilia).",
                "padre(juan, pepe).",
                "padre(juan, pepa).",
                "padre(hector, maria).",
                "padre(roberto, alejandro).",
                "padre(roberto, cecilia).",
                "hijo(X, Y) :- varon(X), padre(Y, X).",
                "hija(X, Y) :- mujer(X), padre(Y, X)."
        );
	    this.interpreter = new Interpreter(this.validDb);
	}


	@Test
	public void testWithExistingFacts() throws Exception {
	    Assert.assertTrue(this.interpreter.answer("varon(juan)"));
	    Assert.assertTrue(this.interpreter.answer("mujer(maria)"));
	    Assert.assertTrue(this.interpreter.answer("padre(juan, pepa)"));
	}

    @Test
    public void testWithNonExistingFacts() throws Exception {
	    Assert.assertFalse(this.interpreter.answer("padre(juan, pepita)"));
	    Assert.assertFalse(this.interpreter.answer("varon(federico)"));
	    Assert.assertFalse(this.interpreter.answer("mujer(alejandro)"));
    }

    @Test
    public void testWithExistingRules() throws Exception {
	    Assert.assertTrue(this.interpreter.answer("hijo(pepe, juan)"));
	    Assert.assertTrue(this.interpreter.answer("hija(cecilia, roberto)"));
    }

    @Test
    public void testWithFalseRules() throws Exception {
	    Assert.assertFalse(this.interpreter.answer("hija(maria, roberto)"));
	    Assert.assertFalse(this.interpreter.answer("hijo(hector, juan)"));
    }

    @Test
    public void testWithNonExistingRules() throws Exception {
	    Assert.assertFalse(this.interpreter.answer("nieto(pepe, hector)"));
	    Assert.assertFalse(this.interpreter.answer("nieta(maria, hector)"));
    }
}
