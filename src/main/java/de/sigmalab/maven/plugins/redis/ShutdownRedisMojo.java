package de.sigmalab.maven.plugins.redis;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import redis.embedded.RedisServer;

/**
 * Stops the Redis-Server when found in plugin-context.
 *
 * @author  jbellmann
 */
@Mojo(name = "shutdown", defaultPhase = LifecyclePhase.NONE)
public class ShutdownRedisMojo extends AbstractMojo {

    @Parameter(property = "redis.server.skip", defaultValue = "false")
    public boolean skip;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (skip) {
            getLog().info("Redis server had been skipped...");
            return;
        }

        RedisServer redisServer = (RedisServer) getPluginContext().get(RunRedisMojo.REDIS_SERVER_INSTANCE);
        if (redisServer != null) {
            try {
                getLog().info("Shutting down Redis server ...");
                redisServer.stop();
                getLog().info("Redis server stopped.");
            } catch (InterruptedException e) {
                getLog().error(e);
            }
        }
    }
}
