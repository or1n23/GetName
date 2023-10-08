import java.net.*;
import java.io.*;

public class GetName {
    
    public static void main(String[] args) throws MalformedURLException {
        
        String id = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a unique id:  ");

        try {
            id = br.readLine();
        } catch (IOException ioe) {
            System.err.println("There was an input error.");
        }

        String address = "https://www.ecs.soton.ac.uk/people/" + id;
        System.out.println(address);
        URL url = URI.create(address).toURL();
    }
}
