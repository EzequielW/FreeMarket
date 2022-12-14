package com.example.freemarket.util.dataseed;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class DataSeed implements CommandLineRunner{
	private final DataSeedUsers dataSeedUsers;

	@Override
	public void run(String... args) throws Exception {
		log.info("Data seed start");
		dataSeedUsers.loadData();
		log.info("Data seed finish");
	}
}