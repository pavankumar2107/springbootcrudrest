package com.uskcorp.springbootcrudrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uskcorp.springbootcrudrest.model.Expense;

@Repository
public interface ExpenseTestRepository extends CrudRepository<Expense,Long>,JpaSpecificationExecutor<Expense>{
//extends JpaRepository<Expense, Long> {
	@Query(value = "SELECT SUM(COALESCE(credit,0) - COALESCE(debit,0))  as balance FROM expense_test", 
			  nativeQuery = true) 
    Long findBalance();
	
	 @Query(value="select * from Expense_Test",nativeQuery = true)
	 List<Expense> findByFromAndToDate(@Param("fromDate") int fromDate,
	                                 @Param("toDate") int toDate);
	 
	 @Query(value="SELECT * FROM expense_test WHERE (createddate BETWEEN :fromDate AND :toDate)",nativeQuery = true)
	 List<Expense> findByFromAndToDate1(@Param("fromDate") String fromDate,
	                                 @Param("toDate") String toDate);
}
	