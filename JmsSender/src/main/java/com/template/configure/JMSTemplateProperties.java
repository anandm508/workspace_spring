package com.template.configure;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "template.config")
public class JMSTemplateProperties {

	private List<String> brokerUrls;

	private List<String> qeues;

	private List<String> listiners;

	public List<String> getBrokerUrls() {
		return brokerUrls;
	}

	public void setBrokerUrls(List<String> brokerUrls) {
		this.brokerUrls = brokerUrls;
	}

	public List<String> getQeues() {
		return qeues;
	}

	public void setQeues(List<String> qeues) {
		this.qeues = qeues;
	}

	public List<String> getListiners() {
		return listiners;
	}

	public void setListiners(List<String> listiners) {
		this.listiners = listiners;
	}

}
