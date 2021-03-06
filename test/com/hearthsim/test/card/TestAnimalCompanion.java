package com.hearthsim.test.card;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.hearthsim.card.Card;
import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.concrete.Leokk;
import com.hearthsim.card.spellcard.concrete.AnimalCompanion;
import com.hearthsim.card.spellcard.concrete.TheCoin;
import com.hearthsim.exception.HSException;
import com.hearthsim.exception.HSInvalidPlayerIndexException;
import com.hearthsim.util.BoardState;
import com.hearthsim.util.tree.HearthTreeNode;

public class TestAnimalCompanion {


	private HearthTreeNode board;
	private Deck deck;
	private static final byte mana = 2;
	private static final byte attack0 = 5;
	private static final byte health0 = 3;
	private static final byte health1 = 7;

	@Before
	public void setup() throws HSException {
		board = new HearthTreeNode(new BoardState());

		Minion minion0_0 = new Minion("" + 0, mana, attack0, health0, attack0, health0, health0);
		Minion minion0_1 = new Minion("" + 0, mana, attack0, (byte)(health1 - 1), attack0, health1, health1);
		Minion minion1_0 = new Minion("" + 0, mana, attack0, health0, attack0, health0, health0);
		Minion minion1_1 = new Minion("" + 0, mana, attack0, (byte)(health1 - 1), attack0, health1, health1);
		
		board.data_.placeMinion(0, minion0_0);
		board.data_.placeMinion(0, minion0_1);
		
		board.data_.placeMinion(1, minion1_0);
		board.data_.placeMinion(1, minion1_1);
		
		Card cards[] = new Card[10];
		for (int index = 0; index < 10; ++index) {
			cards[index] = new TheCoin();
		}
	
		deck = new Deck(cards);

		AnimalCompanion fb = new AnimalCompanion();
		board.data_.placeCard_hand_p0(fb);

		board.data_.setMana_p0((byte)4);
		board.data_.setMana_p1((byte)4);
		
		board.data_.setMaxMana_p0((byte)4);
		board.data_.setMaxMana_p1((byte)4);
		
	}
	
	@Test
	public void testLeokk0() throws HSInvalidPlayerIndexException {
		
		Card leokk = new Leokk();
		board.data_.placeCard_hand_p0(leokk);
		
		Card theCard = board.data_.getCard_hand_p0(1);
		HearthTreeNode ret = theCard.useOn(1, 1, 0, board, deck, null);
		
		assertTrue(ret == null);
		assertEquals(board.data_.getNumCards_hand(), 2);
		assertEquals(board.data_.getNumMinions_p0(), 2);
		assertEquals(board.data_.getNumMinions_p1(), 2);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 30);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), health0);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), health1 - 1);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), health0);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), health1 - 1);
	}
	
	@Test
	public void testLeokk1() throws HSInvalidPlayerIndexException {
		
		Card leokk = new Leokk();
		board.data_.placeCard_hand_p0(leokk);
		
		Card theCard = board.data_.getCard_hand_p0(1);
		HearthTreeNode ret = theCard.useOn(1, 0, 3, board, deck, null);
		
		//Use Leokk.  The other minions should now be buffed with +1 attack
		assertFalse(ret == null);
		assertEquals(board.data_.getNumCards_hand(), 1);
		assertEquals(board.data_.getNumMinions_p0(), 3);
		assertEquals(board.data_.getNumMinions_p1(), 2);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 30);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), health0);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), health1 - 1);
		assertEquals(board.data_.getMinion_p0(2).getHealth(), 4);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), health0);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), health1 - 1);

		assertEquals(board.data_.getMinion_p0(0).getAttack(), attack0 + 1);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), attack0 + 1);
		assertEquals(board.data_.getMinion_p0(2).getAttack(), 2);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), attack0);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), attack0);

		
		//Now, attack and kill Leokk.  All minions should go back to their original attack
		Minion minion = board.data_.getMinion_p0(2);
		minion.hasAttacked(false);
		ret = minion.attack(3, 1, 1, board, deck, null);
		
		assertFalse(ret == null);
		assertEquals(board.data_.getNumCards_hand(), 1);
		assertEquals(board.data_.getNumMinions_p0(), 2);
		assertEquals(board.data_.getNumMinions_p1(), 2);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 30);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), health0);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), health1 - 1);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), health0 - 2);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), health1 - 1);

		assertEquals(board.data_.getMinion_p0(0).getAttack(), attack0);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), attack0);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), attack0);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), attack0);

	}
	
	@Test
	public void test0() throws HSInvalidPlayerIndexException {
		
		Card theCard = board.data_.getCard_hand_p0(0);
		HearthTreeNode ret = theCard.useOn(0, 1, 0, board, deck, null);
		
		assertTrue(ret == null);
		assertEquals(board.data_.getNumCards_hand(), 1);
		assertEquals(board.data_.getNumMinions_p0(), 2);
		assertEquals(board.data_.getNumMinions_p1(), 2);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 30);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), health0);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), health1 - 1);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), health0);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), health1 - 1);
	}

	@Test
	public void test1() throws HSInvalidPlayerIndexException {
		
		Card theCard = board.data_.getCard_hand_p0(0);
		HearthTreeNode ret = theCard.useOn(0, 0, 0, board, deck, null);
		
		assertFalse(ret == null);
		assertEquals(0, board.data_.getNumCards_hand());
		assertEquals(1, board.data_.getMana_p0());
		assertEquals(3, board.data_.getNumMinions_p0());
		assertEquals(2, board.data_.getNumMinions_p1());
		assertEquals(30, board.data_.getHero_p0().getHealth());
		assertEquals(30, board.data_.getHero_p1().getHealth());
		assertEquals(health0, board.data_.getMinion_p0(0).getHealth());
		assertEquals(health1 - 1, board.data_.getMinion_p0(1).getHealth());
		assertEquals(health0, board.data_.getMinion_p1(0).getHealth());
		assertEquals(health1  - 1, board.data_.getMinion_p1(1).getHealth());

		if (board.data_.getMinion_p0(2) instanceof Leokk) {
			assertEquals(board.data_.getMinion_p0(0).getAttack(), attack0 + 1);
			assertEquals(board.data_.getMinion_p0(1).getAttack(), attack0 + 1);
		} else {
			assertEquals(board.data_.getMinion_p0(0).getAttack(), attack0);
			assertEquals(board.data_.getMinion_p0(1).getAttack(), attack0);
		}
		assertEquals(board.data_.getMinion_p1(0).getAttack(), attack0);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), attack0);

	}
}
