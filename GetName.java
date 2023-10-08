import java.net.*;
import java.io.*;

public class GetName {
    public static void main(String[] args) throws MalformedURLException {
        
        String id = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a unique id:");

        try {
            id = br.readLine();
        } catch (IOException ioe) {
            System.err.println("There was an input error.");
        }

        String address = "https://www.ecs.soton.ac.uk/people/" + id;
        URL url = URI.create(address).toURL();
        URLConnection urlConnection = null;
        HttpURLConnection connection = null;
        BufferedReader in = null;

        try {
            urlConnection = url.openConnection();
            connection = (HttpURLConnection)urlConnection;
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch(IOException exception) {
            System.err.println("Invalid URL");
        }

        String current, nameLine, name;
        try {
            while ((current = in.readLine()) != null) {
                if (current.indexOf("\"@type\": \"Person\"") != -1) {
                    nameLine = in.readLine();
                    name = nameLine.substring(21, nameLine.length() - 2);
                    System.out.println(name);
                }
            }
        } catch (IOException exception) {
            System.err.println("IO Exception");
        }
    }
}