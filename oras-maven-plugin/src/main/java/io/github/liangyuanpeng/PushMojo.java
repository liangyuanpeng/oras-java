package io.github.liangyuanpeng;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "push")
public class PushMojo extends AbstractMojo {

    @Parameter(name = "username", defaultValue = "")
    private String name;

    @Parameter(name = "password", defaultValue = "")
    private String password;

    public void execute() throws MojoExecutionException, MojoFailureException {
//        log.info("hello push wirh oras");
    }

}
