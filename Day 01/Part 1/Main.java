
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


class Solution {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        
        FileInputStream fStream = new FileInputStream("../input");
        DataInputStream in = new DataInputStream(fStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String inputLine;
        List<String> calibrationNumbers = new ArrayList<>();

        while ((inputLine = br.readLine()) != null) {
            String currentCalibrationNumber = "" ;

            for (int i = 0; i < inputLine.length(); i++) {
                char c = inputLine.charAt(i);

                if (isInt(c)){
                    currentCalibrationNumber += c ;
                }
            }

            calibrationNumbers.add(currentCalibrationNumber);
        }
        in.close();
        
        int CalibrationSum = 0;

        for (int i = 0; i < calibrationNumbers.size(); i++) {
            if(calibrationNumbers.get(i).length() == 0){
                continue;
            }

            char first_element = calibrationNumbers.get(i).charAt(0);
            char last_element = calibrationNumbers.get(i).charAt(calibrationNumbers.get(i).length() - 1);
            String new_number = "" + first_element + last_element;
            int calibrationNumber = Integer.valueOf(new_number);
            CalibrationSum += calibrationNumber;
        }

        System.out.println(CalibrationSum);

    }

    public static boolean isInt(char c){
        if (c == 0){
            return false;
        }
  
        return Character.isDigit(c);
    }
}