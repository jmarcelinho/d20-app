package com.example.d20.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.Game;
import com.example.d20.model.Loan;
import com.example.d20.model.Ownership;
import com.example.d20.model.User;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanServiceTests {
	
	@Autowired
	LoanService loanService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	GameService gameService;
	
	@Autowired
	OwnershipService ownershipService;
	
	// checking whether the data is being persisted or not
		@Test
		public void persistenceTest() {
			Game game = new Game("Munchkin", "Tabuleiro", "RPG");
			User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
			User loanee = new User("Pigmeu", "Zinho", "43255511", "pigpig@oink.com");
			Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
			
			userService.addUser(owner);
			userService.addUser(loanee);
			gameService.addGame(game);
			ownershipService.addOwnership(ownership);
			
			Loan loan = new Loan(ownership, loanee, 20.0);
			Assertions.assertThat(loan.getPrice()).isEqualTo(20.0);
			
			Assertions.assertThat( loanService.getAllLoans().size() ).isEqualTo(0);
			loanService.addLoan(loan);
			
			Assertions.assertThat( loanService.getAllLoans().size() ).isEqualTo(1);
			Assertions.assertThat( loanService.getAllLoans().get(0) ).isEqualTo(loan);
		}
		
		// checking if the data persisted is being deleted as it should
		@Test
		public void deleteTest() {
			Game game = new Game("Munchkin", "Tabuleiro", "RPG");
			User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
			User loanee = new User("Pigmeu", "Zinho", "43255511", "pigpig@oink.com");
			Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
			
			userService.addUser(owner);
			userService.addUser(loanee);
			gameService.addGame(game);
			ownershipService.addOwnership(ownership);
			
			Loan loan = new Loan(ownership, loanee, 20.0);
			
			loanService.addLoan(loan);
			Assertions.assertThat( loanService.getAllLoans().size() ).isEqualTo(1);
			
			loanService.delete(loan.getId());
			Assertions.assertThat( loanService.getAllLoans().size() ).isEqualTo(0);
		}
		
		// checking if findAllByLoanee is working as intended
		@Test
		public void findAllByLoaneeTest() {
			Game game = new Game("Munchkin", "Tabuleiro", "RPG");
			Game game2 = new Game("UAAAAAAAA", "Cartas", "Vrau");
			
			User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
			User loanee = new User("Pigmeu", "Zinho", "43255511", "pigpig@oink.com");
			
			Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
			Ownership ownership2 = new Ownership(owner, game2, 5.3, "yooo", true);
			
			userService.addUser(owner);
			userService.addUser(loanee);
			gameService.addGame(game);
			gameService.addGame(game2);
			
			ownershipService.addOwnership(ownership);
			ownershipService.addOwnership(ownership2);
			
			Loan loan = new Loan(ownership, loanee, 20.0);
			Loan loan2 = new Loan(ownership2, loanee, 10.5);
			
			loanService.addLoan(loan);
			loanService.addLoan(loan2);
			
			Assertions.assertThat( loanService.getLoanByLoanee(loanee).size() ).isEqualTo(2);
			Assertions.assertThat( loanService.getLoanByLoanee(loanee).get(0) ).isEqualTo(loan);
			Assertions.assertThat( loanService.getLoanByLoanee(loanee).get(1) ).isEqualTo(loan2);
		}
		
		// checking if findAllByItem is working as intended
		@Test
		public void findAllByItemTest() {
			Game game = new Game("Munchkin", "Tabuleiro", "RPG");
			
			User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
			User loanee = new User("Pigmeu", "Zinho", "43255511", "pigpig@oink.com");
			User loanee2 = new User("Dougao", "Watson", "12355543", "dougasso@vrauvrau.com");
			
			Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
			
			userService.addUser(owner);
			userService.addUser(loanee);
			userService.addUser(loanee2);
			
			gameService.addGame(game);
			
			ownershipService.addOwnership(ownership);
			
			Loan loan = new Loan(ownership, loanee, 20.0);
			Loan loan2 = new Loan(ownership, loanee2, 10.5);
			
			loanService.addLoan(loan);
			loanService.addLoan(loan2);
			
			Assertions.assertThat( loanService.getLoanByItem(ownership).size() ).isEqualTo(2);
			Assertions.assertThat( loanService.getLoanByItem(ownership).get(0) ).isEqualTo(loan);
			Assertions.assertThat( loanService.getLoanByItem(ownership).get(1) ).isEqualTo(loan2);
		}
		
		// checking if findAllByFinished is working as intended
		@Test
		public void findAllByFinishedTest() {
			Game game = new Game("Munchkin", "Tabuleiro", "RPG");
			
			User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
			User loanee = new User("Pigmeu", "Zinho", "43215551", "pigpig@oink.com");
			User loanee2 = new User("Dougao", "Watson", "12345553", "dougasso@vrauvrau.com");
			
			Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
			
			userService.addUser(owner);
			userService.addUser(loanee);
			userService.addUser(loanee2);
			
			gameService.addGame(game);
			
			ownershipService.addOwnership(ownership);
			
			Loan loan = new Loan(ownership, loanee, 20.0);
			Loan loan2 = new Loan(ownership, loanee2, 10.5);
			
			loanService.addLoan(loan);
			loanService.addLoan(loan2);
			
			Assertions.assertThat( loanService.getLoanByStatus(false).size() ).isEqualTo(2);
			Assertions.assertThat( loanService.getLoanByStatus(false).get(0) ).isEqualTo(loan);
			Assertions.assertThat( loanService.getLoanByStatus(false).get(1) ).isEqualTo(loan2);
		}
		
		// checking if findAllByStatus is working as intended
		@Test
		public void findAllByFinishedTest2() {
			Game game = new Game("Munchkin", "Tabuleiro", "RPG");
			
			User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
			User loanee = new User("Pigmeu", "Zinho", "43255511", "pigpig@oink.com");
			User loanee2 = new User("Dougao", "Watson", "15552343", "dougasso@vrauvrau.com");
			
			Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
			
			userService.addUser(owner);
			userService.addUser(loanee);
			userService.addUser(loanee2);
			
			gameService.addGame(game);
			
			ownershipService.addOwnership(ownership);
			
			Loan loan = new Loan(ownership, loanee, 20.0);
			Loan loan2 = new Loan(ownership, loanee2, 10.5);
			
			loan.finishLoan();
			loan2.finishLoan();
			
			loanService.addLoan(loan);
			loanService.addLoan(loan2);
			
			Assertions.assertThat( loanService.getLoanByStatus(false).size() ).isEqualTo(0);
			Assertions.assertThat( loanService.getLoanByStatus(true).size() ).isEqualTo(2);
			
			Assertions.assertThat( loanService.getLoanByStatus(true).get(0) ).isEqualTo(loan);
			Assertions.assertThat( loanService.getLoanByStatus(true).get(1) ).isEqualTo(loan2);
		}
}
