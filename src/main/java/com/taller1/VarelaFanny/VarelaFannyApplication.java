package com.taller1.VarelaFanny;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.taller1.VarelaFanny.model.CheckMeasur;
import com.taller1.VarelaFanny.model.CheckMeasurPK;
import com.taller1.VarelaFanny.model.Institution;
import com.taller1.VarelaFanny.model.Institutioncampus;
import com.taller1.VarelaFanny.model.Measurement;
import com.taller1.VarelaFanny.model.Nexuspoll;
import com.taller1.VarelaFanny.model.Nexusquestion;
import com.taller1.VarelaFanny.model.Person;
import com.taller1.VarelaFanny.model.PersonRole;
import com.taller1.VarelaFanny.model.PersonRolePK;
import com.taller1.VarelaFanny.model.Physicalcheckup;
import com.taller1.VarelaFanny.model.Rolee;
import com.taller1.VarelaFanny.model.UserRole;
import com.taller1.VarelaFanny.model.Userr;
import com.taller1.VarelaFanny.model.Visit;
import com.taller1.VarelaFanny.repositories.CheckMeasurRepository;
import com.taller1.VarelaFanny.repositories.InstitutionRepository;
import com.taller1.VarelaFanny.repositories.InstitutioncampusRepository;
import com.taller1.VarelaFanny.repositories.MeasurementRepository;
import com.taller1.VarelaFanny.repositories.PersonRepository;
import com.taller1.VarelaFanny.repositories.PhysicalcheckupRepository;
import com.taller1.VarelaFanny.repositories.RoleeRepository;
import com.taller1.VarelaFanny.repositories.UserrRepository;
import com.taller1.VarelaFanny.repositories.VisitRepository;
import com.taller1.VarelaFanny.services.CheckMeasurService;
import com.taller1.VarelaFanny.services.NexuspollService;
import com.taller1.VarelaFanny.services.NexusquestionService;

