// CHECKSTYLE:OFF

package hudson.maven.reporters;

import org.jvnet.localizer.Localizable;
import org.jvnet.localizer.ResourceBundleHolder;

@SuppressWarnings({
    "",
    "PMD"
})
public class Messages {

    private final static ResourceBundleHolder holder = ResourceBundleHolder.get(Messages.class);

    /**
     * Deploying the attached artifact {0}
     * 
     */
    public static String MavenArtifact_DeployingAttachedArtifact(Object arg1) {
        return holder.format("MavenArtifact.DeployingAttachedArtifact", arg1);
    }

    /**
     * Deploying the attached artifact {0}
     * 
     */
    public static Localizable _MavenArtifact_DeployingAttachedArtifact(Object arg1) {
        return new Localizable(holder, "MavenArtifact.DeployingAttachedArtifact", arg1);
    }

    /**
     * Maven report output goes to {0}, which is outside project reporting path{1}
     * 
     */
    public static String ReportCollector_OutsideSite(Object arg1, Object arg2) {
        return holder.format("ReportCollector.OutsideSite", arg1, arg2);
    }

    /**
     * Maven report output goes to {0}, which is outside project reporting path{1}
     * 
     */
    public static Localizable _ReportCollector_OutsideSite(Object arg1, Object arg2) {
        return new Localizable(holder, "ReportCollector.OutsideSite", arg1, arg2);
    }

    /**
     * Unable to obtain the reportsDirectory from surefire:test mojo
     * 
     */
    public static String SurefireArchiver_NoReportsDir() {
        return holder.format("SurefireArchiver.NoReportsDir");
    }

    /**
     * Unable to obtain the reportsDirectory from surefire:test mojo
     * 
     */
    public static Localizable _SurefireArchiver_NoReportsDir() {
        return new Localizable(holder, "SurefireArchiver.NoReportsDir");
    }

    /**
     * Maven reports
     * 
     */
    public static String ReportAction_DisplayName() {
        return holder.format("ReportAction.DisplayName");
    }

    /**
     * Maven reports
     * 
     */
    public static Localizable _ReportAction_DisplayName() {
        return new Localizable(holder, "ReportAction.DisplayName");
    }

    /**
     * Record fingerprints
     * 
     */
    public static String MavenFingerprinter_DisplayName() {
        return holder.format("MavenFingerprinter.DisplayName");
    }

    /**
     * Record fingerprints
     * 
     */
    public static Localizable _MavenFingerprinter_DisplayName() {
        return new Localizable(holder, "MavenFingerprinter.DisplayName");
    }

    /**
     * Unable to copy Javadoc from {0} to {1}
     * 
     */
    public static String MavenJavadocArchiver_FailedToCopy(Object arg1, Object arg2) {
        return holder.format("MavenJavadocArchiver.FailedToCopy", arg1, arg2);
    }

    /**
     * Unable to copy Javadoc from {0} to {1}
     * 
     */
    public static Localizable _MavenJavadocArchiver_FailedToCopy(Object arg1, Object arg2) {
        return new Localizable(holder, "MavenJavadocArchiver.FailedToCopy", arg1, arg2);
    }

    /**
     * Redeploy Artifacts
     * 
     */
    public static String MavenAbstractArtifactRecord_Displayname() {
        return holder.format("MavenAbstractArtifactRecord.Displayname");
    }

    /**
     * Redeploy Artifacts
     * 
     */
    public static Localizable _MavenAbstractArtifactRecord_Displayname() {
        return new Localizable(holder, "MavenAbstractArtifactRecord.Displayname");
    }

    /**
     * Archive the artifacts
     * 
     */
    public static String MavenArtifactArchiver_DisplayName() {
        return holder.format("MavenArtifactArchiver.DisplayName");
    }

    /**
     * Archive the artifacts
     * 
     */
    public static Localizable _MavenArtifactArchiver_DisplayName() {
        return new Localizable(holder, "MavenArtifactArchiver.DisplayName");
    }

    /**
     * Maven-generated site
     * 
     */
    public static String MavenSiteArchiver_DisplayName() {
        return holder.format("MavenSiteArchiver.DisplayName");
    }

    /**
     * Maven-generated site
     * 
     */
    public static Localizable _MavenSiteArchiver_DisplayName() {
        return new Localizable(holder, "MavenSiteArchiver.DisplayName");
    }

