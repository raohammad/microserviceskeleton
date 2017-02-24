package com.hammad.sink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

import com.hammad.domain.Data;

/*
 * author: raohammad
 * Sink for domain object Data. 
 * Subscriber to DataSink.Sink.DATASINK queue
 * Change in application-prod.yml/application-dev.yml for topic names accordingly
 */

@EnableBinding(DataSink.Sink.class)
public class DataSink {
	
	private static Logger logger = LoggerFactory.getLogger(DataSink.class);
	
	/*
	 * Requirement: receive the message from topic queue
	 */
	@StreamListener(Sink.DATASINK)
	public void listen(Data sinkData) {
		
		logger.info("Received at DataSink: " + sinkData.toString());
		
	}
	
	public interface Sink {
		String DATASINK = "data-sink";

		@Input(DATASINK)
		SubscribableChannel dataSink();
	}
}
