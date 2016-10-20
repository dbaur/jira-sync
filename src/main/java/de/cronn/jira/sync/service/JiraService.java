package de.cronn.jira.sync.service;

import java.net.URL;
import java.time.Instant;
import java.util.List;

import de.cronn.jira.sync.config.JiraConnectionProperties;
import de.cronn.jira.sync.domain.JiraIssue;
import de.cronn.jira.sync.domain.JiraIssueUpdate;
import de.cronn.jira.sync.domain.JiraPriority;
import de.cronn.jira.sync.domain.JiraProject;
import de.cronn.jira.sync.domain.JiraRemoteLink;
import de.cronn.jira.sync.domain.JiraResolution;
import de.cronn.jira.sync.domain.JiraServerInfo;
import de.cronn.jira.sync.domain.JiraTransition;
import de.cronn.jira.sync.domain.JiraUser;
import de.cronn.jira.sync.domain.JiraVersion;

public interface JiraService extends AutoCloseable {

	URL getUrl();

	void login(JiraConnectionProperties connectionProperties);

	void logout();

	void evictAllCaches();

	@Override
	void close();

	JiraServerInfo getServerInfo();

	JiraUser getMyself();

	JiraUser getUserByName(String username);

	JiraIssue getIssueByKey(String key);

	JiraProject getProjectByKey(String projectKey);

	List<JiraPriority> getPriorities();

	List<JiraResolution> getResolutions();

	List<JiraVersion> getVersions(String projectKey);

	List<JiraIssue> getIssuesByFilterId(String filterId);

	List<JiraRemoteLink> getRemoteLinks(String issueKey, Instant ifModifiedSince);

	List<JiraTransition> getTransitions(String issueKey);

	void addRemoteLink(JiraIssue fromIssue, JiraIssue toIssue, JiraService toJiraService, URL remoteLinkIcon);

	void addComment(String issueKey, String commentText);

	JiraIssue createIssue(JiraIssue issue);

	void updateIssue(String issueKey, JiraIssueUpdate issueUpdate);

	void transitionIssue(String issueKey, JiraIssueUpdate issueUpdate);
}
