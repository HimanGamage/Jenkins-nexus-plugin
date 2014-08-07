// CHECKSTYLE:OFF

package hudson.maven;

import org.jvnet.localizer.Localizable;
import org.jvnet.localizer.ResourceBundleHolder;

@SuppressWarnings({
    "",
    "PMD"
})
public class Messages {

    private final static ResourceBundleHolder holder = ResourceBundleHolder.get(Messages.class);

    /**
     * Maven Home {0} doesn’t exist
     * 
     */
    public static String MavenVersionCallable_MavenHomeDoesntExist(Object arg1) {
        return holder.format("MavenVersionCallable.MavenHomeDoesntExist", arg1);
    }

    /**
     * Maven Home {0} doesn’t exist
     * 
     */
    public static Localizable _MavenVersionCallable_MavenHomeDoesntExist(Object arg1) {
        return new Localizable(holder, "MavenVersionCallable.MavenHomeDoesntExist", arg1);
    }

    /**
     * Downloaded artifact {0}{1}
     * 
     */
    public static String MavenModuleSetBuild_DownloadedArtifact(Object arg1, Object arg2) {
        return holder.format("MavenModuleSetBuild.DownloadedArtifact", arg1, arg2);
    }

    /**
     * Downloaded artifact {0}{1}
     * 
     */
    public static Localizable _MavenModuleSetBuild_DownloadedArtifact(Object arg1, Object arg2) {
        return new Localizable(holder, "MavenModuleSetBuild.DownloadedArtifact", arg1, arg2);
    }

    /**
     * Kept because {0} is kept
     * 
     */
    public static String MavenBuild_KeptBecauseOfParent(Object arg1) {
        return holder.format("MavenBuild.KeptBecauseOfParent", arg1);
    }

    /**
     * Kept because {0} is kept
     * 
     */
    public static Localizable _MavenBuild_KeptBecauseOfParent(Object arg1) {
        return new Localizable(holder, "MavenBuild.KeptBecauseOfParent", arg1);
    }

    /**
     * Release New Version
     * 
     */
    public static String ReleaseAction_DisplayName() {
        return holder.format("ReleaseAction.DisplayName");
    }

    /**
     * Release New Version
     * 
     */
    public static Localizable _ReleaseAction_DisplayName() {
        return new Localizable(holder, "ReleaseAction.DisplayName");
    }

    /**
     * Monitor Maven Process
     * 
     */
    public static String MavenProbeAction_DisplayName() {
        return holder.format("MavenProbeAction.DisplayName");
    }

    /**
     * Monitor Maven Process
     * 
     */
    public static Localizable _MavenProbeAction_DisplayName() {
        return new Localizable(holder, "MavenProbeAction.DisplayName");
    }

    /**
     * No such file {0}
     * Perhaps you need to specify the correct POM file path in the project configuration?
     * 
     */
    public static String MavenModuleSetBuild_NoSuchPOMFile(Object arg1) {
        return holder.format("MavenModuleSetBuild.NoSuchPOMFile", arg1);
    }

    /**
     * No such file {0}
     * Perhaps you need to specify the correct POM file path in the project configuration?
     * 
     */
    public static Localizable _MavenModuleSetBuild_NoSuchPOMFile(Object arg1) {
        return new Localizable(holder, "MavenModuleSetBuild.NoSuchPOMFile", arg1);
    }

    /**
     * Discovered a new module {0} {1}
     * 
     */
    public static String MavenModuleSetBuild_DiscoveredModule(Object arg1, Object arg2) {
        return holder.format("MavenModuleSetBuild.DiscoveredModule", arg1, arg2);
    }

    /**
     * Discovered a new module {0} {1}
     * 
     */
    public static Localizable _MavenModuleSetBuild_DiscoveredModule(Object arg1, Object arg2) {
        return new Localizable(holder, "MavenModuleSetBuild.DiscoveredModule", arg1, arg2);
    }

    /**
     * The current installed version of 'config-file-provider' is not compatible with this core anymore (requires >= 2.3)
     * 
     */
    public static String PluginImpl_updateConfiProvider() {
        return holder.format("PluginImpl.updateConfiProvider");
    }

    /**
     * The current installed version of 'config-file-provider' is not compatible with this core anymore (requires >= 2.3)
     * 
     */
    public static Localizable _PluginImpl_updateConfiProvider() {
        return new Localizable(holder, "PluginImpl.updateConfiProvider");
    }

    /**
     * No classworlds*.jar found in {0} -- Is this a valid maven directory?
     * 
     */
    public static String MavenProcessFactory_ClassWorldsNotFound(Object arg1) {
        return holder.format("MavenProcessFactory.ClassWorldsNotFound", arg1);
    }

    /**
     * No classworlds*.jar found in {0} -- Is this a valid maven directory?
     * 
     */
    public static Localizable _MavenProcessFactory_ClassWorldsNotFound(Object arg1) {
        return new Localizable(holder, "MavenProcessFactory.ClassWorldsNotFound", arg1);
    }

