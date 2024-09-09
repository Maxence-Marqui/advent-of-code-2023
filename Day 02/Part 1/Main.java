
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        int idSum = 0;

        HashMap<String, Integer> cubeCount = new HashMap<>();
        cubeCount.put("red", 12);
        cubeCount.put("green", 13);
        cubeCount.put("blue", 14);

        FileInputStream fStream = new FileInputStream("../input.txt");
        DataInputStream in = new DataInputStream(fStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            boolean isGamePossible = true;
            Pattern gameIdPattern = Pattern.compile("Game ([0-9]+)");
            Pattern cubeCountPattern = Pattern.compile("([0-9]+) ([green|blue|red]+)");

            Matcher gameIdMatcher = gameIdPattern.matcher(inputLine);

            inputLine = inputLine.replaceAll("Game [0-9]+: ", "");
            System.out.println(inputLine);
            String[] rounds = inputLine.split("; ");
            for (String round : rounds) {
                String[] cubes = round.split(", ");
                for (String cube : cubes) {
                    Matcher cubesMatcher = cubeCountPattern.matcher(cube);
                    if (cubesMatcher.find()) {
                        int count = Integer.parseInt(cubesMatcher.group(1));
                        String color = cubesMatcher.group(2);

                        if(count > cubeCount.get(color)){
                            isGamePossible = false;
                        }

                        if (!isGamePossible) {
                            break;
                        }
                    }
                }

                if (!isGamePossible) {
                    break;
                }
            }

            if (isGamePossible && gameIdMatcher.find()) {
                int gameId = Integer.parseInt(gameIdMatcher.group(1));
                System.out.println(gameId);
                idSum += gameId;

            }
        }

        in.close();

        System.out.println(idSum);
    }
}