@SpringBootApplication
public class VarelaFannyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VarelaFannyApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner command(UserrRepository userrRepository, PersonRepository personRepository, InstitutioncampusRepository instCampusRepository, CheckMeasurService checkRepository,
			PhysicalcheckupRepository phyRepository, VisitRepository visitRepository, RoleeRepository roleeRepository, MeasurementRepository measurementRepository, InstitutionRepository institutionRepository, 
			NexuspollService nexusService, NexusquestionService nexusquesService) {
		
		return (args) -> {
	
			Person person1 = new Person();
			person1.setPersName("Fanny");
			person1.setPersLastname("Varela");
			person1.setPersIddocument("100747");
			personRepository.save(person1);
	
			Person person2 = new Person();
			person2.setPersName("Liliana");
			person2.setPersLastname("Castillo");
			person2.setPersIddocument("567648");
			personRepository.save(person2);
			
			Institution inst1 = new Institution();
			inst1.setInstName("Institución 1");
			institutionRepository.save(inst1);
			
			Institution inst2 = new Institution();
			inst2.setInstName("Institución 2");
			institutionRepository.save(inst2);
			
			Institutioncampus instCampus1 = new Institutioncampus();
			instCampus1.setInstcamName("Campus1");
			instCampusRepository.save(instCampus1);
			
			Institutioncampus instCampus2 = new Institutioncampus();
			instCampus2.setInstcamName("Campus2");
			instCampusRepository.save(instCampus2);
			
			Visit visit1 = new Visit();
			Date entranceDate = new Date(System.currentTimeMillis());
			visit1.setVisitDetail("visit Detail1");
			visit1.setVisitEntrancedate(entranceDate);
			visit1.setPerson(person1);
			visit1.setInstitutioncampus(instCampus1);
			visitRepository.save(visit1);
			
			Visit visit2 = new Visit();
			visit2.setVisitDetail("visit Detail2");
			visit2.setVisitEntrancedate(entranceDate);
			visit2.setPerson(person2);
			visit2.setInstitutioncampus(instCampus1);
			visitRepository.save(visit2);
			
			Physicalcheckup physicalcheckup1 = new Physicalcheckup();
			physicalcheckup1.setPhycheDate(entranceDate);
			physicalcheckup1.setVisit(visit2);
			physicalcheckup1.setPerson(person1);
			phyRepository.save(physicalcheckup1);
			
			Physicalcheckup physicalcheckup2 = new Physicalcheckup();
			physicalcheckup2.setPhycheDate(entranceDate);
			physicalcheckup2.setVisit(visit1);
			physicalcheckup2.setPerson(person2);
			phyRepository.save(physicalcheckup2);
			
			BigDecimal measMaxthreshold = new BigDecimal(18288733);
			BigDecimal measMinthreshold = new BigDecimal(122233212);
			
			Measurement measurement1 = new Measurement();
			measurement1.setMeasName("Medida 1");
			measurement1.setMeasDescription("Descripcion 1");
			measurement1.setMeasUnit("unidad 1");
			measurement1.setMeasMaxthreshold(measMaxthreshold);
			measurement1.setMeasMinthreshold(measMinthreshold);
			measurement1.setInstitution(inst1);
			
			measurementRepository.save(measurement1);
			
			Measurement measurement2 = new Measurement();
			measurement2.setMeasName("Medida 2");
			measurement2.setMeasDescription("Descripcion 2");
			measurement2.setMeasUnit("unidad 2");
			measurement2.setMeasMaxthreshold(measMaxthreshold);
			measurement2.setMeasMinthreshold(measMinthreshold);
			measurement2.setInstitution(inst2);
			
			measurementRepository.save(measurement2);
			
			CheckMeasur checkMeasur = new CheckMeasur();
			BigDecimal measvalue = new BigDecimal(23224242);
			checkMeasur.setMeasvalue(measvalue);
			checkMeasur.setPhysicalcheckup(physicalcheckup1);
			checkMeasur.setMeasurement(measurement1);	
			checkRepository.createCheckMeasur(checkMeasur);
			
			CheckMeasur checkMeasur1 = new CheckMeasur();
			BigDecimal measvalue1 = new BigDecimal(67224242);
			checkMeasur1.setMeasvalue(measvalue1);
			checkMeasur1.setPhysicalcheckup(physicalcheckup2);
			checkMeasur1.setMeasurement(measurement2);	
			checkRepository.createCheckMeasur(checkMeasur1);
			
			
			
			Date startDate = new GregorianCalendar(2021, Calendar.MARCH, 10).getTime();
			Date startDate1 = new GregorianCalendar(2021, Calendar.SEPTEMBER, 10).getTime();
			Date endDate1 = new GregorianCalendar(2021, Calendar.JULY, 3).getTime();
			Date endDate = new GregorianCalendar(2021, Calendar.NOVEMBER, 3).getTime();
			
			Nexuspoll nexus = new Nexuspoll();
			nexus.setInstInstId( BigDecimal.valueOf( inst1.getInstId()));
			nexus.setNexpollStartdate(startDate1);
			nexus.setNexpollEnddate(endDate);
			nexus.setNexpollName("Nexus");
			nexusService.createNexuspoll(nexus);
			
			Nexuspoll nexus1 = new Nexuspoll();
			nexus1.setInstInstId( BigDecimal.valueOf( inst2.getInstId()));
			nexus1.setNexpollStartdate(startDate);
			nexus1.setNexpollEnddate(endDate1);
			nexus1.setNexpollName("Nexus 1");
			nexusService.createNexuspoll(nexus1);
			
			Nexuspoll nexus2 = new Nexuspoll();
			nexus2.setInstInstId( BigDecimal.valueOf( inst1.getInstId()));
			nexus2.setNexpollStartdate(startDate1);
			nexus2.setNexpollEnddate(endDate1);
			nexus2.setNexpollName("Nexus 2");
			
			nexusService.createNexuspoll(nexus2);
			
			
			Nexusquestion ques = new  Nexusquestion();
			ques.setNexquesName("question ");
			ques.setNexquesWeight(measvalue1);
			ques.setNexuspoll(nexus1);
			nexusquesService.createNexusquestion(ques);
			
			Nexusquestion ques1 = new  Nexusquestion();
			ques1.setNexquesName("question 1");
			ques1.setNexquesWeight(measMaxthreshold);
			ques1.setNexuspoll(nexus);
			nexusquesService.createNexusquestion(ques1);
			
			Nexusquestion ques2 = new  Nexusquestion();
			ques2.setNexquesName("question 2");
			ques2.setNexquesWeight(measMinthreshold);
			ques2.setNexuspoll(nexus1);
			nexusquesService.createNexusquestion(ques2);
			
			
						
			Userr user1 = new Userr();
			user1.setUserName("user1");
			user1.setUserPassword("{noop}123");

			//user1.setPerson(person1);
			user1.setRole("admin");
			userrRepository.save(user1);
			
			Userr user2 = new Userr();
			user2.setUserName("user2");
			user2.setUserPassword("{noop}123");

			//user2.setPerson(person2);
			user2.setRole("operator");
			userrRepository.save(user2);
			
			System.out.println("repoo:" + userrRepository.findByUserName("user1"));
			System.out.println("Roool u1:" + user1.getRole());

			
			System.out.println(userrRepository.findByUserName("user1").get(0).getUserPassword());
			
		
		};
	}

}
