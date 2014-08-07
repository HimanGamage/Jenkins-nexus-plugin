package org.wso2.downstream;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import jenkins.model.Jenkins;
import jenkins.mvn.GlobalSettingsProvider;
import jenkins.mvn.SettingsProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.deployer.ArtifactDeploymentException;
import org.apache.maven.artifact.metadata.ArtifactMetadata;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.ArtifactRepositoryFactory;
import org.apache.maven.artifact.repository.ArtifactRepositoryPolicy;
import org.apache.maven.artifact.repository.Authentication;
import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.cli.transfer.BatchModeMavenTransferListener;
import org.apache.maven.repository.Proxy;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

import hudson.FilePath;
import hudson.Launcher;
import hudson.Util;
import hudson.maven.MavenBuild;
import hudson.maven.MavenEmbedder;
import hudson.maven.MavenEmbedderException;
import hudson.maven.MavenEmbedderRequest;
import hudson.maven.MavenModule;
import hudson.maven.MavenModuleSet;
import hudson.maven.MavenModuleSetBuild;
import hudson.maven.MavenUtil;
import hudson.maven.RedeployPublisher;
import hudson.maven.reporters.MavenAbstractArtifactRecord;
import hudson.maven.reporters.MavenArtifactRecord;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Node;
import hudson.model.Result;
import hudson.model.TaskListener;
import hudson.remoting.Callable;
import hudson.tasks.Maven.MavenInstallation;

public class Test {
	public String id = "deploymentrepo";
	public final boolean evenIfUnstable = false;
	public final String releaseEnvVar = null;
	/**
	 * Repository URL to deploy artifacts to.
	 */
	public String url = "http://localhost:8081/nexus/content/repositories/snapshots/";
	public boolean uniqueVersion = true;
	private static final Logger LOG = Logger.getLogger(Test.class.getName());

	public boolean testMethod(AbstractBuild<?, ?> build, TaskListener listener)
			throws IOException, InterruptedException {
		LOG.log(Level.INFO,
				"####################### Redeploypublisher performer");
		LOG.log(Level.INFO,
				"####################### Redeploypublisher performer url :"
						+ url);

		if (build.getResult().isWorseThan(getTreshold()))
			return true; // build failed. Don't publish

		/**
		 * Check if we should skip or not
		 */
		if (releaseEnvVar != null) {
			LOG.log(Level.INFO,
					"####################### Redeploypublisher performer A");
			String envVarValue = build.getEnvironment(listener).get(
					releaseEnvVar);
			if ("true".equals(envVarValue)) { // null or false are ignored
				// listener.getLogger()
				// .println(
				// "[INFO] Skipping deploying artifact as release build is in progress.");
				return true; // skip the deploy
			}
		}

		List<MavenAbstractArtifactRecord> mavenAbstractArtifactRecords = getActions(
				build, listener);
		LOG.log(Level.INFO, "####################### List size :"
				+ mavenAbstractArtifactRecords.size());
		if (mavenAbstractArtifactRecords == null
				|| mavenAbstractArtifactRecords.isEmpty()) {
			LOG.log(Level.INFO,
					"####################### Redeploypublisher performer B");
			// listener.getLogger()
			// .println(
			// "[ERROR] No artifacts are recorded. Is this a Maven project?");
			build.setResult(Result.FAILURE);
			return true;
		}

		if (build instanceof MavenModuleSetBuild
				&& ((MavenModuleSetBuild) build).getParent()
						.isArchivingDisabled()) {
			LOG.log(Level.INFO,
					"####################### Redeploypublisher performer C");
			// listener.getLogger().println("[ERROR] You cannot use the \"Deploy artifacts to Maven repository\" feature if you "
			// +
			// "disabled automatic artifact archiving");
			build.setResult(Result.FAILURE);
			return true;
		}

		long startupTime = System.currentTimeMillis();
		try {
			MavenEmbedder embedder = createEmbedder(listener, build);
			ArtifactRepositoryLayout layout = (ArtifactRepositoryLayout) embedder
					.lookup(ArtifactRepositoryLayout.ROLE, "default");
			ArtifactRepositoryFactory factory = (ArtifactRepositoryFactory) embedder
					.lookup(ArtifactRepositoryFactory.ROLE);
			ArtifactRepository artifactRepository = null;
			if (url != null) {
				// By default we try to get the repository definition from the
				// job configuration
				artifactRepository = getDeploymentRepository(factory, layout,
						id, url);
			}
			for (MavenAbstractArtifactRecord mavenAbstractArtifactRecord : mavenAbstractArtifactRecords) {
				if (artifactRepository == null
						&& mavenAbstractArtifactRecord instanceof MavenArtifactRecord) {
					// If no repository definition is set on the job level we
					// try to take it from the POM
					MavenArtifactRecord mavenArtifactRecord = (MavenArtifactRecord) mavenAbstractArtifactRecord;
					artifactRepository = getDeploymentRepository(factory,
							layout, mavenArtifactRecord.repositoryId,
							mavenArtifactRecord.repositoryUrl);
				}
				if (artifactRepository == null) {
					listener.getLogger()
							.println(
									"[ERROR] No Repository settings defined in the job configuration or distributionManagement of the module.");
					build.setResult(Result.FAILURE);
					return true;
				}
				LOG.log(Level.INFO,
						"####################### Before deploy");

				mavenAbstractArtifactRecord.deploy(embedder,
						artifactRepository, listener);
				LOG.log(Level.INFO,
						"####################### after deploy");

			}
			// listener.getLogger().println("[INFO] Deployment done in " +
			// Util.getTimeSpanString(System.currentTimeMillis() -
			// startupTime));
			return true;
		} catch (MavenEmbedderException e) {
			e.printStackTrace(listener.error(e.getMessage()));
		} catch (ComponentLookupException e) {
			e.printStackTrace(listener.error(e.getMessage()));

		} catch (ArtifactDeploymentException e) {
			e.printStackTrace(listener.error(e.getMessage()));
		}
		// failed
		build.setResult(Result.FAILURE);
		// listener.getLogger().println("[INFO] Deployment failed after " +
		// Util.getTimeSpanString(System.currentTimeMillis() - startupTime));
		return true;

	}

