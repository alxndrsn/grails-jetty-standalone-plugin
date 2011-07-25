package standalone;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import java.net.URL;
import java.security.ProtectionDomain;

public class Start {

    public static void main(String[] args) throws Exception {

        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        PatternLayout layout = new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN);
        rootLogger.addAppender(new ConsoleAppender(layout));

        Server server = new Server();

        SocketConnector connector = new SocketConnector();

        // Set some timeout options to make debugging easier.

        connector.setMaxIdleTime(1000 * 60 * 60);
        connector.setSoLingerTime(-1);
        connector.setPort(9090);
        server.setConnectors(new Connector[]{connector});

        WebAppContext context = new WebAppContext();
        context.setServer(server);
        context.setContextPath("/");

        ProtectionDomain protectionDomain = Start.class.getProtectionDomain();
        URL location = protectionDomain.getCodeSource().getLocation();
        context.setWar(location.toExternalForm());

        server.setHandler(context);
        try {
            server.start();
            System.in.read();
            server.stop();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(100);
        }
    }
}