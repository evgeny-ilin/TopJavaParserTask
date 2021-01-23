package parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Parser {
    private final String parseUrl;
    private String urlText;

    private String address;
    private String city;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    public Parser(String parseUrl) {
        this.parseUrl = parseUrl;
    }

    public void parse() throws IOException {
        setUrlText();

        address = parseArg("address");
        city = parseArg("city");
        email = parseArg("email");
        firstName = parseArg("firstName");
        lastName = parseArg("lastName");
        phone = parseArg("phone");

    }

    public void print() {
        System.out.println(address + "," +
                city + "," +
                email + "," +
                firstName + "," +
                lastName + "," +
                phone);
    }

    private void setUrlText() throws IOException {
        URL url = new URL(parseUrl);
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                stringBuilder.append(inputLine);
        }
        urlText = stringBuilder.toString();
    }

    private String parseArg(String arg) {
        int start = urlText.indexOf(arg) + arg.length() + 3;
        int end = urlText.indexOf("\"",start);
        return urlText.substring(start,end);
    }
}
