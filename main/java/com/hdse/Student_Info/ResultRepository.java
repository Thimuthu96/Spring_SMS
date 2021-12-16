package com.hdse.Student_Info;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

	@Query("SELECT r FROM Result r WHERE r.first_name LIKE %?1% "
			+ " AND r.grade LIKE %?2% "
			+ " AND r.subject LIKE %?3% "
			+ " AND r.month LIKE %?4% ")
	public List<Result> findByKeyword(String first_name,String grade,String subject,String month);
	
}
