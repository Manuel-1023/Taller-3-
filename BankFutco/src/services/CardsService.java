package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Cards;

public class CardsService implements ICardsService {
	private final List<Cards> storage = new ArrayList<>();

	@Override
	public Cards save(Cards card) {
		if (card.getCardNumber() != null) findById(card.getCardNumber()).ifPresent(existing -> storage.remove(existing));
		storage.add(card);
		return card;
	}

	@Override
	public Optional<Cards> findById(String cardNumber) {
		return storage.stream().filter(c -> c.getCardNumber() != null && c.getCardNumber().equals(cardNumber)).findFirst();
	}

	@Override
	public List<Cards> findAll() {
		return new ArrayList<>(storage);
	}

	@Override
	public boolean deleteById(String cardNumber) {
		return findById(cardNumber).map(storage::remove).orElse(false);
	}
}
