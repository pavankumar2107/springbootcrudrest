package com.uskcorp.springbootcrudrest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uskcorp.springbootcrudrest.exception.ResourceNotFoundException;
import com.uskcorp.springbootcrudrest.model.AppInfo;
import com.uskcorp.springbootcrudrest.repository.AppInfoRepository;

@RestController
@RequestMapping("/api")
public class AppInfoController {

	@Autowired
	private AppInfoRepository repository;

	@GetMapping("/getAll")
	public List<AppInfo> getAll() {
		return repository.findAll();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<AppInfo> getById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		AppInfo appInfo = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No data found on id:: " + id));
		return ResponseEntity.ok().body(appInfo);
	}

	@PostMapping("/create")
	public AppInfo create(@Valid @RequestBody AppInfo appInfo) {
		return repository.save(appInfo);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AppInfo> update(@PathVariable(value = "id") Long id,
			@Valid @RequestBody AppInfo appInfoDetails) throws ResourceNotFoundException {
		AppInfo appInfo = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No data found on id:: " + id));

		appInfo.setText(appInfoDetails.getText());
		final AppInfo updatedInfo = repository.save(appInfo);
		return ResponseEntity.ok(updatedInfo);
	}

	@DeleteMapping("/delete/{id}")
	public Map<String, Boolean> delete(@PathVariable(value = "id") Long id) throws Exception {
		AppInfo appInfo = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No data found on id:: " + id));

		repository.delete(appInfo);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}