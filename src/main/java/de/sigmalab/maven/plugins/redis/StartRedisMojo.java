package de.sigmalab.maven.plugins.redis;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

/**
 * Starts the redis-server instance and place it into plugin-context.
 *
 * @author jbellmann
 */
@Mojo(name = "start", defaultPhase = LifecyclePhase.PRE_INTEGRATION_TEST)
public class StartRedisMojo extends AbstractMojo {

    static final String REDIS_SERVER_INSTANCE = "REDIS_SERVER_INSTANCE";

    @Parameter(property = "redis.server.port", defaultValue = "6379")
    public Integer port;

    @Parameter(property = "redis.server.skip", defaultValue = "false")
    public boolean skip;

    @Parameter(property = "redis.server.config")
    public File config;

    @Parameter
    public Master master;

    @Override
    public void execute() throws MojoExecutionException {
        if (skip) {
            getLog().info("Skipping Redis server...");
            return;
        }

        try {

            RedisServerBuilder redisServerBuilder = RedisServer.builder();
            redisServerBuilder = redisServerBuilder.port(port);
            if (master != null) {
                redisServerBuilder = redisServerBuilder.slaveOf(master.getHost(), master.getPort());
            }
            if (config != null) {

                redisServerBuilder = redisServerBuilder.configFile("/path/to/your/redis.conf");
            }
            RedisServer redisServer = redisServerBuilder.build();

            redisServer.start();

            getPluginContext().put(REDIS_SERVER_INSTANCE, redisServer);

            getLog().info("Redis-Server started on port : " + port);
        } catch (Exception e) {
            throw new MojoExecutionException("Unable to start REDIS", e);
        }

    }

}