    /**
     * A Maven installation needs to be available for this project to be built.Either your server has no Maven installations defined, or the requested Maven version does not exist.
     * 
     */
    public static String MavenModuleSetBuild_NoMavenConfigured() {
        return holder.format("MavenModuleSetBuild.NoMavenConfigured");
    }

    /**
     * A Maven installation needs to be available for this project to be built.Either your server has no Maven installations defined, or the requested Maven version does not exist.
     * 
     */
    public static Localizable _MavenModuleSetBuild_NoMavenConfigured() {
        return new Localizable(holder, "MavenModuleSetBuild.NoMavenConfigured");
    }

    /**
     * No such settings file {0} exists
     * Please verify that your alternate settings file is specified properly and exists in the workspace.
     * 
     */
    public static String MavenModuleSetBuild_NoSuchAlternateSettings(Object arg1) {
        return holder.format("MavenModuleSetBuild.NoSuchAlternateSettings", arg1);
    }

    /**
     * No such settings file {0} exists
     * Please verify that your alternate settings file is specified properly and exists in the workspace.
     * 
     */
    public static Localizable _MavenModuleSetBuild_NoSuchAlternateSettings(Object arg1) {
        return new Localizable(holder, "MavenModuleSetBuild.NoSuchAlternateSettings", arg1);
    }

    /**
     * Maven failed with error.
     * 
     */
    public static String MavenBuilder_Failed() {
        return holder.format("MavenBuilder.Failed");
    }

    /**
     * Maven failed with error.
     * 
     */
    public static Localizable _MavenBuilder_Failed() {
        return new Localizable(holder, "MavenBuilder.Failed");
    }

    /**
     * Failed to transfer {0}
     * 
     */
    public static String MavenModuleSetBuild_FailedToTransfer(Object arg1) {
        return holder.format("MavenModuleSetBuild.FailedToTransfer", arg1);
    }

    /**
     * Failed to transfer {0}
     * 
     */
    public static Localizable _MavenModuleSetBuild_FailedToTransfer(Object arg1) {
        return new Localizable(holder, "MavenModuleSetBuild.FailedToTransfer", arg1);
    }

    /**
     * Using settings.xml at {0} and private repository at {1}
     * 
     */
    public static String MavenModuleSetBuild_SettinsgXmlAndPrivateRepository(Object arg1, Object arg2) {
        return holder.format("MavenModuleSetBuild.SettinsgXmlAndPrivateRepository", arg1, arg2);
    }

    /**
     * Using settings.xml at {0} and private repository at {1}
     * 
     */
    public static Localizable _MavenModuleSetBuild_SettinsgXmlAndPrivateRepository(Object arg1, Object arg2) {
        return new Localizable(holder, "MavenModuleSetBuild.SettinsgXmlAndPrivateRepository", arg1, arg2);
    }

    /**
     * Aborted
     * 
     */
    public static String MavenBuilder_Aborted() {
        return holder.format("MavenBuilder.Aborted");
    }

    /**
     * Aborted
     * 
     */
    public static Localizable _MavenBuilder_Aborted() {
        return new Localizable(holder, "MavenBuilder.Aborted");
    }

    /**
     * Maven project
     * 
     */
    public static String MavenModuleSet_Pronoun() {
        return holder.format("MavenModuleSet.Pronoun");
    }

    /**
     * Maven project
     * 
     */
    public static Localizable _MavenModuleSet_Pronoun() {
        return new Localizable(holder, "MavenModuleSet.Pronoun");
    }

    /**
     * Build failed before it gets to this module
     * 
     */
    public static String MavenBuild_FailedEarlier() {
        return holder.format("MavenBuild.FailedEarlier");
    }

    /**
     * Build failed before it gets to this module
     * 
     */
    public static Localizable _MavenBuild_FailedEarlier() {
        return new Localizable(holder, "MavenBuild.FailedEarlier");
    }

    /**
     * Deploy artifacts to Maven repository
     * 
     */
    public static String RedeployPublisher_getDisplayName() {
        return holder.format("RedeployPublisher.getDisplayName");
    }

    /**
     * Deploy artifacts to Maven repository
     * 
     */
    public static Localizable _RedeployPublisher_getDisplayName() {
        return new Localizable(holder, "RedeployPublisher.getDisplayName");
    }

    /**
     * Module
     * 
     */
    public static String MavenModule_Pronoun() {
        return holder.format("MavenModule.Pronoun");
    }

    /**
     * Module
     * 
     */
    public static Localizable _MavenModule_Pronoun() {
        return new Localizable(holder, "MavenModule.Pronoun");
    }

    /**
     * Triggering a new build of {0}
     * 
     */
    public static String MavenBuild_Triggering(Object arg1) {
        return holder.format("MavenBuild.Triggering", arg1);
    }

    /**
     * Triggering a new build of {0}
     * 
     */
    public static Localizable _MavenBuild_Triggering(Object arg1) {
        return new Localizable(holder, "MavenBuild.Triggering", arg1);
    }

    /**
     * ERROR: 'config-file-provider' is not installed or disabled, therefore the config can't be fully loaded!!
     * 
     */
    public static String MavenModuleSet_readResolve_missingConfigProvider() {
        return holder.format("MavenModuleSet.readResolve_missingConfigProvider");
    }

