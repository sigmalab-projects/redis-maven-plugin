package de.sigmalab.maven.plugins.redis;

import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import redis.embedded.RedisServer;

/**
 * Starts the redis-server instance and place it into plugin-context.
 *
 * @author  jbellmann
 */
@Mojo(name = "run", defaultPhase = LifecyclePhase.NONE)
public class RunRedisMojo extends AbstractMojo {

    static final String REDIS_SERVER_INSTANCE = "REDIS_SERVER_INSTANCE";

    @Parameter(property = "redis.server.port", defaultValue = "6379")
    public Integer port;

    @Parameter(property = "redis.server.skip", defaultValue = "false")
    public boolean skip;

    @Override
    public void execute() throws MojoExecutionException {
        if (skip) {
            getLog().info("Skipping Redis server...");
            return;
        }

        try {
            RedisServer redisServer = new RedisServer(port);
            redisServer.start();
            getPluginContext().put(REDIS_SERVER_INSTANCE, redisServer);
            getLog().info("Redis-Server started on port : " + port);
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }

    }

}
