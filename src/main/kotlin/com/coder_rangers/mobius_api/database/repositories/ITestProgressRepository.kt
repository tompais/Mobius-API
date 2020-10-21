package com.coder_rangers.mobius_api.database.repositories

import com.coder_rangers.mobius_api.models.TestProgress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface ITestProgressRepository : JpaRepository<TestProgress, Long>
