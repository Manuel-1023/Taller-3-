package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Loans;

public class LoansService implements ILoansService {
	private final List<Loans> storage = new ArrayList<>();

	@Override
	public Loans save(Loans loan) {
		String id = loan.getDate() != null ? loan.getDate().toString() : null;
		if (id != null) findById(id).ifPresent(existing -> storage.remove(existing));
		storage.add(loan);
		return loan;
	}

	@Override
	public Optional<Loans> findById(String id) {
		return storage.stream().filter(l -> l.getDate() != null && l.getDate().toString().equals(id)).findFirst();
	}

	@Override
	public List<Loans> findAll() {
		return new ArrayList<>(storage);
	}

	@Override
	public boolean deleteById(String id) {
		return findById(id).map(storage::remove).orElse(false);
	}
}
