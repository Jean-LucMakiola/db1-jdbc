import entities.Actor;
import entities.Movie;

import java.util.Arrays;
import java.util.HashSet;

public class TestFixtures {


    static Actor FREEMAN_WOOD = new Actor("nm0939706", "Freeman Wood");
    static Actor KATHLEEN_FREEMAN = new Actor("nm0293466", "Kathleen Freeman");
    static Actor HOWARD_FREEMAN = new Actor("nm0293418", "Howard Freeman");
    static Actor MONA_FREEMAN = new Actor("nm0293530", "Mona Freeman");
    static Actor ANNE_HATHAWAY = new Actor("nm0004266", "Anne Hathaway");
    static Actor MORGAN_FREEMAN = new Actor("nm0000151", "Morgan Freeman");


    static Movie WWS_1929 = new Movie(
            "tt0020596",
            "The Wolf of Wall Street",
            1929,
            new HashSet<>(Arrays.asList("Drama")));

    static Movie WWS_2013 = new Movie(
            "tt0993846",
            "The Wolf of Wall Street",
            2013,
            new HashSet<>(Arrays.asList("Crime", "Biography", "Comedy")));

    static Movie GITS_1995 = new Movie(
            "tt0113568",
            "Ghost in the Shell",
            1995,
            new HashSet<>(Arrays.asList("Animation", "Crime", "Action")));

    static Movie GITS_TNM_2015 = new Movie(
            "tt4337072",
            "Ghost in the Shell: The New Movie",
            2015,
            new HashSet<>(Arrays.asList("Animation", "Sci-Fi", "Action")));

    static void setup(){
        ANNE_HATHAWAY.costarNameToCount.put("Hector Elizondo", 3);
        ANNE_HATHAWAY.costarNameToCount.put("Helena Bonham Carter", 3);
        ANNE_HATHAWAY.costarNameToCount.put("Heather Matarazzo", 2);
        ANNE_HATHAWAY.costarNameToCount.put("Jeremy Strong", 2);
        ANNE_HATHAWAY.costarNameToCount.put("Jesse Eisenberg", 2);

        ANNE_HATHAWAY.playedIn.addAll(Arrays.asList(
                "Mothers' Instinct",
                "The Idea of You",
                "She Came to Me",
                "Armageddon Time",
                "Locked Down"));

        MORGAN_FREEMAN.costarNameToCount.put("Michael Caine", 5);
        MORGAN_FREEMAN.costarNameToCount.put("Aaron Eckhart", 4);
        MORGAN_FREEMAN.costarNameToCount.put("Ashley Judd", 4);
        MORGAN_FREEMAN.costarNameToCount.put("Bruce Willis", 3);
        MORGAN_FREEMAN.costarNameToCount.put("Cary Elwes", 3);

        MORGAN_FREEMAN.playedIn.addAll(Arrays.asList(
                "My Dead Friend Zoe",
                "57 Seconds",
                "A Good Person",
                "The Ritual Killer",
                "Paradise Highway"));

        GITS_1995.actorNames.addAll(Arrays.asList(
                "Akio Ôtsuka",
                "Atsuko Tanaka",
                "Iemasa Kayumi",
                "Kôichi Yamadera",
                "Masato Yamanouchi",
                "Namaki Masakazu",
                "Shinji Ogawa",
                "Tamio Ôki",
                "Tesshô Genda",
                "Yutaka Nakano"));

        GITS_TNM_2015.actorNames.addAll(Arrays.asList(
                "Ikkyû Jaku",
                "Kazuya Nakai",
                "Ken'ichirô Matsuda",
                "Kenji Nojima",
                "Maaya Sakamoto",
                "Mayumi Asano",
                "Megumi Han",
                "Miyuki Sawashiro",
                "Mugihito",
                "Naoto"));
    }


}
