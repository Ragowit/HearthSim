package com.hearthsim.card.minion.concrete;

import com.hearthsim.card.minion.MinionWithEnrage;

public class GrommashHellscream extends MinionWithEnrage {


	private static final String NAME = "Grommash Hellscream";
	private static final byte MANA_COST = 8;
	private static final byte ATTACK = 4;
	private static final byte HEALTH = 9;
	
	private static final boolean TAUNT = false;
	private static final boolean DIVINE_SHIELD = false;
	private static final boolean WINDFURY = false;
	private static final boolean CHARGE = true;
	
	private static final boolean SUMMONED = false;
	private static final boolean TRANSFORMED = false;
	
	public GrommashHellscream() {
		this(
				MANA_COST,
				ATTACK,
				HEALTH,
				ATTACK,
				(byte)0,
				HEALTH,
				HEALTH,
				TAUNT,
				DIVINE_SHIELD,
				WINDFURY,
				CHARGE,
				false,
				false,
				false,
				SUMMONED,
				TRANSFORMED,
				false,
				false,
				false,
				true,
				false
			);
	}
	
	public GrommashHellscream(	
			byte mana,
			byte attack,
			byte health,
			byte baseAttack,
			byte extraAttackUntilTurnEnd,
			byte baseHealth,
			byte maxHealth,
			boolean taunt,
			boolean divineShield,
			boolean windFury,
			boolean charge,
			boolean hasAttacked,
			boolean hasWindFuryAttacked,
			boolean frozen,
			boolean summoned,
			boolean transformed,
			boolean destroyOnTurnStart,
			boolean destroyOnTurnEnd,
			boolean enraged,
			boolean isInHand,
			boolean hasBeenUsed) {
		
		super(
			NAME,
			mana,
			attack,
			health,
			baseAttack,
			extraAttackUntilTurnEnd,
			baseHealth,
			maxHealth,
			taunt,
			divineShield,
			windFury,
			charge,
			hasAttacked,
			hasWindFuryAttacked,
			frozen,
			summoned,
			transformed,
			destroyOnTurnStart,
			destroyOnTurnEnd,
			enraged,
			isInHand,
			hasBeenUsed);
	}
	
	@Override
	public Object deepCopy() {
		return new GrommashHellscream(
				this.mana_,
				this.attack_,
				this.health_,
				this.baseAttack_,
				this.extraAttackUntilTurnEnd_,
				this.baseHealth_,
				this.maxHealth_,
				this.taunt_,
				this.divineShield_,
				this.windFury_,
				this.charge_,
				this.hasAttacked_,
				this.hasWindFuryAttacked_,
				this.frozen_,
				this.summoned_,
				this.transformed_,
				this.destroyOnTurnStart_,
				this.destroyOnTurnEnd_,
				this.enraged_,
				this.isInHand_,
				this.hasBeenUsed_);
	}
	

	@Override
	public void enrage() {
		attack_ = (byte)(attack_ + 6);
	}

	@Override
	public void pacify() {
		attack_ = (byte)(attack_ - 6);
	}
}
