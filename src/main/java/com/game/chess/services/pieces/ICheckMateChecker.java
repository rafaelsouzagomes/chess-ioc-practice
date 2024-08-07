package com.game.chess.services.pieces;

import com.game.chess.services.components.squareboard.SquareBoard;
import com.game.chess.services.pieces.pawn.ITeamManager;

public interface ICheckMateChecker {

	boolean isAvailableForTeamToCheck(SquareBoard[][] squareBoard);

	void setTeamManager(ITeamManager teamManager);

}
