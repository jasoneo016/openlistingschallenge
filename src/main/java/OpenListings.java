import api.DistanceCalculator;
import com.google.maps.model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class OpenListings {


    public static void main(String[] args) {

        // Problem #1:
        String json1 = "[[2], [2, [3]]]";
        String json2 = "[]";
        String json3 = "[[1]]";
        String json4 = "";
        String json5 = "[";

        System.out.println("Problem 1: ");
        System.out.println(getDimensionsOfArray(json1)); // This should = 3
        System.out.println(getDimensionsOfArray(json2)); // This should = 1
        System.out.println(getDimensionsOfArray(json3)); // This should = 2
        System.out.println(getDimensionsOfArray(json4)); // This should = 0
        System.out.println(getDimensionsOfArray(json5)); // This should = -1
        System.out.println();

        // Problem #2:
        String website = "/Users/jzhang/website";
        System.out.println("Problem 2: ");
        for (int i = 0; i < getListOfShittyListings(website).size(); i++) {
            System.out.println(getListOfShittyListings(website).get(i));
        }
        System.out.println();

        // Problem #3:
        /*
            If I were to implement a more feature rich version of this wrapper,
            I would add functionality to track the duration it would take, also include considerations of traffic,
            and also providing alternate routes for users if a faster one is possible.
         */
        System.out.println("Problem 3: ");
        String origin = "2301 Hyperion Ave., Los Angeles, CA 90027";
        String destination = "1061 Market St., San Francisco, CA 94103";
        Distance distance = DistanceCalculator.getDistance(origin, destination);
        System.out.println("The distance between origin and destination is: " + distance);
        System.out.println();
    }

    //Can we assume all items in arrays are numbers?
    static int getDimensionsOfArray(String json) {
        if (json.length() == 0) {
            return 0;
        }

        // If the method can take in more than just a String, then this should be included
//        if (!(json instanceof String)) {
//            return -1;
//        }

        Stack<Character> stack = new Stack<>();
        int depth = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < json.length(); i++) {
            if (json.charAt(i) == '[') {
                stack.push(json.charAt(i));
                depth++;
            } else if (json.charAt(i) == ']') {
                stack.pop();
                depth--;
            }
            max = Math.max(depth, max);
        }
        return stack.size() == 0 ? max : -1;
    }

    static List<String> getListOfShittyListings(String path) {
        List<String> shittyListings = new ArrayList<>();
        String shittyListingString = "shittylistings.com";
        try (Stream<Path> paths = Files.walk(Paths.get(path))
                                        .filter(s -> s.toString().endsWith(".html"))) {
            paths.forEach(filePath ->
                    checkHTMLForShittyListings(filePath.toFile(), shittyListings, shittyListingString));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shittyListings;
    }

    private static void checkHTMLForShittyListings(File file, List<String> shittyListings, String shittyListingString) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.contains(shittyListingString)) {
                    shittyListings.add(file.getAbsolutePath());
                    break;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
