package io.github.liangyuanpeng;

import io.github.liangyuanpeng.oras.Downloader;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mojo(name = "push")
public class PushMojo extends AbstractMojo {

    private static final Logger logger = LoggerFactory.getLogger(PushMojo.class);

    @Parameter(name = "username", defaultValue = "")
    private String name;

    @Parameter(name = "password", defaultValue = "")
    private String password;

    public void execute() throws MojoExecutionException, MojoFailureException {
        logger.info("hello push wirh oras");
        Downloader.getInstalce().download();
    }

}
