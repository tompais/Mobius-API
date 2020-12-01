package com.coder_rangers.mobius_api.database.repositories

import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface ITaskResultRepository : JpaRepository<Task.Result, Long> {
    @Query("SELECT SUM(tr.score) FROM Result tr WHERE tr.patient.id = ?1 AND tr.task.game.isTestGame = true")
    @RestResource(exported = false)
    fun getTestTotalScore(patientId: Long): Int

    @Query("FROM Result tr WHERE tr.patient.id = ?1 AND tr.task.game.category IN ?2")
    @RestResource(exported = false)
    fun getResultsByPatientAndGameCategories(patientId: Long, gameCategories: List<Category>): List<Task.Result>
}
