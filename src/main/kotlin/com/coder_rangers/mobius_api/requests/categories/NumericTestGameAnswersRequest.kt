package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

class NumericTestGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<Int>>
) : TestGameAnswersRequest<Int>(category, gameId, patientTaskAnswersRequestList)
