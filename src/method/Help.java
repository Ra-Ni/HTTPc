package method;

public class Help implements Method {

    private String arg;

    public Help() {

    }
    public Help(String code) {
        arg = code;
    }

    @Deprecated
    public String getName() {
        return "HELP";
    }

    @Override
    public String help() {
        return
            "httpc is a curl-like application but supports HTTP protocol only.\n\n" +
            "Usage: httpc request.command [arguments]\n\n" +
            "The commands are:\n" +
            "\tget\tExecutes a HTTP GET request and prints the response.\n" +
            "\tpost\tExecutes a HTTP POST request and prints the response.\n" +
            "\thelp\tPrints this screen.\n\n" +
            "Use \"httpc help [request.command]\" for more information about a request command.";
    }

    @Deprecated
    public void addBody(String args) {

    }

    @Deprecated
    public void addBody(String args, boolean isFile) {

    }
}
