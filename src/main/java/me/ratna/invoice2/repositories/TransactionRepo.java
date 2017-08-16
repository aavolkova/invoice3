package me.ratna.invoice2.repositories;


import me.ratna.invoice2.models.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.awt.*;

public interface TransactionRepo extends CrudRepository<Transaction, Long> {
}