	protected Result getTreshold() {
		if (evenIfUnstable) {
			return Result.UNSTABLE;
		} else {
			return Result.SUCCESS;
		}
	}

	protected List<MavenAbstractArtifactRecord> getActions(
			AbstractBuild<?, ?> build, TaskListener listener) {
		List<MavenAbstractArtifactRecord> actions = new ArrayList<MavenAbstractArtifactRecord>();

		MavenModuleSetBuild mavenBuild = (MavenModuleSetBuild) build;
		LOG.log(Level.INFO,
				"####################### Redeploypublisher mavenmoduleset name:"
						+ mavenBuild.getFullDisplayName());
		if (mavenBuild == null) {
			LOG.log(Level.INFO, "####################### mavengetaction null");
			return actions;
		}
		LOG.log(Level.INFO,
				"####################### mavengetaction entity set size"
						+ mavenBuild.getModuleLastBuilds().entrySet().size());

		for (Entry<MavenModule, MavenBuild> e : mavenBuild
				.getModuleLastBuilds().entrySet()) {
			LOG.log(Level.INFO, "####################### mavengetaction for");
			MavenAbstractArtifactRecord a = e.getValue().getAction(
					MavenAbstractArtifactRecord.class);
			if (a == null) {
				LOG.log(Level.INFO,
						"####################### mavengetaction for a null");
				listener.getLogger().println(
						"No artifacts are recorded for module"
								+ e.getKey().getName()
								+ ". Is this a Maven project?");
			} else {
				LOG.log(Level.INFO,
						"####################### mavengetaction for add :"+a.getDisplayName());
				actions.add(a);
			}

		}
		return actions;
	}

	private ArtifactRepository getDeploymentRepository(
			ArtifactRepositoryFactory factory, ArtifactRepositoryLayout layout,
			String repositoryId, String repositoryUrl)
			throws ComponentLookupException {
		if (repositoryUrl == null)
			return null;
		final ArtifactRepository repository = factory
				.createDeploymentArtifactRepository(repositoryId,
						repositoryUrl, layout, uniqueVersion);
		return new WrappedArtifactRepository(repository, uniqueVersion);
	}

	private MavenEmbedder createEmbedder(TaskListener listener,
			AbstractBuild<?, ?> build) throws MavenEmbedderException,
			IOException, InterruptedException {
		MavenInstallation m = null;
		File settingsLoc = null, remoteGlobalSettingsFromConfig = null;
		String profiles = null;
		Properties systemProperties = null;
		String privateRepository = null;
		FilePath remoteSettingsFromConfig = null;

		File tmpSettings = File.createTempFile("jenkins", "temp-settings.xml");
		try {
			AbstractProject project = build.getProject();

			if (project instanceof MavenModuleSet) {
				MavenModuleSet mavenModuleSet = ((MavenModuleSet) project);
				profiles = mavenModuleSet.getProfiles();
				systemProperties = mavenModuleSet.getMavenProperties();

				String altSettingsPath = SettingsProvider
						.getSettingsRemotePath(
								((MavenModuleSet) project).getSettings(),
								build, listener);
				String remoteGlobalSettingsPath = GlobalSettingsProvider
						.getSettingsRemotePath(
								((MavenModuleSet) project).getGlobalSettings(),
								build, listener);
				if (remoteGlobalSettingsPath != null) {
					remoteGlobalSettingsFromConfig = new File(
							remoteGlobalSettingsPath);
				}

				Node buildNode = build.getBuiltOn();

				if (buildNode == null) {
					// assume that build was made on master
					buildNode = Jenkins.getInstance();
				}

				if (StringUtils.isBlank(altSettingsPath)) {
					// get userHome from the node where job has been executed
					String remoteUserHome = build.getWorkspace().act(
							new GetUserHome());
					altSettingsPath = remoteUserHome + "/.m2/settings.xml";
				}

				// we copy this file in the master in a temporary file
				FilePath filePath = new FilePath(tmpSettings);
				FilePath remoteSettings = build.getWorkspace().child(
						altSettingsPath);
				if (!remoteSettings.exists()) {
					// JENKINS-9084 we finally use $M2_HOME/conf/settings.xml as
					// maven does

					String mavenHome = ((MavenModuleSet) project).getMaven()
							.forNode(buildNode, listener).getHome();
					String settingsPath = mavenHome + "/conf/settings.xml";
					remoteSettings = build.getWorkspace().child(settingsPath);
				}
				listener.getLogger().println(
						"Maven RedeployPublisher use remote "
								+ (buildNode != null ? buildNode.getNodeName()
										: "local") + " maven settings from : "
								+ remoteSettings.getRemote());
				remoteSettings.copyTo(filePath);
				settingsLoc = tmpSettings;

			}

			MavenEmbedderRequest mavenEmbedderRequest = new MavenEmbedderRequest(
					listener, m != null ? m.getHomeDir() : null, profiles,
					systemProperties, privateRepository, settingsLoc);

			if (remoteGlobalSettingsFromConfig != null) {
				mavenEmbedderRequest
						.setGlobalSettings(remoteGlobalSettingsFromConfig);
			}

			mavenEmbedderRequest
					.setTransferListener(new BatchModeMavenTransferListener(
							listener.getLogger()));

			return MavenUtil.createEmbedder(mavenEmbedderRequest);
		} finally {
			if (tmpSettings != null) {
				tmpSettings.delete();
			}
		}
	}

