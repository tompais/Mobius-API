package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.AnswerWithResult
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

class TextGameAnswersWithResultsRequest(
    category: Game.Category,
    gameId: Long,
    isTestGame: Boolean,
    patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<AnswerWithResult<String>>>
) : GameAnswersRequest<AnswerWithResult<String>>(category, gameId, isTestGame, patientTaskAnswersRequestList)
