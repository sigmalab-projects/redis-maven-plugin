package de.sigmalab.maven.plugins.redis;

/**
 * Configuration for Master-Host.
 * 
 * @author jbellmann
 *
 */
public class Master {

    private String host;

    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
