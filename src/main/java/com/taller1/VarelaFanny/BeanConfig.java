package com.taller1.VarelaFanny;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.taller1.VarelaFanny.delegate.CheckMeasurDelegate;
import com.taller1.VarelaFanny.delegate.ICheckMeasurDelegate;
import com.taller1.VarelaFanny.delegate.IMeasurementDelegate;
import com.taller1.VarelaFanny.delegate.INexuspollDelegate;
import com.taller1.VarelaFanny.delegate.INexusquestionDelegate;
import com.taller1.VarelaFanny.delegate.IPhysicalcheckupDelegate;
import com.taller1.VarelaFanny.delegate.IVisitDelegate;
import com.taller1.VarelaFanny.delegate.MeasurementDelegate;
import com.taller1.VarelaFanny.delegate.NexuspollDelegate;
import com.taller1.VarelaFanny.delegate.NexusquestionDelegate;
import com.taller1.VarelaFanny.delegate.PhysicalcheckupDelegate;
import com.taller1.VarelaFanny.delegate.VisitDelegate;

@Configuration
public class BeanConfig {
	
	@Bean
	RestTemplate template() {
		return new RestTemplate();
	}
	
	@Bean
	public IMeasurementDelegate measurDelegate() {
		return new MeasurementDelegate();
	}
		
	@Bean
	public IVisitDelegate vistDelegate() {
		return new VisitDelegate();
	}
	
	@Bean
	public IPhysicalcheckupDelegate physDelegate() {
		return new PhysicalcheckupDelegate();
	}
	
	@Bean
	public ICheckMeasurDelegate checkDelegate() {
		return new CheckMeasurDelegate();
	}
	
	@Bean
	public INexuspollDelegate nexusDelegate() {
		return new NexuspollDelegate();
	}
	
	@Bean
	public INexusquestionDelegate nexusquesDelegate() {
		return new NexusquestionDelegate();
	}
	
	


}
