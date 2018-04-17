package com.template.configure;

import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.util.ClassUtils;

@Configuration
@ConditionalOnProperty(prefix = "qvc.Jms.template", value = "autoconfigure", havingValue = "true")
public class TemplateConfigurer implements InitializingBean {

	private static final String BROKER_URL = "template.config.broker-urls";
	private static final String QEUE_KEY = "template.config.qeues";
	private static final String LISTINER_KEY = "template.config.listiners";
	private static final String CONNECTION_FACTORY_PREFIX = "CF_";
	private static final String DSTINATION_PREFIX = "DEST_";
	private static final String LISTINER_PREFIX = "LST_TO_";

	private Set<String> brokerUrls = new TreeSet<>();
	private Set<String> qeues = new TreeSet<>();
	private Set<String> listiner = new TreeSet<>();
	private Properties configProperty;

	/**
	 * @return
	 * 
	 * @Description : Create ConnectionFactory Bean(ActiveMq) Based on the YAML
	 *              configuration. <BR>
	 *              Example :-<BR>
	 * 
	 * 
	 *              <pre>
	 *              Sample YAML configuration
	 *              	template:
	 *              	  config:
	 *              	   broker-urls:- tcp://127.0.0.1:61616
	 *              
	 *              Connection factory bean Name -> CF-61616
	 *              This name will be used for AutoWireing of this bean
	 *              </pre>
	 */
	@Bean
	public BeanFactoryPostProcessor createConnectionfactory() {
		return bf -> {
			BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) bf;
			brokerUrls.forEach(url -> {
				beanFactory.registerBeanDefinition(formatConnectionFactoryName(url),
						BeanDefinitionBuilder.genericBeanDefinition(ActiveMQConnectionFactory.class)
								.addPropertyValue("brokerURL", configProperty.getProperty(url)).getBeanDefinition());
			});

		};
	}

	/**
	 * @return
	 * @Description : Create JmsTemplate Bean Based on the YAML configuration
	 *              configuration. <BR>
	 *              Example :-<BR>
	 * 
	 *              <pre>
	 *              Sample YAML configuration
	 *              	template:
	 *              	  config:
	 *              	   broker-urls:- tcp://127.0.0.1:61616
	 *                    qeues:
	 *                     Test
	 *              QeueName = Test
	 *              JmsTemplate bean Name -> Test
	 *              This name will be used for AutoWireing of this bean
	 *              </pre>
	 */
	@Bean
	public BeanFactoryPostProcessor createJmsTemplate() {
		return bf -> {
			BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) bf;
			Object[] brokerurls = brokerUrls.toArray();
			Object[] qeueNames = qeues.toArray();
			for (int i = 0; i < qeueNames.length; i++) {
				String qeueName = configProperty.getProperty(qeueNames[i].toString());
				createActiveMqDestination(beanFactory, qeueName);
				beanFactory.registerBeanDefinition(qeueName,
						BeanDefinitionBuilder.genericBeanDefinition(JmsTemplate.class)
								.addConstructorArgValue(
										bf.getBean(formatConnectionFactoryName(brokerurls[i].toString())))
								.addPropertyValue("defaultDestination", bf.getBean(DSTINATION_PREFIX + qeueName))
								.getBeanDefinition());
			}
		};
	}

	/**
	 * @return
	 */
	@Bean
	public BeanFactoryPostProcessor createListiners() {
		return bf -> {
			Object[] listinerKey = listiner.toArray();
			Object[] qeueNames = qeues.toArray();
			BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) bf;
			JmsTemplate template = null;
			for (int i = 0; i < listinerKey.length; i++) {
				template = bf.getBean(configProperty.getProperty(qeueNames[i].toString()), JmsTemplate.class);
				try {
					Class<?> listinerClass = Class.forName(configProperty.getProperty(listinerKey[i].toString()));
					/* Register ListinerImpl Class */
					beanFactory.registerBeanDefinition(ClassUtils.getShortName(listinerClass),
							BeanDefinitionBuilder.genericBeanDefinition(listinerClass).getBeanDefinition());
					/* Register DefaultMessageListenerContainer */
					beanFactory.registerBeanDefinition(
							LISTINER_PREFIX + configProperty.getProperty(qeueNames[i].toString()),
							BeanDefinitionBuilder.genericBeanDefinition(DefaultMessageListenerContainer.class)
									.addPropertyValue("destination", template.getDefaultDestination())
									.addPropertyValue("connectionFactory", template.getConnectionFactory())
									.addPropertyValue("messageListener",
											bf.getBean(ClassUtils.getShortName(listinerClass), listinerClass))
									.getBeanDefinition());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	/**
	 * @param beanRegistry
	 * @param qeueName
	 * 
	 * @Description Use to create ActiveMqQeue type of object which will be used as
	 *              reference to jmsTemplate and listener
	 */
	private void createActiveMqDestination(BeanDefinitionRegistry beanRegistry, String qeueName) {

		beanRegistry.registerBeanDefinition(DSTINATION_PREFIX + qeueName, BeanDefinitionBuilder
				.genericBeanDefinition(ActiveMQQueue.class).addConstructorArgValue(qeueName).getBeanDefinition());
	}

	/**
	 * @param url
	 * @return formated String
	 * 
	 */
	private String formatConnectionFactoryName(String url) {
		return CONNECTION_FACTORY_PREFIX + configProperty.getProperty(url).split(":")[2];
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
		yaml.setResources(new ClassPathResource("application.yml"));
		configProperty = yaml.getObject();
		Set<Object> keys = configProperty.keySet();
		keys.forEach(key -> {
			if (key.toString().startsWith(BROKER_URL))
				brokerUrls.add(key.toString());
			else if (key.toString().startsWith(QEUE_KEY))
				qeues.add(key.toString());
			else if (key.toString().startsWith(LISTINER_KEY))
				listiner.add(key.toString());
		});

	}
}
