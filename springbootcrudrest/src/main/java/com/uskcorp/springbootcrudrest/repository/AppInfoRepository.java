package com.uskcorp.springbootcrudrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uskcorp.springbootcrudrest.model.AppInfo;

@Repository
public interface AppInfoRepository extends JpaRepository<AppInfo, Long> {

}