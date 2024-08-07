package com.game.chess.services.impls.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.game.chess.configs.CustomTestConfig;
import com.game.chess.models.dtos.MovimentOptionsAvailableDTO;
import com.game.chess.models.enums.EnumNameNotaionSquare;
import com.game.chess.models.enums.EnumTeam;
import com.game.chess.models.enums.EnumTypePiece;
import com.game.chess.services.components.Board8x8Factory;
import com.game.chess.services.components.IBoard;
import com.game.chess.services.components.squareboard.SquareBoard;
import com.game.chess.services.pieces.IMovimentOptions;
import com.game.chess.services.pieces.pawn.ITeamManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import(CustomTestConfig.class)
class MovimentOptionsTest {
	
	@Autowired
	@Qualifier("whitePawnTeamManager")
	private ITeamManager WhitePawnTeamManager;
	
	@Autowired
	@Qualifier("blackPawnTeamManager")
	private ITeamManager blackPawnTeamManager;
	
	@Autowired
	@Qualifier("blackTeamManager")
	private ITeamManager blackTeamManager;
	
	@Autowired
	@Qualifier("whiteTeamManager")
	private ITeamManager whiteTeamManager;
	
	@Autowired
	private IBoard chessBoard;
	
	@Autowired
	private IMovimentOptions movOptions;
	
	@Autowired
	Board8x8Factory bFactory;

	@BeforeEach
    void setUp() {
        chessBoard.createNewGame();
        movOptions.setChessBoard(chessBoard);
        movOptions.setTeamManager(blackPawnTeamManager);
        movOptions.setCurrentPosition(EnumNameNotaionSquare.A1);
    }
	  
	@Test
	void invalid_moviment_in_init_chessBoard() {
		movOptions.addAnyMoveType(0, 0);
		MovimentOptionsAvailableDTO movimentsOptions = movOptions.getMovimentsOptions();
		assertEquals(0, movimentsOptions.getChessSquaresAvailable().size());
	}
	
	  
	@Test
	void valid_moviment_in_init_chessBoard() {
		movOptions.addMove(4, 2);
		MovimentOptionsAvailableDTO movimentsOptions = movOptions.getMovimentsOptions();
		assertEquals(1, movimentsOptions.getChessSquaresAvailable().size());
	}
	
	@Test
	void valid_capture_moviment_in_init_chessBoard() {
		movOptions.addCaptureMove(7, 6);
		MovimentOptionsAvailableDTO movimentsOptions = movOptions.getMovimentsOptions();
		assertEquals(1, movimentsOptions.getChessSquaresAvailable().size());
	}
	
	@Test
	void invalid_capture_moviment_in_init_chessBoard() {
		movOptions.addCaptureMove(0, 3);
		MovimentOptionsAvailableDTO movimentsOptions = movOptions.getMovimentsOptions();
		assertEquals(0, movimentsOptions.getChessSquaresAvailable().size());
	}
	
	@Test
	@Disabled
	void checkerCheckMateTester_1() {
		bFactory.addAllSquare_empty();
		bFactory.addSquare(EnumTypePiece.BISHOP, EnumTeam.WHITE, EnumNameNotaionSquare.D5);
		bFactory.addSquare(EnumTypePiece.KING, EnumTeam.BLACK, EnumNameNotaionSquare.E4);
		
		
		EnumNameNotaionSquare enumNameNotaionSquare = EnumNameNotaionSquare.C8;
		bFactory.addSquare(EnumTypePiece.BISHOP, EnumTeam.BLACK, enumNameNotaionSquare);
		
		SquareBoard[][] squareBoards = bFactory.build();
		
		movOptions.setCurrentPosition(enumNameNotaionSquare);
		movOptions.setTeamManager(blackTeamManager);
		
		movOptions.setSquareBoard(squareBoards);
		movOptions.addAnyMoveType(EnumNameNotaionSquare.D7.getIndex_x(), EnumNameNotaionSquare.D7.getIndex_y());
		MovimentOptionsAvailableDTO movimentsOptions = movOptions.getMovimentsOptions();
		assertEquals(0, movimentsOptions.getChessSquaresAvailable().size());
	}
	
	@Test
	void checkerCheckMateTester_2() {
		bFactory.addAllSquare_empty();
		bFactory.addSquare(EnumTypePiece.BISHOP, EnumTeam.WHITE, EnumNameNotaionSquare.D5);
		bFactory.addSquare(EnumTypePiece.KING, EnumTeam.BLACK, EnumNameNotaionSquare.E5);
		
		SquareBoard[][] squareBoards = bFactory.build();
		
		EnumNameNotaionSquare enumNameNotaionSquare = EnumNameNotaionSquare.get(0, 2);
		bFactory.addSquare(EnumTypePiece.BISHOP, EnumTeam.BLACK, enumNameNotaionSquare);
		
		movOptions.setSquareBoard(squareBoards);
		movOptions.addAnyMoveType(0, 3);
		MovimentOptionsAvailableDTO movimentsOptions = movOptions.getMovimentsOptions();
		assertEquals(1, movimentsOptions.getChessSquaresAvailable().size());
	}
	
	@Test
	void checkerCheckMateTester_3() {
		bFactory.addAllSquare_empty();
		bFactory.addSquare(EnumTypePiece.BISHOP, EnumTeam.WHITE, EnumNameNotaionSquare.D5);
		bFactory.addSquare(EnumTypePiece.KING, EnumTeam.WHITE, EnumNameNotaionSquare.E4);
		
		SquareBoard[][] squareBoards = bFactory.build();
		
		EnumNameNotaionSquare enumNameNotaionSquare = EnumNameNotaionSquare.get(0, 2);
		bFactory.addSquare(EnumTypePiece.BISHOP, EnumTeam.BLACK, enumNameNotaionSquare);
		
		movOptions.setSquareBoard(squareBoards);
		movOptions.addAnyMoveType(0, 3);
		MovimentOptionsAvailableDTO movimentsOptions = movOptions.getMovimentsOptions();
		assertEquals(1, movimentsOptions.getChessSquaresAvailable().size());
	}

}
