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
 * @author jbellmann
 */
@Mojo(name = "stop", defaultPhase = LifecyclePhase.POST_INTEGRATION_TEST)
public class StopRedisMojo extends AbstractMojo {

    @Parameter(property = "redis.server.skip", defaultValue = "false")
    public boolean skip;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (skip) {
            getLog().info("Redis server had been skipped...");
            return;
        }

        RedisServer redisServer = (RedisServer) getPluginContext().get(StartRedisMojo.REDIS_SERVER_INSTANCE);
        if (redisServer != null) {
            getLog().info("Shutting down Redis server ...");
            redisServer.stop();
            getLog().info("Redis server stopped.");
        }
    }
}
