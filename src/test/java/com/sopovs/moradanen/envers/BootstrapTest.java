package com.sopovs.moradanen.envers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BootstrapTest extends AbstractTest {

	@Test
	public void testBootstrap() {
		assertEquals(150L, personRepository.count());
		assertEquals(193L, bookRepository.count());
		assertEquals(470L, editionRepository.count());
		assertEquals(1702L, circulationRepository.count());
	}

}
