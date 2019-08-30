package Hello;

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
public class TestGreeting {
    private String name;
    private String expectedGreeting;

    public TestGreeting(String name, String expectedGreeting) {
        this.name = name;
        this.expectedGreeting = expectedGreeting;
    }

    @Parameters
    public static Collection<Object[]> adviceToTest() {
        return Arrays.asList(new Object[][]{
                {"", "Hello, World!"},
                {"gorgeous", "Hello, gorgeous!"},
                {"Sara", "Hello, Sara!"}
        });
    }

    @Test
    public void testGetAdviceById() {
        when()
                .get("http://localhost:8080/greeting?name=" + name)
                .then()
                .statusCode(200)
                .body("content", equalTo(expectedGreeting));
    }
}
