package me.ratna.invoice2.repositories;

import me.ratna.invoice2.models.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository <Item, Long>{


}
