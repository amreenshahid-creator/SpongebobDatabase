import java.sql.*;
import java.util.Scanner;

public class Interface {
    public static void main(String[] args) {
        String link = "jdbc:sqlite:spongebob.db";

        try (Connection connection = DriverManager.getConnection(link);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("============ Welcome to the Spongebob Database ============\n");

            while(true) {
                System.out.println("\n------ Main Menu ------");
                System.out.println("1. Episodes by Season");
                System.out.println("2. Characters in Episodes");
                System.out.println("3. Locations in Episodes");
                System.out.println("4. Episode Details");
                System.out.println("5. Quit");
                System.out.println("-----------------------");
                System.out.print("\nWhat would you like to search? (1-5): ");

                int option = scanner.nextInt();

                if(option == 1) {
                    System.out.print("\nEnter a season (1-2): ");
                    int season = scanner.nextInt();

                    String q1 = "select episodeID, title from Episode where season = ?";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(q1);
                    preparedStatement1.setInt(1, season);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    System.out.println("\nEpisodes in Season " + season);
                    while(resultSet1.next()) {
                        System.out.println(resultSet1.getInt("episodeID") + ". " + resultSet1.getString("title"));
                    }
                    continue;
                }

                else if(option == 2) {
                    System.out.print("\nEnter an Episode ID: ");
                    int id = scanner.nextInt();

                    String q2 = "select Character.name from Character join EpisodeCharacter " +
                            "on Character.characterID  = EpisodeCharacter.characterID where episodeID = ?";

                    PreparedStatement preparedStatement2 = connection.prepareStatement(q2);
                    preparedStatement2.setInt(1, id);
                    ResultSet resultSet2 = preparedStatement2.executeQuery();

                    int i = 1;
                    System.out.println("\nMain Characters in Episode " + id);
                    while(resultSet2.next()) {
                        System.out.println(i + ". " + resultSet2.getString("name"));
                        i++;
                    }
                    continue;
                }

                else if(option == 3) {
                    System.out.print("\nEnter an Episode ID: ");
                    int id = scanner.nextInt();

                    String q3 = "select Location.name from Location join EpisodeLocation" +
                            " on Location.locationID = EpisodeLocation.locationID where episodeID = ?";

                    PreparedStatement preparedStatement3 = connection.prepareStatement(q3);
                    preparedStatement3.setInt(1, id);
                    ResultSet resultSet3 = preparedStatement3.executeQuery();

                    System.out.println("\nProminent Locations in Episode " + id);
                    int i = 1;
                    while(resultSet3.next()) {
                        System.out.println(i + ". " + resultSet3.getString("name"));
                        i++;
                    }
                    continue;
                }

                else if (option == 4) {
                    System.out.print("\nEnter an Episode ID: ");
                    int id = scanner.nextInt();

                    String q4 = "select title, season, releaseDate from Episode where episodeID = ?";
                    PreparedStatement preparedStatement4 = connection.prepareStatement(q4);
                    preparedStatement4.setInt(1, id);
                    ResultSet resultSet4 = preparedStatement4.executeQuery();

                    if (resultSet4.next()) {
                        System.out.print("\nEpisode Details: ");
                        System.out.print("\nTitle: " + resultSet4.getString("title"));
                        System.out.print("\nSeason: " + resultSet4.getString("season"));
                        System.out.print("\nRelease Date: " + resultSet4.getString("releaseDate"));
                    } else {
                        System.out.print("\nEpisode not in database");
                    }
                }

                else if(option == 5) {
                    System.out.println("\nBye!");
                    return;
                }

                else {
                    System.out.println("\nPlease pick an option from 1-5");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}