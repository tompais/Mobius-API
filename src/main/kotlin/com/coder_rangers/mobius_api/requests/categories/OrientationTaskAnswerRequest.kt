package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.TaskAnswer

class OrientationTaskAnswerRequest(
    category: Game.Category,
    gameId: Long,

    val taskAnswers: List<TaskAnswer<Boolean>>
) : GameAnswersRequest(category, gameId)
