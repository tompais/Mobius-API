package com.coder_rangers.mobius_api.database.repositories

import com.coder_rangers.mobius_api.models.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
@RepositoryRestResource(exported = false)
interface ITaskRepository : JpaRepository<Task, Long>
