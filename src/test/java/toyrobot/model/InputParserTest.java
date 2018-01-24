package toyrobot.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toyrobot.domain.InputParser;
import toyrobot.service.OperationService;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class InputParserTest {

  @Mock
  OperationService operationService;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void givenAValidPlaceCommand_place_shouldGeneratePlaceOperationFromOperationService() {
    String input = "place 0 0 north";
    InputParser.place(operationService).apply(input);
    verify(operationService).place(eq(0), eq(0), eq("north"));
  }
}