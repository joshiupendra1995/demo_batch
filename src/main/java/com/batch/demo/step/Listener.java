package com.batch.demo.step;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Upendra
 *
 */
@Slf4j
public class Listener extends JobExecutionListenerSupport {

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("Job Execution completed!!" + jobExecution.getStatus());
		System.exit(1);
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("Job is still running!!" + jobExecution.getStatus());

		if (jobExecution.isRunning()) {
			log.info("----------------------------");
		}
	}

}
