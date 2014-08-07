package org.wso2.downstream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	Map<AbstractBuild, List<DownstreamBuilds>> downStreamBuilds = new ConcurrentHashMap<AbstractBuild, List<DownstreamBuilds>>();
	private Map<AbstractBuild, List<AbstractBuild>> downStreamFinishedBuildsMap = new ConcurrentHashMap<AbstractBuild, List<AbstractBuild>>();

	@Override
	public void onStarted(AbstractBuild build, TaskListener listener) {

	}

	@Override
	public void onCompleted(AbstractBuild build, TaskListener listener) {

		try {
			if (build instanceof MavenModuleSetBuild) {

				if (findCause(build) == build) {
					LOG.log(Level.INFO, "####################### build :"
							+ build.getFullDisplayName() + " caused by :"
							+ findCause(build).getFullDisplayName());
					List<DownstreamBuilds> downstreamBuildList = new AutoMergeProcessor()
							.getDownstreamBuildList(build);
					if (downstreamBuildList.size() == 0) {
						LOG.log(Level.INFO, "####################### build :"
								+ build.getFullDisplayName()
								+ " has no children");
						if (build.getResult() == Result.SUCCESS) {
							new Test().testMethod(build, listener);
						}else{
							LOG.log(Level.INFO, "####################### main build is not successful");
						}

					} else {
					
						if(build.getResult()==Result.SUCCESS){
							LOG.log(Level.INFO, "####################### build :"
									+ build.getFullDisplayName() + " has children");
							if (!downStreamBuilds.containsKey(build)) {
								downStreamBuilds.put(build, downstreamBuildList);

							}
							if (!downStreamFinishedBuildsMap
									.containsKey(findCause(build))) {
								LOG.log(Level.INFO,
										"####################### initialize the map for :"
												+ findCause(build));
								downStreamFinishedBuildsMap.put(findCause(build),
										new ArrayList<AbstractBuild>());
							}
						}else{
							LOG.log(Level.INFO, "####################### main build is not successful");
						}

					}

				} else {

					if (build.getResult() == Result.SUCCESS) {
						LOG.log(Level.INFO,
								"####################### add to the list");
						List<AbstractBuild> downStreamMapList = downStreamFinishedBuildsMap
								.get(findCause(build));
						if (downStreamMapList != null) {
							LOG.log(Level.INFO,
									"####################### downstreammaplist in not equal null");
							downStreamFinishedBuildsMap.get(findCause(build))
									.add(build);
							
							List<DownstreamBuilds> buildList = downStreamBuilds
									.get(findCause(build));

							if (downStreamFinishedBuildsMap.get(findCause(build))
									.size() == buildList.size()) {
								LOG.log(Level.INFO,
										"####################### finished the process with "
												+ downStreamBuilds.size() + " "
												+ downStreamFinishedBuildsMap.size());
								new Test().testMethod(findCause(build), listener);
								downStreamFinishedBuildsMap.remove(findCause(build));
								downStreamBuilds.remove(findCause(build));
								LOG.log(Level.INFO,
										"####################### finished the process with removing "
												+ downStreamBuilds.size() + " "
												+ downStreamFinishedBuildsMap.size());
							}
							
							
						}else{
							LOG.log(Level.INFO,
									"####################### downstreammaplist in not equal null not adding to list");
						}
						
						

					}else{
						LOG.log(Level.INFO,
								"####################### build faild and removing mainbuild data");
						downStreamFinishedBuildsMap.remove(findCause(build));
						downStreamBuilds.remove(findCause(build));
					}

			

				}

			} else {
				LOG.log(Level.INFO,
						"####################### is not a instace of mavenModulesetbuild ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private AbstractBuild findCause(AbstractBuild<?, ?> currentBuild) {

		AbstractBuild testBuild = currentBuild;
		Outer: for (Cause c : currentBuild.getCauses()) {

			if (!(c instanceof UpstreamCause)) {
				testBuild = currentBuild;
				break Outer;

			} else {
				UpstreamCause upcause = (UpstreamCause) c;
				String upProjectName = upcause.getUpstreamProject();
				int buildNumber = upcause.getUpstreamBuild();
				LOG.log(Level.INFO, "################ Cause Project name :"
						+ upProjectName + " Build no :" + buildNumber);
				AbstractProject upproject = Hudson
						.getInstance()
						.getItemByFullName(upProjectName, AbstractProject.class);
				AbstractBuild upBuild = (AbstractBuild) upproject
						.getBuildByNumber(buildNumber);
				testBuild = findCause(upBuild);
			}
		}
		return testBuild;
	}

}