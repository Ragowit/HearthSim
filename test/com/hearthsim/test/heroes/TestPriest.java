package com.hearthsim.test.heroes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.hearthsim.card.Card;
import com.hearthsim.card.Deck;
import com.hearthsim.card.minion.Hero;
import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.minion.concrete.ChillwindYeti;
import com.hearthsim.card.minion.heroes.Priest;
import com.hearthsim.card.spellcard.concrete.TheCoin;
import com.hearthsim.exception.HSException;
import com.hearthsim.exception.HSInvalidPlayerIndexException;
import com.hearthsim.player.playercontroller.ArtificialPlayer;
import com.hearthsim.util.BoardState;
import com.hearthsim.util.BoardStateFactory;
import com.hearthsim.util.tree.HearthTreeNode;

public class TestPriest {


	private HearthTreeNode board;
	private Deck deck;

	@Before
	public void setup() {
		board = new HearthTreeNode(new BoardState(new Priest(), new Hero()));

		Minion minion0_0 = new ChillwindYeti();
		Minion minion0_1 = new ChillwindYeti();
		Minion minion1_0 = new ChillwindYeti();
		Minion minion1_1 = new ChillwindYeti();
		
		board.data_.placeCard_hand_p0(minion0_0);
		board.data_.placeCard_hand_p0(minion0_1);
				
		board.data_.placeCard_hand_p1(minion1_0);
		board.data_.placeCard_hand_p1(minion1_1);

		Card cards[] = new Card[10];
		for (int index = 0; index < 10; ++index) {
			cards[index] = new TheCoin();
		}
	
		deck = new Deck(cards);

		board.data_.setMana_p0((byte)8);
		board.data_.setMana_p1((byte)8);
		
		board.data_.setMaxMana_p0((byte)8);
		board.data_.setMaxMana_p1((byte)8);
		
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
		
	}
	
