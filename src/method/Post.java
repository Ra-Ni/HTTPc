package method;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import exception.MethodException;

public class Post implements Method {
    private boolean consumed;
    private StringBuilder body;

    public Post() {
        body = new StringBuilder();
        consumed = false;
    }
    @Override
    public String getName() {
        return "POST";
    }

    @Override
    public String help() {
        return
            "Usage: httpc post [-v] [-h key:value] [-d inline-data] [-f file] URL\n\n" +
            "Post executes a HTTP POST request for a given URL with inline data or from file.\n\n" +
            "\t-v\t\t\t\tPrints the detail of the response such as protocol, status, and headers.\n" +
            "\t-h key:value\tAssociates headers to HTTP Request with the format'key:value'.\n" +
            "\t-d string\t\tAssociates an inline data to the body HTTP POST request.\n" +
            "\t-f file\t\t\tAssociates the content of a file to the body HTTP POST request.\n\n" +
            "Either [-d] or [-f] can be used but not both.";
    }

    @Override
    public void addBody(String args) {
        if(consumed) {
            throw new MethodException(this);
        }
        consumed = true;
        body.append(args);
    }

    @Override
    public void addBody(String args, boolean isFile) {

        if(isFile) {
            Scanner in = null;
            try {
                in = new Scanner(new File(args)).useDelimiter("\\A");
            } catch (FileNotFoundException e) {
                throw new MethodException(this);
            }
            addBody(in.next());
        } else {
            addBody(args);
        }
    }

    @Override
    public String toString() {
        return body.toString();
    }
}
