package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Balance;

public class BalanceService implements IBalanceService {
	private final List<Balance> storage = new ArrayList<>();

	@Override
	public Balance save(Balance balance) {
		String id = balance.getDate() != null ? balance.getDate().toString() : null;
		if (id != null) findById(id).ifPresent(existing -> storage.remove(existing));
		storage.add(balance);
		return balance;
	}

	@Override
	public Optional<Balance> findById(String id) {
		return storage.stream().filter(b -> b.getDate() != null && b.getDate().toString().equals(id)).findFirst();
	}

	@Override
	public List<Balance> findAll() {
		return new ArrayList<>(storage);
	}

	@Override
	public boolean deleteById(String id) {
		return findById(id).map(storage::remove).orElse(false);
	}
}
