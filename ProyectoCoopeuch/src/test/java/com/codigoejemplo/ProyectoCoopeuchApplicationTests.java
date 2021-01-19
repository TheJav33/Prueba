package com.codigoejemplo;




import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class ProyectoCoopeuchApplicationTests {

	private MockMvc mvc;
	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void testObtenerTareasPorId() throws Exception{
		mvc.perform(get("/tareas/3"))
			.andExpect(content().string("dn_IDENTIFICADOR"));
		
	}
	
}
