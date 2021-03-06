package com.hearthsim.card.spellcard.concrete;

import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.concrete.Huffer;
import com.hearthsim.card.minion.concrete.Leokk;
import com.hearthsim.card.minion.concrete.Misha;
import com.hearthsim.card.spellcard.SpellCard;
import com.hearthsim.exception.HSInvalidPlayerIndexException;
import com.hearthsim.util.tree.HearthTreeNode;

public class AnimalCompanion extends SpellCard {

	
	/**
	 * Constructor
	 * 
	 * @param hasBeenUsed Whether the card has already been used or not
	 */
	public AnimalCompanion(boolean hasBeenUsed) {
		super("Animal Companion", (byte)3, hasBeenUsed);
	}

	/**
	 * Constructor
	 * 
	 * Defaults to hasBeenUsed = false
	 */
	public AnimalCompanion() {
		this(false);
	}

	@Override
	public Object deepCopy() {
		return new AnimalCompanion(this.hasBeenUsed_);
	}
	
	/**
	 * 
	 * Use the card on the given target
	 * 
	 * Summons either Huffer, Leokk, or Misha
	 * 
	 * @param thisCardIndex The index (position) of the card in the hand
	 * @param playerIndex The index of the target player.  0 if targeting yourself or your own minions, 1 if targeting the enemy
	 * @param minionIndex The index of the target minion.
	 * @param boardState The BoardState before this card has performed its action.  It will be manipulated and returned.
	 * 
	 * @return The boardState is manipulated and returned
	 */
	@Override
	protected HearthTreeNode use_core(
			int thisCardIndex,
			int playerIndex,
			int minionIndex,
			HearthTreeNode boardState,
			Deck deckPlayer0, Deck deckPlayer1)
		throws HSInvalidPlayerIndexException
	{
		if (minionIndex > 0 || playerIndex == 1) {
			return null;
		}
		
		int numMinions = boardState.data_.getNumMinions_p0();
		if (numMinions >= 7)
			return null;
		
		double rnd = Math.random();
		Minion minion = null;
		if (rnd < 0.333333333333333333333333333) {
			minion = new Huffer();
		} else if (rnd > 0.66666666666666666666666666666) {
			minion = new Leokk();
		} else {
			minion = new Misha();
		}
		boardState.data_.setMana_p0(boardState.data_.getMana_p0() + 3);
		boardState.data_.placeCard_hand_p0(minion);
		HearthTreeNode toRet = minion.useOn(boardState.data_.getNumCards_hand() - 1, playerIndex, numMinions + 1, boardState, deckPlayer0, deckPlayer1);
		
		return super.use_core(thisCardIndex, playerIndex, minionIndex, toRet, deckPlayer0, deckPlayer1);
	}

}
