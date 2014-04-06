package edu.sjsu.cmpe.library.config;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class LibraryServiceConfiguration extends Configuration {

	private String apolloUser;
    private String apolloPassword;
    private String apolloHost;
    private int apolloPort;
    
    /**
	 * @return the apolloUser
	 */
	public String getApolloUser() {
		return apolloUser;
	}

	/**
	 * @param apolloUser the apolloUser to set
	 */
	public void setApolloUser(String apolloUser) {
		this.apolloUser = apolloUser;
	}

	/**
	 * @return the apolloPassword
	 */
	public String getApolloPassword() {
		return apolloPassword;
	}

	/**
	 * @param apolloPassword the apolloPassword to set
	 */
	public void setApolloPassword(String apolloPassword) {
		this.apolloPassword = apolloPassword;
	}

	/**
	 * @return the apolloHost
	 */
	public String getApolloHost() {
		return apolloHost;
	}

	/**
	 * @param apolloHost the apolloHost to set
	 */
	public void setApolloHost(String apolloHost) {
		this.apolloHost = apolloHost;
	}

	/**
	 * @return the apolloPort
	 */
	public int getApolloPort() {
		return apolloPort;
	}

	/**
	 * @param apolloPort the apolloPort to set
	 */
	public void setApolloPort(int apolloPort) {
		this.apolloPort = apolloPort;
	}

	@NotEmpty
    @JsonProperty
    private String stompQueueName;

    @NotEmpty
    @JsonProperty
    private String stompTopicName;

    @NotEmpty
    @JsonProperty
    private String libraryName;

    /**
     * @return the stompQueueName
     */
    public String getStompQueueName() {
	return stompQueueName;
    }

    /**
     * @param stompQueueName
     *            the stompQueueName to set
     */
    public void setStompQueueName(String stompQueueName) {
	this.stompQueueName = stompQueueName;
    }

    /**
     * @return the stompTopicName
     */
    public String getStompTopicName() {
	return stompTopicName;
    }

    /**
     * @param stompTopicName
     *            the stompTopicName to set
     */
    public void setStompTopicName(String stompTopicName) {
	this.stompTopicName = stompTopicName;
    }

    /**
     * @return the libraryName
     */
    public String getLibraryName() {
	return libraryName;
    }

    /**
     * @param libraryName
     *            the libraryName to set
     */
    public void setLibraryName(String libraryName) {
	this.libraryName = libraryName;
    }
}