package com.game.chess.components.piece;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.game.chess.enums.EnumTeam;
import com.game.chess.enums.EnumTypePiece;

@Component
public class Piece {
	
	@NonNull
	private EnumTeam team;
	
	@NonNull
	private EnumTypePiece type;
	
	protected Piece() {}
	
	@Override
	public boolean equals(Object obj) {
		Piece pieceToCompare = (Piece) obj;
		
		return this.getType() == pieceToCompare.getType() && 
				this.getTeam() == pieceToCompare.getTeam();
	}
	
	@Override
	public String toString() {
		return  type.getAbr()+team.getAbr();
	}
	
	protected void setTeam(EnumTeam team) {
		this.team = team;
	}
	
	protected void setType(EnumTypePiece type) {
		this.type = type;
	}
	
	public EnumTeam getTeam() {
		return team;
	}
	
	public EnumTypePiece getType() {
		return type;
	}

	public boolean isKing() {
		return type == EnumTypePiece.KING;
	}
	
}