	@Test
	public void test0() throws HSException {
		
		//null case
		
		Minion minion = board.data_.getMinion_p0(0);
		HearthTreeNode ret = minion.attack(0, 1, 0, board, deck, null);
		
		assertFalse(ret == null);
		assertEquals(board.data_.getNumCards_hand(), 0);
		assertEquals(board.data_.getNumMinions_p0(), 2);
		assertEquals(board.data_.getNumMinions_p1(), 2);
		assertEquals(board.data_.getMana_p0(), 8);
		assertEquals(board.data_.getMana_p1(), 8);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 26);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), 5);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), 5);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), 5);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), 5);

		assertEquals(board.data_.getMinion_p0(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), 4);
		
		
		Hero hero = board.data_.getHero_p0();
		ret = hero.useHeroAbility(0, 1, 0, board, deck, null);
		assertEquals(board.data_.getNumCards_hand(), 0);
		assertEquals(board.data_.getNumMinions_p0(), 2);
		assertEquals(board.data_.getNumMinions_p1(), 2);
		assertEquals(board.data_.getMana_p0(), 6);
		assertEquals(board.data_.getMana_p1(), 8);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 28);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), 5);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), 5);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), 5);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), 5);

		assertEquals(board.data_.getMinion_p0(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), 4);
		
		minion.hasAttacked(false);
		ret = minion.attack(0, 1, 2, board, deck, null);
		assertEquals(board.data_.getNumCards_hand(), 0);
		assertEquals(board.data_.getNumMinions_p0(), 2);
		assertEquals(board.data_.getNumMinions_p1(), 2);
		assertEquals(board.data_.getMana_p0(), 6);
		assertEquals(board.data_.getMana_p1(), 8);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 28);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), 1);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), 5);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), 5);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), 1);

		assertEquals(board.data_.getMinion_p0(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), 4);
		
		minion.hasAttacked(false);
		ret = hero.useHeroAbility(0, 1, 2, board, deck, null);
		assertEquals(board.data_.getNumCards_hand(), 0);
		assertEquals(board.data_.getNumMinions_p0(), 2);
		assertEquals(board.data_.getNumMinions_p1(), 2);
		assertEquals(board.data_.getMana_p0(), 4);
		assertEquals(board.data_.getMana_p1(), 8);
		assertEquals(board.data_.getHero_p0().getHealth(), 30);
		assertEquals(board.data_.getHero_p1().getHealth(), 28);
		assertEquals(board.data_.getMinion_p0(0).getHealth(), 1);
		assertEquals(board.data_.getMinion_p0(1).getHealth(), 5);
		assertEquals(board.data_.getMinion_p1(0).getHealth(), 5);
		assertEquals(board.data_.getMinion_p1(1).getHealth(), 3);

		assertEquals(board.data_.getMinion_p0(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p0(1).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(0).getAttack(), 4);
		assertEquals(board.data_.getMinion_p1(1).getAttack(), 4);
		
	}
	
	@Test
	public void test1() throws HSException {
		
		ArtificialPlayer ai0 = new ArtificialPlayer(
				0.9,
				0.9,
				1.0,
				1.0,
				1.0,
				0.1,
				0.1,
				0.1,
				0.5,
				0.5,
				0.0,
				0.5,
				0.0,
				0.0
				);
		
		BoardStateFactory factory = new BoardStateFactory(null, null, 2000000000);
		HearthTreeNode tree = new HearthTreeNode(board.data_);
		try {
			tree = factory.doMoves(tree, ai0);
		} catch (HSException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		//best move is to kill one enemy yeti with 2 of your own yeti and heal one of your own yeti
		HearthTreeNode bestPlay = tree.findMaxOfFunc(ai0);

		assertFalse(tree == null);
		assertEquals(bestPlay.data_.getNumCards_hand(), 0);
		assertEquals(bestPlay.data_.getNumMinions_p0(), 2);
		assertEquals(bestPlay.data_.getNumMinions_p1(), 1);
		assertEquals(bestPlay.data_.getMana_p0(), 6);
		assertEquals(bestPlay.data_.getMana_p1(), 8);
		assertEquals(bestPlay.data_.getHero_p0().getHealth(), 30);
		assertEquals(bestPlay.data_.getHero_p1().getHealth(), 30);
		assertEquals(bestPlay.data_.getMinion_p0(0).getHealth(), 3);
		assertEquals(bestPlay.data_.getMinion_p0(1).getHealth(), 1);
		assertEquals(bestPlay.data_.getMinion_p1(0).getHealth(), 5);

		assertEquals(bestPlay.data_.getMinion_p0(0).getAttack(), 4);
		assertEquals(bestPlay.data_.getMinion_p0(1).getAttack(), 4);
		assertEquals(bestPlay.data_.getMinion_p1(0).getAttack(), 4);
		
	}
	
	@Test
	public void test2() throws HSException {
		
		
		//one of your yeti is damaged
		board.data_.getMinion_p0(0).setHealth((byte)3);
		
		ArtificialPlayer ai0 = new ArtificialPlayer(
				0.9,
				0.9,
				1.0,
				1.0,
				1.0,
				0.1,
				0.1,
				0.1,
				0.5,
				0.5,
				0.0,
				0.5,
				0.0,
				0.0
				);
		
		BoardStateFactory factory = new BoardStateFactory(null, null, 2000000000);
		HearthTreeNode tree = new HearthTreeNode(board.data_);
		try {
			tree = factory.doMoves(tree, ai0);
		} catch (HSException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
		//best move is to heal the damaged yeti and attack with both to kill one of the enemy's yeti
		HearthTreeNode bestPlay = tree.findMaxOfFunc(ai0);

		assertFalse(tree == null);
		assertEquals(bestPlay.data_.getNumCards_hand(), 0);
		assertEquals(bestPlay.data_.getNumMinions_p0(), 2);
		assertEquals(bestPlay.data_.getNumMinions_p1(), 1);
		assertEquals(bestPlay.data_.getMana_p0(), 6);
		assertEquals(bestPlay.data_.getMana_p1(), 8);
		assertEquals(bestPlay.data_.getHero_p0().getHealth(), 30);
		assertEquals(bestPlay.data_.getHero_p1().getHealth(), 30);
		assertEquals(bestPlay.data_.getMinion_p0(0).getHealth(), 1);
		assertEquals(bestPlay.data_.getMinion_p0(1).getHealth(), 1);
		assertEquals(bestPlay.data_.getMinion_p1(0).getHealth(), 5);

		assertEquals(bestPlay.data_.getMinion_p0(0).getAttack(), 4);
		assertEquals(bestPlay.data_.getMinion_p0(1).getAttack(), 4);
		assertEquals(bestPlay.data_.getMinion_p1(0).getAttack(), 4);
		
	}
}
