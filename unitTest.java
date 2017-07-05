import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class unitTest {

	@Test
	public void testSesli() {

		simpleProgramme den = new simpleProgramme();
		int sesliHarf = den.sesli("aaabbbb");

		Assert.assertEquals(3, sesliHarf);

	}

	@Test
	public void testSessiz(){

		simpleProgramme ene = new simpleProgramme();
		int sessizHarf = ene.sessiz("aaabbbb");

		Assert.assertEquals(4, sessizHarf);
	}

}
