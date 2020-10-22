package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.TaskAnswer

class OrientationTestGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    taskAnswers: List<TaskAnswer<Boolean>>
) : TestGameAnswersRequest(category, gameId, taskAnswers)
