package com.uskcorp.springbootcrudrest.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uskcorp.springbootcrudrest.exception.ResourceNotFoundException;
import com.uskcorp.springbootcrudrest.model.Expense;
import com.uskcorp.springbootcrudrest.repository.ExpenseTestRepository;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin
public class ExpenseTestController {
	//
	@Autowired
	private ExpenseTestRepository repository;

	@GetMapping("/getAll")
	public List<Expense> getAll() {
		System.out.println("balance:" + repository.findBalance());
		return (List<Expense>) repository.findAll();
		
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Expense> getById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Expense expense = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No data found on id:: " + id));
		return ResponseEntity.ok().body(expense);
	}

	@PostMapping("/create")
	public Expense create(@Valid @RequestBody Expense expense) {
		return repository.save(expense);
	}

	@PostMapping("/insert")
	public Expense insert(@Valid @RequestBody Expense expense) throws Exception {
		long avlBal = 0l;
		try {
			avlBal = repository.findBalance();
		} catch (Exception e) {
			avlBal = 0;
		}
		/*
		 * if (repository.findBalance() == null) { avlBal = 0; }
		 */
		if (expense.isTypeOfExpense()) {// for credit if true
			long amount = expense.getCredit();
			long totalBal = avlBal + amount;
			Expense e1 = new Expense(new Date(), expense.getPurposeOfExpense(), avlBal, expense.getCredit(), 0,
					new Date(), null, expense.getDescription(), totalBal,expense.isTypeOfExpense());
			return repository.save(e1);
		} else {
			long amount = expense.getDebit();
			long totalBal = avlBal - amount;
			if (amount < avlBal) {
				Expense e1 = new Expense(new Date(), expense.getPurposeOfExpense(), avlBal, 0, expense.getDebit(),
						new Date(), null, expense.getDescription(), totalBal,expense.isTypeOfExpense());
				return repository.save(e1);
			} else {
				throw new Exception("balance not available");
			}

		}
	}
	
	@GetMapping("/getById/{fromDate}/{toDate}")
	public List<Expense> getById(@PathVariable(value = "fromDate") String fromDate,@PathVariable(value = "toDate") String toDate) throws ResourceNotFoundException {
		
//		java.text.SimpleDateFormat sdf = 
//			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//			String fDate = sdf.format(fromDate);
//			String tDate = sdf.format(toDate);
			
		List<Expense> expense = repository.findByFromAndToDate1(fromDate,toDate);
//				.orElseThrow(() -> new ResourceNotFoundException("No data found on id:: " + id));
		return expense;
	}
	/*java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String currentTime = sdf.format(dt);*/
	
	
	
	
	//SELECT * FROM expense_test WHERE createddate BETWEEN DATE_SUB(NOW(), INTERVAL 30 DAY) AND NOW()
	
	//SELECT * FROM expense_test WHERE (createddate BETWEEN '2019-02-04 03:00:00' AND '2019-02-04 15:11:50')
	/*
	 * @PutMapping("/update/{id}") public ResponseEntity<AppInfo>
	 * update(@PathVariable(value = "id") Long id,
	 * 
	 * @Valid @RequestBody AppInfo appInfoDetails) throws
	 * ResourceNotFoundException { AppInfo appInfo = repository.findById(id)
	 * .orElseThrow(() -> new ResourceNotFoundException("No data found on id:: "
	 * + id));
	 * 
	 * appInfo.setText(appInfoDetails.getText()); final AppInfo updatedInfo =
	 * repository.save(appInfo); return ResponseEntity.ok(updatedInfo); }
	 * 
	 * @DeleteMapping("/delete/{id}") public Map<String, Boolean>
	 * delete(@PathVariable(value = "id") Long id) throws Exception { AppInfo
	 * appInfo = repository.findById(id) .orElseThrow(() -> new
	 * ResourceNotFoundException("No data found on id:: " + id));
	 * 
	 * repository.delete(appInfo); Map<String, Boolean> response = new
	 * HashMap<>(); response.put("deleted", Boolean.TRUE); return response; }
	 */
}