package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.AnswerWithResult
import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

class TextTestGameAnswersWithResultsRequest(
    category: Game.Category,
    gameId: Long,
    patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<AnswerWithResult<String>>>
) : TestGameAnswersRequest<AnswerWithResult<String>>(category, gameId, patientTaskAnswersRequestList)
