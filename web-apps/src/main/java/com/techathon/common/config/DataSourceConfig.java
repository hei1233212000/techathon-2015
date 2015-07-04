package com.techathon.common.config;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.AutofetchConfig;
import com.avaje.ebean.config.AutofetchMode;
import com.avaje.ebean.config.ServerConfig;
import com.techathon.model.entity.Exercise;
import com.techathon.model.entity.ExerciseRequest;
import com.techathon.model.entity.Sample;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class DataSourceConfig {
	@Bean
	public EbeanServer getDefaultEbeanServer() {
		ServerConfig config = EbeanServerConfig();
		Arrays.asList(Sample.class, ExerciseRequest.class, Exercise.class).forEach(config::addClass);
		return EbeanServerFactory.create(config);
	}

	@Bean
	public ServerConfig EbeanServerConfig() {
		ServerConfig c = new ServerConfig();
		c.setName("default");

		// TODO: adding selection logic would NOT be a good idea
		c.setDdlGenerate(true);
		c.setDdlRun(true);
		c.setDataSource(h2DataSource());
		c.setDefaultServer(true);
		c.setRegister(true);

		// set the autofetch
		AutofetchConfig autofetchConfig = new AutofetchConfig();
		autofetchConfig.setQueryTuning(true); // enable autofetch
		autofetchConfig.setProfiling(true); // enable collection of profiling information
		autofetchConfig.setMode(AutofetchMode.DEFAULT_ON);
		autofetchConfig.setProfilingMin(1); // minimum amount of profiling to collect before autofetch will start tuning the query
		autofetchConfig.setProfilingBase(10); // profile every query up to base
		autofetchConfig.setProfilingRate(0.05); // after base collect profiling on 5% of queries
		c.setAutofetchConfig(autofetchConfig);

		return c;
	}

	@Bean
	public DataSource h2DataSource() {
		JdbcDataSource h2DataSource = new JdbcDataSource();
		h2DataSource.setURL("jdbc:h2:mem:basic;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
		h2DataSource.setUser("sa");
		h2DataSource.setPassword("");
		return h2DataSource;
	}
}
