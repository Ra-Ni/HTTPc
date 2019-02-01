package runnable;

import exception.MethodException;
import exception.RedirectionException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Reply implements Runnable {
    private boolean verbose;
    private String output;
    private Scanner in;


    public void verboseOn() {
        verbose = true;
    }

    public void setOutput(String arg) {
        output = arg;
    }

    public void open(InputStream in) {
        this.in = new Scanner(in);
    }

    @Override
    public void run() {
        PrintWriter out;

        try {
            out = output == null ? new PrintWriter(System.out) : new PrintWriter(output);
        } catch (FileNotFoundException e) {
            throw new MethodException();
        }

        if (in.findInLine(".*(3\\d\\d).*") != null) {
            in.useDelimiter("(Location: )");
            in.next();
            in.useDelimiter("\\p{javaWhitespace}+");
            in.next();
            throw new RedirectionException(in.next());
        }

        if(!verbose) {
            while (!in.nextLine().isEmpty()) ;
        }

        in.useDelimiter("\\A");
        out.print(in.next());

        out.flush();
        out.close();
    }
}
