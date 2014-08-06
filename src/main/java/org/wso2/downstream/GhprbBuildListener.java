package org.wso2.downstream;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.wso2.downstream.AutoMergeProcessor.DownstreamBuilds;

import hudson.Extension;
import hudson.Launcher;
import hudson.maven.MavenModuleSetBuild;
import hudson.maven.RedeployPublisher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Cause;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.model.TaskListener;
import hudson.model.Cause.UpstreamCause;
import hudson.model.Executor;
import hudson.model.Hudson;
import hudson.model.listeners.RunListener;
import hudson.triggers.Trigger;

/**
 * 
 * @author janinko
 */
@Extension
public class GhprbBuildListener extends RunListener<AbstractBuild> {
	/** The Logger. */
	private static final Logger LOG = Logger.getLogger(GhprbBuildListener.class
			.getName());

	@Override
	public void onStarted(AbstractBuild build, TaskListener listener) {

	}

	@Override
	public void onCompleted(AbstractBuild build, TaskListener listener) {
		LOG.log(Level.INFO, "####################### Himans new plugin build :");
		try {
			if(build instanceof MavenModuleSetBuild){
				LOG.log(Level.INFO, "####################### is a instace of mavenModulesetbuild ");
				List<DownstreamBuilds> downStreamBuilds=new AutoMergeProcessor().getDownstreamBuildList(build);
				if(downStreamBuilds.size()==0){
					
				}
				
				
				
				new Test().testMethod(build, listener);
			}else{
				LOG.log(Level.INFO, "####################### is not a instace of mavenModulesetbuild ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}