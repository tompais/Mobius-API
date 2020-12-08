package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Task

fun List<Task.Result>.groupByCategory(): Map<Category, List<Task.Result>> =
    groupBy { taskResult -> taskResult.task.game!!.category }

fun List<Task.Result>.mapToCategoriesWithPercentages(): List<Pair<Category, Int>> =
    groupByCategory().map { patientResult ->
        patientResult.key to patientResult.value.sumBy { result -> result.score } * 100 / patientResult.value.map { result -> result.task.answers!!.count { answer -> answer.type == Answer.Type.EXPECTED } }
            .sum()
    }
