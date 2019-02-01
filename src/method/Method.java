package method;

public interface Method {
    String getName();
    String toString();
    String help();
    void addBody(String args);
    void addBody(String args,boolean isFile);
}
