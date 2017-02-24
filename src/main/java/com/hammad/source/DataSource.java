package com.hammad.source;

import java.util.Date;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.hammad.domain.Data;

/**
 * Configures Spring Cloud Stream support.
 *
 * See http://docs.spring.io/spring-cloud-stream/docs/current/reference/htmlsingle/
 * for more information.
 */

/*
 * author: raohammad
 * Processor of domain object Data. 
 * Publisher for DataSource.Source.DATASOURCE queue
 * Change in application-prod.yml/application-dev.yml for topic names accordingly
 */

@EnableBinding(DataSource.Source.class)
public class DataSource {

	@Bean
	@InboundChannelAdapter(value = Source.DATASOURCE, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
	public MessageSource<Data> dataMessageSource() {
		return () -> MessageBuilder.withPayload(new Data(new Date(),"source")).build();
	}
	
	public interface Source {
		String DATASOURCE = "data-source";

		@Output(DATASOURCE)
		MessageChannel dataSource();
	}
}
