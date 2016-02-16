import MathWiz.MathProtocol;

public class Bots {
    private MathProtocol mathProtocol = new MathProtocol();

    public String handleRequest(String request) {
        String resolvedEquation = null;
        if (request.startsWith("MATH")) {
            resolvedEquation = mathProtocol.process(request);
        }
        return resolvedEquation;
    }

}
