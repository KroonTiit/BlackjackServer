package com.blackjack.tiit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.tiit.cards.Deck;
import com.blackjack.tiit.game.GameRound;
import com.blackjack.tiit.game.Hand;
import com.blackjack.tiit.player.Player;
import com.blackjack.tiit.player.PlayerRepo;


@RestController
@RequestMapping("/")
public class Controller {
	private Logger logger = LoggerFactory.getLogger(Controller.class);
	@Autowired
	private PlayerRepo playerRepo;
	private Deck theDeck= new Deck();
	private Player dealer= new Player("Dealer", 100000);
	
	@RequestMapping(value = "createPlayer", method = RequestMethod.POST)
	public Player createPlayer(@RequestBody Player player) {
		logger.info("new player created: "+player.getName());
		return playerRepo.save(player);
	}
	
	@RequestMapping(value = "getPlayerByName/{name}", method = RequestMethod.GET)
	public Player getPlayerByName(@PathVariable String name) {
		Player player = playerRepo.findByName(name);
		if(player != null) {
			logger.info("Player "+player.getName()+" logger in");
		} else {
			logger.info("Player "+name+" tryed logging in, no player found in db");
		}
		return player;
	}
	
	@RequestMapping(value = "addCashToPlayer", method = RequestMethod.POST)
	public Player addCashToPlayer(@RequestBody Player player) {
		Player newPlayer = playerRepo.findByName(player.getName());
		newPlayer.setCash(newPlayer.getCash()+player.getCash());
		logger.info("Player "+player.getName()+" added to account:"+ player.getCash());
		return playerRepo.save(newPlayer);
	}
	@RequestMapping(value = "dealHand", method = RequestMethod.POST)
	public GameRound dealHand(@RequestBody Player player) {
		theDeck= new Deck(5,true);
		Hand dealersHand = new Hand(dealer);
		Hand playersHand = new Hand(player);
		dealersHand.addCard(theDeck.dealNextCard());
		playersHand.addCard(theDeck.dealNextCard());
		dealersHand.addCard(theDeck.dealNextCard());
		playersHand.addCard(theDeck.dealNextCard());
		dealersHand.setHandSum();
		playersHand.setHandSum();
		GameRound game = new GameRound(dealersHand, playersHand);
		return game;
	}
	
	@RequestMapping(value = "dealPlayer", method = RequestMethod.POST)
	public GameRound dealPlayer(@RequestBody GameRound game) {
		game.getPlayersHand().addCard(theDeck.dealNextCard());
		return game;
	}
	
	@RequestMapping(value = "dealDealer", method = RequestMethod.POST)
	public GameRound dealDealer(@RequestBody GameRound game) {
		Hand dealersHand = game.getDealersHand();
		while(true) {
			dealersHand.setHandSum();
			if(dealersHand.getHandSum() < 17) {
				dealersHand.addCard(theDeck.dealNextCard());
			} else {
				break;
			}
		}
		return game;
	}
	
	@RequestMapping(value = "dealWin", method = RequestMethod.POST)
	public Player dealWinnings(@RequestBody Player player) {
		player.setCash(player.getCash()+player.getBet());
		logger.info("Player "+player.getName()+" won:"+ player.getBet());
		return playerRepo.save(player);
	}
	
	@RequestMapping(value = "dealLoss", method = RequestMethod.POST)
	public Player dealLoss(@RequestBody Player player) {
		player.setCash(player.getCash()-player.getBet());
		logger.info("Player "+player.getName()+" lost:"+ player.getBet());
		return playerRepo.save(player);
	}
	
}
