package com.game.chess.services.impls.piece.bishop.moviment;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.game.chess.models.enums.EnumNameNotaionSquare;
import com.game.chess.services.pieces.moviment.ISenseDirection;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TopRight implements ISenseDirection{
	
	private EnumNameNotaionSquare initPos;
	
	private boolean secondPosExists;

	@Override
	public boolean isFastReturn(int x, int y) {
		return y>=7 || x<=0;
	}
	
	@Override
	public int getYReturn(int y) {
		if(!secondPosExists)
			return initPos.getIndex_y();
		if(y>=7) 
			return 7;
		return y;
	}

	@Override
	public int getFastXReturn(int x) {
		if(!secondPosExists) 
			return initPos.getIndex_x();
		if(x<=0) 
			return 0;
		
		return x;
	}

	@Override
	public int getY(int y) {
		return y+1;
	}

	@Override
	public int getX(int x) {
		return x-1;
	}

	@Override
	public ISenseDirection setInitPosition(int x, int y) {
		this.initPos = EnumNameNotaionSquare.get(x, y);
		this.secondPosExists = EnumNameNotaionSquare.get(getX(initPos.getIndex_x()), getY(initPos.getIndex_y()))!=null;
		return this;
	}



}
