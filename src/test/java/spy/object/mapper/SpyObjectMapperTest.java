package spy.object.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SpyObjectMapperTest {
	@Test
	void test() throws JsonProcessingException {
		final var objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		final var spiedObjectMapper = spy(objectMapper);
		doThrow(new IllegalStateException()).when(spiedObjectMapper).writeValueAsString(any());
		assertThrows(IllegalStateException.class, () -> spiedObjectMapper.writeValueAsString(1));
		assertEquals("1", objectMapper.writeValueAsString(1));
		assertEquals(LocalTime.parse("13:37"), objectMapper.readValue("\"13:37\"", LocalTime.class));
	}
}
