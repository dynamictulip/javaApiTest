import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class TestGetAdviceById {
    private String expectedAdvice;
    private int id;

    public TestGetAdviceById(int id, String expectedAdvice) {
        this.id = id;
        this.expectedAdvice = expectedAdvice;
        RestAssured.registerParser("text/html", Parser.JSON);
    }

    @Parameters
    public static Collection<Object[]> adviceToTest() {
        return Arrays.asList(new Object[][]{
                {1, "Remember that spiders are more afraid of you, than you are of them."},
                {2, "Smile and the world smiles with you. Frown and you're on your own."},
                {3, "Don't eat non-snow-coloured snow."}
        });
    }

    @Test
    public void testGetAdviceById() {
        when()
                .get("https://api.adviceslip.com/advice/" + id)
        .then()
                .statusCode(200)
                .body("slip.advice", equalTo(expectedAdvice));
    }
}
