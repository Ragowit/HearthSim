package com.hearthsim.test.minion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.hearthsim.card.Card;
import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.concrete.BloodfenRaptor;
import com.hearthsim.card.minion.concrete.RaidLeader;
import com.hearthsim.card.spellcard.concrete.HolySmite;
import com.hearthsim.card.spellcard.concrete.TheCoin;
import com.hearthsim.exception.HSInvalidPlayerIndexException;
import com.hearthsim.util.BoardState;
import com.hearthsim.util.tree.HearthTreeNode;

public class TestRaidLeader {


	private HearthTreeNode board;
	private Deck deck;

	@Before
	public void setup() {
		board = new HearthTreeNode(new BoardState());

		Minion minion0_0 = new BloodfenRaptor();
		Minion minion0_1 = new RaidLeader();
		Minion minion1_0 = new BloodfenRaptor();
		Minion minion1_1 = new RaidLeader();
		
		board.data_.placeCard_hand_p0(minion0_0);
		board.data_.placeCard_hand_p0(minion0_1);
				
		board.data_.placeCard_hand_p1(minion1_0);
		board.data_.placeCard_hand_p1(minion1_1);

		Card cards[] = new Card[10];
		for (int index = 0; index < 10; ++index) {
			cards[index] = new TheCoin();
		}
	
		deck = new Deck(cards);

		board.data_.setMana_p0((byte)10);
		board.data_.setMana_p1((byte)10);
		
		board.data_.setMaxMana_p0((byte)10);
		board.data_.setMaxMana_p1((byte)10);
		
		HearthTreeNode tmpBoard = new HearthTreeNode(board.data_.flipPlayers());
		try {
			tmpBoard.data_.getCard_hand_p0(0).useOn(0, 0, 1, tmpBoard, deck, null);
			tmpBoard.data_.getCard_hand_p0(0).useOn(0, 0, 1, tmpBoard, deck, null);
		} catch (HSInvalidPlayerIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board = new HearthTreeNode(tmpBoard.data_.flipPlayers());
		try {
			board.data_.getCard_hand_p0(0).useOn(0, 0, 1, board, deck, null);
			board.data_.getCard_hand_p0(0).useOn(0, 0, 1, board, deck, null);
		} catch (HSInvalidPlayerIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board.data_.resetMana();
		board.data_.resetMinions();
		
		Minion fb = new RaidLeader();
		board.data_.placeCard_hand_p0(fb);

	}
	
	@Test
	public void test0() throws HSInvalidPlayerIndexException {

		Card theCard = board.data_.getCard_hand_p0(0);
		HearthTreeNode ret = theCard.useOn(0, 0, 0, board, deck, null);
		assertTrue(ret == null);
		assertTrue(board.data_.getNumCards_hand() == 1);
		assertTrue(board.data_.getNumMinions_p0() == 2);
		assertTrue(board.data_.getNumMinions_p1() == 2);
		assertTrue(board.data_.getHero_p0().getHealth() == 30);
		assertTrue(board.data_.getHero_p1().getHealth() == 30);
		assertTrue(board.data_.getMinion_p0(0).getHealth() == 2);
		assertTrue(board.data_.getMinion_p0(1).getHealth() == 2);
		assertTrue(board.data_.getMinion_p1(0).getHealth() == 2);
		assertTrue(board.data_.getMinion_p1(1).getHealth() == 2);
		
		assertTrue(board.data_.getMinion_p0(0).getAttack() == 2);
		assertTrue(board.data_.getMinion_p0(1).getAttack() == 4);
		assertTrue(board.data_.getMinion_p1(0).getAttack() == 2);
		assertTrue(board.data_.getMinion_p1(1).getAttack() == 4);
	}
	
	@Test
	public void test1() throws HSInvalidPlayerIndexException {

		Card theCard = board.data_.getCard_hand_p0(0);
		HearthTreeNode ret = theCard.useOn(0, 0, 1, board, deck, null);
		assertFalse(ret == null);
		assertTrue(board.data_.getNumCards_hand() == 0);
		assertTrue(board.data_.getNumMinions_p0() == 3);
		assertTrue(board.data_.getNumMinions_p1() == 2);
		assertTrue(board.data_.getHero_p0().getHealth() == 30);
		assertTrue(board.data_.getHero_p1().getHealth() == 30);
		assertTrue(board.data_.getMinion_p0(0).getHealth() == 2);
		assertTrue(board.data_.getMinion_p0(1).getHealth() == 2);
		assertTrue(board.data_.getMinion_p0(2).getHealth() == 2);
		assertTrue(board.data_.getMinion_p1(0).getHealth() == 2);
		assertTrue(board.data_.getMinion_p1(1).getHealth() == 2);
		
		assertTrue(board.data_.getMinion_p0(0).getAttack() == 3);
		assertTrue(board.data_.getMinion_p0(1).getAttack() == 3);
		assertTrue(board.data_.getMinion_p0(2).getAttack() == 5);
		assertTrue(board.data_.getMinion_p1(0).getAttack() == 2);
		assertTrue(board.data_.getMinion_p1(1).getAttack() == 4);
	}

	@Test
	public void test2() throws HSInvalidPlayerIndexException {

		Card theCard = board.data_.getCard_hand_p0(0);
		HearthTreeNode ret = theCard.useOn(0, 0, 1, board, deck, null);
		assertFalse(ret == null);
		assertTrue(board.data_.getNumCards_hand() == 0);
		assertTrue(board.data_.getNumMinions_p0() == 3);
		assertTrue(board.data_.getNumMinions_p1() == 2);
		assertTrue(board.data_.getHero_p0().getHealth() == 30);
		assertTrue(board.data_.getHero_p1().getHealth() == 30);
		assertTrue(board.data_.getMinion_p0(0).getHealth() == 2);
		assertTrue(board.data_.getMinion_p0(1).getHealth() == 2);
		assertTrue(board.data_.getMinion_p0(2).getHealth() == 2);
		assertTrue(board.data_.getMinion_p1(0).getHealth() == 2);
		assertTrue(board.data_.getMinion_p1(1).getHealth() == 2);
		
		assertTrue(board.data_.getMinion_p0(0).getAttack() == 3);
		assertTrue(board.data_.getMinion_p0(1).getAttack() == 3);
		assertTrue(board.data_.getMinion_p0(2).getAttack() == 5);
		assertTrue(board.data_.getMinion_p1(0).getAttack() == 2);
		assertTrue(board.data_.getMinion_p1(1).getAttack() == 4);

		
		
		board.data_.placeCard_hand_p0(new HolySmite());
		theCard = board.data_.getCard_hand_p0(0);
		ret = theCard.useOn(0, 1, 1, board, deck, null);
		
		assertFalse(ret == null);
		assertEquals(board.data_.getNumCards_hand(), 0);
		assertEquals(board.data_.getNumMinions_p0(), 3);
		assertEquals(board.data_.getNumMinions_p1(), 1);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 30);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), 2);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), 2);
		assertEquals(board.data_.getMinion_p0(2).getHealth(), 2);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), 2);
		
		assertEquals(board.data_.getMinion_p0(0).getAttack(), 3);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), 3);
		assertEquals(board.data_.getMinion_p0(2).getAttack(), 5);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), 3);
	}
}
