package com.batch.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.batch.demo.entity.Employee;
import com.batch.demo.model.EmployeeDto;
import com.batch.demo.step.Listener;
import com.batch.demo.step.SkipPolicyJob;

/**
 * @author Upendra
 *
 */
@Configuration
@EnableBatchProcessing
@Order(Ordered.LOWEST_PRECEDENCE)
public class BatchConfig {

	/**
	 * @param jobBuilderFactory
	 * @param stepBuilderFactory
	 * @param itemReader
	 * @param itemProcessor
	 * @param itemWriter
	 * @return job
	 */
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<EmployeeDto> itemReader, ItemProcessor<EmployeeDto, Employee> itemProcessor,
			ItemWriter<Employee> itemWriter) {

		Step step = stepBuilderFactory.get("load-job").<EmployeeDto, Employee>chunk(10).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).faultTolerant().skipPolicy(skipPolicy()).taskExecutor(taskExecutor()).build();

		return jobBuilderFactory.get("load-emp-job").incrementer(new RunIdIncrementer()).listener(new Listener())
				.start(step).build();
	}

	/**
	 * @return itemReader
	 */
	@Bean
	public FlatFileItemReader<EmployeeDto> itemReader() {

		FlatFileItemReader<EmployeeDto> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(new ClassPathResource("sample-data.csv"));
		flatFileItemReader.setName("EMP-READER");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	/**
	 * @return lineMapper
	 */
	@Bean
	public LineMapper<EmployeeDto> lineMapper() {

		DefaultLineMapper<EmployeeDto> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("empId", "firstName", "middleInitial", "lastName", "gender", "email", "fatherName",
				"motherName", "weight", "salary", "phoneNumber", "country", "city", "state", "zipCode", "region",
				"userName", "password");
		BeanWrapperFieldSetMapper<EmployeeDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(EmployeeDto.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

		return defaultLineMapper;
	}

	@Bean
	public TaskExecutor taskExecutor() {
		return new SyncTaskExecutor();
	}

	@Bean
	public SkipPolicy skipPolicy() {
		return new SkipPolicyJob();
	}

}
