import exception.MethodException;
import exception.RedirectionException;
import method.Get;
import method.Method;
import method.Post;
import runnable.Reply;
import runnable.Request;

import java.io.IOException;
import java.net.Socket;

/**
 * Httpc is a java curl-like application but supports HTTP protocol only.
 * <p>
 * The Uml Diagram for this application is found in the project directory as "UMLDiagram.png".
 *
 * @author Rani Rafid
 */
public class httpc {
    private Request request;
    private Reply reply;
    private Socket socket;

    private httpc(Method method) {
        request = new Request(method);
        reply = new Reply();
        socket = null;
    }

    private void exec() throws IOException,MethodException {
        boolean redirect = true;
        for(int i = 0; i < 5 && redirect; i++) {
            try {
                socket = new Socket(request.host(), 80);
                reply.open(socket.getInputStream());
                request.open(socket.getOutputStream());
                request.run();
                reply.run();

                redirect = false;

            } catch (RedirectionException e) {
                request.setUrl(e.getMessage());
            }
        }

        if(redirect) {
            System.err.println("Limit of redirects reached.");
            throw new MethodException();
        }
    }

    /**
     * This is the main method which starts the http client
     *
     * @param args arguments to read from
     */
    public static void main(String[] args) {
        httpc httpc;

        try {

            if (args == null || args.length < 2) {
                throw new MethodException();
            } else if (args[0].equalsIgnoreCase("post")) {
                httpc = new httpc(new Post());
            } else if (args[0].equalsIgnoreCase("get")) {
                httpc = new httpc(new Get());
            } else {
                throw new MethodException(args[1]);
            }

            for (int i = 1; i < args.length; i++) {
                if (args[i].equalsIgnoreCase("-v")) {
                    httpc.reply.verboseOn();
                } else if (args[i].equalsIgnoreCase("-h")) {
                    httpc.request.addHeader(args[++i]);
                } else if (args[i].equalsIgnoreCase("-d")) {
                    httpc.request.addBody(args[++i]);
                } else if (args[i].equalsIgnoreCase("-f")) {
                    httpc.request.addBody(args[++i], true);
                } else if (args[i].equalsIgnoreCase("-o")) {
                    httpc.reply.setOutput(args[++i]);
                } else if (args[i].startsWith("http://")) {
                    httpc.request.setUrl(args[i]);
                }
            }

            httpc.exec();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.exit(0);
        }
    }
}
