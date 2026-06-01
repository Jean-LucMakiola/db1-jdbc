import entities.Actor;
import entities.Movie;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImdbSearchTests {

    static ConnectionConfig config;

    @BeforeAll
    static void setUp() {
        try {
            config = ImdbSearch.getConnectionConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertFalse(config.getUsername().isBlank(), "please provide your database username in the \"database.properties\" file");
        Assertions.assertFalse(config.getPassword().isBlank(), "please provide your database password in the \"database.properties\" file");

        TestFixtures.setup();
        
    }

    @Test
    @Order(1)
    @DisplayName("connectDatabase(config) should return a valid connection to the Postgres server")
    void testConnectDatabase() throws SQLException, IOException {
        try (var connection = ImdbSearch.openConnection()) {
            Assertions.assertTrue(connection.isValid(0), "connection is not valid");
        }
    }

    @Test
    @Order(2)
    @DisplayName("Movie query 1: Wolf of Wall Street")
    void testQueryMovies1() throws SQLException, IOException {
        try (var connection = ImdbSearch.openConnection()) {
            List<Movie> results = ImdbSearch.findMovies(connection, "Wolf of Wall Street");
            Assertions.assertIterableEquals(List.of(TestFixtures.WWS_1929, TestFixtures.WWS_2013), results);
        }
    }

    @Test
    @Order(2)
    @DisplayName("Movie query 2: Ghost in the Shell")
    void testQueryMovies2() throws SQLException, IOException {
        try (var connection = ImdbSearch.openConnection()) {
            List<Movie> results = ImdbSearch.findMovies(connection, "Ghost in the Shell");

            Assertions.assertEquals(TestFixtures.GITS_1995, results.get(0), "The first result should be \"Ghost in the Shell\" from 1995");
            Assertions.assertEquals(TestFixtures.GITS_1995.actorNames, results.get(0).actorNames, "\"Ghost in the Shell (1995)\" did not contain the correct actors");
            Assertions.assertTrue(results.contains(TestFixtures.GITS_TNM_2015), "The result should contain \"Ghost in the Shell: The New Movie\" from 2015");
        }
    }

    @Test
    @Order(3)
    @DisplayName("Actor/Actress query 1: Anne Hathaway")
    void testQueryActors1() throws SQLException, IOException {
        try (var connection = ImdbSearch.openConnection()) {
            List<Actor> results = ImdbSearch.findActors(connection, "Anne Hathaway");
            Assertions.assertEquals(1, results.size());

            Assertions.assertEquals(TestFixtures.ANNE_HATHAWAY, results.get(0));
            Assertions.assertEquals(TestFixtures.ANNE_HATHAWAY.playedIn, results.get(0).playedIn);
            Assertions.assertEquals(TestFixtures.ANNE_HATHAWAY.costarNameToCount, results.get(0).costarNameToCount);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Actor/Actress query 2: Freeman")
    void testQueryActors2() throws SQLException, IOException {
        try (var connection = ImdbSearch.openConnection()) {
            List<Actor> results = ImdbSearch.findActors(connection, "Freeman");
            Assertions.assertEquals(List.of(TestFixtures.MORGAN_FREEMAN, TestFixtures.FREEMAN_WOOD, TestFixtures.KATHLEEN_FREEMAN, TestFixtures.HOWARD_FREEMAN, TestFixtures.MONA_FREEMAN), results);

            Assertions.assertEquals(TestFixtures.MORGAN_FREEMAN, results.get(0));
            Assertions.assertEquals(TestFixtures.MORGAN_FREEMAN.playedIn, results.get(0).playedIn);
            Assertions.assertEquals(TestFixtures.MORGAN_FREEMAN.costarNameToCount, results.get(0).costarNameToCount);
        }
    }
}
