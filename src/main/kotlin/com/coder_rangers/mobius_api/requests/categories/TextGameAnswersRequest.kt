package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

class TextGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    areTestGameAnswers: Boolean,
    patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<String>>
) : GameAnswersRequest<String>(category, gameId, areTestGameAnswers, patientTaskAnswersRequestList)