    /**
     * ERROR: 'config-file-provider' is not installed or disabled, therefore the config can't be fully loaded!!
     * 
     */
    public static Localizable _MavenModuleSet_readResolve_missingConfigProvider() {
        return new Localizable(holder, "MavenModuleSet.readResolve_missingConfigProvider");
    }

    /**
     * Found a module with path {0} but no associated project
     * 
     */
    public static String MavenModuleSetBuild_FoundModuleWithoutProject(Object arg1) {
        return holder.format("MavenModuleSetBuild.FoundModuleWithoutProject", arg1);
    }

    /**
     * Found a module with path {0} but no associated project
     * 
     */
    public static Localizable _MavenModuleSetBuild_FoundModuleWithoutProject(Object arg1) {
        return new Localizable(holder, "MavenModuleSetBuild.FoundModuleWithoutProject", arg1);
    }

    /**
     * Asynchronous execution failure
     * 
     */
    public static String MavenBuilder_AsyncFailed() {
        return holder.format("MavenBuilder.AsyncFailed");
    }

    /**
     * Asynchronous execution failure
     * 
     */
    public static Localizable _MavenBuilder_AsyncFailed() {
        return new Localizable(holder, "MavenBuilder.AsyncFailed");
    }

    /**
     * Deploy to Maven repository
     * 
     */
    public static String MavenRedeployer_DisplayName() {
        return holder.format("MavenRedeployer.DisplayName");
    }

    /**
     * Deploy to Maven repository
     * 
     */
    public static Localizable _MavenRedeployer_DisplayName() {
        return new Localizable(holder, "MavenRedeployer.DisplayName");
    }

    /**
     * ERROR: Please update the 'config-file-provider' plugin, the current version is not supported anymore! (id={0})
     * 
     */
    public static String MavenModuleSet_readResolve_updateConfigProvider(Object arg1) {
        return holder.format("MavenModuleSet.readResolve_updateConfigProvider", arg1);
    }

    /**
     * ERROR: Please update the 'config-file-provider' plugin, the current version is not supported anymore! (id={0})
     * 
     */
    public static Localizable _MavenModuleSet_readResolve_updateConfigProvider(Object arg1) {
        return new Localizable(holder, "MavenModuleSet.readResolve_updateConfigProvider", arg1);
    }

    /**
     * A Maven installation needs to be available for this project to be built.
     * Either your server has no Maven installations defined, or the requested Maven version does not exist.
     * 
     */
    public static String MavenModuleSetBuild_NoMavenInstall() {
        return holder.format("MavenModuleSetBuild.NoMavenInstall");
    }

    /**
     * A Maven installation needs to be available for this project to be built.
     * Either your server has no Maven installations defined, or the requested Maven version does not exist.
     * 
     */
    public static Localizable _MavenModuleSetBuild_NoMavenInstall() {
        return new Localizable(holder, "MavenModuleSetBuild.NoMavenInstall");
    }

    /**
     * Build a maven project
     * 
     */
    public static String MavenModuleSet_DiplayName() {
        return holder.format("MavenModuleSet.DiplayName");
    }

    /**
     * Build a maven project
     * 
     */
    public static Localizable _MavenModuleSet_DiplayName() {
        return new Localizable(holder, "MavenModuleSet.DiplayName");
    }

    /**
     * Reusing existing maven process
     * 
     */
    public static String ProcessCache_Reusing() {
        return holder.format("ProcessCache.Reusing");
    }

    /**
     * Reusing existing maven process
     * 
     */
    public static Localizable _ProcessCache_Reusing() {
        return new Localizable(holder, "ProcessCache.Reusing");
    }

    /**
     * Waiting for Jenkins to finish collecting data
     * 
     */
    public static String MavenBuilder_Waiting() {
        return holder.format("MavenBuilder.Waiting");
    }

    /**
     * Waiting for Jenkins to finish collecting data
     * 
     */
    public static Localizable _MavenBuilder_Waiting() {
        return new Localizable(holder, "MavenBuilder.Waiting");
    }

    /**
     * Failed to parse POMs
     * 
     */
    public static String MavenModuleSetBuild_FailedToParsePom() {
        return holder.format("MavenModuleSetBuild.FailedToParsePom");
    }

    /**
     * Failed to parse POMs
     * 
     */
    public static Localizable _MavenModuleSetBuild_FailedToParsePom() {
        return new Localizable(holder, "MavenModuleSetBuild.FailedToParsePom");
    }

    /**
     * Maven Home {0} is not a directory
     * 
     */
    public static String MavenVersionCallable_MavenHomeIsNotDirectory(Object arg1) {
        return holder.format("MavenVersionCallable.MavenHomeIsNotDirectory", arg1);
    }

    /**
     * Maven Home {0} is not a directory
     * 
     */
    public static Localizable _MavenVersionCallable_MavenHomeIsNotDirectory(Object arg1) {
        return new Localizable(holder, "MavenVersionCallable.MavenHomeIsNotDirectory", arg1);
    }

}
