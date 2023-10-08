import java.net.*;
import java.io.*;


public class GetName {
    static final int BEGIN_IDX           = 21;
    static final int END_IDX             = 2;
    static final String ADDRESS_TEMPLATE = "https://www.ecs.soton.ac.uk/people/";

    static URL createURL() throws MalformedURLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a unique ID:");
        String id = br.readLine();
        String address = ADDRESS_TEMPLATE + id;

        return URI.create(address).toURL();
    }

    static void findName(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    
        String currentLine, nameLine, name;
        Boolean nameFound = false;

        while ((currentLine = in.readLine()) != null) {
            if (currentLine.indexOf("\"@type\": \"Person\"") != -1) {
                nameLine = in.readLine();
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
        try {
            findName(createURL());
        } catch (MalformedURLException exception) {
            System.out.println("Invalid URL.");
        } catch (IOException exception) {
            System.out.println("IO exception occurred.");
        }
    }
}