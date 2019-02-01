package runnable;

import exception.MethodException;
import method.Method;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Request implements Method,Runnable {
    private StringBuilder head;
    private String path, host;
    private Method method;
    private OutputStream out;

    public Request(Method method) {
        this.method = method;
        head = new StringBuilder();

    }

    public void addHeader(String arg) {
        head.append(arg).append("\r\n");
    }

    public void addBody(String arg) {
        method.addBody(arg,false);
    }

    public String host() {
        return host;
    }

    public void addBody(String arg,boolean isFile) {
        method.addBody(arg,isFile);
    }

    public void setUrl(String arg) {
        if(arg.isEmpty()) {
            throw new MethodException(method);
        }

        arg = arg.replace("http://","");
        int var = arg.indexOf("/");


        if(var == 0) {
            path = arg;
        } else if(var == -1) {
            path = "/";
            host = arg;
        } else {
            host = arg.substring(0,var);
            path = arg.substring(var);
        }
    }

    @Override
    public String getName() {
        return method.getName();
    }

    public void open(OutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        PrintWriter o = new PrintWriter(new OutputStreamWriter(out));
        StringBuilder t = new StringBuilder(method.getName()).append(' ').append(path)
                .append(" HTTP/1.0\r\nHost: ").append(host).append("\r\n").append(head);

        String temp = method.toString();

        if(!temp.isEmpty()) {
            t.append("Content-Length:").append(temp.length()).append("\r\n");
        }

        t.append("\r\n");

        if(!temp.isEmpty()) {
            t.append(temp);
        }

        o.print(t.toString());
        o.flush();
    }

    @Override
    public String help() {
        return method.help();
    }

}
