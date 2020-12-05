package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

class NumericGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    areTestGameAnswers: Boolean,
    patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<Int>>
) : GameAnswersRequest<Int>(category, gameId, areTestGameAnswers, patientTaskAnswersRequestList)
