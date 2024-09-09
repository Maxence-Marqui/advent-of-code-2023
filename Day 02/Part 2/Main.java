
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

        int powerSum = 0;

        FileInputStream fStream = new FileInputStream("../input.txt");
        DataInputStream in = new DataInputStream(fStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String inputLine;

        while ((inputLine = br.readLine()) != null) {

            HashMap<String, Integer> cubeCount = new HashMap<>();
            cubeCount.put("red", 0);
            cubeCount.put("green", 0);
            cubeCount.put("blue", 0);

            Pattern cubeCountPattern = Pattern.compile("([0-9]+) ([green|blue|red]+)");

            inputLine = inputLine.replaceAll("Game [0-9]+: ", "");
            String[] rounds = inputLine.split("; ");

            for (String round : rounds) {
                String[] cubes = round.split(", ");

                for (String cube : cubes) {
                    Matcher cubesMatcher = cubeCountPattern.matcher(cube);
                    if (cubesMatcher.find()) {
                        int count = Integer.parseInt(cubesMatcher.group(1));
                        String color = cubesMatcher.group(2);

                        if(count > cubeCount.get(color)){
                            cubeCount.put(color, count);
                        }
                    }
                }
            }

            powerSum += cubeCount.get("green") * cubeCount.get("blue") * cubeCount.get("red");
        }

        in.close();

        System.out.println(powerSum);
    }
}
