import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

public class Main {
    public static final String[] PARSE_ITEMS = {"address", "city", "email", "firstName", "lastName", "phone"};
    public static final String[] INPUT_ITEMS = {"url", "start", "end", "timeout"};
    public static String url;
    public static int start, end;
    public static long timeout;

    public static void main(String[] args) {
        try {
            fillParams(args);
            System.out.println("address,city,email,firstName,lastName,phone");
            for (int i = start; i <= end; i++) {
                String urlText = getUrlText(url + i);
                for (String item : PARSE_ITEMS) {
                    parseArg(item, urlText);
                }
                System.out.println();
                Thread.sleep(timeout);
            }
        } catch (Exception e) {
            System.out.printf("Something gets wrong: {} %n", e.getMessage());
        }
    }

    private static void fillParams(String[] args) throws IOException {
        String[] params = new String[4];
        if (args.length < 3) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int i = 0;
            for (String item : INPUT_ITEMS) {
                System.out.println("Please input " + item);
                params[i++] = reader.readLine();
            }
        } else {
            params = Arrays.copyOf(args, args.length);
        }

        url = params[0];
        start = Integer.parseInt(params[1]);
        end = Integer.parseInt(params[2]);
        timeout = params.length < 4 ? 500 : Integer.parseInt(params[3]);
        url = url.endsWith("=") ? url : url + "=";
        if (start >= end) {
            throw new IllegalArgumentException("End value must be greater than Start value");
        }
    }

    private static String getUrlText(String parseUrl) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new URL(parseUrl).openStream()))) {
            String inputLine;
            while (!(inputLine = in.readLine()).contains("</head>")) {
                stringBuilder.append(inputLine);
            }
        }
        return stringBuilder.toString();
    }

    private static void parseArg(String arg, String urlText) {
        int start = urlText.indexOf(arg) + arg.length() + 3;
        int end = urlText.indexOf("\"", start);
        System.out.print(urlText.substring(start, end) + ",");
    }
}