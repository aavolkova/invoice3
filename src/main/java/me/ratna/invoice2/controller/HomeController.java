package me.ratna.invoice2.controller;

import me.ratna.invoice2.models.Choice;
import me.ratna.invoice2.models.Item;
import me.ratna.invoice2.models.Transaction;
import me.ratna.invoice2.repositories.ItemRepo;
import me.ratna.invoice2.repositories.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {


    @Autowired
    ItemRepo itemRepo;

    @Autowired
    TransactionRepo transactionRepo;


    @GetMapping("/")
    public String homePage(){
        return "index";
    }
//-----------------Owner Stuff-----------------------------

    @GetMapping("/owner")
    public String ownerPage (){
        return "owner";
    }

    @GetMapping("/addItem")
    public String addItem(Model model){

        model.addAttribute("newItem", new Item());
        return "addItem";
    }
    @PostMapping("/addItem")
        public String listAllItems(Model model,@ModelAttribute ("newItem") Item newItem, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return "addItem";
        }
        newItem.setTaxPrice();
        newItem.setTotalPrice();
        itemRepo.save(newItem);
        Iterable<Item> arrAll = itemRepo.findAll();

        model.addAttribute("allItems", arrAll);
        return "listAllItems";
    }

    @GetMapping("/listAllItems")
    public String showAllItems(Model model){
        Iterable<Item> arrAll = itemRepo.findAll();
        model.addAttribute("allItems", arrAll);
        return "listAllItems";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        itemRepo.delete(id);
        return "redirect:/listAllItems";
    }
    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") long id,Model model){
        Item anItem = itemRepo.findOne(id);
        model.addAttribute("newItem", anItem);
        return "addItem";
    }



//----------------- client stuff--------------------

    @GetMapping("/client")
    public String clientPage (){
        return "shopper/client";
    }

    @GetMapping("/listAllItemsShopper")
    public String showAllItemsShopper(Model model){
        Iterable<Item> arrAll = itemRepo.findAll();
        model.addAttribute("allItems", arrAll);
        model.addAttribute("customerChoice",new Choice());
        return "shopper/listAllItemsShopper";
    }
    @PostMapping("/listAllItemsShopper")
    public String addItemsToCart(Model model, @ModelAttribute("customerChoice") Choice customerChoice){
        long thisId=customerChoice.getIdChoice();
        int thisQuantity=customerChoice.getQntyChoice();

        //set the quantity to a different number
        Item anItem=itemRepo.findOne(thisId);
        int currentQuantity = anItem.getQuantity();
        anItem.setQuantity(currentQuantity-thisQuantity);
        update(thisId,model);


        //make a new transaction and populate it with the item data
        Transaction aTransaction= new Transaction();
        aTransaction.setItemId(thisId);
        aTransaction.setAQuantity(thisQuantity);
        aTransaction.setDescription(anItem.getDescription());
        aTransaction.setPrice(anItem.getPrice());
        aTransaction.setTaxPrice(anItem.getTaxPrice());
        aTransaction.setTotalPrice();

        //save the transaction
        transactionRepo.save(aTransaction);

        model.addAttribute("anItem", anItem);
        model.addAttribute("aTrans", aTransaction);



        return "shopper/lastTransaction";
    }
    @GetMapping("/printRecipt")
    public String printRecipt(Model model){
      double taxDue = 0;
      double totalPurchasePrice=0;
      double priceDue = 0;

      Iterable<Transaction> list = transactionRepo.findAll();
      for(Transaction aTrans: list){
          taxDue=taxDue+aTrans.getTaxPrice();
          priceDue=priceDue+aTrans.getPrice();
      }
        totalPurchasePrice=taxDue+priceDue;

        model.addAttribute("list",list);
        model.addAttribute("priceDue", priceDue);
        model.addAttribute("taxDue",taxDue);
        model.addAttribute("totalPurchasePrice", totalPurchasePrice);

        return "shopper/printRecipt";
    }

}


