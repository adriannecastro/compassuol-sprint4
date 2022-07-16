package br.com.compass.sprint4;

import br.com.compass.sprint4.repository.PartiesRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class HelloWorldMockito {

    @Test
    void hello() {
        Mockito.mock(PartiesRepository.class);
    }
}
