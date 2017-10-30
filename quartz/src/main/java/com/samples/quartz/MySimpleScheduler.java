package com.samples.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

public class MySimpleScheduler {

	static class MyHelloJobInnerClass implements Job{
		
		public void execute(JobExecutionContext arg0) throws JobExecutionException {
			System.out.println("Job Called");
		}
	}
	
	
	public static void main(String[] args) {

		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			System.out.println("Scheduler started");
			scheduler.start();

//			Job newJob = () -> {System.out.println("Job Called")};
// 			scheduler.scheduleJob( newJob( () -> {System.out.println("Job Called");} ).withIdentity("MyHelloJob").build(), newTrigger().startNow().withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever()).build());
//			scheduler.scheduleJob(newJob( new Job(){
//				public void execute(JobExecutionContext arg0) throws JobExecutionException {
//					System.out.println("Job Called");
//				}
//			}).withIdentity("MyHelloJob").build(), newTrigger().startNow().withSchedule(simpleSchedule().withIntervalInSeconds(10).repeatForever()).build());

			scheduler.scheduleJob(
					newJob( AppJob.class )
					// newJob( MyHelloJobInnerClass.class )
					.withIdentity("MyHelloJob")
					.build(),
					
					newTrigger().startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(1)
							.repeatForever()).build()
					);
			
			System.out.println("Waiting for thread to start");
			try {
				Thread.sleep(1000 * 65);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Waiting for thread to start");
			
			
			scheduler.shutdown();
			System.out.println("Scheduler shutdown");

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
