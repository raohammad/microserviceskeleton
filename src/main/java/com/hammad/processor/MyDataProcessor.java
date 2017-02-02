package com.hammad.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import com.hammad.domain.Data;

@EnableBinding(Processor.class)
public class MyDataProcessor {
	
	private final int VERSION=100;
	private static Logger logger = LoggerFactory.getLogger(MyDataProcessor.class);
	
	/*
	 * Requirement: receive the message from topic queue
	 */
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Data processData(Data receivedData) {
		logger.info("Received at DataProcessor: ProcessorId:" + receivedData.toString());
		
		// Change data
		receivedData.setProcessor_version(this.VERSION+"");
		
		// Send to final topic queue
		return receivedData;
	}
}
