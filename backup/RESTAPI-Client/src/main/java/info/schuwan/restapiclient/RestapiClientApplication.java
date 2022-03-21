package info.schuwan.restapiclient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.UnresolvedAddressException;
import java.util.Base64;

@SpringBootApplication
public class RestapiClientApplication {

    private static HttpURLConnection connection;
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(RestapiClientApplication.class, args);
//        method1();          //java.net.HTTPURLConnection
        String apiURL = "https://jsonplaceholder.typicode.com/albums";
//        method2(apiURL);          //java.net.HTTPClient.newHttpClient();
        restAuth("user_user2", "123");
    }

    public static void method1(){
        //Method 1 :
        //java.net.HTTPURLConnection

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");
            connection = (HttpURLConnection) url.openConnection();
            //request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(15000);

            int myConnectionStatus = connection.getResponseCode();

            System.out.println("Response  [" + myConnectionStatus + "]");
            if (connection == null) {
                if (myConnectionStatus > 299) {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                } else {
                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    while ((line = reader.readLine()) != null) {
                        responseContent.append(line);
                    }
                    reader.close();
                }

                System.out.println(responseContent.toString());
                parseMyjson(responseContent.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    public static void method2(String apiURL) {             // Method2:     java.net.HTTPClient.newHttpClient();
        HttpClient client = HttpClient.newHttpClient();
        client.authenticator();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiURL)).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(RestapiClientApplication::parseMyjson)
//                .thenAccept(System.out::println)
                .join();
    }

    public static String parseMyjson(String resonseBody) throws UnresolvedAddressException {
        JSONArray albums = new JSONArray(resonseBody);
        try{
            for (int i = 0; i < albums.length(); i++) {
                JSONObject album = albums.getJSONObject(i);
                int id  = album.getInt("id");
                int userId =  album.getInt("userId");
                String title = album.getString("title");
                System.out.println(id + "\t\t" +title + "\t\t" + userId);
            }
        }
        catch (UnresolvedAddressException exception){
//            exception.printStackTrace();
            System.out.println("bad url link, >>>>>>\t");
        }
        return null;
    }

    public static void restAuth(String userAccountName, String secretKey) throws IOException, InterruptedException {
        // Customer ID
        final String username = userAccountName;
        // Customer secret
        final String secret = secretKey;

        // Concatenate customer key and customer secret and use base64 to encode the concatenated string
        String plainCredentials = username + ":" + secret;
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));

        String authorizationHeader = "Basic " + base64Credentials;  // Create authorization header

        HttpClient client = HttpClient.newHttpClient();

        // Create HTTP request object
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.agora.io/dev/v1/projects"))
                .GET()
                .header("Authorization", authorizationHeader)
                .header("Content-Type", "application/json")
                .build();
        // Send HTTP request
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }


}

