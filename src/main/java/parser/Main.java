package parser;

import util.ValidationUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static util.ValidationUtil.validateArgs;

public class Main {
    public static String url;
    public static int start;
    public static int end;
    public static long timeout;

    public static void main(String[] args) {
        try {
            fillArgs(args);
            System.out.println("address,city,email,firstName,lastName,phone");
            for (int i = start; i <= end; i++) {
                String parseUrl = url + i;
                Parser parser = new Parser(parseUrl);
                parser.parse();
                parser.print();
                Thread.sleep(timeout);
            }
        } catch (Exception e) {
            System.out.println("Something gets wrong: {}".formatted(e.toString()));
        }
    }

    private static void fillArgs(String[] args) throws IOException {
        String[] params = new String[4];
        if (!validateArgs(args)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please input url: ");
            params[0] = reader.readLine();
            System.out.println("Please input start: ");
            params[1] = reader.readLine();
            System.out.println("Please input end: ");
            params[2] = reader.readLine();
            System.out.println("Please input timeout: ");
            params[3] = reader.readLine();
        } else {
            params = Arrays.copyOf(args, args.length);
        }

        if (params[3].isEmpty()) {
            params[3] = "500";
        }

        url = params[0];
        start = Integer.parseInt(params[1]);
        end = Integer.parseInt(params[2]);
        timeout = Integer.parseInt(params[3]);

        ValidationUtil.checkParams();
    }

}
