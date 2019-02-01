package method;

import exception.MethodException;

public class Get implements Method {
    @Override
    public String getName() {
        return "GET";
    }

    @Override
    public String help() {
        return
            "Usage: httpc get [-v] [-h key:value] URL\n\n" +
            "Get executes a HTTP GET request for a given URL.\n\n" +
            "\t-v\t\t\t\tPrints the detail of the response such as protocol, status,and headers\n" +
            "\t-h key:value\tAssociates headers to HTTP Request with the format 'key:value'."
        ;
    }

    @Override
    public void addBody(String args) {
        throw new MethodException(this);
    }

    @Override
    public void addBody(String args, boolean isFile) {
        throw new MethodException();
    }

    @Override
    public String toString() {
        return "";
    }


}
