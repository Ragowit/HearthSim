package com.hearthsim.test.minion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.hearthsim.card.Card;
import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.concrete.AbusiveSergeant;
import com.hearthsim.card.minion.concrete.BoulderfistOgre;
import com.hearthsim.card.minion.concrete.RaidLeader;
import com.hearthsim.card.minion.concrete.ScarletCrusader;
import com.hearthsim.card.spellcard.concrete.TheCoin;
import com.hearthsim.exception.HSInvalidPlayerIndexException;
import com.hearthsim.util.BoardState;
import com.hearthsim.util.tree.HearthTreeNode;

public class TestAbusiveSergeant {

	private HearthTreeNode board;
	private Deck deck;

	@Before
	public void setup() {
		board = new HearthTreeNode(new BoardState());

		Minion minion0_0 = new BoulderfistOgre();
		Minion minion0_1 = new RaidLeader();
		Minion minion1_0 = new BoulderfistOgre();
		Minion minion1_1 = new RaidLeader();
		Minion minion1_2 = new ScarletCrusader();
		
		board.data_.placeCard_hand_p0(minion0_0);
		board.data_.placeCard_hand_p0(minion0_1);
				
		board.data_.placeCard_hand_p1(minion1_0);
		board.data_.placeCard_hand_p1(minion1_1);
		board.data_.placeCard_hand_p1(minion1_2);

		Card cards[] = new Card[10];
		for (int index = 0; index < 10; ++index) {
			cards[index] = new TheCoin();
		}
	
		deck = new Deck(cards);

		Card fb = new AbusiveSergeant();
		board.data_.placeCard_hand_p0(fb);

		board.data_.setMana_p0((byte)8);
		board.data_.setMana_p1((byte)8);
		
		board.data_.setMaxMana_p0((byte)8);
		board.data_.setMaxMana_p1((byte)8);
		
		HearthTreeNode tmpBoard = new HearthTreeNode(board.data_.flipPlayers());
		try {
			tmpBoard.data_.getCard_hand_p0(0).useOn(0, 0, 1, tmpBoard, deck, null);
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
		
	}
	
	

	@Test
	public void test0() throws HSInvalidPlayerIndexException {
		
		//null case
		Card theCard = board.data_.getCard_hand_p0(0);
		HearthTreeNode ret = theCard.useOn(0, 1, 0, board, deck, null);
		
		assertTrue(ret == null);
		assertEquals(board.data_.getNumCards_hand(), 1);
		assertEquals(board.data_.getNumMinions_p0(), 2);
		assertEquals(board.data_.getNumMinions_p1(), 3);
		assertEquals(board.data_.getMana_p0(), 8);
		assertEquals(board.data_.getMana_p1(), 8);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 30);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), 2);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), 7);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), 1);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), 2);
		assertEquals(board.data_.getMinion_p1(2).getHealth(), 7);

		assertEquals(board.data_.getMinion_p0(0).getAttack(), 2);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), 7);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), 2);
		assertEquals(board.data_.getMinion_p1(2).getAttack(), 7);
		
		assertTrue(board.data_.getMinion_p1(0).getDivineShield());
	}
	
	@Test
	public void test1() throws HSInvalidPlayerIndexException {
		
		//null case
		Card theCard = board.data_.getCard_hand_p0(0);
		HearthTreeNode ret = theCard.useOn(0, 0, 0, board, deck, null);
		
		assertTrue(ret == null);
		assertEquals(board.data_.getNumCards_hand(), 1);
		assertEquals(board.data_.getNumMinions_p0(), 2);
		assertEquals(board.data_.getNumMinions_p1(), 3);
		assertEquals(board.data_.getMana_p0(), 8);
		assertEquals(board.data_.getMana_p1(), 8);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 30);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), 2);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), 7);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), 1);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), 2);
		assertEquals(board.data_.getMinion_p1(2).getHealth(), 7);

		assertEquals(board.data_.getMinion_p0(0).getAttack(), 2);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), 7);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), 2);
		assertEquals(board.data_.getMinion_p1(2).getAttack(), 7);
		
		assertTrue(board.data_.getMinion_p1(0).getDivineShield());
	}
	
	@Test
	public void test2() throws HSInvalidPlayerIndexException {
		
		Card theCard = board.data_.getCard_hand_p0(0);
		HearthTreeNode ret = theCard.useOn(0, 0, 3, board, deck, null);
		
		assertFalse(ret == null);
		assertEquals(board.data_.getNumCards_hand(), 0);
		assertEquals(board.data_.getNumMinions_p0(), 3);
		assertEquals(board.data_.getNumMinions_p1(), 3);
		assertEquals(board.data_.getMana_p0(), 7);
		assertEquals(board.data_.getMana_p1(), 8);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 30);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), 2);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), 7);
		assertEquals(board.data_.getMinion_p0(2).getHealth(), 1);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), 1);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), 2);
		assertEquals(board.data_.getMinion_p1(2).getHealth(), 7);

		assertEquals(board.data_.getMinion_p0(0).getAttack(), 2);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), 7);
		assertEquals(board.data_.getMinion_p0(2).getAttack(), 3);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), 2);
		assertEquals(board.data_.getMinion_p1(2).getAttack(), 7);

		//At this point, the BoardState should have 4 children: one that buffs the Raid Leader
		//and another that buffs the Boulderfist Ogre, for each side
		assertEquals(board.numChildren(), 4);
		
		assertEquals(board.getChildren().get(0).data_.getMinion_p0(0).getExtraAttackUntilTurnEnd(), 2);
		assertEquals(board.getChildren().get(1).data_.getMinion_p0(1).getExtraAttackUntilTurnEnd(), 2);
		assertEquals(board.getChildren().get(2).data_.getMinion_p1(0).getExtraAttackUntilTurnEnd(), 2);
		assertEquals(board.getChildren().get(3).data_.getMinion_p1(1).getExtraAttackUntilTurnEnd(), 2);
		
		//make sure that the extra attack is working!
		HearthTreeNode child1 = board.getChildren().get(0);
		Minion minion = child1.data_.getMinion_p0(0);
		minion.attack(1, 1, 0, child1, deck, null);
		assertEquals(child1.data_.getNumCards_hand(), 0);
		assertEquals(child1.data_.getNumMinions_p0(), 3);
		assertEquals(child1.data_.getNumMinions_p1(), 3);
		assertEquals(child1.data_.getMana_p0(), 7);
		assertEquals(child1.data_.getMana_p1(), 8);
		assertEquals(child1.data_.getHero_p0().getHealth(), 30);
		assertEquals(child1.data_.getHero_p1().getHealth(), 26);
		assertEquals(child1.data_.getMinion_p0(0).getHealth(), 2);
		assertEquals(child1.data_.getMinion_p0(1).getHealth(), 7);
		assertEquals(child1.data_.getMinion_p0(2).getHealth(), 1);
		assertEquals(child1.data_.getMinion_p1(0).getHealth(), 1);
		assertEquals(child1.data_.getMinion_p1(1).getHealth(), 2);
		assertEquals(child1.data_.getMinion_p1(2).getHealth(), 7);

		assertEquals(child1.data_.getMinion_p0(0).getAttack(), 2);
		assertEquals(child1.data_.getMinion_p0(1).getAttack(), 7);
		assertEquals(child1.data_.getMinion_p0(2).getAttack(), 3);
		assertEquals(child1.data_.getMinion_p1(0).getAttack(), 4);
		assertEquals(child1.data_.getMinion_p1(1).getAttack(), 2);
		assertEquals(child1.data_.getMinion_p1(2).getAttack(), 7);
		
	}
}