	private static final class GetUserHome implements
			Callable<String, IOException> {
		private static final long serialVersionUID = -8755705771716056636L;

		public String call() throws IOException {
			return System.getProperty("user.home");
		}
	}
	public static class WrappedArtifactRepository implements ArtifactRepository {
        private ArtifactRepository artifactRepository;
        private boolean uniqueVersion;
        public WrappedArtifactRepository (ArtifactRepository artifactRepository, boolean uniqueVersion)
        {
            this.artifactRepository = artifactRepository;
            this.uniqueVersion = uniqueVersion;
        }
        public String pathOf( Artifact artifact )
        {
            return artifactRepository.pathOf( artifact );
        }
        public String pathOfRemoteRepositoryMetadata( ArtifactMetadata artifactMetadata )
        {
            return artifactRepository.pathOfRemoteRepositoryMetadata( artifactMetadata );
        }
        public String pathOfLocalRepositoryMetadata( ArtifactMetadata metadata, ArtifactRepository repository )
        {
            return artifactRepository.pathOfLocalRepositoryMetadata( metadata, repository );
        }
        public String getUrl()
        {
            return artifactRepository.getUrl();
        }
        public void setUrl( String url )
        {
            artifactRepository.setUrl( url );
        }
        public String getBasedir()
        {
            return artifactRepository.getBasedir();
        }
        public String getProtocol()
        {
            return artifactRepository.getProtocol();
        }
        public String getId()
        {
            return artifactRepository.getId();
        }
        public void setId( String id )
        {
            artifactRepository.setId( id );
        }
        public ArtifactRepositoryPolicy getSnapshots()
        {
            return artifactRepository.getSnapshots();
        }
        public void setSnapshotUpdatePolicy( ArtifactRepositoryPolicy policy )
        {
            artifactRepository.setSnapshotUpdatePolicy( policy );
        }
        public ArtifactRepositoryPolicy getReleases()
        {
            return artifactRepository.getReleases();
        }
        public void setReleaseUpdatePolicy( ArtifactRepositoryPolicy policy )
        {
            artifactRepository.setReleaseUpdatePolicy( policy );
        }
        public ArtifactRepositoryLayout getLayout()
        {
            return artifactRepository.getLayout();
        }
        public void setLayout( ArtifactRepositoryLayout layout )
        {
            artifactRepository.setLayout( layout );
        }
        public String getKey()
        {
            return artifactRepository.getKey();
        }
        public boolean isUniqueVersion()
        {
            return this.uniqueVersion;
        }
        
        public void setUniqueVersion(boolean uniqueVersion) {
            this.uniqueVersion = uniqueVersion;
        }
        
        public boolean isBlacklisted()
        {
            return artifactRepository.isBlacklisted();
        }
        public void setBlacklisted( boolean blackListed )
        {
            artifactRepository.setBlacklisted( blackListed );
        }
        public Artifact find( Artifact artifact )
        {
            return artifactRepository.find( artifact );
        }
        public List<String> findVersions( Artifact artifact )
        {
            return artifactRepository.findVersions( artifact );
        }
        public boolean isProjectAware()
        {
            return artifactRepository.isProjectAware();
        }
        public void setAuthentication( Authentication authentication )
        {
            artifactRepository.setAuthentication( authentication );
        }
        public Authentication getAuthentication()
        {
            return artifactRepository.getAuthentication();
        }
        public void setProxy( Proxy proxy )
        {
            artifactRepository.setProxy( proxy );
        }
        public Proxy getProxy()
        {
            return artifactRepository.getProxy();
        }
        public List<ArtifactRepository> getMirroredRepositories()
        {
            return Collections.emptyList();
        }
        public void setMirroredRepositories( List<ArtifactRepository> arg0 )
        {
            // noop            
        }
    } 

}
