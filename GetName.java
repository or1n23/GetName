// Program to fetch the name of a person on the public soton people finder
// page, given a unique email ID.


import java.net.*;
import java.io.*;


public class GetName {
    // Constants
    static final int    BEGIN_IDX        = 21;
    static final int    END_IDX          = 2;
    static final String ADDRESS_TEMPLATE = "https://www.ecs.soton.ac.uk/people/";

    // Create a URL object by concatenating the address template and user input.
    static URL createURL() throws MalformedURLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a unique ID:");
        String id = br.readLine();
        String address = ADDRESS_TEMPLATE + id;

        return URI.create(address).toURL();
    }

    // Find the line of HTML containing the name, and output it.
    static void findName(URL url) throws IOException {
        // Open a connection to the URL and create an input buffer to read data from it.
        URLConnection urlConnection = url.openConnection();
        BufferedReader inputBuffer = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    
        String currentLine, nameLine, name;
        Boolean nameFound = false;

        // Read each line until the one containing the name is found.
        while ((currentLine = inputBuffer.readLine()) != null) {
            if (currentLine.indexOf("\"@type\": \"Person\"") != -1) {
                nameLine = inputBuffer.readLine();
                // Take a substring of the correct line to get the name. Output the name to console.
                name = nameLine.substring(BEGIN_IDX, nameLine.length() - END_IDX);
                System.out.println(name);
                nameFound = true;
            }
        }
        if (!nameFound) {
            System.out.println("Name not found. Please check the inputted unique ID.");
        }
    }

    public static void main(String[] args) {
        // Call the functions and catch any exceptions.
        try {
            findName(createURL());
        } catch (MalformedURLException exception) {
            System.out.println("Invalid URL.");
        } catch (IOException exception) {
            System.out.println("IO exception occurred.");
        }
    }
}