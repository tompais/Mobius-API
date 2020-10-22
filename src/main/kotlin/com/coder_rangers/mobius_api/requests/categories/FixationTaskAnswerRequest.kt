package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.TaskAnswer

class FixationTaskAnswerRequest(
    category: Game.Category,
    gameId: Long,

    val taskAnswers: List<TaskAnswer<String>>
) : GameAnswersRequest(category, gameId)
