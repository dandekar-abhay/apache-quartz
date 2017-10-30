package com.samples.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Hello world!
 *
 */
public class AppJob implements Job
{
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("Job Called " + AppJob.class);
	}
	
}
