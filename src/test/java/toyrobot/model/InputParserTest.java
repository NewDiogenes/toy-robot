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
  String input;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void givenAValidPlaceCommandFacingNorth_place_shouldGeneratePlaceOperationFromOperationService() {
    input = "place 0 0 north";
    InputParser.place(operationService).apply(input);
    verify(operationService).place(eq(0), eq(0), eq(Direction.NORTH));
  }
  @Test
  public void givenAValidPlaceCommandFacingEast_place_shouldGeneratePlaceOperationFromOperationService() {
    input = "place 0 0 east";
    InputParser.place(operationService).apply(input);
    verify(operationService).place(eq(0), eq(0), eq(Direction.EAST));
  }
  @Test
  public void givenAValidPlaceCommandFacingSouth_place_shouldGeneratePlaceOperationFromOperationService() {
    input = "place 0 0 south";
    InputParser.place(operationService).apply(input);
    verify(operationService).place(eq(0), eq(0), eq(Direction.SOUTH));
  }
  @Test
  public void givenAValidPlaceCommandFacingWest_place_shouldGeneratePlaceOperationFromOperationService() {
    input = "place 0 0 west";
    InputParser.place(operationService).apply(input);
    verify(operationService).place(eq(0), eq(0), eq(Direction.WEST));
  }
}