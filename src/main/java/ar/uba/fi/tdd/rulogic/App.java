package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDatabaseException;
import ar.uba.fi.tdd.rulogic.exceptions.InvalidQueryException;
import ar.uba.fi.tdd.rulogic.model.Interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) throws IOException, InvalidDatabaseException {
	    List<String> rawDb = Files.lines(Paths.get(args[0])).collect(Collectors.toList());
        Interpreter interpreter = new Interpreter(rawDb);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your query or letter 'q' to exit program.");
        String s = br.readLine();
        while (!s.equals("q")) {
            try {
                System.out.println("Result: " + (interpreter.answer(s) ? "Yes" : "No"));
            } catch (InvalidQueryException e) {
                System.out.println(e.getMessage());
            }
            s = br.readLine();
        }
    }
}