    /**
     * [JENKINS] Recording test results
     * 
     */
    public static String SurefireArchiver_Recording() {
        return holder.format("SurefireArchiver.Recording");
    }

    /**
     * [JENKINS] Recording test results
     * 
     */
    public static Localizable _SurefireArchiver_Recording() {
        return new Localizable(holder, "SurefireArchiver.Recording");
    }

    /**
     * Record build information
     * 
     */
    public static String BuildInfoRecorder_DisplayName() {
        return holder.format("BuildInfoRecorder.DisplayName");
    }

    /**
     * Record build information
     * 
     */
    public static Localizable _BuildInfoRecorder_DisplayName() {
        return new Localizable(holder, "BuildInfoRecorder.DisplayName");
    }

    /**
     * Publish javadoc
     * 
     */
    public static String MavenJavadocArchiver_DisplayName() {
        return holder.format("MavenJavadocArchiver.DisplayName");
    }

    /**
     * Publish javadoc
     * 
     */
    public static Localizable _MavenJavadocArchiver_DisplayName() {
        return new Localizable(holder, "MavenJavadocArchiver.DisplayName");
    }

    /**
     * E-mail Notification
     * 
     */
    public static String MavenMailer_DisplayName() {
        return holder.format("MavenMailer.DisplayName");
    }

    /**
     * E-mail Notification
     * 
     */
    public static Localizable _MavenMailer_DisplayName() {
        return new Localizable(holder, "MavenMailer.DisplayName");
    }

    /**
     * Deploying the main artifact {0}
     * 
     */
    public static String MavenArtifact_DeployingMainArtifact(Object arg1) {
        return holder.format("MavenArtifact.DeployingMainArtifact", arg1);
    }

    /**
     * Deploying the main artifact {0}
     * 
     */
    public static Localizable _MavenArtifact_DeployingMainArtifact(Object arg1) {
        return new Localizable(holder, "MavenArtifact.DeployingMainArtifact", arg1);
    }

    /**
     * Unable to obtain the destDir from javadoc mojo
     * 
     */
    public static String MavenJavadocArchiver_NoDestDir() {
        return holder.format("MavenJavadocArchiver.NoDestDir");
    }

    /**
     * Unable to obtain the destDir from javadoc mojo
     * 
     */
    public static Localizable _MavenJavadocArchiver_NoDestDir() {
        return new Localizable(holder, "MavenJavadocArchiver.NoDestDir");
    }

    /**
     * Publish Test javadoc
     * 
     */
    public static String MavenTestJavadocArchiver_DisplayName() {
        return holder.format("MavenTestJavadocArchiver.DisplayName");
    }

    /**
     * Publish Test javadoc
     * 
     */
    public static Localizable _MavenTestJavadocArchiver_DisplayName() {
        return new Localizable(holder, "MavenTestJavadocArchiver.DisplayName");
    }

    /**
     * Publish surefire reports
     * 
     */
    public static String SurefireArchiver_DisplayName() {
        return holder.format("SurefireArchiver.DisplayName");
    }

    /**
     * Publish surefire reports
     * 
     */
    public static Localizable _SurefireArchiver_DisplayName() {
        return new Localizable(holder, "SurefireArchiver.DisplayName");
    }

    /**
     * Record Maven reports
     * 
     */
    public static String ReportCollector_DisplayName() {
        return holder.format("ReportCollector.DisplayName");
    }

    /**
     * Record Maven reports
     * 
     */
    public static Localizable _ReportCollector_DisplayName() {
        return new Localizable(holder, "ReportCollector.DisplayName");
    }

    /**
     * Deployment History
     * 
     */
    public static String HistoryWidgetImpl_Displayname() {
        return holder.format("HistoryWidgetImpl.Displayname");
    }

    /**
     * Deployment History
     * 
     */
    public static Localizable _HistoryWidgetImpl_Displayname() {
        return new Localizable(holder, "HistoryWidgetImpl.Displayname");
    }

    /**
     * Failed to install artifact to the master
     * 
     */
    public static String MavenArtifactArchiver_FailedToInstallToMaster() {
        return holder.format("MavenArtifactArchiver.FailedToInstallToMaster");
    }

    /**
     * Failed to install artifact to the master
     * 
     */
    public static Localizable _MavenArtifactArchiver_FailedToInstallToMaster() {
        return new Localizable(holder, "MavenArtifactArchiver.FailedToInstallToMaster");
    }

}
