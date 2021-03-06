package com.hearthsim.card.spellcard;

import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.exception.HSInvalidPlayerIndexException;
import com.hearthsim.util.tree.HearthTreeNode;
import com.json.JSONObject;

public class SpellDamage extends SpellCard {

	protected byte damage_;
	
	public SpellDamage(String name, byte mana, byte damage, boolean hasBeenUsed) {
		super(name, mana, hasBeenUsed);
		damage_ = damage;
	}

	public SpellDamage() {
		super("Damage Spell", (byte)0, false);
		damage_ = 0;
	}

	public byte getAttack() {
		return damage_;
	}
	
	@Override
	public boolean equals(Object other)
	{

		if (!super.equals(other)) {
			return false;
		}
		
		if (other == null) {
			return false;
		}

		if (this.getClass() != other.getClass()) {
			return false;
		}
		
		if (this.damage_ != ((SpellDamage)other).damage_) {
			return false;
		}

	   return true;
	}
	
	@Override
	public Object deepCopy() {
		return new SpellDamage(this.getName(), this.getMana(), damage_, this.hasBeenUsed());
	}

	public void attack(
			Minion minion,
			int targetPlayerIndex,
			int targetMinionIndex,
			HearthTreeNode boardState,
			Deck deckPlayer0,
			Deck deckPlayer1)
		throws HSInvalidPlayerIndexException
	{
		minion.takeDamage(damage_, 0, targetPlayerIndex, targetMinionIndex, boardState, deckPlayer0, deckPlayer1, true);
 	}
	
	/**
	 * 
	 * Use the card on the given target
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
			Deck deckPlayer0,
			Deck deckPlayer1)
		throws HSInvalidPlayerIndexException
	{
		if (this.hasBeenUsed()) {
			//Card is already used, nothing to do
			return null;
		}
				
		this.hasBeenUsed(true);

		if (minionIndex == 0) {
			//attack a hero
			if (playerIndex == 0) {
				this.attack(boardState.data_.getHero_p0(), playerIndex, minionIndex, boardState, deckPlayer0, deckPlayer1);
			} else {
				this.attack(boardState.data_.getHero_p1(), playerIndex, minionIndex, boardState, deckPlayer0, deckPlayer1);
			}
			boardState.data_.setMana_p0(boardState.data_.getMana_p0() - this.mana_);
			boardState.data_.removeCard_hand(thisCardIndex);
			return boardState;
		} else {
			Minion target = null;
			if (playerIndex == 0) {
				if (boardState.data_.getNumMinions_p0() + 1 > minionIndex)
					target = boardState.data_.getMinion_p0(minionIndex - 1);
				else
					return null;
			} else {
				if (boardState.data_.getNumMinions_p1() + 1 > minionIndex)
					target = boardState.data_.getMinion_p1(minionIndex - 1);
				else
					return null;
			}
			this.attack(target, playerIndex, minionIndex, boardState, deckPlayer0, deckPlayer1);
			boardState.data_.setMana_p0(boardState.data_.getMana_p0() - this.mana_);
			boardState.data_.removeCard_hand(thisCardIndex);

			if (target.getHealth() <= 0) {
				if (playerIndex == 0)
					boardState.data_.removeMinion_p0(minionIndex-1);
				else
					boardState.data_.removeMinion_p1(minionIndex-1);
			}
			return boardState;
		}
		
	}

	public JSONObject toJSON() {
		JSONObject json = super.toJSON();
		json.put("type", "SpellDamage");
		return json;
	}
}
