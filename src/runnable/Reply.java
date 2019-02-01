package runnable;

import exception.MethodException;
import exception.RedirectionException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Reply implements Runnable {
    private boolean verbose;
    private String output;
    private InputStream in;


    public void verboseOn() {
        verbose = true;
    }

    public void setOutput(String arg) {
        output = arg;
    }

    public void open(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        Scanner scan = new Scanner(new InputStreamReader(in));
        PrintWriter out;

        try {
            out = output == null ? new PrintWriter(System.out) : new PrintWriter(output);
        } catch (FileNotFoundException e) {
            throw new MethodException();
        }

        if (scan.findInLine(".*(3\\d\\d).*") != null) {
            scan.useDelimiter("(Location: )");
            scan.next();
            scan.useDelimiter("\\p{javaWhitespace}+");
            scan.next();
            throw new RedirectionException(scan.next());
        } else if(scan.findInLine(".*(4\\d\\d).*") != null) {
            throw new MethodException();
        }

        if(!verbose) {
            scan.useDelimiter(".*(\\{\\n).*");
            scan.next();
        }

        scan.useDelimiter("\\A");
        out.print(scan.next());
        out.flush();
        out.close();
    }
}
