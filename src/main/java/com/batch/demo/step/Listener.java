package com.batch.demo.step;

import org.springframework.batch.core.BatchStatus;
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

		if (jobExecution.getStatus().equals(BatchStatus.COMPLETED)) {
			log.info("Job Execution completed!!" + jobExecution.getStatus());
			System.exit(1);
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		
		if (jobExecution.isRunning()) {
			log.info("Job is still running!!");
			log.info("----------------------------");
		}
	}

}
