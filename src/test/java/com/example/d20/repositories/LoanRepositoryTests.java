package com.example.d20.repositories;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.d20.model.User;
import com.example.d20.model.Game;
import com.example.d20.model.Loan;
import com.example.d20.model.Ownership;
import com.example.d20.repository.GameRepository;
import com.example.d20.repository.LoanRepository;
import com.example.d20.repository.OwnershipRepository;
import com.example.d20.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LoanRepositoryTests {
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GameRepository gameRepository;
	
	@Autowired
	OwnershipRepository ownershipRepository;
	
	// checking whether the data is being persisted or not
	@Test
	public void persistenceTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		User loanee = new User("Pigmeu", "Zinho", "43211555", "pigpig@oink.com");
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		userRepository.save(owner);
		userRepository.save(loanee);
		gameRepository.save(game);
		ownershipRepository.save(ownership);
		
		Loan loan = new Loan(ownership, loanee, 20.0);
		Assertions.assertThat(loan.getPrice()).isEqualTo(20.0);
		
		Assertions.assertThat( loanRepository.findAll().size() ).isEqualTo(0);
		loanRepository.save(loan);
		
		Assertions.assertThat( loanRepository.findAll().size() ).isEqualTo(1);
		Assertions.assertThat( loanRepository.findAll().get(0) ).isEqualTo(loan);
	}
	
	// checking if the data persisted is being deleted as it should
	@Test
	public void deleteTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		User loanee = new User("Pigmeu", "Zinho", "43211555", "pigpig@oink.com");
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		userRepository.save(owner);
		userRepository.save(loanee);
		gameRepository.save(game);
		ownershipRepository.save(ownership);
		
		Loan loan = new Loan(ownership, loanee, 20.0);
		
		loanRepository.save(loan);
		Assertions.assertThat( loanRepository.findAll().size() ).isEqualTo(1);
		
		loanRepository.delete(loan);
		Assertions.assertThat( loanRepository.findAll().size() ).isEqualTo(0);
	}
	
	// checking if the data persisted is being updated as it should
	@Test
	public void updateTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		User loanee = new User("Pigmeu", "Zinho", "43215551", "pigpig@oink.com");
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		userRepository.save(owner);
		userRepository.save(loanee);
		gameRepository.save(game);
		ownershipRepository.save(ownership);
		
		Loan loan = new Loan(ownership, loanee, 20.0);
		loanRepository.save(loan);
		
		loan.setPrice(25.5);
		loanRepository.save(loan);
		
		Ownership updatedOwnership = ownershipRepository.findAll().get(0);
		Assertions.assertThat(updatedOwnership).isEqualTo(ownership);
	}
	
	// checking if findAllByLoanee is working as intended
	@Test
	public void findAllByLoaneeTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		Game game2 = new Game("UAAAAAAAA", "Cartas", "Vrau");
		
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		User loanee = new User("Pigmeu", "Zinho", "43215551", "pigpig@oink.com");
		
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		Ownership ownership2 = new Ownership(owner, game2, 5.3, "yooo", true);
		
		userRepository.save(owner);
		userRepository.save(loanee);
		
		gameRepository.save(game);
		gameRepository.save(game2);
		
		ownershipRepository.save(ownership);
		ownershipRepository.save(ownership2);
		
		Loan loan = new Loan(ownership, loanee, 20.0);
		Loan loan2 = new Loan(ownership2, loanee, 10.5);
		
		loanRepository.save(loan);
		loanRepository.save(loan2);
		
		Assertions.assertThat( loanRepository.findAllByLoanee(loanee).size() ).isEqualTo(2);
		Assertions.assertThat( loanRepository.findAllByLoanee(loanee).get(0) ).isEqualTo(loan);
		Assertions.assertThat( loanRepository.findAllByLoanee(loanee).get(1) ).isEqualTo(loan2);
	}
	
	// checking if findAllByItem is working as intended
	@Test
	public void findAllByItemTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		User loanee = new User("Pigmeu", "Zinho", "43215551", "pigpig@oink.com");
		User loanee2 = new User("Dougao", "Watson", "12343555", "dougasso@vrauvrau.com");
		
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		userRepository.save(owner);
		userRepository.save(loanee);
		userRepository.save(loanee2);
		
		gameRepository.save(game);
		
		ownershipRepository.save(ownership);
		
		Loan loan = new Loan(ownership, loanee, 20.0);
		Loan loan2 = new Loan(ownership, loanee2, 10.5);
		
		loanRepository.save(loan);
		loanRepository.save(loan2);
		
		Assertions.assertThat( loanRepository.findAllByItem(ownership).size() ).isEqualTo(2);
		Assertions.assertThat( loanRepository.findAllByItem(ownership).get(0) ).isEqualTo(loan);
		Assertions.assertThat( loanRepository.findAllByItem(ownership).get(1) ).isEqualTo(loan2);
	}
	
	// checking if findAllByFinished is working as intended
	@Test
	public void findAllByFinishedTest() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		User loanee = new User("Pigmeu", "Zinho", "43215551", "pigpig@oink.com");
		User loanee2 = new User("Dougao", "Watson", "12555343", "dougasso@vrauvrau.com");
		
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		userRepository.save(owner);
		userRepository.save(loanee);
		userRepository.save(loanee2);
		
		gameRepository.save(game);
		
		ownershipRepository.save(ownership);
		
		Loan loan = new Loan(ownership, loanee, 20.0);
		Loan loan2 = new Loan(ownership, loanee2, 10.5);
		
		loanRepository.save(loan);
		loanRepository.save(loan2);
		
		Assertions.assertThat( loanRepository.findAllByFinished(false).size() ).isEqualTo(2);
		Assertions.assertThat( loanRepository.findAllByFinished(false).get(0) ).isEqualTo(loan);
		Assertions.assertThat( loanRepository.findAllByFinished(false).get(1) ).isEqualTo(loan2);
	}
	
	// checking if findAllByFinished is working as intended
	@Test
	public void findAllByFinishedTest2() {
		Game game = new Game("Munchkin", "Tabuleiro", "RPG");
		
		User owner = new User("Matheus", "Oliveira", "12131212", "aaa@gmail.com");
		User loanee = new User("Pigmeu", "Zinho", "43211555", "pigpig@oink.com");
		User loanee2 = new User("Dougao", "Watson", "12345553", "dougasso@vrauvrau.com");
		
		Ownership ownership = new Ownership(owner, game, 15.5, "Teste", true);
		
		userRepository.save(owner);
		userRepository.save(loanee);
		userRepository.save(loanee2);
		
		gameRepository.save(game);
		
		ownershipRepository.save(ownership);
		
		Loan loan = new Loan(ownership, loanee, 20.0);
		Loan loan2 = new Loan(ownership, loanee2, 10.5);
		
		loan.finishLoan();
		loan2.finishLoan();
		
		loanRepository.save(loan);
		loanRepository.save(loan2);
		
		Assertions.assertThat( loanRepository.findAllByFinished(false).size() ).isEqualTo(0);
		Assertions.assertThat( loanRepository.findAllByFinished(true).size() ).isEqualTo(2);
		
		Assertions.assertThat( loanRepository.findAllByFinished(true).get(0) ).isEqualTo(loan);
		Assertions.assertThat( loanRepository.findAllByFinished(true).get(1) ).isEqualTo(loan2);
	}
}
